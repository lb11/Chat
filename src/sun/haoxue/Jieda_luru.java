package sun.haoxue;

import java.util.ArrayList;

import android.app.Activity;
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

public class Jieda_luru extends Activity{
	EditText medit_tiankong_ti;
	EditText medit_kong;
	Button mbutton_tianjia;
	TextView mtext_tiankong_back;
	TextView mtext_finish;
	Button mButton_kong_tijia;
	Button mbutton_right;
	TextView mtext_title;
	LinearLayout linearlayout;
	TextView mtext_xianshi;
	ArrayList<String> list=new ArrayList<String>();
	Cursor c;
	Databaseh helper;
	SQLiteDatabase db;
	String s="";
	String s_a="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xuanze_luru);
		init();
		linearlayout.removeView(mbutton_right);
		mbutton_right.setText("添加答案");
		mtext_title.setText("解答题录入");
		onclick();
	}
	private void onclick() {
		// TODO Auto-generated method stub
OnClickListener listener=new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch(v.getId())
				{
				case R.id.text_xuanze_back:
					finish();
					break;
				case R.id.button_xuanze_tianjia:
					if(!(medit_tiankong_ti.getText().toString().equals("")))
					{
						s=medit_tiankong_ti.getText().toString();
						medit_tiankong_ti.setText("");
						mtext_xianshi.append(s);
						mtext_xianshi.append("\n");
//						Toast.makeText(getApplicationContext(),flag_kong+"  "+flag_c, Toast.LENGTH_SHORT).show();
					}
					else
					{
						Toast.makeText(getApplicationContext(), "题目不能为空", Toast.LENGTH_SHORT).show();
					}
					break;
				case R.id.button_xuanze_tianjia_xuan:
					if(!(medit_kong.getText().toString().equals("")))
					{
						s_a=medit_kong.getText().toString();
						medit_kong.setText("");
						mtext_xianshi.append(s_a);
						mtext_xianshi.append("\n");
					}
					else
						Toast.makeText(getApplicationContext(), "答案不能为空", Toast.LENGTH_SHORT).show();
					break;
				case R.id.text_xuanze_finish:
					if(s.equals(""))
					{
						Toast.makeText(getApplicationContext(), "未输入题目", Toast.LENGTH_SHORT).show();
					}
					else if(s_a.equals(""))
						Toast.makeText(getApplicationContext(), "未输入答案", Toast.LENGTH_SHORT).show();
					else
					{
						int index=1;
						Cursor c=db.query("timu", null, null, null, null, null, null);
						if(c.getCount()!=0)
						{
							c.moveToLast();
							index=c.getInt(1)+1;
						}
						db.execSQL("insert into timu(index1,leixing,neirong,right,sum,shoucang) values(?,?,?,?,?,?)",new Object[]{index,"解答",s,0,0,0});
						db.execSQL("insert into answer(tishu,neirong,right)values(?,?,?)",new Object[]{index,s_a,3});
						Toast.makeText(getApplicationContext(), "录入成功", Toast.LENGTH_SHORT).show();
						finish();
					}
					break;
				default:
					break;
				}
			}

		};
		mtext_tiankong_back.setOnClickListener(listener);
		mbutton_tianjia.setOnClickListener(listener);
		mButton_kong_tijia.setOnClickListener(listener);
		mtext_finish.setOnClickListener(listener);
	}
	private void init() {
		// TODO Auto-generated method stub
		medit_tiankong_ti=(EditText)findViewById(R.id.edit_timu_xuanze);
		medit_kong=(EditText)findViewById(R.id.edit_answer_xuangxiang);
		mbutton_tianjia=(Button)findViewById(R.id.button_xuanze_tianjia);
		mtext_tiankong_back=(TextView)findViewById(R.id.text_xuanze_back);
		mtext_xianshi=(TextView)findViewById(R.id.text_xianshi);
		linearlayout=(LinearLayout)findViewById(R.id.linearn_t);
		mtext_finish=(TextView)findViewById(R.id.text_xuanze_finish);
		mbutton_right=(Button)findViewById(R.id.button_xuanze_right);
		mtext_title=(TextView)findViewById(R.id.text_xuan_title);
		mButton_kong_tijia=(Button)findViewById(R.id.button_xuanze_tianjia_xuan);
		
		helper=new Databaseh(getApplicationContext());
		db=helper.getWritableDatabase();
	}

}
