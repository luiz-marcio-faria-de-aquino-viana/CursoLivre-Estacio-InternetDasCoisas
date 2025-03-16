/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * AppError.ino
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

/* Methodes */

// showDbgMsg(): show debug message
// debugLevel - debug level to show message
// fileName - method file name
// methodName - method name
// msg - message to display
void showDbgMsg(int debugLevel, String fileName, String methodName, String msg) {
  if(debugLevel != DEBUG_LEVEL) return;
  String dbgmsg = "\nDBG(" + fileName + "; " + methodName + "): " + msg;
  Serial.print(dbgmsg);
}

// showErrMsg(): show error message
// fileName - method file name
// methodName - method name
// msg - message to display
void showErrMsg(String fileName, String methodName, String msg) {
  if(DEBUG_LEVEL != DEBUG_LEVEL_00) {
    String errmsg = "\nERR(" + fileName + "; " + methodName + "): " + msg;
    Serial.print(errmsg);
  }
  while( true );
}

/* Initialization */

// initAppError(): command service initialization
void initAppError() {
  /* nothing todo! */
}
