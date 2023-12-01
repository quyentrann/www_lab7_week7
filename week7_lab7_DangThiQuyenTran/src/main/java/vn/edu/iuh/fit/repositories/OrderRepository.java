package vn.edu.iuh.fit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.entity.RequestOrderDate;
import vn.edu.iuh.fit.entity.ResponseOrderByDateBetween;
import vn.edu.iuh.fit.models.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o.orderId from Order o order by o.orderId desc limit 1")
    public long getCurrentOrderID();

    @Query(value = "SELECT od.order_detail_id, p.name, o.order_date, SUM(od.price) AS prices, SUM(od.quantity) AS quantity FROM orders AS o \n" +
            "INNER JOIN order_detail AS od ON o.order_id = od.order_id\n" +
            "INNER JOIN product AS p ON p.product_id = od.product_id\n" +
            "WHERE order_date BETWEEN ?1 AND ?2\n" +
            "GROUP BY p.name, o.order_date ORDER BY o.order_date", nativeQuery = true)
    public List<Object[]> getOrderByDateBetWeen(String fromDate, String toDate);

    @Query(value = "SELECT od.order_detail_id, p.name, o.order_date, SUM(od.price) AS prices, SUM(od.quantity) AS quantity, e.emp_id\n" +
            "FROM orders AS o \n" +
            "INNER JOIN order_detail AS od ON o.order_id = od.order_id\n" +
            "INNER JOIN product AS p ON p.product_id = od.product_id\n" +
            "INNER JOIN employee AS e ON o.emp_id = e.emp_id\n" +
            "WHERE e.emp_id = ?1 AND order_date BETWEEN ?2 AND ?3\n" +
            "GROUP BY p.name, o.order_date\n" +
            "ORDER BY o.order_date", nativeQuery = true)
    public List<Object[]> getOrderByEmpIDANDDateBetWeen(long empID, String fromDate, String toDate);
}
