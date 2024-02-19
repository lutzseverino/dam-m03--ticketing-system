package cat.lasalle.server.controller;

import cat.lasalle.commons.ticket.TicketDTO;
import cat.lasalle.server.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ticket")
public class TicketController {
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<TicketDTO> createTicket(@RequestBody TicketDTO ticketDTO) {
        return ResponseEntity.ok(ticketService.create(ticketDTO));
    }

    @GetMapping
    public ResponseEntity<List<TicketDTO>> readAllTickets() {
        return ResponseEntity.ok(ticketService.readAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> readTicketById(@PathVariable String id) {
        return ResponseEntity.ok(ticketService.readById(id));
    }

    @PutMapping
    public ResponseEntity<Void> updateTicket(@RequestBody TicketDTO ticketDTO) {
        ticketService.update(ticketDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable String id) {
        ticketService.delete(id);
        return ResponseEntity.ok().build();
    }
}
