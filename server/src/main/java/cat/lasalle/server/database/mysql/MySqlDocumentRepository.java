package cat.lasalle.server.database.mysql;

import cat.lasalle.server.database.DatabaseRepository;
import cat.lasalle.server.database.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Qualifier("mysql")
public class MySqlDocumentRepository implements DatabaseRepository<Document> {
    private final MySqlDocumentBridge mySqlDocumentBridge;

    @Autowired
    public MySqlDocumentRepository(MySqlDocumentBridge mySqlDocumentBridge) {
        this.mySqlDocumentBridge = mySqlDocumentBridge;
    }

    @Override public Optional<Document> findById(int id) {
        return mySqlDocumentBridge.findById(id);
    }

    @Override public void save(Document document) {
        mySqlDocumentBridge.save(document);
    }

    @Override public void delete(Document document) {
        mySqlDocumentBridge.delete(document);
    }
}
