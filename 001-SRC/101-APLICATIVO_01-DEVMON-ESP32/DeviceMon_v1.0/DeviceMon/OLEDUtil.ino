/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * OLEDUtil.ino
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

// printMsg(): function to print message on a line
// row - row number
// col - column number
// str - message data
void printMsg(int col, int row, String str)
{
  int ypos = row * DEF_OLED_ROWSIZE;
  int xpos = col * DEF_OLED_COLSIZE;

  Heltec.display->setColor(BLACK); 
  Heltec.display->fillRect(0, ypos, DEF_OLED_MAXWIDTH, ypos + DEF_OLED_ROWSIZE);

  Heltec.display->setColor(WHITE); 
  Heltec.display->drawString(xpos, ypos, str);
}
