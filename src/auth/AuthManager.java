package auth;

import entities.*;
import java.io.*;

/**
 * The {@code AuthManager} class is responsible for handling user authentication
 * in the influencer platform. It provides methods to log in users
 * by verifying their credentials against stored data.
 */
public class AuthManager {

    /**
     * Logs in a user by verifying the provided username and password.
     *
     * <p>This method reads user credentials from a file named {@code users.txt}.
     * If the username exists and the password matches, it returns the corresponding
     * {@link User} object based on the user's role (Admin, Influencer, or BrandManager).
     * If the password does not match, a {@link PasswordNotMatchingException} is thrown.
     * If the user is not found, the method returns {@code null}.</p>
     *
     * @param username The username of the user attempting to log in.
     * @param password The password of the user attempting to log in.
     * @return A {@link User} object representing the logged-in user (Admin, Influencer, or BrandManager).
     * @throws PasswordNotMatchingException If the provided password does not match the stored password.
     * @throws IOException If an I/O error occurs while reading the user data file.
     */
    public static User login(String username, String password) throws PasswordNotMatchingException, IOException {
        File file = new File("users.txt"); // File containing user credentials
        BufferedReader br = new BufferedReader(new FileReader(file)); // BufferedReader to read the file
        String line;

        // Read each line of the file to find the user
        while ((line = br.readLine()) != null) {
            String[] creds = line.split(","); // Split the line into credentials
            if (creds[0].equals(username)) { // Check if the username matches
                if (!creds[1].equals(password)) {
                    // Throw exception if the password does not match
                    throw new PasswordNotMatchingException("Incorrect Password");
                }
                String role = creds[2]; // Get the user role
                // Return the appropriate User object based on the role
                if (role.equals("Admin")) return new Admin(username, password);
                if (role.equals("Influencer")) return new Influencer(username, password);
                if (role.equals("BrandManager")) return new BrandManager(username, password, "SampleBrand", 5000);
            }
        }
        br.close(); // Close the BufferedReader
        return null; // Return null if the user is not found
    }
}