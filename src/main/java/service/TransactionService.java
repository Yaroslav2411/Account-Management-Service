package service;

import java.util.HashSet;
import java.util.Set;

public class TransactionService {
    public static Set<String> transactionCategories = Set.of(
            "Health", "Beauty", "Education");

    public Set<String> validateCategories (Set<String> categories) {
        Set<String> result = new HashSet<>();
        for (String category : categories) {
            if (transactionCategories.contains(category))
                result.add(category);
        }
        return result;
    }
}
