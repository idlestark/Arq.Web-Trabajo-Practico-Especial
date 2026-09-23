package content.controller;

import content.entities.Fee;
import content.service.FeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fee")
public class FeeController {

    private final FeeService feeService;

    @Operation(summary = "Get all fees", description = "Gets a list of all existent fees")
    @ApiResponse(responseCode = "200", description = "Fees list obtained successfully")
    @GetMapping
    public ResponseEntity<List<Fee>> getAllFees() {
        List<Fee> fees = feeService.findAllFees();
        if (fees.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(fees);
    }

    @Operation(summary = "Get fee by ID", description = "Gets a single fee specified by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fee found successfully"),
            @ApiResponse(responseCode = "404", description = "Fee not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Fee> getFeeById(@PathVariable("id") Long id) {
        Fee fee = feeService.findFeeById(id);
        if (fee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(fee);
    }

    @Operation(summary = "Create fee", description = "Creates a new fee")
    @ApiResponse(responseCode = "201", description = "Fee created successfully")
    @PostMapping
    public ResponseEntity<Fee> createFee(@RequestBody Fee fee) {
        Fee newFee = feeService.saveFee(fee);
        if (newFee == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(201).body(newFee);
    }

    @Operation(summary = "Delete fee", description = "Deletes a fee specified by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Fee deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Fee not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFee(@PathVariable("id") Long id) {
        feeService.deleteFee(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update fee", description = "Updates an existent fee with the given information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fee updated successfully"),
            @ApiResponse(responseCode = "404", description = "Fee not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Fee> updateFee(@PathVariable("id") Long id, @RequestBody Fee fee) {
        Fee existentFee = feeService.findFeeById(id);
        if (existentFee == null) {
            return ResponseEntity.notFound().build();
        }

        existentFee.setType(fee.getType());
        existentFee.setStartDate(fee.getStartDate());
        existentFee.setEndDate(fee.getEndDate());
        existentFee.setAmount(fee.getAmount());

        Fee updatedFee = feeService.saveFee(existentFee);
        return ResponseEntity.ok(updatedFee);
    }

    @Operation(summary = "Update price", description = "Updates fee prices")
    @ApiResponse(responseCode = "200", description = "Prices updated successfully")
    @PostMapping("/update-price")
    public ResponseEntity<Void> updatePrice(
            @RequestParam double newBaseFee,
            @RequestParam double newExtraFee,
            @RequestParam LocalDate startDate) {
        feeService.updatePrice(newBaseFee, newExtraFee, startDate);
        return ResponseEntity.ok().build();
    }
}
