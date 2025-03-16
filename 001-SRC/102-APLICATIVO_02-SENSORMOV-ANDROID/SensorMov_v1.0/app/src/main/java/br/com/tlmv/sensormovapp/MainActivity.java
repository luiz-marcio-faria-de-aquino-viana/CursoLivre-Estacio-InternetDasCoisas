/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * MainActivity.java
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

package br.com.tlmv.sensormovapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.tlmv.sensormovapp.adapter.LsRedeViewAdapter;
import br.com.tlmv.sensormovapp.util.FormatUtil;
import br.com.tlmv.sensormovapp.vo.RedeVO;

public class MainActivity extends AppCompatActivity implements Runnable {
//Static
    public static MainActivity gApp = null;

    public static Integer gScanNum = 1001;

    private ArrayList<RedeVO> m_lsRede;

    private LsRedeViewAdapter m_adapter;

    public String m_strData = "";

//Private

    private void refreshView() {
        this.m_adapter = new LsRedeViewAdapter(this.getBaseContext(), m_lsRede);

        ListView lstRedeView = (ListView)this.findViewById(R.id.lstMainActivityRede);
        lstRedeView.setAdapter(m_adapter);
        lstRedeView.invalidateViews();
        lstRedeView.scrollBy(0, 0);

        m_adapter.notifyDataSetChanged();

        NumberFormat nf = FormatUtil.newDecimalFormat(2);

        TextView lblRedeView = (TextView)this.findViewById(R.id.lblMainActivityTitulo);
        lblRedeView.setText("Redes Disponiveis (" + m_lsRede.size() + "):");
    }

    /* CHECK_PERMISSIONS */

    private void requestPermissions() {
        requestPermissions(
                new String[] {
                        Manifest.permission.ACCESS_WIFI_STATE,
                        Manifest.permission.CHANGE_WIFI_STATE,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.READ_PHONE_STATE },
                1001);
    }

    private boolean checkPermissions() {
        boolean bCheck =
                (MainActivity.gApp.checkSelfPermission(Manifest.permission.ACCESS_WIFI_STATE) == PackageManager.PERMISSION_GRANTED) &&
                (MainActivity.gApp.checkSelfPermission(Manifest.permission.CHANGE_WIFI_STATE) == PackageManager.PERMISSION_GRANTED) &&
                (MainActivity.gApp.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) &&
                (MainActivity.gApp.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) &&
                (MainActivity.gApp.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED);
        return bCheck;
    }

    /* SHOW SENSORS AND NETWORKKS */

    private void showSensors() {
        System.err.println("SENSORES");
        System.err.println("========");
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> lsSensor = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor o : lsSensor) {
            System.err.println(o.getName());
        }
    }

    private void showWifiNetworks() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if( checkPermissions() ) {
                BroadcastReceiver wifiScanReceiver = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context c, Intent intent) {
                        WifiManager wifiMan = (WifiManager) MainActivity.gApp.getApplicationContext().getSystemService(WIFI_SERVICE);
                        boolean success = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false);

                        if(ActivityCompat.checkSelfPermission(MainActivity.gApp, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }

                        StringBuilder sb = new StringBuilder();
                        if (success) {
                            System.err.println("NETWORKS (success)");
                            System.err.println("==================");

                            MainActivity.gApp.m_lsRede = new ArrayList<RedeVO>();

                            List<ScanResult> lsNet = wifiMan.getScanResults();
                            for (ScanResult o : lsNet) {
                                RedeVO oRede = new RedeVO(MainActivity.gScanNum, o);
                                oRede.debug();

                                if(sb.length() > 0)
                                    sb.append(";");
                                sb.append(oRede.toEncodedUrl());

                                MainActivity.gApp.m_lsRede.add(oRede);
                            }
                            MainActivity.gApp.refreshView();
                        }
                        else {
                            System.err.println("OLD_NETWORKS (failure)");
                            System.err.println("======================");

                            MainActivity.gApp.m_lsRede = new ArrayList<RedeVO>();

                            List<ScanResult> lsNet = wifiMan.getScanResults();
                            for (ScanResult o : lsNet) {
                                RedeVO oRede = new RedeVO(MainActivity.gScanNum, o);
                                oRede.debug();

                                if(sb.length() > 0)
                                    sb.append(";");
                                sb.append(oRede.toEncodedUrl());

                                MainActivity.gApp.m_lsRede.add(oRede);
                            }
                            MainActivity.gApp.refreshView();
                        }
                        MainActivity.gScanNum++;

                        String strUrl = sb.toString();
                        System.err.println("======================");
                        System.err.println(strUrl);
                        System.err.println("======================");

                        try {
                            MainActivity.gApp.sendMessage(strUrl);
                        }
                        catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                };

                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
                registerReceiver(wifiScanReceiver, intentFilter);

                //WifiManager wifiMan = (WifiManager) gApp.getApplicationContext().getSystemService(WIFI_SERVICE);

                //int n = 0;
                //for( ; ; ) {
                //    System.err.println("Starting network scan... " + (++n));
                //    boolean success = wifiMan.startScan();
                //    if( !success ) {
                //        System.err.println("Scan failure! ");
                //    }

                //    try {
                //        Thread.sleep(60000);
                //    }
                //    catch(Exception e) { }
                //}
            }
        }
    }

//Public

    public void init() {
        requestPermissions();

        showSensors();
        showWifiNetworks();
    }

    /* Methodes */

    public static String getDeviceName() {
        WifiManager wifiManager = (WifiManager) MainActivity.gApp.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wInfo = wifiManager.getConnectionInfo();
        String deviceId = wInfo.getMacAddress();
        return deviceId;
    }

    /* Events */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivity.gApp = this;
        init();

        setContentView(R.layout.mainactivity);
    }

    /* Network Communication */
    public void sendMessage(String strData)
        throws Exception
    {
        //this.m_strData = "GET /ServerMon/ArrRede/Add/" + strData + " HTTP/1.1";
        //this.m_strData = "http://192.168.0.15:9090/ServerMon/ArrRede/Add/" + strData;
        this.m_strData = String.format(
            Defs.REQURL_ARRREDE_ADD,
            Defs.PROTO_HTTP,
            Defs.SERVER_IPADDR,
            Integer.toString(Defs.SERVER_PORT),
            strData);

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
            URL url = new URL(MainActivity.gApp.m_strData);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                ArrayList<Byte> lsData = new ArrayList<Byte>();

                BufferedInputStream bis = new BufferedInputStream(urlConnection.getInputStream());
                byte[] buf = new byte[Defs.BUFSIZE];
                int numread = -1;
                while ((numread = bis.read(buf, 0, Defs.BUFSIZE)) != -1) {
                    for (int i = 0; i < numread; i++) {
                        lsData.add(buf[i]);
                    }
                }

                char[] outData = new char[lsData.size()];
                for (int j = 0; j < lsData.size(); j++) {
                    byte ch = (byte) lsData.get(j);
                    outData[j] = (char) ch;
                }

                String strResult = new String(outData);
                System.err.println("Data: " + strResult);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                urlConnection.disconnect();
            }
        }
        catch(Exception e1) {
            e1.printStackTrace();
        }
    }

    /* Getters/Setters */

    public static MainActivity getApp() {
        return MainActivity.gApp;
    }

}
