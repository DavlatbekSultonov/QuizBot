package command;

import java.util.List;
import java.util.Optional;

public interface Repository<ENTITY extends BaseEntity<Id> , Id> {

    void creat(ENTITY entity);
    Optional<ENTITY> findById(Id id);
    void Delete(Id id);
    List<ENTITY> findAll();

}
