package com.secure.doc.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/test")
public class MessageTestController {
	
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('SUPERADMIN') or hasRole('ADMIN')")
	public String userAccess(Principal principal,Authentication authentication, HttpServletRequest request) {
		

	    if (request.isUserInRole("ROLE_ADMIN")) {
	       
	    	System.out.println("ROLE ADMIN");
	    }
	    
	    if (request.isUserInRole("ROLE_USER")) {
		       
	    	System.out.println("USER ADMIN");
	    }
		
		
		return "User Content."+principal.getName()+" ---"+authentication.getAuthorities();
	}

	@GetMapping("/sup")
	@PreAuthorize("hasRole('SUPERADMIN')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
		
		
		String num = userDetails.getUsername() +"  ----"+ userDetails.getAuthorities();
		
		return "Admin Board.--"+num;
	}
	

}
