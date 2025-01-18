package api.common.exception;

/**
 * All the client related exception should extend NonCriticalTypeException.<br>
 * All 4XX HttpStatus should use NonCriticalTypeException.
 */
public class NonCriticalTypeException extends BaseException {
  public NonCriticalTypeException(ExceptionInfo exceptionInfo) {
    super(exceptionInfo, "NonCriticalType");
  }
}
