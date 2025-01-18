package api.common.exception.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorResponse {

  /** HttpStatus code */
  private HttpStatus code;

  /** Api response message */
  private String message;

  /** (optional) Error type */
  private ErrorType type;

  /** (optional) Additional details or action items that the client can take to resolve it. */
  private String details;

  /** (Optional) Exception related fields. */
  private List<String> fields;
}
