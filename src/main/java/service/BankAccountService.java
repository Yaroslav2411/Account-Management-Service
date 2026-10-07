package service;

import java.util.List;
import java.util.Map;
import entity.BankAccount;
import entity.Transaction;
import entity.TransactionType;
import entity.User;

public class BankAccountService {
    private Map<User, List<BankAccount>> userAccounts;
}
