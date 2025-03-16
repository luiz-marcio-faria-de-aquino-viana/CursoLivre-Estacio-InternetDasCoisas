/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * AlgorHarmonica.java
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

package br.com.tlmv.harmonicas.algor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Paint;
import java.util.ArrayList;

import br.com.tlmv.harmonicas.vo.HarmonicaVO;
import br.com.tlmv.harmonicas.vo.PointVO;
import br.com.tlmv.harmonicas.vo.PositionVO;

public class AlgorHarmonica 
{
//Private
	private ArrayList<HarmonicaVO> lsHarmonica;
	private int maxNumHarmonica;
	
	private PointVO[] lsPtsSignal = null; 
			
	private double val_freq;

	private PositionVO p_Signal = new PositionVO();
	private PositionVO p_Harmonica = new PositionVO();
	
	private double w = 1.0;
	private double h = 1.0;
	
	/* Auxiliary Functions */
	
	public void buildSignal() {
		if(this.lsHarmonica.size() > 0) {
			HarmonicaVO oHarmonica = this.lsHarmonica.get(0);			
			ArrayList<PointVO> lsPtsHarmonica = oHarmonica.getLsPts();

			lsPtsSignal = new PointVO[lsPtsHarmonica.size()];
			for(int j = 0; j < lsPtsHarmonica.size(); j++) {
				PointVO oPtSrc = lsPtsHarmonica.get(j);

				PointVO oPtDst = new PointVO(oPtSrc.getXp(), oPtSrc.getYp());
				lsPtsSignal[j] = oPtDst;				
			}
			
			for(int i = 1; i < this.maxNumHarmonica; i++) {
				oHarmonica = this.lsHarmonica.get(i);			
				lsPtsHarmonica = oHarmonica.getLsPts();

				for(int j = 0; j < lsPtsHarmonica.size(); j++) {
					PointVO oPtSrc = lsPtsHarmonica.get(j);
					
					lsPtsSignal[j] = lsPtsSignal[j].sum(oPtSrc);
				}
			}
		}		
	}
	
	private void calculate_PosSignal() {
		p_Signal = new PositionVO(this.w, this.h, lsPtsSignal);
	}
	
	private void calculate_PosHarmonica() {
		p_Harmonica = new PositionVO();
		
		for(int i = 0; i < this.maxNumHarmonica; i++) {
			HarmonicaVO oHarmonica = this.lsHarmonica.get(i);

			ArrayList<PointVO> lsPts = oHarmonica.getLsPts();
			PositionVO p = new PositionVO(this.w, this.h, lsPts);
			
			if(i == 0) {
				p_Harmonica = p;
			}
			else {
				if(p.getSclY() < p_Harmonica.getSclY())
					p_Harmonica = p;
			}
		}		
	}
		
//Public
	
	public AlgorHarmonica(double w, double h, int maxNumHarmonica, double val_freq) {
		this.w = w;
		this.h = h;
		
		this.lsHarmonica = new ArrayList<HarmonicaVO>();
		this.maxNumHarmonica = maxNumHarmonica;
		this.val_freq = val_freq;
		
		for(int i = 0; i < this.maxNumHarmonica; i++) {
			HarmonicaVO oHarmonica = new HarmonicaVO(i, this.val_freq);
			this.lsHarmonica.add(oHarmonica);
		}
	}
	
	/* Methodes */
	
	public void execute() {
		for(int i = 0; i < this.maxNumHarmonica; i++) {
			HarmonicaVO oHarmonica = this.lsHarmonica.get(i);
			oHarmonica.execute();
		}		
		this.calculate_PosHarmonica();

		this.buildSignal();
		this.calculate_PosSignal();
	}

	public void drawSignal(Graphics g) {
		if(lsPtsSignal.length > 0) {
			PointVO pTmp = lsPtsSignal[0];
			
			PointVO ptI = pTmp.transf(p_Signal);
			PointVO ptF = null;
			for(int i = 1; i < lsPtsSignal.length; i++) {
				pTmp = lsPtsSignal[i];
				
				ptF = pTmp.transf(p_Signal);
				
				g.setColor(Color.RED);
				g.drawLine((int)ptI.getXp(), (int)ptI.getYp(), (int)ptF.getXp(), (int)ptF.getYp());
				
				ptI = ptF;
			}
		}
	}

	public void drawAllHarmonica(Graphics g) {
		int step = 255 / this.maxNumHarmonica;
		int t_step = 0;
		for(int i = 0; i < this.maxNumHarmonica; i++) {
			Color c = new Color(t_step, t_step, 255);
			g.setColor(c);
			this.drawHarmonica(g, i, c);
			
			t_step += step;
		}
	}

	public void drawHarmonica(Graphics g, int val_N, Color c) {
		if(val_N < this.maxNumHarmonica) {
			HarmonicaVO oHarmonica = this.lsHarmonica.get(val_N);

			ArrayList<PointVO> lsPts = oHarmonica.getLsPts();
			if(lsPts.size() > 0) {
				PointVO pTmp = lsPts.get(0);
				
				PointVO ptI = pTmp.transf(p_Harmonica);

				PointVO ptF = null;
				for(int i = 1; i < lsPts.size(); i++) {
					pTmp = lsPts.get(i);
					
					ptF = pTmp.transf(p_Harmonica);
					
					g.setColor(c);
					g.drawLine((int)ptI.getXp(), (int)ptI.getYp(), (int)ptF.getXp(), (int)ptF.getYp());
					
					ptI = ptF;
				}
			}
		}
	}
	
	/* Getters/Setters */
	
	public HarmonicaVO get(int pos) {
		HarmonicaVO o = null;
		if(pos < this.lsHarmonica.size())
			o = this.lsHarmonica.get(pos);
		return o;
	}

	public int size() {
		return this.lsHarmonica.size();
	}

	public double getValFreq() {
		return this.val_freq;
	}
	
}
