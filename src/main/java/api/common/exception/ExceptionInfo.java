package api.common.exception;

import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@ToString
public class ExceptionInfo {
  private HttpStatus httpStatusCode;
  private String logErrorMessage;
  private String apiErrorMessage;
  private Map<String, String> logParams;
  private List<String> fields;
  private Exception exception;
}
