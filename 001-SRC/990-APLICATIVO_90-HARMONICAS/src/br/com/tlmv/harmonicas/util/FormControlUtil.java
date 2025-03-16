/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * FormControlUtil.java
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

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.table.JTableHeader;

import br.com.tlmv.harmonicas.AppDefs;
import br.com.tlmv.harmonicas.frm.MainFrame;
import br.com.tlmv.harmonicas.vo.ItemDataVO;

public class FormControlUtil 
{
	public static void loadIcon(JFrame frm)
	{
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image iconImg = tk.getImage(MainFrame.class.getResource(AppDefs.APP_ICON));
		frm.setIconImage(iconImg);
	}

	public static int setCbxByText(JComboBox cbx, String[] lsItemData, String text)
	{
		if( (lsItemData == null) || (lsItemData.length == 0) ) return -1;
		
		for(int i = 0; i < lsItemData.length; i++)
		{
			String item = lsItemData[i];
			
			if( text.equals(item) )
			{
				cbx.setSelectedIndex(i);
				return i;
			}
		}
		cbx.setSelectedIndex(0);
		return 0;
	}

	public static int setCbx(JComboBox cbx, String[] lsItemData, String id)
	{
		if( (lsItemData == null) || (lsItemData.length == 0) ) return -1;
		
		for(int i = 0; i < lsItemData.length; i++)
		{
			String item = lsItemData[i];
			
			if( id.equals(item) )
			{
				cbx.setSelectedIndex(i);
				return i;
			}
		}
		cbx.setSelectedIndex(0);
		return 0;
	}

	public static int setCbx(JComboBox cbx, ItemDataVO[] lsItemData, String id)
	{
		if( (lsItemData == null) || (lsItemData.length == 0) ) return -1;
		
		for(int i = 0; i < lsItemData.length; i++)
		{
			ItemDataVO item = lsItemData[i];
			
			if( id.equals(item.getItemDataId()) )
			{
				cbx.setSelectedIndex(i);
				return i;
			}
		}
		cbx.setSelectedIndex(0);
		return 0;
	}

	public static int setCbx(JComboBox cbx, ArrayList<ItemDataVO> lsItemData, String id)
	{
		if( (lsItemData == null) || (lsItemData.size() == 0) ) return -1;
		
		for(int i = 0; i < lsItemData.size(); i++)
		{
			ItemDataVO item = lsItemData.get(i);
			
			if( id.equals(item.getItemDataId()) )
			{
				cbx.setSelectedIndex(i);
				return i;
			}
		}
		cbx.setSelectedIndex(0);
		return 0;
	}
	
	public static int setCbxByValue(JComboBox cbx, ItemDataVO[] lsItemData, String value)
	{
		if( (lsItemData == null) || (lsItemData.length == 0) ) return -1;
		
		for(int i = 0; i < lsItemData.length; i++)
		{
			ItemDataVO item = lsItemData[i];
			
			if( value.equals(item.getDescricao()) )
			{
				cbx.setSelectedIndex(i);
				return i;
			}
		}
		cbx.setSelectedIndex(0);
		return 0;
	}

	public static int setCbxByValue(JComboBox cbx, ArrayList<ItemDataVO> lsItemData, String value)
	{
		if( (lsItemData == null) || (lsItemData.size() == 0) ) return -1;
		
		for(int i = 0; i < lsItemData.size(); i++)
		{
			ItemDataVO item = lsItemData.get(i);
			
			if( value.equals(item.getDescricao()) )
			{
				cbx.setSelectedIndex(i);
				return i;
			}
		}
		cbx.setSelectedIndex(0);
		return 0;
	}
	
	public static JPanel newTabPanel(String name) 
	{
	    JPanel panel = new JPanel(false);
	    panel.setLayout(null);
	    panel.setName(name);	    
	    return panel;
	}	
	
	public static JLabel newLabel(String label, int xp, int yp, int w, int h, boolean isVisible)
	{
		JLabel o = new JLabel(label);
		o.setLocation(xp, yp);
		o.setSize(w, h); 
		o.setVisible(isVisible);
		return o;		
	}
	
	public static JCheckBox newCheckBox(boolean isChecked, String label, int xp, int yp, int w, int h, boolean isVisible, boolean isEnabled)
	{
		JCheckBox o = new JCheckBox();
		o.setLocation(xp, yp);
		o.setSize(w, h); 
		o.setVisible(isVisible);
		o.setEnabled(isEnabled);
		o.setSelected(isChecked);
		o.setText(label);
		return o;
	}
	
	public static JCheckBox newCheckBox(boolean isChecked, String label, int xp, int yp, int w, int h, boolean isVisible, boolean isEnabled, String action)
	{
		JCheckBox o = new JCheckBox();
		o.setLocation(xp, yp);
		o.setSize(w, h); 
		o.setVisible(isVisible);
		o.setEnabled(isEnabled);
		o.setSelected(isChecked);
		o.setText(label);
		o.setActionCommand(action);
		return o;
	}
	
	public static JTextField newTextField(String textval, int xp, int yp, int w, int h, boolean isVisible, boolean isEditable)
	{
		JTextField o = new JTextField();
		o.setLocation(xp, yp);
		o.setSize(w, h); 
		o.setVisible(isVisible);
		o.setEditable(isEditable);
		o.setText(textval);
		return o;
	}
	
	public static JPasswordField newPasswordField(String textval, int xp, int yp, int w, int h, boolean isVisible, boolean isEditable)
	{
		JPasswordField o = new JPasswordField();
		o.setLocation(xp, yp);
		o.setSize(w, h); 
		o.setVisible(isVisible);
		o.setEditable(isEditable);
		o.setText(textval);
		return o;
	}
	
	public static JTextArea newTextArea(String textval, int xp, int yp, int w, int h, boolean isVisible, boolean isEditable)
	{
		Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
		
		JTextArea o = new JTextArea();
		o.setLocation(xp, yp);
		o.setSize(w, h);
		o.setVisible(isVisible);
		o.setEditable(isEditable);
		o.setText(textval);
		o.setBorder(border);
		o.setAutoscrolls(true);
		return o;
	}
	
	public static JComboBox newComboBox(Object[] arr, int xp, int yp, int w, int h, boolean isVisible)
	{
		JComboBox o = new JComboBox(arr);
		o.setLocation(xp, yp);
		o.setSize(w, h); 
		o.setVisible(isVisible);
		return o;
	}	

	public static JComboBox newComboBox(Object[] arr, int xp, int yp, int w, int h, boolean isVisible, String action)
	{
		JComboBox o = new JComboBox(arr);
		o.setLocation(xp, yp);
		o.setSize(w, h); 
		o.setVisible(isVisible);
		o.setActionCommand(action);
		return o;
	}	
	
	public static JList newList(JComponent parent, Object[] arr, int xp, int yp, int w, int h, boolean isVisible)
	{
		JList o = new JList(arr);
		o.setBounds(xp, yp, w, h);
	
		JScrollPane oPanel = new JScrollPane(o);
		oPanel.setLocation(xp, yp);
		oPanel.setSize(w, h); 
		oPanel.setVisible(isVisible);
		parent.add(oPanel);
		
		return o;
	}	
	
	public static JTable newTable(JComponent parent, String[] hdr, String[][] rows, int xp, int yp, int w, int h, boolean isVisible)
	{
        JTable o = new JTable(rows, hdr);
         
		JScrollPane oPanel = new JScrollPane(o);
        oPanel.setBounds(xp, yp, w, h);
		parent.add(oPanel);
		
		return o;
	}	
	
	public static JButton newButton(String label, String action, int xp, int yp, int w, int h, boolean isVisible, ActionListener listener)
	{
		JButton o = new JButton(label);
		o.setActionCommand(action);
		o.setLocation(xp, yp);
		o.setSize(w, h); 
		o.setVisible(isVisible);
		if(listener != null)
			o.addActionListener(listener);
		return o;
	}
	
	public static JMenuItem newMenuItem(String label, String action, ActionListener listener)
	{
		JMenuItem mnuItem = new JMenuItem(label);
		mnuItem.setActionCommand(action);
		mnuItem.addActionListener(listener);
		return mnuItem;
	}
	
}

