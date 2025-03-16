/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * RedeVO.java
 * Autor:
 * - Luiz Marcio Faria de Aquino Viana, Pós-D.Sc. - Engenheiro, 08/02/2023
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

package br.com.tlmv.sensormovapp.vo;

import android.net.wifi.ScanResult;

import java.text.NumberFormat;

import br.com.tlmv.sensormovapp.Defs;
import br.com.tlmv.sensormovapp.MainActivity;
import br.com.tlmv.sensormovapp.util.EncodeUtil;
import br.com.tlmv.sensormovapp.util.FormatUtil;

public class RedeVO
{
//Static
    private static String NULLSTR = EncodeUtil.urlEncode("?");

//Private
    private static Integer seqNumId = 1001;

    private Integer id;								// object id

    private String seqNum;                          // sequential number

    private Integer scanNum;                        // network scanning number

    private String equipment;                       // equipment id

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

    private static Integer nextId() {
        return RedeVO.seqNumId++;
    }

    private double convertFromDecibelMeterToMiliWatt(double levelDecibelMeter) {
        double val_N = levelDecibelMeter / 10.0;
        double val_SB = Math.pow(10.0, val_N);
        double val_S = Defs.defWiFiMaxPowerMiliWatt * val_SB;
        return val_S;
    }

    private double convertFromMiliWattToMeters(double levelDecibelMeter) {
        double diff = (this.powerWatt - Defs.defWiFiLimitPowerMiliWatt) / Defs.defWiFiLimitPowerMiliWatt;

        double dist = (Defs.defWiFiLimitDistanceMeter / diff) * Defs.defWiFiPowerMiliWattLossPerMeter;
        return dist;
    }

//Public

    public RedeVO()
    {
        this.id = RedeVO.nextId();
        this.seqNum = Integer.toString(this.id);
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

    public RedeVO(
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
        this.id = RedeVO.nextId();
        this.seqNum = Integer.toString(this.id);
        this.scanNum = scanNum;
        this.equipment = equipment;
        this.bssid = bssid;
        this.ssid = ssid;
        this.capabilities = capabilities;
        this.centerFreq0 = centerFreq0;
        this.centerFreq1 = centerFreq1;
        this.channelWidth = channelWidth;
        this.frequency = frequency;
        this.level = level;
        this.operatorFriendlyName = operatorFriendlyName;
        this.timestamp = timestamp;
        this.venueName = venueName;
        this.powerWatt = convertFromDecibelMeterToMiliWatt(this.level);
        this.distance = convertFromMiliWattToMeters(this.powerWatt);
    }

    public RedeVO(Integer scanNum, ScanResult o)
    {
        this.id = RedeVO.nextId();
        this.seqNum = Integer.toString(this.id);
        this.scanNum = scanNum;
        this.equipment = MainActivity.gApp.getDeviceName();
        this.bssid = o.BSSID;
        this.ssid = o.SSID;
        this.capabilities = o.capabilities;
        this.centerFreq0 = o.centerFreq0;
        this.centerFreq1 = o.centerFreq1;
        this.channelWidth = o.channelWidth;
        this.frequency = o.frequency;
        this.level = o.level;
        this.operatorFriendlyName = o.operatorFriendlyName.toString();
        this.timestamp = o.timestamp;
        this.venueName = o.venueName.toString();
        this.powerWatt = convertFromDecibelMeterToMiliWatt(this.level);
        this.distance = convertFromMiliWattToMeters(this.powerWatt);
    }

    /* Methodes */

    public String toEncodedUrl()
    {
        NumberFormat nf3 = FormatUtil.newDecimalFormat(3);
        NumberFormat nf6 = FormatUtil.newDecimalFormat(6);

        String strmsg =
                "(" +
                Integer.toString(this.id) + "," +
                this.seqNum + "," +
                Integer.toString(this.scanNum) + "," +
                ( ( this.equipment != null && !"".equals(this.equipment) ) ? EncodeUtil.urlEncode(this.equipment) : RedeVO.NULLSTR) + "," +
                ( ( this.bssid != null && !"".equals(this.bssid) ) ? EncodeUtil.urlEncode(this.bssid) : RedeVO.NULLSTR) + "," +
                ( ( this.ssid != null && !"".equals(this.ssid) ) ? EncodeUtil.urlEncode(this.ssid) : RedeVO.NULLSTR) + "," +
                ( ( this.capabilities != null && !"".equals(this.capabilities) ) ? EncodeUtil.urlEncode(this.capabilities) : RedeVO.NULLSTR) + "," +
                Integer.toString(this.centerFreq0) + "," +
                Integer.toString(this.centerFreq1) + "," +
                Integer.toString(this.channelWidth) + "," +
                Integer.toString(this.frequency) + "," +
                Integer.toString(this.level) + "," +
                ( ( this.operatorFriendlyName != null && !"".equals(this.operatorFriendlyName) ) ? EncodeUtil.urlEncode(this.operatorFriendlyName) : RedeVO.NULLSTR) + "," +
                Long.toString(this.timestamp) + "," +
                ( ( this.venueName != null && !"".equals(this.venueName) ) ? EncodeUtil.urlEncode(this.venueName) : RedeVO.NULLSTR) +
                ")";
        return strmsg;
    }

    public void debug()
    {
        NumberFormat nf3 = FormatUtil.newDecimalFormat(3);
        NumberFormat nf6 = FormatUtil.newDecimalFormat(6);

        String strmsg =
                "Id: " + Integer.toString(this.id) + "," +
                ";SeqNum: " + this.seqNum + "," +
                ";ScanNum: " + Integer.toString(this.scanNum) + "," +
                ";Equipment: " + EncodeUtil.urlEncode(this.equipment) + "," +
                ";BSSID: " + this.bssid +
                ";SSID: " + this.ssid +
                ";Capabilities: " + this.capabilities +
                ";CenterFreq0: " + Integer.toString(this.centerFreq0) +
                ";CenterFreq1: " + Integer.toString(this.centerFreq1) +
                ";ChannelWidth: " + Integer.toString(this.channelWidth) +
                ";Frequency: " + Integer.toString(this.frequency) +
                ";Level: " + Integer.toString(this.level) +
                ";OperatorFriendlyName: " + this.operatorFriendlyName +
                ";Timestamp: " + Long.toString(this.timestamp) +
                ";VenueName: " + this.venueName +
                ";Distance: " + nf3.format(this.distance) +
                ";PowerWatt: " + nf6.format(this.powerWatt);
        System.err.println(strmsg);
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

    public static Integer getSeqNumId() {
        return seqNumId;
    }

    public static void setSeqNumId(Integer seqNumId) {
        RedeVO.seqNumId = seqNumId;
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
