/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * Evento.java
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
//	"data_hora":"20230128113530",
//	"equipamento":"b827ebbf9d51",
//	"luz_acesa":"1",
//	"movimento":"1",
//	"fumaca":"0.987654",
//	"gas_carbonico":"0.123456",
//	"gas_natural":"0.123654",
//	"humidade_ar":"0.654321",
//  "temperatura":"32.123456",
// 	"ligarVent":"1",
//	"ligarArCond":"1",
//	"ligarDry":"1" }
public class Evento extends ObjetoBase
{
	private static final long serialVersionUID = 202302020053L;
	
	//Private
	private Integer id;
	private Integer seqNum;
	private Date dataHora;
	private String equipamento;
	private Integer luzAcesa;
	private Integer movimento;
	private Double fumaca;
	private Double gasCarbonico;
	private Double gasNatural;
	private Double temperatura;
	private Double humidadeAr;
	private Integer ligarVent;
	private Integer ligarArCond;
	private Integer ligarDry;
	
	private Integer nextId() {
		Date dataAtual = new Date();
		
		long dataAtualMili = (dataAtual.getTime() / 100) % 100000;
		return ((int)dataAtualMili);
	}
	
//Public
	
	public Evento() 
	{
		super(AppDefs.BASE_OBJTYPE_EVENTO, "");
				
		this.id = this.nextId();
		this.seqNum = -1;		
		this.dataHora = new Date();
		this.equipamento = "";
		this.luzAcesa = 0;
		this.movimento = 0;
		this.fumaca = 0.0;
		this.gasCarbonico = 0.0;
		this.gasNatural = 0.0;
		this.temperatura = 0.0;
		this.humidadeAr = 0.0;
		this.ligarVent = 0;
		this.ligarArCond = 0;
		this.ligarDry = 0;
	}

	public Evento(
		Integer id,
		Integer seqNum,
		Date dataHora,
		String equipamento,
		Integer luzAcesa,
		Integer movimento,
		Double fumaca,
		Double gasCarbonico,
		Double gasNatural,
		Double temperatura,
		Double humidadeAr,
		Integer ligarVent,
		Integer ligarArCond,
		Integer ligarDry) 
	{
		super();
		
		this.id = id;
		this.seqNum = seqNum;
		this.dataHora = dataHora;
		this.equipamento = equipamento;
		this.luzAcesa = luzAcesa;
		this.movimento = movimento;
		this.fumaca = fumaca;
		this.gasCarbonico = gasCarbonico;
		this.gasNatural = gasNatural;
		this.temperatura = temperatura;
		this.humidadeAr = humidadeAr;
		this.ligarVent = ligarVent;
		this.ligarArCond = ligarArCond;
		this.ligarDry = ligarDry; 
		
		this.baseInit(AppDefs.BASE_OBJTYPE_EVENTO, Integer.toString(this.id));
	}

	public Evento(String strJSON)
		throws Exception
	{
		JSONObject jsonObj = new JSONObject(strJSON);
		this.fromJSONObject(jsonObj);
	}

	public Evento(JSONObject jsonObj)
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
		DateFormat df1 = new SimpleDateFormat(AppDefs.FMT_DATETIME_INV_MASC);
		
		NumberFormat nf1 = StringUtil.newDecimalFormatEnUS(6);
		
		String[] strArr = strData.split(",");
		if(strArr.length >= 12) {
			String strSeqNum = strArr[0];
			String strEquipamento = strArr[1];
			String strLuzAcesa = strArr[2];
			String strMovimento = strArr[3];
			String strFumaca = strArr[4];
			String strGasCarbonico = strArr[5];
			String strGasNatural = strArr[6];
			String strTemperatura = strArr[7];
			String strHumidadeAr = strArr[8];
			String strLigarVent = strArr[9];
			String strLigarArCond = strArr[10];
			String strLigarDry = strArr[11];
					
			this.id = this.nextId();
			this.seqNum = StringUtil.safeInt(strSeqNum);
			this.dataHora = new Date();			//StringUtil.safeDate(df1, strDataHora);
			this.equipamento = strEquipamento;
			this.luzAcesa = StringUtil.safeInt(strLuzAcesa);
			this.movimento = StringUtil.safeInt(strMovimento);
			this.fumaca = StringUtil.safeDbl(nf1, strFumaca);
			this.gasCarbonico = StringUtil.safeDbl(nf1, strGasCarbonico);
			this.gasNatural = StringUtil.safeDbl(nf1, strGasNatural);
			this.temperatura = StringUtil.safeDbl(nf1, strTemperatura);
			this.humidadeAr = StringUtil.safeDbl(nf1, strHumidadeAr);
			this.ligarVent = StringUtil.safeInt(strLigarVent);
			this.ligarArCond = StringUtil.safeInt(strLigarArCond);
			this.ligarDry = StringUtil.safeInt(strLigarDry);
		}
	}
	
	public String toStrJSON() 
	{
		DateFormat df1 = new SimpleDateFormat(AppDefs.FMT_DATETIME_INV_MASC);
		
		NumberFormat nf1 = StringUtil.newDecimalFormatEnUS(2); 

		String strJSON = "{ " +
			"\"id\":\"" + Integer.toString(this.id) + "\"," +
			"\"seqNum\":\"" + Integer.toString(this.seqNum) + "\"," +
			"\"dataHora\":\"" + df1.format(this.dataHora) + "\"," +
			"\"equipamento\":\"" + this.equipamento + "\"," +
			"\"luz_acesa\":\"" + Integer.toString(this.luzAcesa) + "\"," +
			"\"movimento\":\"" + Integer.toString(this.movimento) + "\"," +
			"\"fumaca\":\"" + nf1.format(this.fumaca) + "\"," +
			"\"gas_carbonico\":\"" + nf1.format(this.gasCarbonico) + "\"," +
			"\"gas_natural\":\"" + nf1.format(this.gasNatural) + "\"," +
			"\"temperatura\":\"" + nf1.format(this.temperatura) + "\"," +
			"\"humidade_ar\":\"" + nf1.format(this.humidadeAr) + "\"," +
			"\"ligarVent\":\"" + Integer.toString(this.ligarVent) + "\"," +
			"\"ligarArCond\":\"" + Integer.toString(this.ligarArCond) + "\"," +
			"\"ligarDry\":\"" + Integer.toString(this.ligarDry) + " }";
		return strJSON;
	}

	public String toHtmlTable()
	{
		DateFormat df1 = new SimpleDateFormat(AppDefs.FMT_DATETIME_INV_MASC);
		
		NumberFormat nf1 = StringUtil.newDecimalFormatEnUS(2); 

		StringBuilder sb = new StringBuilder();
		sb.append(	"<tr>" +
						"<td>" + Integer.toString(this.id) + "</td>" +
						"<td>" + df1.format(this.dataHora) + "</td>" +
						"<td>" + this.equipamento + "</td>" +
						"<td>" + Integer.toString(this.movimento) + "</td>" +
						"<td>" + Integer.toString(this.luzAcesa) + "</td>" +
						"<td>" + nf1.format(this.fumaca) + "</td>" +
						"<td>" + nf1.format(this.gasCarbonico) + "</td>" +
						"<td>" + nf1.format(this.gasNatural) + "</td>" +
						"<td>" + nf1.format(this.temperatura) + "</td>" +
						"<td>" + nf1.format(this.humidadeAr) + "</td>" +
						"<td>" + Integer.toString(this.ligarVent) + "</td>" +
						"<td>" + Integer.toString(this.ligarArCond) + "</td>" +
						"<td>" + Integer.toString(this.ligarDry) + "</td>" +
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
		this.seqNum = JSONUtil.safeIntFromJSON(jsonObj, "seqNum", -1);
		this.dataHora = JSONUtil.safeDateFromJSON(jsonObj, "data_hora", dataAtual);
		this.equipamento = JSONUtil.safeStrFromJSON(jsonObj, "equipamento", "000000000000");
		this.luzAcesa = JSONUtil.safeIntFromJSON(jsonObj, "luz_acesa", 0);
		this.movimento = JSONUtil.safeIntFromJSON(jsonObj, "movimento", 0);
		this.fumaca = JSONUtil.safeDblFromJSON(jsonObj, "fumaca", 0.0);
		this.gasCarbonico = JSONUtil.safeDblFromJSON(jsonObj, "gas_carbonico", 0.0);
		this.gasNatural = JSONUtil.safeDblFromJSON(jsonObj, "gas_natural", 0.0);
		this.temperatura = JSONUtil.safeDblFromJSON(jsonObj, "temperatura", 0.0);
		this.humidadeAr = JSONUtil.safeDblFromJSON(jsonObj, "humidade_ar", 0.0);
		this.ligarVent = JSONUtil.safeIntFromJSON(jsonObj, "ligar_vent", 0);
		this.ligarArCond = JSONUtil.safeIntFromJSON(jsonObj, "ligar_arcond", 0);
		this.ligarDry = JSONUtil.safeIntFromJSON(jsonObj, "ligar_dry", 0);
		
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

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public String getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(String equipamento) {
		this.equipamento = equipamento;
	}

	public Integer getLuzAcesa() {
		return luzAcesa;
	}

	public void setLuzAcesa(Integer luzAcesa) {
		this.luzAcesa = luzAcesa;
	}

	public Double getGasCarbonico() {
		return gasCarbonico;
	}

	public void setGasCarbonico(Double gasCarbonico) {
		this.gasCarbonico = gasCarbonico;
	}

	public Double getHumidadeAr() {
		return humidadeAr;
	}

	public void setHumidadeAr(Double humidadeAr) {
		this.humidadeAr = humidadeAr;
	}

	public Double getGasNatural() {
		return gasNatural;
	}

	public void setGasNatural(Double gasNatural) {
		this.gasNatural = gasNatural;
	}

	public Integer getMovimento() {
		return movimento;
	}

	public void setMovimento(Integer movimento) {
		this.movimento = movimento;
	}

	public Double getFumaca() {
		return fumaca;
	}

	public void setFumaca(Double fumaca) {
		this.fumaca = fumaca;
	}

	public Double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(Double temperatura) {
		this.temperatura = temperatura;
	}

	public Integer getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(Integer seqNum) {
		this.seqNum = seqNum;
	}

	public Integer getLigarVent() {
		return ligarVent;
	}

	public void setLigarVent(Integer ligarVent) {
		this.ligarVent = ligarVent;
	}

	public Integer getLigarArCond() {
		return ligarArCond;
	}

	public void setLigarArCond(Integer ligarArCond) {
		this.ligarArCond = ligarArCond;
	}

	public Integer getLigarDry() {
		return ligarDry;
	}

	public void setLigarDry(Integer ligarDry) {
		this.ligarDry = ligarDry;
	}
			
}
