/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * FormatUtil.java
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
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.tlmv.harmonicas.AppDefs;
import br.com.tlmv.harmonicas.AppMain;

public class FormatUtil 
{
//Public
	
	public static String formatDataFull(Date dt)
	{
		int dd = dt.getDate();
		int mm = dt.getMonth();
		int yy = dt.getYear() + 1900;
		
		String strMM = AppDefs.LS_MESES[mm];
		
		String str = String.format("%02d de %s de %04d", dd, strMM, yy);
		return str;
	}
	
	public static NumberFormat newNumberFormatPtBr(Integer numdec)
	{
		NumberFormat nf = NumberFormat.getInstance(AppMain.getApp().getPtBr());
		nf.setMaximumFractionDigits(numdec);
		nf.setMinimumFractionDigits(numdec);
		nf.setGroupingUsed(true);
		return nf;
	}
	
	public static NumberFormat newNumberFormatEnUs(Integer numdec)
	{
		NumberFormat nf = NumberFormat.getInstance(AppMain.getApp().getEnUs());
		nf.setMaximumFractionDigits(numdec);
		nf.setMinimumFractionDigits(numdec);
		nf.setGroupingUsed(true);
		return nf;
	}
	
	public static NumberFormat newNumberFormatWithoutGroupSepPtBr(Integer numdec)
	{
		NumberFormat nf = NumberFormat.getInstance(AppMain.getApp().getPtBr());
		nf.setMaximumFractionDigits(numdec);
		nf.setMinimumFractionDigits(numdec);
		nf.setGroupingUsed(false);
		return nf;
	}
	
	public static NumberFormat newNumberFormatWithoutGroupSepEnUs(Integer numdec)
	{
		NumberFormat nf = NumberFormat.getInstance(AppMain.getApp().getEnUs());
		nf.setMaximumFractionDigits(numdec);
		nf.setMinimumFractionDigits(numdec);
		nf.setGroupingUsed(false);
		return nf;
	}

	public static DateFormat newDateFormat(String fmt)
	{
		DateFormat df = new SimpleDateFormat(fmt);
		return df;
	}	
	
	public static String getOnlyNumbers(String strIn)
	{
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < strIn.length(); i++)
		{
			char ch = strIn.charAt(i);
			if((ch >= '0') && (ch <= '9'))
				sb.append(ch);
		}		
		return sb.toString();
	}
	
	public static String formatCNPJ(String strIn)
	{
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < strIn.length(); i++)
		{
			int pos = strIn.length() - i - 1;
			
			char ch = strIn.charAt(pos);
			sb.insert(0, ch);
			
			if(i == 1) 
				sb.insert(0, "-");
			else if(i == 5)
				sb.insert(0, "/");
			else if(i == 8)
				sb.insert(0, ".");
			else if(i == 11)
				sb.insert(0, ".");
		}
		
		return sb.toString();
	}
	
	public static String formatCPF(String strIn)
	{
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < strIn.length(); i++)
		{
			int pos = strIn.length() - i - 1;
			
			char ch = strIn.charAt(pos);
			sb.insert(0, ch);
			
			if(i == 1) 
				sb.insert(0, "-");
			else if(i == 4)
				sb.insert(0, ".");
			else if(i == 7)
				sb.insert(0, ".");
		}
		
		return sb.toString();
	}
	
	public static String formatTelefone7a9Dig(String strIn)
	{
		if(strIn.length() < 7) return strIn;

		// 280-1001
		// 3117-0809
		// 99983-7207
		String prefixo = strIn.substring(0, strIn.length() - 4);
		String sufixo = strIn.substring(strIn.length() - 4, strIn.length());
		
		String str = String.format("%s-%s", prefixo, sufixo);		
		return str;
	}
	
	public static String formatTelefone(String strIn)
	{
		// (21)280-0809
		// (21)3117-0809
		// (21)99983-7207
		if(strIn.length() < 9) 
			return formatTelefone7a9Dig(strIn);
		if((strIn.length() == 9) && (strIn.charAt(0) == '9'))
			return formatTelefone7a9Dig(strIn);
		
		String ddd = strIn.substring(0, 2);
		String prefixo = strIn.substring(2, strIn.length() - 4);
		String sufixo = strIn.substring(strIn.length() - 4, strIn.length());

		String str = String.format("(%s)%s-%s", ddd, prefixo, sufixo);		
		return str;
	}
	
	public static String formatCEP(String strIn)
	{
		if(strIn.length() < 8) return strIn;
		
		// 22621-200
		String prefixo = strIn.substring(0, strIn.length() - 3);
		String sufixo = strIn.substring(strIn.length() - 3, strIn.length());

		String str = String.format("%s-%s", prefixo, sufixo);		
		return str;
	}
	
	public static String formatNumber(int n, int val)
	{
		String str = Integer.toString(val);
		for(int i = str.length(); i < n; i++)
			str = "0" + str;
		return str;
	}
	
}
