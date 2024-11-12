package content.controller;
import content.service.TicketDetailsService;
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

    @GetMapping
    public ResponseEntity<List<TicketDetails>> getAllTicketDetails() {
        List<TicketDetails> ticketDetails = ticketDetailsService.findAll();
        if(ticketDetails.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ticketDetails);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDetails> getTicketDetailsById(@PathVariable("id") Long id) {
        TicketDetails details = ticketDetailsService.findById(id);
        if(details == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(details);
    }

    @PostMapping
    public ResponseEntity<TicketDetails> createTicketDetails(@RequestBody TicketDetails scooterTicket) {
        TicketDetails details = ticketDetailsService.save(scooterTicket);
        if (details == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(details);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicketDetails(@PathVariable("id") Long id) {
        ticketDetailsService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketDetails> updateTicketDetails(@PathVariable("id") Long id, @RequestBody TicketDetails details) {
        TicketDetails existentDetails = ticketDetailsService.findById(id);
        if(details == null){
            return ResponseEntity.notFound().build();
        }

        existentDetails.setTicket(details.getTicket());
        existentDetails.setTimeUsed(details.getTimeUsed());
        existentDetails.setTimePaused(details.getTimePaused());
        existentDetails.setFeeBase(details.getFeeBase());
        existentDetails.setFeeExtra(details.getFeeExtra());
        existentDetails.setTripId(details.getTripId());

        TicketDetails detailsUpdated = ticketDetailsService.update(details);

        return ResponseEntity.ok(detailsUpdated);
    }
}
