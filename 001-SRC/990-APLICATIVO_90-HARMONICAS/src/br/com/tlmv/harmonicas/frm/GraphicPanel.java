/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * GraphicPanel.java
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

package br.com.tlmv.harmonicas.frm;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import br.com.tlmv.harmonicas.algor.AlgorHarmonica;
import br.com.tlmv.harmonicas.vo.PointGroupVO;

public class GraphicPanel extends JPanel 
{
//Private
	private AlgorHarmonica algor = null;
	
	private int maxNumHarmonica;
	private double val_freq;
	private int val_N;
	
//Public
	
	public GraphicPanel(int x, int y, int w, int h, int maxNumHarmonica, double val_freq, int val_N) {
		this.setLocation(x, y);
		this.setSize(w, h);

		this.maxNumHarmonica = maxNumHarmonica;
		this.val_freq = val_freq;
		this.val_N = val_N;
	
		this.algor = new AlgorHarmonica(
			w,
			h,
			this.maxNumHarmonica, 
			this.val_freq);
		this.algor.execute();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);

		this.algor.drawSignal(g);
		this.algor.drawAllHarmonica(g);
		//this.algor.drawHarmonica(g, 0, Color.BLUE);
	}

}
