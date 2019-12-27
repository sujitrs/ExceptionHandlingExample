package org.sj.teammember;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
@CrossOrigin(origins = "*")
@Slf4j

public class TeamMemberController {
@Autowired
TeamMemberRepo repo;

	
@PostMapping("/addUser")

		public ResponseEntity addUser(@Valid @RequestBody TeamMember user, BindingResult result, Model model) {
	
	
	if (result.hasErrors()) {
        List<String> errors = result.getAllErrors().stream()
          .map(DefaultMessageSourceResolvable::getDefaultMessage)
          .collect(Collectors.toList());
        return new ResponseEntity<>(errors, HttpStatus.OK);
    }
	
	
			if (repo.findByEmailID(user.getEmailID()).isPresent()) {
		        throw new TeamMemberEmailIDAlreadyExistsException(user.getEmailID());
		    }

		log.info("Received user for saving {} ",user);
		TeamMember savedUser=repo.save(user);
		return new ResponseEntity<>(savedUser,HttpStatus.OK);
		}
	
	@GetMapping("/getUser/{id}")
	public TeamMember getUser(@PathVariable (value="id") UUID id) {
		log.info("Get user {}",id);
		return repo.findById(id).orElseThrow(()->new TeamMemberNotFoundException());
	}
	
	@GetMapping("/getAllUsers")
	@ResponseBody
	public List<TeamMember> getAllUsers() {
		log.info("Get All Users" );
		return (List<TeamMember>) repo.findAll();
	}
	
	
	@PatchMapping("/updateUser/{id}")
	public TeamMember updateUser(@PathVariable (value="id") UUID id, @RequestBody TeamMember receivedUser) {
		log.info("Update User with ID {0} and details as {1}",new Object[] {id, receivedUser});
		TeamMember savedUser=repo.findById(id).orElseThrow(()->new TeamMemberNotFoundException());
		savedUser.setEmailID(receivedUser.getEmailID());
		savedUser.setName(receivedUser.getName());
		repo.save(savedUser);
		return savedUser;
	}
	
	
}


