package auth;

/**
 * Exception thrown when a payment transaction fails.
 *
 * <p>This exception is typically used in financial operations to indicate
 * that a payment could not be processed successfully due to various reasons,
 * such as insufficient funds, network issues, or invalid payment details.</p>
 */
public class PaymentFailedException extends Exception {

    /**
     * Constructs a new {@code PaymentFailedException} with the specified detail message.
     *
     * @param message The detail message, which is saved for later retrieval by the {@link #getMessage()} method.
     */
    public PaymentFailedException(String message) {
        super(message);
    }
}
