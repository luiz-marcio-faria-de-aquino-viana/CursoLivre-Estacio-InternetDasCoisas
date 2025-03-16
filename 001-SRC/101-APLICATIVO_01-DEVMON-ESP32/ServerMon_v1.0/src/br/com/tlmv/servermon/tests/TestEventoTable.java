/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * TestEventoTable.java
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

package br.com.tlmv.servermon.tests;

import java.util.Date;

import br.com.tlmv.servermon.AppDataManager;
import br.com.tlmv.servermon.AppDefs;
import br.com.tlmv.servermon.AppError;
import br.com.tlmv.servermon.AppMain;
import br.com.tlmv.servermon.data.Evento;
import br.com.tlmv.servermon.nosql.EventoTable;

public class TestEventoTable extends TestBase
{
//Private
	private int szTblEvento = -1;
	
//Public
	public static final String TEST_NAME = "TEST_EVENTO_TABLE";
	
	public TestEventoTable() {
		super(TestEventoTable.TEST_NAME);
	}
	
	/* Methodes */
	
	public boolean testEventoTableAddElements() {
		boolean bResult = false;
		
		AppError err = AppMain.getApp().getErr();
		
		AppDataManager dataMan = AppMain.getApp().getDataMan(); 
		
		try {
			err.writeUnitTest(this.getClass().getName(), "testEventoTableAddElements()", "Iniciando teste unitario da tabela de eventos...");
			
			EventoTable tblEvento = dataMan.getTblEvento();
			for(int i = 0; i < 100; i++) {
				
				Integer oid = AppDefs.SEQ_INIT_EVENTO + i;
				Integer numSeq = AppDefs.SEQ_INIT_EVENTO + i;
				Date dataHora = new Date();
				String equipamento = "5dab89ef46dd";
				Integer luzAcesa = ((Math.random() > 0.5) ? 1 : 0);
				Integer movimento = 0;
				Double fumaca = 0.0;
				Double gasCarbonico = 0.0;
				Double gasNatural = (Math.random() * 0.1);
				Double temperatura = 15.0 + (Math.random() * 25.0);
				Double humidadeAr = 0.2 + (Math.random() * 0.2);
				Integer ligarVent = 0;
				Integer ligarArCond = 0;
				Integer ligarDry = 0;
				
				if(luzAcesa == 1) {
					movimento = ((Math.random() > 0.5) ? 1 : 0);
					if(movimento == 1) {
						fumaca = Math.random();
						gasCarbonico = 0.05 + (Math.random() * 0.25);
					}
				}
				
				Evento oEvento = new Evento(
					oid,
					numSeq,
					dataHora,
					equipamento,
					luzAcesa,
					movimento,
					fumaca,
					gasCarbonico,
					gasNatural,
					temperatura,
					humidadeAr,
					ligarVent,
					ligarArCond,
					ligarDry);
				tblEvento.putObj(oEvento);
			}
			
			this.szTblEvento = tblEvento.getTableSize();
			
			String warnmsg = "EventoTableSize = " + this.szTblEvento; 
			err.writeUnitTest(this.getClass().getName(), "testEventoTableAddElements()", warnmsg);
			
			tblEvento.debugAll(AppDefs.DEBUG_LEVEL);
			
			bResult = true;
		}
		catch(Exception e) {
			err.writeError(this.getClass().getName(), "testEventoTableAddElements()", e.getMessage());
			e.printStackTrace();
		}

		err.writeUnitTestResult(this.getClass().getName(), "testEventoTableAddElements()", "Concluido teste unitario da tabela de eventos!", bResult);			
		return bResult;
	}

	public boolean testEventoTableSaveData() {
		boolean bResult = false;
		
		AppError err = AppMain.getApp().getErr();
		
		AppDataManager dataMan = AppMain.getApp().getDataMan(); 
		
		try {
			err.writeUnitTest(this.getClass().getName(), "testEventoTableSaveData()", "Iniciando teste unitario de gravacao da tabela de eventos...");

			EventoTable tblEvento = dataMan.getTblEvento();
			tblEvento.saveData();
			
			bResult = true;
		}
		catch(Exception e) {
			err.writeError(this.getClass().getName(), "testEventoTableSaveData()", e.getMessage());
			e.printStackTrace();
		}

		err.writeUnitTestResult(this.getClass().getName(), "testEventoTableSaveData()", "Concluido teste unitario de gravacao da tabela de eventos!", bResult);			
		return bResult;
	}

	public boolean testEventoTableLoadData() {
		boolean bResult = false;
		
		AppError err = AppMain.getApp().getErr();
		
		AppDataManager dataMan = AppMain.getApp().getDataMan(); 
		
		try {
			err.writeUnitTest(this.getClass().getName(), "testEventoTableLoadData()", "Iniciando teste unitario de leitura da tabela de eventos...");

			EventoTable tblBreed = dataMan.getTblEvento();
			tblBreed.clear();
			tblBreed.loadData();
			tblBreed.debugAll(AppDefs.DEBUG_LEVEL);
			
			bResult = true;
		}
		catch(Exception e) {
			err.writeError(this.getClass().getName(), "testEventoTableLoadData()", e.getMessage());
			e.printStackTrace();
		}

		err.writeUnitTestResult(this.getClass().getName(), "testEventoTableLoadData()", "Concluido teste unitario de leitura da tabela de eventos!", bResult);			
		return bResult;
	}
	
	public boolean testEventoTableSizeAfterLoadData() {
		boolean bResult = false;
		
		AppError err = AppMain.getApp().getErr();
		
		AppDataManager dataMan = AppMain.getApp().getDataMan(); 
		
		try {
			err.writeUnitTest(this.getClass().getName(), "testEventoTableSizeAfterLoadData()", "Iniciando teste unitario de verificacao do tamanho da tabela de eventos...");

			EventoTable tblEvento = dataMan.getTblEvento();	
			int szTblEvento = tblEvento.getTableSize();
			
			String warnmsg = "OldEventoTableSize = " + szTblEvento + "; NewEventoTableSize = " + this.szTblEvento; 
			AppMain.getApp().getErr().writeUnitTest(this.getClass().getName(), "testEventoTableSizeAfterLoadData()", warnmsg);

			if(szTblEvento == this.szTblEvento)
				bResult = true;
		}
		catch(Exception e) {
			err.writeError(this.getClass().getName(), "testEventoTableSizeAfterLoadData()", e.getMessage());
			e.printStackTrace();
		}

		err.writeUnitTestResult(this.getClass().getName(), "testEventoTableSizeAfterLoadData()", "Concluido teste unitario de verificacao do tamanho da tabela de eventos!", bResult);
		return bResult;
	}
	
	@Override
	public boolean executeUnitTest() {
		if( !this.testEventoTableAddElements() )
			return false;
		
		if( !this.testEventoTableSaveData() )
			return false;
		
		if( !this.testEventoTableLoadData() )
			return false;
		
		if( !this.testEventoTableSizeAfterLoadData() )
			return false;
		
		return true;
	}
		
}
