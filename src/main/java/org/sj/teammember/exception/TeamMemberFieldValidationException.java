package org.sj.teammember.exception;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.validation.ObjectError;

public class TeamMemberFieldValidationException extends RuntimeException {

	
	
	  public TeamMemberFieldValidationException(String errors) {
		  super(errors);
		  
		  
		  }
	 
}
