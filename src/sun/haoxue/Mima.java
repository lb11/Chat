package sun.haoxue;

import android.R.anim;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Mima extends Activity{
	TextView mtext_mi_qi;
	TextView mtext_gai;
	TextView mtext_fanhui;
	TextView mtext_mi_cancel;
	SQLiteDatabase db;
	Databaseh helper;
	Cursor c;
	int count;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mima_shezhi);
		init();
		onclick();
	}
	private void init() {
		// TODO Auto-generated method stub
		mtext_gai=(TextView)findViewById(R.id.text_mima_gai);
		mtext_mi_qi=(TextView)findViewById(R.id.text_mima_ti);
		mtext_fanhui=(TextView)findViewById(R.id.text_mima_back);
		mtext_mi_cancel=(TextView)findViewById(R.id.text_mi_quxiao);
		helper=new Databaseh(this);
		db=helper.getWritableDatabase();
		c=db.query("mima", null, null, null, null, null, null);
		count=c.getCount();
	}
	private void onclick() {
		// TODO Auto-generated method stub
		OnClickListener listener=new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (v.getId()) {
				case R.id.text_mima_back:
					finish();
					break;
				case R.id.text_mima_gai:
					db.close();
					db=helper.getWritableDatabase();
					c=db.query("mima", null, null, null, null, null, null);
					if(c.getCount()!=0)
					{
					Intent intent_g=new Intent();
					intent_g.setClass(Mima.this, Mima_shuru.class);
					Bundle bundle_g=new Bundle();
					bundle_g.putInt("zhi", 1);
					intent_g.putExtras(bundle_g);
					startActivity(intent_g);
					}
					else
						Toast.makeText(getApplicationContext(), "还未设置密码", Toast.LENGTH_SHORT).show();
					break;
				case R.id.text_mima_ti:
					db.close();
					db=helper.getWritableDatabase();
					c=db.query("mima", null, null, null, null, null, null);
					if(c.getCount()==0)
					{
						db.close();
					db=helper.getWritableDatabase();
					Intent intent_t=new Intent();
					intent_t.setClass(Mima.this, Mima_shuru.class);
					Bundle bundle_t=new Bundle();
					bundle_t.putInt("zhi", 2);
					intent_t.putExtras(bundle_t);
					startActivity(intent_t);
					}
					else
						Toast.makeText(getApplicationContext(), "密码已设置", Toast.LENGTH_SHORT).show();
					break;
				case R.id.text_mi_quxiao:
					db.close();
					db=helper.getWritableDatabase();
					c=db.query("mima", null, null, null, null, null, null);
					if(c.getCount()==0)
						Toast.makeText(getApplicationContext(), "还未设置密码", Toast.LENGTH_SHORT).show();
					else
					{
						db.close();
						db=helper.getWritableDatabase();
						db.delete("mima", null, null);
						Toast.makeText(getApplicationContext(), "取消密码成功", Toast.LENGTH_SHORT).show();
					}
					break;
				default:
					break;
				}
			}
		};
		mtext_mi_qi.setOnClickListener(listener);
		mtext_gai.setOnClickListener(listener);
		mtext_fanhui.setOnClickListener(listener);
		mtext_mi_cancel.setOnClickListener(listener);
	}

}
