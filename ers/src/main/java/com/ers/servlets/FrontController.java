package com.ers.servlets;

import javax.servlet.http.HttpServlet;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.util.UserHelper;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 7987808365861146746L;

	public void init() {}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException{
			final String URI = req.getRequestURI().substring(13);
			
			System.out.println(URI);
			
			System.out.println(Arrays.toString(URI.split("/")));
			
			switch(URI.split("/")[1]) {
			case "employee":
				UserHelper.handleGet(req, res);
				System.out.println("Processed Employee GET");
				break;
			case "reimbursement":
				break;
			}
			
			System.out.println("Finished");
	}
}
