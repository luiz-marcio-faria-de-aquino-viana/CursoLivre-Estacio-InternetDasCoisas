/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * SvcSim.ino
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

/* SIMULATION CONTROLS
 */
int gPessoaFumando_value;
int gPessoaFumando_timeout;
double gPessoaFumando_tax;

/* SENSORS DEFINITION
 */
// Sensor_Movimento
int    gSensor_movimento_value;
int gSensor_movimento_timeout;
double gSensor_movimento_rate;
double gSensor_movimento_tax;
// Sensor_LuzAcesa
int    gSensor_luzAcesa_value;
int gSensor_luzAcesa_timeout;
double gSensor_luzAcesa_rate;           // Evento Dependente - SE movimento ENTAO:
double gSensor_luzAcesa_tax;
// Sensor_Fumaca
double gSensor_fumaca_value;
double gSensor_fumaca_min;
double gSensor_fumaca_max;
double gSensor_fumaca_rate;
double gSensor_fumaca_tax;
// Sensor_GasCarbonico
double gSensor_gasCarbonico_value;
double gSensor_gasCarbonico_min;
double gSensor_gasCarbonico_max;
double gSensor_gasCarbonico_rate;
double gSensor_gasCarbonico_tax;
// Sensor_GasNatural
double gSensor_gasNatural_value;
double gSensor_gasNatural_min;
double gSensor_gasNatural_max;
double gSensor_gasNatural_rate;
double gSensor_gasNatural_tax;
// Sensor_Temperatura
double gSensor_temperatura_value;
double gSensor_temperatura_min;
double gSensor_temperatura_max;
double gSensor_temperatura_rate;
double gSensor_temperatura_tax;
// Sensor_HumidadeAr
double gSensor_humidadeAr_value;
double gSensor_humidadeAr_min;
double gSensor_humidadeAr_max;
double gSensor_humidadeAr_rate;
double gSensor_humidadeAr_tax;

/* ACTUATORS DEFINITION
 */
int gActuator_ligarVent = 0;
int gActuator_ligarArCond = 0;
int gActuator_ligarDry = 0;

//svcsim_resetData(): funcao para limpar os dados do evento
//p_evento - ponteiro para a estrutura do evento
void svcsim_resetData(evento_t* p_evento) {
  p_evento->seqNum = -1;
  p_evento->equipamento = "";
  p_evento->luzAcesa = 0;
  p_evento->movimento = 0;
  p_evento->fumaca = 0.0;
  p_evento->gasCarbonico = 0.0;
  p_evento->gasNatural = 0.0;
  p_evento->temperatura = 0.0;
  p_evento->humidadeAr = 0.0;
  p_evento->ligarVent = 0;
  p_evento->ligarArCond = 0;
  p_evento->ligarDry = 0;
}

//svcsim_setData(): funcao para atualizar os dados do evento
//p_evento - ponteiro para a estrutura do evento
bool svcsim_setData(evento_t* p_evento) {
  long ts = esp_timer_get_time();
  
  p_evento->seqNum = ts;
  p_evento->equipamento = getCtxSerialNumber();
  p_evento->luzAcesa = gSensor_luzAcesa_value;
  p_evento->movimento = gSensor_movimento_value;
  p_evento->fumaca = gSensor_fumaca_value;
  p_evento->gasCarbonico = gSensor_gasCarbonico_value;
  p_evento->gasNatural = gSensor_gasNatural_value;
  p_evento->temperatura = gSensor_temperatura_value;
  p_evento->humidadeAr = gSensor_humidadeAr_value;   
  p_evento->ligarVent = gActuator_ligarVent;
  p_evento->ligarArCond = gActuator_ligarArCond;
  p_evento->ligarDry = gActuator_ligarDry;
}

//svcsim_debugData(): funcao de depuracao do evento
//p_evento - ponteiro para a estrutura do evento
void svcsim_debugData(evento_t* p_evento) {
    String msg = 
      "\n***"
      "\nSEQ=" + String(p_evento->seqNum) +
      "\nEQP=" + p_evento->equipamento +
      "\nMOV=" + String(p_evento->movimento) +
      "\nLUZ=" + String(p_evento->luzAcesa) +
      "\nCO2=" + String(p_evento->gasCarbonico) +
      ";GLP=" + String(p_evento->gasNatural) +
      ";PESSFUM=" + String(gPessoaFumando_value) +
      ";FUM=" + String(p_evento->fumaca) +
      ";VENT=" + String(gActuator_ligarVent) +
      "\nTEMP=" + String(p_evento->temperatura) +
      ";ARCOND=" + String(gActuator_ligarArCond) +
      "\nHUM=" + String(p_evento->humidadeAr) +
      ";DRY=" + String(gActuator_ligarDry) +
      "\n***" +
      "\n";

    showDbgMsg(DEBUG_LEVEL_01, __SVCSIM_H, "svcsim_debug()", msg);
}

//svcsim_random(): funcao para gerar numeros aleatorios
double svcsim_random()
{
  double randomDblVal = (double)esp_random() / 1000000.0;
  int randomIntVal = (int)randomDblVal;

  double randomVal = randomDblVal - (double)randomIntVal;

  String msg = "RandomVal=" + String(randomVal);
  showDbgMsg(DEBUG_LEVEL_99, __SVCSIM_H, "svcsim_debug()", msg);
  
  return randomVal;
}

//svcsim_step1SvcSim(): funcao que executa o STEP_1 da simulação (GAS_NATURAL)
void svcsim_step1SvcSim() {
  // GAS_NATURAL
  if(gActuator_ligarVent == 0) {  //VENT = 'DESLIGADO'
    gSensor_gasNatural_value = gSensor_gasNatural_value + gSensor_gasNatural_rate;
  }
  else if(gActuator_ligarVent == 1) {
    gSensor_gasNatural_value = gSensor_gasNatural_value - gSensor_gasNatural_rate;

    if(gSensor_gasNatural_value < 0.0) {
      gSensor_gasNatural_value = 0.0;
    }
  }
}

//svcsim_step2SvcSim(): funcao que executa o STEP_2 da simulação (TEMPERATURA)
void svcsim_step2SvcSim() {
  // TEMPERATURA
  if(gActuator_ligarArCond == 0) {  //ARCOND = 'DESLIGADO'
    gSensor_temperatura_value = gSensor_temperatura_value + gSensor_temperatura_rate;
  }
  else if(gActuator_ligarArCond == 1) {
    gSensor_temperatura_value = gSensor_temperatura_value - gSensor_temperatura_rate;

    if(gSensor_temperatura_value < 0.0) {
      gSensor_temperatura_value = 0.0;
    }
  }
}

//svcsim_step3SvcSim(): funcao que executa o STEP_3 da simulação (HUMIDADE_AR)
void svcsim_step3SvcSim() {
  // HUMUDADE_AR
  if(gActuator_ligarDry == 0) {  ///DRY = 'DESLIGADO'
    gSensor_humidadeAr_value = gSensor_humidadeAr_value + gSensor_humidadeAr_rate;
  }
  else if(gActuator_ligarDry == 1) {
    gSensor_humidadeAr_value = gSensor_humidadeAr_value - gSensor_humidadeAr_rate;

    if(gSensor_humidadeAr_value < 0.0) {
      gSensor_humidadeAr_value = 0.0;
    }
  }
}
 
//svcsim_step4SvcSim(): funcao que executa o STEP_4 da simulação (MOVIMENTO)
void svcsim_step4SvcSim() {
  // MOVIMENTO
  if(gSensor_movimento_timeout <= 0) {
    int btnPin_value = getBtnPin();
    
    //gSensor_movimento_value = ((svcsim_random() < gSensor_movimento_tax) ? 1 : 0);
    gSensor_movimento_value = ((btnPin_value == LOW) ? 1 : 0);
    if(gSensor_movimento_value == 1) {
      gSensor_movimento_timeout = DEF_MOVIMENTO_TIMEOUT;
    
      gSensor_luzAcesa_timeout = 0;
      gSensor_luzAcesa_value = 0;

      gActuator_ligarArCond = 0;
      gActuator_ligarDry = 0;
    }
  }
  else {
    gSensor_movimento_timeout = gSensor_movimento_timeout - 1;
    if(gSensor_movimento_timeout <= 0) {
      gSensor_movimento_timeout = 0;
      gSensor_movimento_value = 0;
    
      gSensor_luzAcesa_timeout = 0;
      gSensor_luzAcesa_value = 0;

      gActuator_ligarArCond = 0;
      gActuator_ligarDry = 0;
    }    
  }
}
 
//svcsim_step5SvcSim(): funcao que executa o STEP_5 da simulação (LUZ_ACESA)
void svcsim_step5SvcSim() {
  // LUZ_ACESA
  if(gSensor_movimento_value == 1) {
    if(gSensor_luzAcesa_timeout <= 0) {
      gSensor_luzAcesa_value = ((svcsim_random() < gSensor_luzAcesa_tax) ? 1 : 0);
      if(gSensor_luzAcesa_value == 1) {
        gSensor_luzAcesa_timeout = DEF_LUZACESA_TIMEOUT;
      }
    }
    else {
      gSensor_luzAcesa_timeout = gSensor_luzAcesa_timeout - 1;
      if(gSensor_luzAcesa_timeout <= 0) {
        gSensor_luzAcesa_timeout = 0;
        gSensor_luzAcesa_value = 0;
      }    
    }    
  }
}
 
//svcsim_step6SvcSim(): funcao que executa o STEP_6 da simulação (PESSOA_FUMANDO)
void svcsim_step6SvcSim() {
  // PESSOA_FUMANDO
  if(gSensor_movimento_value == 1) {
    if(gPessoaFumando_timeout <= 0) {
      gPessoaFumando_value = ((svcsim_random() < gPessoaFumando_tax) ? 1 : 0);
      if(gPessoaFumando_value == 1) {
        gPessoaFumando_timeout = DEF_PESSOAFUMANDO_TIMEOUT;
      }
    }
    else {
      gPessoaFumando_timeout = gPessoaFumando_timeout - 1;
      if(gPessoaFumando_timeout <= 0) {
        gPessoaFumando_timeout = 0;
        gPessoaFumando_value = 0;
      }    
    }    
  }
}

//svcsim_step7SvcSim(): funcao que executa o STEP_7 da simulação (GAS_CARBONICO)
void svcsim_step7SvcSim() {
  // GAS_CARBONICO
  if(gSensor_movimento_value == 1) {
    if(gActuator_ligarVent == 0) {  //VENT = 'DESLIGADO'
      gSensor_gasCarbonico_value = gSensor_gasCarbonico_value + gSensor_gasCarbonico_rate;
    }
    else if(gActuator_ligarVent == 1) {
      gSensor_gasCarbonico_value = gSensor_gasCarbonico_value - gSensor_gasCarbonico_rate;

      if(gSensor_gasCarbonico_value < 0.0) {
        gSensor_gasCarbonico_value = 0.0;
      }
    }
  }
  else {
    if(gActuator_ligarVent == 1) {
      gSensor_gasCarbonico_value = gSensor_gasCarbonico_value - gSensor_gasCarbonico_rate;
  
      if(gSensor_gasCarbonico_value < 0.0) {
        gSensor_gasCarbonico_value = 0.0;
      }
    }
  }
}

 
//svcsim_step8SvcSim(): funcao que executa o STEP_8 da simulação (FUMACA)
void svcsim_step8SvcSim() {
  // FUMACA
  if(gPessoaFumando_value == 1) {
    if(gActuator_ligarVent == 0) {
      gSensor_fumaca_value = gSensor_fumaca_value + gSensor_fumaca_rate;
    }
    else if(gActuator_ligarVent == 1) {
      gSensor_fumaca_value = gSensor_fumaca_value - gSensor_fumaca_rate;

      if(gSensor_fumaca_value < 0.0) {
        gSensor_fumaca_value = 0.0;
      }
    }
  }
  else {
    if(gActuator_ligarVent == 1) {
      gSensor_fumaca_value = gSensor_fumaca_value - gSensor_fumaca_rate;

      if(gSensor_fumaca_value < 0.0) {
        gSensor_fumaca_value = 0.0;
      }
    }
  }
}

//svcsim_checkSvcSim(): funcao de verificacao dos limites de simulacao do evento
bool svcsim_checkSvcSim() {
  bool bAlarm = false;

  if( (gSensor_gasNatural_value > gSensor_gasNatural_max) ||
      (gSensor_gasCarbonico_value > gSensor_gasCarbonico_max) ||
      (gSensor_fumaca_value > gSensor_fumaca_max) ) {
    gActuator_ligarVent = 1;
    bAlarm = true;
  }
  else if( (gSensor_gasNatural_value < gSensor_gasNatural_min) &&
           (gSensor_gasCarbonico_value < gSensor_gasCarbonico_min) &&
           (gSensor_fumaca_value < gSensor_fumaca_min) ) {
    gActuator_ligarVent = 0;
    bAlarm = true;
  }

  if(gSensor_temperatura_value > gSensor_temperatura_max) {
    gActuator_ligarArCond = 1;
    bAlarm = true;
  }
  else if(gSensor_temperatura_value < gSensor_temperatura_min) {
    gActuator_ligarArCond = 0;    
    bAlarm = true;
  }
  
  if(gSensor_humidadeAr_value > gSensor_humidadeAr_max) {
    gActuator_ligarDry = 1;
    bAlarm = true;
  }
  else if(gSensor_humidadeAr_value < gSensor_humidadeAr_min) {
    gActuator_ligarDry = 0;
    bAlarm = true;
  }

  if( (gActuator_ligarVent == 1) ||
      (gActuator_ligarArCond == 1) ||
      (gActuator_ligarDry == 1) ) {
    setLedPin(HIGH);
  }
  else {
    setLedPin(LOW);    
  }
 
  return bAlarm;
}

//svcsim_initSvcSim(): funcao para inicializar os dados de simulacao do evento
void svcsim_initSvcSim() {
  //Pessoa_Fumando
  gPessoaFumando_value = 0;
  gPessoaFumando_timeout = 0;
  gPessoaFumando_tax = 0.3;
  
  //Sensor_Movimento
  gSensor_movimento_value = 0;
  gSensor_movimento_timeout = 0;
  gSensor_movimento_tax = 0.25;

  // Sensor_LuzAcesa
  gSensor_luzAcesa_value = 0;
  gSensor_luzAcesa_timeout = 0;
  gSensor_luzAcesa_tax = 0.75;
  
  // Sensor_Fumaca
  gSensor_fumaca_value = 0.0;
  gSensor_fumaca_min = 0.05;
  gSensor_fumaca_max = 0.6;
  gSensor_fumaca_rate = 0.0125;
  gSensor_fumaca_tax = 0.3;
  
  // Sensor_GasCarbonico
  gSensor_gasCarbonico_value = 0.0;
  gSensor_gasCarbonico_min = 0.1;
  gSensor_gasCarbonico_max = 0.5;
  gSensor_gasCarbonico_rate = 0.0125;
  gSensor_gasCarbonico_tax = 0.75;
  
  // Sensor_GasNatural
  gSensor_gasNatural_value = 0.0;
  gSensor_gasNatural_min = 0.01;
  gSensor_gasNatural_max = 0.3;
  gSensor_gasNatural_rate = 0.00125;
  gSensor_gasNatural_tax = 0.1;
  
  // Sensor_Temperatura
  gSensor_temperatura_value = 0.0;
  gSensor_temperatura_min = 15.0;
  gSensor_temperatura_max = 26.0;
  gSensor_temperatura_rate = 0.25;
  gSensor_temperatura_tax = 0.75;
  
  // Sensor_HumidadeAr
  gSensor_humidadeAr_value = 0.0;
  gSensor_humidadeAr_min = 0.05;
  gSensor_humidadeAr_max = 0.6;
  gSensor_humidadeAr_rate = 0.0125;
  gSensor_humidadeAr_tax = 0.75;

  // Actuator_LigarVent
  gActuator_ligarVent = 0;

  // Actuator_LigarArCond
  gActuator_ligarArCond = 0;
  
  // Actuator_LigarDry
  gActuator_ligarDry = 0;  
}

//svcsim_execSvcSim(): funcao de execucao da simulacao do evento
//p_evento - ponteiro para a estrutura do evento
bool svcsim_execSvcSim(evento_t* p_evento) {
  bool bResult = false;
  
  svcsim_step1SvcSim();
  svcsim_step2SvcSim();
  svcsim_step3SvcSim();
  svcsim_step4SvcSim();
  svcsim_step5SvcSim();
  svcsim_step6SvcSim();
  svcsim_step7SvcSim();
  svcsim_step8SvcSim();
  
  svcsim_setData(p_evento);

  if( svcsim_checkSvcSim() ) {
    bResult = true;
  }

  svcsim_debugData(p_evento);
  
  return bResult;
}
