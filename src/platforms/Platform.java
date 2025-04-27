package platforms;

/**
 * Represents a platform for tracking campaigns and retrieving engagement metrics.
 *
 * <p>The {@code Platform} interface defines the methods that any social media platform
 * must implement to track campaigns and provide engagement statistics such as likes, shares, and views.</p>
 */
public interface Platform {
    
    /**
     * Tracks a campaign for a specified influencer.
     *
     * @param influencerName The name of the influencer whose campaign is being tracked.
     */
    void trackCampaign(String influencerName);

    /**
     * Returns the number of likes on the platform.
     *
     * @return The number of likes.
     */
    int getLikes();

    /**
     * Returns the number of shares on the platform.
     *
     * @return The number of shares.
     */
    int getShares();

    /**
     * Returns the number of views on the platform.
     *
     * @return The number of views.
     */
    int getViews();
}