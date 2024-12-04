package contact;

public class ContactType {

    private String icon;

    private String name;

    public ContactType(String icon, String name) {
        this.icon = icon;
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("""
                        \s
                         -> Type Icon: %s
                         -> Type Name: %s
               \s""", icon, name);
    }
}
