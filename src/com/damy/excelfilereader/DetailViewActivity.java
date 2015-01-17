package com.damy.excelfilereader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

@SuppressLint("SimpleDateFormat")
public class DetailViewActivity extends Activity {
	private ImageView imgPhoto = null;
	//private RelativeLayout rlBack = null;
	
	private TextView lblName;
	private TextView lblGender;
	private TextView lblBirthday;
	private TextView lblNation;
	private TextView lblOriginDocimile;
	private TextView lblHomeland;
	private TextView lblPartyDate;
	private TextView lblWorkDate;
	private TextView lblHealthState;
	private TextView lblTechJobLevel;
	private TextView lblSpecTime;
	private TextView lblFullTimeSystem;
	private TextView lblOnLineStudy;
	private TextView lblGraduateSchool;
	private TextView lblCurrentJobLevel;
	private TextView lblCurrentFutureJobLevel;
	private TextView lblCurrentExitJobLevel;
	private TextView lblHistory;
	private TextView lblState;
	private TextView lblYearExamineResult;
	private TextView lblExitCause;
	private TextView lblFamily_Relation1;
	private TextView lblFamily_Relation2;
	private TextView lblFamily_Relation3;
	private TextView lblFamily_Relation4;
	private TextView lblFamily_Relation5;
	private TextView lblFamily_Relation6;
	private TextView lblFamily_Relation7;
	private TextView lblFamily_Name1;
	private TextView lblFamily_Name2;
	private TextView lblFamily_Name3;
	private TextView lblFamily_Name4;
	private TextView lblFamily_Name5;
	private TextView lblFamily_Name6;
	private TextView lblFamily_Name7;
	private TextView lblFamily_Birthday1;
	private TextView lblFamily_Birthday2;
	private TextView lblFamily_Birthday3;
	private TextView lblFamily_Birthday4;
	private TextView lblFamily_Birthday5;
	private TextView lblFamily_Birthday6;
	private TextView lblFamily_Birthday7;
	private TextView lblFamily_Party1;
	private TextView lblFamily_Party2;
	private TextView lblFamily_Party3;
	private TextView lblFamily_Party4;
	private TextView lblFamily_Party5;
	private TextView lblFamily_Party6;
	private TextView lblFamily_Party7;
	private TextView lblFamily_Job1;
	private TextView lblFamily_Job2;
	private TextView lblFamily_Job3;
	private TextView lblFamily_Job4;
	private TextView lblFamily_Job5;
	private TextView lblFamily_Job6;
	private TextView lblFamily_Job7;
	
	private String []mDetailData = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detailview);
		
		mDetailData = new String[GlobalData.EXCEL_COLS];
		mDetailData = getIntent().getStringArrayExtra("Data");
		
		if ( mDetailData == null )
		{
			Toast.makeText(DetailViewActivity.this, getString(R.string.DetailView_SelectError), Toast.LENGTH_LONG).show();
			DetailViewActivity.this.finish();
		}
		
		imgPhoto = (ImageView) findViewById(R.id.imgDetailView_Photo);
		/*
		imgPhoto.setOnClickListener( new OnClickListener() {

			float zoomFactor = 1.5f;
			boolean zoomOut = false;
			
			@Override
			public void onClick(View v) {
				if (zoomOut) {
					rlView.setScaleX(1.0f);
					rlView.setScaleY(1.0f);
					zoomOut = false;
				}
				else {
					rlView.setScaleX(zoomFactor);
					rlView.setScaleY(zoomFactor);
					zoomOut = true;
				}
			}			
		});
		*/
		String strFilePath = getImagePathFromName_Birthday(mDetailData[9], mDetailData[18]);
		int nFlag = 0;
		if ( strFilePath != null && strFilePath.length() > 0 )
		{
			strFilePath = "Download/Photo/" + "" + strFilePath + ".jpg";
			File file = new File(Environment.getExternalStorageDirectory(), strFilePath);
			FileInputStream streamIn = null;
			try {
				streamIn = new FileInputStream(file);
				nFlag = 1;
			} catch (FileNotFoundException e) {
				nFlag = 0;
			}
			
			strFilePath = getImagePathFromName_Birthday(mDetailData[9], mDetailData[18]);
			strFilePath = "Download/Photo/" + "" + strFilePath + ".bmp";
			File file1 = new File(Environment.getExternalStorageDirectory(), strFilePath);
			FileInputStream streamIn1 = null;
			try {
				streamIn1 = new FileInputStream(file1);
				nFlag = 2;
			} catch (FileNotFoundException e) {
				if (nFlag == 0)
					nFlag = 0;
			}
			
			if (nFlag == 1)
			{
				Bitmap bitmap = BitmapFactory.decodeStream(streamIn); //This gets the image
				try {
					streamIn.close();
				} catch (IOException e) {
				}
				try {
					Drawable drw = new BitmapDrawable(bitmap);
					imgPhoto.setImageDrawable(drw);
				} catch (Exception e) {
					imgPhoto.setImageResource(R.drawable.defaultphoto);
				}
			}
			else if (nFlag == 2)
			{
				Bitmap bitmap = BitmapFactory.decodeStream(streamIn1); //This gets the image
				try {
					streamIn1.close();
				} catch (IOException e) {
				}
				try {
					Drawable drw = new BitmapDrawable(bitmap);
					imgPhoto.setImageDrawable(drw);
				} catch (Exception e) {
					imgPhoto.setImageResource(R.drawable.defaultphoto);
				}
			}
			else
				imgPhoto.setImageResource(R.drawable.defaultphoto);
		}
		else
			imgPhoto.setImageResource(R.drawable.defaultphoto);
		/*
		rlBack = (RelativeLayout)findViewById(R.id.rlDetailView);
		rlBack.setOnClickListener( new OnClickListener() {
			@Override
			public void onClick(View v)
			{
				Toast.makeText(DetailViewActivity.this, "", Toast.LENGTH_SHORT).show();
			}
		});
		*/
		
		lblName = (TextView) findViewById(R.id.lblDetailView_NameValue);
		lblName.setText(mDetailData[9]);
		lblName.setOnClickListener( new OnClickListener() {

			float zoomFactor = 1.5f;
			boolean zoomOut = false;
			
			@Override
			public void onClick(View v) {
				if (zoomOut) {
					v.setScaleX(1.0f);
					v.setScaleY(1.0f);
					zoomOut = false;
				}
				else {
					v.setScaleX(zoomFactor);
					v.setScaleY(zoomFactor);
					zoomOut = true;
				}
			}			
		});
		lblGender = (TextView) findViewById(R.id.lblDetailView_GenderValue);
		lblGender.setText(mDetailData[16]);
		lblBirthday = (TextView) findViewById(R.id.lblDetailView_BirthdayValue);
		lblBirthday.setText(mDetailData[18]);
		lblNation = (TextView) findViewById(R.id.lblDetailView_NationValue);
		lblNation.setText(mDetailData[17]);
		lblOriginDocimile = (TextView) findViewById(R.id.lblDetailView_OriginDomicileValue);
		lblOriginDocimile.setText(mDetailData[20]);
		lblHomeland = (TextView) findViewById(R.id.lblDetailView_HomelandValue);
		lblHomeland.setText(mDetailData[21]);
		lblPartyDate = (TextView) findViewById(R.id.lblDetailView_PartyDateValue);
		lblPartyDate.setText(mDetailData[23]);
		lblWorkDate = (TextView) findViewById(R.id.lblDetailView_WorkDateValue);
		lblWorkDate.setText(mDetailData[22]);
		lblHealthState = (TextView) findViewById(R.id.lblDetailView_HealthStateValue);
		lblHealthState.setText(mDetailData[41]);
		lblTechJobLevel = (TextView) findViewById(R.id.lblDetailView_TechJobLevelValue);
		lblTechJobLevel.setText(mDetailData[38]);
		lblSpecTime = (TextView) findViewById(R.id.lblDetailView_SpecTimeValue);
		//lblSpecTime.setText(mDetailData[]);
		lblFullTimeSystem = (TextView) findViewById(R.id.lblDetailView_FullTimeSystemValue);
		lblFullTimeSystem.setText(mDetailData[31]);
		lblOnLineStudy = (TextView) findViewById(R.id.lblDetailView_OnLineStudyValue);
		lblOnLineStudy.setText(mDetailData[34]);
		lblGraduateSchool = (TextView) findViewById(R.id.lblDetailView_GraduateSchoolValue);
		lblGraduateSchool.setText(mDetailData[25]);
		lblCurrentJobLevel = (TextView) findViewById(R.id.lblDetailView_CurrentJobLevelValue);
		lblCurrentJobLevel.setText(mDetailData[10]);
		lblCurrentFutureJobLevel = (TextView) findViewById(R.id.lblDetailView_CurrentFutureJobLevelValue);
		lblCurrentFutureJobLevel.setText(mDetailData[12]);
		lblCurrentExitJobLevel = (TextView) findViewById(R.id.lblDetailView_CurrentFutureExitJobLevelValue);
		lblCurrentExitJobLevel.setText(mDetailData[13]);
		lblHistory = (TextView) findViewById(R.id.lblDetailView_HistoryValue);
		lblHistory.setText(mDetailData[47]);
		lblState = (TextView) findViewById(R.id.lblDetailView_StateValue);
		lblState.setText(mDetailData[39]);
		lblYearExamineResult = (TextView) findViewById(R.id.lblDetailView_YearExamineResultValue);
		lblYearExamineResult.setText(mDetailData[40]);
		lblExitCause = (TextView) findViewById(R.id.lblDetailView_ExitCauseValue);
		lblExitCause.setText(mDetailData[42]);
		lblFamily_Relation1 = (TextView) findViewById(R.id.lblDetailView_Family_Relation_1);
		lblFamily_Relation1.setText(mDetailData[48]);
		lblFamily_Relation2 = (TextView) findViewById(R.id.lblDetailView_Family_Relation_2);
		lblFamily_Relation2.setText(mDetailData[53]);
		lblFamily_Relation3 = (TextView) findViewById(R.id.lblDetailView_Family_Relation_3);
		lblFamily_Relation3.setText(mDetailData[58]);
		lblFamily_Relation4 = (TextView) findViewById(R.id.lblDetailView_Family_Relation_4);
		lblFamily_Relation4.setText(mDetailData[63]);
		lblFamily_Relation5 = (TextView) findViewById(R.id.lblDetailView_Family_Relation_5);
		lblFamily_Relation5.setText(mDetailData[68]);
		lblFamily_Relation6 = (TextView) findViewById(R.id.lblDetailView_Family_Relation_6);
		lblFamily_Relation6.setText(mDetailData[73]);
		lblFamily_Relation7 = (TextView) findViewById(R.id.lblDetailView_Family_Relation_7);
		lblFamily_Relation7.setText(mDetailData[78]);
		lblFamily_Name1 = (TextView) findViewById(R.id.lblDetailView_Family_Name_1);
		lblFamily_Name1.setText(mDetailData[49]);
		lblFamily_Name2 = (TextView) findViewById(R.id.lblDetailView_Family_Name_2);
		lblFamily_Name2.setText(mDetailData[54]);
		lblFamily_Name3 = (TextView) findViewById(R.id.lblDetailView_Family_Name_3);
		lblFamily_Name3.setText(mDetailData[59]);
		lblFamily_Name4 = (TextView) findViewById(R.id.lblDetailView_Family_Name_4);
		lblFamily_Name4.setText(mDetailData[64]);
		lblFamily_Name5 = (TextView) findViewById(R.id.lblDetailView_Family_Name_5);
		lblFamily_Name5.setText(mDetailData[69]);
		lblFamily_Name6 = (TextView) findViewById(R.id.lblDetailView_Family_Name_6);
		lblFamily_Name6.setText(mDetailData[74]);
		lblFamily_Name7 = (TextView) findViewById(R.id.lblDetailView_Family_Name_7);
		lblFamily_Name7.setText(mDetailData[79]);
		lblFamily_Birthday1 = (TextView) findViewById(R.id.lblDetailView_Family_Birthday_1);
		lblFamily_Birthday1.setText(mDetailData[50]);
		lblFamily_Birthday2 = (TextView) findViewById(R.id.lblDetailView_Family_Birthday_2);
		lblFamily_Birthday2.setText(mDetailData[55]);
		lblFamily_Birthday3 = (TextView) findViewById(R.id.lblDetailView_Family_Birthday_3);
		lblFamily_Birthday3.setText(mDetailData[60]);
		lblFamily_Birthday4 = (TextView) findViewById(R.id.lblDetailView_Family_Birthday_4);
		lblFamily_Birthday4.setText(mDetailData[65]);
		lblFamily_Birthday5 = (TextView) findViewById(R.id.lblDetailView_Family_Birthday_5);
		lblFamily_Birthday5.setText(mDetailData[70]);
		lblFamily_Birthday6 = (TextView) findViewById(R.id.lblDetailView_Family_Birthday_6);
		lblFamily_Birthday6.setText(mDetailData[75]);
		lblFamily_Birthday7 = (TextView) findViewById(R.id.lblDetailView_Family_Birthday_7);
		lblFamily_Birthday7.setText(mDetailData[80]);
		lblFamily_Party1 = (TextView) findViewById(R.id.lblDetailView_Family_Party_1);
		lblFamily_Party1.setText(mDetailData[51]);
		lblFamily_Party2 = (TextView) findViewById(R.id.lblDetailView_Family_Party_2);
		lblFamily_Party2.setText(mDetailData[56]);
		lblFamily_Party3 = (TextView) findViewById(R.id.lblDetailView_Family_Party_3);
		lblFamily_Party3.setText(mDetailData[61]);
		lblFamily_Party4 = (TextView) findViewById(R.id.lblDetailView_Family_Party_4);
		lblFamily_Party4.setText(mDetailData[66]);
		lblFamily_Party5 = (TextView) findViewById(R.id.lblDetailView_Family_Party_5);
		lblFamily_Party5.setText(mDetailData[71]);
		lblFamily_Party6 = (TextView) findViewById(R.id.lblDetailView_Family_Party_6);
		lblFamily_Party6.setText(mDetailData[76]);
		lblFamily_Party7 = (TextView) findViewById(R.id.lblDetailView_Family_Party_7);
		lblFamily_Party7.setText(mDetailData[81]);
		lblFamily_Job1 = (TextView) findViewById(R.id.lblDetailView_Family_Job_1);
		lblFamily_Job1.setText(mDetailData[52]);
		lblFamily_Job2 = (TextView) findViewById(R.id.lblDetailView_Family_Job_2);
		lblFamily_Job2.setText(mDetailData[57]);
		lblFamily_Job3 = (TextView) findViewById(R.id.lblDetailView_Family_Job_3);
		lblFamily_Job3.setText(mDetailData[62]);
		lblFamily_Job4 = (TextView) findViewById(R.id.lblDetailView_Family_Job_4);
		lblFamily_Job4.setText(mDetailData[67]);
		lblFamily_Job5 = (TextView) findViewById(R.id.lblDetailView_Family_Job_5);
		lblFamily_Job5.setText(mDetailData[72]);
		lblFamily_Job6 = (TextView) findViewById(R.id.lblDetailView_Family_Job_6);
		lblFamily_Job6.setText(mDetailData[77]);
		lblFamily_Job7 = (TextView) findViewById(R.id.lblDetailView_Family_Job_7);
		lblFamily_Job7.setText(mDetailData[82]);
	}

	private String getImagePathFromName_Birthday(String strName, String strBirthday)
	{
		String strFileName = "";
		
		int nYear = 0;
		int nMonth = 0;
		try {
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy.MM");
			Date tempDate = sdFormat.parse(strBirthday);
			nYear = tempDate.getYear();
			nMonth = tempDate.getMonth();
		} catch (ParseException e) {
			return "";
		}
		
		strFileName = strName + "_19" + Integer.toString(nYear);
		if ( nMonth < 9 )
			strFileName = strFileName + "0" + Integer.toString(nMonth+1);
		else
			strFileName = strFileName + Integer.toString(nMonth+1);
		
		return strFileName;
	}
}
