package banking;

import java.sql.*;

public class BankRepository {
    private final Connection connection;

    public BankRepository(String[] args) {
        this.connection = DBConnection.getConnection(args);
    }

    public void createTableCard() {
        String createTableCard = "CREATE TABLE IF NOT EXISTS card(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "number TEXT NOT NULL," +
                "pin TEXT NOT NULL," +
                "balance INTEGER DEFAULT 0)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(createTableCard)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAccount(Account account) {
        if(accountExists(account.getAccountNumber())){
            System.out.println("Account with the same number already exists");
            return;
        }
        String insert = "INSERT INTO CARD (number, pin, balance) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
            preparedStatement.setString(1, account.getAccountNumber());
            preparedStatement.setString(2, account.getPIN());
            preparedStatement.setDouble(3, account.getBalance());
            int i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAccount(Account account) {
        String update = "UPDATE CARD SET balance = ? WHERE number = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(update)) {
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setString(2, account.getAccountNumber());
            int i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeAccount(Account account) {
        String update = "DELETE FROM CARD WHERE number = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(update)) {
            preparedStatement.setString(1, account.getAccountNumber());
            int i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean accountExists(String inputAccountNumber, String inputPinCode) {
        boolean accountExists = false;
        String query = "SELECT * FROM CARD WHERE number = ? AND pin = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, inputAccountNumber);
            preparedStatement.setString(2, inputPinCode);

            try(ResultSet accountInfo = preparedStatement.executeQuery()) {
                while (accountInfo.next()) {
                    accountExists = true;
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountExists;
    }

    public boolean accountExists(String inputAccountNumber) {
        boolean accountExists = false;
        String query = "SELECT * FROM CARD WHERE number = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, inputAccountNumber);

            try(ResultSet accountInfo = preparedStatement.executeQuery()) {
                while (accountInfo.next()) {
                    accountExists = true;
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountExists;
    }

    public Account getAccount(String inputAccountNumber, String inputPinCode) {
        Account account = null;
        String query = "SELECT * FROM CARD WHERE number = ? AND pin = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, inputAccountNumber);
            preparedStatement.setString(2, inputPinCode);

            try(ResultSet accountInfo = preparedStatement.executeQuery()) {
                while (accountInfo.next()) {
                    String accountNumber = accountInfo.getString("number");
                    String accountPin = accountInfo.getString("pin");
                    int accountBalance = accountInfo.getInt("balance");
                    account = new Account(accountNumber, accountPin, accountBalance);
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    public Account getAccount(String inputAccountNumber) {
        Account account = null;
        String query = "SELECT * FROM CARD WHERE number = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, inputAccountNumber);

            try(ResultSet accountInfo = preparedStatement.executeQuery()) {
                while (accountInfo.next()) {
                    String accountNumber = accountInfo.getString("number");
                    String accountPin = accountInfo.getString("pin");
                    int accountBalance = accountInfo.getInt("balance");
                    account = new Account(accountNumber, accountPin, accountBalance);
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    public void doTransfer(Account sender, Account receiver) {
        String updateSenderAccount = "UPDATE CARD SET balance = ? WHERE number = ?";
        String updateReceiverAccount = "UPDATE CARD SET balance = ? WHERE number = ?";
        try(PreparedStatement updateSender = connection.prepareStatement(updateSenderAccount);
            PreparedStatement updateReciever = connection.prepareStatement(updateReceiverAccount)){
            connection.setAutoCommit(false);

            updateSender.setDouble(1, sender.getBalance());
            updateSender.setString(2, sender.getAccountNumber());
            updateSender.executeUpdate();

            updateReciever.setDouble(1, receiver.getBalance());
            updateReciever.setString(2, receiver.getAccountNumber());
            updateReciever.executeUpdate();

            connection.commit();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
