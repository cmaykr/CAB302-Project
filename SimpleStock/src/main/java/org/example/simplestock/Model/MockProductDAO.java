package org.example.simplestock.Model;

import java.util.ArrayList;

public class MockProductDAO implements IProductDAO {
    public static final ArrayList<Product> products = new ArrayList<>();
    private static int autoIncrementedId = 0;
    public MockProductDAO(){
        //Add some inital products
        addProduct(new Prodcut("name","tyep","description","location","quantity","insured","price"));
        addProduct(new Prodcut("name","tyep","description","location","quantity","insured","price"));
        addProduct(new Prodcut("name","tyep","description","location","quantity","insured","price"));
    }
    @Override
    public void addProduct(Product product){
        product.setId(autoIncrementedId);
        autoIncrementedId++;
        products.add(product);
    }
    @Override
    public void updateProduct(Product product){
        for(int i=0;i<products.size();i++){
            if(products.get(i).getId()==product.getId()){
                products.set(i,product);
                break;
            }
        }
    }
    @Override
    public void deleteProduct(Product product){
        products.remove(product);
    }
    @Override
    public Product getProduct(int id){
        for(Product product:products){
            if(product.getId()==id){
                return product;
            }
        }
        return null;
    }
    @Override
    public List<Product> getAllProducts(){
        return new ArrayList<>(products);
    }

}
