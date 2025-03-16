/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * PositionVO.java
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

import java.util.ArrayList;

public class PositionVO {
//Private
	private double dX = 0.0;
	private double dY = 0.0;
	private double sclX = 1.0;
	private double sclY = 1.0;

	private double minX = Double.MAX_VALUE; 
	private double minY = Double.MAX_VALUE;
	
	private double maxX = Double.MIN_VALUE; 
	private double maxY = Double.MIN_VALUE; 
	
	private double w = 1.0;
	private double h = 1.0;

//Public
	
	public PositionVO() {
		this.dX = 0.0;
		this.dY = 0.0;
		this.sclX = 1.0;
		this.sclY = 1.0;

		this.minX = Double.MAX_VALUE; 
		this.minY = Double.MAX_VALUE;
		
		this.maxX = Double.MIN_VALUE; 
		this.maxY = Double.MIN_VALUE; 
		
		this.w = 1.0;
		this.h = 1.0;
	}
	
	public PositionVO(double wScr, double hScr, ArrayList<PointVO> lsPts) {
		for(int i = 0; i < lsPts.size(); i++) {
			PointVO p = lsPts.get(i);
			
			if(p.getXp() < minX)
				minX = p.getXp();
			if(p.getXp() > maxX)
				maxX = p.getXp();
			
			if(p.getYp() < minY)
				minY = p.getYp();
			if(p.getYp() > maxY)
				maxY = p.getYp();
		}
		
		w = maxX - minX;
		h = maxY - minY;
		
		sclX = wScr / w;
		sclY = hScr / h;
		
		dX = -(minX * sclX);
		dY = -(minY * sclY);
	}
	
	public PositionVO(double wScr, double hScr, PointVO[] lsPts) {
		for(int i = 0; i < lsPts.length; i++) {
			PointVO p = lsPts[i];
			
			if(p.getXp() < minX)
				minX = p.getXp();
			if(p.getXp() > maxX)
				maxX = p.getXp();
			
			if(p.getYp() < minY)
				minY = p.getYp();
			if(p.getYp() > maxY)
				maxY = p.getYp();
		}
		
		w = maxX - minX;
		h = maxY - minY;
		
		sclX = wScr / w;
		sclY = hScr / h;
		
		dX = -(minX * sclX);
		dY = -(minY * sclY);
	}

	/* Getters/Setters */
	
	public double getdX() {
		return dX;
	}

	public void setdX(double dX) {
		this.dX = dX;
	}

	public double getdY() {
		return dY;
	}

	public void setdY(double dY) {
		this.dY = dY;
	}

	public double getSclX() {
		return sclX;
	}

	public void setSclX(double sclX) {
		this.sclX = sclX;
	}

	public double getSclY() {
		return sclY;
	}

	public void setSclY(double sclY) {
		this.sclY = sclY;
	}

	public double getMinX() {
		return minX;
	}

	public void setMinX(double minX) {
		this.minX = minX;
	}

	public double getMinY() {
		return minY;
	}

	public void setMinY(double minY) {
		this.minY = minY;
	}

	public double getMaxX() {
		return maxX;
	}

	public void setMaxX(double maxX) {
		this.maxX = maxX;
	}

	public double getMaxY() {
		return maxY;
	}

	public void setMaxY(double maxY) {
		this.maxY = maxY;
	}

	public double getW() {
		return w;
	}

	public void setW(double w) {
		this.w = w;
	}

	public double getH() {
		return h;
	}

	public void setH(double h) {
		this.h = h;
	}
	
}
