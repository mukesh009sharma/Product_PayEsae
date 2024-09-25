package PayEase;
//Main class
import java.util.Scanner;

public class _8Payease {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("------------------------------------------------");
        System.out.println(" Welcome to PayEase - Your Digital Wallet ");
        System.out.println("------------------------------------------------");

        boolean exitApp = false;

        while (!exitApp) {
            // Main Menu
            System.out.println("\nChoose an option:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Option: ");
            int mainOption = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (mainOption) {
                case 1:
                    // User Registration
                    boolean validRegistration = false;
                    while (!validRegistration) {
                        System.out.println("\nRegister a new user:");

                        System.out.print("Username: ");
                        String username = scanner.nextLine();

                        System.out.print("Password: ");
                        String password = scanner.nextLine();

                        String email;
                        while (true) {
                            System.out.print("Email: ");
                            email = scanner.nextLine();
                            if (isValidEmail(email)) {
                                break;
                            } else {
                                System.out.println("Invalid email format. Please enter a valid email.");
                            }
                        }

                        String phone;
                        while (true) {
                            System.out.print("Phone: ");
                            phone = scanner.nextLine();
                            if (isValidPhone(phone)) {
                                break;
                            } else {
                                System.out.println("Invalid phone number. It must be exactly 10 digits.");
                            }
                        }

                        _3User.registerUser(username, password, email, phone);
                        validRegistration = true; // Exit the loop after successful registration
                        System.out.println("Registration successful! You can now log in.");
                    }
                    break;

                case 2:
                    // User Login
                    System.out.println("\nLogin:");
                    System.out.print("Username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Password: ");
                    String loginPassword = scanner.nextLine();
                    _3User user = _3User.loginUser(loginUsername, loginPassword);

                    if (user != null) {
                        System.out.println("Logged in successfully. User ID: " + user.getUserId());
                        _4Wallet wallet = new _4Wallet(user.getUserId());

                        boolean exit = false;
                        while (!exit) {
                            // Options Menu after Login
                            System.out.println("\nChoose an option:");
                            System.out.println("1. View Balance");
                            System.out.println("2. Add Funds");
                            System.out.println("3. Withdraw Funds");
                            System.out.println("4. Transfer Funds");
                            System.out.println("5. Pay Merchant");
                            System.out.println("6. Logout");
                            System.out.print("Option: ");
                            int option = scanner.nextInt();

                            switch (option) {
                                case 1:
                                    // View Balance
                                    System.out.println("Current Balance: " + wallet.viewBalance());
                                    break;

                                case 2:
                                    // Add Funds
                                    System.out.print("Enter amount to add to wallet: ");
                                    double addAmount = scanner.nextDouble();
                                    if (wallet.addFunds(addAmount)) {
                                        System.out.println("Funds added successfully.");
                                    } else {
                                        System.out.println("Failed to add funds.");
                                    }
                                    break;

                                case 3:
                                    // Withdraw Funds
                                    System.out.print("Enter amount to withdraw from wallet: ");
                                    double withdrawAmount = scanner.nextDouble();
                                    if (wallet.withdrawFunds(withdrawAmount)) {
                                        System.out.println("Funds withdrawn successfully.");
                                    } else {
                                        System.out.println("Failed to withdraw funds.");
                                    }
                                    break;

                                case 4:
                                    // Transfer Funds
                                    System.out.print("Enter receiver user ID: ");
                                    int receiverId = scanner.nextInt();
                                    System.out.print("Enter amount to transfer: ");
                                    double transferAmount = scanner.nextDouble();
                                    if (_5Transaction.transferFunds(user.getUserId(), receiverId, transferAmount)) {
                                        System.out.println("Funds transferred successfully.");
                                    } else {
                                        System.out.println("Failed to transfer funds.");
                                    }
                                    break;

                                case 5:
                                    // Pay Merchant
                                    scanner.nextLine(); // Consume newline
                                    System.out.print("Enter merchant ID: ");
                                    String merchantId = scanner.nextLine();
                                    System.out.print("Enter amount to pay: ");
                                    double paymentAmount = scanner.nextDouble();
                                    if (_6MerchantPayment.payMerchant(merchantId, paymentAmount, user.getUserId())) {
                                        System.out.println("Payment to merchant completed successfully.");
                                    } else {
                                        System.out.println("Failed to pay merchant.");
                                    }
                                    break;

                                case 6:
                                    // Logout
                                    System.out.println("Logging out...");
                                    exit = true;
                                    break;

                                default:
                                    System.out.println("Invalid option. Please try again.");
                                    break;
                            }
                        }
                    } else {
                        System.out.println("Login failed. Please check your username and password.");
                    }
                    break;

                case 3:
                    // Exit Application
                    System.out.println("Thank you for using PayEase! Goodbye.");
                    exitApp = true;
                    break;

                default:
                    System.out.println("Invalid option. Please choose 1, 2, or 3.");
                    break;
            }
        }

        scanner.close();
    }

    // Email validation: checks if email contains '@'
    private static boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }

    // Phone validation: checks if phone number is 10 digits
    private static boolean isValidPhone(String phone) {
        return phone != null && phone.matches("\\d{10}");
    }
}
