package com.gerarpowershell.enumerable;

public enum TipoScriptEnum {
	INSTALACAO("ScriptInstalacao.ps1"),
	BACKUP("ScriptBackup.ps1"),
	ROLLBACK("ScriptRollback.ps1");
	
	private String nomeArquivoScript;
	
	TipoScriptEnum(String nomeArquivoScript) {
		this.nomeArquivoScript = nomeArquivoScript;
	}

	public String getNomeArquivoScript() {
		return nomeArquivoScript;
	}
}
