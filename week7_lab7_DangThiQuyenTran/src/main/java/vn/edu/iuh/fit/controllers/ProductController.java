package vn.edu.iuh.fit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.entity.ResponseInfoProduct;
import vn.edu.iuh.fit.models.Product;
import vn.edu.iuh.fit.response.ResponseProduct;
import vn.edu.iuh.fit.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public Product getByID(@PathVariable long id) {
        return productService.findById(id).orElse(null);
    }

    @GetMapping("/ordered-by-cust-{id}")
    public List<ResponseProduct> getProductsOrderedByCustomerID(@PathVariable long id) {
        return productService.getProductsOrderedByCustomerID(id);
    }

    @GetMapping("/information-products/{page}")
    public List<ResponseInfoProduct> getInfoProducts(@PathVariable int page) {
        Pageable pageable = PageRequest.of(page, 9);
        return productService.getInfoProducts(pageable).getContent();
    }

    @GetMapping("/get-total-pages")
    public long getTotalPages() {
        return (long) Math.ceil((double) productService.getTotalPages() / 9);
    }
}
