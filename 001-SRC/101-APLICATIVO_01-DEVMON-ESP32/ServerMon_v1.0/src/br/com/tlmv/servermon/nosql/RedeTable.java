/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * RedeTable.java
 * Autor: 
 * - Luiz Marcio Faria de Aquino Viana, Pós-D.Sc. - Engenheiro, 05/02/2023
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

package br.com.tlmv.servermon.nosql;

import br.com.tlmv.servermon.AppDefs;
import br.com.tlmv.servermon.data.Rede;
import br.com.tlmv.servermon.data.Regra;

public class RedeTable extends BaseTable
{
//Public
	
	public RedeTable() {
		super(AppDefs.BASE_OBJTYPE_REDE, AppDefs.BASE_TBLNAME_REDE);
	}

	public String putObjFromStr(String strData) {
		String key = "-1";
		
		Rede oRede = new Rede();
		oRede.fromStrData(strData);
		if(oRede.getId() != -1) {
			key = Integer.toString(oRede.getId());
			this.getTableData().put(key, oRede);
		}
		return key;
	}	
	
}
