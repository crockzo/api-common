package api.common.exception.util;

import api.common.exception.BaseException;
import api.common.exception.CriticalTypeException;
import api.common.exception.ExceptionInfo;
import api.common.exception.NonCriticalTypeException;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionHelper {

  public static BaseException createExceptionFromExceptionInfo(final ExceptionInfo exceptionInfo) {
    if (exceptionInfo.getHttpStatusCode().is4xxClientError()) {
      return new NonCriticalTypeException(exceptionInfo);
    } else {
      return new CriticalTypeException(exceptionInfo);
    }
  }

  public static BaseException createException(
      final HttpStatus status,
      final String errorType,
      final String logMessage,
      final String apiMessage,
      final Map<String, String> logParams,
      final List<String> fields,
      final Exception exception) {
    ExceptionInfo exceptionInfo =
        ExceptionInfo.builder()
            .httpStatusCode(status)
            .errorType(errorType)
            .logErrorMessage(logMessage)
            .apiErrorMessage(apiMessage)
            .logParams(logParams)
            .fields(fields)
            .exception(exception)
            .build();
    return createExceptionFromExceptionInfo(exceptionInfo);
  }

  public static ExceptionInfo createExceptionInfo(
      final HttpStatus status,
      final String errorType,
      final String logMessage,
      final String apiMessage,
      final Map<String, String> logParams,
      final List<String> fields,
      final Exception exception) {
    return ExceptionInfo.builder()
        .httpStatusCode(status)
        .errorType(errorType)
        .logErrorMessage(logMessage)
        .apiErrorMessage(apiMessage)
        .logParams(logParams)
        .fields(fields)
        .exception(exception)
        .build();
  }
}
