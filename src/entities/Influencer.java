package entities;

import auth.*;
import java.util.Date;
import platforms.*;

/**
 * Represents an influencer in the influencer platform.
 *
 * <p>The {@code Influencer} class extends the {@link User} class and provides
 * functionalities for managing sponsorships, campaigns, and brand partnerships.</p>
 */
public class Influencer extends User {
    String niche; // The niche of the influencer
    double sponsorshipAmount; // The total amount available for sponsorships
    Double engagementRate; // The engagement rate of the influencer
    String[] regions; // The regions where the influencer operates
    BrandManager[] brands = new BrandManager[10]; // Array to store associated brand managers
    int brandCount = 0; // Count of added brand managers
    Campaign[] campaigns = new Campaign[10]; // Array to store campaigns
    int campaignCount = 0; // Count of added campaigns

    /**
     * Represents a campaign created by the influencer.
     */
    public static class Campaign {
        String name; // The name of the campaign
        double budget; // The budget allocated for the campaign
        Date date; // The date of the campaign
        Platform plat; // The platform associated with the campaign

        /**
         * Constructs a new {@code Campaign} with the specified name and budget.
         *
         * @param name The name of the campaign.
         * @param budget The budget allocated for the campaign.
         */
        public Campaign(String name, double budget) {
            this.name = name;
            this.budget = budget;
        }

        /**
         * Returns the budget of the campaign.
         *
         * @return The budget of the campaign.
         */
        public double getBudget() {
            return this.budget;
        }

        /**
         * Returns the name of the campaign.
         *
         * @return The name of the campaign.
         */
        public String getName() {
            return this.name;
        }

        /**
         * Constructs a new {@code Campaign} with the specified name, budget, date, and platform.
         *
         * @param name The name of the campaign.
         * @param budget The budget allocated for the campaign.
         * @param date The date of the campaign.
         * @param p The platform associated with the campaign.
         */
        Campaign(String name, double budget, Date date, Platform p) {
            this.name = name;
            this.budget = budget;
            this.date = date;
            this.plat = p;
        }

        /**
         * Displays the details of the campaign.
         */
        void display() {
            System.out.println("[" + date + "] Campaign: " + name + ", Budget: $" + budget);
        }

        /**
         * Returns the platform associated with the campaign.
         *
         * @return The platform of the campaign.
         */
        public Platform getPlatform() {
            return plat;
        }
    }

    /**
     * Represents an advertiser for a campaign.
     */
    class Advertiser extends Campaign {
        Platform x; // The platform associated with the advertiser

        /**
         * Constructs a new {@code Advertiser} with the specified campaign name, budget, and platform.
         *
         * @param campaignName The name of the campaign.
         * @param budget The budget allocated for the campaign.
         * @param p The platform associated with the advertiser.
         */
        Advertiser(String campaignName, double budget, Platform p) {
            super(campaignName, budget);
            this.x = p;
        }

        /**
         * Displays the details of the campaign.
         */
        void showCampaign() {
            System.out.println("Campaign: " + name + ", Budget: $" + budget);
        }

        /**
         * Converts the advertiser to a campaign.
         *
         * @return A new {@link Campaign} object.
         */
        Campaign toCampaign() {
            return new Campaign(name, budget, new Date(), x);
        }
    }

    /**
     * Constructs a new {@code Influencer} with the specified username and password.
     *
     * @param username The username of the influencer.
     * @param password The password of the influencer.
     */
    public Influencer(String username, String password) {
        super(username, password);
    }

    /**
     * Constructs a new {@code Influencer} with the specified username, password, niche,
     * engagement rate, and regions.
     *
     * @param username The username of the influencer.
     * @param password The password of the influencer.
     * @param niche The niche of the influencer.
     * @param engagementRate The engagement rate of the influencer.
     * @param regions The regions where the influencer operates.
     */
    
    public Influencer(String username, String password, String niche, double engagementRate, String... regions) {
        super(username, password);
        this.niche = niche;
        this.engagementRate = engagementRate;
        this.regions = regions;
    }

    /**
    * Adds a brand manager to the influencer's list of associated brands.
    *
    * <p>This method checks if there is space in the array before adding
    * the brand manager. If the array is full, the brand manager will not be added.</p>
    *
    * @param bm The {@link BrandManager} to be added.
    */
    public void addBrand(BrandManager bm) {
        if (brandCount < brands.length) {
            brands[brandCount++] = bm; // Add brand manager and increment count
        }
    }

    /**
    * Adds two brand managers to the influencer's list of associated brands.
    *
    * <p>This method calls the {@link #addBrand(BrandManager)} method for each
    * brand manager provided.</p>
    *
    * @param bm1 The first {@link BrandManager} to be added.
    * @param bm2 The second {@link BrandManager} to be added.
    */
    public void addBrand(BrandManager bm1, BrandManager bm2) {
        addBrand(bm1); // Add the first brand manager
        addBrand(bm2); // Add the second brand manager
    }

    /**
    * Prints the specified regions where the influencer operates.
    *
    * <p>If no regions are specified, a message indicating that no regions are
    * specified will be printed.</p>
    *
    * @param regions The regions where the influencer operates.
    */
    public void printRegions(String... regions) {
        if (regions == null || regions.length == 0) {
            System.out.println("No regions specified."); // No regions provided
        } else {
            System.out.println("Regions: " + java.util.Arrays.toString(regions)); // Print regions
        }
    }

    /**
    * Updates the engagement rate of the influencer.
    *
    * <p>This method sets the engagement rate to the new value and prints
    * a message indicating the updated engagement rate.</p>
    *
    * @param newRate The new engagement rate to be set.
    */
    public void updateEngagementRate(Double newRate) {
        this.engagementRate = newRate; // Update engagement rate
        System.out.println("Updated engagement rate: " + engagementRate); // Print updated rate
    }

    /**
    * Launches a new campaign for the influencer.
    *
    * <p>This method creates a new {@link Advertiser} object and checks if the
    * influencer has enough sponsorship amount to cover the campaign budget.
    * If successful, the campaign is added to the influencer's list of campaigns.
    * If the budget exceeds the available sponsorship amount, a
    * {@link PaymentFailedException} is thrown.</p>
    *
    * @param campaignName The name of the campaign to be launched.
    * @param budget The budget allocated for the campaign.
    * @param p The {@link Platform} associated with the campaign.
    * @throws PaymentFailedException If the budget exceeds the available sponsorship amount.
    */
    public void launchCampaign(String campaignName, double budget, Platform p) throws PaymentFailedException {
        Advertiser advertiser = new Advertiser(campaignName, budget, p); // Create advertiser
        advertiser.showCampaign(); // Show campaign details
        if (campaignCount < campaigns.length) {
            if (this.sponsorshipAmount >= budget) {
                this.sponsorshipAmount -= budget; // Deduct budget from sponsorship amount
                campaigns[campaignCount++] = advertiser.toCampaign(); // Add campaign to list
            } else {
                throw new PaymentFailedException("Exceeded Budget"); // Throw exception if budget exceeded
            }
        }
    }

    /**
    * Displays the campaigns launched by the influencer.
    *
    * <p>This method prints the name of the influencer and iterates through
    * the list of campaigns, displaying each campaign's details.</p>
    */
    public void viewCampaigns() {
        System.out.println("Campaigns by " + username + ":"); // Print influencer's name
        for (int i = 0; i < campaignCount; i++) {
        campaigns[i].display(); // Display each campaign
        }
    }

    /**
     * Displays the influencer dashboard.
     *
     * <p>This method prints a message indicating the influencer's dashboard
     * along with the username.</p>
     */
    @Override
    public void displayMenu() {
    System.out.println("Influencer Dashboard for " + username); // Print dashboard message
    }

    /**
    * Shows the statistics of the influencer.
    *
    * <p>This method prints the niche, engagement rate, and total sponsorship amount
    * of the influencer, followed by the list of campaigns.</p>
    */
    public void showStats() {
        System.out.println("Niche: " + niche + ", Engagement: " + engagementRate + ", Sponsored: $" + sponsorshipAmount); // Print stats
        viewCampaigns(); // Display campaigns
    }

    /**
    * Returns the engagement rate of the influencer.
    *
    * @return The engagement rate of the influencer.
    */
    public double getEngagementRate() {
        return this.engagementRate; // Return the engagement rate
    }

    /**
    * Returns the regions where the influencer operates.
    *
    * @return An array of regions associated with the influencer.
    */
    public String[] getRegions() {
        return this.regions; // Return the regions
    }

    /**
    * Returns the total sponsorship amount available for the influencer.
    *
    * @return The total sponsorship amount.
    */
    public double getSponsorshipAmount() {
        return this.sponsorshipAmount; // Return the sponsorship amount
    }

    /**
    * Receives a payment and adds it to the influencer's sponsorship amount.
    *
    * <p>This method updates the sponsorship amount by adding the specified
    * payment amount.</p>
    *
    * @param amount The amount of payment to be received.
    */
    public void receivePayment(double amount) {
        sponsorshipAmount += amount; // Update sponsorship amount
        // System.out.println("Received payment of $" + amount); // Optional: Uncomment to print received payment
    }

    /**
    * Returns the array of campaigns associated with the influencer.
    *
    * @return An array of {@link Campaign} objects associated with the influencer.
    */
    public Campaign[] getCampaigns() {
        return this.campaigns; // Return the array of campaigns
    }

    /**
         * Returns the niche of the influencer.
         *
         * @return The niche of the influencer.
         */
        public String getNiche() {
            return this.niche;
        }
}