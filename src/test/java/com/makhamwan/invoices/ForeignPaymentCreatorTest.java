package com.makhamwan.invoices;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.makhamwan.banking.Payment;
import com.makhamwan.banking.PaymentCreator;
import com.makhamwan.banking.PaymentException;
import com.makhamwan.invoices.ForeignPaymentCreator;
import com.makhamwan.invoices.Invoice;

import java.math.BigDecimal;

import static org.testng.Assert.assertEquals;

public class ForeignPaymentCreatorTest {

    PaymentCreator paymentCreator;

    @BeforeMethod
    void setUp() {
        paymentCreator = new ForeignPaymentCreator();
    }

    @Test
    public void testCreatePayment() throws PaymentException {
        Invoice invoice = new Invoice("some-iban", "some-address", null, BigDecimal.TEN);
        Payment payment = paymentCreator.createPayment(invoice);
        assertEquals(payment.getReceiverAccount(), invoice.getIban());
    }

    @Test(expectedExceptions = PaymentException.class)
    public void testCreatePayment_WithoutIban() throws PaymentException {
        Invoice invoice = new Invoice(null, "some-address", "some-account", BigDecimal.TEN);
        paymentCreator.createPayment(invoice);
    }

}
