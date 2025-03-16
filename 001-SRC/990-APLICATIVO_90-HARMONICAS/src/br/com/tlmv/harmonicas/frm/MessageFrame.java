/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * MessageFrame.java
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
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Date;

import javax.swing.JComponent;
import javax.swing.JFrame;

import br.com.tlmv.harmonicas.AppDefs;
import br.com.tlmv.harmonicas.frm.events.ResultEvent;
import br.com.tlmv.harmonicas.frm.events.ResultListener;
import br.com.tlmv.harmonicas.util.FormControlUtil;
import br.com.tlmv.harmonicas.vo.MessageDataVO;

public class MessageFrame extends JFrame implements WindowListener, ResultListener
{
//Private
	private JComponent m_parent = null;
	
	private ResultListener m_resultListener = null;
	
	private MessageDataVO m_messageData = null;
	
//Public
	
	public MessageFrame(JComponent parent)
	{
		m_parent = parent;
	}

	public void init(
		MessageDataVO messageData,
		ResultListener resultListener)
	{
		this.m_resultListener = resultListener;
				
		this.m_messageData = messageData;
		
		String title = "INFORMACAO";
		
		if(this.m_messageData.getMessageType() == AppDefs.DEF_MSGTYPE_ERROR)
			title = "ERRO";
		else if(this.m_messageData.getMessageType() == AppDefs.DEF_MSGTYPE_WARN)
			title = "ATENCAO";

		setTitle(title);

		FormControlUtil.loadIcon(this);
		
		Point pos = new Point(25, 25);
		Dimension dim = new Dimension(950, 720);
		if(m_parent != null)
		{
			pos = m_parent.getLocation();
			dim = m_parent.getSize();			
		}
		
		MessagePanel messagePanel = new MessagePanel();
		messagePanel.init(
				this, 
				this.m_messageData,
				resultListener);
		
		resizePanel(
			pos.x,
			pos.y,
			dim.width,
			dim.height);

		Container c = getContentPane();
		c.add(messagePanel);
		
		this.show();
	}
	
	public void resizePanel(int x_parent, int y_parent, int w_parent, int h_parent)
	{
		int x = (x_parent + (int)((double)w_parent / 2.0)) - 320;
		int y = (y_parent + (int)((double)h_parent / 2.0)) - 155;
		
		setSize(640, 310);
		setLocation(x, y);
		addWindowListener(this);
	}
	
	/* Events */
	
	@Override
	public void windowClosing(WindowEvent e) 
	{
		this.dispose();
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

	/* Listeners */
	
	@Override
	public void actionResultListener(ResultEvent e) 
	{
		if(m_resultListener != null)
			m_resultListener.actionResultListener(e);
	}
	
	/* Getters/Setters */
	
	public ResultListener getResultListener() {
		return m_resultListener;
	}

	public void setResultListener(ResultListener resultListener) {
		this.m_resultListener = resultListener;
	}

	public MessageDataVO getMessageData() {
		return m_messageData;
	}

	public void setMessageData(MessageDataVO messageData) {
		this.m_messageData = messageData;
	}
	
}
