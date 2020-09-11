package com.example;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;


@WebServlet(
    name = "HelloAppEngine",
    urlPatterns = {"/hello"}
)
@SuppressWarnings("serial")
public class HelloAppEngine extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {

	  UserService userService = UserServiceFactory.getUserService();
	  User user = userService.getCurrentUser();
	  
    response.setContentType("text/html");
 
    response.getWriter().println("<h2>Integrating with Google user account</h2>");
    
    if(user != null) {
    	response.getWriter().println("Welcome, " + user.getNickname());
    	response.getWriter().println("<a href='"+ userService.createLogoutURL(request.getRequestURI()) + "'>LogOut</a>");
    	
    }

    else {
    	response.getWriter().println("Please <a href='"+ userService.createLoginURL(request.getRequestURI()) + "'>LogIn</a>");
    }

  }
}