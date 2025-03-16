/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * AppConfig.h
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

#ifndef __APPCONFIG_H
#define __APPCONFIG_H   "__APPCONFIG_H"

/* Initialization */

// initAppConfig(): application context initialization
void initAppConfig();

/* Getters/Setters */

// WIFI AP
String getCfgSSIDAPName();
char* getCfgSSIDAPKey();

// WIFI LINK
char* getCfgSSIDName();
char* getCfgSSIDKey();

// REMOTE SERVER
IPAddress getCfgRemoteIP();
int getCfgRemotePort();

// LOCAL SERVER
//IPAddress getCfgLocalIP();
//int getCfgLocalPort();

// GATEWAY/NETWORK MASK
IPAddress getCfgGatewayIP();
IPAddress getCfgNetworkMask();

// LOCAL SERVER/CLIENT (OVER WIFI)
//WiFiServer getCfgLocalServerWiFi();
WiFiClient getCfgLocalClientWiFi();

#endif
