/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * AppContext.ino
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

/* Methods */

String ctx_gSerialNumber;

/* Initialization */

// initAppContext(): application context initialization
void initAppContext() {
  uint32_t serNum0 = (ESP.getEfuseMac() >> 40) & 0xff;    // 40-47 bits
  uint32_t serNum1 = (ESP.getEfuseMac() >> 32) & 0xff;    // 32-39 bits
  uint32_t serNum2 = (ESP.getEfuseMac() >> 24) & 0xff;    // 24-31 bits
  uint32_t serNum3 = (ESP.getEfuseMac() >> 16) & 0xff;    // 16-23 bits
  uint32_t serNum4 = (ESP.getEfuseMac() >>  8) & 0xff;    //  8-15 bits
  uint32_t serNum5 = (ESP.getEfuseMac() >>  0) & 0xff;    //  0- 7 bits

  ctx_gSerialNumber = 
    String(serNum5, HEX) +
    String(serNum4, HEX) +
    String(serNum3, HEX) +
    String(serNum2, HEX) +
    String(serNum1, HEX) +
    String(serNum0, HEX);
  showDbgMsg(DEBUG_LEVEL_01, __APPCONTEXT_H, "initAppContext()", ctx_gSerialNumber + "\n");
}

/* Getters/Setters */

// initAppContext(): application context initialization
String getCtxSerialNumber() {
  return ctx_gSerialNumber;
}
