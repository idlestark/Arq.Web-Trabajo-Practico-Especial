package content.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "user-microservice", url = "http://localhost:8006/account")
public interface AccountClient {

    @PutMapping("/cancel/{id}")
    void cancelAccount(@PathVariable("id") long id);

}