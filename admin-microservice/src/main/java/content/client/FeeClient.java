package content.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@FeignClient(name = "fee-microservice", url = "http://localhost:8007/fee")
public interface FeeClient {
    @PostMapping("/update-prices")
    void updatePrices(@RequestParam double newTicket, @RequestParam double newExtraTicket, @RequestParam LocalDate dateStart);
}
