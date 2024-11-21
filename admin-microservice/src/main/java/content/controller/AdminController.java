package content.controller;
import content.DTO.ScooterDTO;
import content.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Cancel account", description = "Temporally disables an account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Account canceled successfully"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @PutMapping("/cancel/{id}")
    public ResponseEntity<Void> cancelAccount(@PathVariable("id") long id) {
        adminService.cancelAccount(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get scooters with most trips", description = "Gets a list of the scooters with most trips")
    @ApiResponse(responseCode = "200", description = "Scooters list obtained successfully")
    @GetMapping("/scooter-trip/{minTrips}/{year}")
    public ResponseEntity<List<ScooterDTO>> getScootersWithMoreTrips(
            @RequestParam int minTrips, @RequestParam int year) {
        List<ScooterDTO> result = adminService.getScootersWithMoreTrips(minTrips, year);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Get scooters status", description = "Gets a list of all operative and under maintenance scooters")
    @ApiResponse(responseCode = "200", description = "Scooters lists obtained successfully")
    @GetMapping("/scooters-status")
    public ResponseEntity<Map<String, Long>> getScootersStatus() {
        Map<String, Long>  result = adminService.getScooterStatus();
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Get total collected", description = "Gets the total collected between two specified months in a year")
    @ApiResponse(responseCode = "200", description = "Total collected obtained successfully")
    @GetMapping("/ticket/total-collected")
    public ResponseEntity<Double> getTotalCollected(@RequestParam int year, @RequestParam int monthStart, @RequestParam int monthEnd) {
        double total = adminService.getTotalCollected(year, monthStart, monthEnd);
        return ResponseEntity.ok(total);
    }

    @Operation(summary = "Update price", description = "Updates fee's prices")
    @ApiResponse(responseCode = "204", description = "Prices updated successfully")
    @PostMapping("/ticket/update-prices")
    public ResponseEntity<Void> updatePrices(
            @RequestParam double newBaseFee,
            @RequestParam double newExtraFee,
            @RequestParam LocalDate startDate) {
        adminService.updatePrices(newBaseFee, newExtraFee, startDate);
        return ResponseEntity.ok().build();
    }
}
