package cat.lasalle.server.database.mongo;

import cat.lasalle.server.database.DatabaseRepository;
import cat.lasalle.server.database.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Qualifier("mongo")
@Primary
public class MongoDocumentRepository implements DatabaseRepository<Document> {
    private final MongoDocumentBridge mongoDocumentBridge;

    @Autowired
    public MongoDocumentRepository(MongoDocumentBridge mongoDocumentBridge) {
        this.mongoDocumentBridge = mongoDocumentBridge;
    }

    @Override public Optional<Document> findById(int id) {
        return mongoDocumentBridge.findById(id);
    }

    @Override public void save(Document document) {
        mongoDocumentBridge.save(document);
    }

    @Override public void delete(Document document) {
        mongoDocumentBridge.delete(document);
    }
}
