package novianto.anggoro.invoice.dao;

import novianto.anggoro.invoice.entity.VirtualAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VirtualAccountDao extends JpaRepository<VirtualAccount, String> {

}
