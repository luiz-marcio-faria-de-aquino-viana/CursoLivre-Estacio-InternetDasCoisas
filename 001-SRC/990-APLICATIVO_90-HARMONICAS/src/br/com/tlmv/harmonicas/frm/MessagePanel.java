/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * MessagePanel.java
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

import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.com.tlmv.harmonicas.AppDefs;
import br.com.tlmv.harmonicas.frm.events.ResultEvent;
import br.com.tlmv.harmonicas.frm.events.ResultListener;
import br.com.tlmv.harmonicas.util.FormControlUtil;
import br.com.tlmv.harmonicas.util.FormatUtil;
import br.com.tlmv.harmonicas.vo.MessageDataVO;

public class MessagePanel extends JPanel implements ActionListener
{
//Private
	private MessageFrame m_parentFrame = null;
	private MessagePanel m_panel = null;

	private JLabel lblEventDate = null;
	private JLabel lblEventSubject = null;
	private JLabel lblEventMessage = null;

	private JTextField txtEventDate = null;
	private JTextField txtEventSubject = null;
	private JTextArea txtEventMessage = null;

	private JButton btnFechar = null;

	private int rscode = AppDefs.RSCODE_MESSAGE_NONE;
	
	private void initForm()
	{
		this.setLayout(null);

		NumberFormat nf2 = FormatUtil.newNumberFormatPtBr(2);
		
		DateFormat df = FormatUtil.newDateFormat(AppDefs.DEF_DATETIME_MASC);
		
		MessageDataVO messageData = m_parentFrame.getMessageData();
		
		Insets insets = this.getInsets();

		int xp = insets.left + 5;
		int yp = insets.top + 5;
		
		//Label
		//

		//EventDate
		lblEventDate = new JLabel("Data:");
		lblEventDate.setLocation(xp, yp);
		lblEventDate.setSize(100, 20); 
		lblEventDate.setVisible(true);
		this.add(lblEventDate);
		yp = yp + 25;		
		
		//EventSubject
		lblEventSubject = new JLabel("Assunto:");
		lblEventSubject.setLocation(xp, yp);
		lblEventSubject.setSize(100, 20); 
		lblEventSubject.setVisible(true);
		this.add(lblEventSubject);
		yp = yp + 25;		
		
		//EventMessage
		lblEventMessage = new JLabel("Mensagem:");
		lblEventMessage.setLocation(xp, yp);
		lblEventMessage.setSize(100, 20); 
		lblEventMessage.setVisible(true);
		this.add(lblEventMessage);
		yp = yp + 185;		
		xp = insets.left + 640 - 30 - 100;
		
		//Button
		//
		
		//Button: Fechar
		btnFechar = new JButton("Fechar");
		btnFechar.setActionCommand(AppDefs.DEF_BTN_ACTION_MESSAGE_FECHAR);
		btnFechar.setLocation(xp, yp);
		btnFechar.setSize(100, 20); 
		btnFechar.setVisible(true);
		btnFechar.addActionListener(this);
		this.add(btnFechar);
		xp = insets.left + 5 + 100 + 5;
		yp = insets.top + 5;
		
		//Text
		//
		String strEventDate = df.format(messageData.getEventDate());
		
		txtEventDate = new JTextField(strEventDate);
		txtEventDate.setLocation(xp, yp);
		txtEventDate.setSize(500, 20); 
		txtEventDate.setVisible(true);
		txtEventDate.setEditable(false);
		this.add(txtEventDate);
		yp = yp + 25;
							
		//Text
		//
		txtEventSubject = new JTextField(messageData.getEventSubject());
		txtEventSubject.setLocation(xp, yp);
		txtEventSubject.setSize(500, 20); 
		txtEventSubject.setVisible(true);
		txtEventSubject.setEditable(false);
		this.add(txtEventSubject);
		yp = yp + 25;
		
		//Text
		//
		this.txtEventMessage = FormControlUtil.newTextArea(messageData.getEventMessage(), xp, yp, 500, 180, true, false);
		this.add(txtEventMessage);
		
	}
	
//Public 
	
	public MessagePanel()
	{
		super();
		
		m_panel = this;
	}
	
	public void init(
		MessageFrame parentFrame, 
		MessageDataVO messageData,
		ResultListener resultListener)
	{
		m_parentFrame = parentFrame;
		initForm();
	}
	
	/* Methodes */
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	}
	
	/* Actions */

	public void doActionOk(ActionEvent e) 
	{
		rscode = AppDefs.RSCODE_MESSAGE_OK;			
		m_parentFrame.actionResultListener(new ResultEvent(rscode, null));
		m_parentFrame.dispose();
	}
		
	public void doActionCancelar(ActionEvent e) 
	{
		rscode = AppDefs.RSCODE_MESSAGE_CANCELAR;
		m_parentFrame.actionResultListener(new ResultEvent(rscode, null));
		m_parentFrame.dispose();
	}
	
	public void doActionFechar(ActionEvent e) 
	{
		rscode = AppDefs.RSCODE_MESSAGE_FECHAR;
		m_parentFrame.actionResultListener(new ResultEvent(rscode, null));
		m_parentFrame.dispose();
	}
	
	/* Listeners */
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if( AppDefs.DEF_BTN_ACTION_MESSAGE_OK.equals(e.getActionCommand()) )
		{
			doActionOk(e);						
		}
		else if( AppDefs.DEF_BTN_ACTION_MESSAGE_CANCELAR.equals(e.getActionCommand()) )
		{
			doActionCancelar(e);						
		}
		else if( AppDefs.DEF_BTN_ACTION_MESSAGE_FECHAR.equals(e.getActionCommand()) )
		{
			doActionFechar(e);						
		}
	}

	/* Getters/Setters */
	
	public MessageFrame getParentFrame() {
		return m_parentFrame;
	}

	public MessagePanel getPanel() {
		return m_panel;
	}

	public int getRSCode() {
		return rscode;
	}

	public void setRSCode(int rscode) {
		this.rscode = rscode;
	}
	
}
