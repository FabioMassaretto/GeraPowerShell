package com.gerarpowershell.powershell;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileItem;

import com.gerarpowershell.enumerable.TipoScriptEnum;

public class ScriptBackupPS extends CabecalhoScriptsPS{
	private static List<String> listaConteudoPS = new ArrayList<>();
	private static List<String> listaPastas = new ArrayList<>();
	private static StringBuilder sb = new StringBuilder();
	private static String dirCompletoProjetoPS = "";
	private static String parentFolder = "";
	private static String fileName = "";
	private static String splitedParentFolder[] = {};
	private static String pathWithoutRootFolder = "";
	private static String fullPathDirPackage = "";
	private static String fullPathDirApp = "";
	private static boolean pastaExiste = false;
	
	public ScriptBackupPS(List<FileItem> multipart, String diretorioProjetoPS, String dirAplicacao, String dirPacote) throws IOException {
		listaConteudoPS = super.criaCabecalhoScriptsPS(dirAplicacao, dirPacote, TipoScriptEnum.BACKUP);
		criaPowerShellBackup(multipart, diretorioProjetoPS, dirAplicacao, dirPacote);
	}		
	
	public static void criaPowerShellBackup(List<FileItem> multipart, String diretorioProjetoPS, String diretorioAplicacao, String diretorioPacoteComGMUD) throws IOException {
		dirCompletoProjetoPS = diretorioProjetoPS + File.separator + TipoScriptEnum.BACKUP.getNomeArquivoScript();
		
		// FOR para a criacao da estrutura de pastas dentro do PowerShell
		for(FileItem item : multipart) {
			if(!item.isFormField()) {
				parentFolder = new File(item.getName()).getParent();
				pastaExiste = listaPastas.contains(parentFolder);
				if(!pastaExiste) {
					listaPastas.add(parentFolder);
					sb.append("if (!(Test-Path -PathType Container \"$DirPacote\\Backup\\" + parentFolder + "\\\")) {");
					sb.append(System.getProperty("line.separator"));
					sb.append("	Write-Host \"Criando diretório $DirPacote\\Backup\\" + parentFolder + "\\\" -foregroundColor Blue");
					sb.append(System.getProperty("line.separator"));
					sb.append("	New-Item \"$DirPacote\\Backup\\" + parentFolder + "\\\" -type directory");
					sb.append(System.getProperty("line.separator"));
					sb.append("}");
					sb.append(System.getProperty("line.separator"));
					sb.append(System.getProperty("line.separator"));
				}
			}
		}
		
		// FOR que cria toda a estrutura de backup
		for(FileItem item : multipart) {
			if(!item.isFormField()) {
				fileName = new File(item.getName()).getName();
				parentFolder = new File(item.getName()).getParent();
				
				splitedParentFolder = parentFolder.split("\\\\");

				// Para o caminho do diretorio da aplicação, não precisa do 
				// diretorio raiz onde fica todos os arquivos e pastas da aplicação
				for(int i = 1; i < splitedParentFolder.length; i++) {					
					pathWithoutRootFolder += File.separator + splitedParentFolder[i]; //Começo com i=1 portanto excluo a posição 0, pasta raiz
				}
	
                fullPathDirPackage = parentFolder + File.separator + fileName;
                fullPathDirApp = pathWithoutRootFolder + File.separator + fileName;
                
                sb.append("if (Test-Path -PathType Leaf  \"$DirAplicao" + fullPathDirApp + "\") {");
				sb.append(System.getProperty("line.separator"));
				sb.append("	Copy-Item \"$DirAplicao" + fullPathDirApp + "\" \"$DirPacote\\Backup\\" + fullPathDirPackage + "\" -recurse -force");
				sb.append(System.getProperty("line.separator"));
				sb.append("} else {");
				sb.append(System.getProperty("line.separator"));
				sb.append("	Write-Host \"Não foi possível fazer o backup dos arquivos da origem: " + fullPathDirApp + "\" -foregroundColor Yellow");
				sb.append(System.getProperty("line.separator"));
				sb.append("}");
				sb.append(System.getProperty("line.separator"));
				sb.append(System.getProperty("line.separator"));
				
				pathWithoutRootFolder = "";
			}
		}
		sb.append("Write-Host Backup de arquivos efetuado com sucesso. -foregroundColor Green");
		
		//Adiciona a logica de instalação acima, logo apos o cabeçalho
		listaConteudoPS.add(sb.toString());
		
		Path path = Paths.get(dirCompletoProjetoPS);
		
		Files.write(path, listaConteudoPS, Charset.forName("UTF-8"));
	}
}
