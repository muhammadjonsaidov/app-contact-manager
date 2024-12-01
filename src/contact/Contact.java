package contact;

public class Contact {

    private String name;

    private String phone;

    private String email;

    private ContactType contactType;

    public Contact(String name, String phone, String email, ContactType contactType) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.contactType = contactType;
    }

    public Contact() {
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", contactType=" + contactType +
                '}';
    }
}
