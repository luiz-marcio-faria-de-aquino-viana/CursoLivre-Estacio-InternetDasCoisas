/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * QueryExecutor.java
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

import java.util.Collection;
import java.util.Hashtable;

import br.com.tlmv.servermon.AppContext;
import br.com.tlmv.servermon.AppDataManager;
import br.com.tlmv.servermon.AppDefs;
import br.com.tlmv.servermon.AppError;
import br.com.tlmv.servermon.AppMain;
import br.com.tlmv.servermon.data.Evento;
import br.com.tlmv.servermon.data.ObjetoBase;
import br.com.tlmv.servermon.data.Rede;
import br.com.tlmv.servermon.data.Regra;
import br.com.tlmv.servermon.nosql.EventoTable;
import br.com.tlmv.servermon.nosql.RedeTable;
import br.com.tlmv.servermon.nosql.RegraTable;
import br.com.tlmv.servermon.rest.RESTfullCommand;
import br.com.tlmv.servermon.rest.diy.DoItYourself;
import br.com.tlmv.servermon.utils.EncodeUtil;
import br.com.tlmv.servermon.utils.FileUtil;

public class QueryExecutor
{
//Private
	private RESTfullCommand cmdRequest = null;
	private QueryWorker[] arrWorker = null;
	private StringBuilder output = null;
	
	private StringBuilder dispatchQueryWorker(Collection<ObjetoBase> lsBaseObjectData) {
		AppError err = AppMain.getApp().getErr();
		
		err.writeDebug(AppDefs.DEBUG_LEVEL_01, this.getClass().getName(), "dispatchQueryWorker()", "Dispara processos de consulta...");

		StringBuilder sb = new StringBuilder();
		
		//DISPATCH_THREADS

		ObjetoBase[] arrObj = lsBaseObjectData.toArray(new ObjetoBase[lsBaseObjectData.size()]);
		Integer szArrObj = arrObj.length;
		
		Integer szArrObjPart = szArrObj / AppDefs.THREAD_MAX_WORKERS;
		Integer startPos = 0;
		Integer endPos = szArrObjPart;
		for(int i = 0; i < AppDefs.THREAD_MAX_WORKERS - 1; i++) {
			QueryWorker worker = new QueryWorker(
				i + 1, 
				this.cmdRequest, 
				arrObj, 
				startPos, 
				endPos);
			arrWorker[i] = worker; 
			worker.startQuery();
			
			startPos = endPos;
			endPos = endPos + szArrObjPart;
		}

		endPos = szArrObj;
		QueryWorker lastWorker = new QueryWorker(
				AppDefs.THREAD_MAX_WORKERS, 
				this.cmdRequest, 
				arrObj, 
				startPos, 
				endPos);
			arrWorker[AppDefs.THREAD_MAX_WORKERS - 1] = lastWorker; 
			lastWorker.startQuery();

		//BARRIER - WAINTING_FOR_RESULTS
			
		for(int i = 0; i < AppDefs.THREAD_MAX_WORKERS; i++) {
			QueryWorker worker = arrWorker[i];
			worker.waitForComplete();
			if(worker.getOutput().length() != 0) {
				if(sb.length() == 0) {
					sb.append(worker.getOutput());
				}
				else {
					sb.append(",");
					sb.append(worker.getOutput());
				}
			}
		}
		
		err.writeDebug(AppDefs.DEBUG_LEVEL_01, this.getClass().getName(), "dispatchQueryWorker()", "Termino do processo de consulta!");

		return sb;
	}
	
//Public
	
	public QueryExecutor(RESTfullCommand cmdRequest) {
		this.cmdRequest = cmdRequest;
		this.arrWorker = new QueryWorker[AppDefs.THREAD_MAX_WORKERS];
		this.output = new StringBuilder();
	}
	
	/* Methodes */
	
	//COPYRIGHT
	//
	public void copyright() {
		AppError err = AppMain.getApp().getErr();
		
		err.writeDebug(AppDefs.DEBUG_LEVEL_01, this.getClass().getName(), "copyright()", "Apresenta mensagem de Copyright...");
		
		String str = String.format("%s %s\n\n%s\n", 
			AppDefs.APP_NAME, 
			AppDefs.APP_VERSAO,
			AppDefs.APP_COPYRIGHT);
		this.output.append(str);
	}
	
	//EVENTO
	//
	public void findAllEvento() {
		AppError err = AppMain.getApp().getErr();
		
		AppDataManager dataMan = AppMain.getApp().getDataMan();
		
		err.writeDebug(AppDefs.DEBUG_LEVEL_01, this.getClass().getName(), "findAllEvento()", "Pesquisa todos os eventos...");

		EventoTable tblEvento = dataMan.getTblEvento();
		Hashtable<String,ObjetoBase> tblEventoData = tblEvento.getTableData();
		Collection<ObjetoBase> lsEventoData = tblEventoData.values();
		
		this.output.append("[ ");
		this.output.append(this.dispatchQueryWorker(lsEventoData));				
		this.output.append(" ]");
	}
	
	public void findEventoByPk() {
		AppError err = AppMain.getApp().getErr();
		
		AppDataManager dataMan = AppMain.getApp().getDataMan();
		
		err.writeDebug(AppDefs.DEBUG_LEVEL_01, this.getClass().getName(), "findEventoByPk()", "Pesquisa evento pelo identificador...");

		String key = cmdRequest.getCmdParam();
		AppMain.getApp().getErr().writeDebug(AppDefs.DEBUG_LEVEL_01, this.getClass().getName(), "findEventoByPk()", "KEY: " + key);
		
		EventoTable tblEvento = dataMan.getTblEvento();
		ObjetoBase obj = tblEvento.getObj(key);
		if(obj != null) {
			Evento oEvento = (Evento)obj;

			this.output.append("[ ");
			this.output.append(oEvento.toStrJSON());
			this.output.append(" ]");
		}
		else {
			this.output.append("{ \"error\":\"Identificador de evento invalido.\" }");
		}
	}
	
	public void findEventoByData() {
		AppError err = AppMain.getApp().getErr();
		
		AppDataManager dataMan = AppMain.getApp().getDataMan();
		
		err.writeDebug(AppDefs.DEBUG_LEVEL_01, this.getClass().getName(), "findEventoByData()", "Pesquisa eventos pela data...");

		String dataCorte = cmdRequest.getCmdParam();
		AppMain.getApp().getErr().writeDebug(AppDefs.DEBUG_LEVEL_01, this.getClass().getName(), "findEventoByData()", "DATA: " + dataCorte);
		
		if(dataCorte == null) {
			this.output.append("{ \"error\":\"Data do evento invalida.\" }");
			return;
		}
		
		EventoTable tblEvento = dataMan.getTblEvento();
		Hashtable<String,ObjetoBase> tblEventoData = tblEvento.getTableData();
		Collection<ObjetoBase> lsEventoData = tblEventoData.values();
		
		this.output.append("[ ");
		this.output.append(this.dispatchQueryWorker(lsEventoData));
		this.output.append(" ]");
	}
	
	public void addNewEvento() {
		AppError err = AppMain.getApp().getErr();
		
		AppDataManager dataMan = AppMain.getApp().getDataMan();
		
		err.writeDebug(AppDefs.DEBUG_LEVEL_01, this.getClass().getName(), "addNewEvento()", "Adiciona novo evento...");

		String strData = cmdRequest.getCmdParam();
		err.writeDebug(AppDefs.DEBUG_LEVEL_01, this.getClass().getName(), "addNewEvento()", "VAL: " + strData);
		
		if(strData == null) {
			this.output.append("{ \"error\":\"Valor invalido para o evento.\" }");
			return;
		}
		
		EventoTable tblEvento = dataMan.getTblEvento();
		String key = tblEvento.putObjFromStr(strData);
		this.output.append("{ \"success\":\"" + key + "\" }");
	}
	
	//REDE
	//
	public void findAllRede() {
		AppError err = AppMain.getApp().getErr();
		
		AppDataManager dataMan = AppMain.getApp().getDataMan();
		
		err.writeDebug(AppDefs.DEBUG_LEVEL_01, this.getClass().getName(), "findAllRede()", "Pesquisa todos as redes...");

		RedeTable tblRede = dataMan.getTblRede();
		Hashtable<String,ObjetoBase> tblRedeData = tblRede.getTableData();
		Collection<ObjetoBase> lsRedeData = tblRedeData.values();
		
		this.output.append("[ ");
		this.output.append(this.dispatchQueryWorker(lsRedeData));				
		this.output.append(" ]");
	}
	
	public void findRedeByPk() {
		AppError err = AppMain.getApp().getErr();
		
		AppDataManager dataMan = AppMain.getApp().getDataMan();
		
		err.writeDebug(AppDefs.DEBUG_LEVEL_01, this.getClass().getName(), "findRedeByPk()", "Pesquisa rede pelo identificador...");

		String key = cmdRequest.getCmdParam();
		AppMain.getApp().getErr().writeDebug(AppDefs.DEBUG_LEVEL_01, this.getClass().getName(), "findRedeByPk()", "KEY: " + key);
		
		RedeTable tblRede = dataMan.getTblRede();
		ObjetoBase obj = tblRede.getObj(key);
		if(obj != null) {
			Rede oRede = (Rede)obj;

			this.output.append("[ ");
			this.output.append(oRede.toStrJSON());
			this.output.append(" ]");
		}
		else {
			this.output.append("{ \"error\":\"Identificador de rede invalido.\" }");
		}
	}
	
	public void findRedeByNome() {
		AppError err = AppMain.getApp().getErr();
		
		AppDataManager dataMan = AppMain.getApp().getDataMan();
		
		err.writeDebug(AppDefs.DEBUG_LEVEL_01, this.getClass().getName(), "findRedeByNome()", "Pesquisa redes pelo nome...");

		String nomeRede = EncodeUtil.urlDecode(cmdRequest.getCmdParam());
		AppMain.getApp().getErr().writeDebug(AppDefs.DEBUG_LEVEL_01, this.getClass().getName(), "findRedeByNome()", "NOME: " + nomeRede);
		
		if(nomeRede == null) {
			this.output.append("{ \"error\":\"Nome da rede invalida.\" }");
			return;
		}
		
		RedeTable tblRede = dataMan.getTblRede();
		Hashtable<String,ObjetoBase> tblRedeData = tblRede.getTableData();
		Collection<ObjetoBase> lsRedeData = tblRedeData.values();
		
		this.output.append("[ ");
		this.output.append(this.dispatchQueryWorker(lsRedeData));
		this.output.append(" ]");
	}
	
	public void findRedeByDevice() {
		AppError err = AppMain.getApp().getErr();
		
		AppDataManager dataMan = AppMain.getApp().getDataMan();
		
		err.writeDebug(AppDefs.DEBUG_LEVEL_01, this.getClass().getName(), "findRedeByDevice()", "Pesquisa redes pelo nome...");

		String device = EncodeUtil.urlDecode(cmdRequest.getCmdParam());
		AppMain.getApp().getErr().writeDebug(AppDefs.DEBUG_LEVEL_01, this.getClass().getName(), "findRedeByDevice()", "DEVICE: " + device);
		
		if(device == null) {
			this.output.append("{ \"error\":\"Device de rede invalido.\" }");
			return;
		}
		
		RedeTable tblRede = dataMan.getTblRede();
		Hashtable<String,ObjetoBase> tblRedeData = tblRede.getTableData();
		Collection<ObjetoBase> lsRedeData = tblRedeData.values();
		
		this.output.append("[ ");
		this.output.append(this.dispatchQueryWorker(lsRedeData));
		this.output.append(" ]");
	}
	
	public void addNewArrRede() {
		AppError err = AppMain.getApp().getErr();
		
		AppDataManager dataMan = AppMain.getApp().getDataMan();
		
		err.writeDebug(AppDefs.DEBUG_LEVEL_01, this.getClass().getName(), "addNewArrRede()", "Adiciona novo vetor de redes...");

		String strData = cmdRequest.getCmdParam();
		err.writeDebug(AppDefs.DEBUG_LEVEL_01, this.getClass().getName(), "addNewArrRede()", "VAL: " + strData);

		if(strData == null) {
			this.output.append("{ \"error\":\"Valor invalido para o vetor de redes.\" }");
			return;
		}
		
		this.output.append("[ ");

		RedeTable tblRede = dataMan.getTblRede();

		String[] strArrData = strData.split(";");
		int n = 0;
		for(String str : strArrData) {
			String newStr = str.substring(1, str.length() - 1);
			
			String key = tblRede.putObjFromStr(newStr);
			if(n > 0)
				this.output.append(",");
			this.output.append("{ \"success\":\"" + key + "\" }");
			
			n++;
		}
		
		this.output.append(" ]");	
	}
	
	//REGRA
	//
	public void findAllRegra() {
		AppError err = AppMain.getApp().getErr();

		AppDataManager dataMan = AppMain.getApp().getDataMan();
		
		err.writeDebug(AppDefs.DEBUG_LEVEL_01, this.getClass().getName(), "findAllRegra()", "Pesquisa todos os regras...");
		
		RegraTable tblRegra = dataMan.getTblRegra();
		Hashtable<String,ObjetoBase> tblRegraData = tblRegra.getTableData();
		Collection<ObjetoBase> lsRegraData = tblRegraData.values();
		
		this.output.append("[ ");
		this.output.append(this.dispatchQueryWorker(lsRegraData));				
		this.output.append(" ]");
	}
	
	public void findRegraByPk() {
		AppError err = AppMain.getApp().getErr();

		AppDataManager dataMan = AppMain.getApp().getDataMan();
		
		err.writeDebug(AppDefs.DEBUG_LEVEL_01, this.getClass().getName(), "findRegraByPk()", "Pesquisa regra pelo identificador...");

		String key = cmdRequest.getCmdParam();
		err.writeDebug(AppDefs.DEBUG_LEVEL_01, this.getClass().getName(), "findRegraByPk()", "KEY: " + key);
		
		RegraTable tblRegra = dataMan.getTblRegra();
		ObjetoBase obj = tblRegra.getObj(key);
		if(obj != null) {
			Regra oRegra = (Regra)obj;

			this.output.append("[ ");
			this.output.append(oRegra.toStrJSON());
			this.output.append(" ]");
		}
		else {
			this.output.append("{ \"error\":\"Identificador de regra invalido.\" }");
		}
	}
	
	public void findRegraByNome() {
		AppError err = AppMain.getApp().getErr();

		AppDataManager dataMan = AppMain.getApp().getDataMan();
		
		err.writeDebug(AppDefs.DEBUG_LEVEL_01, this.getClass().getName(), "findRegraByNome()", "Pesquisa regra pela data...");

		String dataCorte = cmdRequest.getCmdParam();
		err.writeDebug(AppDefs.DEBUG_LEVEL_01, this.getClass().getName(), "findRegraByNome()", "DATA: " + dataCorte);
		
		if(dataCorte == null) {
			this.output.append("{ \"error\":\"Data do evento invalida.\" }");
			return;
		}
		
		RegraTable tblRegra = dataMan.getTblRegra();
		Hashtable<String,ObjetoBase> tblRegraData = tblRegra.getTableData();
		Collection<ObjetoBase> lsRegraData = tblRegraData.values();
		
		this.output.append("[ ");
		this.output.append(this.dispatchQueryWorker(lsRegraData));
		this.output.append(" ]");
	}
	
	//DO-IT-YPURSELF
	//
	public void doItYourself() {
		AppContext ctx = AppMain.getApp().getContext();
		AppError err = AppMain.getApp().getErr();

		AppDataManager dataMan = AppMain.getApp().getDataMan();
		
		err.writeDebug(AppDefs.DEBUG_LEVEL_01, this.getClass().getName(), "doItYourself()", "Request DO-It-Yourself Form...");

		String strParams = cmdRequest.getCmdParam();
		err.writeDebug(AppDefs.DEBUG_LEVEL_01, this.getClass().getName(), "doItYourself()", "DATA: " + strParams);
		
		if(strParams == null) {
			this.output.append("{ \"error\":\"Data do evento invalida.\" }");
			return;
		}

		String webDiyDir = ctx.getWebDiyDir();
		
		String diyFile = webDiyDir + "/" + strParams;		
		
		String strIN = FileUtil.readData(diyFile);
		
		DoItYourself diy = new DoItYourself();
		String strOUT = diy.execute(strParams, strIN);
		this.output.append(strOUT);
	}
	
	/* Execution Methodes */

	public byte[] execute() {
		String strResult = null;
		byte[] arrResult = null;

		try {
			//EVENTOS
			//
			if( AppDefs.REST_CMD_LISTALL_EVENTOS_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
				this.findAllEvento();
			}
			else if( AppDefs.REST_CMD_LIST_EVENTO_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
				this.findEventoByPk();
			}
			else if( AppDefs.REST_CMD_LIST_EVENTO_BY_DATA_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
				this.findEventoByData();
			}
			else if( AppDefs.REST_CMD_ADD_EVENTO_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
				this.addNewEvento();
			}
			//REDES
			//
			else if( AppDefs.REST_CMD_LISTALL_REDES_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
				this.findAllRede();
			}
			else if( AppDefs.REST_CMD_LIST_REDE_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
				this.findRedeByPk();
			}
			else if( AppDefs.REST_CMD_LIST_REDE_BY_NOME_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
				this.findRedeByNome();
			}
			else if( AppDefs.REST_CMD_LIST_REDE_BY_DEVICE_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
				this.findRedeByDevice();
			}
			else if( AppDefs.REST_CMD_ADD_ARRREDE_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
				this.addNewArrRede();
			}
			//REGRAS
			//
			else if( AppDefs.REST_CMD_LISTALL_REGRAS_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
				this.findAllRegra();
			}
			else if( AppDefs.REST_CMD_LIST_REGRA_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
				this.findRegraByPk();
			}
			else if( AppDefs.REST_CMD_LIST_REGRA_BY_NOME_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
				this.findRegraByNome();
			}
			//DO-IT-YOURSELF
			//
			else if( AppDefs.REST_CMD_CONTROLE_SISTEMA_VAL.equals(this.cmdRequest.getCmdRequestVal()) ) {
				this.doItYourself();
			}
			//COPYRIGHT
			//
			else {
				this.copyright();
			}

			strResult = this.output.toString();
			arrResult = strResult.getBytes("utf-8");	
		}
		catch(Exception e) { 
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "execute()", e.getMessage());
			e.printStackTrace();
		}
		
		return arrResult;
	}

}
