package com.example.springboot.myfirstwebapp.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WelcomeController {
	
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String goToWelcomePage(ModelMap model) {
		model.put("name", getLoggedinUser());
		return "welcome";
	}
	
	private String getLoggedinUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
	
	
	// CODE BEFORE adding Spring Security
	
	/*
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	// @Autowired - this also works
	private AuthenticationService authenticationService;
	
	public LoginController(AuthenticationService authenticationService) {
		super();
		this.authenticationService = authenticationService;
	}

//	@RequestMapping("login")
//	public String goToLoginPage(@RequestParam String name, ModelMap model) {
//		// To pass query parameters from your controller to the view we need to make use of Model
//		model.put("name", name);
//		logger.debug("Logger: name value is {}" + name);
//		return "login";
//	}
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String goToLoginPage() {
		return "login";
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String goToWelcomePage(@RequestParam String name, String password, ModelMap model) {
		
		if(authenticationService.authenticate(name, password)) {
			model.put("name", name);
			model.put("password", password);
			return "welcome";
		}
		
		model.put("errorMessage", "Invalid Credentials");
		return "login";
	}
	*/
}
