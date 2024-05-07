import java.util.ArrayList;
import java.util.List;

public class AccountManager {
    private List<UserAccount> registeredAccounts;

    public AccountManager() {
        registeredAccounts = new ArrayList<>();
        // Add some sample user accounts
        registerAccount("admin", "admin123", true); // Admin account
        registerAccount("user1", "user123", false); // Regular user account
        registerAccount("user2", "user456", false); // Regular user account
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

    // Method to delete a user account
    public void deleteUserAccount(String username) {
        registeredAccounts.removeIf(account -> account.getUsername().equals(username));
    }
}
