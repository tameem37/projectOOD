package bd.edu.diu.cis.classroom.repository;

import bd.edu.diu.cis.classroom.model.Makeup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MakeupRepository extends CrudRepository<Makeup, Long> {
    Makeup getMakeupById(long id);
}
