/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * AppMain.java
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

import br.com.tlmv.servermon.datacreator.ExecDataCreator;
import br.com.tlmv.servermon.rest.RESTfullServer;
import br.com.tlmv.servermon.tests.UnitTest;

public class AppMain 
{
//Private
	private static AppMain gApp = null;
	
	private AppContext ctx = null;
	
	private AppError err = null;
	
	private AppConfig config = null;
	
	private AppDataManager dataMan = null;

	//START_SWITCHES
	private boolean bUnitTest = false;
	private boolean bDataCreator = false;
	private boolean bRestServer = false;
	
//Public
	
	public AppMain() {
		AppMain.gApp = this;
		
		this.copyright();
		
		this.ctx = new AppContext();
		this.ctx.show();
		
		this.err = new AppError();				// start error log with default values

		this.config = new AppConfig(this.ctx.getConfigFileName());
		this.config.show();
		
		this.err.init(							// re-initialize error log with configuration values
			AppDefs.APP_NAME);
	}
	
	/* Methodes */

	public void copyright()
	{
		String str = String.format("%s %s\n\n%s\n", 
			AppDefs.APP_NAME, 
			AppDefs.APP_VERSAO,
			AppDefs.APP_COPYRIGHT);
		System.out.print(str); 
	}
	
	public void showUsageInfo()
	{
		String str = String.format("%s\n", 
			AppDefs.HLP_USAGE_INFO);
		System.out.print(str); 	
	}
	
	public boolean loadParms(String[] args) {
		for(int i = 0; i < args.length; i++) {
			String strCmd = args[i];
			if( AppDefs.CMD_HELP_STR.equals(strCmd) ) {
				this.showUsageInfo();
				System.exit(0);
			}
			else if( AppDefs.CMD_TEST_STR.equals(strCmd) ) {
				bUnitTest = true;
			}
			else if( AppDefs.CMD_DATACREATOR_STR.equals(strCmd) ) {
				bDataCreator = true;
			}
			else if( AppDefs.CMD_RESTSERVER_STR.equals(strCmd) ) {
				bRestServer = true;
			}
		}
		
		if( !bUnitTest && !bDataCreator && !bRestServer ) {
			this.err.writeError(this.getClass().getName(), "loadParms", AppError.ERR_INVALID_COMMAND_LINE_SWITCHES);
			this.showUsageInfo();
			System.exit(1);
		}
		return true;
	}

	public void execute(String[] args)
		throws Exception
	{
		if( this.loadParms(args) ) 
		{
			this.dataMan = new AppDataManager();
			
			if( this.bDataCreator ) {
				this.doDataCreator();					
			}
			else if( this.bRestServer ) {
				this.doRestServer();										
			}
			else if( this.bUnitTest ) {
				this.doUnitTest();															
			}
		}
	}
	
	public void doDataCreator()
		throws Exception
	{
		this.err.writeLog(this.getClass().getName(), "doDataCreator", "Starting Data Creator...");
		System.out.println("Starting Data Creator...");
		
		ExecDataCreator exec = new ExecDataCreator();
		if( !exec.executeDataCreator() ) {
			this.err.writeError(this.getClass().getName(), "doDataCreator", AppError.ERR_DATA_CREATOR_EXECUTION_FAILURE);
			System.exit(1);	
		}
		
		this.err.writeLog(this.getClass().getName(), "doDataCreator", "Data Creator terminated!");
		System.out.println("Data Creator Terminated!");
	}
	
	public void doRestServer()
		throws Exception
	{
		this.err.writeLog(this.getClass().getName(), "doRestServer", "Loading data tables...");
		System.out.println("Loading data tables...");

		this.dataMan.loadAll();
		
		this.err.writeLog(this.getClass().getName(), "doRestServer", "Starting RESTfull Server...");
		System.out.println("Starting RESTfull Server...");

		RESTfullServer server = new RESTfullServer();
		server.start();

		this.err.writeLog(this.getClass().getName(), "doRestServer", "RESTfull Server started!");
		System.out.println("RESTfull Server started! Press ANY KEY to stop.");
		
		int ch = -1;
		while(ch == -1) {
			try {
				ch = System.in.read();
			}
			catch(Exception e) { }
		}
		
		server.stop();
		
		this.dataMan.saveAll();
		
		this.err.writeLog(this.getClass().getName(), "doRestServer", "RESTfull Server terminated!");
		System.out.println("RESTfull Server Terminated!");
	}

	public void doUnitTest()
		throws Exception
	{
		this.err.writeLog(this.getClass().getName(), "doUnitTest", "Starting Unit Test...");
		System.out.println("Starting Unit Test...");
		
		UnitTest uTest = new UnitTest();
		if( !uTest.executeUnitTest() ) {
			this.err.writeError(this.getClass().getName(), "doUnitTest", AppError.ERR_UNIT_TEST_EXECUTION_FAILURE);
			System.exit(1);	
		}

		this.err.writeLog(this.getClass().getName(), "doUnitTest", "Unit Test terminated!");
		System.out.println("Unit Test terminated!");
	}
	
	/* Getters/Setters */
	
	public static AppMain getApp() {
		return AppMain.gApp;
	}

	public AppContext getContext() {
		return this.ctx;
	}
	
	public AppError getErr() {
		return err;
	}

	public AppDataManager getDataMan() {
		return this.dataMan;
	}
	
	/* MAIN */
	
	public static void main(String[] args)
	{
		try {
			AppMain.gApp = new AppMain();
			AppMain.gApp.execute(args);
		}
		catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
}
