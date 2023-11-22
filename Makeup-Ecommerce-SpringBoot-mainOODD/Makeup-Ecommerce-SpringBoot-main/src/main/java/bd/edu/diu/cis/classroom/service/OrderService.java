package bd.edu.diu.cis.classroom.service;

import bd.edu.diu.cis.classroom.model.Orders;
import bd.edu.diu.cis.classroom.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public void save(Orders order) {
        orderRepository.save(order);
    }
}
