/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * StringUtil.java
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

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public class StringUtil 
{
//Public
	
	// FORMAT_FUNCTIONS

	public static NumberFormat newDecimalFormatPtBR(int fractionDigits)
	{
		NumberFormat nf = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
		nf.setMaximumFractionDigits(fractionDigits);
		nf.setMinimumFractionDigits(2);
		return nf;
	}
	
	public static NumberFormat newDecimalFormatEnUS(int fractionDigits)
	{
		NumberFormat nf = NumberFormat.getNumberInstance(new Locale("en", "US"));
		nf.setMaximumFractionDigits(fractionDigits);
		nf.setMinimumFractionDigits(2);
		nf.setGroupingUsed(false);
		return nf;
	}
	
	// SAFE_CONVERTION_FUNCTIONS
	
	public static String safeStr(String strIn) {
		if(strIn != null) {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < strIn.length(); i++) {
				char ch = strIn.charAt(i);
				if(ch > 255) 
					ch += ' ';
				sb.append(ch);
			}
			return sb.toString();
		}
		return "";
	}
	
	public static Date safeDate(DateFormat df, String strIn) {
		Date result = new Date(1900, 0, 1);
		
		try {
			result = df.parse(strIn);
		}
		catch(Exception e) { }
		
		return result;
	}
	
	public static double safeDbl(NumberFormat nf, String strIn) {
		double result = 0.0;
		
		try {
			result = nf.parse(strIn).doubleValue();
		}
		catch(Exception e) { }
		
		return result;
	}
	
	public static int safeInt(String strIn) {
		int result = 0;
		
		try {
			result = Integer.parseInt(strIn);
		}
		catch(Exception e) { }
		
		return result;
	}
	
	public static long safeLng(String strIn) {
		long result = 0;
		
		try {
			result = Long.parseLong(strIn);
		}
		catch(Exception e) { }
		
		return result;
	}

}
