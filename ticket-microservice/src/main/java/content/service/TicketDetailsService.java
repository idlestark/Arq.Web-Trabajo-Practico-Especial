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
    public List<TicketDetails> findAll(){
        return ticketDetailsRepository.findAll();
    }

    @Transactional(readOnly = true)
    public TicketDetails findById(Long id){
        return ticketDetailsRepository.findById(id).orElse(null);
    }

    @Transactional
    public TicketDetails save(TicketDetails ticketDetails){
        return ticketDetailsRepository.save(ticketDetails);
    }

    @Transactional
    public TicketDetails update(TicketDetails ticketDetails){
        return ticketDetailsRepository.save(ticketDetails);
    }

    @Transactional
    public void delete(Long id){
        ticketDetailsRepository.deleteById(id);
    }
}
