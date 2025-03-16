/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * AppMain.java
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

import java.util.Locale;

import javax.swing.JComponent;
import javax.swing.JFrame;

import br.com.tlmv.harmonicas.frm.MainFrame;
import br.com.tlmv.harmonicas.util.DateUtil;
import br.com.tlmv.harmonicas.util.FileUtil;

public class AppMain extends JFrame
{
//Private

	private static AppMain gApp = null;

	private AppContext m_context = null;
	
	private AppConfig m_config = null;
	
	private Locale m_localePtBr = new Locale(AppDefs.DEF_LANG_PT, AppDefs.DEF_COUNTRY_BR);

	private Locale m_localeEnUs = new Locale(AppDefs.DEF_LANG_EN, AppDefs.DEF_COUNTRY_US);
	
	// Parametros
	private Integer parmTipoComandoEntrada = AppDefs.DEF_PARAMCMD_ENTRADA_NONE;
	
	private boolean loadParams(String[] args)
	{
		int n = 0;
		if(args.length > 0)
		{
			// Obtem Primeiro Parametro
			//
			String cmdParam = args[n++].toLowerCase();
			
			if( AppDefs.DEF_PARAMTAG_ENTRADA_HELP.equals(cmdParam) )
			{
				showUsageMsg();			

				parmTipoComandoEntrada += AppDefs.DEF_PARAMCMD_ENTRADA_HELP;				
				return false;				
			}
			else if( AppDefs.DEF_PARAMTAG_ENTRADA_SHOWVALUES.equals(cmdParam) )
			{
				parmTipoComandoEntrada += AppDefs.DEF_PARAMCMD_ENTRADA_SHOWVALUES;				
			}

			if( AppDefs.DEF_PARAMTAG_ENTRADA_SHOWGRAPHICS.equals(cmdParam) )
			{
				parmTipoComandoEntrada += AppDefs.DEF_PARAMCMD_ENTRADA_SHOWGRAPHICS;
			}
		}

		return false;
	}

//Public
	
	public boolean initContext()
	{
		boolean result = false;
		try {
			m_context = new AppContext();
			//m_config = new AppConfig(m_context.getAppConfigFile());		
			
			result = true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void copyright()
	{
		String str = String.format("%s %s\n\n%s\n", 
			AppDefs.APP_NAME, 
			AppDefs.APP_VERSAO,
			AppDefs.APP_COPYRIGHT);
		System.out.print(str); 
	}
	
	public void copyrightDialog(JComponent parent)
	{
		String eventSubject = String.format("%s %s", 
			AppDefs.APP_NAME, 
			AppDefs.APP_VERSAO);

		String eventMessage = String.format("%s", AppDefs.APP_COPYRIGHT);
		
		AppError.showMessageBox(parent, eventSubject, eventMessage, this.getClass());
	}
	
	public void showUsageMsg()
	{
		String str = String.format("%s\n", 
			AppDefs.HLP_USAGE_INFO);
		System.out.print(str); 	
		System.exit(1);
	}

	public void saveConfig()
		throws Exception
	{
		String outXml = m_config.toXml();
		
		m_config.saveData(m_context.getAppConfigFile(), outXml);
		m_config.loadData(outXml);		
	}

	public void init(String args[])
	{
		DateUtil.disableDaylightSaving();
		
		this.copyright();
	}
	
	public void execute(String args[])
	{
		if( !initContext() ) {
			String errmsg = AppError.ERR_FALHA_INICIALIZACAO_CONTEXTO;
			AppError.showCmdError(errmsg, this.getClass());
		}
		
		if( loadParams(args) ) {
			//TODO:
		}
		
		if((this.parmTipoComandoEntrada & AppDefs.DEF_PARAMCMD_ENTRADA_HELP) != 0) {
			/* nothing todo! */
		}
		else {
			initMainFrame();		
		}
	}
	
	public void initMainFrame()
	{
		try {
			JFrame f = new MainFrame();
			f.show();	
		}
		catch(Exception e) {
			e.printStackTrace();
			
			String errmsg = AppError.ERR_FALHA_PROCESSAMENTO + "(05)";
			AppError.showCmdError(errmsg, this.getClass());
		}
	}
	
	/* Getters/Setters */
	
	public static AppMain getApp()
	{
		return AppMain.gApp;
	}

	public AppContext getContext()
	{
		return m_context;
	}
	
	public AppConfig getConfig()
	{
		return m_config;
	}
	
	public Locale getPtBr()
	{
		return m_localePtBr;
	}

	public Locale getEnUs()
	{
		return m_localeEnUs;
	}

	/* Main */

	public static void main(String[] args) 
	{
		gApp = new AppMain();
		gApp.init(args);
		gApp.execute(args);
	}
	
}
