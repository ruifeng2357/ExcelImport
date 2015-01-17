package com.damy.excelfilereader;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.app.Activity;
import android.content.Intent;

public class AdvancedFindActivity extends Activity {
	
	private Button btnOk;
	private Button btnCancel;
	
	private Bundle extra;
	private Intent intent;
	
	private CheckBox chkManagerKind_1 = null;
	private CheckBox chkManagerKind_2 = null;
	private CheckBox chkManagerKind_3 = null;
	
	private CheckBox chkJobLevel_1 = null;
	private CheckBox chkJobLevel_2 = null;
	private CheckBox chkJobLevel_3 = null;
	private CheckBox chkJobLevel_4 = null;
	private CheckBox chkJobLevel_5 = null;
	private CheckBox chkJobLevel_6 = null;
	private CheckBox chkJobLevel_7 = null;
	private CheckBox chkJobLevel_8 = null;
	
	private CheckBox chkHomeland_1 = null;
	private CheckBox chkHomeland_2 = null;
	private CheckBox chkHomeland_3 = null;
	private CheckBox chkHomeland_4 = null;
	private CheckBox chkHomeland_5 = null;
	private CheckBox chkHomeland_6 = null;
	private CheckBox chkHomeland_7 = null;
	private CheckBox chkHomeland_8 = null;
	
	private CheckBox chkBirthday_All = null;
	private CheckBox chkBirthday_1 = null;
	private CheckBox chkBirthday_2 = null;
	private CheckBox chkBirthday_3 = null;
	private CheckBox chkBirthday_4 = null;
	private CheckBox chkBirthday_5 = null;
	private CheckBox chkBirthday_6 = null;
	private CheckBox chkBirthday_7 = null;
	private CheckBox chkBirthday_8 = null;
	
	private CheckBox chkParty_1 = null;
	private CheckBox chkParty_2 = null;
	private CheckBox chkParty_3 = null;
	private CheckBox chkParty_4 = null;
	private CheckBox chkParty_5 = null;
	private CheckBox chkParty_6 = null;
	private CheckBox chkParty_7 = null;
	private CheckBox chkParty_8 = null;
	
	private CheckBox chkGender_1 = null;
	private CheckBox chkGender_2 = null;
	
	private CheckBox chkNation_1 = null;
	private CheckBox chkNation_2 = null;
	private CheckBox chkNation_3 = null;
	
	private CheckBox chkFullTimeSystem_1 = null;
	private CheckBox chkFullTimeSystem_2 = null;
	private CheckBox chkFullTimeSystem_3 = null;
	private CheckBox chkFullTimeSystem_4 = null;
	private CheckBox chkFullTimeSystem_5 = null;
	
	private CheckBox chkStudyHistory_1 = null;
	private CheckBox chkStudyHistory_2 = null;
	private CheckBox chkStudyHistory_3 = null;
	private CheckBox chkStudyHistory_4 = null;
	private CheckBox chkStudyHistory_5 = null;
	
	private CheckBox chkDegree_1 = null;
	private CheckBox chkDegree_2 = null;
	private CheckBox chkDegree_3 = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.advancedfind);
		
		chkManagerKind_1 = (CheckBox) findViewById(R.id.chkAdvancedFind_Manager_1);
		chkManagerKind_2 = (CheckBox) findViewById(R.id.chkAdvancedFind_Manager_2);
		chkManagerKind_3 = (CheckBox) findViewById(R.id.chkAdvancedFind_Manager_3);
		
		chkJobLevel_1 = (CheckBox) findViewById(R.id.chkAdvancedFind_Job_1);
		chkJobLevel_2 = (CheckBox) findViewById(R.id.chkAdvancedFind_Job_2);
		chkJobLevel_3 = (CheckBox) findViewById(R.id.chkAdvancedFind_Job_3);
		chkJobLevel_4 = (CheckBox) findViewById(R.id.chkAdvancedFind_Job_4);
		chkJobLevel_5 = (CheckBox) findViewById(R.id.chkAdvancedFind_Job_5);
		chkJobLevel_6 = (CheckBox) findViewById(R.id.chkAdvancedFind_Job_6);
		chkJobLevel_7 = (CheckBox) findViewById(R.id.chkAdvancedFind_Job_7);
		chkJobLevel_8 = (CheckBox) findViewById(R.id.chkAdvancedFind_Job_8);
		
		chkHomeland_1 = (CheckBox) findViewById(R.id.chkAdvancedFind_Canton_1);
		chkHomeland_2 = (CheckBox) findViewById(R.id.chkAdvancedFind_Canton_2);
		chkHomeland_3 = (CheckBox) findViewById(R.id.chkAdvancedFind_Canton_3);
		chkHomeland_4 = (CheckBox) findViewById(R.id.chkAdvancedFind_Canton_4);
		chkHomeland_5 = (CheckBox) findViewById(R.id.chkAdvancedFind_Canton_5);
		chkHomeland_6 = (CheckBox) findViewById(R.id.chkAdvancedFind_Canton_6);
		chkHomeland_7 = (CheckBox) findViewById(R.id.chkAdvancedFind_Canton_7);
		chkHomeland_8 = (CheckBox) findViewById(R.id.chkAdvancedFind_Canton_8);
		
		chkBirthday_All = (CheckBox) findViewById(R.id.chkAdvancedFind_Birth_All);
		chkBirthday_All.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked)
				{
					chkBirthday_All.setChecked(true);
					chkBirthday_1.setChecked(false);
					chkBirthday_2.setChecked(false);
					chkBirthday_3.setChecked(false);
					chkBirthday_4.setChecked(false);
					chkBirthday_5.setChecked(false);
					chkBirthday_6.setChecked(false);
					chkBirthday_7.setChecked(false);
					chkBirthday_8.setChecked(false);
				}
				else
				{
					if (chkBirthday_1.isChecked() == false && chkBirthday_2.isChecked() == false && chkBirthday_All.isChecked() == false
					 && chkBirthday_3.isChecked() == false && chkBirthday_4.isChecked() == false
					 && chkBirthday_5.isChecked() == false && chkBirthday_6.isChecked() == false
					 && chkBirthday_7.isChecked() == false && chkBirthday_8.isChecked() == false)
					{
						chkBirthday_All.setChecked(true);
					}
				}
			}
		});		
		chkBirthday_1 = (CheckBox) findViewById(R.id.chkAdvancedFind_Birth_1);
		chkBirthday_1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked)
				{
					chkBirthday_All.setChecked(false);
					chkBirthday_1.setChecked(true);
					chkBirthday_2.setChecked(false);
					chkBirthday_3.setChecked(false);
					chkBirthday_4.setChecked(false);
					chkBirthday_5.setChecked(false);
					chkBirthday_6.setChecked(false);
					chkBirthday_7.setChecked(false);
					chkBirthday_8.setChecked(false);
				}
				else
				{
					if (chkBirthday_1.isChecked() == false && chkBirthday_2.isChecked() == false && chkBirthday_All.isChecked() == false
					 && chkBirthday_3.isChecked() == false && chkBirthday_4.isChecked() == false
					 && chkBirthday_5.isChecked() == false && chkBirthday_6.isChecked() == false
					 && chkBirthday_7.isChecked() == false && chkBirthday_8.isChecked() == false)
					{
						chkBirthday_1.setChecked(true);
					}
				}
			}
		});
		chkBirthday_2 = (CheckBox) findViewById(R.id.chkAdvancedFind_Birth_2);
		chkBirthday_2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked)
				{
					chkBirthday_All.setChecked(false);
					chkBirthday_2.setChecked(true);
					chkBirthday_1.setChecked(false);
					chkBirthday_3.setChecked(false);
					chkBirthday_4.setChecked(false);
					chkBirthday_5.setChecked(false);
					chkBirthday_6.setChecked(false);
					chkBirthday_7.setChecked(false);
					chkBirthday_8.setChecked(false);
				}
				else
				{
					if (chkBirthday_1.isChecked() == false && chkBirthday_2.isChecked() == false && chkBirthday_All.isChecked() == false
					 && chkBirthday_3.isChecked() == false && chkBirthday_4.isChecked() == false
					 && chkBirthday_5.isChecked() == false && chkBirthday_6.isChecked() == false
					 && chkBirthday_7.isChecked() == false && chkBirthday_8.isChecked() == false)
					{
						chkBirthday_2.setChecked(true);
					}
				}
			}
		});
		chkBirthday_3 = (CheckBox) findViewById(R.id.chkAdvancedFind_Birth_3);
		chkBirthday_3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked)
				{
					chkBirthday_All.setChecked(false);
					chkBirthday_3.setChecked(true);
					chkBirthday_2.setChecked(false);
					chkBirthday_1.setChecked(false);
					chkBirthday_4.setChecked(false);
					chkBirthday_5.setChecked(false);
					chkBirthday_6.setChecked(false);
					chkBirthday_7.setChecked(false);
					chkBirthday_8.setChecked(false);
				}
				else
				{
					if (chkBirthday_1.isChecked() == false && chkBirthday_2.isChecked() == false && chkBirthday_All.isChecked() == false
					 && chkBirthday_3.isChecked() == false && chkBirthday_4.isChecked() == false
					 && chkBirthday_5.isChecked() == false && chkBirthday_6.isChecked() == false
					 && chkBirthday_7.isChecked() == false && chkBirthday_8.isChecked() == false)
					{
						chkBirthday_3.setChecked(true);
					}
				}
			}
		});
		chkBirthday_4 = (CheckBox) findViewById(R.id.chkAdvancedFind_Birth_4);
		chkBirthday_4.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked)
				{
					chkBirthday_All.setChecked(false);
					chkBirthday_4.setChecked(true);
					chkBirthday_2.setChecked(false);
					chkBirthday_3.setChecked(false);
					chkBirthday_1.setChecked(false);
					chkBirthday_5.setChecked(false);
					chkBirthday_6.setChecked(false);
					chkBirthday_7.setChecked(false);
					chkBirthday_8.setChecked(false);
				}
				else
				{
					if (chkBirthday_1.isChecked() == false && chkBirthday_2.isChecked() == false && chkBirthday_All.isChecked() == false
					 && chkBirthday_3.isChecked() == false && chkBirthday_4.isChecked() == false
					 && chkBirthday_5.isChecked() == false && chkBirthday_6.isChecked() == false
					 && chkBirthday_7.isChecked() == false && chkBirthday_8.isChecked() == false)
					{
						chkBirthday_4.setChecked(true);
					}
				}
			}
		});
		chkBirthday_5 = (CheckBox) findViewById(R.id.chkAdvancedFind_Birth_5);
		chkBirthday_5.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked)
				{
					chkBirthday_All.setChecked(false);
					chkBirthday_5.setChecked(true);
					chkBirthday_2.setChecked(false);
					chkBirthday_3.setChecked(false);
					chkBirthday_4.setChecked(false);
					chkBirthday_1.setChecked(false);
					chkBirthday_6.setChecked(false);
					chkBirthday_7.setChecked(false);
					chkBirthday_8.setChecked(false);
				}
				else
				{
					if (chkBirthday_1.isChecked() == false && chkBirthday_2.isChecked() == false && chkBirthday_All.isChecked() == false
					 && chkBirthday_3.isChecked() == false && chkBirthday_4.isChecked() == false
					 && chkBirthday_5.isChecked() == false && chkBirthday_6.isChecked() == false
					 && chkBirthday_7.isChecked() == false && chkBirthday_8.isChecked() == false)
					{
						chkBirthday_5.setChecked(true);
					}
				}
			}
		});
		chkBirthday_6 = (CheckBox) findViewById(R.id.chkAdvancedFind_Birth_6);
		chkBirthday_6.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked)
				{
					chkBirthday_All.setChecked(false);
					chkBirthday_6.setChecked(true);
					chkBirthday_2.setChecked(false);
					chkBirthday_3.setChecked(false);
					chkBirthday_4.setChecked(false);
					chkBirthday_5.setChecked(false);
					chkBirthday_1.setChecked(false);
					chkBirthday_7.setChecked(false);
					chkBirthday_8.setChecked(false);
				}
				else
				{
					if (chkBirthday_1.isChecked() == false && chkBirthday_2.isChecked() == false && chkBirthday_All.isChecked() == false
					 && chkBirthday_3.isChecked() == false && chkBirthday_4.isChecked() == false
					 && chkBirthday_5.isChecked() == false && chkBirthday_6.isChecked() == false
					 && chkBirthday_7.isChecked() == false && chkBirthday_8.isChecked() == false)
					{
						chkBirthday_6.setChecked(true);
					}
				}
			}
		});
		chkBirthday_7 = (CheckBox) findViewById(R.id.chkAdvancedFind_Birth_7);
		chkBirthday_7.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked)
				{
					chkBirthday_All.setChecked(false);
					chkBirthday_7.setChecked(true);
					chkBirthday_2.setChecked(false);
					chkBirthday_3.setChecked(false);
					chkBirthday_4.setChecked(false);
					chkBirthday_5.setChecked(false);
					chkBirthday_6.setChecked(false);
					chkBirthday_1.setChecked(false);
					chkBirthday_8.setChecked(false);
				}
				else
				{
					if (chkBirthday_1.isChecked() == false && chkBirthday_2.isChecked() == false && chkBirthday_All.isChecked() == false
					 && chkBirthday_3.isChecked() == false && chkBirthday_4.isChecked() == false
					 && chkBirthday_5.isChecked() == false && chkBirthday_6.isChecked() == false
					 && chkBirthday_7.isChecked() == false && chkBirthday_8.isChecked() == false)
					{
						chkBirthday_7.setChecked(true);
					}
				}
			}
		});
		chkBirthday_8 = (CheckBox) findViewById(R.id.chkAdvancedFind_Birth_8);
		chkBirthday_8.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked)
				{
					chkBirthday_All.setChecked(false);
					chkBirthday_8.setChecked(true);
					chkBirthday_2.setChecked(false);
					chkBirthday_3.setChecked(false);
					chkBirthday_4.setChecked(false);
					chkBirthday_5.setChecked(false);
					chkBirthday_6.setChecked(false);
					chkBirthday_7.setChecked(false);
					chkBirthday_1.setChecked(false);
				}
				else
				{
					if (chkBirthday_1.isChecked() == false && chkBirthday_2.isChecked() == false && chkBirthday_All.isChecked() == false
					 && chkBirthday_3.isChecked() == false && chkBirthday_4.isChecked() == false
					 && chkBirthday_5.isChecked() == false && chkBirthday_6.isChecked() == false
					 && chkBirthday_7.isChecked() == false && chkBirthday_8.isChecked() == false)
					{
						chkBirthday_8.setChecked(true);
					}
				}
			}
		});
		
		chkParty_1 = (CheckBox) findViewById(R.id.chkAdvancedFind_Party_1);
		chkParty_2 = (CheckBox) findViewById(R.id.chkAdvancedFind_Party_2);
		chkParty_3 = (CheckBox) findViewById(R.id.chkAdvancedFind_Party_3);
		chkParty_4 = (CheckBox) findViewById(R.id.chkAdvancedFind_Party_4);
		chkParty_5 = (CheckBox) findViewById(R.id.chkAdvancedFind_Party_5);
		chkParty_6 = (CheckBox) findViewById(R.id.chkAdvancedFind_Party_6);
		chkParty_7 = (CheckBox) findViewById(R.id.chkAdvancedFind_Party_7);
		chkParty_8 = (CheckBox) findViewById(R.id.chkAdvancedFind_Party_8);
		
		chkGender_1 = (CheckBox) findViewById(R.id.chkAdvancedFind_Gender_1);
		chkGender_2 = (CheckBox) findViewById(R.id.chkAdvancedFind_Gender_2);
		
		chkNation_1 = (CheckBox) findViewById(R.id.chkAdvancedFind_Nation_1);
		chkNation_2 = (CheckBox) findViewById(R.id.chkAdvancedFind_Nation_2);
		chkNation_3 = (CheckBox) findViewById(R.id.chkAdvancedFind_Nation_3);
		
		chkFullTimeSystem_1 = (CheckBox) findViewById(R.id.chkAdvancedFind_Career_1);
		chkFullTimeSystem_2 = (CheckBox) findViewById(R.id.chkAdvancedFind_Career_2);
		chkFullTimeSystem_3 = (CheckBox) findViewById(R.id.chkAdvancedFind_Career_3);
		chkFullTimeSystem_4 = (CheckBox) findViewById(R.id.chkAdvancedFind_Career_4);
		chkFullTimeSystem_5 = (CheckBox) findViewById(R.id.chkAdvancedFind_Career_5);
		
		chkStudyHistory_1 = (CheckBox) findViewById(R.id.chkAdvancedFind_BestCareer_1);
		chkStudyHistory_2 = (CheckBox) findViewById(R.id.chkAdvancedFind_BestCareer_2);
		chkStudyHistory_3 = (CheckBox) findViewById(R.id.chkAdvancedFind_BestCareer_3);
		chkStudyHistory_4 = (CheckBox) findViewById(R.id.chkAdvancedFind_BestCareer_4);
		chkStudyHistory_5 = (CheckBox) findViewById(R.id.chkAdvancedFind_BestCareer_5);
		
		chkDegree_1 = (CheckBox) findViewById(R.id.chkAdvancedFind_Degree_1);
		chkDegree_2 = (CheckBox) findViewById(R.id.chkAdvancedFind_Degree_2);
		chkDegree_3 = (CheckBox) findViewById(R.id.chkAdvancedFind_Degree_3);
		
		chkManagerKind_1.setChecked( GlobalData.g_bManagerKind[0] );
		chkManagerKind_2.setChecked( GlobalData.g_bManagerKind[1] );
		chkManagerKind_3.setChecked( GlobalData.g_bManagerKind[2] );
		
		chkJobLevel_1.setChecked(GlobalData.g_bJobLevel[0]);
		chkJobLevel_2.setChecked(GlobalData.g_bJobLevel[1]);
		chkJobLevel_3.setChecked(GlobalData.g_bJobLevel[2]);
		chkJobLevel_4.setChecked(GlobalData.g_bJobLevel[3]);
		chkJobLevel_5.setChecked(GlobalData.g_bJobLevel[4]);
		chkJobLevel_6.setChecked(GlobalData.g_bJobLevel[5]);
		chkJobLevel_7.setChecked(GlobalData.g_bJobLevel[6]);
		chkJobLevel_8.setChecked(GlobalData.g_bJobLevel[7]);
		
		chkHomeland_1.setChecked(GlobalData.g_bHomeland[0]);
		chkHomeland_2.setChecked(GlobalData.g_bHomeland[1]);
		chkHomeland_3.setChecked(GlobalData.g_bHomeland[2]);
		chkHomeland_4.setChecked(GlobalData.g_bHomeland[3]);
		chkHomeland_5.setChecked(GlobalData.g_bHomeland[4]);
		chkHomeland_6.setChecked(GlobalData.g_bHomeland[5]);
		chkHomeland_7.setChecked(GlobalData.g_bHomeland[6]);
		chkHomeland_8.setChecked(GlobalData.g_bHomeland[7]);
		
		chkBirthday_All.setChecked(GlobalData.g_bBirthday[0]);
		chkBirthday_1.setChecked(GlobalData.g_bBirthday[1]);
		chkBirthday_2.setChecked(GlobalData.g_bBirthday[2]);
		chkBirthday_3.setChecked(GlobalData.g_bBirthday[3]);
		chkBirthday_4.setChecked(GlobalData.g_bBirthday[4]);
		chkBirthday_5.setChecked(GlobalData.g_bBirthday[5]);
		chkBirthday_6.setChecked(GlobalData.g_bBirthday[6]);
		chkBirthday_7.setChecked(GlobalData.g_bBirthday[7]);
		chkBirthday_8.setChecked(GlobalData.g_bBirthday[8]);
		
		chkParty_1.setChecked(GlobalData.g_bParty[0]);
		chkParty_2.setChecked(GlobalData.g_bParty[1]);
		chkParty_3.setChecked(GlobalData.g_bParty[2]);
		chkParty_4.setChecked(GlobalData.g_bParty[3]);
		chkParty_5.setChecked(GlobalData.g_bParty[4]);
		chkParty_6.setChecked(GlobalData.g_bParty[5]);
		chkParty_7.setChecked(GlobalData.g_bParty[6]);
		chkParty_8.setChecked(GlobalData.g_bParty[7]);
		
		chkGender_1.setChecked(GlobalData.g_bGender[0]);
		chkGender_2.setChecked(GlobalData.g_bGender[1]);
		
		chkNation_1.setChecked(GlobalData.g_bNation[0]);
		chkNation_2.setChecked(GlobalData.g_bNation[1]);
		chkNation_3.setChecked(GlobalData.g_bNation[2]);
		
		chkFullTimeSystem_1.setChecked(GlobalData.g_bFullTimeSystem[0]);
		chkFullTimeSystem_2.setChecked(GlobalData.g_bFullTimeSystem[1]);
		chkFullTimeSystem_3.setChecked(GlobalData.g_bFullTimeSystem[2]);
		chkFullTimeSystem_4.setChecked(GlobalData.g_bFullTimeSystem[3]);
		chkFullTimeSystem_5.setChecked(GlobalData.g_bFullTimeSystem[4]);
		
		chkStudyHistory_1.setChecked(GlobalData.g_bStudyHistory[0]);
		chkStudyHistory_2.setChecked(GlobalData.g_bStudyHistory[1]);
		chkStudyHistory_3.setChecked(GlobalData.g_bStudyHistory[2]);
		chkStudyHistory_4.setChecked(GlobalData.g_bStudyHistory[3]);
		chkStudyHistory_5.setChecked(GlobalData.g_bStudyHistory[4]);
		
		chkDegree_1.setChecked(GlobalData.g_bDegree[0]);
		chkDegree_2.setChecked(GlobalData.g_bDegree[1]);
		chkDegree_3.setChecked(GlobalData.g_bDegree[2]);
		
		btnOk = (Button) findViewById(R.id.btnAdvancedFind_Ok);
		btnOk.setOnClickListener( new OnClickListener() {
			@Override
			public void onClick(View v)
			{
				GlobalData.g_bManagerKind[0] = chkManagerKind_1.isChecked();
				GlobalData.g_bManagerKind[1] = chkManagerKind_2.isChecked();
				GlobalData.g_bManagerKind[2] = chkManagerKind_3.isChecked();
				
				GlobalData.g_bJobLevel[0] = chkJobLevel_1.isChecked();
				GlobalData.g_bJobLevel[1] = chkJobLevel_2.isChecked();
				GlobalData.g_bJobLevel[2] = chkJobLevel_3.isChecked();
				GlobalData.g_bJobLevel[3] = chkJobLevel_4.isChecked();
				GlobalData.g_bJobLevel[4] = chkJobLevel_5.isChecked();
				GlobalData.g_bJobLevel[5] = chkJobLevel_6.isChecked();
				GlobalData.g_bJobLevel[6] = chkJobLevel_7.isChecked();
				GlobalData.g_bJobLevel[7] = chkJobLevel_8.isChecked();
				
				GlobalData.g_bHomeland[0] = chkHomeland_1.isChecked();
				GlobalData.g_bHomeland[1] = chkHomeland_2.isChecked();
				GlobalData.g_bHomeland[2] = chkHomeland_3.isChecked();
				GlobalData.g_bHomeland[3] = chkHomeland_4.isChecked();
				GlobalData.g_bHomeland[4] = chkHomeland_5.isChecked();
				GlobalData.g_bHomeland[5] = chkHomeland_6.isChecked();
				GlobalData.g_bHomeland[6] = chkHomeland_7.isChecked();
				GlobalData.g_bHomeland[7] = chkHomeland_8.isChecked();
				
				GlobalData.g_bBirthday[0] = chkBirthday_All.isChecked();
				GlobalData.g_bBirthday[1] = chkBirthday_1.isChecked();
				GlobalData.g_bBirthday[2] = chkBirthday_2.isChecked();
				GlobalData.g_bBirthday[3] = chkBirthday_3.isChecked();
				GlobalData.g_bBirthday[4] = chkBirthday_4.isChecked();
				GlobalData.g_bBirthday[5] = chkBirthday_5.isChecked();
				GlobalData.g_bBirthday[6] = chkBirthday_6.isChecked();
				GlobalData.g_bBirthday[7] = chkBirthday_7.isChecked();
				GlobalData.g_bBirthday[8] = chkBirthday_8.isChecked();
				
				GlobalData.g_bParty[0] = chkParty_1.isChecked();
				GlobalData.g_bParty[1] = chkParty_2.isChecked();
				GlobalData.g_bParty[2] = chkParty_3.isChecked();
				GlobalData.g_bParty[3] = chkParty_4.isChecked();
				GlobalData.g_bParty[4] = chkParty_5.isChecked();
				GlobalData.g_bParty[5] = chkParty_6.isChecked();
				GlobalData.g_bParty[6] = chkParty_7.isChecked();
				GlobalData.g_bParty[7] = chkParty_8.isChecked();
				
				GlobalData.g_bGender[0] = chkGender_1.isChecked();
				GlobalData.g_bGender[1] = chkGender_2.isChecked();
				
				GlobalData.g_bNation[0] = chkNation_1.isChecked();
				GlobalData.g_bNation[1] = chkNation_2.isChecked();
				GlobalData.g_bNation[2] = chkNation_3.isChecked();
				
				GlobalData.g_bFullTimeSystem[0] = chkFullTimeSystem_1.isChecked();
				GlobalData.g_bFullTimeSystem[1] = chkFullTimeSystem_2.isChecked();
				GlobalData.g_bFullTimeSystem[2] = chkFullTimeSystem_3.isChecked();
				GlobalData.g_bFullTimeSystem[3] = chkFullTimeSystem_4.isChecked();
				GlobalData.g_bFullTimeSystem[4] = chkFullTimeSystem_5.isChecked();
				
				GlobalData.g_bStudyHistory[0] = chkStudyHistory_1.isChecked();
				GlobalData.g_bStudyHistory[1] = chkStudyHistory_2.isChecked();
				GlobalData.g_bStudyHistory[2] = chkStudyHistory_3.isChecked();
				GlobalData.g_bStudyHistory[3] = chkStudyHistory_4.isChecked();
				GlobalData.g_bStudyHistory[4] = chkStudyHistory_5.isChecked();
				
				GlobalData.g_bDegree[0] = chkDegree_1.isChecked();
				GlobalData.g_bDegree[1] = chkDegree_2.isChecked();
				GlobalData.g_bDegree[2] = chkDegree_3.isChecked();
				
				extra = new Bundle();
				intent = new Intent();
				intent.putExtras(extra);
				AdvancedFindActivity.this.setResult(RESULT_OK, intent);
				AdvancedFindActivity.this.finish();
			}
		});
		
		btnCancel = (Button) findViewById(R.id.btnAdvancedFind_Cancel);
		btnCancel.setOnClickListener( new OnClickListener() {
			@Override
			public void onClick(View v)
			{
				AdvancedFindActivity.this.finish();
				return;
			}
		});
	}
}
