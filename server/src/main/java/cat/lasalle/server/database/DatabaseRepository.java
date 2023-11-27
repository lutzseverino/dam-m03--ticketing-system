package cat.lasalle.server.database;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DatabaseRepository<T extends Document> {
    Optional<T> findById(int id);

    void save(T document);

    void delete(T document);
}
