package content.controller;

import content.entities.Ticket;
import content.DTO.TicketsDetailsDTO;
import content.service.TicketDetailsService;
import content.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import content.entities.TicketDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ticket-details")
public class TicketDetailsController {

    private final TicketDetailsService ticketDetailsService;
    private final TicketService ticketService;

    @Operation(summary = "Get all ticket details", description = "Gets a list of all tickets details")
    @ApiResponse(responseCode = "200", description = "Ticket list obtained successfully")
    @GetMapping
    public ResponseEntity<List<TicketDetails>> getAllTicketDetails() {
        List<TicketDetails> ticketDetails = ticketDetailsService.findAllTicketDetails();
        if (ticketDetails.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ticketDetails);
    }

    @Operation(summary = "Get ticket details by ID", description = "Gets a single ticket detail specified by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket detail found successfully"),
            @ApiResponse(responseCode = "404", description = "Ticket detail not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TicketDetails> getTicketDetailsById(@PathVariable("id") Long id) {
        TicketDetails details = ticketDetailsService.findTicketDetailsById(id);
        if (details == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(details);
    }

    @Operation(summary = "Create ticket detail", description = "Creates a new ticket detail")
    @ApiResponse(responseCode = "201", description = "Ticket detail created successfully")
    @PostMapping
    public ResponseEntity<TicketDetails> createTicketDetails(@RequestBody TicketsDetailsDTO ticketDetailsDTO) {
        Ticket ticket = ticketService.findTicketById(ticketDetailsDTO.getTicketId());
        if (ticket == null) {
            return ResponseEntity.badRequest().build();
        }

        TicketDetails ticketDetails = new TicketDetails(
                ticket,
                ticketDetailsDTO.getTripId(),
                ticketDetailsDTO.getFeeBase(),
                ticketDetailsDTO.getFeeExtra(),
                ticketDetailsDTO.getTimeUse(),
                ticketDetailsDTO.getTimePaused()
        );

        ticketDetails.calculateAmount();
        TicketDetails saved = ticketDetailsService.saveTicketDetails(ticketDetails);
        return ResponseEntity.status(201).body(saved);
    }

    @Operation(summary = "Delete ticket detail", description = "Deletes an existent ticket detail")
    @ApiResponse(responseCode = "204", description = "Ticket detail deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicketDetails(@PathVariable("id") Long id) {
        ticketDetailsService.deleteTicketDetails(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update ticket detail", description = "Updates an existent ticket detail with the given information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket detail updated successfully"),
            @ApiResponse(responseCode = "404", description = "Ticket detail not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TicketDetails> updateTicketDetails(@PathVariable("id") Long id, @RequestBody TicketDetails details) {
        TicketDetails existentDetails = ticketDetailsService.findTicketDetailsById(id);
        if (existentDetails == null) {
            return ResponseEntity.notFound().build();
        }

        existentDetails.setTicket(details.getTicket());
        existentDetails.setTimeUsed(details.getTimeUsed());
        existentDetails.setTimePaused(details.getTimePaused());
        existentDetails.setFeeBase(details.getFeeBase());
        existentDetails.setFeeExtra(details.getFeeExtra());
        existentDetails.setTripId(details.getTripId());

        TicketDetails updatedDetails = ticketDetailsService.updateTicketDetails(existentDetails);
        return ResponseEntity.ok(updatedDetails);
    }
}
