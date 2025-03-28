/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * XmlUtil.java
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

package br.com.tlmv.harmonicas.util;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.com.tlmv.harmonicas.AppDefs;

public class XmlUtil 
{
	
	public static String getXmlBody(String xmlData)
	{
		String strXmlData = StringUtil.replaceAll(
			xmlData, 
			AppDefs.defXmlVersion, 
			"");
		return strXmlData;
	}

	public static String getXmlDataEncode(String xmlData)
	{
		String strTmp = StringUtil.replaceAll(
			xmlData, 
			"\"", 
			"&quot;");

		strTmp = StringUtil.replaceAll(
			strTmp, 
			"'", 
			"&quot;");

		strTmp = StringUtil.replaceAll(
			strTmp, 
			"<", 
			"&lt;");

		strTmp = StringUtil.replaceAll(
			strTmp, 
			">", 
			"&gt;");
		
		strTmp = StringUtil.stripAccents(strTmp, false);
		return strTmp;		
	}

	public static String getXmlDataDecode(String xmlData)
	{
		String strTmp = StringUtil.replaceAll(
			xmlData, 
			"&quot;", 
			"'");

		strTmp = StringUtil.replaceAll(
			strTmp, 
			"&lt;", 
			"<");

		strTmp = StringUtil.replaceAll(
			strTmp, 
			"&gt;", 
			">");
		
		strTmp = StringUtil.stripAccents(strTmp, false);
		return strTmp;		
	}

	public static String getAttrValueByName(Node oNode, String name, boolean withEncode, String defaultVal)
	{
		String result = "";

		if(defaultVal != null)
			result = defaultVal;
		
		NamedNodeMap lsAttr = oNode.getAttributes();

		Node oAttr = lsAttr.getNamedItem(name);
		if(oAttr != null) {
			try {
				if( withEncode )
					result = EncodeUtil.decode(oAttr.getFirstChild().getTextContent());
				else
					result = oAttr.getFirstChild().getTextContent();
			}
			catch(Exception e) { }
		}
			
		return result;
	}
	
	public static Node getChildNodeByName(NodeList lsChild, String name)
	{
		for(int i = 0; i < lsChild.getLength(); i++)
		{
			Node nNode = lsChild.item(i);
			
			String[] arr = StringUtil.split(nNode.getNodeName(), ':');
			String tk = arr[arr.length - 1];
			if( name.equalsIgnoreCase(tk) )
				return nNode;
		}
		return null;
	}

	public static Node getChildNodeByName(Node oNode, String name)
	{
		NodeList lsChild = oNode.getChildNodes();
		return getChildNodeByName(lsChild, name);
	}

	public static ArrayList<Node> getListChildNodeByName(NodeList lsChild, String name)
	{
		ArrayList<Node> lsResult = new ArrayList<Node>();
		
		for(int i = 0; i < lsChild.getLength(); i++)
		{
			Node nNode = lsChild.item(i);
			
			String[] arr = StringUtil.split(nNode.getNodeName(), ':');
			String tk = arr[arr.length - 1];
			if( name.equalsIgnoreCase(tk) )
				lsResult.add(nNode);
		}
		return lsResult;
	}

	public static ArrayList<Node> getListChildNodeByName(Node oNode, String name)
	{
		if(oNode == null) return new ArrayList<Node>();
		
		NodeList lsChild = oNode.getChildNodes();
		return getListChildNodeByName(lsChild, name);
	}
	
	public static String getChildNodeValueByName(NodeList lsChild, String name, boolean withEncode, String defaultVal)
	{
		String result = "";

		try
		{
			for(int i = 0; i < lsChild.getLength(); i++)
			{
				Node nNode = lsChild.item(i);
				
				String[] arr = StringUtil.split(nNode.getNodeName(), ':');
				String tk = arr[arr.length - 1];
				if( name.equalsIgnoreCase(tk) )
				{
					try
					{
						if( withEncode )
							result = EncodeUtil.decode(nNode.getFirstChild().getTextContent());
						else
							result = nNode.getFirstChild().getTextContent();
					}
					catch(Exception e)
					{
						if(defaultVal != null)
							result = defaultVal;
						else
							result = "";
					}
					
					return result;
				}
			}			
		}
		catch(Exception e)
		{
			
		}

		if(defaultVal != null)
			result = defaultVal;
		else
			result = "";
		
		return result;
	}
	
	public static double getChildNodeValueByName(NumberFormat nf, NodeList lsChild, String name, boolean withEncode, double defaultVal)
	{
		double result = defaultVal;

		try
		{
			for(int i = 0; i < lsChild.getLength(); i++)
			{
				Node nNode = lsChild.item(i);
				
				String[] arr = StringUtil.split(nNode.getNodeName(), ':');
				String tk = arr[arr.length - 1];
				if( name.equalsIgnoreCase(tk) )
				{
					try {
						if( withEncode )
							result = StringUtil.safeDbl(nf, EncodeUtil.decode(nNode.getFirstChild().getTextContent()));
						else
							result = StringUtil.safeDbl(nf, nNode.getFirstChild().getTextContent());
					}
					catch(Exception e) {

					}
				}
			}			
		}
		catch(Exception e)
		{
			
		}

		return result;
	}

	public static String getChildNodeValueByName(Node oNode, String name, boolean withEncode, String defaultVal)
	{
		NodeList lsChild = oNode.getChildNodes();
		return getChildNodeValueByName(lsChild, name, withEncode, defaultVal);
	}

	public static double getChildNodeValueByName(NumberFormat nf, Node oNode, String name, boolean withEncode, double defaultVal)
	{
		NodeList lsChild = oNode.getChildNodes();
		return getChildNodeValueByName(nf, lsChild, name, withEncode, defaultVal);
	}
	
	// Create XML Element With Level
	
	public static String createXmlElem(String tagname, String val, int level, boolean bToUpperCase)
	{
		String strVal = StringUtil.stripAccents(val, bToUpperCase);

		String str = "";
		for(int i = 0; i < level; i++)
			str += "\t";
		
		//str += String.format("<%s><![CDATA[%s]]></%s>\n", tagname, strVal, tagname);
		str += String.format("<%s>%s</%s>\n", tagname, strVal, tagname);
		return str;
	}
	
	public static String createXmlElem(String tagname, int val, int level)
	{
		String strVal = StringUtil.valToStr(val);
		
		String str = "";
		for(int i = 0; i < level; i++)
			str += "\t";

		//str += String.format("<%s><![CDATA[%s]]></%s>\n", tagname, strVal, tagname);
		str += String.format("<%s>%s</%s>\n", tagname, strVal, tagname);
		return str;
	}
	
	public static String createXmlElem(String tagname, long val, int level)
	{
		String strVal = StringUtil.valToStr(val);
		
		String str = "";
		for(int i = 0; i < level; i++)
			str += "\t";		

		//str += String.format("<%s><![CDATA[%s]]></%s>\n", tagname, strVal, tagname);
		str += String.format("<%s>%s</%s>\n", tagname, strVal, tagname);
		return str;
	}
		
	public static String createXmlElem(NumberFormat nf, String tagname, double val, int level)
	{
		String strVal = StringUtil.valToStr(nf, val);


		String str = "";
		for(int i = 0; i < level; i++)
			str += "\t";
				
		//str += String.format("<%s><![CDATA[%s]]></%s>\n", tagname, strVal, tagname);
		str += String.format("<%s>%s</%s>\n", tagname, strVal, tagname);
		return str;
	}
	
	public static String createXmlElem(String tagname, boolean val, int level)
	{
		String strVal = ( val ) ? "S" : "N";
		
		String str = "";
		for(int i = 0; i < level; i++)
			str += "\t";		

		//str += String.format("<%s><![CDATA[%s]]></%s>\n", tagname, strVal, tagname);
		str += String.format("<%s>%s</%s>\n", tagname, strVal, tagname);
		return str;
	}
	
	public static String createXmlElem(DateFormat df, String tagname, Date val, int level)
	{
		String strVal = StringUtil.valToStr(df, val);

		String str = "";
		for(int i = 0; i < level; i++)
			str += "\t";
				
		str += String.format("<%s>%s</%s>\n", tagname, strVal, tagname);
		return str;
	}
	
	// Create XML Data Element With Level
	
	public static String createXmlElemData(String xmlNs, String tagname, String strVal, int level)
	{
		String newStrVal = StringUtil.stripAccents(strVal, true);
		
		String str = "";
		for(int i = 0; i < level; i++)
			str += "\t";
		
		//str += String.format("<%s><![CDATA[%s]]></%s>\n", tagname, strVal, tagname);
		str += String.format("<%s xmlns='%s'>%s</%s>\n", tagname, xmlNs, newStrVal, tagname);
		return str;
	}
	
	public static String createXmlElemData(String tagname, String strVal, int level)
	{
		String newStrVal = StringUtil.stripAccents(strVal, true);
		
		String str = "";
		for(int i = 0; i < level; i++)
			str += "\t";
		
		//str += String.format("<%s><![CDATA[%s]]></%s>\n", tagname, strVal, tagname);
		str += String.format("<%s>%s</%s>\n", tagname, newStrVal, tagname);
		return str;
	}
	
	// Create XML Element START/END Tags
	
	public static String createXmlElemStartEndTags(String tagname, String val, int level)
	{
		String strTab = "";
		for(int i = 0; i < level; i++)
			strTab += "\t";
				
		String str = String.format("%s<%s>\n%s%s</%s>\n", strTab, tagname, val, strTab, tagname);
		return str;
	}	
	
	// Create XML Document Element
	
	public static String createXmlDocElemISO8859(String tagname, String val)
	{
		String str = String.format("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n<%s>\n%s</%s>\n", tagname, val, tagname);
		return str;
	}	
	
	public static String createXmlDocElemUTF8(String tagname, String val)
	{
		String str = String.format("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<%s>\n%s</%s>\n", tagname, val, tagname);
		return str;
	}	
	
    public static Node getChildNode(Node node, String name)
    {
        NodeList ls = node.getChildNodes();
        for(int i = 0; i < ls.getLength(); i++)
        {
        	Node n = ls.item(i);
        	
            if ( n.getNodeName().equals(name) )
                return n;
        }
        return null;
    }

    public static String serializeNode(Node node)
    {
        String s = "";
        
        if( node.getNodeName().equals("#text") ) return node.getTextContent();
        
        s+= "<" + node.getNodeName()+" ";
        
        NamedNodeMap attributes = node.getAttributes();
        if( attributes!= null ){
            for( int i = 0;i<attributes.getLength();i++ ){
                s+=attributes.item(i).getNodeName()+"=\""+attributes.item(i).getNodeValue()+"\"";
            }
        }
        
        NodeList childs = node.getChildNodes();
        if( childs == null || childs.getLength() == 0 ){
            s+= "/>";
            return s;
        }
        s+=">";
        
        for( int i = 0;i<childs.getLength();i++ )
            s+=serializeNode(childs.item(i));
        s+= "</"+node.getNodeName()+">";
        
        return s;
    } 
    
    public static String innerText(String xml, String tagName)
    {
    	StringBuilder result = new StringBuilder();
    	
    	String startTag = "<" + tagName;
    	String endTag = "</" + tagName + ">";
    	
    	int i_pos = xml.indexOf(startTag);
    	int f_pos = xml.indexOf(endTag);
    	
    	int state = 0;
    	for(int i = i_pos; i < f_pos; i++)
    	{
    		char c = xml.charAt(i);
    		
    		if(state == 0) {
    			if(c == '>') state = 1;
    		}
    		else if(state == 1) {
    			result.append(c);
    		}
    	}
    		
    	return result.toString();
    }
		
}
