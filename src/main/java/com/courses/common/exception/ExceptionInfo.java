package com.courses.common.exception;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
@NoArgsConstructor
public class ExceptionInfo {
  private HttpStatusCode httpStatusCode;
  private String logErrorMessage;
  private String apiErrorMessage;
  private Map<String, String> logParams;
  private List<String> fields;
  private Exception exception;
}
