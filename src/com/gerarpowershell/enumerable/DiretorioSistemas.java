package com.gerarpowershell.enumerable;

public enum DiretorioSistemas {
	MCS("MCS", "D:\\inetpub\\MCS\\MapfreConnectSite"),
	NCC("NCC", "D:\\inetpub\\MCS\\MapfreConnectSite"),
	OUTRO("OUTRO", "OUTRO");
	;
	
	private String diretorio;
	private String sistema;

	DiretorioSistemas(String sistema, String diretorio) {
		this.sistema = sistema;
		this.diretorio = diretorio;
	}
	
	public String getDiretorio() {
		return diretorio;
	}

	public String getSistema() {
		return sistema;
	}

}
