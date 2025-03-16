/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * PointVO.java
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

public class PointVO 
{
//Static
	public static final double HALF_WIDTH = 1.0;
	public static final double HALF_HEIGHT = 1.0;
	
//Private
	private double xp;
	private double yp;
	
//Public
	
	public PointVO(double xp, double yp) {
		this.xp = xp;
		this.yp = yp;
	}
	
	/* Methodes */
	
	public void draw(Graphics g) {
		double x = this.xp - PointVO.HALF_WIDTH;
		double y = this.yp - PointVO.HALF_HEIGHT;
		
		double w = 2.0 * PointVO.HALF_WIDTH;
		double h = 2.0 * PointVO.HALF_HEIGHT;
		
		g.drawRect((int)x, (int)y, (int)w, (int)h);
	}
	
	public PointVO transf(PositionVO p) {
		double x = p.getdX() + (this.xp * p.getSclX());
		double y = p.getdY() + (this.yp * p.getSclY());

		PointVO oPt = new PointVO(x, y);
		return oPt;
	}
	
	public PointVO transf(double dX, double dY, double sclX, double sclY) {
		double x = dX + (this.xp * sclX);
		double y = dY + (this.yp * sclY);

		PointVO oPt = new PointVO(x, y);
		return oPt;
	}

	public PointVO sum(PointVO o) {
		double x = this.xp;
		double y = this.yp + o.getYp();

		PointVO oPt = new PointVO(x, y);
		return oPt;
	}

	/* Getters/Setters */
	
	public double getXp() {
		return xp;
	}

	public void setXp(double xp) {
		this.xp = xp;
	}

	public double getYp() {
		return yp;
	}

	public void setYp(double yp) {
		this.yp = yp;
	}
	
}
