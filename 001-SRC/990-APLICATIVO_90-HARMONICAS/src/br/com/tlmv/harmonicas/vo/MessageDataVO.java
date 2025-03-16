/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * MessageDataVO.java
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

package br.com.tlmv.harmonicas.vo;

import java.io.StringReader;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import br.com.tlmv.harmonicas.AppDefs;

public class MessageDataVO 
{
//Private
	private int messageType;
	private int debugLevel;
	private Date eventDate;
	private String eventSubject;
	private String eventMessage;
	private String className;
	private String action;
		
//Public
	
	public MessageDataVO()
	{
		reset();
	}
	
	public MessageDataVO(
		int messageType,
		int debugLevel,
		Date eventDate,
		String eventSubject,
		String eventMessage,
		String className,
		String action)
	{
		this.messageType = messageType;
		this.debugLevel = debugLevel;
		this.eventDate = eventDate;
		this.eventSubject = eventSubject;
		this.eventMessage = eventMessage;
		this.className = className;
		this.action = action;
	}
	
	/* Methodes */
	
	public void reset()
	{
		messageType = AppDefs.DEF_MSGTYPE_NONE;
		debugLevel = AppDefs.DEBUG_LEVEL99;
		eventDate = new Date();
		eventSubject = "";
		eventMessage = "";
		className = "";
		action = AppDefs.DEF_BTN_ACTION_MESSAGE_NONE;		
	}

	/* Getters/Setters */

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public int getDebugLevel() {
		return debugLevel;
	}

	public void setDebugLevel(int debugLevel) {
		this.debugLevel = debugLevel;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventSubject() {
		return eventSubject;
	}

	public void setEventSubject(String eventSubject) {
		this.eventSubject = eventSubject;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getEventMessage() {
		return eventMessage;
	}

	public void setEventMessage(String eventMessage) {
		this.eventMessage = eventMessage;
	}	

}
