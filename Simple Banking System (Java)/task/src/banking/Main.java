package banking;

import org.sqlite.SQLiteDataSource;

public class Main {
    public static void main(String[] args) {
        BankApplication bankApplication = new BankApplication();
        bankApplication.runApplication(args);
    }
}