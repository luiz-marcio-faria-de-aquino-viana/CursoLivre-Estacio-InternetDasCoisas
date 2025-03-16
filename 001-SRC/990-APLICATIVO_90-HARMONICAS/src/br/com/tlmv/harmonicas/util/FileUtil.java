/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * FileUtil.java
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

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamResult;

import br.com.tlmv.harmonicas.AppDefs;
import br.com.tlmv.harmonicas.vo.FileDataVO;

public class FileUtil 
{
	
	public static boolean launchExternalApplication(String fileName)
	{
		boolean result = false;
		
		try {
			if( fileName.startsWith(AppDefs.DEF_HTTP_PROTOCOL) || fileName.startsWith(AppDefs.DEF_HTTPS_PROTOCOL) )
			{
				URI uri = new URI(fileName);
				
				Desktop oDesktop = Desktop.getDesktop();
				oDesktop.browse(uri);							
			}
			else
			{
				File f = new File(fileName);
				
				Desktop oDesktop = Desktop.getDesktop();
				oDesktop.open(f);			
			}
			
			result = true;
		}
		catch(Exception e) { }	
		
		return result;
	}
	
	public static void listFileDir(List<FileDataVO> lsFileData, String curFullFilePath, String[] extFilter, boolean filesOnly)
	{
		File fCurFullFilePath = new File(curFullFilePath);
		
		String[] arrFilePath = fCurFullFilePath.list();
		for(String newFilePath : arrFilePath)
		{
			String newFullFilePath = String.format("%s/%s", curFullFilePath, newFilePath);
			
			File fNewFullFilePath = new File(newFullFilePath);			
			if( fNewFullFilePath.isDirectory() )
			{
				String newFileName = FileUtil.getFileName(newFullFilePath);
				String newFileExt = FileUtil.getFileExtension(newFullFilePath);
				Date newFileDataModificacao = new Date(fNewFullFilePath.lastModified());
				
				if( !filesOnly )
				{					
					FileDataVO fileData = new FileDataVO(
							newFullFilePath,
							newFileName,
							newFileExt,
							newFileDataModificacao,
							false);
					lsFileData.add(fileData);
				}				
				listFileDir(lsFileData, newFullFilePath, extFilter, filesOnly);
			}
			else
			{
				String newFileName = FileUtil.getFileName(newFullFilePath);
				String newFileExt = FileUtil.getFileExtension(newFullFilePath);
				Date newFileDataModificacao = new Date(fNewFullFilePath.lastModified());
				
				if(ListUtil.findPosInList(extFilter, newFileExt) != -1)
				{
					FileDataVO fileData = new FileDataVO(
						newFullFilePath,
						newFileName,
						newFileExt,
						newFileDataModificacao,
						true);
					lsFileData.add(fileData);
				}
			}
		}
	}
	
	public static String getFileNameWithoutExt(String fullFileName)
	{
		File fFullFileName = new File(fullFileName);

		String fileName = fFullFileName.getName();

		String[] arrFileName = StringUtil.split(fileName, '.');
		String resFileName = "";		
		if(arrFileName.length > 0) {	
			resFileName = arrFileName[0];
			for(int i = 1; i < arrFileName.length - 1; i++)
				resFileName = resFileName + "." + arrFileName[i];
		}
		return resFileName;
	}
	
	public static String getFileName(String fullFileName)
	{
		File fFullFileName = new File(fullFileName);

		String fileName = fFullFileName.getName();
		return fileName;
	}
	
	public static String getFileExtension(String fullFileName)
	{
		File fFullFileName = new File(fullFileName);

		String fileName = fFullFileName.getName();
		
		String[] arrFileExt = StringUtil.split(fileName, '.');
		String fileExt = "";
		if(arrFileExt.length > 0)
			fileExt = arrFileExt[arrFileExt.length - 1];
		return fileExt;
	}
	
	public static boolean isExistFile(String fileName)
	{
		File f = new File(fileName);
		return f.exists();
	}

	public static void renameFile(String srcFileName, String dstFileName)
	{
		try 
		{
			File srcFile = new File(srcFileName);
			File dstFile = new File(srcFileName);
			
			if( srcFile.exists() )
			{
				if( srcFile.isFile() )
					srcFile.renameTo(dstFile);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void copyFile(String srcFileName, String dstFileName)
	{
		FileInputStream sin = null;
		FileOutputStream sout = null;
		
		try 
		{
			File srcFile = new File(srcFileName);
			File dstFile = new File(dstFileName);
			
			if( srcFile.exists() )
			{
				if( srcFile.isFile() )
				{
					sin = new FileInputStream(srcFile);
			        sout = new FileOutputStream(dstFile);

			        byte[] buf = new byte[4096];
			        int numread = -1;
			        while((numread = sin.read(buf, 0, 4096)) != -1) 
			        	sout.write(buf, 0, numread);
			        sout.flush();
			        
					sout.close();					
					sin.close();
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static byte[] readBytes(String fileName)
	{
		byte[] bytes = null;
		try {
			InputStream fin = new FileInputStream(fileName);
			bytes = new byte[fin.available()];
			fin.read(bytes);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}
	
	public static String readData(String fileName)
	{
		StringBuilder sb = new StringBuilder();
		
		BufferedReader fin = null;		
		try
		{
			fin = new BufferedReader(new FileReader(fileName));
			String sbuf = null;
			while((sbuf = fin.readLine()) != null)
				sb.append(sbuf);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				if(fin != null) fin.close();				
			}
			catch(Exception e1)
			{
				
			}
		}
		
		return sb.toString();
	}
	
	public static String readData(File f)
	{
		StringBuilder sb = new StringBuilder();
		
		BufferedReader fin = null;		
		try
		{
			fin = new BufferedReader(new FileReader(f));
			String sbuf = null;
			while((sbuf = fin.readLine()) != null)
				sb.append(sbuf);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				if(fin != null) fin.close();				
			}
			catch(Exception e1)
			{
				
			}
		}
		
		return sb.toString();
	}
	
	public static boolean writeData(String fileName, String strData, boolean removeLines)
	{
		boolean rscode = false;
		
		if( removeLines )
			strData = StringUtil.removeLines(strData);
		
		BufferedWriter fout = null;		
		try
		{
			fout = new BufferedWriter(new FileWriter(fileName));
			fout.write(strData);

			rscode = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				if(fout != null) fout.close();				
			}
			catch(Exception e1)
			{
				
			}
		}
		
		return rscode;
	}
		
	public static boolean writeData(String fileName, byte[] arrData, int arrSz)
	{
		boolean rscode = false;
		
		FileOutputStream fout = null;		
		try
		{
			fout = new FileOutputStream(new File(fileName));
			fout.write(arrData, 0, arrSz);

			rscode = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				if(fout != null) fout.close();				
			}
			catch(Exception e1)
			{
				
			}
		}
		
		return rscode;
	}
	
	public static boolean writeData(String fileName, ArrayList<String> lsData)
	{
		boolean rscode = false;
		
		BufferedWriter fout = null;		
		try
		{
			fout = new BufferedWriter(new FileWriter(fileName));
			for(String strData : lsData)
				fout.write(strData);

			rscode = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				if(fout != null) fout.close();				
			}
			catch(Exception e1)
			{
				
			}
		}
		
		return rscode;
	}
	
	public static boolean writeData(File f, String strData)
	{
		boolean rscode = false;
		
		BufferedWriter fout = null;		
		try
		{
			fout = new BufferedWriter(new FileWriter(f));
			fout.write(strData);

			rscode = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				if(fout != null) fout.close();				
			}
			catch(Exception e1)
			{
				
			}
		}
		
		return rscode;
	}
	
	public static boolean writeData(File f, byte[] arrData, int arrSz)
	{
		boolean rscode = false;
		
		FileOutputStream fout = null;		
		try
		{
			fout = new FileOutputStream(f);
			fout.write(arrData, 0, arrSz);

			rscode = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				if(fout != null) fout.close();				
			}
			catch(Exception e1)
			{
				
			}
		}
		
		return rscode;
	}
	
	public static boolean writeData(File f, ArrayList<String> lsData)
	{
		boolean rscode = false;
		
		BufferedWriter fout = null;		
		try
		{
			fout = new BufferedWriter(new FileWriter(f));
			for(String strData : lsData)
				fout.write(strData);

			rscode = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				if(fout != null) fout.close();				
			}
			catch(Exception e1)
			{
				
			}
		}
		
		return rscode;
	}
	
}
