package com.securityEmpl.config;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.securityEmpl.SessionTimeoutListner;
import com.securityEmpl.evaluator.PermissionEval;
import com.securityEmpl.service.LoginServiceImpl;

@Configuration
@EnableWebSecurity 
@EnableMethodSecurity
public class SecurityConfiguration{

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
	    return httpSecurity
	    	.cors(withDefaults())
	        .csrf(csrf -> csrf.disable()) 
	        .authorizeHttpRequests(auth -> auth
	        	.requestMatchers("/getJobs").permitAll()
	        	.requestMatchers("/getManagers").permitAll()
	        	.requestMatchers("/getDepartments").permitAll()
	            .requestMatchers("/registerUser").permitAll()
	            .requestMatchers("/api/loginUser").permitAll()
	            .requestMatchers("/api/checkSession").permitAll()	            
	            .requestMatchers("/api/colleagues").hasAuthority("ROLE_STAFF")
	            .anyRequest().authenticated()
	        )
            .sessionManagement(sess -> sess
                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            )
	        .build();
	}
	
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200")); 
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Bean
	AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder){         
    	DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();         
    	authenticationProvider.setUserDetailsService(userDetailsService);         
    	authenticationProvider.setPasswordEncoder(passwordEncoder);         
		return new ProviderManager(authenticationProvider);     
	}
    
    @Bean
    UserDetailsService userDetailsService() {
    	return new LoginServiceImpl();
    }
    
    @Bean
    SecurityContextRepository securityContextRepository() {
    	return new HttpSessionSecurityContextRepository();
    }
    
    @Bean
    SessionTimeoutListner sessionTimeoutListner() {
    	return new SessionTimeoutListner();
    }
    
    @Bean
    RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        hierarchy.setHierarchy("ROLE_ADMIN > ROLE_STAFF\n" +
                "ROLE_STAFF > ROLE_USER\n" +
                "ROLE_USER > ROLE_GUEST");
        return hierarchy;
    }
    
    @Bean
    PermissionEvaluator myPermissionEvaluator() {
    	return new PermissionEval();
    }
    
    @Bean 
    MethodSecurityExpressionHandler methodSecurityExpressionHandler() {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(myPermissionEvaluator());
        return expressionHandler;
    }
}
