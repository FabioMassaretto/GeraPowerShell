package com.gerarpowershell.bat;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.gerarpowershell.enumerable.TipoScriptEnum;

public class ExecutorGmudBat {
	
	public ExecutorGmudBat(String nomeGMUD, String dirPacote, String dirRaizProjeto) throws IOException {		
		criarExcutorGMUD(nomeGMUD, dirPacote, dirRaizProjeto);
	}

	public static void criarExcutorGMUD(String nomeGMUD, String dirPacote, String dirRaizProjeto) throws IOException {
		StringBuilder sb = new StringBuilder();
		List<String> listaConteudo = new ArrayList<>();
		String caminhoPS = dirPacote + File.separator + nomeGMUD + File.separator + "PS";
		String lineSeparator = System.getProperty("line.separator");
		final String CAMINHO_ARQUIVO_BAT = dirRaizProjeto + File.separator + "ExecutorGMUD.bat";
		
		sb.append("@echo off");
		sb.append(lineSeparator);
		sb.append("REM Variaveis de nome da GMUD e valores do powershell");
		sb.append(lineSeparator);
		sb.append("SET GMUD=" + nomeGMUD);
		sb.append(lineSeparator);
		sb.append("REM #################");
		sb.append(lineSeparator);
		sb.append(lineSeparator);
		
		sb.append("ECHO INICIANDO EXECUCAO DE POWERSHELL");
		sb.append(lineSeparator);
		sb.append("ECHO INICIANDO EXECUCAO DE POWERSHELL EM %DATE% AS %TIME% > LOG_EXECUTORGMUD.log");
		sb.append(lineSeparator);
		sb.append("ECHO. >> LOG_EXECUTORGMUD.log");
		sb.append(lineSeparator);
		sb.append(lineSeparator);
		
		sb.append("REM #############################");
		sb.append(lineSeparator);
		sb.append("REM ### INICIO DA EXECUCAO DO POWERSHELL: " + TipoScriptEnum.BACKUP.getNomeArquivoScript() + " ###");
		sb.append(lineSeparator);
		sb.append("SET NOME_PS=ScriptBackup.ps1");
		sb.append(lineSeparator);
		sb.append("SET CAMINHO_PS=" + caminhoPS + File.separator + TipoScriptEnum.BACKUP.getNomeArquivoScript());
		sb.append(lineSeparator);
		sb.append("ECHO Executando %NOME_PS%");
		sb.append(lineSeparator);
		sb.append("ECHO Executando %NOME_PS% >> LOG_EXECUTORGMUD.log");
		sb.append(lineSeparator);
		sb.append("powershell %CAMINHO_PS%");
		sb.append(lineSeparator);
		sb.append("ECHO.");
		sb.append(lineSeparator);
		sb.append(lineSeparator);
		
		sb.append("IF %ERRORLEVEL% EQU 0 (");
		sb.append(lineSeparator);
		sb.append("	ECHO %NOME_PS% executado com sucesso.");
		sb.append(lineSeparator);
		sb.append("	ECHO %NOME_PS% executado com sucesso. >> LOG_EXECUTORGMUD.log");
		sb.append(lineSeparator);
		sb.append("	ECHO. >> LOG_EXECUTORGMUD.log");
		sb.append(lineSeparator);
		sb.append(") ELSE ( ");
		sb.append(lineSeparator);
		sb.append("	ECHO Ocorreu um erro executando o arquivo %NOME_PS%.");
		sb.append(lineSeparator);
		sb.append("	ECHO Ocorreu um erro executando o arquivo %NOME_PS%. >> LOG_EXECUTORGMUD.log");
		sb.append(lineSeparator);
		sb.append("	ECHO. >> LOG_EXECUTORGMUD.log");
		sb.append(lineSeparator);
		sb.append(")");
		sb.append(lineSeparator);
		sb.append("REM ### FIM DA EXECUCAO DO POWERSHELL " + TipoScriptEnum.BACKUP.getNomeArquivoScript() + " ###");
		sb.append(lineSeparator);
		sb.append(lineSeparator);
		
		sb.append("REM #############################");
		sb.append(lineSeparator);
		sb.append("REM ### INICIO DA EXECUCAO DO POWERSHELL: "+ TipoScriptEnum.INSTALACAO.getNomeArquivoScript() +" ###");
		sb.append(lineSeparator);
		sb.append("SET NOME_PS=" + TipoScriptEnum.INSTALACAO.getNomeArquivoScript());
		sb.append(lineSeparator);
		sb.append("SET CAMINHO_PS=" + caminhoPS + File.separator + TipoScriptEnum.INSTALACAO.getNomeArquivoScript());
		sb.append(lineSeparator);
		sb.append("ECHO Executando %NOME_PS%");
		sb.append(lineSeparator);
		sb.append("ECHO Executando %NOME_PS% >> LOG_EXECUTORGMUD.log");
		sb.append(lineSeparator);
		sb.append("powershell %CAMINHO_PS%");
		sb.append(lineSeparator);
		sb.append("ECHO.");
		sb.append(lineSeparator);
		sb.append(lineSeparator);
		
		sb.append("IF %ERRORLEVEL% EQU 0 (");
		sb.append(lineSeparator);
		sb.append("	ECHO %NOME_PS% executado com sucesso.");
		sb.append(lineSeparator);
		sb.append("	ECHO %NOME_PS% executado com sucesso. >> LOG_EXECUTORGMUD.log");
		sb.append(lineSeparator);
		sb.append("	ECHO. >> LOG_EXECUTORGMUD.log");
		sb.append(lineSeparator);
		sb.append(") ELSE ( ");
		sb.append(lineSeparator);
		sb.append("	ECHO Ocorreu um erro executando o arquivo %NOME_PS%.");
		sb.append(lineSeparator);
		sb.append("	ECHO Ocorreu um erro executando o arquivo %NOME_PS%. >> LOG_EXECUTORGMUD.log");
		sb.append(lineSeparator);
		sb.append("	ECHO. >> LOG_EXECUTORGMUD.log");
		sb.append(lineSeparator);
		sb.append(")");
		sb.append(lineSeparator);
		sb.append("REM ### FIM DA EXECUCAO DO POWERSHELL ScriptInstalacao.ps1 ###");
		sb.append(lineSeparator);
		sb.append(lineSeparator);
		
		sb.append("REM Se houver erro a execucao é abortada.");
		sb.append(lineSeparator);
		sb.append("IF %ERRORLEVEL% GTR 0 ( ");
		sb.append(lineSeparator);
		sb.append("	SET RESULT=ERROS");
		sb.append(lineSeparator);
		sb.append("	ECHO A execucao foi abortada com ERROS");
		sb.append(lineSeparator);
		sb.append("	ECHO A execucao foi abortada com ERROS >> LOG_EXECUTORGMUD.log");
		sb.append(lineSeparator);
		sb.append("	ECHO. >> LOG_EXECUTORGMUD.log");
		sb.append(lineSeparator);
		sb.append("	GOTO RESULT");
		sb.append(lineSeparator);
		sb.append(") ELSE (");
		sb.append(lineSeparator);
		sb.append("	SET RESULT=SUCESSO"); 
		sb.append(lineSeparator);
		sb.append(")");
		sb.append(lineSeparator);
		sb.append(lineSeparator);
		
		sb.append(":RESULT");
		sb.append(lineSeparator);
		sb.append("ECHO.");
		sb.append(lineSeparator);
		sb.append("ECHO %GMUD% Executada com %RESULT% em %DATE% as %TIME%");
		sb.append(lineSeparator);
		sb.append("ECHO %GMUD% Executada com %RESULT% em %DATE% as %TIME% >> LOG_EXECUTORGMUD.log");
		sb.append(lineSeparator);
		sb.append("ECHO. >> LOG_EXECUTORGMUD.log");
		sb.append(lineSeparator);
		sb.append("GOTO END");
		sb.append(lineSeparator);
		sb.append(lineSeparator);
		
		sb.append(":END");
		sb.append(lineSeparator);
		sb.append("ECHO.");
		sb.append(lineSeparator);
		sb.append("ECHO Para ver o log completo, verifique o arquivo LOG_EXECUTORGMUD");
		sb.append(lineSeparator);
		sb.append("ECHO Execucao de Batch finalizada em %DATE% as %TIME% >> LOG_EXECUTORGMUD.log");
		sb.append(lineSeparator);
		sb.append("IF %ERRORLEVEL% GTR 0 ( PAUSE )");
		
		listaConteudo.add(sb.toString());
		
		Path path = Paths.get(CAMINHO_ARQUIVO_BAT);
		
		Files.write(path, listaConteudo, Charset.forName("UTF-8"));
	}
}
