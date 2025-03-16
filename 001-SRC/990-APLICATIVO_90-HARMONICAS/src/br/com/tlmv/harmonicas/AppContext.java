/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * AppContext.java
 * Autor: 
 * - Luiz Marcio Faria de Aquino Viana, Pós-D.Sc. - Engenheiro, 04/02/2023
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

package br.com.tlmv.harmonicas;

public class AppContext 
{
//Private
	private String homeDir;
	private String configDir;
	private String exportsDir;
	private String reportsDir;
	private String tempDir;
	private String tmpDir;
	private String tmpLogDir;
	private String saidaDir;
	//
	private String appConfigFile;
	
//Public

	public AppContext()
		throws Exception
	{
		this.homeDir = System.getenv(AppDefs.APP_HOME);
		if(this.homeDir != null)
		{
			this.configDir = homeDir + AppDefs.DEF_DIR_CONFIG;
			this.exportsDir = homeDir + AppDefs.DEF_DIR_EXPORTS;
			this.reportsDir = homeDir + AppDefs.DEF_DIR_REPORTS;
			this.tempDir = homeDir + AppDefs.DEF_DIR_TEMP;
			this.tmpDir = homeDir + AppDefs.DEF_DIR_TMP;
			this.tmpLogDir = homeDir + AppDefs.DEF_DIR_LOG;
			this.saidaDir = homeDir + AppDefs.DEF_DIR_SAIDA;
			
			this.appConfigFile = configDir + AppDefs.DEF_CONFIG_APPFILE;
		}			
	}
	
	/* Getters/Setters */
		
	public String getHomeDir() {
		return homeDir;
	}
	
	public void setHomeDir(String homeDir) {
		this.homeDir = homeDir;
	}
	
	public String getConfigDir() {
		return configDir;
	}

	public void setConfigDir(String configDir) {
		this.configDir = configDir;
	}

	public String getAppConfigFile() {
		return appConfigFile;
	}

	public void setAppConfigFile(String appConfigFile) {
		this.appConfigFile = appConfigFile;
	}

	public String getExportsDir() {
		return exportsDir;
	}

	public void setExportsDir(String exportsDir) {
		this.exportsDir = exportsDir;
	}

	public String getReportsDir() {
		return reportsDir;
	}

	public void setReportsDir(String reportsDir) {
		this.reportsDir = reportsDir;
	}

	public String getTempDir() {
		return tempDir;
	}

	public void setTempDir(String tempDir) {
		this.tempDir = tempDir;
	}

	public String getTmpDir() {
		return tmpDir;
	}

	public void setTmpDir(String tmpDir) {
		this.tmpDir = tmpDir;
	}

	public String getSaidaDir() {
		return saidaDir;
	}

	public void setSaidaDir(String saidaDir) {
		this.saidaDir = saidaDir;
	}

	public String getTmpLogDir() {
		return tmpLogDir;
	}

	public void setTmpLogDir(String tmpLogDir) {
		this.tmpLogDir = tmpLogDir;
	}

}
