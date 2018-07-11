package com.gerarpowershell.powershell;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.gerarpowershell.enumerable.TipoScriptEnum;
import com.sun.javafx.scene.control.skin.TooltipSkin;

public class CabecalhoScriptsPS {
	public CabecalhoScriptsPS() {

	}
	
	public CabecalhoScriptsPS(String dirProjetoPS, String dirAplicacao, String dirPacote, TipoScriptEnum tipoScript) throws IOException {
		this.criaCabecalhoScriptsPS(dirProjetoPS, dirAplicacao, dirPacote, tipoScript);
	}
	
	public List<String> criaCabecalhoScriptsPS(String dirProjetoPS, String dirAplicacao, String dirPacote, TipoScriptEnum tipoScript) throws IOException {
		StringBuilder sb = new StringBuilder();
		List<String> listParamPS = new ArrayList<>();
		String dirCompletoProjetoPS = "";
		
		switch (tipoScript) {
		case INSTALACAO:
			sb.append("Write-Host Copiando arquivos para Instalação... -foregroundColor Blue");
			dirCompletoProjetoPS = dirProjetoPS + File.separator + tipoScript.getNomeArquivoScript();
			break;
		case BACKUP:
			sb.append("Write-Host Copiando arquivos para Backup... -foregroundColor Blue");
			dirCompletoProjetoPS = dirProjetoPS + File.separator + tipoScript.getNomeArquivoScript();
			break;
		case ROLLBACK:
			sb.append("Write-Host Copiando arquivos para Rollback... -foregroundColor Blue");
			dirCompletoProjetoPS = dirProjetoPS + File.separator + tipoScript.getNomeArquivoScript();
			break;
		default:
			break;
		}

		sb.append(System.getProperty("line.separator"));
		sb.append(System.getProperty("line.separator"));
		sb.append("[String]$DirAplicao = \"" + dirAplicacao + "\" #Diretório da aplicação (raiz)");
		sb.append(System.getProperty("line.separator"));
		sb.append("[String]$DirPacote = \"" + dirPacote + "\" #Diretório de origem (onde contempla o pacote)");
		sb.append(System.getProperty("line.separator"));
		listParamPS.add(sb.toString());
		
		Path path = Paths.get(dirCompletoProjetoPS);
		
		Files.write(path, listParamPS, Charset.forName("UTF-8"));
		
		return listParamPS;
	}
}
