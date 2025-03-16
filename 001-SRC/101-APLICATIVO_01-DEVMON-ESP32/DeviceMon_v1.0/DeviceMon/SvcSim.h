/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * SvcSim.h
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

#ifndef __SVCSIM_H
#define __SVCSIM_H  "__SVCSIM_H"

//svcsim_resetData(): funcao para limpar os dados do evento
//p_evento - ponteiro para a estrutura do evento
void svcsim_resetData(evento_t* p_evento);

//svcsim_setData(): funcao para atualizar os dados do evento
//p_evento - ponteiro para a estrutura do evento
bool svcsim_setData(evento_t* p_evento);

//svcsim_debugData(): funcao de depuracao do evento
//p_evento - ponteiro para a estrutura do evento
void svcsim_debugData(evento_t* p_evento);

//svcsim_random(): funcao para gerar numeros aleatorios
double svcsim_random();

//svccim_step1SvcSim(): funcao que executa o STEP_1 da simulação (GAS_NATURAL)
void svcsim_step1SvcSim();

//svccim_step2SvcSim(): funcao que executa o STEP_2 da simulação (TEMPERATURA)
void svcsim_step2SvcSim();

//svccim_step3SvcSim(): funcao que executa o STEP_3 da simulação (HUMIDADE_AR)
void svcsim_step3SvcSim();
 
//svccim_step4SvcSim(): funcao que executa o STEP_4 da simulação (MOVIMENTO / LUZ_ACESA / GAS_CARBONICO / FUMACA)
void svcsim_step4SvcSim();

//svcsim_initSvcSim(): funcao para inicializar os dados de simulacao do evento
void svcsim_initSvcSim();

//svcsim_execSvcSim(): funcao de execucao da simulacao do evento
//p_evento - ponteiro para a estrutura do evento
bool svcsim_execSvcSim(evento_t* p_evento);

#endif
