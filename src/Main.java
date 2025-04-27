import auth.*;
import contracts.*;
import entities.*;
import java.io.*;
import java.text.SimpleDateFormat;
import platforms.*;

/**
 * The main entry point for the Influencer Platform application.
 *
 * <p>The {@code Main} class handles user authentication, manages user sessions,
 * and provides dashboards for different user types including influencers, brand managers,
 * and admins. It also initializes sample data for testing purposes.</p>
 */
public class Main {
    private static final int MAX_INFLUENCERS = 100; // Maximum number of influencers
    private static final int MAX_CONTRACTS = 100; // Maximum number of contracts
    
    // Data storage
    private static Influencer[] influencers = new Influencer[MAX_INFLUENCERS]; // Array to store influencers
    private static Contract[] contracts = new Contract[MAX_CONTRACTS]; // Array to store contracts
    private static int influencerCount = 0; // Current count of influencers
    private static int contractCount = 0; // Current count of contracts
    
    /**
     * The main method that starts the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        initializeSampleData(); // Initialize sample data for testing
        runApplication(); // Start the application
    }

    /**
     * Runs the main application loop, handling user login and session management.
     */
    private static void runApplication() {
        while (true) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("\n=== INFLUENCER PLATFORM ===");
                System.out.print("Enter username (or 'exit' to quit): ");
                String username = reader.readLine();

                if ("exit".equalsIgnoreCase(username)) {
                    System.out.println("Exiting application...");
                    return; // Exit the application
                }

                String password = getPassword(reader); // Get user password
                User user = AuthManager.login(username, password); // Authenticate user

                if (user != null) {
                    handleUserSession(user, reader); // Handle user session
                } else {
                    System.out.println("Invalid credentials or user not found.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    /**
     * Initializes sample data for influencers and brand managers.
     */
    private static void initializeSampleData() {
        // Initialize influencers (must match users.txt)
        influencers[influencerCount++] = new Influencer("inf1", "pass123", "Fashion", 4.5, "US", "EU");
        influencers[influencerCount++] = new Influencer("inf2", "pass123", "Tech", 4.2, "Global");
        influencers[influencerCount++] = new Influencer("inf3", "pass123", "Lifestyle", 4.8, "US", "Asia");
        
        // Initialize brand managers
        BrandManager brand1 = new BrandManager("brand1", "pass123", "FashionCo", 50000);
        BrandManager brand2 = new BrandManager("brand2", "pass123", "TechGiant", 100000);
        
        // Sample contracts with payments
        try {
            Contract contract1 = brand1.offerSponsorship(influencers[0], 2500);
            contracts[contractCount++] = contract1;
            
            Contract contract2 = brand2.offerSponsorship(influencers[1], 5000);
            contracts[contractCount++] = contract2;
        } catch (Exception e) {
            System.out.println("Error initializing sample contracts: " + e.getMessage());
        }
    }

    /**
     * Handles the user session after successful login.
     *
     * @param user The logged-in user.
     * @param reader The BufferedReader for user input.
     * @throws Exception If an error occurs during session handling.
     */
    private static void handleUserSession(User user, BufferedReader reader) throws Exception {
        System.out.println("\nLogin successful! Welcome, " + user.username + ".");
        
        while (true) {
            user.displayMenu();
            
            if (user instanceof Influencer) {
                Influencer loggedInInfluencer = findInfluencer(user.username);
                if (loggedInInfluencer != null) {
                    if (!influencerDashboard(loggedInInfluencer, reader)) {
                        if (logoutPrompt(reader)) {
                            return; // Go back to login
                        }
                    }
                } else {
                    System.out.println("Error: Could not load influencer data");
                    return;
                }
            } else if (user instanceof BrandManager) {
                if (!brandManagerDashboard((BrandManager)user, reader)) {
                    if (logoutPrompt(reader)) {
                        return; // Go back to login
                    }
                }
            } else if (user instanceof Admin) {
                if (!adminDashboard((Admin)user, reader)) {
                    if (logoutPrompt(reader)) {
                        return; // Go back to login
                    }
                }
            }
        }
    }

    /**
    * Prompts the user with logout options and handles the user's choice.
    *
    * @param reader BufferedReader to read user input.
    * @return true if the user chooses to log out and log in as a different user; false otherwise.
    * @throws IOException if an I/O error occurs.
    */
    private static boolean logoutPrompt(BufferedReader reader) throws IOException {
        System.out.println("\n=== LOGOUT OPTIONS ===");
        System.out.println("1. Login as different user");
        System.out.println("2. Exit application");
        System.out.print("Choose: ");
        
        int choice = Integer.parseInt(reader.readLine());
        if (choice == 1) {
            System.out.println("Logging out...");
            return true;
        } else if (choice == 2) {
            System.out.println("Exiting application...");
            System.exit(0);
        } else {
            System.out.println("Invalid choice, staying logged in");
        }
        return false;
    }

    /**
    * Displays the influencer dashboard and handles user choices.
    *
    * @param influencer The influencer currently logged in.
    * @param reader BufferedReader to read user input.
    * @return true if the user chooses to stay logged in; false if the user chooses to log out.
    * @throws Exception if an error occurs during the operation.
    */

    // ========== INFLUENCER DASHBOARD ========== //
    private static boolean influencerDashboard(Influencer influencer, BufferedReader reader) throws Exception {
        System.out.println("\n=== INFLUENCER DASHBOARD ===");
        System.out.println("Logged in as: " + influencer.username);
        System.out.println("1. View Full Profile");
        System.out.println("2. View My Contracts");
        System.out.println("3. View Earnings Report");
        System.out.println("4. Launch New Campaign");
        System.out.println("5. Logout");
        System.out.print("Choose: ");
        
        int choice = Integer.parseInt(reader.readLine());
        switch (choice) {
            case 1: showFullProfile(influencer); break;
            case 2: viewMyContracts(influencer.username); break;
            case 3: showEarningsReport(influencer); break;
            case 4: launchCampaign(influencer, reader); break;
            case 5: return false; // Trigger logout
            default: System.out.println("Invalid choice!");
        }
        return true;
    }

    /**
    * Displays the full profile of the influencer.
    *
    * @param influencer The influencer whose profile is to be displayed.
    */

    private static void showFullProfile(Influencer influencer) {
        System.out.println("\n=== YOUR PROFILE ===");
        System.out.println("Username: " + influencer.username);
        System.out.println("Niche: " + influencer.getNiche());
        System.out.println("Engagement Rate: " + influencer.getEngagementRate());
        System.out.println("Regions: " + String.join(", ", influencer.getRegions()));
        System.out.printf("Total Earnings: $%.2f\n", influencer.getSponsorshipAmount());
        for(Influencer.Campaign s : influencer.getCampaigns()){
            if(s!=null){
                System.out.println("Campaign Name :"+s.getName()+" Budget: "+s.getBudget()+" Platform: "+s.getPlatform().getClass().getSimpleName()+" Likes: "+s.getPlatform().getLikes()+" Views: "+s.getPlatform().getViews()+" Shares: "+s.getPlatform().getShares());
            }
        }
    }

    /**
    * Displays the contracts associated with the specified influencer.
    *
    * @param username The username of the influencer whose contracts are to be displayed.
    */

    private static void viewMyContracts(String username) {
        System.out.println("\n=== YOUR CONTRACTS ===");
        boolean found = false;
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        
        for (int i = 0; i < contractCount; i++) {
            if (contracts[i].getInfluencer().username.equals(username)) {
                Contract c = contracts[i];
                System.out.printf("- Brand: %s | Amount: $%.2f | Date: %s\n",
                    c.getBrand().brandName,
                    c.getAmount(),
                    sdf.format(c.getStartDate()));
                found = true;
            }
        }
        
        if (!found) System.out.println("No contracts found");
    }

    /**
    * Displays the earnings report for the specified influencer.
    *
    * @param influencer The influencer whose earnings report is to be displayed.
    */

    private static void showEarningsReport(Influencer influencer) {
        System.out.println("\n=== EARNINGS REPORT ===");
        System.out.printf("Total Earnings: $%.2f\n", influencer.getSponsorshipAmount());
        
        System.out.println("\nPayment Details:");
        boolean found = false;
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        
        for (int i = 0; i < contractCount; i++) {
            if (contracts[i].getInfluencer().username.equals(influencer.username)) {
                Contract c = contracts[i];
                System.out.printf("- %s: $%.2f on %s\n",
                    c.getBrand().brandName,
                    c.getAmount(),
                    sdf.format(c.getStartDate()));
                found = true;
            }
        }
        
        if (!found) System.out.println("No payment history found");
    }

    /**
    * Launches a new campaign for the specified influencer.
    *
    * @param influencer The influencer launching the campaign.
    * @param reader BufferedReader to read user input.
    * @throws Exception if an error occurs during the campaign launch.
    */

    private static void launchCampaign(Influencer influencer, BufferedReader reader) throws Exception {
        System.out.println("\n=== LAUNCH NEW CAMPAIGN ===");
        System.out.print("Campaign name: ");
        String name = reader.readLine();
        System.out.print("Budget: $");
        double budget = Double.parseDouble(reader.readLine());
        
        System.out.println("Select platform:");
        System.out.println("1. YouTube");
        System.out.println("2. Instagram");
        System.out.println("3. Twitter");
        System.out.print("Choice: ");
        int platform = Integer.parseInt(reader.readLine());
        Platform p = getPlatform(platform);
        boolean x  =true;
        try{
            influencer.launchCampaign(name, budget, p);
        }catch(PaymentFailedException e){
             System.out.println(e.getMessage());
             x = false;
        }
        if(x) {
            System.out.println("Campaign launched successfully!");
            p.trackCampaign(influencer.username);
            System.out.println("Tracking campaign on " + p.getClass().getSimpleName());
        } 
    }

    /**
    * Displays the brand manager dashboard and handles user choices.
    *
    * @param brand The brand manager currently logged in.
    * @param reader BufferedReader to read user input.
    * @return true if the user chooses to stay logged in; false if the user chooses to log out.
    * @throws Exception if an error occurs during the operation.
    */

    // ========== BRAND MANAGER DASHBOARD ========== //
    private static boolean brandManagerDashboard(BrandManager brand, BufferedReader reader) throws Exception {
        System.out.println("\n=== BRAND DASHBOARD ===");
        System.out.printf("Brand: %s | Budget: $%.2f\n", brand.brandName, brand.getSponsorshipBudget());
        System.out.println("1. Offer New Sponsorship");
        System.out.println("2. View My Contracts");
        System.out.println("3. Logout");
        System.out.print("Choose: ");
        
        int choice = Integer.parseInt(reader.readLine());
        switch (choice) {
            case 1: offerSponsorship(brand, reader); break;
            case 2: viewBrandContracts(brand.username); break;
            case 3: return false; // Trigger logout
            default: System.out.println("Invalid choice!");
        }
        return true;
    }

    /**
    * Offers a sponsorship to an influencer.
    *
    * @param brand The brand manager offering the sponsorship.
    * @param reader BufferedReader to read user input.
    * @throws Exception if an error occurs during the sponsorship offer.
    */

    private static void offerSponsorship(BrandManager brand, BufferedReader reader) throws Exception {
        System.out.println("\n=== OFFER SPONSORSHIP ===");
        System.out.println("Available Influencers:");
        
        for (int i = 0; i < influencerCount; i++) {
            System.out.printf("%d. %s (%s, Engagement: %.1f)\n",
                i+1,
                influencers[i].username,
                influencers[i].getNiche(),
                influencers[i].getEngagementRate());
        }
        
        System.out.print("\nSelect influencer (number): ");
        int infNum = Integer.parseInt(reader.readLine()) - 1;
        
        if (infNum < 0 || infNum >= influencerCount) {
            System.out.println("Invalid selection!");
            return;
        }
        
        System.out.print("Sponsorship amount: $");
        double amount = Double.parseDouble(reader.readLine());
        
        try {
            Influencer selectedInfluencer = influencers[infNum];
            Contract contract = brand.offerSponsorship(selectedInfluencer, amount);
            
            if (contractCount < MAX_CONTRACTS) {
                contracts[contractCount++] = contract;
                System.out.println("\nSponsorship offer created successfully!");
                contract.displayContract();
            } else {
                System.out.println("Cannot create more contracts - system limit reached");
            }
        } catch (InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (PaymentFailedException e) {
            System.out.println("Payment Error: " + e.getMessage());
        }
    }

    /**
    * Displays the contracts associated with the specified brand manager.
    *
    * @param username The username of the brand manager whose contracts are to be displayed.
    */

    private static void viewBrandContracts(String username) {
        System.out.println("\n=== YOUR CONTRACTS ===");
        boolean found = false;
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        
        for (int i = 0; i < contractCount; i++) {
            if (contracts[i].getBrand().username.equals(username)) {
                Contract c = contracts[i];
                System.out.printf("- Influencer: %s | Amount: $%.2f | Date: %s\n",
                    c.getInfluencer().username,
                    c.getAmount(),
                    sdf.format(c.getStartDate()));
                found = true;
            }
        }
        
        if (!found) System.out.println("No contracts found");
    }

    /**
    * Displays the admin dashboard and handles user choices.
    *
    * @param admin The admin currently logged in.
    * @param reader BufferedReader to read user input.
    * @return true if the user chooses to stay logged in; false if the user chooses to log out.
    * @throws Exception if an error occurs during the operation.
    */

    // ========== ADMIN DASHBOARD ========== //
    private static boolean adminDashboard(Admin admin, BufferedReader reader) throws Exception {
        System.out.println("\n=== ADMIN DASHBOARD ===");
        System.out.println("1. View All Influencers");
        System.out.println("2. View All Contracts");
        System.out.println("3. System Statistics");
        System.out.println("4. Logout");
        System.out.print("Choose: ");
        
        int choice = Integer.parseInt(reader.readLine());
        switch (choice) {
            case 1: showAllInfluencers(); break;
            case 2: showAllContracts(); break;
            case 3: showSystemStats(); break;
            case 4: return false; // Trigger logout
            default: System.out.println("Invalid choice!");
        }
        return true;
    }

    /**
    * Displays all influencers in the system.
    */

    private static void showAllInfluencers() {
        System.out.println("\n=== ALL INFLUENCERS ===");
        for (int i = 0; i < influencerCount; i++) {
            System.out.printf("%d. %s - %s (Earnings: $%.2f)\n",
                i+1,
                influencers[i].username,
                influencers[i].getNiche(),
                influencers[i].getSponsorshipAmount());
        }
    }

    /**
    * Displays all contracts in the system.
    */

    private static void showAllContracts() {
        System.out.println("\n=== ALL CONTRACTS ===");
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        
        for (int i = 0; i < contractCount; i++) {
            Contract c = contracts[i];
            System.out.printf("%d. %s -> %s: $%.2f on %s \n",
                i+1,
                c.getBrand().brandName,
                c.getInfluencer().username,
                c.getAmount(),
                sdf.format(c.getStartDate()));
        }
    }

    /**
    * Displays system statistics including total influencers, contracts, and money transferred.
    */

    private static void showSystemStats() {
        System.out.println("\n=== SYSTEM STATISTICS ===");
        System.out.printf("Total Influencers: %d\n", influencerCount);
        System.out.printf("Total Contracts: %d\n", contractCount);
        
        double totalPayments = 0;
        for (int i = 0; i < influencerCount; i++) {
            totalPayments += influencers[i].getSponsorshipAmount();
        }
        System.out.printf("Total Money Transferred: $%.2f\n", totalPayments);
    }

    /**
    * Finds an influencer by username.
    *
    * @param username The username of the influencer to find.
    * @return The influencer object if found; null otherwise.
    */

    // ========== HELPER METHODS ========== //
    private static Influencer findInfluencer(String username) {
        for (int i = 0; i < influencerCount; i++) {
            if (influencers[i].username.equals(username)) {
                return influencers[i];
            }
        }
        return null;
    }

    private static Platform getPlatform(int choice) {
        switch (choice) {
            case 1: return new YouTube();
            case 2: return new Instagram();
            case 3: return new Twitter();
            default: return new YouTube();
        }
    }

    /**
    * Prompts the user to enter a password and retrieves it securely.
    *
    * This method checks if a console is available for secure password input.
    * If a console is available, it uses it to read the password without echoing it.
    * If no console is available, it prompts the user to enter the password normally,
    * which may echo the input.
    *
    * @param reader BufferedReader to read user input if no console is available.
    * @return The password entered by the user as a String.
    * @throws IOException if an I/O error occurs while reading the input.
    */

    private static String getPassword(BufferedReader reader) throws IOException {
        Console console = System.console();
        if (console != null) {
            return new String(console.readPassword("Enter password: "));
        } else {
            System.out.print("Enter password: ");
            return reader.readLine();
        }
    }
}