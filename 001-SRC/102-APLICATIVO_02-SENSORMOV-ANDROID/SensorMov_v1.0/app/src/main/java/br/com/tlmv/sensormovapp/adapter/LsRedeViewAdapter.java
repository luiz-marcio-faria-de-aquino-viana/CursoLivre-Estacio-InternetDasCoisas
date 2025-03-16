/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * LsRedeViewAdapterVO.java
 * Autor:
 * - Luiz Marcio Faria de Aquino Viana, Pós-D.Sc. - Engenheiro, 09/02/2023
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

package br.com.tlmv.sensormovapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;

import br.com.tlmv.sensormovapp.MainActivity;
import br.com.tlmv.sensormovapp.R;
import br.com.tlmv.sensormovapp.util.FormatUtil;
import br.com.tlmv.sensormovapp.vo.RedeVO;

public class LsRedeViewAdapter extends ArrayAdapter<RedeVO> {
//Private
    private Context m_context;
    private ArrayList m_lsRede;

//Public

    public LsRedeViewAdapter(Context context, ArrayList lsRede)
    {
        super(context, R.layout.lsrede);

        this.m_context = context;
        this.m_lsRede = lsRede;

        this.clear();
        for(int i = 0; i < m_lsRede.size(); i++) {
            this.add((RedeVO)m_lsRede.get(i));
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        NumberFormat nf3 = FormatUtil.newDecimalFormat(3);
        NumberFormat nf6 = FormatUtil.newDecimalFormat(6);

        RedeVO o = null;

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) m_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lsrede, parent, false);
            o = (RedeVO)m_lsRede.get(position);
        }
        else {
            o = (RedeVO)m_lsRede.get(position);
        }

        if(o != null) {
            String strObjectId = Integer.toString(o.getId());
            String strScanNum = Integer.toString(o.getScanNum());
            String strEquipment = o.getEquipment();
            String strBSSID = o.getBssid();
            String strSSID = o.getSsid();
            String strLevel = Integer.toString(o.getLevel());
            String strTimestamp = Long.toString(o.getTimestamp());
            String strDistance = nf3.format(o.getDistance());
            String strPowerWatt = nf6.format(o.getPowerWatt());

            //TextView txtObjectId = (TextView) convertView.findViewById(R.id.txtRedeObjectId);
            //txtObjectId.setText(strObjectId);

            //TextView txtScanNum = (TextView) convertView.findViewById(R.id.txtRedeScanNum);
            //txtScanNum.setText(strScanNum);

            //TextView txtEquipment = (TextView) convertView.findViewById(R.id.txtRedeEquipment);
            //txtEquipment.setText(strEquipment);

            TextView txtBSSID = (TextView) convertView.findViewById(R.id.txtRedeBSSID);
            txtBSSID.setText(strBSSID);

            TextView txtSSID = (TextView) convertView.findViewById(R.id.txtRedeSSID);
            txtSSID.setText(strSSID);

            TextView txtLevel = (TextView) convertView.findViewById(R.id.txtRedeLevel);
            txtLevel.setText(strLevel);

            TextView txtDistance = (TextView) convertView.findViewById(R.id.txtRedeDistance);
            txtDistance.setText(strDistance);

            TextView txtPowerWatt = (TextView) convertView.findViewById(R.id.txtRedePowerWatt);
            txtPowerWatt.setText(strPowerWatt);

            //TextView txtTimestamp = (TextView) convertView.findViewById(R.id.txtRedeTimestamp);
            //txtTimestamp.setText(strTimestamp);
        }

        return convertView;
    }

    /* Getters/Setters */

    public ArrayList getLsRede() {
        return m_lsRede;
    }

}
