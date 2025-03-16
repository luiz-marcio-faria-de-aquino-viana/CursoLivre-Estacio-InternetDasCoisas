/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * Regra.java
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

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

import br.com.tlmv.servermon.AppDefs;
import br.com.tlmv.servermon.AppMain;
import br.com.tlmv.servermon.utils.JSONUtil;
import br.com.tlmv.servermon.utils.StringUtil;

//{
//	"id":"1001",
//	"nome_regra":"regra1",
//	"nome_campo":"fumaca",
//	"operacao":"gt",
//	"valor":"0.2",
//	"se_faca":"ALERTA_FUMACA",
//	"senao_faca":"regra10" }
public class Regra extends ObjetoBase
{
//Private
	private Integer id;
	private String nomeRegra;
	private String nomeCampo;
	private String operacao;
	private Double valor;
	private String seFaca;
	private String senaoFaca;
	
//Public
	
	public Regra() 
	{
		super(AppDefs.BASE_OBJTYPE_REGRA, "");
				
		this.id = -1;
		this.nomeRegra = "";
		this.nomeCampo = "";
		this.operacao = "";
		this.valor = 0.0;
		this.seFaca = "";
		this.senaoFaca = "";
	}

	public Regra(
		Integer id,
		String nomeRegra,
		String nomeCampo,
		String operacao,
		Double valor,
		String seFaca,
		String senaoFaca) 
	{
		super();
		
		this.id = id;
		this.nomeRegra = nomeRegra;
		this.nomeCampo = nomeCampo;
		this.operacao = operacao;
		this.valor = valor;
		this.seFaca = seFaca;
		this.senaoFaca = senaoFaca;
		
		this.baseInit(AppDefs.BASE_OBJTYPE_REGRA, Integer.toString(this.id));
	}

	public Regra(String strJSON)
		throws Exception
	{
		JSONObject jsonObj = new JSONObject(strJSON);
		this.fromJSONObject(jsonObj);
	}

	public Regra(JSONObject jsonObj)
		throws Exception
	{
		this.fromJSONObject(jsonObj);
	}
	
	/* Methodes */
	
	@Override
	public void init(JSONObject jsonObj) 
	{
		try {
			this.fromJSONObject(jsonObj);
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "init()", e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void fromStrData(String strData) {
		NumberFormat nf1 = StringUtil.newDecimalFormatEnUS(6);
		
		String[] strArr = strData.split(",");
		if(strArr.length >= 6) {
			String strId = strArr[0];
			String strNomeRegra = strArr[1];
			String strNomeCampo = strArr[2];
			String strOperacao = strArr[3];
			String strValor = strArr[4];
			String strSeFaca = strArr[5];
			String strSenaoFaca = strArr[6];
					
			this.id = StringUtil.safeInt(strId);
			this.nomeRegra = strNomeRegra;
			this.nomeCampo = strNomeCampo;
			this.operacao = strOperacao;
			this.valor = StringUtil.safeDbl(nf1, strValor);
			this.seFaca = strSeFaca;
			this.senaoFaca = strSenaoFaca;
		}
	}
	
	public String toStrJSON() 
	{
		DateFormat df1 = new SimpleDateFormat(AppDefs.FMT_DATETIME_INV_MASC);
		
		NumberFormat nf1 = StringUtil.newDecimalFormatEnUS(6); 

		String strJSON = "{ " +
			"\"id\":\"" + Integer.toString(this.id) + "\"," +
			"\"nome_regra\":\"" + this.nomeRegra + "\"," +
			"\"nome_campo\":\"" + this.nomeCampo + "\"," +
			"\"operacao\":\"" + this.operacao + "\"," +
			"\"valor\":\"" + nf1.format(this.valor) + "\"," +
			"\"se_faca\":\"" + this.seFaca + "\"," +
			"\"senao_faca\":\"" + this.senaoFaca + " }";
		return strJSON;
	}
	
	public String toHtmlTable()
	{
		NumberFormat nf2 = StringUtil.newDecimalFormatEnUS(2); 

		StringBuilder sb = new StringBuilder();
		sb.append(	"<tr>" +
						"<td>" + Integer.toString(this.id) + "</td>" +
						"<td>" + this.nomeRegra + "</td>" +
						"<td>" + this.nomeCampo + "</td>" +
						"<td>" + this.operacao + "</td>" +
						"<td>" + nf2.format(this.valor) + "</td>" +
						"<td>" + this.seFaca + "</td>" +
						"<td>" + this.senaoFaca + "</td>" +
					"</tr>");
		return sb.toString();
	}

	public void fromStrJSON(String strJSON)
		throws Exception
	{
		JSONObject jsonObj = new JSONObject(strJSON);
		this.fromJSONObject(jsonObj);
	}
	
	public JSONObject toJSON() 
		throws Exception
	{
		String strJSON = this.toStrJSON();
		JSONObject jsonObj = new JSONObject(strJSON);
		return jsonObj;
	}

	public void fromJSONObject(JSONObject jsonObj)
		throws Exception
	{
		Date dataAtual = new Date();

		this.id = JSONUtil.safeIntFromJSON(jsonObj, "id", -1);
		this.nomeRegra = JSONUtil.safeStrFromJSON(jsonObj, "nome_regra", "");
		this.nomeCampo = JSONUtil.safeStrFromJSON(jsonObj, "nome_campo", "");
		this.operacao = JSONUtil.safeStrFromJSON(jsonObj, "operacao", "");
		this.valor = JSONUtil.safeDblFromJSON(jsonObj, "valor", 0.0);
		this.seFaca = JSONUtil.safeStrFromJSON(jsonObj, "se_faca", "");
		this.senaoFaca = JSONUtil.safeStrFromJSON(jsonObj, "senao_faca", "");
		
		this.setBaseDescription(Integer.toString(this.id));
	}

	@Override
	public void debug(int debugLevel) {
		if( !AppMain.getApp().getErr().checkDebugLevel(debugLevel) ) return;
		
		String strJSON = this.toStrJSON(); 
		AppMain.getApp().getErr().writeDebug(debugLevel, this.getClass().getName(), "debug()", strJSON);
	}

	/* Getters/Setters */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeRegra() {
		return nomeRegra;
	}

	public void setNomeRegra(String nomeRegra) {
		this.nomeRegra = nomeRegra;
	}

	public String getNomeCampo() {
		return nomeCampo;
	}

	public void setNomeCampo(String nomeCampo) {
		this.nomeCampo = nomeCampo;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getSeFaca() {
		return seFaca;
	}

	public void setSeFaca(String seFaca) {
		this.seFaca = seFaca;
	}

	public String getSenaoFaca() {
		return senaoFaca;
	}

	public void setSenaoFaca(String senaoFaca) {
		this.senaoFaca = senaoFaca;
	}
			
}
