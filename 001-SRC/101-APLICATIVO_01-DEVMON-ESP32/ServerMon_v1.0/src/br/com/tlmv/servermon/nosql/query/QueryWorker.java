/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * QueryWorker.java
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

package br.com.tlmv.servermon.nosql.query;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.tlmv.servermon.AppDefs;
import br.com.tlmv.servermon.data.Evento;
import br.com.tlmv.servermon.data.ObjetoBase;
import br.com.tlmv.servermon.data.Rede;
import br.com.tlmv.servermon.data.Regra;
import br.com.tlmv.servermon.rest.RESTfullCommand;
import br.com.tlmv.servermon.utils.EncodeUtil;
import br.com.tlmv.servermon.utils.StringUtil;

public class QueryWorker implements Runnable
{
//Private
	private Integer threadId = -1;
	private RESTfullCommand cmdRequest = null;
	private ObjetoBase[] arrObj = null;
	private Integer startPos = -1;
	private Integer endPos = -1;
	private StringBuilder output = null;
	private Thread threadObj = null;
	private boolean isRunning = false;
	
//Public
	
	public QueryWorker(Integer threadId, RESTfullCommand cmdRequest, ObjetoBase[] arrObj, Integer startPos, Integer endPos) {
		this.threadId = threadId;
		this.cmdRequest = cmdRequest;
		this.arrObj = arrObj;
		this.startPos = startPos;
		this.endPos = endPos;
		this.output = new StringBuilder();
		this.threadObj = null;
		this.isRunning = false;
	}
	
	/* Methodes */
	
	public void startQuery() {
		this.isRunning = true;

		this.threadObj = new Thread(this);
		this.threadObj.start();
	}

	public synchronized void waitForComplete() {
		while( this.isRunning ) {
			try {
				this.threadObj.wait();
			}
			catch(Exception e) { };
		}
		this.threadObj = null;
		this.notifyAll();
	}

	//FIND
	
	//EVENTOS
	//
	public void findAllEventoPart() {
		for(int i = this.startPos; i < this.endPos; i++) {
			Evento oEvento = (Evento)arrObj[i];
			if(this.output.length() == 0) {
				this.output.append(oEvento.toStrJSON());
			}
			else {
				this.output.append(",");
				this.output.append(oEvento.toStrJSON());
			}
		}
	}
	
	public void findEventoByDataPart() {
		DateFormat df1 = new SimpleDateFormat(AppDefs.FMT_DATETIME_INV_MASC);
		
		String strDataCorte = EncodeUtil.urlDecode(cmdRequest.getCmdParam());

		Date dataCorte = StringUtil.safeDate(df1, strDataCorte);
		
		long dataIniMili = dataCorte.getTime();
		long dataFimMili = dataIniMili + AppDefs.D1;
		
		for(int i = this.startPos; i < this.endPos; i++) {
			Evento oEvento = (Evento)arrObj[i];
			
			Date dataHora = oEvento.getDataHora();
			
			long dataHoraMili = dataHora.getTime();
			
			if( (dataHoraMili >= dataIniMili) && (dataHoraMili < dataFimMili) ) {
				if(this.output.length() == 0) {
					this.output.append(oEvento.toStrJSON());
				}
				else {
					this.output.append(",");
					this.output.append(oEvento.toStrJSON());
				}
			}
		}
	}
	
	//REDES
	//
	public void findAllRedePart() {
		for(int i = this.startPos; i < this.endPos; i++) {
			Rede oRede = (Rede)arrObj[i];
			if(this.output.length() == 0) {
				this.output.append(oRede.toStrJSON());
			}
			else {
				this.output.append(",");
				this.output.append(oRede.toStrJSON());
			}
		}
	}
	
	public void findRedeByNomePart() {
		String strNome = EncodeUtil.urlDecode(cmdRequest.getCmdParam());

		for(int i = this.startPos; i < this.endPos; i++) {
			Rede oRede = (Rede)arrObj[i];
			
			if( strNome.equalsIgnoreCase(oRede.getSsid()) ) {
				if(this.output.length() == 0) {
					this.output.append(oRede.toStrJSON());
				}
				else {
					this.output.append(",");
					this.output.append(oRede.toStrJSON());
				}
			}
		}
	}
	
	public void findRedeByDevicePart() {
		String strDevice = EncodeUtil.urlDecode(cmdRequest.getCmdParam());

		for(int i = this.startPos; i < this.endPos; i++) {
			Rede oRede = (Rede)arrObj[i];
			
			if( strDevice.equalsIgnoreCase(oRede.getEquipment()) ) {
				if(this.output.length() == 0) {
					this.output.append(oRede.toStrJSON());
				}
				else {
					this.output.append(",");
					this.output.append(oRede.toStrJSON());
				}
			}
		}
	}
	
	//REGRAS
	//
	public void findAllRegraPart() {
		for(int i = this.startPos; i < this.endPos; i++) {
			Regra oRegra = (Regra)arrObj[i];
			if(this.output.length() == 0) {
				this.output.append(oRegra.toStrJSON());
			}
			else {
				this.output.append(",");
				this.output.append(oRegra.toStrJSON());
			}
		}
	}
	
	public void findRegraByNomePart() {
		String strNomeRegra = EncodeUtil.urlDecode(cmdRequest.getCmdParam());

		for(int i = this.startPos; i < this.endPos; i++) {
			Regra oRegra = (Regra)arrObj[i];
			
			String nomeRegra = oRegra.getNomeRegra();
			if( strNomeRegra.equals(nomeRegra) ) {
				if(this.output.length() == 0) {
					this.output.append(oRegra.toStrJSON());
				}
				else {
					this.output.append(",");
					this.output.append(oRegra.toStrJSON());
				}
			}
		}
	}
	
	/* Thread */
	
	@Override
	public void run() {
		//EVENTOS
		//
		if( AppDefs.REST_CMD_LISTALL_EVENTOS_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
			this.findAllEventoPart();
		}
		else if( AppDefs.REST_CMD_LIST_EVENTO_BY_DATA_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
			this.findEventoByDataPart();
		}
		//REDES
		//
		else if( AppDefs.REST_CMD_LISTALL_REDES_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
			this.findAllRedePart();
		}
		else if( AppDefs.REST_CMD_LIST_REDE_BY_NOME_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
			this.findRedeByNomePart();
		}
		else if( AppDefs.REST_CMD_LIST_REDE_BY_DEVICE_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
			this.findRedeByDevicePart();
		}
		//REGRAS
		//
		else if( AppDefs.REST_CMD_LISTALL_REGRAS_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
			this.findAllRegraPart();
		}
		else if( AppDefs.REST_CMD_LIST_REGRA_BY_NOME_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
			this.findRegraByNomePart();
		}
		this.isRunning = false;
	}

	/* Getters/Setters */

	public ObjetoBase[] getArrBaseObject() {
		return arrObj;
	}

	public void setArrBaseObject(ObjetoBase[] arrObj) {
		this.arrObj = arrObj;
	}

	public Integer getStartPos() {
		return startPos;
	}

	public void setStartPos(Integer startPos) {
		this.startPos = startPos;
	}

	public Integer getEndPos() {
		return endPos;
	}

	public void setEndPos(Integer endPos) {
		this.endPos = endPos;
	}

	public StringBuilder getOutput() {
		return output;
	}

	public void setOutput(StringBuilder output) {
		this.output = output;
	}
	
}
