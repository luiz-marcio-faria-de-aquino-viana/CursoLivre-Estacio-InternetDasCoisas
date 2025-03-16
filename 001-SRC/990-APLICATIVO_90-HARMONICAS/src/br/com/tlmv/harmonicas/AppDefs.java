/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * AppDefs.java
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

public class AppDefs {
	public static int DEBUG_LEVEL_00 = 0;	//0=disabled
	public static int DEBUG_LEVEL_01 = 1;

	public static int DEBUG_LEVEL = AppDefs.DEBUG_LEVEL_01;

	public static String APP_NAME = "992-APLICATIVO_92-PLA";

	public static String APP_DESCRIPTION = "001 - Implementacao do Algoritimo PLA";

	public static String DATASET = "/home/lmarcio/102-CURSOS/101-UNIVERSIDADE_ESTACIO_DE_SA/101-CURSOS_LIVRES_PRESENCIAIS/101-CURSO_LIVRE_PRESENCIAL-UNIDADE_SULACAP-INTRODUCAO_INTERNET_DAS_COISAS/201-DATASETS/winequality-red/winequality-red.dat";

	public static double MAX_INTERVAL = 1.0;

	public static double MIN_INTERVAL = -1.0;
	
	public static int SAMPLE_SIZE = 10;

	public static int DATASET_SIZE = 1000;

	public static int MAX_NUMBER_ITER = 1000;
	
	public static int DBG_NUM_ITER = 100;
	
	public static double VC_DIMENSION = 3;	// Perceptron VC-dimension = 3 
	
	public static double CONFIANCE = 0.95;	// Confianca = 95% => Sigma = 5%

	public static double W_N = 0.1;			// step size
	
	public static double TICK_SZ = 3.0;
	
	public static double RAD_SZ = 4.0;
	
	//public static int MAX_NUM_EXEC = 1000;
	public static int MAX_NUM_EXEC = 10;

	public static double EXPECTED_ERR_BOUND = 0.01;
	
	public static int DEF_SEQ_INITVAL = 100001;
	public static int DEF_SEQ_TERMVAL = 999999;

	//WINEQUALITY-RED HEAD_SIZE
	//
	public static int DEF_HDRSZ_WINEQUALITY_RED = 16;

	//WINEQUALITY-RED COLUMN_NAME
	//
	public static int DEF_COL_FIXEDACIDITY = 0;
	public static int DEF_COL_VOLATILEACIDITY = 1;
	public static int DEF_COL_CITRICACID = 2;
	public static int DEF_COL_RESIDUALSUGAR = 3;
	public static int DEF_COL_CHLORIDES = 4;
	public static int DEF_COL_FREESULFURDIOXIDE = 5;
	public static int DEF_COL_TOTALSULFURDIOXIDE = 6;
	public static int DEF_COL_DENSITY = 7;
	public static int DEF_COL_PH = 8;
	public static int DEF_COL_SULPHATES = 9;
	public static int DEF_COL_ALCOHOL = 10;
	public static int DEF_COL_QUALITY = 11;
		
	//WINEQUALITY-RED [MIN,MAX] RANGES
	//
	public static double DEF_MIN_FIXEDACIDITY = 4.6;
	public static double DEF_MAX_FIXEDACIDITY = 15.9;
	//
	public static double DEF_MIN_VOLATILEACIDITY = 0.12;
	public static double DEF_MAX_VOLATILEACIDITY = 1.58;
	//
	public static double DEF_MIN_CITRICACID = 0.0;
	public static double DEF_MAX_CITRICACID = 1.0;
	//
	public static double DEF_MIN_RESIDUALSUGAR = 0.9;	
	public static double DEF_MAX_RESIDUALSUGAR = 15.5;	
	//
	public static double DEF_MIN_CHLORIDES = 0.012;
	public static double DEF_MAX_CHLORIDES = 0.611;
	//
	public static double DEF_MIN_FREESULFURDIOXIDE = 1.0;	
	public static double DEF_MAX_FREESULFURDIOXIDE = 72.0;	
	//
	public static double DEF_MIN_TOTALSULFURDIOXIDE = 6.0;
	public static double DEF_MAX_TOTALSULFURDIOXIDE = 289.0;
	//
	public static double DEF_MIN_DENSITY = 0.99007;
	public static double DEF_MAX_DENSITY = 1.00369;
	//
	public static double DEF_MIN_PH = 2.74;
	public static double DEF_MAX_PH = 4.01;
	//
	public static double DEF_MIN_SULPHATES = 0.33;
	public static double DEF_MAX_SULPHATES = 2.0;
	//
	public static double DEF_MIN_ALCOHOL = 8.4;
	public static double DEF_MAX_ALCOHOL = 14.9;
	//
	public static int DEF_MIN_QUALITY = 0;
	public static int DEF_MAX_QUALITY = 10;

	//WINEQUALITY-RED MEANS
	//
	public static double DEF_MEAN_FIXEDACIDITY = (DEF_MIN_FIXEDACIDITY + DEF_MAX_FIXEDACIDITY) / 2.0;
	public static double DEF_MEAN_VOLATILEACIDITY = (DEF_MIN_VOLATILEACIDITY + DEF_MAX_VOLATILEACIDITY) / 2.0;
	public static double DEF_MEAN_CITRICACID = (DEF_MIN_CITRICACID + DEF_MAX_CITRICACID) / 2.0;
	public static double DEF_MEAN_RESIDUALSUGAR = (DEF_MIN_RESIDUALSUGAR + DEF_MAX_RESIDUALSUGAR) / 2.0;	
	public static double DEF_MEAN_CHLORIDES = (DEF_MIN_CHLORIDES + DEF_MAX_CHLORIDES) / 2.0;
	public static double DEF_MEAN_FREESULFURDIOXIDE = (DEF_MIN_FREESULFURDIOXIDE + DEF_MAX_FREESULFURDIOXIDE) / 2.0;	
	public static double DEF_MEAN_TOTALSULFURDIOXIDE = (DEF_MIN_TOTALSULFURDIOXIDE + DEF_MAX_TOTALSULFURDIOXIDE) / 2.0;
	public static double DEF_MEAN_DENSITY = (DEF_MIN_DENSITY + DEF_MAX_DENSITY) / 2.0;
	public static double DEF_MEAN_PH = (DEF_MIN_PH + DEF_MAX_PH) / 2.0;
	public static double DEF_MEAN_SULPHATES = (DEF_MIN_SULPHATES + DEF_MAX_SULPHATES) / 2.0;
	public static double DEF_MEAN_ALCOHOL = (DEF_MIN_ALCOHOL + DEF_MAX_ALCOHOL) / 2.0;
	public static int DEF_MEAN_QUALITY = (DEF_MIN_QUALITY + DEF_MAX_QUALITY) / 2;
	
}
