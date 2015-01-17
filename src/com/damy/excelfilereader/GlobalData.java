package com.damy.excelfilereader;

import android.content.Context;

public class GlobalData 
{
	public static PeopleData []gPeopleData;
	public static String []gCellTitles;
	public static int[] gCellWidth;
	public static boolean []g_bManagerKind = null;
	public static boolean []g_bJobLevel = null;
	public static boolean []g_bHomeland = null;
	public static boolean []g_bBirthday = null;
	public static boolean []g_bParty = null;
	public static boolean []g_bGender = null;
	public static boolean []g_bNation = null;
	public static boolean []g_bFullTimeSystem = null;
	public static boolean []g_bStudyHistory = null;
	public static boolean []g_bDegree = null;
	
	public static String g_SaveFile = "ExcelFileReader";
	public static String g_SaveCondition = "SaveCondition";
	
	public static final int EXCEL_COLS = 90;
	
	public static void init()
	{
		int i = 0;
		
		g_bManagerKind = new boolean[3];
		for ( i = 0; i < 3; i++ )
			g_bManagerKind[i] = false;
		
		g_bJobLevel = new boolean[8];
		for ( i = 0; i < 8; i++ )
			g_bJobLevel[i] = false;
		
		g_bHomeland = new boolean[8];
		for ( i = 0; i < 8; i++ )
			g_bHomeland[i] = false;
		
		g_bBirthday = new boolean[9];
		for ( i = 0; i < 9; i++ )
			g_bBirthday[i] = false;
		
		g_bParty = new boolean[8];
		for ( i = 0; i < 8; i++ )
			g_bParty[i] = false;
		
		g_bGender = new boolean[2];
		for ( i = 0; i < 2; i++ )
			g_bGender[i] = false;
		
		g_bNation = new boolean[3];
		for ( i = 0; i < 3; i++ )
			g_bNation[i] = false;
		
		g_bFullTimeSystem = new boolean[5];
		for ( i = 0; i < 5; i++ )
			g_bFullTimeSystem[i] = false;
		
		g_bStudyHistory = new boolean[5];
		for ( i = 0; i < 5; i++ )
			g_bStudyHistory[i] = false;
		
		g_bDegree = new boolean[3];
		for ( i = 0; i < 3; i++ )
			g_bDegree[i] = false;
		
		return;
	}
	
	public static void setCellTitles(Context context)
	{
		gCellTitles = context.getResources().getStringArray(R.array.Excel_Col);		
		return;
	}
	
	public static void setCellWidth(Context context)
	{
		gCellWidth = context.getResources().getIntArray(R.array.Cell_Width);
		return;
	}
}
