/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * JSONUtil.java
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
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.tlmv.servermon.AppDefs;
import br.com.tlmv.servermon.data.Evento;
import br.com.tlmv.servermon.data.ObjetoBase;

public class JSONUtil 
{
//Public
	
	public static Object safeObjFromJSON(JSONObject jsonObj, String key, ObjetoBase defaultVal)
	{
		ObjetoBase oResult = defaultVal;
		
		try {
			if( jsonObj.has(key) ) { 
				JSONObject oJSONResult = jsonObj.getJSONObject(key);

				if(oResult != null) {
					if(oResult.getBaseObjectType() == AppDefs.BASE_OBJTYPE_EVENTO) {
						((Evento)oResult).init(oJSONResult);						
					}
				}
			}
		}
		catch(Exception e) { }

		return oResult;
	}
	
	public static JSONArray safeJSONArrFromJSON(JSONObject jsonObj, String key)
	{
		JSONArray jsonArrResult = null;
		
		try {
			if( jsonObj.has(key) ) 
				jsonArrResult = jsonObj.getJSONArray(key);
		}
		catch(Exception e) { }

		return jsonArrResult;
	}
	
	public static JSONObject safeJSONObjFromJSON(JSONObject jsonObj, String key, JSONObject defaultVal)
	{
		JSONObject oJSONResult = defaultVal;
		
		try {
			if( jsonObj.has(key) ) 
				oJSONResult = jsonObj.getJSONObject(key);
		}
		catch(Exception e) { }

		return oJSONResult;
	}
	
	public static String safeStrFromJSON(JSONObject jsonObj, String key, String defaultVal)
	{
		String strResult = defaultVal;
		
		try {
			if( jsonObj.has(key) ) 
				strResult = jsonObj.getString(key);
		}
		catch(Exception e) { }

		return strResult;
	}
	
	public static Date safeDateFromJSON(JSONObject jsonObj, String key, Date defaultVal)
	{
		Date result = defaultVal;
		
		try {
			if( jsonObj.has(key) ) { 
				DateFormat df1 = new SimpleDateFormat(AppDefs.FMT_DATETIME_INV_MASC);
				result = df1.parse(jsonObj.getString(key));
			}
		}
		catch(Exception e) { }

		return result;
	}
	
	public static Integer safeIntFromJSON(JSONObject jsonObj, String key, Integer defaultVal)
	{
		Integer result = defaultVal;
		
		try {
			if( jsonObj.has(key) ) 
				result = jsonObj.getInt(key);
		}
		catch(Exception e) { }

		return result;
	}
	
	public static Long safeLngFromJSON(JSONObject jsonObj, String key, Long defaultVal)
	{
		Long result = defaultVal;
		
		try {
			if( jsonObj.has(key) ) 
				result = jsonObj.getLong(key);
		}
		catch(Exception e) { }

		return result;
	}
	
	public static Double safeDblFromJSON(JSONObject jsonObj, String key, Double defaultVal)
	{
		Double result = defaultVal;
		
		try {
			if( jsonObj.has(key) ) 
				result = jsonObj.getDouble(key);
		}
		catch(Exception e) { }

		return result;
	}
	
}
