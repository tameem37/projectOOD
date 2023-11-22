package bd.edu.diu.cis.classroom.service;

import bd.edu.diu.cis.classroom.model.Makeup;
import bd.edu.diu.cis.classroom.repository.MakeupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MakeupService {
    @Autowired
    private MakeupRepository makeupRepository;

    public List<Makeup> getAll() {
        return (List<Makeup>) makeupRepository.findAll();
    }

    public Makeup get(long id) {
        return makeupRepository.getMakeupById(id);
    }

    public void save(Makeup makeup) {
        makeupRepository.save(makeup);
    }

    public void delete(long id) {
        makeupRepository.delete(get(id));
    }

}
