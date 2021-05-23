package main;

import entity.Product;
import entity.Van;

public class Main {
    public static void main(String[] args) {
        Van van=new Van();
        Product product=new Product("orange",true);
        Product product1=new Product("milk",true);
        van.add(product);
        van.add(product1);
        System.out.println(van);
    }
}
