package content.service;
import content.client.AccountClient;
import content.client.FeeClient;
import content.client.TicketClient;
import content.client.ScooterClient;
import content.DTO.ScooterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@Service
public class AdminService {

    private final AccountClient accountClient;
    private final ScooterClient scooterClient;
    private final TicketClient ticketClient;
    private final FeeClient feeClient;


    public void cancelAccount(long id) {
       accountClient.cancelAccount(id);
    }

    public List<ScooterDTO> getScootersWithMoreTrips(int minTrips, int year) {
        return accountClient.getScootersWithMostTrips(minTrips, year);
    }

    public Map<String, Long> getScooterStatus(){
        return scooterClient.getScooterStatus();
    }

    public double getTotalInvoiced(int year, int monthStart, int monthEnd) {
        return ticketClient.getTotalInvoiced(year, monthStart, monthEnd);
    }

    public void updatePrices(double newBaseFee, double newExtraFee, LocalDate startDate) {
        feeClient.updatePrices(newBaseFee, newExtraFee, startDate);
    }

}