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
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.zeroturnaround.zip.ZipUtil;

import com.gerarpowershell.bat.ExecutorGmudBat;
import com.gerarpowershell.bat.RollbackBat;
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
	private String outputZipfile = "";
	private String nomeGMUD = "";
	Diretorio diretorio = new Diretorio();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String filePathCtx = getServletContext().getInitParameter("file-upload");

		//process only if its multipart content
        if(ServletFileUpload.isMultipartContent(request)){
            try {
            	Path path = Paths.get(filePathCtx);
            	if(!Files.exists(path)) {
            		Files.createDirectories(path);
            	}
            	
                List<FileItem> multiparts = new ServletFileUpload(
                                         new DiskFileItemFactory()).parseRequest(new ServletRequestContext(request));
              
                for(FileItem item : multiparts){
                	if(item.getFieldName().equals("nomeSistema")) {
                		nomeSistema = item.getString();
                		nomeSistema = nomeSistema.toUpperCase();
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
                		nomeGMUD = "GMUD_" + numeroChamado + "_" + numeroTask;
                		diretorioPacoteComGMUD = diretorioPacote + File.separator + nomeGMUD;
                	}   	
                	
                    if(!item.isFormField()){
                    	/* 
                    	 * Crio todas as pastas que servirão de base para colocar os arquivos
                    	 * 
                    	 * localSalvarPS = local onde ficará o projeto ex: C:\temp
                    	 * nomeSistema = Qual o nome do Sistema, definido na index ex.: MCS
                    	 * numeroChamado = Qual GMUD do sistema ex.: C0001234
                    	 * numeroTask = Qual o numero da task que esta abrindo ex.: T0001234
                    	 * 
                    	 * Exemplo da estrutura de pastas final: C:\temp\MCS\C0001234\T0001234
                    	*/ 
                    	if(!diretorio.baseDiretoriosExists(localSalvarPS, nomeSistema, numeroChamado, numeroTask)) {                    		
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
                        String fullPath = sitePath + File.separator + parentPath;
                        
                        // Crio diretorio chamado "site" se já não existir dentro da pasta "TYYYYYY" do projeto
                        path = Paths.get(fullPath);
                    	if(!Files.exists(path)) {
                    		Files.createDirectories(path);
                    	}
                        
                    	// Copio todos o arquivos e pastas que o usuario selecionou na tela inicial
                    	// para a pasta "site" criado anteriormente
                        item.write( new File(fullPath + File.separator + name));
                    }
                }
                
                // Cria os arquivos script PowerShell de Instalação, Backup e Rollback
                ScriptInstalacaoPS.CriaPowerShelInstalacao(multiparts, diretorio.getPsFolderPath().toString(), diretorioAplicacao, diretorioPacoteComGMUD);
                ScriptBackupPS.criaPowerShellBackup(multiparts, diretorio.getPsFolderPath().toString(), diretorioAplicacao, diretorioPacoteComGMUD);
                ScriptRollbackPS.criaPowerShellRollback(multiparts, diretorio.getPsFolderPath().toString(), diretorioAplicacao, diretorioPacoteComGMUD);
                
                // Cria os arquivos .bat ExecutorGMUD e Rollback
                ExecutorGmudBat.criarExcutorGMUD(nomeGMUD, diretorioPacote, diretorio.getPackagePath().toString());
                RollbackBat.criarExcutorGMUD(nomeGMUD, diretorioPacote, diretorio.getPackagePath().toString());

                // Cria o .zip do projeto
                outputZipfile = diretorio.getGmudPackagePath() + File.separator + nomeGMUD + ".zip";
                ZipUtil.pack(new File(diretorio.getPackagePath().toString()), new File(outputZipfile));
                
               //File uploaded successfully
               request.setAttribute("message", "Arquivos gerados com sucesso!");
               request.setAttribute("outputZipFile", outputZipfile);
            } catch (Exception ex) {
               request.setAttribute("message", "Não foi possível gera os arquivos por conta do erro: " + ex);
            }                   
        }else{
            request.setAttribute("message", "Desculpa, não foi possível fazer a requisição de upload!");
        }
    
        try {
        	request.getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);
			//request.getRequestDispatcher("/result.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
