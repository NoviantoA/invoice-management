package novianto.anggoro.invoice.dao;

import novianto.anggoro.invoice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDao extends JpaRepository<Payment, String> {
}
