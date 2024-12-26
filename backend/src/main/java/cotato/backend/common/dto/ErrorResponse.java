package cotato.backend.common.dto;

import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import cotato.backend.common.exception.ErrorCode;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse extends BaseResponse {

	private final String message;
	private final Map<String, String> reasons;
	private final String code;

	private ErrorResponse(
		HttpStatus status,
		String message,
		String code,
		Map<String, String> reasons
	) {
		super(status);
		this.message = message;
		this.reasons = reasons;
		this.code = code;
	}

	public static ErrorResponse from(ErrorCode errorCode) {
		HttpStatus status = errorCode.getHttpStatus();
		String message = errorCode.getMessage();
		String code = errorCode.getCode();

		return new ErrorResponse(status, message, code, null);
	}

	public static ErrorResponse of(HttpStatus httpStatus, String message, String code) {

		return new ErrorResponse(httpStatus, message, code, null);
	}

	public static ErrorResponse of(ErrorCode errorCode, Map<String, String> reasons) {
		HttpStatus status = errorCode.getHttpStatus();
		String message = errorCode.getMessage();
		String code = errorCode.getCode();

		return new ErrorResponse(status, message, code, reasons);
	}

}
