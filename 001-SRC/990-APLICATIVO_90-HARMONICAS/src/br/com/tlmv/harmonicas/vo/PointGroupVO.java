/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * PointGroupVO.java
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

public class PointGroupVO 
{
//Private
	private ArrayList<PointVO> lsPts = null;
	
//Public
		
	public PointGroupVO() {
		this.lsPts = new ArrayList<PointVO>();
	}

	/* Methodes */
	
	public void draw(Graphics g) {
		if(lsPts.size() > 0) {
			PointVO ptI = lsPts.get(0);
			ptI.draw(g);

			PointVO ptF = null;
			for(int i = 1; i < lsPts.size(); i++) {
				ptF = lsPts.get(i);
				ptF.draw(g);
				
				g.drawLine((int)ptI.getXp(), (int)ptI.getYp(), (int)ptF.getXp(), (int)ptF.getYp());
				
				ptI = ptF;
			}
		}
	}

	/* Getters/Setters */
	
	public ArrayList<PointVO> getLsPts() {
		return lsPts;
	}

	public void setLsPts(ArrayList<PointVO> lsPts) {
		this.lsPts = lsPts;
	}
	
}
