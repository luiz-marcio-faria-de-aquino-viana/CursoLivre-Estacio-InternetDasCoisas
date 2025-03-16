/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * ResourceBr.h
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

#ifndef __RESOURCEBR_H
#define __RESOURCEBR_H  "__RESOURCEBR_H"

/* LABEL MESSAGES
 */
#define RES_LBL_NOME_EMPRESA                        ((String)"Empresa")
#define RES_LBL_ENDERECO_EMPRESA                    ((String)"Endereco")
#define RES_LBL_SITE_EMPRESA                        ((String)"Site")
#define RES_LBL_EMAIL_EMPRESA                       ((String)"E-mail")
#define RES_LBL_TELEFONE_EMPRESA                    ((String)"Telefone")
//
#define RES_LBL_NOME_AUTOR                          ((String)"Autor")
#define RES_LBL_CARGO_AUTOR                         ((String)"Cargo")
#define RES_LBL_REGISTRO_AUTOR                      ((String)"Registro")
#define RES_LBL_CPF_AUTOR                           ((String)"CPF")
#define RES_LBL_IDENTIDADE_AUTOR                    ((String)"Identidade")
#define RES_LBL_EMAIL_AUTOR                         ((String)"E-mail")
#define RES_LBL_TELEFONE_AUTOR                      ((String)"Telefone")

/* PIN LABEL
 */
#define RES_LBL_PIN_IGN                             ((String)"IGN")
//
#define RES_LBL_PIN_LED                             ((String)"LED")
//
#define RES_LBL_PIN_IP0                             ((String)"IP0")
#define RES_LBL_PIN_IP1                             ((String)"IP1")
#define RES_LBL_PIN_IP2                             ((String)"IP2")
#define RES_LBL_PIN_IP3                             ((String)"IP3")
//
#define RES_LBL_PIN_XP0                             ((String)"XP0")
#define RES_LBL_PIN_XP1                             ((String)"XP1")
#define RES_LBL_PIN_XP2                             ((String)"XP2")
#define RES_LBL_PIN_XP3                             ((String)"XP3")

/* TEXT MESSAGES
 */
#define RES_TXT_MESSAGE_REMOTEUNITSTARTED           ((String)"RemoteUnit started!")
#define RES_TXT_MESSAGE_LISTEVENTLOG                ((String)"List of Event Log:")
#define RES_TXT_MESSAGE_LISTSENSORDATA              ((String)"List of Sensor Data:")
#define RES_TXT_MESSAGE_HARTBEATHIGH                ((String)"Hartbeat: O")
#define RES_TXT_MESSAGE_HARTBEATLOW                 ((String)"Hartbeat: X")
#define RES_TXT_MESSAGE_CONNECTTOWIFI               ((String)"Connecting to WiFi... ")
#define RES_TXT_MESSAGE_CONNECTEDTOWIFI             ((String)"Connected to WiFi!")
#define RES_TXT_MESSAGE_LOCALIPADDR                 ((String)"Local IP: ")
#define RES_TXT_MESSAGE_STARTINGACCESSPOINT         ((String)"Starting Access Point... ")
#define RES_TXT_MESSAGE_ACCESSPOINTSTARTED          ((String)"Access Point Started!")
#define RES_TXT_MESSAGE_ACCESSPOINTIPADDR           ((String)"Access Point IP: ")
#define RES_TXT_MESSAGE_ACCESSPOINTSSIDNAME         ((String)"Access Point SSID: ")
#define RES_TXT_MESSAGE_ACCESSPOINTSSIDKEY          ((String)"Access Point KEY: ")

/* ERROR MESSAGES
 */
#define ERR_CANT_INITIALIZE_APPL_DEFINITIONS        ((String)"Can't initialize the Application Definitions")
#define ERR_CANT_INITIALIZE_APPL_CONTEXT            ((String)"Can't initialize the Application Context")
#define ERR_CANT_INITIALIZE_APPL_CONFIG             ((String)"Can't initialize the Application Configuration")
#define ERR_CANT_INITIALIZE_APPL_ERROR              ((String)"Can't initialize the Application Error")
//
#define ERR_CANT_INITIALIZE_LOGSERVICE              ((String)"Can't initialize the Log Service")
#define ERR_CANT_INITIALIZE_SENSORSERVICE           ((String)"Can't initialize the Sensor Service")
#define ERR_CANT_INITIALIZE_ALARMSERVICE            ((String)"Can't initialize the Alarm Service")
#define ERR_CANT_INITIALIZE_GPSSERVICE              ((String)"Can't initialize the GPS Service")
#define ERR_CANT_INITIALIZE_NEWORKSERVICE           ((String)"Can't initialize the Network Service")
#define ERR_CANT_INITIALIZE_COMMANDERVICE           ((String)"Can't initialize the Command Service")
//
#define ERR_CANT_ADDNEW_LOGSERVICE_EVENT            ((String)"Can't add new Log Service event")
#define ERR_CANT_ADDNEW_SENSORSERVICE_SENSORDATA    ((String)"Can't add new Sensor Service data")

#endif
