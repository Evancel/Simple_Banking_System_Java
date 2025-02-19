package banking;

public class BankApplication {
    private boolean exit = false;

    private BankRepository bankRepository;
    private UserInterface userInterface = new UserInterface();
    private AccountsMap accountsMap = new AccountsMap();

    public void runApplication(String[] args){
        bankRepository = new BankRepository(args);
        bankRepository.createTableCard();

        while(!exit) {
            userInterface.printStartMenu();
            handleUserChoice(userInterface.getIntInput());
            System.out.println();
        }
    }

    private void handleUserChoice(int userChoice){
        switch (userChoice){
            case 1 -> createAccount();
            case 2 -> logIntoAccount();
            case 0 -> exit();
            default -> System.out.println("Invalid choice! Please enter a valid option.");
        }
    }

    private void createAccount(){
        Account account;
        do{
            account = new Account();
        }while(bankRepository.accountExists(account.getAccountNumber()));

        bankRepository.addAccount(account);
        userInterface.printCardCreatedPrompt(account.getAccountNumber(), account.getPIN());
    }

    private void logIntoAccount(){
        userInterface.printInputCardNumber();
        String inputCardNumber = userInterface.getStringInput();
        userInterface.printInputPIN();
        String inputPinCode = userInterface.getStringInput();

        if(!bankRepository.accountExists(inputCardNumber, inputPinCode)){
            userInterface.printInvalidCardNumberOrPin();
            return;
        }

        Account currAccount =bankRepository.getAccount(inputCardNumber, inputPinCode);
        userInterface.printLogInSuccessfully();
        handleAccountSession(currAccount);
    }

    private void handleAccountSession(Account account){
        boolean loggedIn = true;
        while(loggedIn && !exit) {
            userInterface.printLogInMenu();
            int userChoice = userInterface.getIntInput();

            switch (userChoice) {
                case 1 -> userInterface.printBalance(account.getBalance());
                case 2 -> addIncome(account);
                case 3 -> doTransfer(account);
                case 4 -> closeAccount(account);
                case 5 -> {
                    userInterface.printLogOut();
                    loggedIn = false;
                }
                case 0 -> exit();
                default -> System.out.println("invalid choice. Try again");
            }
        }
    }

    private void addIncome(Account account){
        userInterface.printAddIncomePrompt();
        double income = userInterface.getDoubleInput();
        if(income < 0){
            System.out.println("The amount of income is negative. The operation is impossible!");
            return;
        }
        account.changeBalance(income);
        bankRepository.updateAccount(account);
        userInterface.printIncomeAdded();
    }

    private void doTransfer(Account accountSender){
        userInterface.printTransferPrompt();
        String inputCardNumber = userInterface.getStringInput();

        if(inputCardNumber.equals(accountSender.getAccountNumber())){
            userInterface.printTransferSameAccount();
            return;
        }

        if(!accountSender.validCardNumberByLuhnAlgorithm(inputCardNumber)){
            userInterface.printTransferInvalidCardNumber();
            return;
        }

        if(!bankRepository.accountExists(inputCardNumber)){
            userInterface.printTransferNoSuchCard();
            return;
        }

        Account accountReceiver = bankRepository.getAccount(inputCardNumber);
        userInterface.printTransferEnterSumPrompt();
        double amount = userInterface.getDoubleInput();
        if(amount <= 0){
            userInterface.printTransferInvalidAmount();
            return;
        }

        double currbalance = accountSender.getBalance();
        if(amount > currbalance){
            userInterface.printTransferNotEnoughMoney();
            return;
        }

        accountSender.changeBalance(-amount);
        accountReceiver.changeBalance(amount);

        bankRepository.doTransfer(accountSender, accountReceiver);
        userInterface.printTransferSuccess();
    }
    private void closeAccount(Account account){
        if(!bankRepository.accountExists(account.getAccountNumber())){
            userInterface.printCloseInvalidAccount();
            return;
        }
        bankRepository.closeAccount(account);
        userInterface.printCloseAccountSuccess();
    }

    private void exit(){
        userInterface.printExit();
        exit = true;
    }
}
