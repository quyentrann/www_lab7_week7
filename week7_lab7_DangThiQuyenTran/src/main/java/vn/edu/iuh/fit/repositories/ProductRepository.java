package vn.edu.iuh.fit.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.models.Product;
import vn.edu.iuh.fit.response.ResponseProduct;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT p.name, prm.path, od.price, od.quantity, o.order_date " +
            "FROM Customer c " +
            "INNER JOIN Orders o ON c.custId = o.custId " +
            "INNER JOIN OrderDetail od ON o.orderId = od.orderId " +
            "INNER JOIN Product p ON od.productId = p.productId " +
            "INNER JOIN ProductImage prm ON p.productId = prm.productId " +
            "WHERE c.custId = :customerId " +
            "ORDER BY o.orderDate DESC", nativeQuery = true)
    List<Object[]> getProductsOrderedByCustomerID(@Param("customerId") long customerId);

    @Query(value = "SELECT p.productId, p.name, pm.path, pr.price " +
            "FROM Product p " +
            "INNER JOIN ProductImage pm ON p.productId = pm.productId " +
            "INNER JOIN (" +
            "   SELECT priceId, note, price, priceDateTime, productId " +
            "   FROM (" +
            "       SELECT *, ROW_NUMBER() OVER (PARTITION BY productId ORDER BY priceDateTime DESC) AS rn " +
            "       FROM ProductPrice" +
            "   ) AS subquery " +
            "   WHERE rn = 1" +
            ") AS pr ON p.productId = pr.productId",
            countQuery = "SELECT COUNT(*) FROM Product",
            nativeQuery = true)
    List<Object[]> getInfoProducts(Pageable pageable);
}
