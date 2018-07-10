package com.gerarpowershell.powershell;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CabecalhoScriptsPS {
	public CabecalhoScriptsPS() {

	}
	
	public CabecalhoScriptsPS(String dirAplicacao, String dirPacote, String tipoScript) throws IOException {
		this.criaCabecalhoScriptsPS(dirAplicacao, dirPacote, tipoScript);
	}
	
	public List<String> criaCabecalhoScriptsPS(String dirAplicacao, String dirPacote, String tipoScript) throws IOException {
		StringBuilder sb = new StringBuilder();
		List<String> listParamPS = new ArrayList<>();
		sb.append("Write-Host Copiando arquivos para " + tipoScript + "... -foregroundColor Blue");
		sb.append(System.getProperty("line.separator"));
		sb.append(System.getProperty("line.separator"));
		sb.append("[String]$DirAplicao = \"" + dirAplicacao + "\" #Diretório da aplicação (raiz)");
		sb.append(System.getProperty("line.separator"));
		sb.append("[String]$DirPacote = \"" + dirPacote + "\" #Diretório de origem (onde contempla o pacote)");
		sb.append(System.getProperty("line.separator"));
		listParamPS.add(sb.toString());
		
		Path path = Paths.get("c:\\TEmp\\test.ps1");
		
		Files.write(path, listParamPS, Charset.forName("UTF-8"));
		
		return listParamPS;
	}
}
