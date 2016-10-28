package geom.exceptions;

public class InvalidInputException extends NonRetryableException {

  private static final long serialVersionUID = -1L;

  public InvalidInputException() {
  }

  public InvalidInputException(Throwable cause) {
    initCause(cause);
  }

  public InvalidInputException(String message) {
    super(message);
  }

  public InvalidInputException(String message, Throwable cause) {
    super(message);
    initCause(cause);
  }
}