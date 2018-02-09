package lk.innoviv.mas.exception;

public class UnknownIdException extends RuntimeException {

    private static final long serialVersionUID = -1042388154163860849L;
    private final String id;

    public UnknownIdException(String id, String message) {
        super(message);
        this.id = id;
    }
}
