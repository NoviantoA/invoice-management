package novianto.anggoro.invoice.dao;

import novianto.anggoro.invoice.entity.InvoiceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceTypeDao extends JpaRepository<InvoiceType, String> {
}
