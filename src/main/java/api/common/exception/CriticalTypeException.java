package api.common.exception;

/**
 * All the server related exception should extend CriticalTypeException.<br>
 * This exception will be used to determine the exceptions that requires intervention.<br>
 * Using this exception we can create alerts for monitoring purpose.
 * All 5XX HttpStatus should use CriticalTypeException.
 */
public class CriticalTypeException extends BaseException {
  public CriticalTypeException(ExceptionInfo exceptionInfo) {
    super(exceptionInfo, "CriticalType");
  }
}
