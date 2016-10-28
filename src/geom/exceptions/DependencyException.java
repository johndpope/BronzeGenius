package geom.exceptions;

/**
 * Class of exceptions which are
 * dependent on resources external to PSR like
 * databases or other
 * services,
 * but which are non-retryable.
 */
public class DependencyException extends NonRetryableException {

  private static final long serialVersionUID = -1L;

  public DependencyException() {
  }

  public DependencyException(Throwable cause) {
    initCause(cause);
  }

  public DependencyException(String message) {
    super(message);
  }

  public DependencyException(String message, Throwable cause) {
    super(message);
    initCause(cause);
  }
}