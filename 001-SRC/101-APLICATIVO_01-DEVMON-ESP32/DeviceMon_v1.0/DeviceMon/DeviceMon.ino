/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * DeviceMon.ino
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

String gAppHartBeat_msg = RES_TXT_MESSAGE_HARTBEATHIGH;
int gAppHartBeat_value = HIGH;
int gAppHartBeat_delay = DEF_TIMEMILI_500;

int gAppLed_pin = PIN_LED;
int gAppLed_value = LOW;

int gAppBtn_pin = PIN_PRG;
int gAppBtn_value = LOW;

// copyright(): function to display the copyright message
void copyright()
{
  Heltec.display->clear();
  printMsg(0, 0, APP_NAME + " " + APP_VERSION);
  printMsg(0, 1, APP_COPYRIGHT_NAME);
  printMsg(0, 2, APP_COPYRIGHT_AUTHOR_NAME);
  printMsg(0, 3, APP_COPYRIGHT_AUTHOR_EMAIL1);
  printMsg(0, 4, APP_COPYRIGHT_AUTHOR_EMAIL2);
  printMsg(0, 5, APP_COPYRIGHT_AUTHOR_PHONE1);
  Heltec.display->display();
  
  delay(DEF_TIMEMILI_10000);
}

// serialCopyright(): function to display the copyright message at serial port
void serialCopyright()
{
  if(DEBUG_LEVEL == DEBUG_LEVEL_00) return;
  
  String msg = "\n";
  
  msg += APP_NAME + " " + APP_VERSION + "\n\n";
  
  //COMPANY
  msg += APP_COPYRIGHT_NAME + "\n";
  msg += APP_COPYRIGHT_AUTHOR_NAME + "\n";
  msg += APP_COPYRIGHT_AUTHOR_EMAIL1 + "\n";
  msg += APP_COPYRIGHT_AUTHOR_EMAIL2 + "\n";
  msg += APP_COPYRIGHT_AUTHOR_PHONE1 + "\n\n";

  Serial.print(msg);
}

// hartbeat(): application "Hart Beat" function
void hartbeat() {
  if(gAppHartBeat_value == HIGH) {
    gAppHartBeat_msg = RES_TXT_MESSAGE_HARTBEATLOW;
    gAppHartBeat_value = LOW;
  }
  else {
    gAppHartBeat_msg = RES_TXT_MESSAGE_HARTBEATHIGH;
    gAppHartBeat_value = HIGH;
  }

  printMsg(0, 5, gAppHartBeat_msg);
  Heltec.display->display();
}

// setupWiFi(): WiFi initialization function
void setupWiFi() {
  Serial.print(RES_TXT_MESSAGE_CONNECTTOWIFI);
  
  WiFi.begin(getCfgSSIDName(), getCfgSSIDKey());
  while(WiFi.status() != WL_CONNECTED) {
      delay(DEF_TIMEMILI_500);
      Serial.print(".");
  }
  Serial.println(RES_TXT_MESSAGE_CONNECTEDTOWIFI);

  Serial.print(RES_TXT_MESSAGE_LOCALIPADDR);
  Serial.println(WiFi.localIP());
}

// setup(): application initialization function
void setup() {
  Heltec.begin(
    true,                           // Display Enable
    false,                          // LoRa Enable
    true,                           // Serial Enable
    false,                          // PABOOST Enable
    DEF_LORA_STDFREQ);              // LORA STDFREQ

  pinMode(gAppLed_pin, OUTPUT);
  pinMode(gAppBtn_pin, INPUT);

  serialCopyright();
  copyright();

  /* Application Initialization
  */
  initAppError();
  initAppContext();
  initAppConfig();

  /* Initialize WiFi Connection
   */
  setupWiFi();
  
  /* Service Initialization
  */
  svcap_initSvcAP();
  svccom_initSvcCom();
  svcsim_initSvcSim();

  Heltec.display->clear();
  printMsg(0, 0, RES_TXT_MESSAGE_REMOTEUNITSTARTED);
  printMsg(0, 1, "S/N: " + getCtxSerialNumber());
  Heltec.display->display();
}

/* Execution */

// loop(): application message loop function
void loop() {
  evento_t p_evento;
  svcsim_resetData(&p_evento);

  hartbeat();
  
  svcap_execSvcAP(&p_evento);
  if( svcsim_execSvcSim(&p_evento) ) {
    svccom_execSvcCom(DEF_REQ_ADDEVENTO, &p_evento);
  }

  delay(gAppHartBeat_delay);
}

/* Getters/Setters */

int getLedPin() {
  gAppLed_value = digitalRead(gAppLed_pin);
  return gAppLed_value;
}

void setLedPin(int value) {
  gAppLed_value = value;
  digitalWrite(gAppLed_pin, gAppLed_value);
}

int getBtnPin() {
  gAppBtn_value = digitalRead(gAppBtn_pin);
  return gAppBtn_value;
}
