package content.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ticket-microservice", url = "http://localhost:8004/ticket")
public interface TicketClient {
    @GetMapping("/totalInvoiced")
    Double getTotalInvoiced(
            @RequestParam int year,
            @RequestParam int monthStart,
            @RequestParam int monthEnd
    );
}
