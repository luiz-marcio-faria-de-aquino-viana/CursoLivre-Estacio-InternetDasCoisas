/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * Defs.java
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

package br.com.tlmv.sensormovapp;

public class Defs
{
    // Result codes
    //
    public static int RS_OK = 0;
    public static int RS_ERR = 1;

    public static String DATE_FORMAT = "MM/dd/yyyy";
    public static String DATE_FORMAT_SIMPL = "MM/dd/yy HH:mm";

    public static String TIME_FORMAT = "HH:mm";

    public static String PROTO_HTTP = "http";
    public static String PROTO_HTTPS = "https";

    public static String SERVER_IPADDR = "172.16.2.72";
    public static int SERVER_PORT = 9090;

    public static String REQURL_ARRREDE_ADD = "%s://%s:%s/ServerMon/ArrRede/Add/%s";

    //public static String DEFAULT_PROTO = Defs.PROTO_HTTPS;
    public static String DEFAULT_PROTO = Defs.PROTO_HTTP;

    // Constants
    //
    public static int CONST_MAX_WAITING_TIME_FOR_THREAD = 5000;

    // HTTP Method
    //
    public static int HTTP_METHOD_GET = 3001;
    public static int HTTP_METHOD_POST = 3002;

    // Return Type
    //
    public static int HTTP_RET_XML = 0;
    public static int HTTP_RET_IMG = 1;
    public static int HTTP_RET_XMLLIST = 2;

    // Network events
    //
    public static int SOCK_ONRECEIVE = 200;
    public static int SOCK_ONCLOSE = 201;

    // Data buffer
    //
    public static int BUFSIZE = 256 * 1024;	// 256KB

    // Permissions
    //
    public static int PERMISSION_INTERNET					= 1001;
    public static int PERMISSION_READ_PHONE_STATE			= 1002;
    public static int PERMISSION_WRITE_EXTERNAL_STORAGE		= 1003;
    public static int PERMISSION_ACCESS_WIFI_STATE			= 1004;
    public static int PERMISSION_CALL_PHONE					= 1005;

    // Mime Types
    //
    public static String[] MIME_TYPE = {
            "pdf;application/pdf",
            "xls;application/vnd.ms-excel",
            "doc;application/msword",
            "zip;application/x-compressed",
            "bmp;image/bmp",
            "gif;image/gif",
            "jpg;image/jpeg",
            "htm;text/html",
            "html;text/html",
            "rtf;application/rtf",
            "xml;text/xml",
            "txt;text/plain",
            "ppt;application/vnd.ms-powerpoint",
            "exe;application/octet-stream",
            "dwf;drawing/x-dwf",
            "dwg;application/dwg"
    };

    // WiFi Parameters
    //
    public static final Double defWiFiMaxPowerDecibelMeter = 20.0;
    public static final Double defWiFiMaxPowerMiliWatt = 100.0;
    public static final Double defWiFiPowerMiliWattLossPerMeter = 1.0;
    public static final Double defWiFiLimitPowerMiliWatt = 0.000025;
    public static final Double defWiFiLimitDistanceMeter = 100.0;

}
