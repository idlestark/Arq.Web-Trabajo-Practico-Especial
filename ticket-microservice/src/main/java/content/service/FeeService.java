package content.service;
import content.entities.Fee;
import content.repository.FeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeeService {

    private final FeeRepository feeRepository;

    @Transactional(readOnly = true)
    public List<Fee> findAll() {
        return feeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Fee findById(Long id) {
        return feeRepository.findById(id).orElse(null);
    }

    @Transactional
    public Fee save(Fee fee) {
        return feeRepository.save(fee);
    }

    @Transactional
    public Fee update(Fee fee) {
        return feeRepository.save(fee);
    }

    @Transactional
    public void delete(Long id) {
        feeRepository.deleteById(id);
    }

    public void updatePrice(double newBaseFee, double newExtraFee, LocalDate startDate) {
        Fee baseFee = new Fee(Fee.FeeType.BASE, newBaseFee, startDate, null);
        Fee extraFee = new Fee(Fee.FeeType.EXTRA_PAUSE, newExtraFee, startDate, null);
        feeRepository.save(baseFee);
        feeRepository.save(extraFee);
    }
}