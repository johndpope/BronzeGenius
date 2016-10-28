package geom.exceptions;

/**
 * The base abstract exception which
 * are non-retryable for clients. The
 * clients are not supposed to retry on
 * these exceptions as those
 * requests will always result in Non-Retryable
 * Exceptions
 */
public abstract class NonRetryableException extends RuntimeException {

  private String code;

  private static final long serialVersionUID = -1L;

  public NonRetryableException() {
  }

  public NonRetryableException(Throwable cause) {
    initCause(cause);
  }

  public NonRetryableException(String message) {
    super(message);
  }

  public NonRetryableException(String message, Throwable cause) {
    super(message);
    initCause(cause);
  }
  public String getCode() {
    return this.code;
  }

  public void setCode(String code) {
    this.code = code;
  }


  @Override
  public String getMessage() { 
    return super.getMessage();
  }
}