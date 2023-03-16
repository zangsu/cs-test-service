package GDHS.server.controller;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserController {
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("UserController.service");
	}
}
