package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @InjectMocks
    ProductController productController;

    @Mock
    ProductServiceImpl productService;

    @BeforeEach
    void SetUp(){

    }

    @Test
    void testCreateProductPage() {
        Model model = mock(Model.class);
        String returnedValue = productController.createProductPage(model);
        assertEquals("createProduct", returnedValue);
    }

    @Test
    void testCreateProductPost() {
        Model model = mock(Model.class);
        Product product = new Product();
        String returnedValue = productController.createProductPost(product, model);

        verify(productService).create(product);
        assertEquals("redirect:list", returnedValue);
    }

    @Test
    void testProductListPage() {
        Model model = mock(Model.class);
        String returnedValue = productController.productListPage(model);

        verify(productService).findAll();
        assertEquals("productList", returnedValue);
    }

    @Test
    void testEditProductPage() {
        Model model = mock(Model.class);
        String returnedValue = productController.editProductPage("productId", model);

        verify(productService).findById("productId");
        assertEquals("EditProduct", returnedValue);
    }

    @Test
    void testEditProductPost() {
        Model model = mock(Model.class);
        Product product = new Product();
        String returnedValue = productController.editProductPost(product, model);

        verify(productService).edit(product);
        assertEquals("redirect:list", returnedValue);
    }

    @Test
    void testDeleteProductPost() {
        Product product = new Product();
        String returnedValue = productController.deleteProductPost("productId");

        assertEquals("redirect:/product/list", returnedValue);
    }
}