package com.damy.excelfilereader;

import java.io.File;
import java.io.FileNotFoundException;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class MainActivity extends Activity {
	
	public PeopleData []gPeopleData1;
	public PeopleData []gPeopleData2;
	public PeopleData []gPeopleData3;
	
	private Handler handler = null;
	private ExcelFileReadThread mRequestThread = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		handler= new Handler() {
			public void handleMessage(Message msg){
				if (msg.what == 0)
				{
					startActivity(new Intent(MainActivity.this, SelectActivity.class));
					finish();
				}
				else if (msg.what == 1)
				{
	    			Toast.makeText(MainActivity.this, getString(R.string.file_readerror), Toast.LENGTH_SHORT).show();
	    			MainActivity.this.finish();
				}
				else if (msg.what == 2)
	    			Toast.makeText(MainActivity.this, getString(R.string.file_readerror1), Toast.LENGTH_SHORT).show();
				else if (msg.what == 3)
	    			Toast.makeText(MainActivity.this, getString(R.string.file_readerror2), Toast.LENGTH_SHORT).show();
				else if (msg.what == 4)
	    			Toast.makeText(MainActivity.this, getString(R.string.file_readerror3), Toast.LENGTH_SHORT).show();
			}
		};
		
		mRequestThread = new ExcelFileReadThread();
		mRequestThread.start();
		
		
		return;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override 
	public void onResume()
	{
		super.onResume();
				
		return;
	}
	
	private PeopleData[] readExcelFile(Context context, String fileName, int nKind)
	{
		if ( !isExternalStorageAvailable() || isExternalStorageReadOnly() )
			return null;
		
		PeopleData []data = null;
		
		try {
			File file = new File(Environment.getExternalStorageDirectory(), fileName);
			if (file.exists())
			{
				Workbook w;
				try {
					w = Workbook.getWorkbook(file);
					Sheet sheet = w.getSheet(0);
					
					int nRows = sheet.getRows();
					if (nRows < 2)
					{
						return null;
					}
					
					data = new PeopleData[nRows-1];
					
					for ( int  row = 0; row < sheet.getRows()-1; row++ )
					{						
						data[row] = new PeopleData();						
						for ( int col = 0; col < GlobalData.EXCEL_COLS; col++ )
						{
							/////////////////////////////////////////////
							//
							try {
								Cell cell = sheet.getCell(col, row+1);
								String strData = cell.getContents().toString();
								data[row].mPeopleData[col] = strData;
								if (col == 2)
								{
									switch(nKind)
									{
									case 0:
										data[row].mPeopleData[col] = getString(R.string.Manual_ManagerTitle_1);
										break;
									case 1:
										data[row].mPeopleData[col] = getString(R.string.Manual_ManagerTitle_2);
										break;
									case 2:
										data[row].mPeopleData[col] = getString(R.string.Manual_ManagerTitle_3);
										break;
									}
								}
							} catch (Exception e) {
								data[row].mPeopleData[col] = "";
							}
							//
							/////////////////////////////////////////////
						}
					}
				} catch(FileNotFoundException e){
					return null;
				}catch (BiffException e) {
					return null;
				} catch (Exception e) {
					return null;
				}
			}			
		} catch (Exception e){
			return null;
		}
		
		return data;
	}
	
	public static boolean isExternalStorageReadOnly()
	{
		String extStorageState = Environment.getExternalStorageState();
		if ( Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState) )
			return true;
		
		return false;
	}
	
	public static boolean isExternalStorageAvailable()
	{
		String extStorageState = Environment.getExternalStorageState();
		if ( Environment.MEDIA_MOUNTED.equals(extStorageState) )
			return true;
		
		return false;
	}
	
	class ExcelFileReadThread extends Thread implements Runnable 
	{        
        public ExcelFileReadThread() 
        {
        }
         
		@Override
        public void run() 
        {
            super.run();   

    		GlobalData.setCellTitles(MainActivity.this);
    		GlobalData.setCellWidth(MainActivity.this);
    		GlobalData.init();
    		
    		gPeopleData1 = readExcelFile(MainActivity.this, "Download/Data1.xls", 0);
    		gPeopleData2 = readExcelFile(MainActivity.this, "Download/Data2.xls", 1);
    		gPeopleData3 = readExcelFile(MainActivity.this, "Download/Data3.xls", 2);
    		
    		int nRows = 0;
    		if ( gPeopleData1 == null && gPeopleData2 == null && gPeopleData3 == null )
    		{
    			handler.sendEmptyMessage(1);
    			return;
    		}
    		
    		if ( gPeopleData1 != null )
    			nRows += gPeopleData1.length;
    		else
    			handler.sendEmptyMessage(2);
    		if ( gPeopleData2 != null )
    			nRows += gPeopleData2.length;
    		else
    			handler.sendEmptyMessage(3);
    		if ( gPeopleData3 != null )
    			nRows += gPeopleData3.length;
    		else
    			handler.sendEmptyMessage(4);
    		
    		GlobalData.gPeopleData = new PeopleData[nRows];		
    		int nPos = 0;
    		if (gPeopleData1 != null)
    		{
    			for ( int i = 0; i < gPeopleData1.length; i++)
    			{
    				GlobalData.gPeopleData[nPos] = new PeopleData();
    				GlobalData.gPeopleData[nPos] = gPeopleData1[i];
    				nPos++;
    			}
    		}
    		
    		if (gPeopleData2 != null)
    		{
    			for ( int i = 0; i < gPeopleData2.length; i++)
    			{
    				GlobalData.gPeopleData[nPos] = new PeopleData();
    				GlobalData.gPeopleData[nPos] = gPeopleData2[i];
    				nPos++;
    			}
    		}
    		
    		if (gPeopleData3 != null)
    		{
    			for ( int i = 0; i < gPeopleData3.length; i++)
    			{
    				GlobalData.gPeopleData[nPos] = new PeopleData();
    				GlobalData.gPeopleData[nPos] = gPeopleData3[i];
    				nPos++;
    			}
    		}
    		
    		handler.sendEmptyMessage(0);
        }
    }
}
