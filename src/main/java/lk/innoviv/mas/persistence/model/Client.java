package lk.innoviv.mas.persistence.model;

public class Client implements BaseSerializable {

    public enum Attribute {
        Domain(null),
        ApplicationName(null),;

        private String defaultValue;

        Attribute(String defaultValue) {
            this.defaultValue = defaultValue;
        }

        public String getDefaultValue() {
            return defaultValue;
        }
    }

    private Long id;

    private String name;


    public Client() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long clientId) {
        this.id = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{id: " + getId() +
                "\nname: " + getName() + "}";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + getId().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Client)) {
            return super.equals(other);
        }

        Client otherClient = (Client) other;
        if (getId() != null) {
            return getId() == otherClient.getId();
        } else {
            return super.equals(other);
        }
    }
}
