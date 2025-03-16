/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * DoItYourself.java
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

package br.com.tlmv.servermon.rest.diy;

import java.util.Collection;
import java.util.Hashtable;

import br.com.tlmv.servermon.AppDataManager;
import br.com.tlmv.servermon.AppMain;
import br.com.tlmv.servermon.data.Evento;
import br.com.tlmv.servermon.data.ObjetoBase;
import br.com.tlmv.servermon.data.Rede;
import br.com.tlmv.servermon.data.Regra;
import br.com.tlmv.servermon.nosql.EventoTable;
import br.com.tlmv.servermon.nosql.RedeTable;
import br.com.tlmv.servermon.nosql.RegraTable;
import br.com.tlmv.servermon.utils.StringUtil;

public class DoItYourself 
{
//Private
	
	private String replaceTags(String strIN, String tagName, String tagValue)
	{
		String strOUT = strIN.replace(tagName, tagValue);				
		return strOUT;
	}
	
//Public
	
	public String execute(String strData, String strIN) {
		String strOUT = strIN;
		
		AppDataManager dataMan = AppMain.getApp().getDataMan();

		StringBuilder sb = new StringBuilder();

		if( "list_all_evento.diy".equals(strData) ) {
			EventoTable tblEvento = dataMan.getTblEvento();
			Hashtable<String,ObjetoBase> tblEventoData = tblEvento.getTableData();
			Collection<ObjetoBase> lsEventoData = tblEventoData.values();
			if(lsEventoData.size() > 0) {
				for(ObjetoBase o : lsEventoData) {
					sb.append(((Evento)o).toHtmlTable());				
				}
			}
			strOUT = replaceTags(strIN, "#TAG_LISTA_EVENTOS#", sb.toString());
		}
		else if( "list_all_rede.diy".equals(strData) ) {
			RedeTable tblRede = dataMan.getTblRede();
			Hashtable<String,ObjetoBase> tblRedeData = tblRede.getTableData();
			Collection<ObjetoBase> lsRedeData = tblRedeData.values();
			if(lsRedeData.size() > 0) {
				for(ObjetoBase o : lsRedeData) {
					sb.append(((Rede)o).toHtmlTable());				
				}
			}		
			strOUT = replaceTags(strIN, "#TAG_LISTA_REDES#", sb.toString());
		}
		else if( "list_all_regra.diy".equals(strData) ) {
			RegraTable tblRegra = dataMan.getTblRegra();
			Hashtable<String,ObjetoBase> tblRegraData = tblRegra.getTableData();
			Collection<ObjetoBase> lsRegraData = tblRegraData.values();
			if(lsRegraData.size() > 0) {
				for(ObjetoBase o : lsRegraData) {
					sb.append(((Regra)o).toHtmlTable());				
				}
			}		
			strOUT = replaceTags(strIN, "#TAG_LISTA_REGRAS#", sb.toString());
		}
		
		return strOUT;
	}

}
