package controller;

import org.sj.teammember.entities.TeamMember;
import org.sj.teammember.exception.TeamMemberEmailIDAlreadyExistsException;
import org.sj.teammember.exception.TeamMemberFieldValidationException;
import org.sj.teammember.exception.TeamMemberNotFoundException;
import org.sj.teammember.repo.TeamMemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.stream.Collectors;

import ch.qos.logback.classic.Logger;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import utils.Util;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
@CrossOrigin(origins = "*")/* @TODO rethink */
@Slf4j

public class TeamMemberController {
@Autowired
TeamMemberRepo repo;

	
@PostMapping("/addUser")

		public ResponseEntity addUser(@Valid @RequestBody TeamMember user, BindingResult result, Model model) throws TeamMemberFieldValidationException,TeamMemberEmailIDAlreadyExistsException {
	
		// Check if bean validation is successful
		log.debug("Checking if bean validation is successful for {} ",user);
			if (result.hasErrors()) {
				log.debug("Bean validation failed for {} ",user);
		        List<ObjectError> errors = result.getAllErrors();
		        log.debug("Throwing  TeamMemberFieldValidationException for {} ",user);
		        throw new TeamMemberFieldValidationException(Util.getError(errors));
		    }

		// Check if duplicate record is getting inserted
		log.debug("Check if duplicate record is getting inserted for {} ",user);
			if (repo.findByEmailID(user.getEmailID()).isPresent()) {
				log.debug("Duplicate emailID found for {} ",user);
		        throw new TeamMemberEmailIDAlreadyExistsException(user.getEmailID());
		    }
		
		log.debug("Saving {} ",user);
		TeamMember savedUser=repo.save(user);
		log.debug("Record Saved =>{} ",user);
		return new ResponseEntity<>(savedUser,HttpStatus.OK);
		}
	
	@GetMapping("/getUser/{id}")
	public TeamMember getUser(@PathVariable (value="id") UUID id) {
		log.debug("Get user {}",id);
		return repo.findById(id).orElseThrow(()->new TeamMemberNotFoundException("Team Member ", "ID", (Object)id));
	}
	
	@GetMapping("/getAllUsers")
	@ResponseBody
	public List<TeamMember> getAllUsers() {
		log.debug("Get All Users" );
		return (List<TeamMember>) repo.findAll();
	}
	
	
	@PatchMapping("/updateUser/{id}")
	public TeamMember updateUser(@PathVariable (value="id") UUID id, @RequestBody TeamMember receivedUser) {
		log.debug("Update User with ID {} and details as {}",new Object[] {id, receivedUser});
		TeamMember savedUser=repo.findById(id).orElseThrow(()->new TeamMemberNotFoundException("Team Member ", "ID", (Object)id));
		savedUser.setEmailID(receivedUser.getEmailID());
		savedUser.setName(receivedUser.getName());
		savedUser.setVersion(receivedUser.getVersion());
		repo.save(savedUser);
		return savedUser;
	}
	
	
}


