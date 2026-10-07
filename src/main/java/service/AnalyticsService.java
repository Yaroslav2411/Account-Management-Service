package service;
import entity.BankAccount;
import entity.Transaction;
import entity.TransactionType;
import entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class AnalyticsService {
    public BigDecimal getMonthlySpendingByCategory(BankAccount bankAccount, String category) {
        BigDecimal totalSum = BigDecimal.ZERO;
        if (bankAccount == null || !bankAccount.getTransaction().contains(category))
            return totalSum;
        LocalDateTime oneMonth = LocalDateTime.now().minusMonths(1L);
        for (Transaction transaction : bankAccount.getTransaction()) {
            if (TransactionType.PAYMENT.equals(transaction.getType())
                    && transaction.getCategory().contains(category)
                    && transaction.getCreatedDate().isAfter(oneMonth)) ;
            totalSum = totalSum.add(transaction.getValue());
        }
        return totalSum;
    }

    public Map<String, BigDecimal> getMonthlySpendingByCategories(User user, Set<String> categories) {
        Map<String, BigDecimal> resultMap = new HashMap<>();
        Set<String> validCategories = new TransactionService().validateCategories(categories);
        if (user == null || validCategories.isEmpty()) {
            return resultMap;
        }
        LocalDateTime oneMonth = LocalDateTime.now().minusMonths(1L);
        for (BankAccount bankAccount : user.getBankAccount()) {
            for (Transaction transaction : bankAccount.getTransaction()) {
                if (TransactionType.PAYMENT.equals(transaction.getType())
                        && validCategories.contains(transaction.getCategory())
                        && transaction.getCreatedDate().isAfter(oneMonth)) {
                    resultMap.merge(transaction.getCategory(), transaction.getValue(), BigDecimal::add);
                }
            }
        }
        return resultMap;
    }

    public LinkedHashMap<String, List<Transaction>> getTransactionHistorySortedByAmount(User user) {
        LinkedHashMap<String, List<Transaction>> resultMap = new LinkedHashMap<>();
        if (user == null) {
            return resultMap;
        }
        List<Transaction> transactions = new ArrayList<>();
        for (BankAccount bankAccount : user.getBankAccount()) {
            for (Transaction transaction : bankAccount.getTransaction()) {
                if (TransactionType.PAYMENT.equals(transaction.getType())) {
                    transactions.add(transaction);
                }
            }
        }
        transactions.sort(Comparator.comparing(Transaction::getValue));
        for (Transaction transaction : transactions) {
            resultMap.computeIfAbsent(transaction.getCategory(), k -> new ArrayList<>()).add(transaction);

        }
        return resultMap;
    }
    public List<Transaction> getLastNTransactions(User user, int n){
        List<Transaction>transactions=new ArrayList<>();
        List<Transaction>result=new ArrayList<>();
        if(user==null){
            return result;
        }
        for(BankAccount bankAccount: user.getBankAccount()) {
            transactions.addAll(bankAccount.getTransaction());
        }
        transactions.sort((t1,t2)->t2.getCreatedDate().compareTo(t1.getCreatedDate()));
        for(int i=0;i<Math.min(n,transactions.size());i++){
            result.add(transactions.get(i));
        }
        return result;
    }
    public PriorityQueue<Transaction> getTopNLargestTransactions(User user, int n){ //5 метод
        PriorityQueue<Transaction>allTransaction=new PriorityQueue<>();
        if(user==null){
            return allTransaction;
        }
        for(BankAccount bankAccount: user.getBankAccount()){
            for(Transaction transaction: bankAccount.getTransaction()){
                if(TransactionType.PAYMENT.equals(transaction.getType())){
                    if(allTransaction.size()<n){
                        allTransaction.offer(transaction);
                    }else if(allTransaction.peek().getValue().compareTo(transaction.getValue())<0);
                    allTransaction.poll();
                    allTransaction.offer(transaction);
                }
            }
        }return allTransaction;
    }
}
