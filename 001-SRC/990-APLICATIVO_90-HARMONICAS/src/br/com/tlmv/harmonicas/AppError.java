/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * AppError.java
 * Autor: 
 * - Luiz Marcio Faria de Aquino Viana, Pós-D.Sc. - Engenheiro, 04/02/2023
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

package br.com.tlmv.harmonicas;

import java.util.Date;

import javax.swing.JComponent;

import br.com.tlmv.harmonicas.frm.MessageFrame;
import br.com.tlmv.harmonicas.vo.MessageDataVO;

public class AppError 
{
//Public
	//ERROS
	public static String ERR_FALHA_CONSULTA_NFSE = "Falha na consulta NFS-e";
	public static String ERR_FALHA_GERACAO_NFSE = "Falha na geracao NFS-e";
	public static String ERR_FALHA_CANCELAMENTO_NFSE = "Falha no cancelamento da NFS-e";
	public static String ERR_FALHA_SUBSTITUICAO_NFSE = "Falha na substituicao da NFS-e";
	public static String ERR_FALHA_PROCESSAMENTO = "Falha no processamento";
	public static String ERR_FALHA_LEITURA_DADOS = "Falha na leitura dos dados";
	public static String ERR_INVALID_COMMAND_SWITCH = "Linha de comando invalida";
	public static String ERR_FALHA_GRAVACAO_XML = "Falha na gravacao do arquivo XML";
	public static String ERR_FALHA_GRAVACAO_PDF = "Falha na criacao do arquivo PDF";
	public static String ERR_TEMPLATE_NFSE_INEXISTENTE = "O template padrao da NFS-e nao existe";
	public static String ERR_CAMPOS_OBRIGATORIOS = "Campos obrigatorios nao informados";
	public static String ERR_FALHA_ABERTURA_BANCO_DADOS = "Falha na abertura do banco de dados";
	public static String ERR_FALHA_LEITURA_DADOS_INICIAIS_BANCO_DADOS = "Falha na leitura dos dados iniciais do banco de dados";
	public static String ERR_CAMPOS_INVALIDOS = "Campos invalidos";
	public static String ERR_FALHA_VALIDACAO_DADOS = "Falha na validacao dos dados";
	public static String ERR_VALOR_FINAL_MENOR_INICIAL = "Valor final menor que valor inicial";
	public static String ERR_VALOR_ATUAL_MENOR_INICIAL = "Valor atual menor que valor inicial";
	public static String ERR_VALOR_ATUAL_MAIOR_FINAL = "Valor atual maior que valor final";
	public static String ERR_FALHA_INICIALIZACAO_CONTEXTO = "Falha na inicializacao do contexto e leitura do arquivo de configuracao";
	public static String ERR_REPOSITORIO_INVALIDO = "Diretorio do repositorio inexistente";
	public static String ERR_REPOSITORIO_NAO_DIRETORIO = "Repositorio nao e um diretorio";
	public static String ERR_NUM_MAX_DIAS_SUBST_NFSE = "NFS-e emitida a mais de 7 dias nao pode ser substituida";
	public static String ERR_FALHA_PADRAO_RETENCAO_FEDERAL = "Valores invalidos para o padrao de retencao federal";
	public static String ERR_VALOR_JUROS_PADRAO_MAIOR_ZERO = "Valor do juros padrao tem que ser maior ou igual a zero";
	public static String ERR_VALOR_MULTA_PADRAO_MAIOR_ZERO = "Valor da multa padrao tem que ser maior ou igual a zero";
	public static String ERR_VALOR_DIAS_PROTESTO_MAIOR_ZERO = "Dias de protesto tem que ser maior ou igual a zero";
	
	//ALERTAS
	public static String ALR_FILE_SAVED = "File saved: %s";
	public static String ALR_NFSE_EMITIDA = "NFS-e emitida (em %s segundos)";
	public static String ALR_BOLETOS_EMITIDOS = "Boletos emitidos (em %s segundos)";
	
	/* Methodes */

	//Console Output - MESSAGES
	
	public static void showCmdMessage(String msg, Class oClass)
	{
		String strmsg = String.format("MSG(%s): %s", oClass.getSimpleName(), msg);
		System.out.println(strmsg);
	}
	
	public static void showCmdWarn(int debugLevel, String msg, Class oClass)
	{
		if(debugLevel == AppDefs.DEBUG_LEVEL)
		{
			String warnmsg = String.format("WARN(%s): %s", oClass.getSimpleName(), msg);
			System.out.println(warnmsg);
		}
	}

	public static void showCmdError(String msg, Class oClass)
	{
		String errmsg = String.format("ERR(%s): %s", oClass, msg);
		System.out.println(errmsg);		
		//System.exit(1);
	}

	public static void showCmdFatalError(String msg, Class oClass)
	{
		String errmsg = String.format("FATALERR(%s): %s", oClass, msg);
		System.out.println(errmsg);		
		System.exit(1);
	}

	//MessageBox Output - MESSAGES
	
	public static void showMessageBox(
		JComponent parent,
		String eventSubject, 
		String eventMessage, 
		Class oClass)
	{
		if(parent != null)
		{
			Date eventDate = new Date();
			
			MessageDataVO messageData = new MessageDataVO(
				AppDefs.DEF_MSGTYPE_NONE,
				AppDefs.DEBUG_LEVEL00,
				eventDate,
				eventSubject,
				eventMessage,
				oClass.getSimpleName(),
				AppDefs.DEF_BTN_ACTION_MESSAGE_NONE);
			
			MessageFrame f = new MessageFrame(parent);
			f.init(messageData, null);
			f.show();
		}
		else
		{
			String errmsg = eventSubject + " - " + eventMessage;

			AppError.showCmdMessage(
				errmsg, 
				oClass);		
		}			
	}
	
	public static void showWarnBox(
		JComponent parent,
		int debugLevel,
		String eventSubject, 
		String eventMessage, 
		Class oClass)
	{
		if(parent != null)
		{
			if(debugLevel == AppDefs.DEBUG_LEVEL)
			{
				Date eventDate = new Date();
				
				MessageDataVO messageData = new MessageDataVO(
					AppDefs.DEF_MSGTYPE_WARN,
					debugLevel,
					eventDate,
					eventSubject,
					eventMessage,
					oClass.getSimpleName(),
					AppDefs.DEF_BTN_ACTION_MESSAGE_NONE);
				
				MessageFrame f = new MessageFrame(parent);
				f.init(messageData, null);
				f.show();			
			}
		}
		else
		{
			String errmsg = eventSubject + " - " + eventMessage;

			AppError.showCmdWarn(
				debugLevel,
				errmsg, 
				oClass);		
		}			
	}

	public static void showErrorBox(
		JComponent parent,
		String eventSubject, 
		String eventMessage, 
		Class oClass)
	{
		if(parent != null)
		{
			Date eventDate = new Date();
			
			MessageDataVO messageData = new MessageDataVO(
				AppDefs.DEF_MSGTYPE_ERROR,
				AppDefs.DEBUG_LEVEL00,
				eventDate,
				eventSubject,
				eventMessage,
				oClass.getSimpleName(),
				AppDefs.DEF_BTN_ACTION_MESSAGE_NONE);
		
			MessageFrame f = new MessageFrame(parent);
			f.init(messageData, null);
			f.show();			
		}
		else
		{
			String errmsg = eventSubject + " - " + eventMessage;

			AppError.showCmdError(
				errmsg,
				oClass);		
		}
		//System.exit(1);
	}
	
}
