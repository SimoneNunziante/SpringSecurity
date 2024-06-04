package com.securityEmpl.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.securityEmpl.SessionTimeoutListner;
import com.securityEmpl.config.CookieRenewalFilter;
import com.securityEmpl.dto.LoginRequest;
import com.securityEmpl.entity.EmployeesEntity;
import com.securityEmpl.service.EmployeeService;
import com.securityEmpl.userdetails.CustomUserDetails;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/api")
public class AuthenticationController {

	private static final Logger logger = LoggerFactory.getLogger(SessionTimeoutListner.class);
    private static final SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
    private static final CookieClearingLogoutHandler cookies = new CookieClearingLogoutHandler("JSESSIONID");
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    SecurityContextRepository securityContextRepository;
    
    @Autowired
    EmployeeService employeeService;
    
    @Autowired
    CookieRenewalFilter cookieRenewalFilter;

    @PostMapping("/loginUser")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(), loginRequest.getPassword()
                )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            securityContextRepository.saveContext(SecurityContextHolder.getContext(), request, response);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String email = userDetails.getUsername();
            return ResponseEntity.ok().body(Map.of("sessionCreated", true, "email", email));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("sessionCreated", false));
        }
    }
    
    
    @GetMapping("/checkSession")
    public ResponseEntity<?> checkSession(HttpServletRequest request, HttpServletResponse response){
    	HttpSession session = request.getSession(false);
    	boolean sessionActive = (session != null) && !session.isNew();

    	if (!sessionActive) {
    	    SecurityContextHolder.clearContext();
    	    response.setHeader("Set-Cookie", "JSESSIONID=; Path=/; HttpOnly; Expires=Thu, 01 Jan 1970 00:00:00 GMT");
    	}

    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	boolean isAuthenticated = authentication != null && authentication.isAuthenticated();

    	logger.info("Checking session: Session Active = {}, Is Authenticated = {}", sessionActive, isAuthenticated);
    	return ResponseEntity.ok().body(Map.of("sessionActive", sessionActive && isAuthenticated));
    }
    
    
	@PostMapping("/logout")
	public void performLogout(Authentication authentication, HttpServletRequest request, HttpServletResponse response){     
		logoutHandler.logout(request, response, authentication);
		cookies.logout(request, response, authentication);
	}

	
	@PreAuthorize("hasAuthority('ROLE_STAFF')")
    @GetMapping("/colleagues")
    public ResponseEntity<List<EmployeesEntity>> getColleagues() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.info("User: {}, Roles: {}", authentication.getName(), authentication.getAuthorities());
    	Integer employeeId = ((CustomUserDetails) authentication.getPrincipal()).getUser_id();
    	List<EmployeesEntity> colleagues = employeeService.findEmployeesInSameDepartment(employeeId);
    	return ResponseEntity.ok().body(colleagues);
    }
}
