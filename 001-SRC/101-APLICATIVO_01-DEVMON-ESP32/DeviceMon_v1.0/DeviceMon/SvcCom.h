/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * SvcCom.h
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

#ifndef __SVCCOM_H
#define __SVCCOM_H  "__SVCCOM_H"

//svccom_buildRequestURL(): funcao para construir URL requisicao usando dados do evento
//baseUrl - URL base
//p_evento - ponteiro para a estrutura do evento
String svccom_buildRequestURL(String baseUrl, evento_t* p_evento);

//svccom_serverRequest(): funcao de requisicao de dados ao servidor
//reqUrl - URL de requisicao
void svccom_serverRequest(String reqUrl);

//svccom_initSvcCom(): funcao para inicializar do servico de comunicacao
void svccom_initSvcCom();

//svccom_execSvcCom(): funcao de execucao do servico de comunicacao
//baseUrl - URL base
//p_evento - ponteiro para a estrutura do evento
bool svccom_execSvcCom(String baseUrl, evento_t* p_evento);

#endif
