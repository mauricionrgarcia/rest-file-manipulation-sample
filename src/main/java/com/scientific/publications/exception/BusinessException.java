package com.scientific.publications.exception;

/**
 * API business exception
 *
 * @author <a href="mailto:mauricionrgarcia@gmail.com">Mauricio Garcia</a>
 * @version
 * @sinse 17/12/2017 18:48:53
 */
public class BusinessException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -921775727443834374L;

	/**
	 * error code
	 */
	private String code;

	/**
	 * error mesage
	 */
	private String message;

	/**
	 * description of the error message
	 */
	private String erroMessage;

	/**
	 * Constructor
	 */
	public BusinessException() {
		super();
	}

	/**
	 * Constructor
	 *
	 * @param code code
	 */
	public BusinessException(String code) {
		this.code = code;
	}

	/**
	 * Constructor
	 *
	 * @param code code
	 * @param erroMessage message
	 * @param cause description of the error message
	 */
	public BusinessException(String code, String message, String erroMessage) {
		super(code);
		this.code = code;
		this.message = message;
		this.erroMessage = erroMessage;
	}

	/**
	 * Constructor
	 *
	 * @param code code
	 * @param erroMessage mensagem
	 * @param cause description of the error message
	 * @param e Throwable
	 */
	public BusinessException(String code, String message, String erroMessage, Throwable e) {
		super(code, e);
		this.code = code;
		this.message = message;
		this.erroMessage = erroMessage;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	@Override
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the erroMessage
	 */
	public String getErroMessage() {
		return erroMessage;
	}

	/**
	 * @param erroMessage the erroMessage to set
	 */
	public void setErroMessage(String erroMessage) {
		this.erroMessage = erroMessage;
	}

}
