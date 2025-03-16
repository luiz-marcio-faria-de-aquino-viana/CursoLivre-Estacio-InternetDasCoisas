/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * EncodeUtil.java
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

import java.net.URLDecoder;
import java.net.URLEncoder;

public class EncodeUtil 
{
//Public

	public static String urlEscape(String str)
	{
	    str = str.replaceAll("'", "&#39;");
	    str = str.replaceAll("\"", "&quot;");
	    str = str.replaceAll("\n", " ");
	    str = str.replaceAll("<", "&lt;");
	    str = str.replaceAll(">", "&gt;");
	    return str;
	}
	
	public static String urlEncode(String str)
	{
		String result = "";
		if(str != null)
		{
			try {
				result = URLEncoder.encode(str, "utf-8");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static String urlDecode(String str)
	{
		String result = "";
		if(str != null)
		{
			try {
				result = URLDecoder.decode(str, "utf-8");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return result;
	}
	
}
