package com.courses.common.exception;

import static com.courses.common.exception.ExceptionConstants.API_ERROR_MESSAGE;
import static com.courses.common.exception.ExceptionConstants.HTTP_STATUS;
import static com.courses.common.exception.ExceptionConstants.KEY_VALUE_SEPARATOR;
import static com.courses.common.exception.ExceptionConstants.LOG_ERROR_MESSAGE;
import static com.courses.common.exception.ExceptionConstants.LOG_PARAMS;
import static com.courses.common.exception.ExceptionConstants.MESSAGE_SEPARATOR;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException extends RuntimeException {
  private String errorMessage;
  private ExceptionInfo exceptionInfo;
  private String type;

  public BaseException(ExceptionInfo exceptionInfo, String type) {
    this.exceptionInfo = exceptionInfo;
    this.type = type + "$" + this.getClass().getSimpleName();
    this.errorMessage = buildErrorMessage();
  }

  /**
   * This creates the error message that will be printed as a log.<br>
   * It uses the {@link ExceptionInfo} object to create the error message.<br>
   * The error message contains information about : <br>
   * - ExceptionType : CriticalType or NonCriticalType.<br>
   * - LogErrorMessage : server message with specific information related to exception for
   * debugging.<br>
   * - HttpStatus : HttpStatus of the exception.<br>
   * - LogParams : request parameters & fields for which the exception occurred.<br>
   *
   * @return errorMessage to be logged.
   */
  public String buildErrorMessage() {
    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder
        .append(ExceptionConstants.EXCEPTION_TYPE)
        .append(KEY_VALUE_SEPARATOR)
        .append(this.type)
        .append(MESSAGE_SEPARATOR);

    stringBuilder
        .append(HTTP_STATUS)
        .append(KEY_VALUE_SEPARATOR)
        .append(this.exceptionInfo.getHttpStatusCode().value())
        .append(MESSAGE_SEPARATOR);

    stringBuilder
        .append(LOG_ERROR_MESSAGE)
        .append(KEY_VALUE_SEPARATOR)
        .append(this.exceptionInfo.getLogErrorMessage())
        .append(MESSAGE_SEPARATOR);

    stringBuilder
        .append(API_ERROR_MESSAGE)
        .append(KEY_VALUE_SEPARATOR)
        .append(this.exceptionInfo.getApiErrorMessage())
        .append(MESSAGE_SEPARATOR);

    Map<String, String> logParamMap =
        Optional.ofNullable(this.exceptionInfo.getLogParams()).orElse(new HashMap<>());
    stringBuilder
        .append(LOG_PARAMS)
        .append(KEY_VALUE_SEPARATOR)
        .append(
            logParamMap.entrySet().stream()
                .map(entry -> entry.getKey() + KEY_VALUE_SEPARATOR + entry.getValue())
                .collect(Collectors.joining(",")));

    return stringBuilder.toString();
  }
}
