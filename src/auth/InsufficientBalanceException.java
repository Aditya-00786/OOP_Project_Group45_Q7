package auth;

/**
 * Exception thrown when a user attempts to perform an operation that requires
 * a balance greater than what is currently available.
 *
 * <p>This exception is typically used in financial transactions where
 * the available balance is insufficient to complete the requested operation.</p>
 */
public class InsufficientBalanceException extends Exception {

    /**
     * Constructs a new {@code InsufficientBalanceException} with the specified detail message.
     *
     * @param msg The detail message, which is saved for later retrieval by the {@link #getMessage()} method.
     */
    public InsufficientBalanceException(String msg) {
        super(msg);
    }
}