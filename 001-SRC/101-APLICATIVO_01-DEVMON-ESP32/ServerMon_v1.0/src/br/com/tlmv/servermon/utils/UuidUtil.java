/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * UuidUtil.java
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

package br.com.tlmv.servermon.utils;

import java.util.Date;

import br.com.tlmv.servermon.AppDefs;

public class UuidUtil 
{
//Private
	private static Long nextRequestId = AppDefs.DEF_REST_INITIAL_REQUEST_ID;
	
//Public
	
	public static String generateUUID() {
		Date dataAtual = new Date();
		
		long dataAtualMili = dataAtual.getTime();
		long rnd = Math.round(Math.random() * 1000000.0) % 10000L;
		
		String uuid = Long.toHexString(dataAtualMili) + "_" + Long.toHexString(rnd);
		return uuid;
	}

	public static Long generateRequestID() {
		return UuidUtil.nextRequestId++;
	}

}
