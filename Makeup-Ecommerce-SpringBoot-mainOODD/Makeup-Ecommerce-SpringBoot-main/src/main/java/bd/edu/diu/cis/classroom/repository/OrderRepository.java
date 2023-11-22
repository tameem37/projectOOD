package bd.edu.diu.cis.classroom.repository;

import bd.edu.diu.cis.classroom.model.Orders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Orders, Long> {
}
