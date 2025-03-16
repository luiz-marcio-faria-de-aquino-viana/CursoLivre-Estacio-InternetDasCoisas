/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * DataCreatorRegra.java
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

package br.com.tlmv.servermon.datacreator;

import br.com.tlmv.servermon.AppDataManager;
import br.com.tlmv.servermon.AppDefs;
import br.com.tlmv.servermon.AppError;
import br.com.tlmv.servermon.AppMain;
import br.com.tlmv.servermon.nosql.RegraTable;

public class DataCreatorRegra extends DataCreatorBase
{
//Private
	private int szTblRegra = -1;
		
//Public
	public static final String DATA_CREATOR_NAME = "DATA_CREATOR_REGRA";
		
	public DataCreatorRegra() {
		super(DataCreatorRegra.DATA_CREATOR_NAME);
	}

	/* Methodes */
	
	public boolean dataCreatorForAllRegras() {
		boolean bResult = false;
		
		try {
			AppMain.getApp().getErr().writeLog(this.getClass().getName(), "dataCreatorForAllRegras()", "Iniciando a carga de todas as regras...");

			AppError err = AppMain.getApp().getErr();
			AppDataManager dataMan = AppMain.getApp().getDataMan(); 

			RegraTable tblRegra = dataMan.getTblRegra();
			tblRegra.loadInitData();
			
			this.szTblRegra = tblRegra.getTableSize();
			
			String dbgmsg = "RegraTableSize = " + this.szTblRegra; 
			err.writeDebug(AppDefs.DEBUG_LEVEL_01, this.getClass().getName(), "dataCreatorForAllRegras()", dbgmsg);
						
			tblRegra.debugAll(AppDefs.DEBUG_LEVEL_01);
			tblRegra.saveData();
			
			bResult = true;
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "dataCreatorForAllRegras()", e.getMessage());
			e.printStackTrace();
		}

		AppMain.getApp().getErr().writeLog(this.getClass().getName(), "dataCreatorForAllRegras()", "Concluido a carga de todas as regras!");
		return bResult;
	}

	/* EXECUTE */
	
	@Override
	public boolean executeDataCreator() {
		if( !this.dataCreatorForAllRegras() )
			return false;
		return true;
	}
	
}
