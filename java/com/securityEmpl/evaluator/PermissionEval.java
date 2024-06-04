package com.securityEmpl.evaluator;

import java.io.Serializable;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.securityEmpl.dto.DeleteRequestDTO;
import com.securityEmpl.userdetails.CustomUserDetails;

public class PermissionEval implements PermissionEvaluator{
	
	private static final Logger logger = LoggerFactory.getLogger(PermissionEval.class);

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		if(authentication == null || targetDomainObject == null || permission == null) {
			return false;
		}
		else {
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

			if(permission.equals("delete")) {
				
				DeleteRequestDTO requestId = (DeleteRequestDTO) targetDomainObject;
				Integer userId = userDetails.getUser_id();
				
				Collection<? extends GrantedAuthority> userAuthority = authentication.getAuthorities();
				for(GrantedAuthority authority: userAuthority) {
					String auth = authority.getAuthority();
					if(auth.equals("ROLE_ADMIN")) {
						logger.info("Admin {} trying to access with permission {}",
								userDetails.getUsername(),
								permission.toString());
						return true;
					}
				}
				
				return userId.equals(requestId.getId());
			}
			else {
				return false;
			}
		}
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		if(authentication == null || targetId == null || targetType == null || permission == null) {
			return false;
		}
		else {
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			logger.info("User {} trying to access {}-{} with permission {}",
					userDetails.getUsername(),
					targetId,
					targetType,
					permission.toString());
			return true;
		}
	}

}
