package vn.edu.iuh.fit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.entity.RequestOrderDate;
import vn.edu.iuh.fit.entity.ResponseOrderByDateBetween;
import vn.edu.iuh.fit.models.Order;
import vn.edu.iuh.fit.services.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public boolean create(@RequestBody Order order) {
        return orderService.create(order) != null;
    }

    @PostMapping("/orders-by-date-between")
    public List<ResponseOrderByDateBetween> getOrderByDateBetween(@RequestBody RequestOrderDate requestOrderDate) {
        return orderService.getOrderByDateBetWeen(requestOrderDate);
    }

    @GetMapping("/{id}")
    public Order getByID(@PathVariable long id) {
        return orderService.getByID(id).orElse(null);
    }

    @GetMapping("/current-order-id")
    public long getCurrentOrderID() {
        return orderService.getCurrentOrderID();
    }
}
