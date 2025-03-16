/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * TestRegraTable.java
 * Autor: 
 * - Luiz Marcio Faria de Aquino Viana, Pós-D.Sc. - Engenheiro, 29/01/2023
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

package br.com.tlmv.servermon.tests;

import br.com.tlmv.servermon.AppDataManager;
import br.com.tlmv.servermon.AppDefs;
import br.com.tlmv.servermon.AppError;
import br.com.tlmv.servermon.AppMain;
import br.com.tlmv.servermon.nosql.EventoTable;
import br.com.tlmv.servermon.nosql.RegraTable;

public class TestRegraTable extends TestBase
{
//Private
	private int szTblRegra = -1;
	
//Public
	public static final String TEST_NAME = "TEST_REGRA_TABLE";
	
	public TestRegraTable() {
		super(TestRegraTable.TEST_NAME);
	}
	
	/* Methodes */
	
	public boolean testRegraTableAddElements() {
		boolean bResult = false;
		
		AppError err = AppMain.getApp().getErr();

		AppDataManager dataMan = AppMain.getApp().getDataMan(); 

		try {
			err.writeUnitTest(this.getClass().getName(), "testRegraTableAddElements()", "Iniciando teste unitario da tabela de regras...");
			
			RegraTable tblRegra = dataMan.getTblRegra();
			tblRegra.loadInitData();
			
			this.szTblRegra = tblRegra.getTableSize();
			
			String warnmsg = "RegraTableSize = " + this.szTblRegra; 
			err.writeUnitTest(this.getClass().getName(), "testRegraTableAddElements()", warnmsg);
			
			tblRegra.debugAll(AppDefs.DEBUG_LEVEL);
			
			bResult = true;
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "testRegraTableAddElements()", e.getMessage());
			e.printStackTrace();
		}

		err.writeUnitTestResult(this.getClass().getName(), "testRegraTableAddElements()", "Concluido teste unitario da tabela de regras!", bResult);			
		return bResult;
	}

	public boolean testRegraTableSaveData() {
		boolean bResult = false;
		
		AppError err = AppMain.getApp().getErr();

		AppDataManager dataMan = AppMain.getApp().getDataMan(); 

		try {
			err.writeUnitTest(this.getClass().getName(), "testRegraTableSaveData()", "Iniciando teste unitario de gravacao da tabela de regras...");

			RegraTable tblRegra = dataMan.getTblRegra();
			tblRegra.saveData();
			
			bResult = true;
		}
		catch(Exception e) {
			err.writeError(this.getClass().getName(), "testRegraTableSaveData()", e.getMessage());
			e.printStackTrace();
		}

		err.writeUnitTestResult(this.getClass().getName(), "testRegraTableSaveData()", "Concluido teste unitario de gravacao da tabela de regras!", bResult);			
		return bResult;
	}

	public boolean testRegraTableLoadData() {
		boolean bResult = false;
		
		AppError err = AppMain.getApp().getErr();

		AppDataManager dataMan = AppMain.getApp().getDataMan(); 
		
		try {
			err.writeUnitTest(this.getClass().getName(), "testRegraTableLoadData()", "Iniciando teste unitario de leitura da tabela de regras...");

			RegraTable tblRegra = dataMan.getTblRegra();
			tblRegra.clear();
			tblRegra.loadData();
			tblRegra.debugAll(AppDefs.DEBUG_LEVEL);
			
			bResult = true;
		}
		catch(Exception e) {
			err.writeError(this.getClass().getName(), "testRegraTableLoadData()", e.getMessage());
			e.printStackTrace();
		}

		err.writeUnitTestResult(this.getClass().getName(), "testRegraTableLoadData()", "Concluido teste unitario de leitura da tabela de regras!", bResult);			
		return bResult;
	}
	
	public boolean testRegraTableSizeAfterLoadData() {
		boolean bResult = false;
		
		AppError err = AppMain.getApp().getErr();

		AppDataManager dataMan = AppMain.getApp().getDataMan(); 
		
		try {
			err.writeUnitTest(this.getClass().getName(), "testRegraTableSizeAfterLoadData()", "Iniciando teste unitario de verificacao do tamanho da tabela de regras...");

			RegraTable tblRegra = dataMan.getTblRegra();	
			int szTblRegra = tblRegra.getTableSize();
			
			String warnmsg = "OldRegraTableSize = " + szTblRegra + "; NewRegraTableSize = " + this.szTblRegra; 
			err.writeUnitTest(this.getClass().getName(), "testRegraTableSizeAfterLoadData()", warnmsg);

			if(szTblRegra == this.szTblRegra)
				bResult = true;
		}
		catch(Exception e) {
			err.writeError(this.getClass().getName(), "testRegraTableSizeAfterLoadData()", e.getMessage());
			e.printStackTrace();
		}

		err.writeUnitTestResult(this.getClass().getName(), "testRegraTableSizeAfterLoadData()", "Concluido teste unitario de verificacao do tamanho da tabela de regras!", bResult);
		return bResult;
	}
	
	@Override
	public boolean executeUnitTest() {
		if( !this.testRegraTableAddElements() )
			return false;
		
		if( !this.testRegraTableSaveData() )
			return false;
		
		if( !this.testRegraTableLoadData() )
			return false;
		
		if( !this.testRegraTableSizeAfterLoadData() )
			return false;
		
		return true;
	}
		
}
