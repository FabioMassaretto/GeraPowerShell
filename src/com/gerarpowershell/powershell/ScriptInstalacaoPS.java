package com.gerarpowershell.powershell;

import java.io.IOException;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

import com.gerarpowershell.enumerable.TipoScriptEnum;

public class ScriptInstalacaoPS extends CabecalhoScriptsPS {

	public ScriptInstalacaoPS(List<FileItem> multipart, String diretorioProjetoPS, String diretorioAplicacao, String diretorioPacoteComGMUD) throws IOException {
		super.criaCabecalhoScriptsPS(diretorioProjetoPS, diretorioAplicacao, diretorioPacoteComGMUD, TipoScriptEnum.INSTALACAO);
	}
	
}
