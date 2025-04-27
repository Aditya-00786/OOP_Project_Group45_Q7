package platforms;

/**
 * Represents the YouTube platform for tracking campaigns and recommending influencers.
 *
 * <p>The {@code YouTube} class implements the {@link Platform} and {@link RecommendationEngine}
 * interfaces, providing functionalities to track campaigns and recommend influencers based on niche.</p>
 */
public class YouTube implements Platform, RecommendationEngine {
    int likes = 10000; // The number of likes on the platform
    int shares = 2000; // The number of shares on the platform
    int views = 500000; // The number of views on the platform
    int subscribers = 1200000; // The number of subscribers on the platform

    /**
     * Constructs a new {@code YouTube} instance.
     */
    public YouTube() {
        super(); // Call to the superclass constructor
    }

    /**
     * Tracks a campaign for a specified influencer on YouTube.
     *
     * @param influencerName The name of the influencer whose campaign is being tracked.
     */
    @Override
    public void trackCampaign(String influencerName) {
        System.out.println("Tracking YouTube campaign for " + influencerName); // Print tracking message
    }

    /**
     * Recommends influencers on YouTube based on the specified niche.
     *
     * @param niche The niche for which influencers are to be recommended.
     */
    @Override
    public void recommendInfluencers(String niche) {
        System.out.println("Recommending YouTube influencers in niche: " + niche); // Print recommendation message
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