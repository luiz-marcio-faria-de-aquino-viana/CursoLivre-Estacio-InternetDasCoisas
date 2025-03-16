/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * AppDefs.h
 * Autor: 
 * - Luiz Marcio Faria de Aquino Viana, Pós-D.Sc. - Engenheiro, 30/01/2023
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

#ifndef __APPDEFS_H
#define __APPDEFS_H     "__APPDEFS_H"

/* DEBUG LEVEL
 */
#define DEBUG_LEVEL_00                      0
#define DEBUG_LEVEL_01                      1
#define DEBUG_LEVEL_02                      2
#define DEBUG_LEVEL_03                      3
#define DEBUG_LEVEL_04                      4
#define DEBUG_LEVEL_05                      5
//
#define DEBUG_LEVEL_99                      99

#define DEBUG_LEVEL                         1

/* APPLICATION SERIAL DEFINITION
 */
#define SER_BAUDRATE_115200                 115200

/* APPLICATION DEFINITION
 */
#define APP_NAME                            ((String)"DeviceMon")

#define APP_VERSION                         ((String)"1.0.20230130")

//COPYRIGHT
#define APP_COPYRIGHT_NAME                  ((String)"TLMV Consultoria Sistemas")
#define APP_COPYRIGHT_AUTHOR_NAME           ((String)"Luiz Marcio F. A. Viana")
#define APP_COPYRIGHT_AUTHOR_EMAIL1         ((String)"luiz.marcio.viana@gmail.com")
#define APP_COPYRIGHT_AUTHOR_EMAIL2         ((String)"luiz_marcio@hotmail.com")
#define APP_COPYRIGHT_AUTHOR_PHONE1         ((String)"(21)99983-7207")

/* CONTEXT DEFINITION
 */
#define DEF_CTX_SERIAL_NUMBER_NONE          ((String)"?")

/* CONFIG DEFINITION
 */
#define DEF_CFG_SERVER_PROTOCOL_HTTP        ((String)"http://")
//#define DEF_CFG_SERVER_URL                  ((String)"192.168.0.15")
//#define DEF_CFG_SERVER_URL                  ((String)"192.168.43.13")
#define DEF_CFG_SERVER_URL                  ((String)"172.16.2.72")

/* REQUEST DEFINITION
 */
#define DEF_REQ_ADDEVENTO                   ((String)"/ServerMon/Evento/Add/")

/* ESP32v2 - USED PIN DEFINITION
 */
#define PIN_PRG                             0
#define PIN_LED                             LED_BUILTIN

#define DEF_LORA_STDFREQ                    868E6

/* WIFI AP
 */
#define AP_SSID_BASENAME                    ((String)"TLMV_");            //SSID_NAME = TLMV_c485089412b3;TLMV_50b7c3004d93;...
#define AP_SSID_KEY                         "0198237645";

/* WIFI LINK
 */
//#define SSID_NAME                           "CLARO_WIFI_2.4ghz"
//#define SSID_KEY                            "aq127172"
//#define SSID_NAME                           "IVONNET"
//#define SSID_KEY                            "voa voa dona Ivonne"
//#define SSID_NAME                           "TLMV3"
//#define SSID_KEY                            "0198237645"
#define SSID_NAME                           "ESTACIO-VISITANTES"
#define SSID_KEY                            "estacio@2014"

/* REMOTE SERVER
 */
//#define DEF_CFG_REMOTEIP                    "192.168.0.15"
//#define DEF_CFG_REMOTEIP                    ((String)"192.168.43.13")
#define DEF_CFG_REMOTEIP                    ((String)"172.16.2.72")
#define DEF_CFG_REMOTEPORT                  9090

/* LOCAL SERVER
 */
//#define DEF_CFG_LOCALIP                     "192.168.43.13"
//#define DEF_CFG_LOCALPORT                   9091

/* GATEWAY / NETWORK MASK
 */
#define DEF_CFG_GATEWAYIP                   "192.168.1.1"
#define DEF_CFG_NETWORKMASK                 "255.255.255.0"

/* OLED PARAMS
 */
#define DEF_OLED_MAXWIDTH                   128
#define DEF_OLED_MAXHEIGHT                  64
//
#define DEF_OLED_FONTSIZE                   10
//
#define DEF_OLED_ROWSIZE                    10
#define DEF_OLED_COLSIZE                    10
//
#define DEF_OLED_NUMROWS                    6
#define DEF_OLED_NUMCOLS                    12

/* TIMEOUT DEFINITION
 */
#define DEF_TIMEMILI_500                    500
#define DEF_TIMEMILI_10000                  10000

/* SIMULATION TIME DEFINITION
 */
#define DEF_MOVIMENTO_TIMEOUT               15
#define DEF_LUZACESA_TIMEOUT                10
#define DEF_PESSOAFUMANDO_TIMEOUT           5

/* FILE_EXTENSIONS
 */
#define EXT_TMP                             ((String)".$")
#define EXT_DAT                             ((String)".dat")
#define EXT_JPG                             ((String)".jpg")
#define EXT_JPEG                            ((String)".jpeg")
#define EXT_PNG                             ((String)".png")
#define EXT_HTML                            ((String)".html")
#define EXT_LOG                             ((String)".log")
#define EXT_CSV                             ((String)".csv")

/* MIMETYPE
 */
#define MIMETYPE_XML                        ((String)"application/soap+xml")
#define MIMETYPE_JSON                       ((String)"application/json")
#define MIMETYPE_JPG                        ((String)"image/jpeg")
#define MIMETYPE_JPEG                       ((String)"image/jpeg")
#define MIMETYPE_PNG                        ((String)"image/png")
#define MIMETYPE_HTML                       ((String)"text/html")
#define MIMETYPE_BLOB                       ((String)"binary/octet-stream")

/* EVENT LOG STRUCTURE 
 */
typedef struct evento_struct {
  long seqNum;
  String equipamento;
  int luzAcesa;
  int movimento;
  double fumaca;
  double gasCarbonico;
  double gasNatural;
  double temperatura;
  double humidadeAr;
  int ligarVent;
  int ligarArCond;
  int ligarDry;
} evento_t;

#endif
