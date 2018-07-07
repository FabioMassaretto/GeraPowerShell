package com.gerarpowershell.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Diretorio {
	private Path packagePath;
	private Path psFolderPath;
	private Path siteFolderPath;
	private boolean isBaseDiretoriosCriada = false;
	
	// Define o nome das pastas do pacote (pastas: PS e site)
	private String pastaPS = "PS";
	private String pastaSite = "Site";
	
	public void CriarBaseDiretorios(String rootDiretory, String nomeProjeto, String numeroChamado, String numeroTask) throws IOException {
		Path fullPath = Paths.get(rootDiretory, nomeProjeto, numeroChamado, numeroTask);
		Path psPath = Paths.get(fullPath.toString(), pastaPS);
		Path sitePath = Paths.get(fullPath.toString(), pastaSite);		
		
		// Cria a estrutura de pastas do pacote 
		// (diretorio raiz / nome do Projeto / numero Chamado / numero Task)
		// Ex.: C:/temp/MCS/CXXXXXX/TYYYYYY
		if(!Files.exists(fullPath)) {
			Files.createDirectories(fullPath);
		}
		
		// Cria a pasta PS dentro da pasta TYYYYYY
		if(!Files.exists(psPath)) {
			Files.createDirectories(psPath);
		}
		// Cria a pasta Site dentro da pasta TYYYYYY
		if(!Files.exists(sitePath)) {
			Files.createDirectories(sitePath);
		}
		
		packagePath = fullPath;
		psFolderPath = psPath;
		siteFolderPath = sitePath;
		setBaseDiretoriosCriada(true);
	}

	public Path getPackagePath() {
		return packagePath;
	}

	public Path getPsFolderPath() {
		return psFolderPath;
	}

	public Path getSiteFolderPath() {
		return siteFolderPath;
	}

	public boolean isBaseDiretoriosCriada() {
		return isBaseDiretoriosCriada;
	}

	public void setBaseDiretoriosCriada(boolean isBaseDiretorios) {
		this.isBaseDiretoriosCriada = isBaseDiretorios;
	}

}
