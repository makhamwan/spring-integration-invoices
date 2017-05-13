package com.makhamwan.invoices;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.makhamwan.banking.Payment;
import com.makhamwan.banking.PaymentCreator;
import com.makhamwan.banking.PaymentException;
import com.makhamwan.invoices.Invoice;
import com.makhamwan.invoices.LocalPaymentCreator;

import java.math.BigDecimal;

import static org.testng.Assert.assertEquals;

public class LocalPaymentCreatorTest {

    PaymentCreator paymentCreator;

    @BeforeMethod
    void setUp() {
        paymentCreator = new LocalPaymentCreator();
    }

    @Test
    public void testCreatePayment() throws PaymentException {
        Invoice invoice = new Invoice(null, "some-address", "some-account", BigDecimal.TEN);
        Payment payment = paymentCreator.createPayment(invoice);
        assertEquals(payment.getReceiverAccount(), invoice.getAccount());
    }

    @Test(expectedExceptions = PaymentException.class)
    public void testCreatePayment_WithoutAccount() throws PaymentException {
        Invoice invoice = new Invoice("some-iban", "some-address", null, BigDecimal.TEN);
        paymentCreator.createPayment(invoice);
    }

}
