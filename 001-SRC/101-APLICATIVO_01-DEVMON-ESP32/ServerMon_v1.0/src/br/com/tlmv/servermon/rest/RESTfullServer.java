/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * RESTfullServer.java
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

import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import br.com.tlmv.servermon.AppDefs;

public class RESTfullServer
{
//Private
	private static RESTfullServer gServer = null;
	
	private HttpServer httpServer = null;
	
//Public
	
	public RESTfullServer() {
		RESTfullServer.gServer = this;
	}

	/* Methodes */
	
	public void init() {
		//TODO:
	}
	
	public void start() 
		throws Exception
	{
		//InetSocketAddress addr = new InetSocketAddress(AppDefs.DEF_REST_SERVER_ADDR, AppDefs.DEF_REST_SERVER_PORT);		
		InetSocketAddress addr = new InetSocketAddress(AppDefs.DEF_REST_SERVER_PORT);
		httpServer = HttpServer.create(addr, AppDefs.DEF_REST_REQUEST_QUEUE_SIZE);
		httpServer.createContext(AppDefs.DEF_REST_APPLICATION_CONTEXT, new RESTfullCommandHandler());
		httpServer.setExecutor(null);
		httpServer.start();
	}
	
	public void stop() {
		if(httpServer != null) {
			this.httpServer.stop(AppDefs.DEF_REST_SERVER_STOP_DELAY);
		}
	}
	
}
