package org.sj.teammember.exception;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.net.ConnectException;
import java.sql.SQLException;

import org.springframework.dao.DataIntegrityViolationException;




@ControllerAdvice
@Slf4j
public class TeamMemberExceptionHandler {
	   
	  
	  /*
		 * Option 1 to handle each error separately if you wish to communicate the error message separately in response entity
		 * + Can communicate specific messages and provide details of what went wrong
		 * 
		 */
		
		
	/*
	 * private ResponseEntity<String> error(HttpStatus status, Exception e) {
	 * log.error("Exception : ", e); return ResponseEntity.status(status).
	 * body("Server is busy, please try after some time");
	 * 
	 * }
	 * 
	 * 
	 * @ExceptionHandler({RuntimeException.class})
	 * 
	 * public ResponseEntity<String> handleRunTimeException(RuntimeException e) {
	 * 
	 * return error(INTERNAL_SERVER_ERROR, e);
	 * 
	 * }
	 * 
	 * @ExceptionHandler({HttpMessageNotReadableException.class})
	 * 
	 * public ResponseEntity<String>
	 * handleRunTimeException(HttpMessageNotReadableException e) {
	 * 
	 * return error(HttpStatus.BAD_REQUEST, e);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * @ExceptionHandler({TeamMemberNotFoundException.class})
	 * 
	 * public ResponseEntity<String>
	 * handleNotFoundException(TeamMemberNotFoundException e) {
	 * 
	 * return error(NOT_FOUND, e);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * @ExceptionHandler({DataIntegrityViolationException.class})
	 * 
	 * public ResponseEntity<String>
	 * handleNotFoundException(DataIntegrityViolationException e) { return
	 * error(HttpStatus.CONFLICT, e);
	 * 
	 * }
	 * 
	 * 
	 * @ResponseBody
	 * 
	 * @ExceptionHandler(TeamMemberEmailIDAlreadyExistsException.class)
	 * 
	 * @ResponseStatus(HttpStatus.CONFLICT)
	 * 
	 * VndErrors teamMemberAlreadyExistsExceptionHandler(
	 * TeamMemberEmailIDAlreadyExistsException ex) {
	 * 
	 * return new VndErrors("error", ex.getMessage());
	 * 
	 * }
	 */
		 
	 /*
	 * Option 2 to handle more than one exception; it should be used if you DO NOT wish to communicate the error message separately in response entity; it uses only HTTP response status
	 * + Can club multiple exception
	 * + well suited when you do not want to expose internal traces/messages to outside world, specially when your APIs are exposed directly on Internet/External Party
	 * - You cannot have any text message as response
	 * 
	 */
	
	
	 
	 // Stack Trace Printing not required
	  
	  @ResponseStatus(HttpStatus.CONFLICT)
	  @ExceptionHandler({org.springframework.orm.ObjectOptimisticLockingFailureException.class}) 
	  public void  handle(org.springframework.orm.ObjectOptimisticLockingFailureException ex) {
		  log.debug("CONFLICT Business Exception"); // Not an error. Since its a 	  business exception. It is not required to logged at error level. This log 	  prints Stack Trace 
	  }
	  
	  @ResponseStatus(HttpStatus.NOT_FOUND)
	  
	  @ExceptionHandler({TeamMemberNotFoundException.class}) public void
	  handle(TeamMemberNotFoundException e) {// No need to print stack trace
		  log.debug("NOT_FOUND TeamMemberNotFoundException"); // Not an error.	  Since its a business exception. It is not required to logged at error	  level.This log prints Stack Trace 
	  }
	  
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  @ExceptionHandler({TeamMemberFieldValidationException.class}) public void
	  handle(TeamMemberFieldValidationException e) {
		  log.debug("Field Level Validation Failed"); } // No need to print stack trace
	 
	  
	  @ResponseStatus(HttpStatus.CONFLICT)
	  @ExceptionHandler({TeamMemberEmailIDAlreadyExistsException.class}) public void
	  handle(TeamMemberEmailIDAlreadyExistsException e) {
		  log.debug("DUPLICATE_FOUND Team Member Email ID already exists"); } // No need to print stack trace


	 // Stack Trace Printing required
	  
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  @ExceptionHandler({HttpMessageNotReadableException.class}) public void
	  handle(HttpMessageNotReadableException e) {
		  log.error("BAD_REQUEST :: Message not readable Exception =",e); } //Prints stack trace
	  
	  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	  @ExceptionHandler({CannotCreateTransactionException.class,SQLException.class ,RuntimeException.class ,ConnectException.class })
	  public void handle(RuntimeException ex) {
	  log.error("INTERNAL_SERVER_ERROR Exception =",ex);//Prints Stack Trace 
	  }
	 
	  	  
	  
}
