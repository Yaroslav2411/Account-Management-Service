package entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class BankAccount {
    private String id;
    private BigDecimal balance;
    private User owner;
    private List<Transaction>transaction;

}
