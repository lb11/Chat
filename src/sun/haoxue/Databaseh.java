package sun.haoxue;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Databaseh extends SQLiteOpenHelper{

	public Databaseh(Context context) {
		super(context, "xuexi", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table timu(" +
				"id          integer primary key autoincrement," +
				"index1      integer,"+
				"leixing     varchar(8)," +
				"neirong     varchar(200)," +
				"right       integer," +
				"sum         integer," +
				"shoucang   integer)");
		
		db.execSQL("create table answer(" +
				"id          integer primary key autoincrement," +
				"tishu       interger," +
				"neirong     varchar(200)," +
				"right       integer)");
		db.execSQL("create table biji("+
				"id          integer primary key autoincrement," +
				"index2      integer," +
				"tishu       integer," +
				"neirong     varchar(200))");
		db.execSQL("create table mima(" +
				"id          integer primary key autoincrement," +
				"mima        integer," +
				"question    varchar(200)," +
				"answer      varchar(100)," +
				"qiyong      integer)");
/*		db.execSQL("create table anwer_t(" +
				"id          integer primary key autoincrement," +
				"tishu       integer," +
				"leixing     varchar(8))");
*/
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

}
