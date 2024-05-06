import java.util.ArrayList;
import java.util.List;

public class AccountManager {
    private List<UserAccount> registeredAccounts;

    public AccountManager() {
        registeredAccounts = new ArrayList<>();
    }

    // Method to register a new account
    public void registerAccount(String username, String password, boolean isAdmin) {
        registeredAccounts.add(new UserAccount(username, password, isAdmin));
    }

    // Method to verify login credentials
    public boolean verifyLogin(String username, String password) {
        for (UserAccount account : registeredAccounts) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                return true; // Credentials match
            }
        }
        return false; // No matching account found
    }

    // Additional method to check if a username already exists
    public boolean isUsernameTaken(String username) {
        for (UserAccount account : registeredAccounts) {
            if (account.getUsername().equals(username)) {
                return true; // Username already exists
            }
        }
        return false; // Username is available
    }}