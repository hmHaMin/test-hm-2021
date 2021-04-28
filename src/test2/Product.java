package test2;

public class Product {
    private String code;
    private String name;
    private String owner;
    private String inputDate;
    private String warrantyYear;
    private static Product instance;

    private Product() {
    }

    public static Product getInstance() {
        if (instance == null) {
            instance = new Product();
        }

        return instance;
    }

    public static Product getProduct(String line) {
        Product product = getInstance();
        String[] s = line.split(",");
        product.warrantyYear = s[4];
        return product;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public String getOwner() {
        return this.owner;
    }

    public String getInputDate() {
        return this.inputDate;
    }

    public String getWarrantyYear() {
        return this.warrantyYear;
    }

    public void setWarrantyYear(String warrantyYear) {
        this.warrantyYear = warrantyYear;
    }

    public String toString() {
        return this.code + "," + this.name + "," + this.owner + "," + this.inputDate + "," + this.warrantyYear;
    }
}