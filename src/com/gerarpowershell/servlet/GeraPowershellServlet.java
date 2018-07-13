package com.gerarpowershell.servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import com.gerarpowershell.enumerable.DiretorioSistemasEnum;
import com.gerarpowershell.enumerable.TipoScriptEnum;
import com.gerarpowershell.powershell.CabecalhoScriptsPS;
import com.gerarpowershell.powershell.ScriptBackupPS;
import com.gerarpowershell.powershell.ScriptInstalacaoPS;
import com.gerarpowershell.powershell.ScriptRollbackPS;
import com.gerarpowershell.utils.Diretorio;

@WebServlet("/GeraPowershellServlet")
@MultipartConfig
public class GeraPowershellServlet extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7306254444681787832L;

	private String localSalvarPS = "C:\\Temp\\"; 
	private String nomeSistema = "";
	private String numeroChamado = "";
	private String numeroTask = "";
	private String diretorioAplicacao = "";
	private String diretorioPacote = "";
	private String diretorioPacoteComGMUD = "";
	Diretorio diretorio = new Diretorio();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String filePathCtx = getServletContext().getInitParameter("file-upload");
		//CabecalhoScriptsPS cabecalho = new CabecalhoScriptsPS();
		

		//process only if its multipart content
        if(ServletFileUpload.isMultipartContent(request)){
            try {
            	Path path = Paths.get(filePathCtx);
            	if(!Files.exists(path)) {
            		Files.createDirectories(path);
            	}
            	//List<FileItem> items = uploadHandler.parseRequest(new ServletRequestContext(request));
            	
                List<FileItem> multiparts = new ServletFileUpload(
                                         new DiskFileItemFactory()).parseRequest(new ServletRequestContext(request));
              
                for(FileItem item : multiparts){
                	if(item.getFieldName().equals("nomeSistema")) {
                		nomeSistema = item.getString();
                	}
                	
                	if(item.getFieldName().equals("numeroChamado")) {
                		numeroChamado = item.getString();
                	}
                	
                	if(item.getFieldName().equals("numeroTask")) {
                		numeroTask = item.getString();
                	}
                	
                	if(item.getFieldName().equals("diretorioSistema")) {
                		diretorioAplicacao = item.getString();
                	}
                	
                	if(item.getFieldName().equals("diretorioPacote")) {
                		diretorioPacote = item.getString();
                		diretorioPacoteComGMUD = diretorioPacote + File.separator + "GMUD_" + numeroChamado + "_" + numeroTask;
                	}
     	
                	
                    if(!item.isFormField()){
                    	if(!diretorio.isBaseDiretoriosCriada()) {                    		
                    		try {
                    			diretorio.CriarBaseDiretorios(localSalvarPS, nomeSistema, numeroChamado, numeroTask);
                    		} catch (IOException e1) {
                    			// TODO Auto-generated catch block
                    			e1.printStackTrace();
                    		}
                    	}
                    	
                        String name = new File(item.getName()).getName();
                        String parentPath = new File(item.getName()).getParent();
                        
                        String sitePath = diretorio.getSiteFolderPath().toString();
                        
                        //String fulPath = filePathCtx + parentPath;
                        String fullPath = sitePath + File.separator + parentPath;
                        
                        path = Paths.get(fullPath);
                    	if(!Files.exists(path)) {
                    		Files.createDirectories(path);
                    	}
                        
                        item.write( new File(fullPath + File.separator + name));
                    }
                }
           
                // Cria os arquivos script PowerShell de Instalação, Backup e Rollback
                ScriptInstalacaoPS scriptInstalacao = new ScriptInstalacaoPS(multiparts, diretorio.getPsFolderPath().toString(), diretorioAplicacao, diretorioPacoteComGMUD);
                ScriptBackupPS scriptBackup = new ScriptBackupPS(multiparts, diretorio.getPsFolderPath().toString(), diretorioAplicacao, diretorioPacoteComGMUD);
                ScriptRollbackPS scriptRollback = new ScriptRollbackPS(multiparts, diretorio.getPsFolderPath().toString(), diretorioAplicacao, diretorioPacoteComGMUD);
                
               //File uploaded successfully
               request.setAttribute("message", "File Uploaded Successfully");
            } catch (Exception ex) {
               request.setAttribute("message", "File Upload Failed due to " + ex);
            }          
         
        }else{
            request.setAttribute("message",
                                 "Sorry this Servlet only handles file upload request");
        }
    
        try {
			request.getRequestDispatcher("/result.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
