/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * ObjetoBase.java
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

package br.com.tlmv.servermon.data;

import java.io.Serializable;

import org.json.JSONObject;

import br.com.tlmv.servermon.AppDefs;
import br.com.tlmv.servermon.utils.UuidUtil;

public class ObjetoBase implements Serializable
{
//Private
	private static final long serialVersionUID = 202210250904L;

	private String baseObjectId = AppDefs.BASE_OBJID_NONE;
	private Integer baseObjectType = AppDefs.BASE_OBJTYPE_NONE;
	private String baseDescription = "";
	
//Public
	
	public ObjetoBase() {
		this.baseInit(UuidUtil.generateUUID(), AppDefs.BASE_OBJTYPE_NONE, "");
	}

	public ObjetoBase(Integer objectType) {
		this.baseInit(baseObjectType, "");
	}

	public ObjetoBase(Integer baseObjectType, String baseDescription) {
		this.baseInit(baseObjectType, baseDescription);
	}

	public ObjetoBase(String baseObjectId, Integer baseObjectType, String baseDescription) {
		this.baseInit(baseObjectId, baseObjectType, baseDescription);
	}
	
	/* Methodes */
	
	public void baseInit(Integer baseObjectType, String baseDescription) {
		this.baseObjectId = UuidUtil.generateUUID();
		this.baseObjectType = baseObjectType;
		this.baseDescription = baseDescription;		
	}
	
	public void baseInit(String baseObjectId, Integer baseObjectType, String baseDescription) {
		this.baseObjectId = baseObjectId;
		this.baseObjectType = baseObjectType;
		this.baseDescription = baseDescription;		
	}
	
	public void init(JSONObject jsonObj) {
		/* nothing todo! */
	}

	public void fromStrData(String strData) {
		/* nothing todo! */
	}
	
	@Override
	public String toString() {
		return this.baseDescription;
	}
	
	public void debug(int debugLevel) {
		/* nothing todo! */
	}

	/* Getters/Setters */

	public String getBaseObjectId() {
		return baseObjectId;
	}

	public void setBaseObjectId(String baseObjectId) {
		this.baseObjectId = baseObjectId;
	}

	public String getBaseDescription() {
		return baseDescription;
	}

	public void setBaseDescription(String baseDescription) {
		this.baseDescription = baseDescription;
	}

	public Integer getBaseObjectType() {
		return baseObjectType;
	}

	public void setBaseObjectType(Integer baseObjectType) {
		this.baseObjectType = baseObjectType;
	}
	
}
