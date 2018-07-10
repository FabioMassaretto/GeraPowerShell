package com.gerarpowershell.powershell;

import java.io.IOException;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

public class ScriptInstalacaoPS extends CabecalhoScriptsPS {

	public ScriptInstalacaoPS(List<FileItem> multipart, String diretorioPacoteComGMUD, String diretorioAplicacao) throws IOException {
		super.criaCabecalhoScriptsPS(diretorioAplicacao, diretorioPacoteComGMUD, "Instalação");
	}
	
}
