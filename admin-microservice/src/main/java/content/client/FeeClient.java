package content.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@FeignClient(name = "ticket-microservice", contextId = "feeClient", url = "http://localhost:8008/fee")
public interface FeeClient {

    @PostMapping("/update-price")
    void updatePrices(
            @RequestParam("newBaseFee") double newBaseFee,
            @RequestParam("newExtraFee") double newExtraFee,
            @RequestParam("startDate") LocalDate startDate
    );
}
