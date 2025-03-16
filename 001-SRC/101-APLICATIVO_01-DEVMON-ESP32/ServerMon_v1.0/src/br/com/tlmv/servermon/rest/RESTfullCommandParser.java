/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * RESTfullCommandParser.java
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

import br.com.tlmv.servermon.AppDefs;

public class RESTfullCommandParser 
{
//Private
	private RESTfullCommand cmd = null;
	
//Public
	
	public RESTfullCommandParser(RESTfullCommand cmd) {
		this.cmd = cmd;
	}
	
	/* Methodes */
	
	public boolean executeParser() {
		boolean bResult = false;
		
		//COPYRIGHT
		//
		if( parseCommand(AppDefs.REST_CMD_COPYRIGHT_STR) ) {
			this.cmd.setCmdRequestVal(AppDefs.REST_CMD_COPYRIGHT_VAL);
			bResult = true;
		}
		//EVENTOS
		//
		else if( parseCommand(AppDefs.REST_CMD_LISTALL_EVENTOS_STR) ) {
			this.cmd.setCmdRequestVal(AppDefs.REST_CMD_LISTALL_EVENTOS_VAL);
			bResult = true;
		}
		else if( parseCommand(AppDefs.REST_CMD_LIST_EVENTO_STR) ) {
			this.cmd.setCmdRequestVal(AppDefs.REST_CMD_LIST_EVENTO_VAL);

			String strParam = parseCommandParam(AppDefs.REST_CMD_LIST_EVENTO_STR); 
			if( !"?".equals(strParam) ) {
				this.cmd.setCmdParam(strParam);
				bResult = true;
			}
			else {
				this.cmd.setCmdRequestVal(AppDefs.REST_CMD_DEFAULT_VAL);
			}
		}
		else if( parseCommand(AppDefs.REST_CMD_LIST_EVENTO_BY_DATA_STR) ) {
			this.cmd.setCmdRequestVal(AppDefs.REST_CMD_LIST_EVENTO_BY_DATA_VAL);

			String strParam = parseCommandParam(AppDefs.REST_CMD_LIST_EVENTO_BY_DATA_STR); 
			if( !"?".equals(strParam) ) {
				this.cmd.setCmdParam(strParam);
				bResult = true;
			}
			else {
				this.cmd.setCmdRequestVal(AppDefs.REST_CMD_DEFAULT_VAL);
			}
		}
		else if( parseCommand(AppDefs.REST_CMD_ADD_EVENTO_STR) ) {
			this.cmd.setCmdRequestVal(AppDefs.REST_CMD_ADD_EVENTO_VAL);

			String strParam = parseCommandParam(AppDefs.REST_CMD_ADD_EVENTO_STR); 
			if( !"?".equals(strParam) ) {
				this.cmd.setCmdParam(strParam);
				bResult = true;
			}
			else {
				this.cmd.setCmdRequestVal(AppDefs.REST_CMD_DEFAULT_VAL);
			}
		}
		//REGRAS
		//
		else if( parseCommand(AppDefs.REST_CMD_LISTALL_REGRAS_STR) ) {
			this.cmd.setCmdRequestVal(AppDefs.REST_CMD_LISTALL_REGRAS_VAL);
			bResult = true;
		}
		else if( parseCommand(AppDefs.REST_CMD_LIST_REGRA_STR) ) {
			this.cmd.setCmdRequestVal(AppDefs.REST_CMD_LIST_REGRA_VAL);

			String strParam = parseCommandParam(AppDefs.REST_CMD_LIST_REGRA_STR); 
			if( !"?".equals(strParam) ) {
				this.cmd.setCmdParam(strParam);
				bResult = true;
			}
			else {
				this.cmd.setCmdRequestVal(AppDefs.REST_CMD_DEFAULT_VAL);
			}
		}
		else if( parseCommand(AppDefs.REST_CMD_LIST_REGRA_BY_NOME_STR) ) {
			this.cmd.setCmdRequestVal(AppDefs.REST_CMD_LIST_REGRA_BY_NOME_VAL);

			String strParam = parseCommandParam(AppDefs.REST_CMD_LIST_REGRA_BY_NOME_STR); 
			if( !"?".equals(strParam) ) {
				this.cmd.setCmdParam(strParam);
				bResult = true;
			}
			else {
				this.cmd.setCmdRequestVal(AppDefs.REST_CMD_DEFAULT_VAL);
			}
		}
		//REDES
		//
		else if( parseCommand(AppDefs.REST_CMD_LISTALL_REDES_STR) ) {
			this.cmd.setCmdRequestVal(AppDefs.REST_CMD_LISTALL_REDES_VAL);
			bResult = true;
		}
		else if( parseCommand(AppDefs.REST_CMD_LIST_REDE_STR) ) {
			this.cmd.setCmdRequestVal(AppDefs.REST_CMD_LIST_REDE_VAL);

			String strParam = parseCommandParam(AppDefs.REST_CMD_LIST_REDE_STR); 
			if( !"?".equals(strParam) ) {
				this.cmd.setCmdParam(strParam);
				bResult = true;
			}
			else {
				this.cmd.setCmdRequestVal(AppDefs.REST_CMD_DEFAULT_VAL);
			}
		}
		else if( parseCommand(AppDefs.REST_CMD_LIST_REDE_BY_NOME_STR) ) {
			this.cmd.setCmdRequestVal(AppDefs.REST_CMD_LIST_REDE_BY_NOME_VAL);

			String strParam = parseCommandParam(AppDefs.REST_CMD_LIST_REDE_BY_NOME_STR); 
			if( !"?".equals(strParam) ) {
				this.cmd.setCmdParam(strParam);
				bResult = true;
			}
			else {
				this.cmd.setCmdRequestVal(AppDefs.REST_CMD_DEFAULT_VAL);
			}
		}
		else if( parseCommand(AppDefs.REST_CMD_LIST_REDE_BY_DEVICE_STR) ) {
			this.cmd.setCmdRequestVal(AppDefs.REST_CMD_LIST_REDE_BY_DEVICE_VAL);

			String strParam = parseCommandParam(AppDefs.REST_CMD_LIST_REDE_BY_DEVICE_STR); 
			if( !"?".equals(strParam) ) {
				this.cmd.setCmdParam(strParam);
				bResult = true;
			}
			else {
				this.cmd.setCmdRequestVal(AppDefs.REST_CMD_DEFAULT_VAL);
			}
		}
		else if( parseCommand(AppDefs.REST_CMD_ADD_ARRREDE_STR) ) {
			this.cmd.setCmdRequestVal(AppDefs.REST_CMD_ADD_ARRREDE_VAL);

			String strParam = parseCommandParam(AppDefs.REST_CMD_ADD_ARRREDE_STR); 
			if( !"?".equals(strParam) ) {
				this.cmd.setCmdParam(strParam);
				bResult = true;
			}
			else {
				this.cmd.setCmdRequestVal(AppDefs.REST_CMD_DEFAULT_VAL);
			}
		}
		//DO-IT-YOURSELF
		//
		else if( parseCommand(AppDefs.REST_CMD_CONTROLE_SISTEMA_BASE_STR) ) {
			this.cmd.setCmdRequestVal(AppDefs.REST_CMD_CONTROLE_SISTEMA_VAL);

			this.cmd.setCmdParam("index.diy");
			bResult = true;
		}
		else if( parseCommand(AppDefs.REST_CMD_CONTROLE_SISTEMA_STR) ) {
			this.cmd.setCmdRequestVal(AppDefs.REST_CMD_CONTROLE_SISTEMA_VAL);

			String strParam = parseCommandParam(AppDefs.REST_CMD_CONTROLE_SISTEMA_STR); 
			if( !"?".equals(strParam) ) {
				this.cmd.setCmdParam(strParam);
				bResult = true;
			}
			else {
				this.cmd.setCmdParam("index.diy");
				bResult = true;
			}
		}
		//DEFAULT
		//
		else {
			this.cmd.setCmdRequestVal(AppDefs.REST_CMD_DEFAULT_VAL);
		}
		
		return bResult;
	}
	
	public boolean parseCommand(String restCmd) {
		String[] restCmdPart = restCmd.split("/");
		int restCmdPartSz = restCmdPart.length;
		
		String[] cmdPart = this.cmd.getCmdRequestStr().split("/");
		int cmdPartSz = cmdPart.length;
		
		if(restCmdPartSz != cmdPartSz)
			return false;
		
		for(int i = 0; i < restCmdPartSz; i++) {
			String restCmdToken = restCmdPart[i];
			String cmdToken = cmdPart[i];
			
			if( !"?".equals(restCmdToken) ) {
				if( !cmdToken.equals(restCmdToken) )
					return false;
			}
		}
		
		return true;
	}
	
	public String parseCommandParam(String restCmd) {
		String[] restCmdPart = restCmd.split("/");
		int restCmdPartSz = restCmdPart.length;
		
		String[] cmdPart = this.cmd.getCmdRequestStr().split("/");
		int cmdPartSz = cmdPart.length;
		
		if(restCmdPartSz != cmdPartSz)
			return "";
		
		for(int i = 0; i < restCmdPartSz; i++) {
			String restCmdToken = restCmdPart[i];
			String cmdToken = cmdPart[i];
			
			if( "?".equals(restCmdToken) )
				return cmdToken;
		}
		
		return "";
	}
	
}
