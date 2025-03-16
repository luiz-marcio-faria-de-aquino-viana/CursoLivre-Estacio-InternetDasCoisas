/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * ListUtil.java
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

package br.com.tlmv.harmonicas.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.tlmv.harmonicas.AppDefs;
import br.com.tlmv.harmonicas.vo.HarmonicaVO;
import br.com.tlmv.harmonicas.vo.ItemDataVO;

public class ListUtil 
{
//Public
	
	//ListUtil: String Type

	public static int findHarmonicaPosInList(ArrayList<HarmonicaVO> lsHarmonica, Integer iterNum)
	{
		for(int i = 0; i < lsHarmonica.size(); i++)
		{
			HarmonicaVO o = lsHarmonica.get(i);

			if(iterNum == o.getVal_iterNum())
				return i;
		}
		return -1;
	}
	
	public static int findHarmonicaPosInList(HarmonicaVO[] lsHarmonica, Integer iterNum)
	{
		for(int i = 0; i < lsHarmonica.length; i++)
		{
			HarmonicaVO o = lsHarmonica[i];

			if(iterNum == o.getVal_iterNum())
				return i;
		}
		return -1;
	}
	
	public static int findItemDataPosInList(ArrayList<ItemDataVO> lsItem, String idItem)
	{
		for(int i = 0; i < lsItem.size(); i++)
		{
			ItemDataVO o = lsItem.get(i);

			if( idItem.compareToIgnoreCase(o.getItemDataId()) == 0 )
				return i;
		}
		return -1;
	}
	
	public static int findItemDataPosInListByDescricao(ArrayList<ItemDataVO> lsItem, String descrItem)
	{
		for(int i = 0; i < lsItem.size(); i++)
		{
			ItemDataVO o = lsItem.get(i);

			if( descrItem.compareToIgnoreCase(o.getDescricao()) == 0 )
				return i;
		}
		return -1;
	}

	public static int findItemDataPosInList(ItemDataVO[] lsItem, String idItem)
	{
		for(int i = 0; i < lsItem.length; i++)
		{
			ItemDataVO o = lsItem[i];

			if( idItem.compareToIgnoreCase(o.getItemDataId()) == 0 )
				return i;
		}
		return -1;
	}

	public static int findItemDataPosInListByDescricao(ItemDataVO[] lsItem, String descrItem)
	{
		for(int i = 0; i < lsItem.length; i++)
		{
			ItemDataVO o = lsItem[i];

			if( descrItem.compareToIgnoreCase(o.getDescricao()) == 0 )
				return i;
		}
		return -1;
	}

	public static int findPosInList(ArrayList<String> lsStr, String inStr)
	{
		for(int i = 0; i < lsStr.size(); i++)
		{
			String str = lsStr.get(i);
			
			if(inStr.compareToIgnoreCase(str) == 0)
				return i;
		}
		return -1;
	}
	
	public static int findPosInList(String[] lsStr, String inStr)
	{
		for(int i = 0; i < lsStr.length; i++)
		{
			String str = lsStr[i];
			
			if(inStr.compareToIgnoreCase(str) == 0)
				return i;
		}
		return -1;
	}
	
	public static int findPosInListStartWith(String[] lsStr, String inStr)
	{
		String inStrL = inStr.toLowerCase();
		
		for(int i = 0; i < lsStr.length; i++)
		{
			String strL = lsStr[i].toLowerCase();
			
			if( inStrL.startsWith(strL) )
				return i;
		}
		return -1;
	}
	
	public static int findPosInArray(int[] lsVal, int inVal)
	{
		for(int i = 0; i < lsVal.length; i++)
		{
			if(inVal == lsVal[i])
				return i;
		}
		return -1;
	}
	
	//ListUtil: ToArray

	public static String[] toArray(int tipoLista, Object obj)
	{
		String[] arr = null;
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		NumberFormat nf6 = FormatUtil.newNumberFormatPtBr(6);
		
		NumberFormat nf2 = FormatUtil.newNumberFormatPtBr(2);
		
		NumberFormat nf1 = FormatUtil.newNumberFormatPtBr(1);

		Date dataHoraAtual = new Date();
		
		Date dataAtual = new Date(dataHoraAtual.getYear(), dataHoraAtual.getMonth(), dataHoraAtual.getDate());
		
		if(tipoLista == AppDefs.defListaHarmonicas) {
			arr = new String[AppDefs.HDR_HARMONICAS_VALUES.length];
			
			HarmonicaVO oHarmonica = (HarmonicaVO)obj; 
			
			String str_iterNum = nf6.format(oHarmonica.getVal_iterNum());
			String str_t = nf6.format(oHarmonica.getVal_t());
			String str_T = nf6.format(oHarmonica.getVal_T());
			String str_An = nf6.format(oHarmonica.getVal_An());
			String str_Bn = nf6.format(oHarmonica.getVal_Bn());
			String str_C = nf6.format(oHarmonica.getVal_C());
			String str_N = nf6.format(oHarmonica.getVal_N());
			String str_freq = nf6.format(oHarmonica.getVal_freq());
			String str_g = nf6.format(oHarmonica.getVal_g());

			int n = 0;
			
			//"IterNum", "t", "T", "An", "Bn", "C", "N", "freq", "g"
			//
			arr[n++] = str_iterNum;
			arr[n++] = str_t;
			arr[n++] = str_T;
			arr[n++] = str_An;
			arr[n++] = str_Bn;
			arr[n++] = str_C;
			arr[n++] = str_N;
			arr[n++] = str_freq;
			arr[n++] = str_g;
		}
		else {
			//TODO:
		}
		return arr;
	}
	
	public static String[][] toArrayList(int tipoLista, ArrayList lsObj)
	{
		ArrayList<String[]> lsResult = new ArrayList<String[]>();
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		NumberFormat nf2 = FormatUtil.newNumberFormatPtBr(2);
		
		NumberFormat nf1 = FormatUtil.newNumberFormatPtBr(1);

		Date dataHoraAtual = new Date();
		
		Date dataAtual = new Date(dataHoraAtual.getYear(), dataHoraAtual.getMonth(), dataHoraAtual.getDate());
		
		int nRows = 0, nCols = 0;
		
		if(tipoLista == AppDefs.defListaHarmonicas)
		{
			nRows = lsObj.size();
			nCols = AppDefs.HDR_HARMONICAS_VALUES.length;
			
			//TODO:
		}
		
		String[][] arrResult = lsResult.toArray(new String[nRows][nCols]);
		return arrResult;
	}
	
}
