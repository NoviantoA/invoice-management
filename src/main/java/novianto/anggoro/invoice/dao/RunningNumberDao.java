package novianto.anggoro.invoice.dao;

import jakarta.persistence.LockModeType;
import novianto.anggoro.invoice.entity.RunningNumber;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;

public interface RunningNumberDao extends CrudRepository<RunningNumber, String > {

    @Lock(LockModeType.PESSIMISTIC_READ)
    RunningNumber findByPrefix(String prefix);
}
