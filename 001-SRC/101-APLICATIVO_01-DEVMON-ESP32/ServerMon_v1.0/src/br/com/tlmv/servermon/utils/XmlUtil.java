/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * XmlUtil.java
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

package br.com.tlmv.servermon.utils;

import java.text.NumberFormat;
import java.util.ArrayList;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlUtil 
{
//Public
	
	public static String getAttrValueByName(Node oNode, String name, String defaultVal)
	{
		String result = "";

		if(defaultVal != null)
			result = defaultVal;
		
		NamedNodeMap lsAttr = oNode.getAttributes();

		Node oAttr = lsAttr.getNamedItem(name);
		if(oAttr != null) {
			try {
				result = oAttr.getFirstChild().getTextContent();
			}
			catch(Exception e) { }
		}
			
		return result;
	}
	
	public static Node getChildNodeByName(NodeList lsChild, String name)
	{
		for(int i = 0; i < lsChild.getLength(); i++) {
			Node nNode = lsChild.item(i);
			
			if( name.equalsIgnoreCase(nNode.getNodeName()) )
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
		
		for(int i = 0; i < lsChild.getLength(); i++) {
			Node nNode = lsChild.item(i);
			
			if( name.equalsIgnoreCase(nNode.getNodeName()) )
				lsResult.add(nNode);
		}
		return lsResult;
	}

	public static ArrayList<Node> getListChildNodeByName(Node oNode, String name)
	{
		if(oNode == null) 
			return new ArrayList<Node>();
		
		NodeList lsChild = oNode.getChildNodes();
		return getListChildNodeByName(lsChild, name);
	}
	
	public static String getChildNodeValueByName(NodeList lsChild, String name, String defaultVal) {
		String result = "";

		try {
			for(int i = 0; i < lsChild.getLength(); i++) {
				Node nNode = lsChild.item(i);
				
				if( name.equalsIgnoreCase(nNode.getNodeName()) ) {
					try {
						result = nNode.getFirstChild().getTextContent();
					}
					catch(Exception e) {
						if(defaultVal != null)
							result = defaultVal;
						else
							result = "";
					}					
					return result;
				}
			}			
		}
		catch(Exception e) { }

		if(defaultVal != null)
			result = defaultVal;
		else
			result = "";
		
		return result;
	}
	
	public static double getChildNodeValueByName(NumberFormat nf, NodeList lsChild, String name, double defaultVal) {
		double result = defaultVal;

		try {
			for(int i = 0; i < lsChild.getLength(); i++) {
				Node nNode = lsChild.item(i);
				
				if( name.equalsIgnoreCase(nNode.getNodeName()) ) {
					try {
						result = StringUtil.safeDbl(nf, nNode.getFirstChild().getTextContent());
					}
					catch(Exception e) { }
				}
			}			
		}
		catch(Exception e) { }

		return result;
	}

	public static String getChildNodeValueByName(Node oNode, String name, String defaultVal)
	{
		NodeList lsChild = oNode.getChildNodes();
		return getChildNodeValueByName(lsChild, name, defaultVal);
	}

	public static double getChildNodeValueByName(NumberFormat nf, Node oNode, String name, double defaultVal)
	{
		NodeList lsChild = oNode.getChildNodes();
		return getChildNodeValueByName(nf, lsChild, name, defaultVal);
	}
	
    public static Node getChildNode(Node node, String name) {
        NodeList ls = node.getChildNodes();
        for(int i = 0; i < ls.getLength(); i++) {
        	Node n = ls.item(i);
        	
            if ( n.getNodeName().equals(name) )
                return n;
        }
        return null;
    }
		
}