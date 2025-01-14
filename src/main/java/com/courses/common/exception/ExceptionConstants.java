package com.courses.common.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionConstants {
  public static String EXCEPTION_TYPE = "ExceptionType";
  public static String MESSAGE_SEPARATOR = "|";
  public static String LOG_ERROR_MESSAGE = "logErrorMessage";
  public static String LOG_PARAMS = "logParams";
  public static String KEY_VALUE_SEPARATOR = ":";
  public static String HTTP_STATUS = "HttpStatus";
}
