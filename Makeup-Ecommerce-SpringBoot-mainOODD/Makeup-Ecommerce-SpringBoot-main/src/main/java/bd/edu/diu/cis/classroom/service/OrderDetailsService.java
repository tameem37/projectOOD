package bd.edu.diu.cis.classroom.service;

import bd.edu.diu.cis.classroom.model.Card;
import bd.edu.diu.cis.classroom.model.Orders;
import bd.edu.diu.cis.classroom.model.OrderDetails;
import bd.edu.diu.cis.classroom.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsService {
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private CardService cardService;

    public void save(OrderDetails orderDetails) {
        orderDetailsRepository.save(orderDetails);
    }

    public void saveBulk(List<Card> cards, Orders order) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setOrders(order);
        for (Card card: cards) {
            orderDetails.setPrice(card.getMakeup().getPrice());
            orderDetails.setMakeup(card.getMakeup());
            orderDetails.setQuantity(card.getQuantity());

            cardService.deleteCard(card);
            save(orderDetails);
        }
    }
}
