package command;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class BaseRepository<ENTITY extends BaseEntity<ID> , ID> implements Repository<ENTITY, ID> {

    private List<ENTITY> entityList = new ArrayList<>();
    @Override
    public void creat(ENTITY entity) {
       entityList.add(entity);
    }

    @Override
    public Optional<ENTITY> findById(ID id) {
    return entityList.stream()
            .filter(entity -> entity.getId().equals(id))
            .findFirst();
    }

    @Override
    public void Delete(ID id) {
        entityList.removeIf(entity -> entity.getId().equals(id));
    }

    @Override
    public List<ENTITY> findAll() {
        return entityList;
    }
}
