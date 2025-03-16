/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * StringUtil.java
 * Autor:
 * - Luiz Marcio Faria de Aquino Viana, Pós-D.Sc. - Engenheiro, 08/02/2023
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

package br.com.tlmv.sensormovapp.util;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.security.MessageDigest;

public class StringUtil {
//Public
	
	public static String getHead(String s, char c) {
		StringBuffer result = new StringBuffer();
		int pos = 0;
		while(pos < s.length()) {
			char ch = s.charAt(pos);
			if(ch == c) break;
			result.append(ch);
			pos++;
		}
		return result.toString();
	}

	public static String getTail(String s, char c) {
		int pos = 0;
		while(pos < s.length()) {
			char ch = s.charAt(pos);
			if(ch == c) 
				return s.substring(pos);
			pos++;
		}
		return "";
	}

	public static String getOnlyNumbers(String s) {
		StringBuilder result = new StringBuilder();
		if(s != null) {
			for(int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if((c >= '0') && (c <= '9'))
					result.append(c);
			}
		}
		return result.toString();
	}

	public static String[] split(String s, char c) {
		ArrayList<String> result = new ArrayList<String>();
		String parseString = new String(s);
		boolean test = (parseString.length() > 0);
		while( test ) {
			String tk = StringUtil.getHead(parseString, c);
			result.add(tk);
			parseString = StringUtil.getTail(parseString, c);
			if(parseString.length() > 0) {
				parseString = parseString.substring(1);
			}
			else {
				test = false;
			}
		}
		return result.toArray(new String[result.size()]);						
	}

	public static String arrayToString(String arr[]) {
		StringBuffer sbuf = new StringBuffer();
		for(int i = 0; i < arr.length; i++) {
			if(sbuf.length() == 0) {
				sbuf.append(arr[i]);
			}
			else {
				sbuf.append(",").append(arr[i]);
			}
		}
		return sbuf.toString();
	}

	public static String timeFormat(Double hourWithFrac) {
		double hour = Math.floor(hourWithFrac);
		double min = Math.floor((hourWithFrac - hour) * 60);
		return String.format("%02.0f:%02.0f", hour, min);
	}
	
	public static double parseTime(String s) {
		double result = 0.0;

		try {
			String[] arr = split(s, ':');
			
			double hh = Double.parseDouble(arr[0]);
			double mm = 0.0;
			if(arr.length > 1)
				mm = Double.parseDouble(arr[1]) / 60.0;
			
			result = (hh + mm);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String safeStr(String s) {
		if(s != null) {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < s.length(); i++) {
				char ch = s.charAt(i);
				if(ch > 255) {
					ch += ' ';
				}
				sb.append(ch);
			}
			return sb.toString();
		}
		return "";
	}
		
	public static String safeStr2(String s) {
		if(s != null)
			return s;
		return "";
	}
	
	public static String strField(String str, int len) {
		StringBuilder result = new StringBuilder();

		str = str.toUpperCase();
		for(int i = 0; i < str.length(); i++) {
			if(i >= len) break;
			
			char c_src = str.charAt(i);
			if( ((c_src >= '0') && (c_src <= '9')) || ((c_src >= 'A') && (c_src <= 'Z')) ) {
				result.append(c_src);
			}
			else {
				result.append(' ');
			}
		}

		while(result.length() < len) {
			result.append(' ');
		}

		return result.toString();
	}
	
	public static String valField(Double val, int len, int dec) {
		long val_res = (long)Math.floor(val * Math.pow(10.0, dec)); 		
		String str = Long.toString(val_res);
		while(str.length() < len)
			str = "0" + str;
		return str;
	}
		
	public static Boolean isEmpty(String str) {
		return ( (str == null) || "".equals(str) );
	}
		
    public static String strToMD5(String str) {
    	String result = "";
    	
    	try {
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        md.update(str.getBytes());
	 
	        byte byteData[] = md.digest();
	 
	        //convert the byte to hex format method 1
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	        	sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
	 
	        //convert the byte to hex format method 2
	        StringBuffer hexString = new StringBuffer();
	    	for (int i=0;i<byteData.length;i++) {
	    		String hex=Integer.toHexString(0xff & byteData[i]);
	   	     	if(hex.length()==1) hexString.append('0');
	   	     	hexString.append(hex);
	    	}
	    	
	    	result = hexString.toString();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return result;
    }
	
    public static Date parseDate(String s, String fmt) {
		DateFormat df = new SimpleDateFormat(fmt);

		Date d = null;			
		try {
			d = df.parse(s);
		}
		catch(Exception e) { }
		return d;
    }
    
    public static int parseInt(String s) {
		Integer i = 0;
		try {
			i = Integer.parseInt(s); 
		}
		catch(Exception e) { }
		return i;
    }
	
    public static double parseDouble(NumberFormat nf, String s) {
		Double d = 0.0;
		try {
			d = nf.parse(s).doubleValue(); 
		}
		catch(Exception e) { }
		return d;
    }
    
	public static String fillLeft(String str, int len, char c) {
		StringBuilder result = new StringBuilder();
		result.append(str);
		while(result.length() < len)
			result.insert(0, c);
		return result.toString();
	}

	public static String fillRight(String str, int len, char c) {
		StringBuilder result = new StringBuilder();
		result.append(str);
		while(result.length() < len)
			result.append(c);
		return result.toString();
	}

	public static String valToStr(Date val) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat hf = new SimpleDateFormat("HH:mm:ss");

		if(val == null)
			return null;
		return df.format(val) + "T" + hf.format(val);
	}
    
	public static String valToStr(Integer val, Integer len) {
		if(val == null)
			return null;
		return Integer.toString(val);
	}
    
	public static String valToStr(String val, Integer len) {
		if(val == null)
			return null;
		return val.substring(0, Math.min(len, val.length()));
	}
    
	public static String valToStr(Double val, Integer len, Integer prec) {
		if(val == null)
			return null;
		
		NumberFormat nf = FormatUtil.newDecimalFormat(prec);
		nf.setGroupingUsed(false);
		return nf.format(val);
	}
    
}
