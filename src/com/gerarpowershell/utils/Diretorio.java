package com.gerarpowershell.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Diretorio {
	private Path mainPackagePath;
	private Path psFolderPath;
	private Path siteFolderPath;
	private Path gmudPackagePath;
	
	// Define o nome das pastas do pacote (pastas: PS e site)
	private String pastaPS = "PS";
	private String pastaSite = "Site";
	
	public Diretorio() {

	}
	
	public void CriarBaseDiretorios(String rootDiretory, String nomeProjeto, String numeroChamado, String numeroTask) throws IOException {
		mainPackagePath = Paths.get(rootDiretory, nomeProjeto, numeroChamado, numeroTask);
		psFolderPath = Paths.get(mainPackagePath.toString(), pastaPS);
		siteFolderPath = Paths.get(mainPackagePath.toString(), pastaSite);
		gmudPackagePath = Paths.get(rootDiretory, nomeProjeto, "Pacote" , numeroChamado, numeroTask);
		
		// Cria a estrutura de pastas do pacote 
		// (diretorio raiz / nome do Projeto / numero Chamado / numero Task)
		// Ex.: C:/temp/MCS/CXXXXXX/TYYYYYY
		if(!Files.exists(mainPackagePath)) {
			Files.createDirectories(mainPackagePath);
		}
		
		// Cria a pasta PS dentro da pasta TYYYYYY
		if(!Files.exists(psFolderPath)) {
			Files.createDirectories(psFolderPath);
		}
		
		// Cria a pasta Site dentro da pasta TYYYYYY
		if(!Files.exists(siteFolderPath)) {
			Files.createDirectories(siteFolderPath);
		}
		
		// Cria a pasta onde o pacote (zip) com todos os arquivos de powershell, .bat e o site ficará
		if(!Files.exists(gmudPackagePath)) {
			Files.createDirectories(gmudPackagePath);
		}
	}
	
	public boolean baseDiretoriosExists(String rootDiretory, String nomeProjeto, String numeroChamado, String numeroTask) {
		mainPackagePath = Paths.get(rootDiretory, nomeProjeto, numeroChamado, numeroTask);
		if(!Files.exists(mainPackagePath)) {
			return false;
		}
		return true;
	}

	public Path getPackagePath() {
		return mainPackagePath;
	}

	public Path getPsFolderPath() {
		return psFolderPath;
	}

	public Path getSiteFolderPath() {
		return siteFolderPath;
	}

	public Path getGmudPackagePath() {
		return gmudPackagePath;
	}

}
