package vn.edu.iuh.fit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import vn.edu.iuh.fit.entity.ResponseInfoProduct;
import vn.edu.iuh.fit.models.Product;
import vn.edu.iuh.fit.repositories.ProductRepository;
import vn.edu.iuh.fit.response.ResponseProduct;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Optional<Product> findById(long id){
        return productRepository.findById(id);
    }
    public List<ResponseProduct> getProductsOrderedByCustomerID(long id){
        List<ResponseProduct> responseProducts = new ArrayList<>();
        for(Object[] o : productRepository.getProductsOrderedByCustomerID(id)){
            ResponseProduct responseProduct = new ResponseProduct((String) o[0], (String) o[1], (Double) o[2],(Integer) o[3],(String) o[4]);
            responseProducts.add(responseProduct);
        }
        return responseProducts;
    }

    public Page<ResponseInfoProduct> getInfoProducts(Pageable pageable) {
        List<ResponseInfoProduct> informationProducts = new ArrayList<>();
        for (Object[] result : productRepository.getInfoProducts(pageable)) {
            long productId = (long) result[0];
            String productName = (String) result[1];
            String imagePath = (String) result[2];
            double price = (double) result[3];

            ResponseInfoProduct informationProduct = new ResponseInfoProduct(productId, productName, imagePath, price);
            informationProducts.add(informationProduct);
        }
        return new PageImpl<>(informationProducts, pageable, informationProducts.size());
    }

    public long getTotalPages(){
        return productRepository.count();
    }
}
