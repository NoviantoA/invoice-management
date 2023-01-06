package novianto.anggoro.invoice.service;

import novianto.anggoro.invoice.exception.VirtualAccountAlreadyPaidException;
import novianto.anggoro.invoice.exception.VirtualAccountNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @Test
    public void testPay() throws VirtualAccountNotFoundException, VirtualAccountAlreadyPaidException {
        paymentService.pay(null,
                null,
                null,
                null,
                null);
    }
}
