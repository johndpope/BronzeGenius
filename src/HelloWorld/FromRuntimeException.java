package HelloWorld;

/**
 * @author xuch.
 */
public class FromRuntimeException extends RuntimeException {
    private static final long serialVersionUID = -1L;

    public FromRuntimeException() {
    }

    public FromRuntimeException(Throwable cause) {
        initCause(cause);
    }

    public FromRuntimeException(String message) {
        super(message);
    }

    public FromRuntimeException(String message, Throwable cause) {
        super(message);
        initCause(cause);
    }

    /**
     * Returns a hint as to whether it makes sense to retry upon this exception.
     * Default is false, but subclass may override.
     */
    public boolean isRetryable() {
        return false;
    }
}
