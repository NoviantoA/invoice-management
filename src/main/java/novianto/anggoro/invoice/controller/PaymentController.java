package novianto.anggoro.invoice.controller;

import novianto.anggoro.invoice.exception.VirtualAccountAlreadyPaidException;
import novianto.anggoro.invoice.exception.VirtualAccountNotFoundException;
import novianto.anggoro.invoice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void pay() throws VirtualAccountAlreadyPaidException, VirtualAccountNotFoundException {
        paymentService.pay(null, null, null, null, null);
    }
}
