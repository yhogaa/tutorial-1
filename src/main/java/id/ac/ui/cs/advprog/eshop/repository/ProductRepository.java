package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Product deleteById(String id) {
        Product product = findById(id);
        if (product != null) {
            productData.remove(product);
            return product; // return product yang dihapus
        }
        return null; // return null jika tidak ada produk dengan id tersebut
    }

    public Product findById(String id){
        for(Product product : productData){
            if(product.getProductId().equals(id)){
                return product;
            }
        }
        return null; // return null jika tidak ada produk dengan id tersebut
    }

    public Iterator<Product> findAll(){
        return productData.iterator();
    }
}