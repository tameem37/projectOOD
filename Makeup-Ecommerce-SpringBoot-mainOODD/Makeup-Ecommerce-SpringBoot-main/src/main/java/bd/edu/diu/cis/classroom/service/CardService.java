package bd.edu.diu.cis.classroom.service;

import bd.edu.diu.cis.classroom.model.Card;
import bd.edu.diu.cis.classroom.model.Makeup;
import bd.edu.diu.cis.classroom.model.User;
import bd.edu.diu.cis.classroom.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    public List<Card> listAll(String username) {
        return (List<Card>) cardRepository.findCardsByUserUsername(username);
    }

    public Card get(long id) {
        return cardRepository.findCardById(id);
    }

    public void addToCard(Makeup makeup, User user) {

        Card card = null;

        card = cardRepository.findCardByMakeupIdAndUserId(makeup.getId(), user.getId());

        if (card == null) {
            card = new Card();
            card.setUser(user);
            card.setMakeup(makeup);
            card.setQuantity(1);
        } else {
            card.setQuantity(card.getQuantity()+1);
        }

        cardRepository.save(card);
    }

    public void updateCard(long cardId, int quantity) {
        Card card = getById(cardId);
        card.setQuantity(quantity);

        cardRepository.save(card);
    }

    public void deleteCard(Card card) {
        cardRepository.delete(card);
    }

    Card getById(long id) {
        return cardRepository.findCardById(id);
    }
}
