package geom.exceptions;

/**
 * Class of the exceptions to be thrown to clients
 * when any of the dependency throws a retryable exception.
 */
public class RetryableDependencyException extends RetryableException {

  private static final long serialVersionUID = -1L;

  public RetryableDependencyException() {
  }

  public RetryableDependencyException(Throwable cause) {
    initCause(cause);
  }

  public RetryableDependencyException(String message) {
    super(message);
  }

  public RetryableDependencyException(String message, Throwable cause) {
    super(message);
    initCause(cause);
  }
}