package novianto.anggoro.invoice.service;

import novianto.anggoro.invoice.dao.VirtualAccountDao;
import novianto.anggoro.invoice.entity.PaymentProvider;
import novianto.anggoro.invoice.entity.VirtualAccount;
import novianto.anggoro.invoice.exception.VirtualAccountAlreadyPaidException;
import novianto.anggoro.invoice.exception.VirtualAccountNotFoundException;
import novianto.anggoro.invoice.helper.VirtualAccountHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private VirtualAccountDao virtualAccountDao;

    public void pay(PaymentProvider provider, String companyId, String accountNumber, BigDecimal amount, String reference) throws VirtualAccountNotFoundException, VirtualAccountAlreadyPaidException {
        // 1. cek apakah virtual account ada?
        // outsource method di VirtualAccountHelper
        VirtualAccount va = VirtualAccountHelper.cekVaAda(virtualAccountDao, provider, companyId, accountNumber);

        // 2. cek apakah virtual account sudah lunas
        cekVaLunas(provider, companyId, accountNumber, va);

        // 3. cek apakah amount pembayara > nilai tagihan

        // 4. update status virtual account menjadi lunas
        // 5. update status invoice menjadi lunas
        // 6. insert ke tabel payment
        // 7. notifikasi (next fase)

    }

    private static void cekVaLunas(PaymentProvider provider, String companyId, String accountNumber, VirtualAccount va) throws VirtualAccountAlreadyPaidException {
        if (va.getInvoice().getPaid()){
            throw new VirtualAccountAlreadyPaidException("VA [" + companyId + "/" + accountNumber + "-" + provider.getCode() + "] sudah dibayar");
        }
    }
}
