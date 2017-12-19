package com.scientific.publications.exception.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.scientific.publications.exception.BusinessException;
import com.scientific.publications.util.BundleMessage;

/**
 * Intercept exception
 *
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 17/12/2017 18:12:34
 */
@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Messaging
	 */
	@Autowired
	private BundleMessage bungleMessage;

	/**
	 * Exception handling when not found resource
	 *
	 * @param ex exception
	 * @param request reqeust
	 * @return {@link ResponseEntity}
	 */
	@ExceptionHandler({ EmptyResultDataAccessException.class })
	protected ResponseEntity<Object> handleEmptyResultDataAccessException(Exception ex, WebRequest request) {
		ResponseError responseError = new ResponseError(HttpStatus.NOT_FOUND.value(),
				String.valueOf(HttpStatus.NOT_FOUND), bungleMessage.getMessage("resource.not.found"), ex.toString());

		return handleExceptionInternal(ex, Arrays.asList(responseError), new HttpHeaders(), HttpStatus.NOT_FOUND,
				request);
	}

	/**
	 * Exception handling for business exception
	 *
	 * @param ex exception
	 * @param request reqeust
	 * @return {@link ResponseEntity}
	 */
	@ExceptionHandler({ BusinessException.class })
	protected ResponseEntity<Object> handleAlgamoneyHandlerException(BusinessException ex, WebRequest request) {
		ResponseError responseError = new ResponseError(HttpStatus.BAD_REQUEST.value(), ex.getCode(),
				bungleMessage.getMessage(ex.getCode()), ExceptionUtils.getRootCauseMessage(ex));

		return handleExceptionInternal(ex, Arrays.asList(responseError), new HttpHeaders(), HttpStatus.BAD_REQUEST,
				request);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.web.servlet.mvc.method.annotation.
	 * ResponseEntityExceptionHandler#handleMethodArgumentNotValid(org.
	 * springframework.web.bind.MethodArgumentNotValidException,
	 * org.springframework.http.HttpHeaders, org.springframework.http.HttpStatus,
	 * org.springframework.web.context.request.WebRequest)
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		return handleExceptionInternal(ex, creteRespondeError(ex.getBindingResult()), headers, HttpStatus.BAD_REQUEST,
				request);
	}

	/**
	 * Prepare errors list of field validation
	 *
	 * @param bindingResult {@link BindingResult}
	 * @return {@link List} {@link ResponseError}
	 */
	private List<ResponseError> creteRespondeError(BindingResult bindingResult) {
		List<ResponseError> errors = new ArrayList<>();

		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			errors.add(new ResponseError(HttpStatus.BAD_REQUEST.value(), String.valueOf(HttpStatus.BAD_REQUEST),
					bungleMessage.getMessage(fieldError), fieldError.toString()));
		}
		return errors;
	}
}
