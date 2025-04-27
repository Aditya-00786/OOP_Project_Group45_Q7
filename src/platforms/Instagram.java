package platforms;

/**
 * Represents the Instagram platform for tracking campaigns and recommending influencers.
 *
 * <p>The {@code Instagram} class implements the {@link Platform} and {@link RecommendationEngine}
 * interfaces, providing functionalities to track campaigns and recommend influencers based on niche.</p>
 */
public class Instagram implements Platform, RecommendationEngine {
    int likes = 8000; // The number of likes on the platform
    int shares = 1500; // The number of shares on the platform
    int views = 300000; // The number of views on the platform
    int followers = 900000; // The number of followers on the platform

    /**
     * Constructs a new {@code Instagram} instance.
     */
    public Instagram() {
        super(); // Call to the superclass constructor
    }

    /**
     * Tracks a campaign for a specified influencer on Instagram.
     *
     * @param influencerName The name of the influencer whose campaign is being tracked.
     */
    @Override
    public void trackCampaign(String influencerName) {
        System.out.println("Tracking Instagram campaign for " + influencerName); // Print tracking message
    }

    /**
     * Recommends influencers on Instagram based on the specified niche.
     *
     * @param niche The niche for which influencers are to be recommended.
     */
    @Override
    public void recommendInfluencers(String niche) {
        System.out.println("Recommending Instagram influencers in niche: " + niche); // Print recommendation message
    }

    /**
     * Returns the number of likes on the platform.
     *
     * @return The number of likes.
     */
    @Override
    public int getLikes() {
        return likes; // Return the number of likes
    }

    /**
     * Returns the number of shares on the platform.
     *
     * @return The number of shares.
     */
    @Override
    public int getShares() {
        return shares; // Return the number of shares
    }

    /**
     * Returns the number of views on the platform.
     *
     * @return The number of views.
     */
    @Override
    public int getViews() {
        return views; // Return the number of views
    }
}