package com.gerarpowershell.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/GeraPowershellServlet")
@MultipartConfig
public class GeraPowershellServlet extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7306254444681787832L;

	private String localSalvarPS = "C:\\Temp\\"; 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String nomeProjeto = request.getParameter("nomeProjeto");
		String numeroChamado = request.getParameter("numeroChamado");
		String numeroTask = request.getParameter("numeroTask");
		String diretorioSistema = request.getParameter("diretorioSistema");
		String diretorioPacote = request.getParameter("diretorioPacote");
		
		try {
			List<Part> listPart = request.getParts().stream().filter(part -> "uploadFiles".equals(part.getName())).collect(Collectors.toList());
			
			for(Part part : listPart) {
				String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
				String filePath = Paths.get(part.getSubmittedFileName()).getParent().toString();
				InputStream fileContent = part.getInputStream();
				//System.out.println(fileContent.);
				System.out.println(filePath);
				System.out.println(fileName);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
