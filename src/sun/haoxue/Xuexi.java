package sun.haoxue;

import android.os.Bundle;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Xuexi extends Activity {
	Button mbutton_xuanze;
	Button mbutton_tiankong;
	Button mbutton_jieda;
	Button mbutton_luru;
	Button mbutton_mima;
	Button mbutton_deleteall;
	Button mbutton_luru_tiankong;
	Button mbutton_luru_jie;
	TextView mtext_mi_delete;
	TextView mtext_mi_forget;
	Button button_mi_1;
	Button button_mi_2;
	Button button_mi_3;
	Button button_mi_4;
	Button button_mi_5;
	Button button_mi_6;
	Button button_mi_7;
	Button button_mi_8;
	Button button_mi_9;
	Button button_mi_0;
	ImageView image_1;
	ImageView image_2;
	ImageView image_3;
	ImageView image_4;
	String s="";
	int flag=0;
	SQLiteDatabase db;
	Databaseh helper=new Databaseh(this);
	Cursor c_m;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		db=helper.getWritableDatabase();
		c_m=db.query("mima", null, null, null, null, null, null);
		c_m.moveToFirst();
		if(c_m.getCount()!=0)
		{
		setContentView(R.layout.suo);
		init2();
		mtext_mi_forget.setVisibility(View.INVISIBLE);
		mtext_mi_forget.setText("确定");
		onclick_1();
		}
		else
		{
		setContentView(R.layout.caidan);
		init();
		click_c();
		}
	}
	
	private void onclick_1() {
		// TODO Auto-generated method stub
android.view.View.OnClickListener listener;
		listener=new android.view.View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (v.getId()) {
				case R.id.button_mi_0:
					if(flag<4)
					{
						flag++;
						s+="0";
					}
					break;
				case R.id.button_mi_1:
					if(flag<4)
					{
						flag++;
						s+="1";
					}
					break;
				case R.id.button_mi_2:
					if(flag<4)
					{
						flag++;
						s+="2";
					}
					break;
				case R.id.button_mi_3:
					if(flag<4)
					{
						flag++;
						s+="3";
					}
					break;
				case R.id.button_mi_4:
					if(flag<4)
					{
						flag++;
						s+="4";
					}
					break;
				case R.id.button_mi_5:
					if(flag<4)
					{
						flag++; 
						s+="5";
					}
					break;
				case R.id.button_mi_6:
					if(flag<4)
					{
						flag++;
						s+="6";
					}
					break;
				case R.id.button_mi_7:
					if(flag<4)
					{
						flag++;
						s+="7";
					}
					break;
				case R.id.button_mi_8:
					if(flag<4)
					{
						flag++;
						s+="8";
					}
					break;
				case R.id.button_mi_9:
					if(flag<4)
					{
						flag++;
						s+="9";
					}
					break;
				case R.id.text_mi_delete:
					if(flag>0)
					{
						flag--;
						s=s.substring(0,s.length()-1);
					}
					break;
				case R.id.text_mi_wangji:
					break;
				default:
					break;
				}
				if(flag==1)
				{
					image_1.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
					image_2.setBackgroundColor(getResources().getColor(android.R.color.white));
					image_3.setBackgroundColor(getResources().getColor(android.R.color.white));
					image_4.setBackgroundColor(getResources().getColor(android.R.color.white));
				}
				else if(flag==2)
				{
					image_1.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
					image_2.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
					image_3.setBackgroundColor(getResources().getColor(android.R.color.white));
					image_4.setBackgroundColor(getResources().getColor(android.R.color.white));
				}
				else if(flag==3)
				{
					image_1.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
					image_2.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
					image_3.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
					image_4.setBackgroundColor(getResources().getColor(android.R.color.white));
				}
				else if(flag==4)
				{
					String s_a;
					s_a=""+c_m.getInt(1);
					image_1.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
					image_2.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
					image_3.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
					image_4.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
					if(s.equals(s_a))
					{
						setContentView(R.layout.caidan);
						init();
						click_c();
					}
					else
					{
						s="";
						flag=0;
						image_1.setBackgroundColor(getResources().getColor(android.R.color.white));
						image_2.setBackgroundColor(getResources().getColor(android.R.color.white));
						image_3.setBackgroundColor(getResources().getColor(android.R.color.white));
						image_4.setBackgroundColor(getResources().getColor(android.R.color.white));
					}
					
				}
				else
				{
					image_1.setBackgroundColor(getResources().getColor(android.R.color.white));
					image_2.setBackgroundColor(getResources().getColor(android.R.color.white));
					image_3.setBackgroundColor(getResources().getColor(android.R.color.white));
					image_4.setBackgroundColor(getResources().getColor(android.R.color.white));
				}
//				Toast.makeText(getApplicationContext(), s+"  "+flag, Toast.LENGTH_SHORT).show();
			}

		};
		button_mi_0.setOnClickListener((android.view.View.OnClickListener) listener);
		button_mi_1.setOnClickListener((android.view.View.OnClickListener) listener);
		button_mi_2.setOnClickListener((android.view.View.OnClickListener) listener);
		button_mi_3.setOnClickListener((android.view.View.OnClickListener) listener);
		button_mi_4.setOnClickListener((android.view.View.OnClickListener) listener);
		button_mi_5.setOnClickListener((android.view.View.OnClickListener) listener);
		button_mi_6.setOnClickListener((android.view.View.OnClickListener) listener);
		button_mi_7.setOnClickListener((android.view.View.OnClickListener) listener);
		button_mi_8.setOnClickListener((android.view.View.OnClickListener) listener);
		button_mi_9.setOnClickListener((android.view.View.OnClickListener) listener);
		mtext_mi_delete.setOnClickListener((android.view.View.OnClickListener) listener);
		mtext_mi_forget.setOnClickListener((android.view.View.OnClickListener) listener);
	}

	private void init2() {
		// TODO Auto-generated method stub
		mtext_mi_forget=(TextView)findViewById(R.id.text_mi_wangji);
		mtext_mi_delete=(TextView)findViewById(R.id.text_mi_delete);
		button_mi_0=(Button)findViewById(R.id.button_mi_0);
		button_mi_1=(Button)findViewById(R.id.button_mi_1);
		button_mi_2=(Button)findViewById(R.id.button_mi_2);
		button_mi_3=(Button)findViewById(R.id.button_mi_3);
		button_mi_4=(Button)findViewById(R.id.button_mi_4);
		button_mi_5=(Button)findViewById(R.id.button_mi_5);
		button_mi_6=(Button)findViewById(R.id.button_mi_6);
		button_mi_7=(Button)findViewById(R.id.button_mi_7);
		button_mi_8=(Button)findViewById(R.id.button_mi_8);
		button_mi_9=(Button)findViewById(R.id.button_mi_9);
		image_1=(ImageView)findViewById(R.id.image_m_1);
		image_2=(ImageView)findViewById(R.id.image_m_2);
		image_3=(ImageView)findViewById(R.id.image_m_3);
		image_4=(ImageView)findViewById(R.id.image_m_4);
	}

	public void init()
	{
		mbutton_jieda=(Button)findViewById(R.id.button_jiedati);
		mbutton_luru=(Button)findViewById(R.id.button_luru);
		mbutton_mima=(Button)findViewById(R.id.button_mima);
		mbutton_tiankong=(Button)findViewById(R.id.button_tiankongti);
		mbutton_xuanze=(Button)findViewById(R.id.button_xuanze);
		mbutton_deleteall=(Button)findViewById(R.id.button_deleteall);
		mbutton_luru_tiankong=(Button)findViewById(R.id.button_luru_tiankong);
		mbutton_luru_jie=(Button)findViewById(R.id.button_luru_jie);
		db=helper.getWritableDatabase();
	}
	
	public void click_c()
	{
		android.view.View.OnClickListener listener;
		listener=new android.view.View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Message listener_1;
				// TODO Auto-generated method stub
				switch(v.getId())
				{
				case R.id.button_xuanze:
					Intent intent;
					intent=new Intent();
					intent.setClass(Xuexi.this,Xuanze_luru.class);
					Bundle bundle_x=new Bundle();
					bundle_x.putString("leixing","选择");
					intent.putExtras(bundle_x);
					startActivity(intent);
					break;
				case R.id.button_tiankongti:
					Intent intent_tiankong=new Intent();
					intent_tiankong.setClass(Xuexi.this,Xuanze_luru.class);
					Bundle bundle_t=new Bundle();
					bundle_t.putString("leixing","填空");
					intent_tiankong.putExtras(bundle_t);
					startActivity(intent_tiankong);
					break;
				case R.id.button_luru_jie:
					Intent intent_lu_j;
					intent_lu_j=new Intent();
					intent_lu_j.setClass(Xuexi.this, Jieda_luru.class);
					startActivity(intent_lu_j);
					break;
				case R.id.button_jiedati:
					Intent intent_j;
					intent_j=new Intent();
					intent_j.setClass(Xuexi.this, Xuanze_luru.class);
					Bundle bundle_j=new Bundle();
					bundle_j.putString("leixing","解答");
					intent_j.putExtras(bundle_j);
					startActivity(intent_j);
					break;
				case R.id.button_luru:
					Intent intent_1;
					intent_1=new Intent();
					intent_1.setClass(Xuexi.this, activity_xuanze.class);
					startActivity(intent_1);
					break;
				case R.id.button_mima:
					Intent intent_mi=new Intent();
					intent_mi.setClass(Xuexi.this,Mima.class);
					startActivity(intent_mi);
					break;
				case R.id.button_deleteall:
					AlertDialog dialog_1 = null;
					AlertDialog.Builder builder_2 = null;
					builder_2=new AlertDialog.Builder(Xuexi.this);
					dialog_1=builder_2.setTitle("启奏")
							.setMessage("确定删除")
							.setNegativeButton("取消", new OnClickListener() {
								
								@Override
								public void onClick(DialogInterface arg0, int arg1) {
									// TODO Auto-generated method stub
									
								}
							})
							.setPositiveButton("确定", new OnClickListener() {
								
								@Override
								public void onClick(DialogInterface arg0, int arg1) {
									// TODO Auto-generated method stub
									db=helper.getWritableDatabase();
									db.delete("timu", null, null);
									db.delete("answer", null, null);
									db.delete("biji", null, null);
									deleteDatabase("xuexi");
									Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_SHORT).show();
								}
							}).create();
					dialog_1.show();
					break;
				case R.id.button_luru_tiankong:
					Intent intent_luru=new Intent();
					intent_luru.setClass(Xuexi.this, Tiankong_luru.class);
					startActivity(intent_luru);
					break;
				default:
					break;
				}
			}
		};
		mbutton_xuanze.setOnClickListener(listener);
		mbutton_luru.setOnClickListener(listener);
		mbutton_tiankong.setOnClickListener(listener);
		mbutton_jieda.setOnClickListener(listener);
		mbutton_mima.setOnClickListener(listener);
		mbutton_deleteall.setOnClickListener(listener);
		mbutton_luru_tiankong.setOnClickListener(listener);
		mbutton_luru_jie.setOnClickListener(listener);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		String Title="信息";
		String Massage="确定退出?";
		if(keyCode==KeyEvent.KEYCODE_BACK)
		{
			AlertDialog dialog=new AlertDialog.Builder(this).create();
			dialog.setTitle(Title);
			dialog.setMessage(Massage);
			dialog.setButton("退出", listener);
			dialog.setButton2("留下", listener);
			dialog.show();
		}
		return false;
	}
	DialogInterface.OnClickListener listener=new OnClickListener() {
		
		@Override
		public void onClick(DialogInterface arg0, int arg1) {
			// TODO Auto-generated method stub
			switch (arg1) {
			case AlertDialog.BUTTON_POSITIVE:
				finish();
				break;
			case AlertDialog.BUTTON_NEGATIVE:
				break;
			default:
				break;
			}
		}
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.xuexi, menu);
		return true;
	}

}
