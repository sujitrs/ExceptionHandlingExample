package org.sj.teammember.exception;

public class TeamMemberEmailIDAlreadyExistsException extends RuntimeException {

	
	public TeamMemberEmailIDAlreadyExistsException(String emailID){
		super("EmailID "+emailID+" already exists");
	}
}
