package cat.lasalle.server.controller;

import cat.lasalle.commons.ticket.TicketDTO;
import cat.lasalle.server.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
        TicketDTO createdTicket = ticketService.create(ticketDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdTicket.getId())
                .toUri();

        return ResponseEntity.created(location).body(createdTicket);
    }

    @GetMapping
    public ResponseEntity<List<TicketDTO>> readAllTickets(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ticketService.readAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> readTicketById(@PathVariable String id) {
        TicketDTO ticket = ticketService.readById(id);

        return ticket == null ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(ticket);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketDTO> updateTicket(@RequestBody TicketDTO ticketDTO) {
        TicketDTO updatedTicket = ticketService.update(ticketDTO);

        return updatedTicket == null ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(updatedTicket);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable String id) {
        ticketService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
