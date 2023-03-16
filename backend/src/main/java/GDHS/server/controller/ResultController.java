package GDHS.server.controller;

import java.io.IOException;

import GDHS.server.dto.CollectionDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ResultController {
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("ResultController.service");
	}
}
