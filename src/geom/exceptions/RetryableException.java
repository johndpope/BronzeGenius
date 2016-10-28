package geom.exceptions;

/**
 * The base abstract exception which
 * are retryable by clients. Depending on
 * the SLA requirements, the
 * clients can choose to retry on these
 * exceptions.
 */
public abstract class RetryableException extends RuntimeException {

  private String code;

  private static final long serialVersionUID = -1L;

  public RetryableException() {
  }

  public RetryableException(Throwable cause) {
    initCause(cause);
  }

  public RetryableException(String message) {
    super(message);
  }

  public RetryableException(String message, Throwable cause) {
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