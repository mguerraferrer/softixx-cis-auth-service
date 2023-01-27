package mx.softixx.cis.cloud.auth.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.val;
import mx.softixx.cis.common.user.exception.UserNotFoundException;
import mx.softixx.cis.common.validation.exception.CustomException;
import mx.softixx.cis.common.validation.util.ProblemDetailUtils;

@Order(Ordered.LOWEST_PRECEDENCE)
@RestControllerAdvice
public class ExceptionHandlerAdvice {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ProblemDetail handlerUserNotFoundException(UserNotFoundException e) {
		val properties = CustomException.populateProperties(e);
		properties.put("id", e.getId());
		properties.put("email", e.getEmail());
		
		return ProblemDetailUtils.notFound(e.getMessage(), properties);		
	}
	
}