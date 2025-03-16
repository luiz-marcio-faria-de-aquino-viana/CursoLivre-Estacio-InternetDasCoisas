/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * MainFrame.java
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

package br.com.tlmv.harmonicas.frm;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JSeparator;

import br.com.tlmv.harmonicas.AppConfig;
import br.com.tlmv.harmonicas.AppDefs;
import br.com.tlmv.harmonicas.AppMain;
import br.com.tlmv.harmonicas.util.FormControlUtil;

public class MainFrame extends JFrame implements ActionListener, WindowListener
{
//Private
	private MainPanel m_mainPanel;
	
	// Create: Main Menu
	
	private void createMainMenu()
	{
		JMenu mnu = new JMenu(AppDefs.DEF_MNU_PRINCIPAL);
		
		mnu.add(FormControlUtil.newMenuItem(
			AppDefs.DEF_MNU_PRINCIPAL_PARAMS,
			AppDefs.DEF_MNU_PRINCIPAL_PARAMS,
			this) );

		mnu.add(FormControlUtil.newMenuItem(
			AppDefs.DEF_MNU_PRINCIPAL_ATUALIZAR,
			AppDefs.DEF_MNU_PRINCIPAL_ATUALIZAR,
			this) );

		mnu.add(new JSeparator());

		mnu.add(FormControlUtil.newMenuItem(
			AppDefs.DEF_MNU_PRINCIPAL_SOBRE,
			AppDefs.DEF_MNU_PRINCIPAL_SOBRE,
			this) );

		mnu.add(new JSeparator());

		mnu.add(FormControlUtil.newMenuItem(
			AppDefs.DEF_MNU_PRINCIPAL_SAIR,
			AppDefs.DEF_MNU_PRINCIPAL_SAIR,
			this) );
		
		JMenuBar mnubar = new JMenuBar();
		mnubar.add(mnu);

		this.setJMenuBar(mnubar) ;		
	}
	
	// Create: Main Panel

	private void createMainPanel()
	{
		m_mainPanel = new MainPanel();
		m_mainPanel.init(this);
		
		Container c = getContentPane();
		c.add(m_mainPanel);		
	}
	
//Public

	public MainFrame()
	{
		String appTitle = getAppTitle();		
		setTitle(appTitle);
		setSize(1280, 900);
		setLocation(25, 25);

		FormControlUtil.loadIcon(this);
		
		addWindowListener(this);
		
		createMainMenu();
		createMainPanel();
	}
	
	/* Methodes */
	
	public String getAppTitle()
	{
		AppMain app = AppMain.getApp();

		AppConfig config = app.getConfig();

		String appTitle = AppDefs.APP_NAME + " - " + AppDefs.APP_VERSAO;		
		return appTitle;
	}
	
	public void resetAppTitle()
	{
		String appTitle = getAppTitle();		
		setTitle(appTitle);		
	}

	/* Events */

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String mnuCmd = e.getActionCommand();

		if( (AppDefs.DEF_MNU_PRINCIPAL_PARAMS).equals(mnuCmd) )
		{
			m_mainPanel.doPrincipalParams(e);
		}
		else if( (AppDefs.DEF_MNU_PRINCIPAL_ATUALIZAR).equals(mnuCmd) )
		{
			m_mainPanel.doPrincipalAtualizar(e);
		}
		else if( (AppDefs.DEF_MNU_PRINCIPAL_SOBRE).equals(mnuCmd) )
		{
			m_mainPanel.doPrincipalSobre(e);						
		}
		else if( (AppDefs.DEF_MNU_PRINCIPAL_SAIR).equals(mnuCmd) )
		{
			m_mainPanel.doPrincipalSair();						
		}
		
	}
	
	@Override
	public void windowClosing(WindowEvent e) 
	{
		m_mainPanel.doPrincipalSair();
	}

	@Override
	public void windowActivated(WindowEvent e) { }

	@Override
	public void windowClosed(WindowEvent e) { }

	@Override
	public void windowDeactivated(WindowEvent e) { }

	@Override
	public void windowDeiconified(WindowEvent e) { }

	@Override
	public void windowIconified(WindowEvent e) { }

	@Override
	public void windowOpened(WindowEvent e) { }
	
}
