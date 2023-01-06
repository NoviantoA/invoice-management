package novianto.anggoro.invoice.exception;

// checked exception
// supaya wajib dihandle oleh yang manggil
public class VirtualAccountNotFoundException extends Exception{

    public VirtualAccountNotFoundException() {
    }

    public VirtualAccountNotFoundException(String message) {
        super(message);
    }
}
