package org.sj.teammember.exception;

import lombok.Getter;
import lombok.extern.java.Log;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Getter
@Log
public class TeamMemberNotFoundException extends RuntimeException {
	  public TeamMemberNotFoundException(String resourceName, String fieldName,Object fieldValue) 
	  { 
		  super(String.format("%s not found with %s : '%s'",resourceName, fieldName, fieldValue)); 
	  }
	 
}