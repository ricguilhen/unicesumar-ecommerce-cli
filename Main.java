public class Main {

    public static void main(String[] args) {
        PaymentType type = PaymentType.PIX;
        PaymentManager paymentManager = new PaymentManager();

        PaymentMethod method = PaymentMethodFactory.create(type);
        paymentManager.setPaymentMethod(method);

        paymentManager.pay(1000);
    }
}