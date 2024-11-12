package content.controller;
import content.entities.Fee;
import content.service.FeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/fee")
public class FeeController {

    private final FeeService feeService;

    @GetMapping
    public ResponseEntity<List<Fee>> getAllTickets() {
        List<Fee> ticket = feeService.findAll();
        if(ticket.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fee> getTicketById(@PathVariable("id") Long id) {
        Fee fee = feeService.findById(id);
        if(fee == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(fee);
    }

    @PostMapping
    public ResponseEntity<Fee> createFee(@RequestBody Fee fee){
        Fee newFee = feeService.save(fee);
        if(newFee == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newFee);
    }

    @DeleteMapping("/{id]")
    public ResponseEntity<Void> deleteFee(@PathVariable("id") Long id) {
        feeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fee> updateFee(@PathVariable("id") Long id, @RequestBody Fee fee){
        Fee existentFee = feeService.findById(id);
        if(existentFee == null){
            return ResponseEntity.notFound().build();
        }

        existentFee.setType(fee.getType());
        existentFee.setStartDate(fee.getStartDate());
        existentFee.setEndDate(fee.getEndDate());
        existentFee.setAmount(fee.getAmount());

        Fee updatedFee = feeService.save(existentFee);

        return ResponseEntity.ok(updatedFee);
    }

    @PostMapping("/update-price")
    public ResponseEntity<Void> updatePrice (@RequestParam double newBaseFee, @RequestParam double newExtraFee, @RequestParam LocalDate startDate) {
        feeService.updatePrice(newBaseFee, newExtraFee, startDate);
        return ResponseEntity.ok().build();
        }
}
