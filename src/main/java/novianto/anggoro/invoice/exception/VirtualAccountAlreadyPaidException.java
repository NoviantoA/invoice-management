package novianto.anggoro.invoice.exception;

// RuntimeException dipakai untuk error yang tidak bisa dihandle oleh yang manggil (controller API, controller web, ISO8583 handler)
// contoh : query select one, tapi balikannya > 1
// public class VirtualAccountAlreadyPaidException extends RuntimeException{
public class VirtualAccountAlreadyPaidException extends Exception{

    public VirtualAccountAlreadyPaidException() {
    }

    public VirtualAccountAlreadyPaidException(String message) {
        super(message);
    }
}
