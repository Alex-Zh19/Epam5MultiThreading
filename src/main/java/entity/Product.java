package entity;

public class Product {
    private String productName;
    private boolean isPerishable;

    public Product(String productName, boolean isPerishable) {
        this.productName = productName;
        this.isPerishable = isPerishable;
    }

    public String getProductName() {
        return productName;
    }

    public boolean isPerishable() {
        return isPerishable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return isPerishable == product.isPerishable && product.productName.equals(this.productName);
    }

    @Override
    public int hashCode() {
        int result=13;
        int buff=7;
        Boolean isPerishable=this.isPerishable;
        result=result+ productName.hashCode()*buff+isPerishable.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result=new StringBuilder("Product{name=");
        result.append(productName);
        result.append(", isPerishable=");
        result.append(isPerishable);
        result.append("}");
        return result.toString();
    }
}
