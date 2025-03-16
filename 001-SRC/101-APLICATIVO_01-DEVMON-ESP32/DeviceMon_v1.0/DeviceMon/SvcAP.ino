/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * SvcAP.ino
 * Autor: 
 * - Luiz Marcio Faria de Aquino Viana, Pós-D.Sc. - Engenheiro, 17/03/2023
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

IPAddress gAccessPoint_IP;

//svcap_initSvcAP(): funcao para inicializar do servico de ponto de acesso (AP)
void svcap_initSvcAP() {
  Serial.println(RES_TXT_MESSAGE_STARTINGACCESSPOINT);

  Serial.println(RES_TXT_MESSAGE_ACCESSPOINTSSIDNAME);
  Serial.print(getCfgSSIDAPName());

  Serial.println(RES_TXT_MESSAGE_ACCESSPOINTSSIDKEY);
  Serial.print(getCfgSSIDAPKey());

  String ssidAPName = getCfgSSIDAPName();
  WiFi.softAP(ssidAPName.c_str(), getCfgSSIDAPKey());
  gAccessPoint_IP = WiFi.softAPIP();

  Serial.println(RES_TXT_MESSAGE_ACCESSPOINTIPADDR);
  Serial.print(gAccessPoint_IP);

  Serial.println(RES_TXT_MESSAGE_ACCESSPOINTSTARTED);
}

//svcap_execSvcAP(): funcao de execucao do servico de ponto de acesso (AP)
//p_evento - ponteiro para a estrutura do evento
bool svcap_execSvcAP(evento_t* p_evento) {
  /* nothing todo! */
}
