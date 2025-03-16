/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * AppDataManager.java
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

package br.com.tlmv.servermon;

import java.util.Hashtable;

import br.com.tlmv.servermon.nosql.EventoTable;
import br.com.tlmv.servermon.nosql.RedeTable;
import br.com.tlmv.servermon.nosql.RegraTable;

public class AppDataManager 
{
//Public
	private EventoTable tblEvento = null; 
	private RegraTable tblRegra = null; 
	private RedeTable tblRede = null; 
	
//Public
	
	public AppDataManager() {
		tblEvento = new EventoTable();
		tblRegra = new RegraTable();
		tblRede = new RedeTable();
	}
	
	/* Methodes */
	
	public void saveAll() 
		throws Exception 
	{
		tblEvento.saveData();
		tblRegra.saveData();
		tblRede.saveData();
	}
	
	public void loadAll() 
		throws Exception
	{
		tblEvento.loadData();
		tblRegra.loadData();
		tblRede.loadData();
	}

	/* Getters/Setters */

	public EventoTable getTblEvento() {
		return tblEvento;
	}

	public void setTblEvento(EventoTable tblEvento) {
		this.tblEvento = tblEvento;
	}

	public RegraTable getTblRegra() {
		return tblRegra;
	}

	public void setTblRegra(RegraTable tblRegra) {
		this.tblRegra = tblRegra;
	}

	public RedeTable getTblRede() {
		return tblRede;
	}

	public void setTblRede(RedeTable tblRede) {
		this.tblRede = tblRede;
	}

}
