package sun.haoxue;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class activity_xuanze extends Activity{
	EditText medit_xuanze_ti;
	EditText medit_xuanxiang;
	Button mbutton_tianjia;
	TextView mtext_xuanze_back;
	TextView mtext_finish;
	Button mButton_xuanxiang_tijia;
	Button mbutton_right;
	LinearLayout linearlayout;
	TextView mtext_xianshi;
	Cursor c;
	String s="";
	
	int s_a=0,s_flag=0;
	ArrayList<String> list=new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xuanze_luru);
		init();//初始化
		click();//点击事件
	}
	
	public void init()
	{
		medit_xuanze_ti=(EditText)findViewById(R.id.edit_timu_xuanze);
		medit_xuanxiang=(EditText)findViewById(R.id.edit_answer_xuangxiang);
		mbutton_tianjia=(Button)findViewById(R.id.button_xuanze_tianjia);
		mtext_xuanze_back=(TextView)findViewById(R.id.text_xuanze_back);
		mtext_xianshi=(TextView)findViewById(R.id.text_xianshi);
		linearlayout=(LinearLayout)findViewById(R.id.linear_xuanze);
		mtext_finish=(TextView)findViewById(R.id.text_xuanze_finish);
		mbutton_right=(Button)findViewById(R.id.button_xuanze_right);
		mButton_xuanxiang_tijia=(Button)findViewById(R.id.button_xuanze_tianjia_xuan);
	}
	public void click()
	{
		final SQLiteDatabase db;
		Databaseh helper=new Databaseh(this);
		db=helper.getWritableDatabase();
		OnClickListener listener;
		listener=new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch(v.getId())
				{
				case R.id.button_xuanze_tianjia:
					s=medit_xuanze_ti.getText().toString();
					if(!s.equals(""))
					{
						mtext_xianshi.append("题目:"+s+"\n");
						medit_xuanze_ti.setText("");
					}
					else
					{
						Toast.makeText(getApplicationContext(), "题目不能为空", Toast.LENGTH_SHORT).show();
					}
					break;
				case R.id.text_xuanze_back:
					finish();
					break;
				case R.id.text_xuanze_finish:
					if(s_flag==0)
					{
						Toast.makeText(getApplicationContext(), "未设置正确答案",Toast.LENGTH_SHORT).show();
					}
					else if(s.equals(""))
						Toast.makeText(getApplicationContext(), "题目不能为空", Toast.LENGTH_SHORT).show();
					else
					{
						int length=list.size();
						int index=1;
						Cursor c=db.query("timu", null, null, null, null, null, null);
						if(c.getCount()!=0)
						{
							c.moveToLast();
							index=c.getInt(1)+1;
						}
						db.execSQL("insert into timu(index1,leixing,neirong,right,sum,shoucang) values(?,?,?,?,?,?)",new Object[]{index,"选择",s,0,0,0});
						for(int i=0;i<length;i++)
						{
							int a_right=-1;
							if(i==s_flag-1)
								a_right*=-1;
//							Toast.makeText(getApplicationContext(),""+a_right, Toast.LENGTH_SHORT).show();
							db.execSQL("insert into answer(tishu,neirong,right)values(?,?,?)",new Object[]{index,list.get(i),a_right});
						}
						Toast.makeText(getApplicationContext(), "录入成功", Toast.LENGTH_SHORT).show();
						finish();
					}
					break;
				case R.id.button_xuanze_right:
					if(medit_xuanxiang.getText().toString().equals(""))
					{
						Toast.makeText(getApplicationContext(), "先输入选项", Toast.LENGTH_SHORT).show();
					}
					else
					{
						
						s_flag=s_a+1;
					}
					break;
				case R.id.button_xuanze_tianjia_xuan:
					if(medit_xuanxiang.getText().toString().equals(""))
					{
						Toast.makeText(getApplicationContext(), "未输入选项", Toast.LENGTH_SHORT).show();
					}
					else
					{
						list.add(medit_xuanxiang.getText().toString());
						mtext_xianshi.append(medit_xuanxiang.getText().toString()+"\n");
						s_a++;
					}
					medit_xuanxiang.setText("");
					break;
				case R.id.button_luru_tiankong:
					Intent intent=new Intent();
					intent.setClass(activity_xuanze.this,Tiankong_luru.class);
					startActivity(intent);
					break;
				default:
					break;
				}
			}
		};
		mtext_xuanze_back.setOnClickListener(listener);
		mbutton_tianjia.setOnClickListener(listener);
		mtext_finish.setOnClickListener(listener);
		mbutton_right.setOnClickListener(listener);
		mButton_xuanxiang_tijia.setOnClickListener(listener);
	}

}
