package entity;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class User {
    private String middleName;
    private String FirstName;
    private String LastName;
    private LocalDate BirthDate;
    private String id;
    private List<BankAccount>bankAccount;
}
