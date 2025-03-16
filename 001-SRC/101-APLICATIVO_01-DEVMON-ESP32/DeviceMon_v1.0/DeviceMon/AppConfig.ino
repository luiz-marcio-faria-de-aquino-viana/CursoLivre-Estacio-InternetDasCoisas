/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * AppConfig.ino
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

#include "All.h"

/* WIFI AP
 */
String cfg_gSSIDAPName;
char* cfg_gSSIDAPKey;

/* WIFI LINK
 */
char* cfg_gSSIDName;
char* cfg_gSSIDKey;

/* REMOTE SERVER
 */
IPAddress cfg_gRemoteIP;
int cfg_gRemotePort;

/* LOCAL SERVER
 */
//IPAddress cfg_gLocalIP;
//int cfg_gLocalPort;

/* GATEWAY / NETWORK MASK
 */
IPAddress cfg_gGatewayIP;
IPAddress cfg_gNetworkMask;

/* LOCAL SERVER/CLIENT (OVER WIFI)
 */
//WiFiServer cfg_gLocalServerWiFi(DEF_CFG_LOCALPORT);
WiFiClient cfg_gLocalClientWiFi;

/* Methodes */

// showAppConfig(): function to show application configuration at OLED display
void showAppConfig() {
  Heltec.display->clear();
  printMsg(0, 0, "SSN: " + String(SSID_NAME));
  printMsg(0, 1, "KEY: " + String(SSID_KEY));
  printMsg(0, 2, "REM: " + String(DEF_CFG_REMOTEIP) + ":" + String(DEF_CFG_REMOTEPORT, DEC));
  //printMsg(0, 3, "LOC: " + String(DEF_CFG_LOCALIP) + ":" + String(DEF_CFG_LOCALPORT, DEC));
  printMsg(0, 3, "GW:  " + String(DEF_CFG_GATEWAYIP));
  printMsg(0, 4, "NET: " + String(DEF_CFG_NETWORKMASK));
  Heltec.display->display();

  delay(DEF_TIMEMILI_10000);
}

// dbgAppConfig(): function to show application configuration at serial port
void dbgAppConfig()
{
  if(DEBUG_LEVEL == DEBUG_LEVEL_00) return;
  
  String msg = String("\n");
  
  msg += String("SSN: ") + String(SSID_NAME) + String("\n");
  msg += String("KEY: ") + String(SSID_KEY) + String("\n");
  msg += String("REM: ") + String(DEF_CFG_REMOTEIP) + ":" + String(DEF_CFG_REMOTEPORT, DEC) + String("\n");
  //msg += String("LOC: ") + String(DEF_CFG_LOCALIP) + ":" + String(DEF_CFG_LOCALPORT, DEC) + String("\n");
  msg += String("GW:  ") + String(DEF_CFG_GATEWAYIP) + String("\n");
  msg += String("NET: ") + String(DEF_CFG_NETWORKMASK) + String("\n\n");

  Serial.print(msg);
}

/* Initialization */

// initAppConfig(): application context initialization
void initAppConfig() {
  cfg_gSSIDAPName = AP_SSID_BASENAME;
  cfg_gSSIDAPName += getCtxSerialNumber();
  
  cfg_gSSIDAPKey = AP_SSID_KEY;
  
  cfg_gSSIDName = SSID_NAME;
  cfg_gSSIDKey = SSID_KEY;
  
  cfg_gRemoteIP.fromString(DEF_CFG_REMOTEIP);
  cfg_gRemotePort = DEF_CFG_REMOTEPORT;

  //cfg_gLocalIP.fromString(DEF_CFG_LOCALIP);
  //cfg_gLocalPort = DEF_CFG_LOCALPORT;
  
  cfg_gGatewayIP.fromString(DEF_CFG_GATEWAYIP);
  cfg_gNetworkMask.fromString(DEF_CFG_NETWORKMASK);
  
  dbgAppConfig();
  showAppConfig();
}

/* Getters/Setters */

// WIFI AP
String getCfgSSIDAPName() {
  return cfg_gSSIDAPName;
}

char* getCfgSSIDAPKey() {
  return cfg_gSSIDAPKey;
}

// WIFI LINK
char* getCfgSSIDName() {
  return cfg_gSSIDName;
}

char* getCfgSSIDKey() {
  return cfg_gSSIDKey;
}

// REMOTE SERVER
IPAddress getCfgRemoteIP() {
  return cfg_gRemoteIP;
}

int getCfgRemotePort() {
  return cfg_gRemotePort;
}

// LOCAL SERVER
//IPAddress getCfgLocalIP() {
//  return cfg_gLocalIP;
//}

//int getCfgLocalPort() {
//  return cfg_gLocalPort;
//}

// GATEWAY/NETWORK MASK
IPAddress getCfgGatewayIP() {
  return cfg_gGatewayIP;
}

IPAddress getCfgNetworkMask() {
  return cfg_gNetworkMask;
}

// LOCAL SERVER/CLIENT (OVER WIFI)
//WiFiServer getCfgLocalServerWiFi() {
//  return cfg_gLocalServerWiFi;
//}

WiFiClient getCfgLocalClientWiFi() {
  return cfg_gLocalClientWiFi;
}
