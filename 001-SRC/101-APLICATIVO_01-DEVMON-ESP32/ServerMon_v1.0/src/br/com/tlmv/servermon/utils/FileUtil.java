/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * FileUtil.java
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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;

import br.com.tlmv.servermon.AppDefs;
import br.com.tlmv.servermon.AppMain;

public class FileUtil 
{
//Public
	
	public static String getFileExt(String fullFileName) {
		String[] strFileNameParts = fullFileName.split("."); 
		if(strFileNameParts.length > 0) {
			String fileExt = strFileNameParts[strFileNameParts.length - 1];
			return fileExt;
		}
		return "";
	}

	public static String getMimeType(String fullFileName) {
		String strResult = AppDefs.MIMETYPE_BLOB;
		
		String fileExt = FileUtil.getFileExt(fullFileName);
		if( AppDefs.EXT_JPG.equals(fileExt) )
			strResult = AppDefs.MIMETYPE_JPG;
		
		if( AppDefs.EXT_JPEG.equals(fileExt) )
			strResult = AppDefs.MIMETYPE_JPEG;
		
		if( AppDefs.EXT_PNG.equals(fileExt) )
			strResult = AppDefs.MIMETYPE_PNG;
		
		return strResult;
	}
	
	public static boolean saveFile(String fileName, byte[] data)
		throws Exception
	{
		try {
			FileOutputStream f = new FileOutputStream(fileName);
			f.write(data);
			f.close();
			return true;
		} 
		catch (Exception e) {
			AppMain.getApp().getErr().writeError(FileUtil.class.getName(), "saveFile", e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
	
	public static String readData(String f) {
		StringBuilder sb = new StringBuilder();
		
		BufferedReader fin = null;		
		try {
			fin = new BufferedReader(new FileReader(f));
			String sbuf = null;
			while((sbuf = fin.readLine()) != null)
				sb.append(sbuf);
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(FileUtil.class.getName(), "readData", e.getMessage());
			e.printStackTrace();
		}
		finally {
			try {
				if(fin != null) fin.close();				
			}
			catch(Exception e1) { }
		}
		
		return sb.toString();
	}
	
	public static ArrayList<String> readDataToArrayList(String f) {
		ArrayList<String> lsStr = new ArrayList<String>();
		
		BufferedReader fin = null;		
		try {
			fin = new BufferedReader(new FileReader(f));
			String sbuf = null;
			while((sbuf = fin.readLine()) != null)
				lsStr.add(sbuf);
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(FileUtil.class.getName(), "readData()", e.getMessage());
			e.printStackTrace();
		}
		finally {
			try {
				if(fin != null) fin.close();				
			}
			catch(Exception e1) { }
		}
		
		return lsStr;
	}

	public static String[] readDataToArray(String f) {
		ArrayList<String> lsStr = readDataToArrayList(f);
		
		String[] arrStr = lsStr.toArray(new String[lsStr.size()]);
		return arrStr;
	}

	public static byte[] readBinaryData(String fileName) {
		byte[] arrByte = null;
		try {
			InputStream fin = new FileInputStream(fileName);
			arrByte = new byte[fin.available()];
			fin.read(arrByte);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return arrByte;
	}
	
}
