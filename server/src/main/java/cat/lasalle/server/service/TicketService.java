package cat.lasalle.server.service;

import cat.lasalle.commons.ticket.TicketDTO;
import cat.lasalle.server.model.Ticket;
import cat.lasalle.server.model.mapper.TicketMapper;
import cat.lasalle.server.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository repository;
    private final TicketMapper mapper;

    @Autowired
    public TicketService(TicketRepository repository, TicketMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public TicketDTO create(TicketDTO dto) {
        Ticket entity = mapper.mapToEntity(dto);

        Ticket createdTicket = repository.save(entity);

        return mapper.mapToDTO(createdTicket);
    }

    public TicketDTO readById(String id) {
        Ticket entity = repository.findById(id).orElseThrow();

        return mapper.mapToDTO(entity);
    }

    public List<TicketDTO> readAll() {
        List<Ticket> entities = repository.findAll();

        return entities.stream().map(mapper::mapToDTO).toList();
    }

    public void update(TicketDTO dto) {
        Ticket entity = mapper.mapToEntity(dto);

        repository.save(entity);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
