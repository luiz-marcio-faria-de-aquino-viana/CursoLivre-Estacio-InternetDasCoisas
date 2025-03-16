/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * DataCreatorBase.java
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

import br.com.tlmv.servermon.AppError;
import br.com.tlmv.servermon.AppMain;

public class DataCreatorBase 
{
//Private
	private String dataCreatorName = "DATACREATOR";
	
//Public
	
	public DataCreatorBase(String dataCreatorName) {
		this.dataCreatorName = dataCreatorName;
	}

	/* Methodes */

	public boolean executeDataCreator() {
		String errmsg = String.format(AppError.ERR_DATA_CREATOR_NOT_IMPLEMENTED, this.dataCreatorName);
		AppMain.getApp().getErr().writeError(this.getClass().getName(), "dataCreatorForAllBreeds", errmsg);
		return false;
	}

	/* Getters/Setters */
	
	public String getDataCreatorName() {
		return dataCreatorName;
	}

	public void setDataCreatorName(String dataCreatorName) {
		this.dataCreatorName = dataCreatorName;
	}

}
