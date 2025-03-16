/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * HarmonicaVO.java
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

package br.com.tlmv.harmonicas.vo;

import java.awt.Graphics;
import java.util.ArrayList;

public class HarmonicaVO {
//Static
	public static final double NUM_STEPS = 360.0;	// 360.0 degree
	
//Private
	private ArrayList<PointVO> lsPts = null;
	
	private double val_An;
	private double val_Bn;
	private double val_C;
	//
	private int val_N;
	private double val_freq;
	private double val_T;
	//
	private int val_iterNum;
	private double val_t;
	//
	private double val_g;
	
	/* Auxiliar Functions */
	
	private double calculate_g(int val_iterNum, double val_t) {
		this.val_iterNum = val_iterNum + 1;
		this.val_t = val_t;
		
		double w1 = ((1.0 / 2.0) * this.val_C);
		double w2 = (this.val_An * Math.sin(2.0 * Math.PI * (double)this.val_N * this.val_freq * this.val_t));
		double w3 = (this.val_Bn * Math.cos(2.0 * Math.PI * (double)this.val_N * this.val_freq * this.val_t));
		
		this.val_g = w1 + w2 + w3;
		return this.val_g;
	}
	
	private double calculate_An() {
		double p1 = 1.0 / (Math.PI * (double)this.val_N);
		
		double w1 = Math.cos((1.0 * Math.PI * (double)this.val_N) / 4.0);
		double w2 = Math.cos((3.0 * Math.PI * (double)this.val_N) / 4.0);
		double w3 = Math.cos((6.0 * Math.PI * (double)this.val_N) / 4.0);
		double w4 = Math.cos((7.0 * Math.PI * (double)this.val_N) / 4.0);
		
		this.val_An = p1 * (w1 - w2 + w3 - w4);
		return this.val_An;
	}
	
	private double calculate_Bn() {
		double p1 = 1.0 / (Math.PI * (double)this.val_N);
		
		double w1 = Math.sin((3.0 * Math.PI * (double)this.val_N) / 4.0);
		double w2 = Math.sin((1.0 * Math.PI * (double)this.val_N) / 4.0);
		double w3 = Math.sin((7.0 * Math.PI * (double)this.val_N) / 4.0);
		double w4 = Math.sin((6.0 * Math.PI * (double)this.val_N) / 4.0);
		
		this.val_Bn = p1 * (w1 - w2 + w3 - w4);
		return this.val_Bn;
	}
	
	private double calculate_C() {
		this.val_C = 3.0 / 8.0;
		return this.val_C;
	}
	
//Public

	public HarmonicaVO(
		int val_N,
		double val_freq) 
	{
		this.lsPts = new ArrayList<PointVO>();
		
		this.val_N = val_N + 1;
		this.val_freq = val_freq;
		this.val_T = 1.0 / this.val_freq;

		calculate_An();
		calculate_Bn();
		calculate_C();
	}
	
	/* Methodes */
	
	public void execute() {
		double tmpVal_t = 0.0;
		
		double step = this.val_T / HarmonicaVO.NUM_STEPS;

		double total_steps = 2.0 * HarmonicaVO.NUM_STEPS;
		
		for(int i = 0; i < total_steps; i++) {
			tmpVal_t = tmpVal_t + step;
			double tmpVal_g = calculate_g(i, tmpVal_t);
			
			PointVO o = new PointVO(tmpVal_t, tmpVal_g);
			this.lsPts.add(o);
		}
	}
	
	/* Getters/Setters */
	
	public ArrayList<PointVO> getLsPts() {
		return lsPts;
	}

	public void setLsPts(ArrayList<PointVO> lsPts) {
		this.lsPts = lsPts;
	}

	public int getVal_iterNum() {
		return val_iterNum;
	}
	
	public void setVal_iterNum(int val_iterNum) {
		this.val_iterNum = val_iterNum;
	}
	
	public double getVal_t() {
		return val_t;
	}
	
	public void setVal_t(double val_t) {
		this.val_t = val_t;
	}
	
	public double getVal_An() {
		return this.val_An;
	}
	
	public void setVal_An(double val_An) {
		this.val_An = val_An;
	}
	
	public double getVal_Bn() {
		return val_Bn;
	}
	
	public void setVal_Bn(double val_Bn) {
		this.val_Bn = val_Bn;
	}
	
	public double getVal_C() {
		return this.val_C;
	}
	
	public void setVal_C(double val_C) {
		this.val_C = val_C;
	}
	
	public int getVal_N() {
		return this.val_N;
	}
	
	public void setVal_N(int val_N) {
		this.val_N = val_N;
	}
	
	public double getVal_freq() {
		return this.val_freq;
	}
	
	public void setVal_freq(double freq) {
		this.val_freq = freq;
	}
	
	public double getVal_g() {
		return this.val_g;
	}
	
	public void setVal_g(double g) {
		this.val_g = g;
	}

	public double getVal_T() {
		return val_T;
	}

	public void setVal_T(double val_T) {
		this.val_T = val_T;
	}
		
}
