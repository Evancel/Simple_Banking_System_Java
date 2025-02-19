package banking;

import java.util.HashMap;
import java.util.Map;

public class AccountsMap {
    private final Map<String,Account> accountsMap;

    public AccountsMap(){
        accountsMap = new HashMap<>();
    }

    public void addToMap(Account account){
        accountsMap.put(account.getAccountNumber(),account);
    }

    public Account getAccount(String accountNumber, String pinCode){
        if(!containsAccountData(accountNumber, pinCode)){
            return null;
        }

        return accountsMap.get(accountNumber);
    }

    public void printMap(){
        for(String account : accountsMap.keySet()){
            System.out.println("accountNumber = " + account + ",pinCode = " + accountsMap.get(account).getPIN());
        }
    }

    public boolean containsAccountData(String accountNumber, String pinCode){
        if(accountsMap.containsKey(accountNumber)){
            return accountsMap.get(accountNumber).getPIN().equals(pinCode);
        }
        return false;
    }
}
