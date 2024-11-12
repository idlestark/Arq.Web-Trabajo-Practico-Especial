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
@RequestMapping("/api/ticket")
public class TicketController {
    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTicket(){
        List<Ticket> tickets = ticketService.findAll();
        if(tickets.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable("id") Long id){
        Ticket ticket = ticketService.findById(id);
        if(ticket == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(ticket);
    }

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket){
        Ticket newTicket = ticketService.save(ticket);
        if(newTicket == null){
           return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newTicket);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ticket> deleteTicket(@PathVariable("id") Long id){
        ticketService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable("id") Long id, @RequestBody Ticket ticket){
        Ticket ticketExist = ticketService.findById(id);

        if(ticketExist == null){
            return ResponseEntity.notFound().build();
        }

        ticketExist.setUserId(ticket.getUserId());
        ticketExist.setDateOfIssue(ticket.getDateOfIssue());

        Ticket ticketUpdated = ticketService.save(ticketExist);

        return ResponseEntity.ok(ticketUpdated);
    }

    /*????????????????????????*/

    @GetMapping("/total-invoiced")
    public ResponseEntity<Double> TotalInvoiced(@RequestParam int year, @RequestParam int monthStart, @RequestParam int monthEnd){
        double total = ticketService.getTotal(year, monthStart, monthEnd);
        return ResponseEntity.ok(total);
    }

}
