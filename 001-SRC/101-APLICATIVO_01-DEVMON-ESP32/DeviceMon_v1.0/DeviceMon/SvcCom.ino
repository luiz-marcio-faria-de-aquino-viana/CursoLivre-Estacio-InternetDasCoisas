/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * SvcCom.ino
 * Autor: 
 * - Luiz Marcio Faria de Aquino Viana, Pós-D.Sc. - Engenheiro, 01/02/2023
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

//svccom_buildRequestURL(): funcao para construir URL requisicao usando dados do evento
//baseUrl - URL base
//p_evento - ponteiro para a estrutura do evento
String svccom_buildRequestURL(String baseUrl, evento_t* p_evento) {
  String tmpReqUrl = baseUrl +
    String(p_evento->seqNum) + "," +
    String(p_evento->equipamento) + "," +
    String(p_evento->luzAcesa) + "," +
    String(p_evento->movimento) + "," +
    String(p_evento->fumaca) + "," +
    String(p_evento->gasCarbonico) + "," +
    String(p_evento->gasNatural) + "," +
    String(p_evento->temperatura) + "," +
    String(p_evento->humidadeAr) + "," +
    String(p_evento->ligarVent) + "," +
    String(p_evento->ligarArCond) + "," +
    String(p_evento->ligarDry);

  String reqUrl = "GET " + tmpReqUrl + " HTTP/1.1";  
  return reqUrl;
}

//svccom_serverRequest(): funcao de requisicao de dados ao servidor
//reqUrl - URL de requisicao
void svccom_serverRequest(String strReqUrl) {
  //String strAccept = "Accept: text/html";
  String strAccept = "Accept: " + MIMETYPE_JSON;
  String strHost = "Host: " + DEF_CFG_REMOTEIP + ":" + String(DEF_CFG_REMOTEPORT);

  String dbgmsg = "ReqURL=" + strReqUrl;
  showDbgMsg(DEBUG_LEVEL_01, __SVCSIM_H, "svccom_serverRequest()", dbgmsg);

  WiFiClient client;

  while( !client.connect(getCfgRemoteIP(), getCfgRemotePort()) ) {
    Serial.println("Falha de conexao");
    delay(500);
  }

  Serial.println("Conectado!");

  Serial.println(strReqUrl);

  client.println(strReqUrl);
  client.println(strAccept);
  client.println(strHost);
  //client.flush();

  Serial.println("Desconectando...");
  client.stop();

  Serial.print("Aguardando 500 milisegundos para proximo envio... ");
  delay(500);
}

//svccom_initSvcCom(): funcao para inicializar do servico de comunicacao
void svccom_initSvcCom() {
  /* nothing todo! */
}

//svccom_execSvcCom(): funcao de execucao do servico de comunicacao
//baseUrl - URL base
//p_evento - ponteiro para a estrutura do evento
bool svccom_execSvcCom(String baseUrl, evento_t* p_evento) {
  String reqUrl = svccom_buildRequestURL(baseUrl, p_evento);
  svccom_serverRequest(reqUrl);
}
