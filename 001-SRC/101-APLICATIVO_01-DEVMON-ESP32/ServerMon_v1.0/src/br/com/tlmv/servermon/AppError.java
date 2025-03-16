/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * AppError.java
 * Autor: 
 * - Luiz Marcio Faria de Aquino Viana, Pós-D.Sc. - Engenheiro, 28/01/2023
 *   Unico Socio e Engenheiro - Desde: 02/08/2000
 *
 * Formação Academica:
 * - Graduação em Engenharia Eletrica, Enfase em Engenharia de Sistemas e Computação - Julho/1997
 *   UERJ - Universidade do Estado do Rio de Janeiro
 * - Mestrado/Doutorado em Engenharia de Sistemas e Computação, Área de Arquitetura de Computadores e Sistemas Operacionais - Março/2002
 *   COPPE/UFRJ - Universidade Federal do Rio de Janeiro 
 * - Pós-Doutoramento em Engenharia de Sistemas e Computação, Área de Arquitetura de Computadores e Sistemas Operacionais - Setembro/2022
 *   COPPE/UFRJ - Universidade Federal do Rio de Janeiro 
 * - MBA em Gestão de Negócios - Julho/2011
 *   IAG/PUC-RJ - Pontifícia Universidade Católica do Rio de Janeiro 
 *
 * Revisoes: ...
 * 
 */

package br.com.tlmv.servermon;

import java.io.File;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.tlmv.servermon.utils.UuidUtil;

public class AppError 
{
//Private
	private String appName;
	
//Public

	/* MESSAGES */
	public static final String ERR_INVALID_COMMAND_LINE_SWITCHES = "Invalid command line switches.";
	public static final String ERR_CANT_OPEN_FILE = "Can't open file (%s).";
	public static final String ERR_CONFIGURATION_FILE_READ_FAILURE = "Configuration file read failure (%s).";
	public static final String ERR_UNIT_TEST_EXECUTION_FAILURE = "Unit test execution failure.";
	public static final String ERR_DATA_CREATOR_EXECUTION_FAILURE = "Data creator execution failure.";
	public static final String ERR_DATA_CREATOR_NOT_IMPLEMENTED = "Data creator not implemented (%s).";
	public static final String ERR_TOO_MANY_REQUESTS = "Too many requestes (RETRY_AFTER = %s).";
	public static final String ERR_UNSUPORTED_IMAGE_FORMAT = "Unsuported image format (REQUEST_URL=%s).";
	public static final String ERR_HTTP_INFORMATIONAL_RESPONSE = "HTTP Informational response (RETRY_AFTER = %s).";
	public static final String ERR_HTTP_SUCCESSFUL_RESPONSE = "HTTP Successful response.";
	public static final String ERR_HTTP_REDIRECTION_RESPONSE = "HTTP Redirection response.";
	public static final String ERR_HTTP_CLIENT_ERROR_RESPONSE = "HTTP Client error responses.";
	public static final String ERR_HTTP_SERVER_ERROR_RESPONSE = "HTTP Server error response.";
	public static final String ERR_HTTP_RESPONSE_ERROR = "HTTP response erro (RESPONSE_CODE=%s).";
	
	/* Constructors */
	
	public AppError() {
		this.init(AppDefs.APP_NAME);
	}
	
	/* Methodes */

	public void init(String appName) {
		this.appName = appName;
	}
	
	// WRITE_TO_LOG_FILE
	
	public boolean checkDebugLevel(int debugLevel) {
		if(debugLevel == AppDefs.DEBUG_LEVEL)
			return true;
		return false;
	}
	
	public void writeToLogFile(String className, String methodName, String logMessage) {
		DateFormat df = new SimpleDateFormat(AppDefs.FMT_DATETIME_INV_MASC);
	
		Date dataAtual = new Date();		
		String outMsg = String.format("\n%s - %s@%s@%s: %s", df.format(dataAtual), this.appName, className, methodName, logMessage);
		System.out.println(outMsg);
	}
	
	public void writeLog(String className, String methodName, String message) {
		this.writeToLogFile(className, methodName, message);
	}
	
	public void writeDebug(int debugLevel, String className, String methodName, String message) {
		if( !checkDebugLevel(debugLevel) ) return;
			this.writeToLogFile(className, methodName, message);
	}
	
	public void writeError(String className, String methodName, String message) {
		this.writeToLogFile(className, methodName, message);
	}
	
	// WRITE_UNIT_TEST
	
	public void writeUnitTest(String className, String methodName, String message) {
		DateFormat df = new SimpleDateFormat(AppDefs.FMT_DATETIME_INV_MASC);
		
		Date dataAtual = new Date();		
		String outMsg = String.format("\n%s - %s@%s@%s: %s", df.format(dataAtual), this.appName, className, methodName, message);
		System.out.println(outMsg);
	}

	public void writeUnitTestResult(String className, String methodName, String message, boolean bResult) {
		DateFormat df = new SimpleDateFormat(AppDefs.FMT_DATETIME_INV_MASC);

		Date dataAtual = new Date();		
		String outMsg = String.format("\n%s - %s@%s@%s: %s", df.format(dataAtual), this.appName, className, methodName, message);
		System.out.println(outMsg);
	}
	
	/* Getters/Setters */
	
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

}
