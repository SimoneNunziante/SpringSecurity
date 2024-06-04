package com.securityEmpl.authorization;

import java.util.Collection;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.securityEmpl.SessionTimeoutListner;

public class AuthorizationManagerImpl implements AuthorizationManager<Object>{

	private final SecurityMetadataSource securityMetadataSource;
	private static final Logger logger = LoggerFactory.getLogger(SessionTimeoutListner.class);


    public AuthorizationManagerImpl(SecurityMetadataSource securityMetadataSource) {
        this.securityMetadataSource = securityMetadataSource;
    }
    
	@Override
	public AuthorizationDecision check(Supplier<Authentication> authenticationSupplier, Object object) {
		Authentication authentication = authenticationSupplier.get();
		Collection<ConfigAttribute> attributes = securityMetadataSource.getAttributes(object);
		boolean granted = checkAccess(authentication, object, attributes);
		return new AuthorizationDecision(granted);		
	}
	
	private boolean checkAccess(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
	    Set<String> userAuthorities = authentication.getAuthorities().stream()
	        .map(GrantedAuthority::getAuthority)
	        .collect(Collectors.toSet());

	    boolean accessGranted = attributes.stream()
	        .map(ConfigAttribute::getAttribute)
	        .anyMatch(userAuthorities::contains);

	    if (!accessGranted) {
	        logger.debug("Access denied for user: {}, required authorities: {}, user authorities: {}", 
	                     authentication.getName(), attributes, userAuthorities);
	    }
	    return accessGranted;
	}
}
