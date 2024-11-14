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
    public List<Fee> findAllFees() {
        return feeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Fee findFeeById(Long id) {
        return feeRepository.findById(id).orElse(null);
    }

    @Transactional
    public Fee saveFee(Fee fee) {
        return feeRepository.save(fee);
    }

    @Transactional
    public Fee updateFee(Fee fee) {
        return feeRepository.save(fee);
    }

    @Transactional
    public void deleteFee(Long id) {
        feeRepository.deleteById(id);
    }

    public void updatePrice(double newBaseFee, double newExtraFee, LocalDate startDate) {
        Fee baseFee = new Fee(startDate, null, newBaseFee, Fee.FeeType.BASE);
        Fee extraFee = new Fee( startDate, null, newExtraFee,Fee.FeeType.EXTRA_PAUSE);
        feeRepository.save(baseFee);
        feeRepository.save(extraFee);
    }
}