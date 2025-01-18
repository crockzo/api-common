package com.courses.common.exception.util;

import com.courses.common.exception.BaseException;
import com.courses.common.exception.CriticalTypeException;
import com.courses.common.exception.ExceptionInfo;
import com.courses.common.exception.NonCriticalTypeException;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionHelper {

  public static BaseException createExceptionFromExceptionInfo(ExceptionInfo exceptionInfo) {
    if (exceptionInfo.getHttpStatusCode().is4xxClientError()) {
      return new NonCriticalTypeException(exceptionInfo);
    } else {
      return new CriticalTypeException(exceptionInfo);
    }
  }

  public static BaseException createException(
      HttpStatus status,
      String logMessage,
      String apiMessage,
      Map<String, String> logParams,
      List<String> fields,
      Exception exception) {
    ExceptionInfo exceptionInfo =
        ExceptionInfo.builder()
            .httpStatusCode(status)
            .logErrorMessage(logMessage)
            .apiErrorMessage(apiMessage)
            .logParams(logParams)
            .fields(fields)
            .exception(exception)
            .build();
    return createExceptionFromExceptionInfo(exceptionInfo);
  }
}
