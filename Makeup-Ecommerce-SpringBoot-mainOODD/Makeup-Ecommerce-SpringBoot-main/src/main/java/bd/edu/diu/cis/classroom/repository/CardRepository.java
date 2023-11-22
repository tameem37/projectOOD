package bd.edu.diu.cis.classroom.repository;

import bd.edu.diu.cis.classroom.model.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {
    Card findCardById(long id);
    List<Card> findCardsByUserUsername(String username);
    Card findCardByMakeupIdAndUserId(long productId, long userId);
}
