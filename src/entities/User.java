package entities;

/**
 * Represents a user in the system.
 *
 * <p>The {@code User} class serves as an abstract base class for different types
 * of users in the application, such as influencers and brand managers. It contains
 * common properties and methods that all users share.</p>
 */
public abstract class User {
    public String username; // The username of the user
    protected String password; // The password of the user

    /**
     * Constructs a new {@code User} with the specified username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     */
    public User(String username, String password) {
        this.username = username; // Set the username
        this.password = password; // Set the password
    }

    /**
     * Displays the menu for the user.
     *
     * <p>This method must be implemented by subclasses to provide specific
     * menu options for different types of users.</p>
     */
    public abstract void displayMenu(); // Abstract method to display user menu
}
