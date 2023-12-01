package vn.edu.iuh.fit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.models.OrderDetail;
import vn.edu.iuh.fit.repositories.OrderDetailRepository;

@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public OrderDetail create(OrderDetail orderDetail){
        return orderDetailRepository.save(orderDetail);
    }

    public long getCurrentOrderDetailID(){
        return orderDetailRepository.getCurrentOrderDetailID();
    }
}
