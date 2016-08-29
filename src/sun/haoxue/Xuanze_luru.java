package sun.haoxue;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class Xuanze_luru extends Activity{
	ImageView mimage_1;
	ImageView mimage_2;
	TextView mtext_timu;
	TextView mtext;
	TextView mtext_return;
	TextView mtext_biji;
	TextView mtext_xianshi;
	TextView mtext_shoucang;
	TextView mtext_delete;
	TextView mtext_title;
	LinearLayout linear_1;
	SQLiteDatabase db;
	Databaseh helper;
	RadioGroup mradiogroup;
	ArrayList<String> list;
	Cursor c;
	Cursor c1;
	int shoucang=0;
	int bundle_int;
	String s_bundle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_xuexi);
		timu_zhuanhhuan();
		bundle_int=mbundle();
		mtext=(TextView)findViewById(R.id.text);
		click_h();
		xuanze();
	}
	
	
	private int mbundle() {
		// TODO Auto-generated method stub
		Bundle bundle=this.getIntent().getExtras();
		s_bundle=bundle.getString("leixing");
		if(s_bundle.equals("选择"))
		{
			mtext_title.setText("选择题");
			return 1;
		}
		else if(s_bundle.equals("填空"))
		{
			mtext_title.setText("填空题");
			return 2;
		}
		else
			mtext_title.setText("解答题");
		return 3;
	}


	@SuppressLint("NewApi")
	public void xuanze()
	{
		int count=0;
		int count_radio=0;
		final RadioButton radio[]=new RadioButton[10];
		mradiogroup.removeAllViewsInLayout();
		if(c.getCount()!=0&&bundle_int==1)
		{
			if(c.getString(2).equals("选择"))
			{
				mtext_timu.setText(c.getString(3));
			shoucang();
			for(c1.moveToFirst();!c1.isAfterLast();c1.moveToNext())
			{
//				Toast.makeText(getApplicationContext(),""+c1.getInt(3), Toast.LENGTH_SHORT).show();
				if(c1.getInt(1)==c.getInt(1))
				{
					radio[count]=new RadioButton(this);
					radio[count].setText(c1.getString(2));
					mradiogroup.addView(radio[count]);
					if(c1.getInt(3)==1)
						count_radio=count;
					count++;
				}
			}
			final int c_r=count_radio;
			final int c_1=count;
			mradiogroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@SuppressLint("InlinedApi")
				@Override
				public void onCheckedChanged(RadioGroup arg0, int arg1) {
					// TODO Auto-generated method stub
					radio[c_r].setTextColor(getResources().getColor(android.R.color.holo_green_light));
					for(int i=0;i<c_1;i++)
					{
						radio[i].setClickable(false);
						if(radio[i].isChecked()&&i!=c_r)
							radio[i].setTextColor(getResources().getColor(android.R.color.holo_red_light));
						else if(radio[i].isChecked()&&i==c_r)
						{
							db.execSQL("update timu set right=? where index1=?",new Object[]{c.getInt(4)+1,c.getInt(1)});
						}
					}
					db.execSQL("update timu set sum=? where index1=?",new Object[]{c.getInt(5)+1,c.getInt(1)});
				}
				
			});
			onright();
		}
		else
		{
			if(!c.isLast())
			{
				c.moveToNext();
				xuanze();
			}
		}
		}
		else if(c.getCount()!=0&&bundle_int==2)
		{
			linear_1.removeAllViews();
			linear_1.addView(mtext_timu);
			final EditText edit[]=new EditText[20];
			if(c.getString(2).equals("填空"))
			{
				int flag_kong=0;
				shoucang();
				String s_ti="",s_ti_1;
				s_ti_1=c.getString(3);
				for(int i=0;i<s_ti_1.length();i++)
				{
					char a_ti=s_ti_1.charAt(i);
					if(a_ti==' ')
					{
						s_ti+="______";
						flag_kong++;
					}
					else
						s_ti+=a_ti;
				}
				final int flag_kong1=flag_kong;
				mtext_timu.setText(s_ti);
				for(int i=0;i<flag_kong;i++)
				{
					edit[i]=new EditText(this);
					linear_1.addView(edit[i]);
				}
				Button button_sure=new Button(this);
				button_sure.setText("确定");
				button_sure.setBackground(getResources().getDrawable(android.R.color.white));
				button_sure.setTextColor(getResources().getColor(android.R.color.secondary_text_light));
				
				for(c1.moveToFirst();!c1.isAfterLast();c1.moveToNext())
				{
					if(c1.getInt(3)==2)
						list.add(c1.getString(2));
				}
				
				button_sure.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						int count_1=0;
						for(int i=0;i<flag_kong1;i++)
						{
							edit[i].setFocusable(false);
							if(edit[i].getText().toString().equals(list.get(i)))
							{
								edit[i].setTextColor(getResources().getColor(android.R.color.holo_green_light));
								count_1++;
							}
							else
								edit[i].setTextColor(getResources().getColor(android.R.color.holo_red_light));
						}
						if(count_1==flag_kong1)
							db.execSQL("update timu set right=? where index1=?",new Object[]{c.getInt(4)+1,c.getInt(1)});
						db.execSQL("update timu set sum=? where index1=?",new Object[]{c.getInt(5)+1,c.getInt(1)});
					}
				});
				linear_1.addView(button_sure);
				onright();
			}
			else
			{
				if(!c.isLast())
				{
					c.moveToNext();
					xuanze();
				}
			}
			
		}
		else if(c.getCount()!=0&&bundle_int==3)
		{
			linear_1.removeAllViews();
			linear_1.addView(mtext_timu);
			if(c.getString(2).equals("解答"))
			{
				shoucang();
				String s_ti = null,s_ti_1;
				s_ti_1=c.getString(3);
				mtext_timu.setText(s_ti_1);
				final EditText edit_j=new EditText(this);
				Button button_j=new Button(this);
				button_j.setText("显示答案");
				final TextView text_j=new TextView(this);
				edit_j.setBackground(getResources().getDrawable(android.R.drawable.alert_light_frame));
				linear_1.addView(edit_j);
				linear_1.addView(button_j);
				linear_1.addView(text_j);
				
				for(c1.moveToFirst();!c1.isAfterLast();c1.moveToNext())
				{
					if(c1.getInt(1)==(c.getInt(1)))
						s_ti=c1.getString(2);
				}
				final String s_f=s_ti;
				button_j.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						edit_j.setFocusable(false);	
						text_j.setText(s_f);
						text_j.setTextColor(getResources().getColor(android.R.color.holo_green_light));
					}
				});
				onright();
			}
			else
			{
				if(!c.isLast())
				{
					c.moveToNext();
					xuanze();
				}
			}
		}
//		Toast.makeText(getApplicationContext(),count+"",Toast.LENGTH_SHORT).show();
	}
	
	@SuppressLint("InlinedApi")
	private void onright() {
		// TODO Auto-generated method stub
		int flag_right=0,position;
		position=c.getPosition();
		db.close();
		db=helper.getWritableDatabase();
		c=db.query("timu", null, null, null, null, null, null);
		c.moveToPosition(position);
		if(c.getInt(5)!=0)
			flag_right=100*c.getInt(4)/c.getInt(5);
		mtext_xianshi.setText("正确率:"+flag_right+"%");
		if(flag_right<=30)
			mtext_xianshi.setTextColor(getResources().getColor(android.R.color.holo_red_light));
		else if(flag_right<=70)
			mtext_xianshi.setTextColor(getResources().getColor(android.R.color.holo_orange_light));
		else
			mtext_xianshi.setTextColor(getResources().getColor(android.R.color.holo_green_light));
	}


	@SuppressLint("InlinedApi")
	private void shoucang() {
		// TODO Auto-generated method stub
		String s_shoucang;
		if(c.getInt(6)==0)
		{
			s_shoucang="收藏";
			mtext_shoucang.setTextColor(getResources().getColor(android.R.color.black));
		}
		else
		{
			s_shoucang="已收藏";
			mtext_shoucang.setTextColor(getResources().getColor(android.R.color.holo_orange_dark));
		}
		mtext_shoucang.setText(s_shoucang);
	}


	public void timu_zhuanhhuan()
	{
		mimage_1=(ImageView)findViewById(R.id.image_zuo);
		mimage_2=(ImageView)findViewById(R.id.image_you);
		mtext_return=(TextView)findViewById(R.id.text_return);
		mtext_biji=(TextView)findViewById(R.id.text_night);
		mtext_delete=(TextView)findViewById(R.id.text_xuanze_delete);
		mtext_shoucang=(TextView)findViewById(R.id.text_xuanze_shoucang);
		mtext_xianshi=(TextView)findViewById(R.id.text_xuanze_xianshi);
		mtext_title=(TextView)findViewById(R.id.text_title);
		mradiogroup=(RadioGroup)findViewById(R.id.radiogroup_xuanze);
		mtext_timu=(TextView)findViewById(R.id.text_xuanze_timu);
		linear_1=(LinearLayout)findViewById(R.id.linear_timu);
		list=new ArrayList<String>();
		helper=new Databaseh(getApplicationContext());
		db=helper.getWritableDatabase();
		c=db.query("timu", null, null, null, null, null, null);
		c1=db.query("answer", null, null, null, null, null, null);
		c.moveToFirst();
//		Toast.makeText(getApplicationContext(), c.getString(3), Toast.LENGTH_SHORT).show();
	}
	
	public void click_h()
	{
		android.view.View.OnClickListener listener;
		listener=new android.view.View.OnClickListener() {
			
			@SuppressLint("InlinedApi")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (v.getId()) {
				case R.id.image_zuo:
					while(!c.isFirst())
					{
						c.moveToPrevious();
//						Toast.makeText(getApplicationContext(), c.getString(2), Toast.LENGTH_SHORT).show();
						if(c.getString(2).equals(s_bundle))
							break;
					}
					xuanze();
					break;
				case R.id.image_you:
					while(!c.isLast())
					{
						c.moveToNext();
//						Toast.makeText(getApplicationContext(), c.getString(2), Toast.LENGTH_SHORT).show();
						if(c.getString(2).equals(s_bundle))
							break;
					}
					xuanze();
					break;
				case R.id.text_return:
					db.close();
					finish();
					break;
				case R.id.text_night:
					Intent intent;
					intent=new Intent();
					intent.setClass(Xuanze_luru.this,Biji.class);
					Bundle bundle_biji=new Bundle();
					if(c.getCount()!=0&&s_bundle.equals(c.getString(2)))
						bundle_biji.putInt("x",c.getInt(1));
					else
						bundle_biji.putInt("x",0);
					intent.putExtras(bundle_biji);
					startActivity(intent);
					break;
				case R.id.text_xuanze_delete:
					db.execSQL("delete from timu where index1=?",new Object[]{c.getInt(1)});
					db.execSQL("delete from answer where tishu=?",new Object[]{c.getInt(1)});
					db.execSQL("delete from biji where tishu=?",new Object[]{c.getInt(1)});
					db.close();
					db=helper.getWritableDatabase();
					c=db.query("timu", null, null, null, null, null, null);
					if(!c.isLast())
						c.moveToNext();
					else
						c.moveToFirst();
					xuanze();
					Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_SHORT).show();
					break;
				case R.id.text_xuanze_shoucang:
					int position=c.getPosition();
					if(c.getInt(6)==0)
					{
						db.execSQL("update timu set shoucang=? where index1=?",new Object[]{1,c.getInt(1)});
						mtext_shoucang.setText("已收藏");
						mtext_shoucang.setTextColor(getResources().getColor(android.R.color.holo_orange_dark));
					}
					else
					{
						db.execSQL("update timu set shoucang=? where index1=?",new Object[]{0,c.getInt(1)});
						mtext_shoucang.setText("收藏");
						mtext_shoucang.setTextColor(getResources().getColor(android.R.color.black));
					}
					db.close();
					db=helper.getWritableDatabase();
					c=db.query("timu", null, null, null, null, null, null);
					c.moveToPosition(position);
					break;
				default:
					break;
				}
			}
		};
		mimage_1.setOnClickListener(listener);
		mimage_2.setOnClickListener(listener);
		mtext_return.setOnClickListener(listener);
		mtext_biji.setOnClickListener(listener);
		mtext_delete.setOnClickListener(listener);
		mtext_shoucang.setOnClickListener(listener);
	}

}
