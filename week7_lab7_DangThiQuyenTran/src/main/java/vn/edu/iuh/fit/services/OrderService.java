package vn.edu.iuh.fit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import vn.edu.iuh.fit.entity.RequestOrderDate;
import vn.edu.iuh.fit.entity.ResponseOrderByDateBetween;
import vn.edu.iuh.fit.models.Order;
import vn.edu.iuh.fit.repositories.OrderRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order create(Order order){
        return orderRepository.save(order);
    }

    public List<ResponseOrderByDateBetween> getOrderByDateBetWeen(RequestOrderDate requestOrderDate){
        List<ResponseOrderByDateBetween> responseOrderByDateBetweens = new ArrayList<>();
        if(requestOrderDate.getEmpID() == 0){
            for (Object[] o : orderRepository.getOrderByDateBetWeen(requestOrderDate.getFromDate(), requestOrderDate.getToDate())){
                ResponseOrderByDateBetween responseOrderByDateBetween = new ResponseOrderByDateBetween((long)o[0],o[1]+"",o[2]+"",(double)o[3],(BigDecimal) o[4]);
                responseOrderByDateBetweens.add(responseOrderByDateBetween);
            }
        }
        else{
            for (Object[] o : orderRepository.getOrderByEmpIDANDDateBetWeen(requestOrderDate.getEmpID(), requestOrderDate.getFromDate(), requestOrderDate.getToDate())){
                ResponseOrderByDateBetween responseOrderByDateBetween = new ResponseOrderByDateBetween((long)o[0],o[1]+"",o[2]+"",(double)o[3],(BigDecimal) o[4]);
                responseOrderByDateBetweens.add(responseOrderByDateBetween);
            }
        }
        return responseOrderByDateBetweens;
    }
    public Optional<Order> getByID(long id){
        return orderRepository.findById(id);
    }
    public long getCurrentOrderID(){
        return orderRepository.getCurrentOrderID();
    }
}
