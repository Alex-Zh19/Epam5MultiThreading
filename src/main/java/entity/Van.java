package entity;
import exception.MultiThreadingException;
import java.util.ArrayList;
import java.util.List;

public class Van {
    private List<Product> shipment = new ArrayList<>();

    public Van() {
    }

    public Van(Van baseVan) throws MultiThreadingException {
        if(baseVan==null){
            throw new MultiThreadingException("NullPointer exception. Base cannot be null");
        }
        this.shipment.addAll(baseVan.shipment);
    }

    public boolean add(Product product) {
        return shipment.add(product);
    }

    public boolean addAll(List<Product> products) {
        return shipment.addAll(products);
    }

    public Product getProduct(int index) throws MultiThreadingException {
        if (index < 0 || index >= shipment.size()) {
            throw new MultiThreadingException("Out of bound exception. Wrong index: " + index);
        }
        return shipment.get(index);
    }

    public ArrayList<Product> getShipment() {
        return new ArrayList<>(shipment);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Van van = (Van) o;
        return van.shipment.equals(this.shipment);
    }

    @Override
    public int hashCode() {
        return shipment.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder result= new StringBuilder("Van{shipment=");
        result.append(shipment.toString());
        result.append("}");
        return result.toString();
    }
}
