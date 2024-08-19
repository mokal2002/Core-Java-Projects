import Handler.Accounts;
import Handler.User;

import config.AppContants;
import service.AccountManager;

import java.sql.*;
import java.util.Scanner;


public class SecureBank {
    private static final String url = AppContants.DB_URL;
    private static final String username = AppContants.DB_USER;
    private static final String password = AppContants.DB_PASSWORD;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            Scanner scanner =  new Scanner(System.in);
            User user = new User(connection, scanner);
            Accounts accounts = new Accounts(connection, scanner);
            AccountManager accountManager = new AccountManager(connection, scanner);

            String email;
            long account_number;

            while(true){
                System.out.println("#################################");
                System.out.println("*** WELCOME TO BANKING SYSTEM ***");
                System.out.println("#################################");
                System.out.println("1.        Register");
                System.out.println("2.        Login");
                System.out.println("3.        Exit");
                System.out.println("#################################");
                // System.out.println();
                System.out.println("---------------------------------");
                System.out.print("Enter your choice: ");
                int choice1 = scanner.nextInt();
                System.out.println("---------------------------------");

                switch (choice1){
                    case 1:
                        user.register();
                        break;
                    case 2:
                        email = user.login();
                        if(email!=null){
                            System.out.println();
                            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                            System.out.println("User Logged In!");
                            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                            if(!accounts.account_exist(email)){
                                System.out.println();
                                System.out.println("No Account Found for this Email Address!!");
                                System.out.println("-------------------------------");
                                System.out.println("1. Open a new Bank Account");
                                
                                System.out.println("2. Exit");
                                System.out.println("-------------------------------");

                                if(scanner.nextInt() == 1) {
                                    account_number = accounts.open_account(email);
                                    System.out.println("========================================");
                                    System.out.println("Account Created Successfully");
                                    System.out.println("Your Account Number is: " + account_number);
                                    System.out.println("========================================");

                                }else{
                                    break;
                                }

                            }
                            account_number = accounts.getAccount_number(email);
                            int choice2 = 0;
                            while (choice2 != 5) {
                                System.out.println();
                                System.out.println("================================");
                                System.out.println("Welcome to your Account!");
                                System.out.println("================================");
                                System.out.println("1.         Debit Money");
                                System.out.println("2.         Credit Money");
                                System.out.println("3.         Transfer Money");
                                System.out.println("4.         Check Balance");
                                System.out.println("5.         Log Out");
                                System.out.println("================================");
                                System.out.println("--------------------------------");
                                System.out.print("Enter your choice: ");

                                choice2 = scanner.nextInt();
                                System.out.println("--------------------------------");

                                switch (choice2) {
                                    case 1:
                                        accountManager.debit_money(account_number);
                                        break;
                                    case 2:
                                        accountManager.credit_money(account_number);
                                        break;
                                    case 3:
                                        accountManager.transfer_money(account_number);
                                        break;
                                    case 4:
                                        accountManager.getBalance(account_number);
                                        break;
                                    case 5:
                                        break;
                                    default:
                                        System.out.println("????????????????????????????");
                                        System.out.println("Enter Valid Choice!");
                                        System.out.println("????????????????????????????");

                                        break;
                                }
                            }

                        }
                        else{
                            System.out.println("????????????????????????????????");

                            System.out.println("Incorrect Email or Password!");
                            System.out.println("????????????????????????????????");

                        }
                    case 3:
                        System.out.println("**************************************");
                        System.out.println("THANK YOU FOR USING BANKING SYSTEM!!!");
                        
                        System.out.println("Exiting System!");
                        System.out.println("**************************************");
                        return;
                    default:
                        System.out.println("????????????????????????????????");

                        System.out.println("Enter Valid Choice");
                        System.out.println("????????????????????????????????");

                        break;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}