package com.example.springboot.myfirstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
public class SayHelloController {
	
	@RequestMapping("say-hello")
	@ResponseBody    // treats the return value as response body and returns as is.
	public String SayHello() {
		return "Hello! What are you learning today";
	}
	
	@RequestMapping("say-hello-html")
	@ResponseBody
	public String SayHelloHtml() {
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title>");
		sb.append("My first HTML Page");
		sb.append("</title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("My first html page with body");
		sb.append("</body>");
		sb.append("</html>");
		
		return sb.toString();
	}
	
	// JSP - Java Server Pages
	// Redirect to sayHello.jsp
	// /src/main/resources/META-INF/resources/WEB-INF/jsp/sayHello.jsp
	@RequestMapping("say-hello-jsp")
	public String SayHelloJsp() {
		return "sayHello";
	}

}
