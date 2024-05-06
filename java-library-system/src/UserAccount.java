import java.util.ArrayList;
import java.util.List;

// Class to represent a user account
class UserAccount {
    private String username;
    private String password;
    private boolean isAdmin;

    public UserAccount(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
