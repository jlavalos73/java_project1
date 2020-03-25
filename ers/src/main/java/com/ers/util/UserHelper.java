package com.ers.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;


public class UserHelper {

	private static ObjectMapper om = new ObjectMapper();

	public static void handleGet(HttpServletRequest req, HttpServletResponse res ) throws IOException {
		final String URI = req.getRequestURI().substring(13).substring(9);
		if(URI.length() == 0) {
			res.getWriter().println(
					om.writeValueAsString(
					new UserService().findAll()));
			
		} else if(URI.charAt(0) == '/'){
			int id = Integer.parseInt(URI.substring(1));
			
			res.getWriter().println(
					om.writeValueAsString(
					new UserService().findById(id)));
		}
	}
}
