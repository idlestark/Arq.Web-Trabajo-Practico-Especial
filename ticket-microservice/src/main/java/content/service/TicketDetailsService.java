package content.service;

import content.entities.TicketDetails;
import content.repository.TicketDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketDetailsService {
    private final TicketDetailsRepository ticketDetailsRepository;

    @Transactional(readOnly = true)
    public List<TicketDetails> findAllTicketDetails(){
        return ticketDetailsRepository.findAll();
    }

    @Transactional(readOnly = true)
    public TicketDetails findTicketDetailsById(Long id){
        return ticketDetailsRepository.findById(id).orElse(null);
    }

    @Transactional
    public TicketDetails saveTicketDetails(TicketDetails ticketDetails){
        return ticketDetailsRepository.save(ticketDetails);
    }

    @Transactional
    public TicketDetails updateTicketDetails(TicketDetails ticketDetails){
        return ticketDetailsRepository.save(ticketDetails);
    }

    @Transactional
    public void deleteTicketDetails(Long id){
        ticketDetailsRepository.deleteById(id);
    }
}
