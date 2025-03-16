/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * Fmt.java
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

public class Fmt 
{
//Public
		
	public static String bToStr(Boolean val)
	{
		if(val == null) return "N";
		
		String strval = ( val ) ? "S" : "N";
		return strval;
	}
		
	public static String intToStr(Integer val)
	{
		if(val == null) return "0";
		
		String strval = Integer.toString(val);
		return strval;
	}
	
	public static String lngToStr(Long val)
	{
		if(val == null) return "0";

		String strval = Long.toString(val);
		return strval;
	}
	
	public static String dblToStr(NumberFormat nf, Double val)
	{
		if(val == null) return nf.format(0.0);

		String strval = nf.format(val);
		return strval;
	}
	
	public static String dateToStr(DateFormat df, Date val)
	{
		if(val == null) return df.format(val);

		String strval = df.format(val);
		return strval;
	}
	
}
