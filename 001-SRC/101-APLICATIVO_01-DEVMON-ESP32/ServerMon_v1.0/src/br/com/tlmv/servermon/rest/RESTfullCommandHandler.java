/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * RESTfullCommandHandler.java
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

package br.com.tlmv.servermon.rest;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import br.com.tlmv.servermon.AppDefs;
import br.com.tlmv.servermon.AppError;
import br.com.tlmv.servermon.AppMain;

public class RESTfullCommandHandler implements HttpHandler 
{
//Public
	
	@Override
	public void handle(HttpExchange httpEx) 
		throws IOException 
	{
		AppError err = AppMain.getApp().getErr();
		
		try {
			URI cmdRequestURI = httpEx.getRequestURI();
			String cmdRequestStr = cmdRequestURI.getRawPath();

			err.writeDebug(AppDefs.DEBUG_LEVEL_01, this.getClass().getName(), "handle()", "CmdRequestStr: " + cmdRequestStr);
	
			RESTfullCommand cmd = new RESTfullCommand(cmdRequestStr);
			RESTfullCommandExecutor exec = new RESTfullCommandExecutor(cmd);

			String contentType = AppDefs.MIMETYPE_JSON;
			
			byte[] arrResp = exec.execute();
			if(arrResp == null) {
				contentType = AppDefs.MIMETYPE_JSON;
				arrResp = "{ \"error\":\"Identificador invalido.\" }".getBytes("utf-8");
			}
			int respSz = arrResp.length;
						
			httpEx.sendResponseHeaders(200, respSz);
			httpEx.setAttribute("content-type", contentType);

			OutputStream os = httpEx.getResponseBody();
	        os.write(arrResp);
	        os.close();
		}
		catch(Exception e) {
			err.writeError(this.getClass().getName(), "handle()", e.getMessage());
			e.printStackTrace();
		}
	}

}
