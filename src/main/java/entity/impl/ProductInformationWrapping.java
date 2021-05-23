package entity.impl;

import entity.InformationWrapping;

public class ProductInformationWrapping implements InformationWrapping {
    private String ProductName;
    private boolean isPerishable;

    public  ProductInformationWrapping(){}

    public ProductInformationWrapping(String name,Boolean isPerishable){
        this.ProductName=name;
        this.isPerishable=isPerishable;
    }
}
