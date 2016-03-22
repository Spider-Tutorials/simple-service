package tutorial;

public class Product {
    private String name;
    private String description;
    private String producer;

    public Product() {
    }

    public Product(String name, String description, String producer) {
        this.name = name;
        this.description = description;
        this.producer = producer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }
}
