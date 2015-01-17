package com.damy.excelfilereader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.damy.excelfilereader.MultiSpinner.MultiSpinnerListener;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;

@SuppressLint("SimpleDateFormat")
public class SelectActivity extends Activity implements MultiSpinnerListener{
	private static final int VIEW_COUNT = 25;
	private static final int REQUEST_DETAIL = 0;
	
	private Spinner spinKind = null;
	private MultiSpinner spinDetail = null;
	private RelativeLayout rlFind = null;
	private Button btnDelete = null;
	private Button btnAdvancedFind = null;
	private EditText txtName;
	private EditText txtUnit;
	private ListView listDatas;
	private TextView []m_lblViewItems = null;
	private DataItemAdapter mAdapter;
	private boolean []m_bSelectFlag;
	
	private ArrayList<PeopleData> arrPeopleData = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select);	
		/*
		Date curDate = new Date();
		Date limitTime = new Date(113, 10, 17);
		long nDiff = limitTime.getTime() - curDate.getTime();
		if (nDiff < 0 )
		{
			Toast.makeText(SelectActivity.this, getString(R.string.date_error), Toast.LENGTH_LONG).show();
			SelectActivity.this.finish();
		}
		*/
		m_bSelectFlag = new boolean[VIEW_COUNT];
		m_lblViewItems = new TextView[VIEW_COUNT];
		SharedPreferences pref = getSharedPreferences(GlobalData.g_SaveFile, MODE_PRIVATE);
		for ( int i = 0; i < VIEW_COUNT; i++)
		{
			String strData = "";
			strData = GlobalData.g_SaveCondition + Integer.toString(i);
			m_bSelectFlag[i] = pref.getBoolean(strData, true);
		}
		
		txtName = (EditText) findViewById(R.id.txtSelect_Name);
		txtUnit = (EditText) findViewById(R.id.txtSelect_Unit);
		m_lblViewItems[0] = (TextView) findViewById(R.id.lblSelect_Name);
		m_lblViewItems[1] = (TextView) findViewById(R.id.lblSelect_Property);
		m_lblViewItems[2] = (TextView) findViewById(R.id.lblSelect_Organ);
		m_lblViewItems[3] = (TextView) findViewById(R.id.lblSelect_Job);
		m_lblViewItems[4] = (TextView) findViewById(R.id.lblSelect_JobLevel);
		m_lblViewItems[5] = (TextView) findViewById(R.id.lblSelect_Birthday);
		m_lblViewItems[6] = (TextView) findViewById(R.id.lblSelect_Gender);
		m_lblViewItems[7] = (TextView) findViewById(R.id.lblSelect_Nation);
		m_lblViewItems[8] = (TextView) findViewById(R.id.lblSelect_OriginDomicile);
		m_lblViewItems[9] = (TextView) findViewById(R.id.lblSelect_Homeland);
		m_lblViewItems[10] = (TextView) findViewById(R.id.lblSelect_WorkDate);
		m_lblViewItems[11] = (TextView) findViewById(R.id.lblSelect_PartyDate);
		m_lblViewItems[12] = (TextView) findViewById(R.id.lblSelect_StudyHistory);
		m_lblViewItems[13] = (TextView) findViewById(R.id.lblSelect_GraduateSchool);
		m_lblViewItems[14] = (TextView) findViewById(R.id.lblSelect_CurrentJobDate);
		m_lblViewItems[15] = (TextView) findViewById(R.id.lblSelect_CurrentJobLevelDate);
		m_lblViewItems[16] = (TextView) findViewById(R.id.lblSelect_JobName);
		m_lblViewItems[17] = (TextView) findViewById(R.id.lblSelect_ManagerKind);
		m_lblViewItems[18] = (TextView) findViewById(R.id.lblSelect_Note1);
		m_lblViewItems[19] = (TextView) findViewById(R.id.lblSelect_Note2);
		m_lblViewItems[20] = (TextView) findViewById(R.id.lblSelect_Note3);
		m_lblViewItems[21] = (TextView) findViewById(R.id.lblSelect_Note4);
		m_lblViewItems[22] = (TextView) findViewById(R.id.lblSelect_Note5);
		m_lblViewItems[23] = (TextView) findViewById(R.id.lblSelect_FullTimeSystem);
		m_lblViewItems[24] = (TextView) findViewById(R.id.lblSelect_Degree);
		
		for (int i = 0; i < VIEW_COUNT; i++)
		{
			if (m_bSelectFlag[i] == false)
				m_lblViewItems[i].setVisibility(View.GONE);
		}
		
		spinKind = (Spinner) findViewById(R.id.spinSelect_Kind);
		ArrayAdapter<CharSequence> adapterCarPool = ArrayAdapter.createFromResource(SelectActivity.this, R.array.ARRSelect_Kind, R.layout.spinner_edit);
		adapterCarPool.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinKind.setAdapter(adapterCarPool);
				
		spinDetail = (MultiSpinner) findViewById(R.id.spinSelect_Detail);		
		List<String> list = new ArrayList<String>();
		String strData[] = getResources().getStringArray(R.array.ARRView_Kind);
		for ( int i = 0; i < strData.length; i++ )
			list.add(strData[i]);		
		spinDetail.setItems(list, getString(R.string.empty_string), this);
				
		rlFind = (RelativeLayout) findViewById(R.id.rlSelect_Find);
		rlFind.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v)
			{				
				getValidConditionForData();
				
				mAdapter.clear();
				updateAddressList();
				
				return;
			}
		});
		
		btnAdvancedFind = (Button) findViewById(R.id.btnSelect_AdvancedFind);
		btnAdvancedFind.setOnClickListener( new OnClickListener() {
			@Override
			public void onClick(View v)
			{
				GlobalData.init();
				
				Intent intent = new Intent(SelectActivity.this, AdvancedFindActivity.class);
				startActivityForResult(intent, REQUEST_DETAIL);
				return;
			}
		});
		
		btnDelete = (Button) findViewById(R.id.btnSelect_Delete);
		btnDelete.setOnClickListener( new OnClickListener() {
			@Override
			public void onClick(View v)
			{
				txtName.setText("");
				txtUnit.setText("");
				spinKind.setSelection(0);				
				mAdapter.clear();
				GlobalData.init();
				updateAddressList();
				
				return;
			}
		});
		
		listDatas = (ListView) findViewById(R.id.listSelect_Datas);
		listDatas.setDividerHeight(0);
		listDatas.setOnItemClickListener( new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				if (arrPeopleData != null && arrPeopleData.size() > 0)
				{
					Toast.makeText(SelectActivity.this, Integer.toString(arg2+1), Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(SelectActivity.this, DetailViewActivity.class);
					intent.putExtra("Data", arrPeopleData.get(arg2).mPeopleData);
					startActivity(intent);
					return;
				}
				return;
			}
		});
		
		initUpdateAddressList();
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		return;
	}
	
	private void updateAddressList()
	{
		if ( arrPeopleData != null && arrPeopleData.size() > 0 )
		{	
			mAdapter = new DataItemAdapter(SelectActivity.this, 0, arrPeopleData);
			listDatas.setAdapter(mAdapter);
		}
	}
	
	private void initUpdateAddressList()
	{
		arrPeopleData = new ArrayList<PeopleData>();
		for (int i = 0; i < GlobalData.gPeopleData.length; i++)
		{
			if (GlobalData.gPeopleData[i].mPeopleData[1].contains(getString(R.string.fildexception_1)))
			{
				continue;
			}
			arrPeopleData.add(GlobalData.gPeopleData[i]);
		}
		mAdapter = new DataItemAdapter(SelectActivity.this, 0, arrPeopleData);
		listDatas.setAdapter(mAdapter);
	}
	
	class DataItemAdapter extends ArrayAdapter<PeopleData> {
		ArrayList<PeopleData> list;
		Context ctx;
		View vListItem;

		public DataItemAdapter(Context ctx, int resourceId, ArrayList<PeopleData> list) {
			super(ctx, resourceId, list);
			this.ctx = ctx;
			this.list = list;
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {			
			
			View view = convertView;
			if (view == null)
			{
				LayoutInflater inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = inflater.inflate(R.layout.finditems, null);
			}
			
			RelativeLayout rl = (RelativeLayout) view.findViewById(R.id.llFindItemsLayout);
			if (position % 2 == 0)
			{
				rl.setBackgroundColor(Color.parseColor("#C0E0F0"));
			}
			else
			{
				rl.setBackgroundColor(Color.parseColor("#FFFFFF"));
			}
			
			TextView txtNo = (TextView)view.findViewById(R.id.lblFindItems_No);			
			txtNo.setText(Integer.toString(position+1));
			
			TextView txtName = (TextView)view.findViewById(R.id.lblFindItems_Name);			
			txtName.setText(list.get(position).mPeopleData[9]);	
			if ( m_bSelectFlag[0] == true )
				txtName.setVisibility(View.VISIBLE);
			else
				txtName.setVisibility(View.GONE);
			TextView txtProperty = (TextView)view.findViewById(R.id.lblFindItems_Property);
			txtProperty.setText(list.get(position).mPeopleData[1]);
			if ( m_bSelectFlag[1] == true )
				txtProperty.setVisibility(View.VISIBLE);
			else
				txtProperty.setVisibility(View.GONE);
			TextView txtOrigan = (TextView)view.findViewById(R.id.lblFindItems_Origan);
			txtOrigan.setText(list.get(position).mPeopleData[8]);
			if ( m_bSelectFlag[2] == true )
				txtOrigan.setVisibility(View.VISIBLE);
			else
				txtOrigan.setVisibility(View.GONE);
			TextView txtJob = (TextView)view.findViewById(R.id.lblFindItems_Job);
			txtJob.setText(list.get(position).mPeopleData[10]);
			if ( m_bSelectFlag[3] == true )
				txtJob.setVisibility(View.VISIBLE);
			else
				txtJob.setVisibility(View.GONE);
			TextView txtJobLevel = (TextView)view.findViewById(R.id.lblFindItems_JobLevel);
			txtJobLevel.setText(list.get(position).mPeopleData[15]);
			if ( m_bSelectFlag[4] == true )
				txtJobLevel.setVisibility(View.VISIBLE);
			else
				txtJobLevel.setVisibility(View.GONE);		
			TextView txtBirthday = (TextView)view.findViewById(R.id.lblFindItems_Birthday);
			txtBirthday.setText(list.get(position).mPeopleData[18]);
			if ( m_bSelectFlag[5] == true )
				txtBirthday.setVisibility(View.VISIBLE);
			else
				txtBirthday.setVisibility(View.GONE);		
			TextView txtGender = (TextView)view.findViewById(R.id.lblFindItems_Gender);
			txtGender.setText(list.get(position).mPeopleData[16]);
			if ( m_bSelectFlag[6] == true )
				txtGender.setVisibility(View.VISIBLE);
			else
				txtGender.setVisibility(View.GONE);
			TextView txtNation = (TextView)view.findViewById(R.id.lblFindItems_Nation);
			txtNation.setText(list.get(position).mPeopleData[17]);
			if ( m_bSelectFlag[7] == true )
				txtNation.setVisibility(View.VISIBLE);
			else
				txtNation.setVisibility(View.GONE);
			TextView txtOriginDomicile = (TextView)view.findViewById(R.id.lblFindItems_OriginDomicile);
			txtOriginDomicile.setText(list.get(position).mPeopleData[20]);
			if ( m_bSelectFlag[8] == true )
				txtOriginDomicile.setVisibility(View.VISIBLE);
			else
				txtOriginDomicile.setVisibility(View.GONE);
			TextView txtHomeland = (TextView)view.findViewById(R.id.lblFindItems_Homeland);
			txtHomeland.setText(list.get(position).mPeopleData[21]);
			if ( m_bSelectFlag[9] == true )
				txtHomeland.setVisibility(View.VISIBLE);
			else
				txtHomeland.setVisibility(View.GONE);
			TextView txtWorkDate = (TextView)view.findViewById(R.id.lblFindItems_WorkDate);
			txtWorkDate.setText(list.get(position).mPeopleData[22]);
			if ( m_bSelectFlag[10] == true )
				txtWorkDate.setVisibility(View.VISIBLE);
			else
				txtWorkDate.setVisibility(View.GONE);
			TextView txtPartyDate = (TextView)view.findViewById(R.id.lblFindItems_PartyDate);
			txtPartyDate.setText(list.get(position).mPeopleData[23]);
			if ( m_bSelectFlag[11] == true )
				txtPartyDate.setVisibility(View.VISIBLE);
			else
				txtPartyDate.setVisibility(View.GONE);
			TextView txtStudyHistory = (TextView)view.findViewById(R.id.lblFindItems_StudyHistory);
			txtStudyHistory.setText(list.get(position).mPeopleData[24]);
			if ( m_bSelectFlag[12] == true )
				txtStudyHistory.setVisibility(View.VISIBLE);
			else
				txtStudyHistory.setVisibility(View.GONE);
			TextView txtGraduateSchool = (TextView)view.findViewById(R.id.lblFindItems_GraduateSchool);
			txtGraduateSchool.setText(list.get(position).mPeopleData[25]);
			if ( m_bSelectFlag[13] == true )
				txtGraduateSchool.setVisibility(View.VISIBLE);
			else
				txtGraduateSchool.setVisibility(View.GONE);
			TextView txtCurrentJobdate = (TextView)view.findViewById(R.id.lblFindItems_CurrentJobDate);
			txtCurrentJobdate.setText(list.get(position).mPeopleData[26]);
			if ( m_bSelectFlag[14] == true )
				txtCurrentJobdate.setVisibility(View.VISIBLE);
			else
				txtCurrentJobdate.setVisibility(View.GONE);
			TextView txtCurrentJobLevel = (TextView)view.findViewById(R.id.lblFindItems_CurrentJobLevelDate);
			txtCurrentJobLevel.setText(list.get(position).mPeopleData[27]);
			if ( m_bSelectFlag[15] == true )
				txtCurrentJobLevel.setVisibility(View.VISIBLE);
			else
				txtCurrentJobLevel.setVisibility(View.GONE);
			TextView txtJobName = (TextView)view.findViewById(R.id.lblFindItems_JobName);
			txtJobName.setText(list.get(position).mPeopleData[28]);
			if ( m_bSelectFlag[16] == true )
				txtJobName.setVisibility(View.VISIBLE);
			else
				txtJobName.setVisibility(View.GONE);		
			TextView txtManagerKind = (TextView)view.findViewById(R.id.lblFindItems_ManagerKind);
			txtManagerKind.setText(list.get(position).mPeopleData[2]);
			if ( m_bSelectFlag[17] == true )
				txtManagerKind.setVisibility(View.VISIBLE);
			else
				txtManagerKind.setVisibility(View.GONE);
			TextView txtNote1 = (TextView)view.findViewById(R.id.lblFindItems_Note1);			
			txtNote1.setText(list.get(position).mPeopleData[3]);
			if ( m_bSelectFlag[18] == true )
				txtNote1.setVisibility(View.VISIBLE);
			else
				txtNote1.setVisibility(View.GONE);
			TextView txtNote2 = (TextView)view.findViewById(R.id.lblFindItems_Note2);
			txtNote2.setText(list.get(position).mPeopleData[4]);
			if ( m_bSelectFlag[19] == true )
				txtNote2.setVisibility(View.VISIBLE);
			else
				txtNote2.setVisibility(View.GONE);
			TextView txtNote3 = (TextView)view.findViewById(R.id.lblFindItems_Note3);
			txtNote3.setText(list.get(position).mPeopleData[5]);
			if ( m_bSelectFlag[20] == true )
				txtNote3.setVisibility(View.VISIBLE);
			else
				txtNote3.setVisibility(View.GONE);
			TextView txtNote4 = (TextView)view.findViewById(R.id.lblFindItems_Note4);
			txtNote4.setText(list.get(position).mPeopleData[6]);
			if ( m_bSelectFlag[21] == true )
				txtNote4.setVisibility(View.VISIBLE);
			else
				txtNote4.setVisibility(View.GONE);
			TextView txtNote5 = (TextView)view.findViewById(R.id.lblFindItems_Note5);
			txtNote5.setText(list.get(position).mPeopleData[7]);
			if ( m_bSelectFlag[22] == true )
				txtNote5.setVisibility(View.VISIBLE);
			else
				txtNote5.setVisibility(View.GONE);
			TextView txtFullTimeSystem = (TextView)view.findViewById(R.id.lblFindItems_FullTimeSystem);
			txtFullTimeSystem.setText(list.get(position).mPeopleData[31]);
			if ( m_bSelectFlag[23] == true )
				txtFullTimeSystem.setVisibility(View.VISIBLE);
			else
				txtFullTimeSystem.setVisibility(View.GONE);
			TextView txtDegree = (TextView)view.findViewById(R.id.lblFindItems_Degree);
			txtDegree.setText(list.get(position).mPeopleData[37]);
			if ( m_bSelectFlag[24] == true )
				txtDegree.setVisibility(View.VISIBLE);
			else
				txtDegree.setVisibility(View.GONE);
					
			return view;
		}
	}
	
	private void updateUI()
	{
		for ( int i = 0; i < VIEW_COUNT; i++ )
		{
			if ( m_bSelectFlag[i] == false )
				m_lblViewItems[i].setVisibility(View.GONE);
			else
				m_lblViewItems[i].setVisibility(View.VISIBLE);
		}
		
		return;
	}	

	@Override
	public void onItemsSelected(boolean[] selected) {
		
		int nLen = selected.length;
		if (nLen != VIEW_COUNT)
			return;
		m_bSelectFlag = selected;
		
		updateUI();
		
		return;
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent)
	{
		super.onActivityResult(requestCode, resultCode, intent);
		
		switch(requestCode)
		{
		case REQUEST_DETAIL:
			if (resultCode == RESULT_OK)
			{				
				getValidConditionForData();
				
				mAdapter.clear();
				updateAddressList();;
			}
			break;
		}
		
		return;
	}
	
	private void getValidConditionForData()
	{
		String strProperty = "";
		String strName = "";
		String strUnit = "";
		long nPropertyPos = spinKind.getSelectedItemId();
		
		strProperty = spinKind.getSelectedItem().toString();				
		strName = txtName.getText().toString();
		strUnit = txtUnit.getText().toString();
		
		arrPeopleData = new ArrayList<PeopleData>();
		
		for ( int i = 0; i < GlobalData.gPeopleData.length; i++ )
		{			
			if ( nPropertyPos == 0 )
			{
				int nLen = strName.length();
				if ( nLen != 0 )
				{
					if ( GlobalData.gPeopleData[i].mPeopleData[9].contains(strName) == false )
					{
						continue;
					}
				}
				
				nLen = txtUnit.length();
				if ( nLen != 0 )
				{
					String strData = GlobalData.gPeopleData[i].mPeopleData[8] + " " + GlobalData.gPeopleData[i].mPeopleData[10];
					if ( strData.contains(strUnit) == false )
					{
						continue;
					}
				}
			}
			else
			{
				int nLen = strProperty.length();
				if ( nLen != 0 )
				{
					if ( GlobalData.gPeopleData[i].mPeopleData[1].contains(strProperty) == false )
					{
						continue;
					}
					else 
					{
						int len1 = strName.length();
						if ( len1 != 0 )
						{
							if ( GlobalData.gPeopleData[i].mPeopleData[9].contains(strName) == false )
							{
								continue;
							}
						}
						
						len1 = txtUnit.length();
						if ( len1 != 0 )
						{
							String strData = GlobalData.gPeopleData[i].mPeopleData[8] + " " + GlobalData.gPeopleData[i].mPeopleData[10];
							if ( strData.contains(strUnit) == false )
							{
								continue;
							}
						}
					}
				}
			}
			
			if (GlobalData.gPeopleData[i].mPeopleData[1].contains(getString(R.string.fildexception_1)))
			{
				continue;
			}
			
			boolean bRet = false;
			bRet = isValidConditionForManagerKind(GlobalData.gPeopleData[i].mPeopleData[2]);
			if ( bRet == false )
			{
				continue;
			}
			
			bRet = isValidConditionForJobLevel(GlobalData.gPeopleData[i].mPeopleData[15]);
			if ( bRet == false )
			{
				continue;
			}
			
			bRet = isValidConditionForHomeland(GlobalData.gPeopleData[i].mPeopleData[10]);
			if ( bRet == false )
			{
				continue;
			}
				
			bRet = isValidConditionForBirthday(GlobalData.gPeopleData[i].mPeopleData[18]);
			if ( bRet == false )
			{
				continue;
			}		
			
			bRet = isValidConditionForParty(GlobalData.gPeopleData[i].mPeopleData[23]);
			if ( bRet == false )
				continue;
			
			bRet = isValidConditionForGender(GlobalData.gPeopleData[i].mPeopleData[16]);
			if ( bRet == false )
			{
				continue;
			}
			
			bRet = isValidConditionForNation(GlobalData.gPeopleData[i].mPeopleData[17]);
			if ( bRet == false )				
			{
				continue;
			}
			
			bRet = isValidConditionForFullTimeSystem(GlobalData.gPeopleData[i].mPeopleData[31]);
			if ( bRet == false )
			{
				continue;
			}
			
			bRet = isValidConditionForStudyHistory(GlobalData.gPeopleData[i].mPeopleData[24]);
			if ( bRet == false )
				continue;
			
			bRet = isValidConditionForDegree(GlobalData.gPeopleData[i].mPeopleData[37]);
			if ( bRet == false )
			{
				continue;
			}
			
			arrPeopleData.add(GlobalData.gPeopleData[i]);
		}
	}
	
	private boolean isValidConditionForManagerKind(String strData)
	{		
		if ( GlobalData.g_bManagerKind[0] == true && GlobalData.g_bManagerKind[1] == true
				  && GlobalData.g_bManagerKind[2] == true )
			return true;
		if ( GlobalData.g_bManagerKind[0] == false && GlobalData.g_bManagerKind[1] == false
				  && GlobalData.g_bManagerKind[2] == false )
			return true;
				
		if (GlobalData.g_bManagerKind[0] == false)
		{
			if (strData.contains( getString(R.string.AdvancedFind_ManagerTitle_1)) == true )
				return false;
		}
		else
		{
			if (strData.contains( getString(R.string.AdvancedFind_ManagerTitle_1)) == true )
				return true;
		}
		
		if (GlobalData.g_bManagerKind[1] == false)
		{
			if (strData.contains( getString(R.string.AdvancedFind_ManagerTitle_2)) == true )
				return false;
		}
		else
		{
			if (strData.contains( getString(R.string.AdvancedFind_ManagerTitle_2)) == true )
				return true;
		}

		if (GlobalData.g_bManagerKind[2] == false)
		{
			if (strData.contains( getString(R.string.AdvancedFind_ManagerTitle_3)) == true )
				return false;
		}
		else
		{
			if (strData.contains( getString(R.string.AdvancedFind_ManagerTitle_3)) == true )
				return true;
		}

		return true;
	}
	
	private boolean isValidConditionForJobLevel(String strData)
	{
		if ( GlobalData.g_bJobLevel[0] == true && GlobalData.g_bJobLevel[1] == true
		  && GlobalData.g_bJobLevel[2] == true && GlobalData.g_bJobLevel[3] == true
		  && GlobalData.g_bJobLevel[4] == true && GlobalData.g_bJobLevel[5] == true
		  && GlobalData.g_bJobLevel[6] == true && GlobalData.g_bJobLevel[7] == true)
			return true;
		if ( GlobalData.g_bJobLevel[0] == false && GlobalData.g_bJobLevel[1] == false
		  && GlobalData.g_bJobLevel[2] == false && GlobalData.g_bJobLevel[3] == false
		  && GlobalData.g_bJobLevel[4] == false && GlobalData.g_bJobLevel[5] == false
		  && GlobalData.g_bJobLevel[6] == false && GlobalData.g_bJobLevel[7] == false)
			return true;
		
		if (GlobalData.g_bJobLevel[0] == false)
		{
			if (strData.contains( getString(R.string.AdvancedFind_JobTitle_1)) == true )
				return false;
		}
		else
		{
			if (strData.contains( getString(R.string.AdvancedFind_JobTitle_1)) == true )
				return true;
		}
		
		if (GlobalData.g_bJobLevel[1] == false)
		{
			if (strData.contains( getString(R.string.AdvancedFind_JobTitle_2)) == true )
				return false;
		}
		else
		{
			if (strData.contains( getString(R.string.AdvancedFind_JobTitle_2)) == true )
				return true;
		}
		
		if (GlobalData.g_bJobLevel[2] == false)
		{
			if (strData.contains( getString(R.string.AdvancedFind_JobTitle_3)) == true )
				return false;
		}
		else
		{
			if (strData.contains( getString(R.string.AdvancedFind_JobTitle_3)) == true )
				return true;
		}
		
		if (GlobalData.g_bJobLevel[3] == false)
		{
			if (getString(R.string.AdvancedFind_JobTitle_4).contains( strData ) == true )
				return false;
		}
		else
		{
			if (getString(R.string.AdvancedFind_JobTitle_4).contains( strData ) == true )
				return true;
		}
		
		if (GlobalData.g_bJobLevel[4] == false)
		{
			if (strData.contains( getString(R.string.AdvancedFind_JobTitle_5)) == true )
				return false;
		}
		else
		{
			if (strData.contains( getString(R.string.AdvancedFind_JobTitle_5)) == true )
				return true;
		}
		
		if (GlobalData.g_bJobLevel[5] == false)
		{
			if (getString(R.string.AdvancedFind_JobTitle_6).contains( strData ) == true )
				return false;
		}
		else
		{
			if (getString(R.string.AdvancedFind_JobTitle_6).contains( strData ) == true )
				return true;
		}
		
		if (GlobalData.g_bJobLevel[6] == false)
		{
			if (strData.contains( getString(R.string.AdvancedFind_JobTitle_7)) == true )
				return false;
		}
		else
		{
			if (strData.contains( getString(R.string.AdvancedFind_JobTitle_7)) == true )
				return true;
		}
		
		if (GlobalData.g_bJobLevel[7] == false)
		{
			if (strData.contains( getString(R.string.AdvancedFind_JobTitle_8)) == true )
				return false;
		}
		else
		{
			if (strData.contains( getString(R.string.AdvancedFind_JobTitle_8)) == true )
				return true;
		}
		
		return true;
	}
	
	private boolean isValidConditionForHomeland(String strData)
	{
		if ( GlobalData.g_bHomeland[0] == true && GlobalData.g_bHomeland[1] == true
		  && GlobalData.g_bHomeland[2] == true && GlobalData.g_bHomeland[3] == true
		  && GlobalData.g_bHomeland[4] == true && GlobalData.g_bHomeland[5] == true
		  && GlobalData.g_bHomeland[6] == true && GlobalData.g_bHomeland[7] == true)
			return true;
		if ( GlobalData.g_bHomeland[0] == false && GlobalData.g_bHomeland[1] == false
		  && GlobalData.g_bHomeland[2] == false && GlobalData.g_bHomeland[3] == false
		  && GlobalData.g_bHomeland[4] == false && GlobalData.g_bHomeland[5] == false
		  && GlobalData.g_bHomeland[6] == false && GlobalData.g_bHomeland[7] == false)
			return true;
		
		if (GlobalData.g_bHomeland[0] == false)
		{
			if (strData.contains( getString(R.string.AdvancedFind_CantonTitle_1)) == true )
				return false;
		}
		else
		{
			if (strData.contains( getString(R.string.AdvancedFind_CantonTitle_1)) == true )
				return true;
		}
		
		if (GlobalData.g_bHomeland[1] == false)
		{
			if (strData.contains( getString(R.string.AdvancedFind_CantonTitle_2)) == true )
				return false;
		}
		else
		{
			if (strData.contains( getString(R.string.AdvancedFind_CantonTitle_2)) == true )
				return true;
		}
		
		if (GlobalData.g_bHomeland[2] == false)
		{
			if (strData.contains( getString(R.string.AdvancedFind_CantonTitle_3)) == true )
				return false;
		}
		else
		{
			if (strData.contains( getString(R.string.AdvancedFind_CantonTitle_3)) == true )
				return true;
		}
		
		if (GlobalData.g_bHomeland[3] == false)
		{
			if (strData.contains( getString(R.string.AdvancedFind_CantonTitle_4)) == true )
				return false;
		}
		else
		{
			if (strData.contains( getString(R.string.AdvancedFind_CantonTitle_4)) == true )
				return true;
		}
		
		if (GlobalData.g_bHomeland[4] == false)
		{
			if (strData.contains( getString(R.string.AdvancedFind_CantonTitle_5)) == true )
				return false;
		}
		else
		{
			if (strData.contains( getString(R.string.AdvancedFind_CantonTitle_5)) == true )
				return true;
		}
		
		if (GlobalData.g_bHomeland[5] == false)
		{
			if (strData.contains( getString(R.string.AdvancedFind_CantonTitle_6)) == true )
				return false;
		}
		else
		{
			if (strData.contains( getString(R.string.AdvancedFind_CantonTitle_6)) == true )
				return true;
		}
		
		if (GlobalData.g_bHomeland[6] == false)
		{
			if (strData.contains( getString(R.string.AdvancedFind_CantonTitle_7)) == true )
				return false;
		}
		else
		{
			if (strData.contains( getString(R.string.AdvancedFind_CantonTitle_7)) == true )
				return true;
		}
		
		if (GlobalData.g_bHomeland[7] == false)
		{
			if (strData.contains( getString(R.string.AdvancedFind_CantonTitle_8)) == true )
				return false;
		}
		else
		{
			if (strData.contains( getString(R.string.AdvancedFind_CantonTitle_8)) == true )
				return true;
		}
		
		return false;
	}
	
	private boolean isValidConditionForBirthday(String strData)
	{
		if (GlobalData.g_bBirthday[0] == true)
			return true;
		if ( GlobalData.g_bBirthday[1] == true && GlobalData.g_bBirthday[2] == true
			  && GlobalData.g_bBirthday[3] == true && GlobalData.g_bBirthday[4] == true
			  && GlobalData.g_bBirthday[5] == true && GlobalData.g_bBirthday[6] == true
			  && GlobalData.g_bBirthday[7] == true && GlobalData.g_bBirthday[8] == true)
			return true;
		if ( GlobalData.g_bBirthday[1] == false && GlobalData.g_bBirthday[2] == false
			  && GlobalData.g_bBirthday[3] == false && GlobalData.g_bBirthday[4] == false
			  && GlobalData.g_bBirthday[5] == false && GlobalData.g_bBirthday[6] == false
			  && GlobalData.g_bBirthday[7] == false && GlobalData.g_bBirthday[8] == false)
			return true;
			
		int nYear = 0;
		try {
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy.MM");
			Date tempDate = sdFormat.parse(strData);
			nYear = tempDate.getYear();
		} catch (ParseException e) {
			nYear = 0;
		}
		
		if (nYear < 50)
			return false;
		
		if (GlobalData.g_bBirthday[1] == true)//50
		{
			if (nYear >= 50 && nYear < 55)
				return true;
		}
		
		if (GlobalData.g_bBirthday[2] == true)//55
		{
			if (nYear >= 55 && nYear < 60)
				return true;
		}
		
		if (GlobalData.g_bBirthday[3] == true)//60
		{
			if (nYear >= 60 && nYear < 65)
				return true;
		}
		
		if (GlobalData.g_bBirthday[4] == true)//65
		{
			if (nYear >= 65 && nYear < 70)
				return true;
		}
		
		if (GlobalData.g_bBirthday[5] == true)//70
		{
			if (nYear >= 70 && nYear < 75)
				return true;
		}
		
		if (GlobalData.g_bBirthday[6] == true)//75
		{
			if (nYear >= 75 && nYear < 80)
				return true;
		}
		
		if (GlobalData.g_bBirthday[7] == true)//80
		{
			if (nYear >= 80 && nYear < 85)
				return true;
		}
		
		if (GlobalData.g_bBirthday[8] == true)//85
		{
			if (nYear >= 85)
				return true;
		}
		
		return false;
	}
	
	private boolean isValidConditionForParty(String strData)
	{
		if ( GlobalData.g_bParty[0] == true && GlobalData.g_bParty[1] == true
			  && GlobalData.g_bParty[2] == true && GlobalData.g_bParty[3] == true
			  && GlobalData.g_bParty[4] == true && GlobalData.g_bParty[5] == true
			  && GlobalData.g_bParty[6] == true && GlobalData.g_bParty[7] == true)
			return true;
		if ( GlobalData.g_bParty[0] == false && GlobalData.g_bParty[1] == false
			  && GlobalData.g_bParty[2] == false && GlobalData.g_bParty[3] == false
			  && GlobalData.g_bParty[4] == false && GlobalData.g_bParty[5] == false
			  && GlobalData.g_bParty[6] == false && GlobalData.g_bParty[7] == false)
			return true;
		
		if (GlobalData.g_bParty[0] == true)
		{
			int nYear = 0;
			try {
				SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy.MM");
				Date tempDate = sdFormat.parse(strData);
				nYear = tempDate.getYear();
			} catch (ParseException e) {
				nYear = 0;
			}
			
			if (nYear == 0)
				return false;
			else
				return true;
		}
				
		if (GlobalData.g_bParty[1] == true)
		{
			if (strData.contains( getString(R.string.AdvancedFind_PartyTitle_2)) == true )
				return true;
		}
		
		if (GlobalData.g_bParty[2] == true)
		{
			if (strData.contains( getString(R.string.AdvancedFind_PartyTitle_3)) == true )
				return true;
		}
		
		if (GlobalData.g_bParty[3] == true)
		{
			if (strData.contains( getString(R.string.AdvancedFind_PartyTitle_4)) == true )
				return true;
		}
		
		if (GlobalData.g_bParty[4] == true)
		{
			if (strData.contains( getString(R.string.AdvancedFind_PartyTitle_5)) == true )
				return true;
		}
		
		if (GlobalData.g_bParty[5] == true)
		{
			if (strData.contains( getString(R.string.AdvancedFind_PartyTitle_6)) == true )
				return true;
		}
		
		if (GlobalData.g_bParty[6] == true)
		{
			if (strData.contains( getString(R.string.AdvancedFind_PartyTitle_7)) == true )
				return true;
		}
		
		if (GlobalData.g_bParty[7] == true)
		{
			if (strData.contains( getString(R.string.AdvancedFind_PartyTitle_8)) == true )
				return true;
		}
		
		return false;
	}
	
	private boolean isValidConditionForGender(String strData)
	{
		if ( GlobalData.g_bGender[0] == true && GlobalData.g_bGender[1] == true )
			return true;
		if ( GlobalData.g_bGender[0] == false && GlobalData.g_bGender[1] == false )
			return true;
		
		if (GlobalData.g_bGender[0] == false)
		{
			if (strData.contains( getString(R.string.AdvancedFind_GenderTitle_1)) == true )
				return false;
		}
		else
		{
			if (strData.contains( getString(R.string.AdvancedFind_GenderTitle_1)) == true )
				return true;
		}
		
		if (GlobalData.g_bGender[1] == false)
		{
			if (strData.contains( getString(R.string.AdvancedFind_GenderTitle_2)) == true )
				return false;
		}
		else
		{
			if (strData.contains( getString(R.string.AdvancedFind_GenderTitle_2)) == true )
				return true;
		}
		
		if (GlobalData.g_bGender[2] == false)
		{
			return false;
		}
		
		return true;
	}
	
	private boolean isValidConditionForNation(String strData)
	{
		if ( GlobalData.g_bNation[0] == true && GlobalData.g_bNation[1] == true && GlobalData.g_bNation[2] == true )
			return true;
		if ( GlobalData.g_bNation[0] == false && GlobalData.g_bNation[1] == false && GlobalData.g_bNation[2] == false )
			return true;
		
		if (GlobalData.g_bNation[0] == false)
		{
			if (strData.contains( getString(R.string.AdvancedFind_NationTitle_1_1)) == true )
				return false;
		}
		else
		{
			if (strData.contains( getString(R.string.AdvancedFind_NationTitle_1_1)) == true )
				return true;
		}
		
		if (GlobalData.g_bNation[1] == false)
		{
			if (strData.contains( getString(R.string.AdvancedFind_NationTitle_2_1)) == true )
				return false;
		}
		else
		{
			if (strData.contains( getString(R.string.AdvancedFind_NationTitle_2_1)) == true )
				return true;
		}
		
		if (GlobalData.g_bNation[2] == false)
		{
			return false;
		}
				
		return true;
	}
	
	private boolean isValidConditionForFullTimeSystem(String strData)
	{
		if ( GlobalData.g_bFullTimeSystem[0] == true && GlobalData.g_bFullTimeSystem[1] == true
		  && GlobalData.g_bFullTimeSystem[2] == true && GlobalData.g_bFullTimeSystem[3] == true
		  && GlobalData.g_bFullTimeSystem[4] == true )
			return true;
		if ( GlobalData.g_bFullTimeSystem[0] == false && GlobalData.g_bFullTimeSystem[1] == false
		  && GlobalData.g_bFullTimeSystem[2] == false && GlobalData.g_bFullTimeSystem[3] == false
		  && GlobalData.g_bFullTimeSystem[4] == false )
			return true;
		
		if (GlobalData.g_bFullTimeSystem[0] == false)
		{
			if (strData.contains( getString(R.string.AdvancedFind_CareerTitle_1)) == true )
				return false;
		}
		else
		{
			if (strData.contains( getString(R.string.AdvancedFind_CareerTitle_1)) == true )
				return true;
		}
		
		if (GlobalData.g_bFullTimeSystem[1] == false)
		{
			if (strData.contains( getString(R.string.AdvancedFind_CareerTitle_2)) == true )
				return false;
		}
		else
		{
			if (strData.contains( getString(R.string.AdvancedFind_CareerTitle_2)) == true )
				return true;
		}
		
		if (GlobalData.g_bFullTimeSystem[2] == false)
		{
			if (strData.contains( getString(R.string.AdvancedFind_CareerTitle_3)) == true )
				return false;
		}
		else
		{
			if (strData.contains( getString(R.string.AdvancedFind_CareerTitle_3)) == true )
				return true;
		}
		
		if (GlobalData.g_bFullTimeSystem[3] == false)
		{
			if (strData.contains( getString(R.string.AdvancedFind_CareerTitle_4)) == true )
				return false;
		}
		else
		{
			if (strData.contains( getString(R.string.AdvancedFind_CareerTitle_4)) == true )
				return true;
		}
		
		if (GlobalData.g_bFullTimeSystem[4] == false)
		{
			return false;
		}
		
		return true;
	}
	
	private boolean isValidConditionForStudyHistory(String strData)
	{		
		if ( GlobalData.g_bStudyHistory[0] == true && GlobalData.g_bStudyHistory[1] == true
		  && GlobalData.g_bStudyHistory[2] == true && GlobalData.g_bStudyHistory[3] == true
		  && GlobalData.g_bStudyHistory[4] == true )
			return true;
		if ( GlobalData.g_bStudyHistory[0] == false && GlobalData.g_bStudyHistory[1] == false
		  && GlobalData.g_bStudyHistory[2] == false && GlobalData.g_bStudyHistory[3] == false
		  && GlobalData.g_bStudyHistory[4] == false )
			return true;		

		if (strData == null || strData.length() == 0)
			return false;
		
		if (GlobalData.g_bStudyHistory[0] == false)
		{
			if (strData.contains( getString(R.string.AdvancedFind_BestCareerTitle_1)) == true )
				return false;
		}
		else
		{
			if (strData.contains( getString(R.string.AdvancedFind_BestCareerTitle_1)) == true )
				return true;
		}
		
		if (GlobalData.g_bStudyHistory[1] == false)
		{
			if (strData.contains( getString(R.string.AdvancedFind_BestCareerTitle_2)) == true )
				return false;
		}
		else
		{
			if (strData.contains( getString(R.string.AdvancedFind_BestCareerTitle_2)) == true )
				return true;
		}
		
		if (GlobalData.g_bStudyHistory[2] == false)
		{
			if (strData.contains( getString(R.string.AdvancedFind_BestCareerTitle_3)) == true )
				return false;
		}
		else
		{
			if (strData.contains( getString(R.string.AdvancedFind_BestCareerTitle_3)) == true )
				return true;
		}
		
		if (GlobalData.g_bStudyHistory[3] == false)
		{
			if (strData.contains( getString(R.string.AdvancedFind_BestCareerTitle_4)) == true )
				return false;
		}
		else
		{
			if (strData.contains( getString(R.string.AdvancedFind_BestCareerTitle_4)) == true )
				return true;
		}
		
		if (GlobalData.g_bStudyHistory[4] == false)
		{
			if (strData.contains( getString(R.string.AdvancedFind_BestCareerTitle_5)) == true )
				return false;
		}	
		else
		{
			if (strData.contains( getString(R.string.AdvancedFind_BestCareerTitle_5)) == true )
				return true;
		}
		
		return true;
	}
	
	private boolean isValidConditionForDegree(String strData)
	{
		if ( GlobalData.g_bDegree[0] == true && GlobalData.g_bDegree[1] == true && GlobalData.g_bDegree[2] == true )
			return true;
		if ( GlobalData.g_bDegree[0] == false && GlobalData.g_bDegree[1] == false && GlobalData.g_bDegree[2] == false )
			return true;		

		if (strData == null || strData.length() == 0)
			return false;
		
		if (GlobalData.g_bDegree[0] == false)
		{
			if (strData.contains( getString(R.string.AdvancedFind_DegreeTitle_1)) == true )
				return false;
		}
		else
		{
			if (strData.contains( getString(R.string.AdvancedFind_DegreeTitle_1)) == true )
				return true;
		}
		
		if (GlobalData.g_bDegree[1] == false)
		{
			if (strData.contains( getString(R.string.AdvancedFind_DegreeTitle_2)) == true )
				return false;
		}
		else
		{
			if (strData.contains( getString(R.string.AdvancedFind_DegreeTitle_2)) == true )
				return true;
		}
		
		if (GlobalData.g_bDegree[2] == false)
		{
			if (strData.contains( getString(R.string.AdvancedFind_DegreeTitle_3)) == true )
				return false;
		}
		else
		{
			if (strData.contains( getString(R.string.AdvancedFind_DegreeTitle_3)) == true )
				return true;
		}
		
		return true;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			SharedPreferences pref = getSharedPreferences(GlobalData.g_SaveFile, MODE_PRIVATE);
			SharedPreferences.Editor editor = pref.edit();
			for ( int i = 0; i < VIEW_COUNT; i++ )
			{
				String strData = "";
				strData = GlobalData.g_SaveCondition + Integer.toString(i);
				editor.putBoolean(strData, m_bSelectFlag[i]);
			}
			editor.commit();
			
			SelectActivity.this.finish();
		}

		return super.onKeyDown(keyCode, event);
	}
}
