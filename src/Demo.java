import service.ContactService;
import service.ContactServiceImpl;

import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        ContactService contactService = new ContactServiceImpl(5, 2);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    1=> Add contact
                    2=> Edit contact
                    3=> Delete contact
                    4=> List contact
                    5=> Search contact
                    0=> Terminate
                    """);

            int com = scanner.nextInt();
            scanner.nextLine();

            switch (com) {
                case 0 -> {
                    System.out.println("Terminating the program. Goodbye!");
                    scanner.close();
                    System.exit(0);
                }
                case 1 -> contactService.add();
                case 2 -> contactService.edit();
                case 3 -> contactService.delete();
                case 4 -> contactService.showList();
                case 5 -> contactService.search();
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
