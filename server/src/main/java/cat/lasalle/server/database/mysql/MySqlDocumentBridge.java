package cat.lasalle.server.database.mysql;

import cat.lasalle.server.database.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MySqlDocumentBridge extends JpaRepository<Document, Integer> {
}
