package com.gerarpowershell.enumerable;

public enum DiretorioSistemas {
	MCS("MCS", "D:\\inetpub\\MCS\\MapfreConnectSite", "D:\\temp"),
	NCC("NCC", "C:\\inetpub\\NCC\\Site", "C:\\temp"),
	OUTRO("OUTRO", "OUTRO", "");
	
	private String diretorio;
	private String sistema;
	private String dirPacote;

	DiretorioSistemas(String sistema, String dirSistema, String dirPacote) {
		this.sistema = sistema;
		this.diretorio = dirSistema;
		this.dirPacote = dirPacote;
	}
	
	public String getDiretorio() {
		return diretorio;
	}

	public String getSistema() {
		return sistema;
	}

	public String getDirPacote() {
		return dirPacote;
	}

}
