/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * TipoPessoaDataVO.java
 * Autor: 
 * - Luiz Marcio Faria de Aquino Viana, Pós-D.Sc. - Engenheiro, 04/02/2023
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

package br.com.tlmv.harmonicas.vo;

import java.util.Date;

import br.com.tlmv.harmonicas.util.StringUtil;

public class ItemDataVO 
{
//Private
	private String itemDataId;
	private String descricao;
	private int intVal;
	private int intVal2;
	private int intVal3;
	private double dblVal;
	private long lngVal;
	private Date dateVal;
	private String strVal;
	private boolean bShowAttr;
	
//Public
	
	public ItemDataVO()
	{
		this.itemDataId = "-1";
		this.descricao = "";
	}
	
	public ItemDataVO(
		Integer itemDataId,
		String descricao)
	{
		this.itemDataId = StringUtil.valToStr(itemDataId);
		this.descricao = descricao;
	}
		
	public ItemDataVO(
		Integer itemDataId,
		String descricao,
		boolean bShowAttr)
	{
		this.itemDataId = StringUtil.valToStr(itemDataId);
		this.descricao = descricao;
		this.bShowAttr = bShowAttr;
	}
	
	public ItemDataVO(
		String itemDataId,
		String descricao)
	{
		this.itemDataId = itemDataId;
		this.descricao = descricao;
	}
	
	public ItemDataVO(
		String itemDataId,
		String descricao,
		boolean bShowAttr)
	{
		this.itemDataId = itemDataId;
		this.descricao = descricao;
		this.bShowAttr = bShowAttr;
	}
	
	public ItemDataVO(
		String itemDataId,
		String descricao,
		int intVal)
	{
		this.itemDataId = itemDataId;
		this.descricao = descricao;
		this.intVal = intVal;
	}
	
	public ItemDataVO(
		String itemDataId,
		String descricao,
		int intVal,
		int intVal2)
	{
		this.itemDataId = itemDataId;
		this.descricao = descricao;
		this.intVal = intVal;
		this.intVal2 = intVal2;
	}
	
	public ItemDataVO(
		String itemDataId,
		String descricao,
		int intVal,
		int intVal2,
		int intVal3)
	{
		this.itemDataId = itemDataId;
		this.descricao = descricao;
		this.intVal = intVal;
		this.intVal2 = intVal2;
		this.intVal3 = intVal3;
	}
		
	public ItemDataVO(
		String itemDataId,
		String descricao,
		String strVal,
		int intVal,
		int intVal2)
	{
		this.itemDataId = itemDataId;
		this.descricao = descricao;
		this.strVal = strVal;
		this.intVal = intVal;
		this.intVal2 = intVal2;
	}
	
	public ItemDataVO(
		String itemDataId,
		String descricao,
		String strVal,
		int intVal,
		int intVal2,
		int intVal3)
	{
		this.itemDataId = itemDataId;
		this.descricao = descricao;
		this.strVal = strVal;
		this.intVal = intVal;
		this.intVal2 = intVal2;
		this.intVal3 = intVal3;
	}
			
	public ItemDataVO(ItemDataVO o)
	{
		this.itemDataId = o.itemDataId;
		this.descricao = o.descricao;
		this.intVal = intVal;
		this.dblVal = dblVal;
		this.lngVal = lngVal;
		this.dateVal = dateVal;
		this.strVal = strVal;
		this.bShowAttr = bShowAttr;
	}

	/* Methodes */
	
	public String toString()
	{
		String str = this.descricao;

		if( bShowAttr )
			str += " - valor: " + this.strVal;
		return str;
	}
	
	/* Getters/Setters */
		
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getItemDataId() {
		return itemDataId;
	}

	public void setItemDataId(String itemDataId) {
		this.itemDataId = itemDataId;
	}

	public int getIntVal() {
		return intVal;
	}

	public void setIntVal(int intVal) {
		this.intVal = intVal;
	}

	public double getDblVal() {
		return dblVal;
	}

	public void setDblVal(double dblVal) {
		this.dblVal = dblVal;
	}

	public long getLngVal() {
		return lngVal;
	}

	public void setLngVal(long lngVal) {
		this.lngVal = lngVal;
	}

	public Date getDateVal() {
		return dateVal;
	}

	public void setDateVal(Date dateVal) {
		this.dateVal = dateVal;
	}

	public String getStrVal() {
		return strVal;
	}

	public void setStrVal(String strVal) {
		this.strVal = strVal;
	}

	public boolean isShowAttr() {
		return bShowAttr;
	}

	public void setShowAttr(boolean bShowAttr) {
		this.bShowAttr = bShowAttr;
	}

	public int getIntVal2() {
		return intVal2;
	}

	public void setIntVal2(int intVal2) {
		this.intVal2 = intVal2;
	}

	public int getIntVal3() {
		return intVal3;
	}

	public void setIntVal3(int intVal3) {
		this.intVal3 = intVal3;
	}	
	
}
