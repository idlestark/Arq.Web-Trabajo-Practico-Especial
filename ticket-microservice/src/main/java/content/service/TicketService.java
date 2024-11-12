package content.service;


import content.entities.Ticket;
import content.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    @Transactional(readOnly = true)
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Ticket findById(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }

    @Transactional
    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Transactional
    public Ticket update(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Transactional
    public void delete(Long id){
        ticketRepository.deleteById(id);
    }


    public double getTotal(int year, int monthStart, int monthEnd) {
        return ticketRepository.getTotal(year, monthStart, monthEnd);
    }



}
