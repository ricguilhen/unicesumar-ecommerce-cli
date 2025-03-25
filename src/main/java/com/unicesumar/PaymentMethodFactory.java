package src.main.java.com.unicesumar;
import src.main.java.com.unicesumar.paymentMethods.BoletoPayment;
import src.main.java.com.unicesumar.paymentMethods.CreditCardPayment;
import src.main.java.com.unicesumar.paymentMethods.PaymentMethod;
import src.main.java.com.unicesumar.paymentMethods.PaymentType;
import src.main.java.com.unicesumar.paymentMethods.PixPayment;

public class PaymentMethodFactory {
    public static PaymentMethod create(PaymentType type) {
        switch (type) {
            case PIX:
                return new PixPayment();
            case CARTAO:
                return new CreditCardPayment();
            case BOLETO:
                return new BoletoPayment();
            default:
                return new PixPayment();
        }
    }
}