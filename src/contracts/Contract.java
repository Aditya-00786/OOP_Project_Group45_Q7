package contracts;

import entities.BrandManager;
import entities.Influencer;
import java.util.Date;

/**
 * Represents a contract between an influencer and a brand manager for a sponsorship deal.
 *
 * <p>This class encapsulates the details of the contract, including the influencer,
 * the brand manager, the amount of sponsorship, and the start date of the contract.</p>
 */
public class Contract {
    Influencer influencer; // The influencer involved in the contract
    BrandManager brand; // The brand manager involved in the contract
    double amount; // The amount of sponsorship
    Date startDate; // The start date of the contract

    /**
     * Constructs a new {@code Contract} with the specified influencer, brand manager, and amount.
     *
     * @param influencer The influencer involved in the contract.
     * @param brand The brand manager involved in the contract.
     * @param amount The amount of sponsorship for the contract.
     */
    public Contract(Influencer influencer, BrandManager brand, double amount) {
        this.influencer = influencer;
        this.brand = brand;
        this.amount = amount;
        this.startDate = new Date(); // Set the current date as the start date
    }

    /**
     * Returns the brand manager associated with this contract.
     *
     * @return The {@link BrandManager} involved in the contract.
     */
    public BrandManager getBrand() {
        return this.brand;
    }

    /**
     * Returns the influencer associated with this contract.
     *
     * @return The {@link Influencer} involved in the contract.
     */
    public Influencer getInfluencer() {
        return this.influencer;
    }

    /**
     * Displays the details of the contract.
     *
     * <p>This method prints the brand name, influencer username, amount, and start date
     * of the contract to the console.</p>
     */
    public void displayContract() {
        System.out.println("Contract: " + brand.brandName + " with " + influencer.username +
                " | Amount: $" + amount + " | Date: " + startDate);
    }

    /**
     * Returns the amount of sponsorship for this contract.
     *
     * @return The amount of sponsorship.
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * Returns the start date of the contract.
     *
     * @return The start date of the contract.
     */
    public Date getStartDate() {
        return this.startDate;
    }
}