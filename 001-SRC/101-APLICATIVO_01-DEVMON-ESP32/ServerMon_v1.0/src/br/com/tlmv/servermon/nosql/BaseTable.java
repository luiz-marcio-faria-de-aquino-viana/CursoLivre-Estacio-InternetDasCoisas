/*
 * Copyright(C) 2023 TLMV Consultoria e Sistemas EIRELI - CNPJ: 03.999.590/0001-04. Todos os direitos reservados.
 *
 * BaseTable.java
 * Autor: 
 * - Luiz Marcio Faria de Aquino Viana, Pós-D.Sc. - Engenheiro, 28/01/2023
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

package br.com.tlmv.servermon.nosql;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;

import br.com.tlmv.servermon.AppContext;
import br.com.tlmv.servermon.AppDefs;
import br.com.tlmv.servermon.AppMain;
import br.com.tlmv.servermon.data.Evento;
import br.com.tlmv.servermon.data.ObjetoBase;
import br.com.tlmv.servermon.data.Regra;
import br.com.tlmv.servermon.utils.FileUtil;
import br.com.tlmv.servermon.utils.UuidUtil;

public class BaseTable
{
//Private
	private Integer tableObjectType = AppDefs.BASE_OBJTYPE_NONE;

	private String tableName = "";
	private String tableFileName = "";
	private String initFileName = "";
	private Hashtable<String,ObjetoBase> tableData = new Hashtable<String,ObjetoBase>();
	private ArrayList<String> initTableData = new ArrayList<String>(); 
	
//Public
	
	public BaseTable(Integer tableObjectType, String tableName) {
		AppMain app = AppMain.getApp();
		AppContext ctx = app.getContext();
		
		this.tableObjectType = tableObjectType;
		this.tableName = tableName;
		this.tableFileName = ctx.getDataDir() + "/" + tableName + AppDefs.EXT_DAT;
		this.initFileName = ctx.getDataInitDir() + "/" + tableName + AppDefs.EXT_CSV;
		this.tableData = new Hashtable<String,ObjetoBase>();
		this.initTableData = new ArrayList<String>(); 
	}
	
	/* Methodes - SAVE/LOAD */
	
	public synchronized void createDataBackup() {
		AppContext ctx = AppMain.getApp().getContext();
		
		String uuid = UuidUtil.generateUUID();
		
		File fsrc = new File(this.tableFileName);
		if( fsrc.exists() ) {
			String newTableFileName = ctx.getDataDir() + "/" + tableName + uuid + AppDefs.EXT_DAT;
			
			File fdst = new File(newTableFileName); 
			if( !fdst.exists() )
				fsrc.renameTo(fdst);
		}		
	}
	
	public synchronized void saveData() 
		throws Exception
	{
		Collection<ObjetoBase> lsObj = this.tableData.values();
		
		FileOutputStream fos = null;
		ObjectOutputStream os = null;
		try {
			this.createDataBackup();
				
			fos = new FileOutputStream(this.tableFileName);
			os = new ObjectOutputStream(fos);
			
			for(ObjetoBase obj : lsObj) {
				os.writeObject(obj);
			}
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "saveData()", e.getMessage());
			e.printStackTrace();
		}
		finally {
			if(os != null) os.close();
			if(fos != null)	fos.close();
		}
	}

	public synchronized void clear() {
		this.tableData = new Hashtable<String,ObjetoBase>();
	}

	public synchronized void loadData() 
		throws Exception
	{
		this.tableData = new Hashtable<String,ObjetoBase>();
		
		FileInputStream fis = null;
		ObjectInputStream is = null;
		try {
			File f = new File(this.tableFileName);
			if( f.exists() ) {
				fis = new FileInputStream(f);
				is = new ObjectInputStream(fis);
				
				ObjetoBase obj = null;
				boolean bEof = false;
				while( !bEof ) {
					try {
						obj = (ObjetoBase)is.readObject();
						this.putObj(obj);
					}
					catch(Exception e) {
						obj = null;
						bEof = true;
					}
				}
			}
		}
		catch(Exception e) {
			AppMain.getApp().getErr().writeError(this.getClass().getName(), "loadData()", e.getMessage());
			e.printStackTrace();
		}
		finally {
			if(is != null) is.close();
			if(fis != null)	fis.close();
		}
	}
	
	public synchronized void loadInitData() 
		throws Exception
	{
		this.initTableData = FileUtil.readDataToArrayList(this.initFileName);
		this.initTableData.remove(0);	// remove header
		
		for(String strData : this.initTableData) {
			if(this.tableObjectType == AppDefs.BASE_OBJTYPE_EVENTO) {
				((EventoTable)this).putObjFromStr(strData);
			}
			else if(this.tableObjectType == AppDefs.BASE_OBJTYPE_REGRA) {
				((RegraTable)this).putObjFromStr(strData);
			}
			else {
				this.putObjFromStr(strData);
			}
		}
	}
		
	/* Methodes - EXIST/PUT/GET */
	
	public boolean existObj(String key) {
		if( this.tableData.containsKey(key) )
			return true;
		return false;
	}

	public ObjetoBase getObj(String key) {
		ObjetoBase oResult = null;
		if( this.existObj(key) ) {
			if(this.tableObjectType == AppDefs.BASE_OBJTYPE_EVENTO) {
				oResult = (Evento)this.tableData.get(key);
			}
			else if(this.tableObjectType == AppDefs.BASE_OBJTYPE_REGRA) {
				oResult = (Regra)this.tableData.get(key);
			}
			else {
				oResult = this.tableData.get(key);
			}					
		}
		return oResult;
	}

	public void putObj(ObjetoBase obj) {
		if(this.tableObjectType == AppDefs.BASE_OBJTYPE_EVENTO) {
			String strId = Integer.toString( ((Evento)obj).getId() );
			this.tableData.put(strId, (Evento)obj);
		}
		else if(this.tableObjectType == AppDefs.BASE_OBJTYPE_REGRA) {
			String strId = Integer.toString( ((Regra)obj).getId() );
			this.tableData.put(strId, (Regra)obj);
		}
		else {
			this.tableData.put(obj.getBaseObjectId(), obj);
		}		
	}
	
	public String putObjFromStr(String str) {
		String strResult = "-1";
		
		if(this.tableObjectType == AppDefs.BASE_OBJTYPE_EVENTO) {
			strResult = ((EventoTable)this).putObjFromStr(str);
		}
		else if(this.tableObjectType == AppDefs.BASE_OBJTYPE_REGRA) {
			strResult = ((RegraTable)this).putObjFromStr(str);
		}
		else {
			strResult = this.putObjFromStr(str);
		}		
		
		return strResult;
	}
	
	/* DEBUG */
	
	public void debugAll(int debugLevel) {
		if( !AppMain.getApp().getErr().checkDebugLevel(debugLevel) ) return;
		
		Collection<ObjetoBase> lsObj = this.tableData.values();
		for(ObjetoBase obj : lsObj) {
			if(this.tableObjectType == AppDefs.BASE_OBJTYPE_EVENTO) {
				((ObjetoBase)obj).debug(debugLevel);
			}
			else if(this.tableObjectType == AppDefs.BASE_OBJTYPE_REGRA) {
				((ObjetoBase)obj).debug(debugLevel);
			}
			else {
				obj.debug(debugLevel);
			}
		}
	}
	
	/* Getters/Setters */
	
	public Integer getTableObjectType() {
		return tableObjectType;
	}

	public void setTableObjectType(Integer tableObjectType) {
		this.tableObjectType = tableObjectType;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableFileName() {
		return tableFileName;
	}

	public void setTableFileName(String tableFileName) {
		this.tableFileName = tableFileName;
	}

	public Hashtable<String, ObjetoBase> getTableData() {
		return tableData;
	}

	public void setTableData(Hashtable<String, ObjetoBase> tableData) {
		this.tableData = tableData;
	}
	
	public int getTableSize() {
		return this.tableData.size();
	}

	public String getInitFileName() {
		return initFileName;
	}

	public void setInitFileName(String initFileName) {
		this.initFileName = initFileName;
	}

	public ArrayList<String> getInitTableData() {
		return initTableData;
	}

	public void setInitTableData(ArrayList<String> initTableData) {
		this.initTableData = initTableData;
	}	
	
}
