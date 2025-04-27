package entities;

/**
 * Represents an administrator in the influencer platform.
 *
 * <p>The {@code Admin} class extends the {@link User} class and provides
 * functionalities for managing influencers, including adding influencers
 * and viewing their statistics.</p>
 */
public class Admin extends User {
    Influencer[] influencers = new Influencer[10]; // Array to store influencers
    int influencerCount = 0; // Count of added influencers

    /**
     * Constructs a new {@code Admin} with the specified username and password.
     *
     * @param username The username of the admin.
     * @param password The password of the admin.
     */
    public Admin(String username, String password) {
        super(username, password);
    }

    /**
     * Adds a new influencer to the admin's list of influencers.
     *
     * <p>This method checks if there is space in the array before adding
     * the influencer. If the array is full, the influencer will not be added.</p>
     *
     * @param i The {@link Influencer} to be added.
     */
    public void addInfluencer(Influencer i) {
        if (influencerCount < influencers.length) {
            influencers[influencerCount++] = i; // Add influencer and increment count
        }
    }

    /**
     * Displays the statistics of all influencers managed by the admin.
     *
     * <p>This method iterates through the list of influencers and calls
     * the {@link Influencer#showStats()} method for each influencer.</p>
     */
    public void viewInfluencerStats() {
        for (int i = 0; i < influencerCount; i++) {
            influencers[i].showStats(); // Show stats for each influencer
        }
    }

    /**
     * Displays the admin panel menu.
     *
     * <p>This method prints a simple message indicating that the admin panel is displayed.</p>
     */
    @Override
    public void displayMenu() {
        System.out.println("Admin Panel");
    }
}