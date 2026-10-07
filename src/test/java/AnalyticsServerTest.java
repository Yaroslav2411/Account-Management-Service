import entity.BankAccount;
import entity.Transaction;
import entity.TransactionType;
import service.AnalyticsService;
import entity.User;
import org.junit.Assert;
import org.junit.Before;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;









public class AnalyticsServerTest {
    private static AnalyticsService analyticsService = new AnalyticsService();
    private static User user = new User();

    private BankAccount bankAccount1;
    private BankAccount bankAccount2;
    private BankAccount bankAccount3;

    private static final BigDecimal ONE_DOLLAR = new BigDecimal(1.0);
    private static final BigDecimal TWO_DOLLARS = new BigDecimal(2.0);
    private static final BigDecimal FIVE_DOLLARS = new BigDecimal(5.0);
    private static final BigDecimal TEN_DOLLARS = new BigDecimal(10.0);

    private static final String BEAUTY_CATEGORY = "Beauty";
    private static final String HEALTH_CATEGORY = "Health";
    private static final String EDUCATION_CATEGORY = "Education";

    private static final LocalDateTime ONE_MONTH_AGO = LocalDateTime.now().minusMonths(1L);
    private static final LocalDateTime TWO_MONTH_AGO = LocalDateTime.now().minusMonths(2L);
    private static final LocalDateTime ONE_WEEK_AGO = LocalDateTime.now().minusWeeks(1L);
    private static final LocalDateTime FIVE_WEEK_AGO = LocalDateTime.now().minusWeeks(5L);

}
