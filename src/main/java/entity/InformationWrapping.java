package entity;

import entity.impl.ProductInformationWrapping;

public interface InformationWrapping {
    default InformationWrapping wrap(String name,boolean isPerishable) {
        CustomWrapper<String,Boolean,InformationWrapping> convert=(productName,perishable)->
                new ProductInformationWrapping(productName,perishable);
        return convert.wrap(name,isPerishable);
    }
}
