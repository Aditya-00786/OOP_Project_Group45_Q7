package platforms;

/**
 * Represents a recommendation engine for suggesting influencers.
 *
 * <p>The {@code RecommendationEngine} interface defines the method that any
 * recommendation system must implement to recommend influencers based on a specific niche.</p>
 */
public interface RecommendationEngine {

    /**
     * Recommends influencers based on the specified niche.
     *
     * @param niche The niche for which influencers are to be recommended.
     */
    void recommendInfluencers(String niche);
}
