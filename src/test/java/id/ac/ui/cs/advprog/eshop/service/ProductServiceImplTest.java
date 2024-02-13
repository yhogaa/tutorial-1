package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @InjectMocks
    ProductServiceImpl productService;

    @Mock
    ProductRepository productRepository;

    @BeforeEach
    void SetUp(){

    }

    Product initiateProduct1() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        return product;
    }

    Product initiateProduct2() {
        Product product = new Product();
        product.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product.setProductName("Sampo Cap Usep");
        product.setProductQuantity(100);
        return product;
    }

    @Test
    void testCreateAndFind() {
        Product product = initiateProduct1();

        Mockito.when(productRepository.create(product)).thenReturn(product);
        productService.create(product);

        Mockito.when(productRepository.findAll()).thenReturn(List.of(product).iterator());
        List<Product> productList = productService.findAll();

        assertFalse(productList.isEmpty());
        Product savedProduct = productList.getFirst();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        List<Product> productList = new ArrayList<>();
        Mockito.when(productRepository.findAll()).thenReturn(productList.iterator());

        List<Product> products = productService.findAll();

        assertTrue(products.isEmpty());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = initiateProduct1();

        Mockito.when(productRepository.create(product1)).thenReturn(product1);
        productService.create(product1);

        Product product2 = initiateProduct2();
        Mockito.when(productRepository.create(product2)).thenReturn(product2);
        productService.create(product2);

        Mockito.when(productRepository.findAll()).thenReturn(List.of(product1, product2).iterator());
        List<Product> productList = productService.findAll();

        assertFalse(productList.isEmpty());
        Product savedProduct = productList.removeFirst();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productList.removeFirst();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertTrue(productList.isEmpty());
    }

    @Test
    void testEditProduct() {
        Product product = initiateProduct1();

        Mockito.when(productRepository.create(product)).thenReturn(product);
        productService.create(product);

        Product editedProduct = new Product();
        editedProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        editedProduct.setProductName("Sampo Cap Bango");
        editedProduct.setProductQuantity(0);
        productService.edit(editedProduct);

        Mockito.when(productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6")).thenReturn(editedProduct);
        Product resultProduct = productService.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");

        assertEquals(editedProduct, resultProduct);
        Mockito.verify(productRepository).edit(editedProduct);
    }

    @Test
    void testDeleteProduct() {
        Product product1 = initiateProduct1();

        Mockito.when(productRepository.create(product1)).thenReturn(product1);
        productService.create(product1);

        productService.deleteById("eb558e9f-1c39-460e-8860-71af6af63bd6");

        Mockito.verify(productRepository).deleteById("eb558e9f-1c39-460e-8860-71af6af63bd6");
    }
}