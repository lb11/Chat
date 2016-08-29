package sun.haoxue;

import android.R.integer;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Mima_shuru extends Activity{
	TextView mtext_back;
	TextView mtext_sure;
	TextView mtext_jiu;
	TextView mtext_jiu_t;
	TextView mtext_xin;
	TextView mtext_xin_t;
	TextView mtext_xin2;
	TextView mtext_xin2_t;
	EditText medit_jiu;
	EditText medit_xin;
	EditText medit_xin2;
	Bundle bundle;
	SQLiteDatabase db;
	Databaseh helper;
	Cursor c;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mima_g);
		bundle=this.getIntent().getExtras();
		int a=bundle.getInt("zhi");
		init();
		if(a==1)
		{
			init1();
			onclick();
		}
		else
		{
//			Toast.makeText(getApplicationContext(), c.getCount(), Toast.LENGTH_SHORT).show();
			init1();
			init2();
			onclick1();
		}
	}
	private void onclick1() {
		// TODO Auto-generated method stub
		OnClickListener listener=new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (v.getId()) {
				case R.id.text_mima_return:
					finish();
					break;
				case R.id.text_mi_sure:
					String s_xin=medit_xin.getText().toString();
					String s_xin2=medit_xin2.getText().toString();
					if(s_xin.length()!=4)
						mtext_xin.setText("密码长度应为4");
					else
					{
						if(s_xin.equals(s_xin2))
						{
							db.execSQL("insert into mima(mima) values(?)",new Object[]{s_xin});
							Toast.makeText(getApplicationContext(), "更改密码成功", Toast.LENGTH_SHORT).show();
							db.close();
							finish();
						}
						else
							mtext_xin2.setText("密码不一致");
					}
					break;
				default:
					break;
				}
			}
		};
		mtext_back.setOnClickListener(listener);
		mtext_sure.setOnClickListener(listener);
	}
	private void init2() {
		// TODO Auto-generated method stub
		mtext_jiu.setVisibility(View.GONE);
		mtext_jiu_t.setVisibility(View.GONE);
		medit_jiu.setVisibility(View.GONE);
		mtext_xin_t.setText("输入密码");
		mtext_xin2_t.setText("确认新密码");
	}
	private void init1() {
		// TODO Auto-generated method stub
		mtext_back=(TextView)findViewById(R.id.text_mima_return);
		mtext_jiu=(TextView)findViewById(R.id.text_mi_jiu);
		mtext_jiu_t=(TextView)findViewById(R.id.text_mi_jiu_ti);
		mtext_sure=(TextView)findViewById(R.id.text_mi_sure);
		mtext_xin=(TextView)findViewById(R.id.text_mi_xin);
		mtext_xin2=(TextView)findViewById(R.id.text_mi_xin_2);
		mtext_xin_t=(TextView)findViewById(R.id.text_mi_xin_t);
		mtext_xin2_t=(TextView)findViewById(R.id.text_mi_xin_2_t);
		medit_jiu=(EditText)findViewById(R.id.edit_mi_jiu);
		medit_xin=(EditText)findViewById(R.id.edit_mi_xin);
		medit_xin2=(EditText)findViewById(R.id.edit_mi_xin_2);
	}
	private void onclick() {
		// TODO Auto-generated method stub
		OnClickListener listener=new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (v.getId()) {
				case R.id.text_mi_sure:
					String s_jiu=medit_jiu.getText().toString();
					String s_xin=medit_xin.getText().toString();
					String s_xin2=medit_xin2.getText().toString();
					
					if(s_jiu.length()!=4)
						mtext_jiu.setText("密码长度必须为4");
//					Toast.makeText(getApplicationContext(), "旧密码长度必须为4", Toast.LENGTH_SHORT).show();
					else if(s_xin.length()!=4)
						mtext_xin.setText("密码长度必须为4");
//						Toast.makeText(getApplicationContext(), "新密码长度必须为4", Toast.LENGTH_SHORT).show();
					else
					{
						String s_a=""+c.getInt(1);
						if(s_jiu.equals(s_a))
						{
							if(s_xin.equals(s_xin2))
							{
								int a_s;
								a_s=Integer.parseInt(s_xin);
								db.execSQL("update mima set mima=? where mima=?",new Object[]{a_s,c.getInt(1)});
								Toast.makeText(getApplicationContext(), "更改密码成功", Toast.LENGTH_SHORT).show();
								db.close();
								finish();
							}
							else
								mtext_xin2.setText("密码不相符");
//								Toast.makeText(getApplicationContext(), "新密码不相符", Toast.LENGTH_SHORT).show();
						}
						else
							mtext_jiu.setText("密码不正确");
//							Toast.makeText(getApplicationContext(), "旧密码不正确", Toast.LENGTH_SHORT).show();
					}
					break;
				case R.id.text_mima_return:
					finish();
					break;
					
				default:
					break;
				}
			}
		};
		mtext_back.setOnClickListener(listener);
		mtext_sure.setOnClickListener(listener);
	}
	private void init() {
		// TODO Auto-generated method stub
		helper=new Databaseh(this);
		db=helper.getWritableDatabase();
		c=db.query("mima", null, null, null, null, null, null);
		c.moveToFirst();
	}

}
