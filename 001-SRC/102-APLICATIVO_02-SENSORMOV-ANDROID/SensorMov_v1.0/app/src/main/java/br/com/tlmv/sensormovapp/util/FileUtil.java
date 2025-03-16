/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * FileUtil.java
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

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import br.com.tlmv.sensormovapp.Defs;
import br.com.tlmv.sensormovapp.MainActivity;

public class FileUtil {

	public static byte[] readFile(String filePath) {
		ByteArrayOutputStream result = new ByteArrayOutputStream();

		File f = null;
		BufferedInputStream fin = null;
		
		byte[] buf = new byte[4096];
		int numbytes = -1;		
		
        try {
	        f = new File(filePath);
	        fin = new BufferedInputStream(new FileInputStream(f));
	        while((numbytes = fin.read(buf)) > 0) {
	        	result.write(buf, 0, numbytes);
	        }
        }
        catch(Exception e2) {
        	e2.printStackTrace();
        }
        finally {
        	try {
	        	if(fin != null) fin.close();
        	}
	        catch(Exception e3) {
	        	e3.printStackTrace();
	        }
        }
        
        return result.toByteArray();
	}
	
	public static String getMimeType(String ext) {
		for(int i = 0; i < Defs.MIME_TYPE.length; i++) {
			String[] arr = Defs.MIME_TYPE[i].split(";");
			if(ext.compareToIgnoreCase(arr[0]) == 0)
				return arr[1];
		}
		return "application/octet-stream";
	}

	public static String getFilePath(Context context, String fileName) {
		File f = context.getFileStreamPath(fileName);
		return f.getAbsolutePath();
	}

    public static String getPath(Uri uri) {
        if( uri == null ) {
            return null;
        }

        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = MainActivity.getApp().managedQuery(uri, projection, null, null, null);
        if( cursor != null ) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        return uri.getPath();
	}	
	
    public static String getFileName(String fullFileName) {
    	String fileName = "";
    	
    	try {
	    	File f = new File(fullFileName);
	    	fileName = f.getName();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return fileName;
	}	
	
    public static String getFileExtension(String fullFileName) {
    	String fileExt = "";
    	
    	try {
	    	int pos = fullFileName.lastIndexOf(".");
	    	fileExt = fullFileName.substring(pos+1);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return fileExt;
	}	
	
}
