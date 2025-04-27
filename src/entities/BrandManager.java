package entities;

import auth.*;
import contracts.Contract;

/**
 * Represents a brand manager in the influencer platform.
 *
 * <p>The {@code BrandManager} class extends the {@link User} class and provides
 * functionalities for managing sponsorship budgets and offering sponsorships
 * to influencers.</p>
 */
public class BrandManager extends User {
    public String brandName; // The name of the brand managed by the brand manager
    double sponsorshipBudget; // The budget available for sponsorships

    /**
     * Constructs a new {@code BrandManager} with the specified username, password,
     * brand name, and sponsorship budget.
     *
     * @param username The username of the brand manager.
     * @param password The password of the brand manager.
     * @param brandName The name of the brand managed by the brand manager.
     * @param sponsorshipBudget The initial budget available for sponsorships.
     */
    public BrandManager(String username, String password, String brandName, double sponsorshipBudget) {
        super(username, password);
        this.brandName = brandName;
        this.sponsorshipBudget = sponsorshipBudget;
    }

    /**
     * Returns the sponsorship budget available for this brand manager.
     *
     * @return The sponsorship budget.
     */
    public double getSponsorshipBudget() {
        return sponsorshipBudget;
    }

    /**
     * Displays the brand manager dashboard.
     *
     * <p>This method prints a message indicating the brand manager's dashboard
     * along with the brand name.</p>
     */
    @Override
    public void displayMenu() {
        System.out.println("Brand Manager Dashboard for " + brandName);
    }

    /**
     * Offers a sponsorship to an influencer.
     *
     * <p>This method checks if the specified amount is within the available
     * sponsorship budget. If the budget is insufficient, an
     * {@link InsufficientBalanceException} is thrown. If the payment to the influencer
     * fails, a {@link PaymentFailedException} is thrown. If successful, the method
     * updates the budget and returns a new {@link Contract}.</p>
     *
     * @param influencer The {@link Influencer} to whom the sponsorship is offered.
     * @param amount The amount of the sponsorship.
     * @return A {@link Contract} representing the sponsorship agreement.
     * @throws InsufficientBalanceException If the sponsorship amount exceeds the budget.
     * @throws PaymentFailedException If the payment to the influencer fails.
     */
    public Contract offerSponsorship(Influencer influencer, double amount) 
            throws InsufficientBalanceException, PaymentFailedException {
        
        if (amount > sponsorshipBudget) {
            throw new InsufficientBalanceException("Not enough budget");
        }
        
        // Update budgets
        sponsorshipBudget -= amount;
        influencer.receivePayment(amount);
        
        return new Contract(influencer, this, amount);
    }
}