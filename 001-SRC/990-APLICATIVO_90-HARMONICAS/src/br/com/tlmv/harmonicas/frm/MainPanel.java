/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * MainPanel.java
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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.validation.Schema;

import org.w3c.dom.Document;

import br.com.tlmv.harmonicas.AppConfig;
import br.com.tlmv.harmonicas.AppDefs;
import br.com.tlmv.harmonicas.AppMain;
import br.com.tlmv.harmonicas.frm.events.ResultEvent;
import br.com.tlmv.harmonicas.frm.events.ResultListener;
import br.com.tlmv.harmonicas.util.FormControlUtil;
import br.com.tlmv.harmonicas.util.FormatUtil;
import br.com.tlmv.harmonicas.util.ListUtil;
import br.com.tlmv.harmonicas.vo.HarmonicaVO;
import sun.awt.AppContext;

public class MainPanel extends JPanel implements Runnable, ActionListener, ItemListener, ResultListener
{
//Private
	private MainFrame m_parentFrame = null;
	private MainPanel m_panel = null;

	/* FORM CONTROLS */

	private JLabel lblListaValores = null;
	private JTable tblListaValores = null;	
	private GraphicPanel panGraphicPanel = null;
	private JButton btnFechar = null;
	
	/* VALUE LIST */
	
	private List<HarmonicaVO> m_lsValores = null;

	/* LOCAL VARS */
	
	private Thread m_thread = null;
	private Boolean m_isRunning = false;
	
	private Boolean m_primVez = false;
	
	/* INIT FORMS */
	
	private void loadListaValores()
	{
		//TODO:
	}

	/* REFRESH FORMS */
	
	private void refreshAllLists() 
	{
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
				refreshLstValores();
            }
        });
	}
	
	private void refreshLstValores()
	{
		loadListaValores();

		DefaultTableModel model = new DefaultTableModel();
		for(String hdr : AppDefs.HDR_HARMONICAS_VALUES)
			model.addColumn(hdr);
		
		for(HarmonicaVO o : m_lsValores) {
			String[] rowData = ListUtil.toArray(AppDefs.defListaHarmonicas, o);
			model.addRow(rowData);
		}
		this.tblListaValores.setModel(model);
	}
	
	/* INIT FORMS */
	
	private void initForm()
	{
		NumberFormat nf2 = FormatUtil.newNumberFormatPtBr(2);
		
		DateFormat df = FormatUtil.newDateFormat(AppDefs.DEF_DATE_MASC);
		
		this.setLayout(null);

		m_primVez = true;				

		//lblListaValores = FormControlUtil.newLabel("Lista de valores:", 5, 5, 150, 20, true);
		//this.add(lblListaValores);
		
		//String[][] rows = new String[0][0];
		//tblListaValores = FormControlUtil.newTable(this, AppDefs.HDR_HARMONICAS_VALUES, rows, 5, 30, 940, 250, true);
		//this.add(tblListaValores);
		
		//panGraphicPanel = new GraphicPanel(5, 5, 1270, 795, 10, 38400.0, 1);
		panGraphicPanel = new GraphicPanel(5, 5, 1270, 795, 10, 60.0, 1);

		//panGraphicPanel = new GraphicPanel(5, 5, 1270, 795, 5, 60.0, 1);
		//panGraphicPanel = new GraphicPanel(5, 5, 1270, 795, 15, 60.0, 1);
		//panGraphicPanel = new GraphicPanel(5, 5, 1270, 795, 125, 60.0, 1);
		//panGraphicPanel = new GraphicPanel(5, 5, 1270, 795, 1250, 60.0, 1);
		panGraphicPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		panGraphicPanel.setVisible(true);
		this.add(panGraphicPanel);
		
		btnFechar = FormControlUtil.newButton("Fechar", AppDefs.DEF_BTN_ACTION_MAIN_FECHAR, 5, 805, 100, 30, true, this);
		this.add(btnFechar);
		
		m_primVez = false;		
	}
	
//Public 
	
	public MainPanel()
	{
		super();
		
		m_panel = this;		
	}
	
	public void init(MainFrame parent)
	{
		m_parentFrame = parent;
		
		Date dataHoraAtual = new Date();
		
		Date dataAtual = new Date(dataHoraAtual.getYear(), dataHoraAtual.getMonth(), dataHoraAtual.getDate());
		
		initForm();
		
		startThread();
	}

	/* Start/Stop Thread */
	
	public void startThread() {
		if( m_isRunning ) return;
		
		this.m_thread = new Thread(this);
		m_thread.start();
	}
	
	public void stopThread() {
		if((m_thread != null) && ( m_isRunning ))
		{
			m_isRunning = false;
			
			try {
				m_thread.join();
			}
			catch(Exception e1) { }
		}
	}
	
	/* Actions */
	
	//MNU_PRINCIPAL
	
	
	public void doPrincipalParams(ActionEvent e) {
		//TODO:
	}

	public void doPrincipalAtualizar(ActionEvent e) {
		//TODO:
	}

	public void doPrincipalSobre(ActionEvent e) {
		AppMain.getApp().copyrightDialog(this);
	}
	
	public void doPrincipalSair(ActionEvent e) {
		doPrincipalSair();
	}
	
	public void doPrincipalSair() {
		stopThread();
		System.exit(0);		
	}
	
	/* Listeners */

	@Override
	public void actionPerformed(ActionEvent e) {
		if( AppDefs.DEF_BTN_ACTION_MAIN_FECHAR.equals(e.getActionCommand()) ) {
			this.doPrincipalSair(e);
		}
	}

	@Override
	public void actionResultListener(ResultEvent e) {
		Date dataHoraAtual = new Date();
		
		Date dataAtual = new Date(dataHoraAtual.getYear(), dataHoraAtual.getMonth(), dataHoraAtual.getDate());
		long dataAtualMili = dataAtual.getTime();
		
		NumberFormat nf6 = FormatUtil.newNumberFormatPtBr(6);
		
		AppMain app = AppMain.getApp();
						
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/* Monitor Thread */

	@Override
	public void run() {
		m_isRunning = true;
		
		AppMain app = AppMain.getApp();
		
		AppConfig config = app.getConfig();
		
		while( m_isRunning )
		{
			try {
				Thread.sleep(AppDefs.THREAD_MAX_SLEEP_TIME);
			}
			catch(Exception e) { }
		}		
		m_isRunning = false;
	}
	
}
