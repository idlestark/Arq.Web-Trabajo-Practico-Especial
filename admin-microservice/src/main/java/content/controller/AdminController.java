package content.controller;
import content.DTO.ScooterDTO;
import content.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")

public class AdminController {

    private final AdminService adminService;

    @PutMapping("/cancel-account/{id}")
    public ResponseEntity<Void> cancelAccount(@PathVariable("id") long id) {
        adminService.cancelAccount(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/scooter-trip/{minTrips}/{year}")
    public ResponseEntity<List<ScooterDTO>> getScootersWithMoreTrips(
            @RequestParam int minTrips, @RequestParam int year) {
        List<ScooterDTO> result = adminService.getScootersWithMoreTrips(minTrips, year);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/scooters/compare")
    public ResponseEntity<Map<String, Long>> scooterVS(){
        Map<String, Long>  result = adminService.getScooterStatus();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/ticket/total-collected")
    public ResponseEntity<Double> getTotalCollected(
            @RequestParam int year, @RequestParam int monthStart, @RequestParam int monthEnd) {
        double total = adminService.getTotalInvoiced(year, monthStart, monthEnd);
        return ResponseEntity.ok(total);
    }

    @PostMapping("/ticket/update-prices")
    public ResponseEntity<Void> updatePrices(
            @RequestParam double newBaseFee,
            @RequestParam double newExtraFee,
            @RequestParam LocalDate startDate) {
        adminService.updatePrices(newBaseFee, newExtraFee, startDate);
        return ResponseEntity.ok().build();
    }
}
