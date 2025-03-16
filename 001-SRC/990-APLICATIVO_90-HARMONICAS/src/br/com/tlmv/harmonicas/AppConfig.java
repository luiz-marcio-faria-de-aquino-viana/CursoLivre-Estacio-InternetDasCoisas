/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * AppConfig.java
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

import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import br.com.tlmv.harmonicas.util.FileUtil;
import br.com.tlmv.harmonicas.util.XmlUtil;

public class AppConfig 
{
//Private
	private String m_nomeAutorProjeto;
	
//Prublic
	
	public AppConfig(String fileName)
		throws Exception
	{
		String xmlData = FileUtil.readData(fileName);
		loadData(xmlData);
	}
	
	/* Methodes */
		
	public void loadDataFromFile(String fileName)
		throws Exception
	{
		String xmlData = FileUtil.readData(fileName);
		loadData(xmlData);
	}
	
	public void loadData(String xmlData)
		throws Exception
	{
		AppContext ctx = AppMain.getApp().getContext();
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		
		InputSource is = new InputSource(new StringReader(xmlData));
	    Document doc = db.parse(is);

	    //LOAD: Config Data
	    
	    Node nConfig = doc.getFirstChild();		    

	    m_nomeAutorProjeto = XmlUtil.getChildNodeValueByName(nConfig, AppDefs.DEF_CFG_NOME_AUTOR_PROJETO, false, null);
	    
	}
	
	public void saveData(String fileName, String xmlData)
	{
		try {
			DateFormat df = new SimpleDateFormat(AppDefs.DEF_DATETIME_FILE_MASC);

			Date dataAtual = new Date();
			
			if( FileUtil.isExistFile(fileName) )
			{
				String bakFileName = String.format("%s.%s", fileName, df.format(dataAtual)); 
				FileUtil.renameFile(fileName, bakFileName);
			}
			FileUtil.writeData(fileName, xmlData, false);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public String toXml()
	{
		StringBuilder sb = new StringBuilder();
		
	    //SAVE: Config Data
		
		sb.append(XmlUtil.createXmlElem(AppDefs.DEF_CFG_NOME_AUTOR_PROJETO, this.m_nomeAutorProjeto, 1, false));
		
		String result = XmlUtil.createXmlDocElemISO8859(AppDefs.DEF_CFG_CONFIGURACAO, sb.toString());
		return result;
	}
	
	/* Getters/Setters */

	public String getNomeAutorProjeto() {
		return m_nomeAutorProjeto;
	}

	public void setNomeAutorProjeto(String nomeAutorProjeto) {
		this.m_nomeAutorProjeto = nomeAutorProjeto;
	}

}
