package com.gerarpowershell.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GeraPowershellServlet")
public class GeraPowershellServlet extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7306254444681787832L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		String teste = "asdasd";
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String teste = request.getParameter("nomeProjeto");
		String teste23 = request.getParameter("numeroChamado");
	}
	
}
