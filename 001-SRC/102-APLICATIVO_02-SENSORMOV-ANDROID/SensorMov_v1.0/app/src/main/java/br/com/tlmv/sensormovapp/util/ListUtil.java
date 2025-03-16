/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * ListUtil.java
 * Autor:
 * - Luiz Marcio Faria de Aquino Viana, Pós-D.Sc. - Engenheiro, 08/02/2023
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

package br.com.tlmv.sensormovapp.util;

import java.util.List;

import android.widget.ListView;
import android.widget.Spinner;

import br.com.tlmv.sensormovapp.vo.ItemVO;

public class ListUtil {

	public static void selectItemListBox(ListView cbx, List ls, String id) {
		for(int i = 0; i < ls.size(); i++) {
			ItemVO o = (ItemVO)ls.get(i);
			if( id.equalsIgnoreCase(o.getId()) ) {
				cbx.setSelection(i);
				return;
			}
		}
	}
		
	public static void selectItemComboBox(Spinner cbx, List ls, String id) {
		for(int i = 0; i < ls.size(); i++) {
			ItemVO o = (ItemVO)ls.get(i);
			if( id.equalsIgnoreCase(o.getId()) ) {
				cbx.setSelection(i);
				return;
			}
		}
	}
		
}
