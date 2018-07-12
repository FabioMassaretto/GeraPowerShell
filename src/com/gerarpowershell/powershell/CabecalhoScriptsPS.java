package com.gerarpowershell.powershell;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.gerarpowershell.enumerable.TipoScriptEnum;

public class CabecalhoScriptsPS {
	public CabecalhoScriptsPS() {

	}
	
	public CabecalhoScriptsPS(String dirAplicacao, String dirPacote, TipoScriptEnum tipoScript) throws IOException {
		this.criaCabecalhoScriptsPS(dirAplicacao, dirPacote, tipoScript);
	}
	
	public List<String> criaCabecalhoScriptsPS(String dirAplicacao, String dirPacote, TipoScriptEnum tipoScript) throws IOException {
		StringBuilder sb = new StringBuilder();
		List<String> listParamPS = new ArrayList<>();
		
		switch (tipoScript) {
		case INSTALACAO:
			sb.append("Write-Host Copiando arquivos para Instalação... -foregroundColor Blue");
			break;
		case BACKUP:
			sb.append("Write-Host Copiando arquivos para Backup... -foregroundColor Blue");
			break;
		case ROLLBACK:
			sb.append("Write-Host Copiando arquivos para Rollback... -foregroundColor Blue");
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
		
		return listParamPS;
	}
}
