package content.controller;
import content.entities.Ticket;
import content.DTO.TicketsDetailsDTO;
import content.service.TicketDetailsService;
import content.service.TicketService;
import lombok.RequiredArgsConstructor;
import content.entities.TicketDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ticket-details")
public class TicketDetailsController {
    private final TicketDetailsService ticketDetailsService;
    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<TicketDetails>> getAllTicketDetails() {
        List<TicketDetails> ticketDetails = ticketDetailsService.findAllTicketDetails();
        if(ticketDetails.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ticketDetails);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDetails> getTicketDetailsById(@PathVariable("id") Long id) {
        TicketDetails details = ticketDetailsService.findTicketDetailsById(id);
        if(details == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(details);
    }

    @PostMapping
    public ResponseEntity<TicketDetails> createTicketDetails(@RequestBody TicketsDetailsDTO ticketDetailsDTO) {
        Ticket ticket = ticketService.findTicketById(ticketDetailsDTO.getTicketId());

        TicketDetails ticketDetails = new TicketDetails(ticket, ticketDetailsDTO.getTripId(),
                ticketDetailsDTO.getFeeBase(), ticketDetailsDTO.getFeeExtra(),
                ticketDetailsDTO.getTimeUse(), ticketDetailsDTO.getTimePaused());

        ticketDetails.calculateAmount();
        ticketDetailsService.saveTicketDetails(ticketDetails);
        return ResponseEntity.ok(ticketDetails);
//        TicketDetails details = ticketDetailsService.saveTicketDetails(scooterTicket);
//        if (details == null) {
//            return ResponseEntity.badRequest().build();
//        }
//        return ResponseEntity.ok(details);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicketDetails(@PathVariable("id") Long id) {
        ticketDetailsService.deleteTicketDetails(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketDetails> updateTicketDetails(@PathVariable("id") Long id, @RequestBody TicketDetails details) {
        TicketDetails existentDetails = ticketDetailsService.findTicketDetailsById(id);
        if(details == null){
            return ResponseEntity.notFound().build();
        }

        existentDetails.setTicket(details.getTicket());
        existentDetails.setTimeUsed(details.getTimeUsed());
        existentDetails.setTimePaused(details.getTimePaused());
        existentDetails.setFeeBase(details.getFeeBase());
        existentDetails.setFeeExtra(details.getFeeExtra());
        existentDetails.setTripId(details.getTripId());

        TicketDetails updatedDetails = ticketDetailsService.updateTicketDetails(details);

        return ResponseEntity.ok(updatedDetails);
    }
}
