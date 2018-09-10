package com.lottery.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.lottery.api.filter.InvalidClientException;
import com.lottery.api.filter.LockedClientException;
import com.lottery.orm.result.BaseRestResult;
import com.lottery.orm.result.RestResult;
import com.lottery.orm.util.MessageTool;

@ControllerAdvice
public class ControllerValidationHandler {
	// @Autowired
	// private MessageSource msgSource;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public RestResult processValidationError(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		FieldError error = result.getFieldError();
		return processFieldError(error);
	}

	private RestResult processFieldError(FieldError error) {
		RestResult message = new RestResult();
		if (error != null) {
			// Locale currentLocale = LocaleContextHolder.getLocale();
			// String msg = msgSource.getMessage(error.getDefaultMessage(),
			// null, currentLocale);
			message.error(error.getDefaultMessage());
		}
		return message;
	}
	
	@ExceptionHandler(value = InvalidClientException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
    public BaseRestResult exception(InvalidClientException exception) {
		BaseRestResult result = new BaseRestResult();
		result.setCode(MessageTool.InvlidClientCode);
		return result;
    }
	
	@ExceptionHandler(value = LockedClientException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
    public BaseRestResult exception(LockedClientException exception) {
		BaseRestResult result = new BaseRestResult();
		result.setCode(MessageTool.Code_5001);
		return result;
    }
}
