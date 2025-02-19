package banking;

import java.util.Scanner;

public class UserInterface {
    Scanner sc = new Scanner(System.in);
    private final String START_MENU = "1. Create an account\n2. Log into account\n0. Exit";
    private final String CARD_CREATED_TEMPLATE = "\nYour card has been created\n" +
            "Your card number:\n%s\nYour card PIN:\n%s\n";
    private final String INPUT_CARD_NUMBER_PROMPT = "\nEnter your card number:";
    private final String INPUT_PIN_PROMPT = "\nEnter your PIN:";
    private final String INVALID_CARD_NUMBER_OR_PIN = "\nWrong card number or PIN!";
    private final String LOGIN_SUCCESSFULLY = "\nYou have successfully logged in!";
    private final String LOGIN_MENU = "\n1. Balance\n2. Add income\n3. Do transfer\n" +
            "4. Close account\n5. Log out\n0. Exit";
    private final String BALANCE_TEMPLATE = "\nBalance: %.2f\n";
    private final String ADD_INCOME_PROMPT = "\nEnter income:";
    private final String INCOME_ADDED_SUCCESSFULLY = "\nIncome was added!";
    private final String TRANSFER_PROMPT = "\nTransfer\nEnter card number:";
    private final String TRANSFER_ENTER_SUM_PROMPT = "\nEnter how much money you want to transfer:";
    private final String TRANSFER_INVALID_CARD_NUMBER = "\nProbably you made a mistake in the card number. Please try again!";
    private final String TRANSFER_NO_SUCH_CARD = "\nSuch a card does not exist.";
    private final String TRANSFER_SAME_ACCOUNT = "\nYou can't transfer money to the same account!";
    private final String TRANSFER_NOT_ENOUGH_MONEY = "\nNot enough money!";
    private final String TRANSFER_INVALID_AMOUNT = "\nThe amount you input is invalid.";
    private final String TRANSFER_SUCCESS = "\nSuccess!";
    private final String CLOSE_ACCOUNT_SUCCESS = "\nThe account has been closed!";
    private final String ACCOUNT_NO_FOUND = "\nThe account doesn't exist or has already been closed";

    private final String LOG_OUT_PROMPT = "\nYou have successfully logged out!";
    private final String EXIT_PROMPT = "\nBye!";

    public void printStartMenu(){
        System.out.println(START_MENU);
    }

    public void printCardCreatedPrompt(String cardNumber, String PIN){
        System.out.printf(CARD_CREATED_TEMPLATE, cardNumber, PIN);
    }

    public void printInputCardNumber(){
        System.out.println(INPUT_CARD_NUMBER_PROMPT);
    }

    public void printInputPIN(){
        System.out.println(INPUT_PIN_PROMPT);
    }

    public void printInvalidCardNumberOrPin(){
        System.out.println(INVALID_CARD_NUMBER_OR_PIN);
    }

    public void printLogInSuccessfully(){
        System.out.println(LOGIN_SUCCESSFULLY);
    }

    public void printLogInMenu(){
        System.out.println(LOGIN_MENU);
    }

    public void printBalance(double balance){
        System.out.printf(BALANCE_TEMPLATE, balance);
    }

    public void printAddIncomePrompt(){
        System.out.println(ADD_INCOME_PROMPT);
    }

    public void printIncomeAdded(){
        System.out.println(INCOME_ADDED_SUCCESSFULLY);
    }

    public void printTransferPrompt(){
        System.out.println(TRANSFER_PROMPT);
    }

    public void printTransferEnterSumPrompt(){
        System.out.println(TRANSFER_ENTER_SUM_PROMPT);
    }

    public void printTransferInvalidCardNumber(){
        System.out.println(TRANSFER_INVALID_CARD_NUMBER);
    }

    public void printTransferNoSuchCard(){
        System.out.println(TRANSFER_NO_SUCH_CARD);
    }

    public void printTransferSameAccount(){
        System.out.println(TRANSFER_SAME_ACCOUNT);
    }

    public void printTransferInvalidAmount(){
        System.out.println(TRANSFER_INVALID_AMOUNT);
    }

    public void printTransferNotEnoughMoney(){
        System.out.println(TRANSFER_NOT_ENOUGH_MONEY);
    }

    public void printTransferSuccess(){
        System.out.println(TRANSFER_SUCCESS);
    }

    public void printCloseAccountSuccess(){
        System.out.println(CLOSE_ACCOUNT_SUCCESS);
    }

    public void printCloseInvalidAccount(){
        System.out.println(ACCOUNT_NO_FOUND);
    }

    public void printLogOut(){
        System.out.println(LOG_OUT_PROMPT);
    }

    public void printExit(){
        System.out.println(EXIT_PROMPT);
        sc.close();
    }

    public int getIntInput(){
        System.out.print("> ");
        String input = sc.nextLine();

        int userChoice = 0;
        try{
            userChoice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Your input is invalid.");
        }

      return userChoice;
    }

    public double getDoubleInput(){
        System.out.print("> ");
        String input = sc.nextLine();

        double userChoice = 0.00;
        try{
            userChoice = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Your input is invalid.");
        }

        return userChoice;
    }

    public String getStringInput(){
        System.out.print("> ");
        return sc.nextLine();
    }
}
