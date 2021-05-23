package entity;

public class Product {
    private String name;
    private boolean isPerishable;

    public Product(String name, boolean isPerishable) {
        this.name = name;
        this.isPerishable = isPerishable;
    }

    public String getName() {
        return name;
    }

    public boolean isPerishable() {
        return isPerishable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return isPerishable == product.isPerishable && product.name.equals(this.name);
    }

    @Override
    public int hashCode() {
        int result=13;
        int buff=7;
        Boolean isPerishable=this.isPerishable;
        result=result+name.hashCode()*buff+isPerishable.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result=new StringBuilder("Product{name=");
        result.append(name);
        result.append(", isPerishable=");
        result.append(isPerishable);
        result.append("}");
        return result.toString();
    }
}
