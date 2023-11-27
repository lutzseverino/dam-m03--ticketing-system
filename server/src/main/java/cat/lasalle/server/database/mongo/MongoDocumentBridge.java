package cat.lasalle.server.database.mongo;

import cat.lasalle.server.database.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoDocumentBridge extends MongoRepository<Document, Integer> {
}
