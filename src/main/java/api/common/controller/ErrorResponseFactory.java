package api.common.controller;

import api.common.exception.BaseException;
import api.common.exception.CriticalTypeException;
import api.common.exception.ExceptionInfo;
import api.common.exception.model.ErrorResponse;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponseFactory {

  public static ErrorResponse createErrorResponse(CriticalTypeException exception) {
    log.error(exception.getErrorMessage());
    ExceptionInfo exceptionInfo = exception.getExceptionInfo();
    return ErrorResponse.builder()
        .code(
            Optional.ofNullable(exceptionInfo.getHttpStatusCode())
                .orElse(HttpStatus.INTERNAL_SERVER_ERROR))
        .message(exceptionInfo.getApiErrorMessage())
        .fields(exceptionInfo.getFields())
        .build();
  }

  public static ErrorResponse createErrorResponse(BaseException exception) {
    log.error(exception.getErrorMessage());
    ExceptionInfo exceptionInfo = exception.getExceptionInfo();
    return ErrorResponse.builder()
        .code(exceptionInfo.getHttpStatusCode())
        .message(exceptionInfo.getApiErrorMessage())
        .fields(exceptionInfo.getFields())
        .build();
  }

  public static ErrorResponse createErrorResponse(ExceptionInfo exceptionInfo) {
    return ErrorResponse.builder()
        .code(exceptionInfo.getHttpStatusCode())
        .message(exceptionInfo.getApiErrorMessage())
        .fields(exceptionInfo.getFields())
        .build();
  }
}
