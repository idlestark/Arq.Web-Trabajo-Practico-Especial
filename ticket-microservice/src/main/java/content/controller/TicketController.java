package content.controller;
import content.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get all tickets", description = "Gets a list of all tickets")
    @ApiResponse(responseCode = "200", description = "Ticket list obtained successfully")
    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets(){
        List<Ticket> tickets = ticketService.findAllTickets();
        if(tickets.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tickets);
    }

    @Operation(summary = "Get ticket by ID", description = "Gets a single ticket specified by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket found successfully"),
            @ApiResponse(responseCode = "404", description = "Ticket not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable("id") Long id){
        Ticket ticket = ticketService.findTicketById(id);
        if(ticket == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(ticket);
    }

    @Operation(summary = "Create ticket", description = "Creates a new ticket")
    @ApiResponse(responseCode = "201", description = "Ticket created successfully")
    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket){
        Ticket newTicket = ticketService.saveTicket(ticket);
        if(newTicket == null){
           return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newTicket);
    }

    @Operation(summary = "Delete ticket", description = "Deletes an existent ticket")
    @ApiResponse(responseCode = "200", description = "ticket deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<Ticket> deleteTicket(@PathVariable("id") Long id){
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "Update ticket", description = "Updates an existent ticket with the given information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ticket updated successfully"),
            @ApiResponse(responseCode = "404", description = "Ticket not found")
    })
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

    @Operation(summary = "Total collected", description = "Gets the total collected between two specified months in a year")
    @ApiResponse(responseCode = "200", description = "Total collected obtained successfully")
    @GetMapping("/total-collected")
    public ResponseEntity<Double> totalCollected(@RequestParam int year, @RequestParam int monthStart, @RequestParam int monthEnd){
        double total = ticketService.getTotalCollected(year, monthStart, monthEnd);
        return ResponseEntity.ok(total);
    }

}
