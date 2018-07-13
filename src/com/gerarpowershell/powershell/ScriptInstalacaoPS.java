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

public class ScriptInstalacaoPS extends CabecalhoScriptsPS {

	private List<String> listaConteudoPS = new ArrayList<String>();
	private StringBuilder sb = new StringBuilder();
	private String dirCompletoProjetoPS = "";
	private String parentFolder = "";
	private String fileName = "";
	private String fullPathDirPackage = "";
	private String fullPathDirApp = "";
	private String pathWithoutRootFolder = "";
	private String splitedParentFolder[] = {};
	
	public ScriptInstalacaoPS(List<FileItem> multipart, String diretorioProjetoPS, String diretorioAplicacao, String diretorioPacoteComGMUD) throws IOException {
		listaConteudoPS =  super.criaCabecalhoScriptsPS(diretorioAplicacao, diretorioPacoteComGMUD, TipoScriptEnum.INSTALACAO);
		
		dirCompletoProjetoPS = diretorioProjetoPS + File.separator + TipoScriptEnum.INSTALACAO.getNomeArquivoScript();
		
		CriaPowerShelInstalacao(multipart, diretorioAplicacao, diretorioPacoteComGMUD);
	}
	
	public void CriaPowerShelInstalacao(List<FileItem> multipart, String diretorioAplicacao, String diretorioPacoteComGMUD) throws IOException {

		for(FileItem item : multipart ) {
			if(!item.isFormField()) {
				fileName = new File(item.getName()).getName();
				parentFolder = new File(item.getName()).getParent();

				splitedParentFolder = parentFolder.split("\\\\");

				for(int i = 1; i < splitedParentFolder.length; i++) {					
					pathWithoutRootFolder += File.separator + splitedParentFolder[i]; //Excluo a posição 0, pasta raiz
				}
	
                fullPathDirPackage = parentFolder + File.separator + fileName;
                fullPathDirApp = pathWithoutRootFolder + File.separator + fileName;
                
				sb.append("if (Test-Path -PathType Leaf  \"$DirPacote\\Site\\" + fullPathDirPackage + "\") {");
				sb.append(System.getProperty("line.separator"));
				sb.append("	Copy-Item \"$DirPacote\\Site\\" + fullPathDirPackage + "\" \"$DirAplicao" + fullPathDirApp + "\" -recurse -force");
				sb.append(System.getProperty("line.separator"));
				sb.append("} else {");
				sb.append(System.getProperty("line.separator"));
				sb.append("	Write-Host \"Não foi possível instalar os arquivos da origem $DirPacote\\Site\\" + fullPathDirPackage + "\" -foregroundColor Red");
				sb.append(System.getProperty("line.separator"));
				sb.append("}");
				sb.append(System.getProperty("line.separator"));
				sb.append(System.getProperty("line.separator"));
				
				pathWithoutRootFolder = "";
			}
		}
		sb.append("Write-Host Arquivos instalados com sucesso... -foregroundColor Green");
		
		//Adiciona a logica de instalação acima, logo apos o cabeçalho
		listaConteudoPS.add(sb.toString());
		
		Path path = Paths.get(dirCompletoProjetoPS);
		
		Files.write(path, listaConteudoPS, Charset.forName("UTF-8"));
	}
}
