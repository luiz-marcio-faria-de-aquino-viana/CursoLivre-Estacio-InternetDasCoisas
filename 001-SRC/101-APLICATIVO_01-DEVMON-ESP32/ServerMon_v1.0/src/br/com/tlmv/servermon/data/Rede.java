/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * Rede.java
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

package br.com.tlmv.servermon.data;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

import br.com.tlmv.servermon.AppDefs;
import br.com.tlmv.servermon.AppMain;
import br.com.tlmv.servermon.utils.EncodeUtil;
import br.com.tlmv.servermon.utils.JSONUtil;
import br.com.tlmv.servermon.utils.StringUtil;

//{
//"id":"1001",
//"seqNum":"012345",
//"scanNum":"9999",
//"equipment":"12cd34ab5678",
//"bssid":"1c:9d:72:83:76:10",
//"ssid":"CLARO_WIFI_2.4ghz",
//"capabilities":"[WPA2-PSK-CCMP+TKIP][WPA-PSK-CCMP+TKIP][ESS][WPS]",
//"center_freq0":"0",
//"center_freq1":"0",
//"channel_width":"0",
//"frequency":"2412",
//"level":"-21",
//"operator_friendly_name":"",
//"timestamp":"58748053207",
//"venue_name":"",
//"distance":"0.0",
//"power_watt":"0.0"
//}
public class Rede extends ObjetoBase
{
//Private
	
	private Integer id;								// object id
	
	private String seqNum;							// sequence number
	
	private Integer scanNum;						// network scan number
	
	private String equipment;						// equipment id
	
	private String bssid;							// address of the access point
	
	private String ssid;							// SSID of the access point
	
	private String capabilities;					// describes the authentication, key management, and encryption schemes supported by the access point
	
	private Integer centerFreq0;					// not used if the AP bandwidth is 20 MHz
													// if the AP use 40, 80, 160 or 320MHz, this is the center frequency (in MHz) 
													// if the AP use 80 + 80 MHz, this is the center frequency of the first segment (in MHz)
	
	private Integer centerFreq1;					// only used if the AP bandwidth is 80 + 80 MHz 
													// if the AP use 80 + 80 MHz, this is the center frequency of the second segment (in MHz)
	
	private Integer channelWidth;					// AP Channel bandwidth; one of 20MHZ, 40MHZ, 80MHZ, 160MHZ, +80MHZ, or 320MHZ
	
	private Integer frequency;						// center frequency of the primary 20 MHz frequency (in MHz) of the channel 
													// over which the client is communicating with the access point

	private Integer level;							// detected signal level in dBm, also known as the RSSI

	private String operatorFriendlyName;			// provider friendly name of the network if it is a passpoint network.

	private Long timestamp;							// timestamp in microseconds (since boot) when this result was last seen.

	private String venueName;						// local name

    private Double distance;						// spected AP distance in meters

    private Double powerWatt;						// spected AP power in mili watts
	
    /* Methodes */

    private double convertFromDecibelMeterToMiliWatt(double levelDecibelMeter) {
        double val_N = levelDecibelMeter / 10.0;
        double val_SB = Math.pow(10.0, val_N);
        double val_S = AppDefs.defWiFiMaxPowerMiliWatt * val_SB;
        return val_S;
    }

    private double convertFromMiliWattToMeters(double levelDecibelMeter) {
        double diff = (this.powerWatt - AppDefs.defWiFiLimitPowerMiliWatt) / AppDefs.defWiFiLimitPowerMiliWatt;

        double dist = (AppDefs.defWiFiLimitDistanceMeter / diff) * AppDefs.defWiFiPowerMiliWattLossPerMeter;
        return dist;
    }
    
//Public
	
	public Rede() 
	{
		super(AppDefs.BASE_OBJTYPE_REDE, "");
				
		this.id = -1;
		this.seqNum = "-1";
		this.scanNum = -1;
		this.equipment = "";		
		this.bssid = "";
		this.ssid = "";
		this.capabilities = "";
		this.centerFreq0 = 0;
		this.centerFreq1 = 0;
		this.channelWidth = 0;
		this.frequency = 0;
		this.level = 0;
		this.operatorFriendlyName = "";
		this.timestamp = 0L;
		this.venueName = "";
        this.distance = 0.0;
        this.powerWatt = 0.0;
	}

	public Rede(
		Integer id,
		String seqNum,
		Integer scanNum,
		String equipment,		
		String bssid,
		String ssid,
		String capabilities,
		Integer centerFreq0,
		Integer centerFreq1, 
		Integer channelWidth,
		Integer frequency, 
		Integer level,
		String operatorFriendlyName,
		Long timestamp,
		String venueName) 
	{
		super();
		
		this.id = -1;
		this.seqNum = "-1";
		this.scanNum = -1;
		this.equipment = "";
		this.bssid = "";
		this.ssid = "";
		this.capabilities = "";
		this.centerFreq0 = 0;
		this.centerFreq1 = 0;
		this.channelWidth = 0;
		this.frequency = 0;
		this.level = 0;
		this.operatorFriendlyName = "";
		this.timestamp = 0L;
		this.venueName = "";
        this.distance = 0.0;
        this.powerWatt = 0.0;
		
		this.baseInit(AppDefs.BASE_OBJTYPE_REDE, Integer.toString(this.id));
	}

	public Rede(String strJSON)
		throws Exception
	{
		JSONObject jsonObj = new JSONObject(strJSON);
		this.fromJSONObject(jsonObj);
	}

	public Rede(JSONObject jsonObj)
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
		if(strArr.length >= 15) {
			String strId = strArr[0];
			String strSeqNum = strId;
			String strScanNum = strArr[2];
			String strEquipment = strArr[3];
			String strBSSID = strArr[4];
			String strSSID = strArr[5];
			String strCapabilities = strArr[6];
			String strCenterFreq0 = strArr[7];
			String strCenterFreq1 = strArr[8];
			String strChannelWidth = strArr[9];
			String strFrequency = strArr[10];
			String strLevel = strArr[11];
			String strOperatorFriendlyName = strArr[12];
			String strTimestamp = strArr[13];
			String strVenueName = strArr[14];

			this.id = StringUtil.safeInt(strId);
			this.seqNum = strSeqNum;
			this.scanNum = StringUtil.safeInt(strScanNum);
			this.equipment = EncodeUtil.urlDecode(strEquipment);
			this.bssid = EncodeUtil.urlDecode(strBSSID);
			this.ssid = EncodeUtil.urlDecode(strSSID);
			this.capabilities = EncodeUtil.urlDecode(strCapabilities);
			this.centerFreq0 = StringUtil.safeInt(strCenterFreq0);
			this.centerFreq1 = StringUtil.safeInt(strCenterFreq1);
			this.channelWidth = StringUtil.safeInt(strChannelWidth);
			this.frequency = StringUtil.safeInt(strFrequency);
			this.level = StringUtil.safeInt(strLevel);
			this.operatorFriendlyName = EncodeUtil.urlDecode(strOperatorFriendlyName);
			this.timestamp = StringUtil.safeLng(strTimestamp);
			this.venueName = EncodeUtil.urlDecode(strVenueName);
	        this.powerWatt = convertFromDecibelMeterToMiliWatt(this.level);
	        this.distance = convertFromMiliWattToMeters(this.powerWatt);
			
			this.baseInit(AppDefs.BASE_OBJTYPE_REDE, Integer.toString(this.id));
		}
	}
	
	public String toStrJSON() 
	{
		DateFormat df1 = new SimpleDateFormat(AppDefs.FMT_DATETIME_INV_MASC);
		
		NumberFormat nf6 = StringUtil.newDecimalFormatEnUS(6); 

		String strJSON = "{ " +
			"\"id\":\"" + Integer.toString(this.id) + "\"," +
			"\"seq_num\":\"" + this.seqNum + "\"," +
			"\"scan_num\":\"" + Integer.toString(this.scanNum) + "\"," +
			"\"equipment\":\"" + this.equipment + "\"," +
			"\"bssid\":\"" + this.bssid + "\"," +
			"\"ssid\":\"" + this.ssid + "\"," +
			"\"capabilities\":\"" + this.capabilities + "\"," +
			"\"center_freq0\":\"" + Integer.toString(this.centerFreq0) + "\"," +
			"\"center_freq1\":\"" + Integer.toString(this.centerFreq1) + "\"," +
			"\"channel_width\":\"" + Integer.toString(this.channelWidth) + "\"," +
			"\"frequency\":\"" + Integer.toString(this.frequency) + "\"," +
			"\"level\":\"" + Integer.toString(this.level) + "\"," +
			"\"operator_friendly_name\":\"" + this.operatorFriendlyName + "\"," +
			"\"timestamp\":\"" + Long.toString(this.timestamp) + "\"," +
			"\"venue_name\":\"" + this.venueName + "\"," +
			"\"power_watt\":\"" + nf6.format(this.powerWatt) + "\"," +
	        "\"distance\":\"" + nf6.format(this.distance) + " }";
		return strJSON;
	}
	
	public String toHtmlTable()
	{
		NumberFormat nf6 = StringUtil.newDecimalFormatEnUS(6); 

		StringBuilder sb = new StringBuilder();
		sb.append(	"<tr>" +
						"<td>" + Integer.toString(this.id) + "</td>" +
						"<td>" + this.scanNum + "</td>" +
						"<td>" + this.equipment + "</td>" +
						"<td>" + this.ssid + "</td>" +
						"<td>" + this.bssid + "</td>" +
						"<td>" + Integer.toString(this.level) + "</td>" +
						"<td>" + nf6.format(this.powerWatt) + "</td>" +
						"<td>" + nf6.format(this.distance) + "</td>" +
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
		this.seqNum = JSONUtil.safeStrFromJSON(jsonObj, "seq_num", "-1");
		this.scanNum = JSONUtil.safeIntFromJSON(jsonObj, "scan_num", -1);
		this.equipment = JSONUtil.safeStrFromJSON(jsonObj, "equipment", "");
		this.bssid = JSONUtil.safeStrFromJSON(jsonObj, "bssid", "");
		this.ssid = JSONUtil.safeStrFromJSON(jsonObj, "ssid", "");
		this.capabilities = JSONUtil.safeStrFromJSON(jsonObj, "capabilities", "");
		this.centerFreq0 = JSONUtil.safeIntFromJSON(jsonObj, "center_freq0", 0);
		this.centerFreq1 = JSONUtil.safeIntFromJSON(jsonObj, "center_freq1", 0);
		this.channelWidth = JSONUtil.safeIntFromJSON(jsonObj, "channel_width", 0);
		this.frequency = JSONUtil.safeIntFromJSON(jsonObj, "frequency", 0);
		this.level = JSONUtil.safeIntFromJSON(jsonObj, "level", 0);
		this.operatorFriendlyName = JSONUtil.safeStrFromJSON(jsonObj, "operator_friendly_name", "");
		this.timestamp = JSONUtil.safeLngFromJSON(jsonObj, "timestamp", 0L);
		this.venueName = JSONUtil.safeStrFromJSON(jsonObj, "venue_name", "");
		this.powerWatt = JSONUtil.safeDblFromJSON(jsonObj, "power_watt", 0.0);
		this.distance = JSONUtil.safeDblFromJSON(jsonObj, "distance", 0.0);
		
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

	public String getBssid() {
		return bssid;
	}

	public void setBssid(String bssid) {
		this.bssid = bssid;
	}

	public String getSsid() {
		return ssid;
	}

	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	public String getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(String capabilities) {
		this.capabilities = capabilities;
	}

	public Integer getCenterFreq0() {
		return centerFreq0;
	}

	public void setCenterFreq0(Integer centerFreq0) {
		this.centerFreq0 = centerFreq0;
	}

	public Integer getCenterFreq1() {
		return centerFreq1;
	}

	public void setCenterFreq1(Integer centerFreq1) {
		this.centerFreq1 = centerFreq1;
	}

	public Integer getChannelWidth() {
		return channelWidth;
	}

	public void setChannelWidth(Integer channelWidth) {
		this.channelWidth = channelWidth;
	}

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getOperatorFriendlyName() {
		return operatorFriendlyName;
	}

	public void setOperatorFriendlyName(String operatorFriendlyName) {
		this.operatorFriendlyName = operatorFriendlyName;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getVenueName() {
		return venueName;
	}

	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	public String getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(String seqNum) {
		this.seqNum = seqNum;
	}

	public Integer getScanNum() {
		return scanNum;
	}

	public void setScanNum(Integer scanNum) {
		this.scanNum = scanNum;
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Double getPowerWatt() {
		return powerWatt;
	}

	public void setPowerWatt(Double powerWatt) {
		this.powerWatt = powerWatt;
	}
			
}
