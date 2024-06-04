package com.securityEmpl.config;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class CookieRenewalFilter {

	public void cookieRenewal(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		if (session != null) {
            Cookie cookie = new Cookie("JSESSIONID", session.getId());
            cookie.setPath(request.getContextPath() + "/");
            cookie.setHttpOnly(true);
            cookie.setSecure(request.isSecure());
            cookie.setMaxAge(300);
            response.addCookie(cookie);
        }
	}
}
