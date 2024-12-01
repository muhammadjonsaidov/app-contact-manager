package service;

import contact.Contact;
import contact.ContactType;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ContactServiceImpl implements ContactService {

    ContactType[] contactTypes;
    Contact[] contacts;

    public ContactServiceImpl(int capacityContact, int capacityType) {
        contactTypes = new ContactType[capacityType];
        contactTypes[0] = new ContactType("\uD83D\uDC6A", "Parents");
        contactTypes[1] = new ContactType("\uD83E\uDEC2", "Friends");

        contacts = new Contact[capacityContact];
    }


    @Override
    public void add() {
        Scanner scanner = new Scanner(System.in);
        /*//get fields - 4 (contactType)
        //name or phone not be null
        //save*/

        System.out.println("Enter name: ");
        String name = scanner.nextLine().trim();

        System.out.println("Enter phone: ");
        String phone = scanner.nextLine().trim();

        System.out.println("Enter email (optional): ");
        String email = scanner.nextLine().trim();

        //chosen or skip
        ContactType contactType = chooseContactType(scanner);

        if (name.isBlank() && phone.isBlank()) {
            System.err.println("You can not create contact with null name or phone");
            add();
            return;
        }

        Contact contact = new Contact(name, phone, email, contactType);

        while (true) {
            System.out.println(contact);
            System.out.println("""
                    Do you want to save the contact?
                    1 => Save
                    2 => Discard
                    3 => Update
                    """);
            int cmd = scanner.nextInt();
            switch (cmd) {
                case 1 -> {
                    saveContact(contact);
                    return;
                }
                case 2 -> {
                    System.out.println("Contact discarded");
                    return;
                }
                case 3 -> {
                    updateContact(contact, scanner);
                }
                default -> System.err.println("Invalid command. Please try again.");
            }
        }
    }

    private void updateContact(Contact contact, Scanner scanner) {
        while (true) {
            System.out.println("""
                    Update options:
                    1 => Update name
                    2 => Update phone
                    3 => Update email
                    4 => Update contact type
                    5 => Cancel update
                    """);
            int cmd = scanner.nextInt();
            scanner.nextLine();
            switch (cmd) {
                case 1 -> {
                    System.out.println("Enter new name: ");
                    String newName = scanner.nextLine();
                    if (!newName.isBlank()) {
                        contact.setName(newName);
                        System.out.println("Name updated successfully.");
                    } else {
                        System.err.println("Name cannot be blank. Update canceled.");
                    }
                }
                case 2 -> {
                    System.out.println("Enter new phone: ");
                    String newPhone = scanner.nextLine();
                    if (!newPhone.isBlank()) {
                        contact.setPhone(newPhone);
                        System.out.println("Phone updated successfully.");
                    } else {
                        System.err.println("Phone cannot be blank. Update canceled.");
                    }
                }
                case 3 -> {
                    System.out.println("Enter new email: ");
                    String newEmail = scanner.nextLine();
                    contact.setEmail(newEmail);
                    System.out.println("Email updated successfully.");
                }
                case 4 -> {
                    System.out.println("Choose new contact type: ");
                    ContactType newContactType = chooseContactType(scanner);

                    if (newContactType != null) {
                        contact.setContactType(newContactType);
                        System.out.println("Contact type updated successfully.");
                    } else {
                        System.out.println("No contact type selected. Update canceled.");
                    }
                }
                case 5 -> {
                    System.out.println("Update canceled.");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }

            System.out.println("""
                    Do you want to update another field?
                    1 => Yes
                    2 => No
                    """);

            int anotherField = scanner.nextInt();
            scanner.nextLine();
            if (anotherField == 2) {
                break;
            }
        }
    }

    @Override
    public void edit() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void showList() {
        while (true) {
            System.out.println("Enter contact number if you want to see about this contact information.");
            for (int i = 0; i < contacts.length; i++) {
                if (contacts[i] == null)
                    break;
                System.out.println(i + 1 + ". " + contacts[i].getName());
            }
            System.out.println("0 => Exit to menu");
            Scanner scanner = new Scanner(System.in);
            int idx = scanner.nextInt();
            if (idx == 0)
                return;
            if (idx - 1 >= contacts.length || contacts[idx - 1] == null) {
                showList();
                return;
            }

            System.out.println(contacts[idx - 1]);
        }
    }

    @Override
    public void search() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter searching keyword");
        String pattern = scanner.nextLine().toLowerCase();
        
        int idx = 1;
        for (Contact contact : contacts) {
            if (contact == null)
                break;
            String name = contact.getName();
            String phone = contact.getPhone();
            String email = contact.getEmail();
            ContactType contactType = contact.getContactType();
            String contactTypeName = contactType != null ? contactType.getName() : null;
            String contactTypeIcon = contactType != null ? contactType.getIcon() : null;

            if ((name != null && name.toLowerCase().contains(pattern)) ||
                (phone != null && phone.toLowerCase().contains(pattern)) ||
                (email != null && email.toLowerCase().contains(pattern)) ||
                (contactTypeName != null && contactTypeName.toLowerCase().contains(pattern)) ||
                (contactTypeIcon != null && contactTypeIcon.toLowerCase().contains(pattern))) {
                System.out.println(idx + ". " + contact);
                idx++;
            }
        }
    }

    @Override
    public void merge() {

    }


    private ContactType chooseContactType(Scanner scanner) {
        //[parents, friends,mate, bla, battar]
        while (true) {
            System.out.println("Choose or add contact type: ");

            for (int i = 0; i < contactTypes.length; i++) {
                ContactType contactType = contactTypes[i];
                if (contactType == null)
                    break;
                System.out.println((i + 1) + ". " + contactType.getIcon() + " " + contactType.getName());
            }
            System.out.println("-1 => Skip");
            System.out.println("0=> Add new contact type");

            int idx;
            try {
                idx = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }

            if (idx == -1) return null;

            if (idx == 0) {
                System.out.println("Enter new contact type icon: ");
                String typeIcon = scanner.nextLine().trim();

                System.out.println("Enter new contact type name: ");
                String typeName = scanner.nextLine().trim();

                if (typeIcon.isEmpty() || typeName.isEmpty()) {
                    System.err.println("Icon and name cannot be empty. Try again.");
                    continue;
                }

                ContactType newContactType = new ContactType(typeIcon, typeName);
                boolean isAdded = false;
                for (int i = 0; i < contactTypes.length; i++) {
                    if (contactTypes[i] == null) {
                        contactTypes[i] = newContactType;
                        isAdded = true;
                        break;
                    }
                }

                if (!isAdded) {
                    ContactType[] newArray = new ContactType[contactTypes.length * 2];
                    System.arraycopy(contactTypes, 0, newArray, 0, contactTypes.length);
                    newArray[contactTypes.length] = newContactType;
                    contactTypes = newArray;
                }

                System.out.println("New contact type added successfully.");
                continue;
            }

            if (idx - 1 < 0 || idx - 1 >= contactTypes.length || contactTypes[idx - 1] == null) {
                System.err.println("Incorrect option. Try again.");
                continue;
            }

            return contactTypes[idx - 1];
        }
    }

    private void saveContact(Contact contact) {
        for (int i = 0; i < contacts.length; i++) {
            if (contacts[i] == null) {
                contacts[i] = contact;
                System.out.println("Contact saved successfully.");
                return;
            }
        }
        Contact[] newArray = new Contact[contacts.length * 2];
        System.arraycopy(contacts, 0, newArray, 0, contacts.length);
        newArray[contacts.length] = contact;
        contacts = newArray;
        System.out.println("Contact saved successfully");
    }
}
