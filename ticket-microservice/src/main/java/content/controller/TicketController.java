package content.controller;
import content.service.TicketService;
import lombok.RequiredArgsConstructor;
import content.entities.Ticket;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ticket")
public class TicketController {
    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets(){
        List<Ticket> tickets = ticketService.findAllTickets();
        if(tickets.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable("id") Long id){
        Ticket ticket = ticketService.findTicketById(id);
        if(ticket == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(ticket);
    }

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket){
        Ticket newTicket = ticketService.saveTicket(ticket);
        if(newTicket == null){
           return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newTicket);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ticket> deleteTicket(@PathVariable("id") Long id){
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable("id") Long id, @RequestBody Ticket ticket){
        Ticket existentTicket = ticketService.findTicketById(id);

        if(existentTicket == null){
            return ResponseEntity.notFound().build();
        }

        existentTicket.setUserId(ticket.getUserId());
        existentTicket.setDateOfIssue(ticket.getDateOfIssue());

        Ticket ticketUpdated = ticketService.saveTicket(existentTicket);

        return ResponseEntity.ok(ticketUpdated);
    }

    @GetMapping("/total-collected")
    public ResponseEntity<Double> totalCollected(@RequestParam int year, @RequestParam int monthStart, @RequestParam int monthEnd){
        double total = ticketService.getTotalCollected(year, monthStart, monthEnd);
        return ResponseEntity.ok(total);
    }

}
