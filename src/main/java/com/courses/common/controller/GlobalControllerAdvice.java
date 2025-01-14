package com.courses.common.controller;

import com.courses.common.exception.BaseException;
import com.courses.common.exception.CriticalTypeException;
import com.courses.common.exception.ExceptionInfo;
import com.courses.common.exception.NonCriticalTypeException;
import com.courses.common.exception.model.ErrorResponse;
import java.util.Optional;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order
@RestControllerAdvice
public class GlobalControllerAdvice {

  @ExceptionHandler(NonCriticalTypeException.class)
  public ResponseEntity<ErrorResponse> exceptionHandler(NonCriticalTypeException exception) {
    ErrorResponse errorResponse = ErrorResponseFactory.createErrorResponse(exception);
    return new ResponseEntity<>(errorResponse, errorResponse.getCode());
  }

  @ExceptionHandler(CriticalTypeException.class)
  public ResponseEntity<ErrorResponse> exceptionHandler(CriticalTypeException exception) {
    ErrorResponse errorResponse = ErrorResponseFactory.createErrorResponse(exception);
    return new ResponseEntity<>(errorResponse, errorResponse.getCode());
  }

  @ExceptionHandler(BaseException.class)
  public ResponseEntity<ErrorResponse> exceptionHandler(BaseException exception) {
    ErrorResponse errorResponse = ErrorResponseFactory.createErrorResponse(exception);
    HttpStatus httpStatus =
        Optional.ofNullable(errorResponse.getCode()).orElse(HttpStatus.INTERNAL_SERVER_ERROR);
    return new ResponseEntity<>(errorResponse, httpStatus);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(Exception exception) {
    ExceptionInfo exceptionInfo =
        ExceptionInfo.builder()
            .exception(exception)
            .httpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR)
            .logErrorMessage(exception.getClass().getSimpleName() + " : " + exception.getMessage())
            .apiErrorMessage("Unable to process the request at this moment.")
            .build();

    ErrorResponse errorResponse =
        ErrorResponseFactory.createErrorResponse(new CriticalTypeException(exceptionInfo));
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
