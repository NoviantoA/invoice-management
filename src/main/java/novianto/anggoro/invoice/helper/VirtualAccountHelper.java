package novianto.anggoro.invoice.helper;

import novianto.anggoro.invoice.dao.VirtualAccountDao;
import novianto.anggoro.invoice.entity.PaymentProvider;
import novianto.anggoro.invoice.entity.VirtualAccount;
import novianto.anggoro.invoice.exception.VirtualAccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class VirtualAccountHelper {

    public static   VirtualAccount cekVaAda(VirtualAccountDao virtualAccountDao, PaymentProvider provider, String companyId, String accountNumber) throws VirtualAccountNotFoundException {
        Optional<VirtualAccount> optVa = virtualAccountDao.findByPaymentProviderAndCompanyIdAndAccountNumber(provider, companyId, accountNumber);
        if (!optVa.isPresent()){
            throw new VirtualAccountNotFoundException("VA [" + companyId + "/" + accountNumber + "-" + provider.getCode() + "] tidak ditemukan");
        }
        VirtualAccount va = optVa.get();
        return va;
    }
}
