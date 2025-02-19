package banking;

import java.util.Random;

public class Account {
    private String accountNumber;
    private static long counter = 100_000_000;
    private String pinCode;
    private double balance = 0.00;

    public Account(){
        generateAccountNumber();
        generatePIN();
    }

    public Account(String accountNumber, String pinCode, double balance){
       this.accountNumber = accountNumber;
       this.pinCode = pinCode;
       this.balance = balance;
    }

    private void generateAccountNumber(){
        int BIN = 400000;
        long account = counter;
        String preAccountNumber = "" + BIN + account;
        int checkNumber = generateCheckNumberByLuhnAlgorithm(preAccountNumber);
        accountNumber = "" + BIN + account + checkNumber;
        counter++;
    }

    private int generateCheckNumberByLuhnAlgorithm(String preAccountNumber){
        int[] arr = new int[preAccountNumber.length()];
        int sum = 0;
        for(int i = 0; i < arr.length; i++){
            arr[i] = preAccountNumber.charAt(i) - '0';  //ascii 0 = 48

            if(i % 2 == 0){
                arr[i] *= 2;

                //if the value more than 9 - subtract 9
                if(arr[i] > 9){
                    arr[i] -= 9;
                }
            }

            sum += arr[i];
        }

        int checkNumber = (10 - (sum % 10)) % 10;

        return checkNumber;
    }

    public String getAccountNumber(){
        return accountNumber;
    }

    private void generatePIN(){
        Random random = new Random();
        int PIN = random.nextInt(1000,10000);
        pinCode = Integer.toString(PIN);
    }

    public String getPIN(){
        return pinCode;
    }

    public double getBalance(){
        return balance;
    }

    public void changeBalance(double income){
        this.balance += income;
    }

    public boolean validCardNumberByLuhnAlgorithm(String inputCardNumber){
        if(!inputCardNumber.matches("\\d{16}")){
            System.out.println("Your input is wrong!");
            return false;
        }
        String first15Digits = inputCardNumber.substring(0,15);
        int inputLastDigit = -1;
        try {
            inputLastDigit = Integer.parseInt(inputCardNumber.substring(15));
        } catch (NumberFormatException e) {
            System.out.println("Number format exception.");
        }

        int correctLastDigit = generateCheckNumberByLuhnAlgorithm(first15Digits);

        return correctLastDigit == inputLastDigit;
    }
}
