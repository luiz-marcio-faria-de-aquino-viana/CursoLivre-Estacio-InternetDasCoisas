/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * AppConfig.java
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

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import br.com.tlmv.servermon.utils.FileUtil;
import br.com.tlmv.servermon.utils.XmlUtil;

public class AppConfig 
{
//Private
	private String debugLevelStr = Integer.toString(AppDefs.DEBUG_LEVEL);
	
	private Integer debugLevelVal = AppDefs.DEBUG_LEVEL;
		
//Public
	
	public AppConfig(String fileName) {
		loadDataFromFile(fileName);
	}
	
	/* Methodes */
		
	public void init(String fileName) {
		loadDataFromFile(fileName);
	}
	
	public void loadDataFromFile(String fileName) {
		try {
			String xmlData = FileUtil.readData(fileName);
			loadData(xmlData);
		}
		catch(Exception e) {
			String errmsg = String.format(AppError.ERR_CONFIGURATION_FILE_READ_FAILURE, fileName);
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "loadDataFromFile()", errmsg);
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void loadData(String xmlData)
		throws Exception
	{
		AppContext ctx = AppMain.getApp().getContext();
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		
		InputSource is = new InputSource(new StringReader(xmlData));
	    Document doc = db.parse(is);

	    Node nConfig = doc.getFirstChild();		    

		this.debugLevelStr = XmlUtil.getChildNodeValueByName(nConfig, AppDefs.CFG_DEBUG_LEVEL, null);
		
	}

	/* Methodes */
	
	public void show() {
		System.out.println("\nCONFIGURACOES:");
		System.out.println("  DebugLevel: " + Integer.toString(this.debugLevelVal) + " - " + this.debugLevelStr);
		System.out.println("");
	}
		
	/* Getters/Setters */

	public Integer getDebugLevelVal() {
		return debugLevelVal;
	}

	public void setDebugLevelVal(Integer debugLevelVal) {
		this.debugLevelVal = debugLevelVal;
	}

	public String getDebugLevelStr() {
		return debugLevelStr;
	}

	public void setDebugLevelStr(String debugLevelStr) {
		this.debugLevelStr = debugLevelStr;
	}
	
}
