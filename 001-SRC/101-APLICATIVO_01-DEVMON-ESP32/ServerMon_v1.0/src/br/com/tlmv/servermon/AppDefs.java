/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * AppDefs.java
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

package br.com.tlmv.servermon;

import java.util.Date;

public class AppDefs 
{
//Public

	//DEBUG_LEVEL
	//
	public static final int DEBUG_LEVEL_00 = 0;
	public static final int DEBUG_LEVEL_01 = 1;
	public static final int DEBUG_LEVEL_02 = 2;
	public static final int DEBUG_LEVEL_03 = 3;
	public static final int DEBUG_LEVEL_04 = 4;
	public static final int DEBUG_LEVEL_05 = 5;
	//
	public static final int DEBUG_LEVEL_99 = 99;	
	
	public static final int DEBUG_LEVEL = AppDefs.DEBUG_LEVEL_01;	
	
	//APPLICATION_NAME
	//
	public static final String APP_NAME = "ServerMon"; 
	
	public static final String APP_VERSAO = "1.0.20230128";

	public static final String APP_COPYRIGHT =
		"Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI\n" +
		"Autor: Luiz Marcio Faria de Aquino Viana, Pos-D.Sc.\n" +
		"E-mail: luiz.marcio.viana@gmail.com\n" +
		"Outro e-mail: luiz_marcio@hotmail.com\n" +
		"Telefone: +55-21-99983-7207\n";

	public static final String APP_ICON = "/br/com/tlmv/servermon/res/servermonapp_48x48.png";

	public static final String APP_HOME = "SERVERMON_HOME";
	
	//DEFAULT_CONTEXT_VALUES
	//
	public static final String CTX_DEFAULT_HOME_DIR = "/home/lmarcio/102-CURSOS/101-CURSO_LIVRE_PRESENCIAL-UNIDADE_SULACAP-INTRODUCAO_INTERNET_DAS_COISAS/991-EXEMPLOS/101-APLICATIVO_01-DEVMON-ESP32";
	
	public static final String CTX_HOME_BIN = "/Bin";
	public static final String CTX_HOME_CONFIG = "/Config";
	public static final String CTX_HOME_DATA = "/Data";
	public static final String CTX_HOME_DATA_INIT = "/Data/__INIT__";
	public static final String CTX_HOME_LOGS = "/Logs";
	public static final String CTX_HOME_WEBDIYDIR = "/WEB-DIY";

	public static final String CTX_CONFIG_FILENAME = "servermon_appconfig.xml";
	public static final String CTX_LOG_FILENAME = "servermon.log";

	//DEFAULT_CONFIG_TAGS
	//
	public static final String CFG_DEBUG_LEVEL = "DebugLevel";
	
	//INET_PROTOCOL
	//
	public static final String DEF_HTTP_PROTOCOL = "http";
	public static final String DEF_HTTPS_PROTOCOL = "https";
	
	//CONNECTION_TIMEOUT
	//
	public static final Integer DEF_CONNECTION_TIMEOUT = 30 * 1000;
	public static final Integer DEF_READ_TIMEOUT = 60 * 1000;
	
	//FILE_READ_BUFFER_SIZE
	//
	public static final Integer DEF_FILE_READ_BUFFER_SIZE = 4096;
		
	//WEBSERVICE_LIMITS
	//
	public static final String WS_MAX_SEARCH_LIMIT = "100";

	//SERVICE_THREADS
	public static final Integer THREAD_MAX_WORKERS 			= 5;				/* 5 workes per query */
	public static final Integer THREAD_SLEEP_TIME 			= 100;				/* 100 milisecond */
	public static final Integer THREAD_FAILURE_SLEEP_TIME	= 5 * 1000;			/* 5 segundo */
	
	//COMMAND_SWITCH - STRING
	public static final String CMD_HELP_STR = "-help";
	public static final String CMD_TEST_STR = "-test";
	public static final String CMD_DATACREATOR_STR = "-creator";
	public static final String CMD_RESTSERVER_STR = "-rest";

	//COMMAND_SWITCH - VALUES
	public static final Integer CMD_HELP_VAL = 1;
	public static final Integer CMD_TEST_VAL = 2;
	public static final Integer CMD_DATACREATOR_VAL = 4;
	public static final Integer CMD_RESTSERVER_VAL = 8;
	
	//USAGE_INFO
	//
	public static final String HLP_USAGE_INFO =
		    "AJUDA\n\n" +
			"Use: servermon -help                          - para apresentar estas informacoes de ajuda.\n" +
			" ou  servermon -test                          - para executar os testes dos componentes da aplicacao.\n" +	
			" ou  servermon -creator                       - para cria o banco de dados inicial da aplicacao.\n" +	
			" ou  servermon -rest                          - para iniciar o servidor RESTfull.\n";	
	
	//FILE_EXTENSIONS
	public static final String EXT_TMP = ".$";
	public static final String EXT_DAT = ".dat";
	public static final String EXT_JPG = ".jpg";
	public static final String EXT_JPEG = ".jpeg";
	public static final String EXT_PNG = ".png";
	public static final String EXT_HTML = ".html";
	public static final String EXT_LOG = ".log";
	public static final String EXT_CSV = ".csv";
	
	// MIMETYPE
	public static final String MIMETYPE_XML = "application/soap+xml";
	public static final String MIMETYPE_JSON = "application/json";
	public static final String MIMETYPE_JPG = "image/jpeg";
	public static final String MIMETYPE_JPEG = "image/jpeg";
	public static final String MIMETYPE_PNG = "image/png";
	public static final String MIMETYPE_HTML = "text/html";
	public static final String MIMETYPE_BLOB = "binary/octet-stream";
	
	//TIME_MILI
	public static final long M1 = 60 * 1000;	
	public static final long M5 = 5 * AppDefs.M1;	
	public static final long M10 = 10 * AppDefs.M1;	
	public static final long M15 = 15 * AppDefs.M1;	
	public static final long M30 = 30 * AppDefs.M1;	
	//
	public static final long H1 = 60 * AppDefs.M1;
	public static final long H24 = 24 * AppDefs.H1;	
	//
	public static final long D1 = AppDefs.H24;	
	public static final long D5 = 5 * AppDefs.D1;	
	public static final long D10 = 10 * AppDefs.D1;	
	public static final long D15 = 15 * AppDefs.D1;	
	public static final long D30 = 30 * AppDefs.D1;	
	
	//OBJECT_ID SEQUENCE
	public static final Integer SEQ_INIT_EVENTO = 100001;
	
	//BASE_OBJECT_ID
	public static final String BASE_OBJID_NONE = "-1";

	//BASE_OBJECT_TYPE
	public static final Integer BASE_OBJTYPE_NONE = -1;
	public static final Integer BASE_OBJTYPE_EVENTO = 1001;
	public static final Integer BASE_OBJTYPE_REGRA = 1002;
	public static final Integer BASE_OBJTYPE_REDE = 1003;
	
	//BASE_TABLE_NAME
	public static final String BASE_TBLNAME_EVENTO = "tbl_evento";
	public static final String BASE_TBLNAME_REGRA = "tbl_regra";
	public static final String BASE_TBLNAME_REDE = "tbl_rede";
	
	//DATE_FORMAT
	public static final String FMT_DATETIME_MASC = "dd/MM/yyyy HH:mm:ss"; 
	public static final String FMT_DATETIME_INV_MASC = "yyyyMMddHHmmss"; 
	
	//REST_SERVER_STR
	public static final String REST_CMD_COPYRIGHT_STR = "/ServerMon/copyright";
	//
	public static final String REST_CMD_LISTALL_EVENTOS_STR = "/ServerMon/Eventos/listall";
	public static final String REST_CMD_LIST_EVENTO_STR = "/ServerMon/Evento/?";
	public static final String REST_CMD_LIST_EVENTO_BY_DATA_STR = "/ServerMon/Evento/listby/data/?";
	public static final String REST_CMD_ADD_EVENTO_STR = "/ServerMon/Evento/Add/?";
	//
	public static final String REST_CMD_LISTALL_REGRAS_STR = "/ServerMon/Regras/listall";
	public static final String REST_CMD_LIST_REGRA_STR = "/ServerMon/Regra/?";
	public static final String REST_CMD_LIST_REGRA_BY_NOME_STR = "/ServerMon/Regra/listby/nome/?";
	//
	public static final String REST_CMD_LISTALL_REDES_STR = "/ServerMon/Redes/listall";
	public static final String REST_CMD_LIST_REDE_STR = "/ServerMon/Rede/?";
	public static final String REST_CMD_LIST_REDE_BY_NOME_STR = "/ServerMon/Rede/listby/nome/?";
	public static final String REST_CMD_LIST_REDE_BY_DEVICE_STR = "/ServerMon/Rede/listby/device/?";
	public static final String REST_CMD_ADD_ARRREDE_STR = "/ServerMon/ArrRede/Add/?";
	//
	public static final String REST_CMD_CONTROLE_SISTEMA_BASE_STR = "/ServerMon/ControleSistema";
	public static final String REST_CMD_CONTROLE_SISTEMA_STR = "/ServerMon/ControleSistema/?";
	//
	public static final String REST_CMD_DEFAULT_STR = AppDefs.REST_CMD_COPYRIGHT_STR;
	
	//REST_SERVER_VAL
	public static final Integer REST_CMD_COPYRIGHT_VAL = 1001;
	//
	public static final Integer REST_CMD_LISTALL_EVENTOS_VAL = 2001;
	public static final Integer REST_CMD_LIST_EVENTO_VAL = 2002;
	public static final Integer REST_CMD_LIST_EVENTO_BY_DATA_VAL = 2003;
	public static final Integer REST_CMD_ADD_EVENTO_VAL = 2004;
	//
	public static final Integer REST_CMD_LISTALL_REGRAS_VAL = 3001;
	public static final Integer REST_CMD_LIST_REGRA_VAL = 3002;
	public static final Integer REST_CMD_LIST_REGRA_BY_NOME_VAL = 3003;
	//
	public static final Integer REST_CMD_LISTALL_REDES_VAL = 4001;
	public static final Integer REST_CMD_LIST_REDE_VAL = 4002;
	public static final Integer REST_CMD_LIST_REDE_BY_NOME_VAL = 4003;
	public static final Integer REST_CMD_LIST_REDE_BY_DEVICE_VAL = 4004;
	public static final Integer REST_CMD_ADD_ARRREDE_VAL = 4005;
	//
	public static final Integer REST_CMD_CONTROLE_SISTEMA_VAL = 9999;
	//
	public static final Integer REST_CMD_DEFAULT_VAL = AppDefs.REST_CMD_COPYRIGHT_VAL;
	
	//DEF_REST_SERVER
	public static final Long DEF_REST_INITIAL_REQUEST_ID = 1000000L;
	public static final Integer DEF_REST_MAX_NUM_WORK_THREADS = 10;
	public static final Integer DEF_REST_REQUEST_QUEUE_SIZE = 100;
	//public static final String DEF_REST_SERVER_ADDR = "192.168.0.237";
	public static final Integer DEF_REST_SERVER_PORT = 9090;
	public static final String DEF_REST_APPLICATION_CONTEXT = "/ServerMon";
	public static final Integer DEF_REST_SERVER_STOP_DELAY = 5;					// server stop delay time in seconds
		
	//DATA_FIELD
	public static final String FLD_EVENTO_ID = "id";
	public static final String FLD_EVENTO_DATAHORA = "dataHora";
	public static final String FLD_EVENTO_EQUIPAMENTO = "equipamento";
	public static final String FLD_EVENTO_LUZACESA = "luzAcesa";
	public static final String FLD_EVENTO_MOVIMENTO = "movimento";
	public static final String FLD_EVENTO_FUMACA = "fumaca";
	public static final String FLD_EVENTO_GASCARBONICO = "gasCarbonico";
	public static final String FLD_EVENTO_GASNATURAL = "gasNatural";
	public static final String FLD_EVENTO_TEMPERATURA = "temperatura";
	public static final String FLD_EVENTO_HUMIDADEAR = "humidadeAr";

	//DATA_OPERATIONS
	public static final String OPER_EQUAL = "eq";
	public static final String OPER_LOWER_THAN = "lt";
	public static final String OPER_LOWER_EQUAL = "le";
	public static final String OPER_GREATER_THAN = "gt";
	public static final String OPER_GREATER_EQUAL = "ge";
	
    //WIFI_PARAMETERS
    public static final Double defWiFiMaxPowerDecibelMeter = 20.0;
    public static final Double defWiFiMaxPowerMiliWatt = 100.0;
    public static final Double defWiFiPowerMiliWattLossPerMeter = 1.0;
    public static final Double defWiFiLimitPowerMiliWatt = 0.000025;
    public static final Double defWiFiLimitDistanceMeter = 100.0;	
	
}
