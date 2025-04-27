package auth;

/**
 * Exception thrown when a user attempts to log in with a password
 * that does not match the stored password for the given username.
 *
 * <p>This exception is typically used in authentication processes
 * to indicate that the provided password is incorrect.</p>
 */
public class PasswordNotMatchingException extends Exception {

    /**
     * Constructs a new {@code PasswordNotMatchingException} with the specified detail message.
     *
     * @param msg The detail message, which is saved for later retrieval by the {@link #getMessage()} method.
     */
    public PasswordNotMatchingException(String msg) {
        super(msg);
    }
}