/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * AppContext.java
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

public class AppContext 
{
//Private
	private String homeDir = "";

	private String binDir = "";
	private String configDir = "";
	private String dataDir = "";
	private String dataInitDir = "";
	private String logsDir = "";
	private String webDiyDir = "";
	
	private String configFileName = "";
	private String logFileName = "";

//Public
	
	public AppContext() {
		this.homeDir = System.getenv(AppDefs.APP_HOME);
		if(homeDir == null)
			this.homeDir = AppDefs.CTX_DEFAULT_HOME_DIR;
		
		this.binDir = this.homeDir + AppDefs.CTX_HOME_BIN;
		this.configDir = this.homeDir + AppDefs.CTX_HOME_CONFIG;
		this.dataDir = this.homeDir + AppDefs.CTX_HOME_DATA;
		this.dataInitDir = this.homeDir + AppDefs.CTX_HOME_DATA_INIT;
		this.logsDir = this.homeDir + AppDefs.CTX_HOME_LOGS;
		this.webDiyDir = this.homeDir + AppDefs.CTX_HOME_WEBDIYDIR;
		
		this.configFileName = this.configDir + "/" + AppDefs.CTX_CONFIG_FILENAME;
		this.logFileName = this.logsDir + "/" + AppDefs.CTX_LOG_FILENAME;
		
	}
	
	/* Methodes */
	
	public void show() {
		System.out.println("CONTEXTO:");
		System.out.println("  HomeDir: " + this.homeDir);
		System.out.println("");
		System.out.println("  BinDir: " + this.binDir);
		System.out.println("  ConfigDir: " + this.configDir);
		System.out.println("  DataDir: " + this.dataDir);
		System.out.println("  DataInitDir: " + this.dataInitDir);
		System.out.println("");		
		System.out.println("  LogDir: " + this.logsDir);
		System.out.println("");		
		System.out.println("  WEB-DIY: " + this.webDiyDir);
		System.out.println("");		
		System.out.println("  ConfigFileName: " + this.configFileName);
		System.out.println("  LogFileName: " + this.logFileName);
		
	}
	
	/* Getters/Settters */

	public String getHomeDir() {
		return homeDir;
	}

	public void setHomeDir(String homeDir) {
		this.homeDir = homeDir;
	}

	public String getBinDir() {
		return binDir;
	}

	public void setBinDir(String binDir) {
		this.binDir = binDir;
	}

	public String getConfigDir() {
		return configDir;
	}

	public void setConfigDir(String configDir) {
		this.configDir = configDir;
	}

	public String getDataDir() {
		return dataDir;
	}

	public void setDataDir(String dataDir) {
		this.dataDir = dataDir;
	}

	public String getConfigFileName() {
		return configFileName;
	}

	public void setConfigFileName(String configFileName) {
		this.configFileName = configFileName;
	}

	public String getLogsDir() {
		return logsDir;
	}

	public void setLogsDir(String logsDir) {
		this.logsDir = logsDir;
	}

	public String getLogFileName() {
		return logFileName;
	}

	public void setLogFileName(String logFileName) {
		this.logFileName = logFileName;
	}

	public String getDataInitDir() {
		return dataInitDir;
	}

	public void setDataInitDir(String dataInitDir) {
		this.dataInitDir = dataInitDir;
	}

	public String getWebDiyDir() {
		return this.webDiyDir;
	}

	public void setWebDiyDir(String webDiyDir) {
		this.webDiyDir = webDiyDir;
	}

}
