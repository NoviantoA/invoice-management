package novianto.anggoro.invoice.dao;

import novianto.anggoro.invoice.entity.PaymentProvider;
import novianto.anggoro.invoice.entity.VirtualAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VirtualAccountDao extends JpaRepository<VirtualAccount, String> {

    Optional<VirtualAccount> findByPaymentProviderAndCompanyIdAndAccountNumber(PaymentProvider provider, String companyId, String accountNumber);
}
