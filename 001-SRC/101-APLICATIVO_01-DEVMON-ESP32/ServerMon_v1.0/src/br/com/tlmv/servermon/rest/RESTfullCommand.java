/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * RESTfullCommand.java
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
import br.com.tlmv.servermon.utils.UuidUtil;

public class RESTfullCommand 
{
//Private
	private Long cmdRequestId = -1L;
	private String cmdRequestStr = AppDefs.REST_CMD_DEFAULT_STR;
	private Integer cmdRequestVal = AppDefs.REST_CMD_DEFAULT_VAL;
	public String cmdParam = "";
	
//Public

	public RESTfullCommand(String cmdRequestStr) {
		this.cmdRequestId = UuidUtil.generateRequestID();
		this.cmdRequestStr = cmdRequestStr;
		if( !this.parseCmdRequest() ) {
			this.defaultCmdRequest();
		}
	}
	
	/* Methodes */

	public void defaultCmdRequest() {
		this.cmdRequestStr = AppDefs.REST_CMD_DEFAULT_STR;
		this.cmdRequestVal = AppDefs.REST_CMD_DEFAULT_VAL;
		this.cmdParam = "";
	}

	public boolean parseCmdRequest() {
		RESTfullCommandParser parser = new RESTfullCommandParser(this);

		boolean bResult = parser.executeParser();
		return bResult;
	}

	/* Getters/Setters */
		
	public Long getCmdRequestId() {
		return cmdRequestId;
	}

	public void setCmdRequestId(Long cmdRequestId) {
		this.cmdRequestId = cmdRequestId;
	}

	public String getCmdRequestStr() {
		return cmdRequestStr;
	}

	public void setCmdRequestStr(String cmdRequestStr) {
		this.cmdRequestStr = cmdRequestStr;
	}

	public Integer getCmdRequestVal() {
		return cmdRequestVal;
	}

	public void setCmdRequestVal(Integer cmdRequestVal) {
		this.cmdRequestVal = cmdRequestVal;
	}

	public String getCmdParam() {
		return cmdParam;
	}

	public void setCmdParam(String cmdParam) {
		this.cmdParam = cmdParam;
	}
		
}
