package sun.haoxue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import javax.security.auth.PrivateCredentialPermission;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Biji extends Activity{
	TextView mtext_biji_return;
	TextView mtext_biji_title;
	TextView mtext_save;
	EditText medit_biji;
	ListView list_bi;
	ArrayList<Integer> list;
	ArrayList<Integer> list_1;
	ArrayList<HashMap<String, Object>> listitem;
	SQLiteDatabase db;
	Databaseh helper;
	Cursor c;
	int s;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.biji);
		init1();
		Bundle bundle=this.getIntent().getExtras();
		s=bundle.getInt("x");
//		c.moveToFirst();
//		Toast.makeText(getApplicationContext(),c.getInt(2)+"  "+c.getString(3), Toast.LENGTH_SHORT).show();
		if(c.getCount()!=0)
		{
			for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
			{
//				Toast.makeText(getApplicationContext(),s+"  "+c.getInt(2)+"  "+c.getString(3), Toast.LENGTH_SHORT).show();
				if(s==c.getInt(2))
				{
					HashMap<String, Object> map=new HashMap<String, Object>();
					map.put("neirong", c.getString(3));
					map.put("delete", ">");
					listitem.add(map);
					list.add(c.getInt(1));
					list_1.add(c.getInt(2));
//					Toast.makeText(getApplicationContext(),s+"  "+c.getInt(2)+"  "+c.getString(3)+"  "+listitem.size(), Toast.LENGTH_SHORT).show();
				}
				
			}
		}
		click_bi();
		String from[]={"neirong","delete"};
		int to[]={R.id.text_item_1,R.id.text_item_2};
		SimpleAdapter madapter=new SimpleAdapter(this,listitem, R.layout.list_bi, from, to);
		list_bi.setAdapter(madapter);
		list_bi.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent_b=new Intent();
				intent_b.setClass(Biji.this, Biji_j.class);
				Bundle bundle_b=new Bundle();
				bundle_b.putInt("index",list.get(arg2));
				intent_b.putExtras(bundle_b);
				startActivity(intent_b);
				finish();
			}
		});
	}
	private void click_bi() {
		// TODO Auto-generated method stub
		OnClickListener listener;
		listener=new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (v.getId()) {
				case R.id.text_biji_return:
					finish();
					break;
				case R.id.text_biji_save:
					String s_edit;
					s_edit=medit_biji.getText().toString();
					if(s_edit.equals(""))
						Toast.makeText(getApplicationContext(), "未输入答案", Toast.LENGTH_SHORT).show();
					else
					{
						int index=1;
						if(c.getCount()!=0)
						{
							c.moveToLast();
							index=c.getInt(1)+1;
						}
						db.execSQL("insert into biji(index2,tishu,neirong) values(?,?,?)",new Object[]{index,s,s_edit});
						Toast.makeText(getApplicationContext(), "已保存", Toast.LENGTH_SHORT).show();
						finish();
					}
					break;
				default:
					break;
				}
			}
		};
		mtext_biji_return.setOnClickListener(listener);
		mtext_biji_title.setOnClickListener(listener);
		medit_biji.setOnClickListener(listener);
		mtext_save.setOnClickListener(listener);
	}
	private void init1() {
		// TODO Auto-generated method stub
		mtext_biji_return=(TextView)findViewById(R.id.text_biji_return);
		mtext_biji_title=(TextView)findViewById(R.id.text_biji_title);
		mtext_save=(TextView)findViewById(R.id.text_biji_save);
		medit_biji=(EditText)findViewById(R.id.biji_edit);
		list_bi=(ListView)findViewById(R.id.list_bi);
		listitem=new ArrayList<HashMap<String,Object>>();
		list=new ArrayList<Integer>();
		list_1=new ArrayList<Integer>();
		helper=new Databaseh(this);
		db=helper.getWritableDatabase();
		c=db.query("biji", null, null, null, null, null, null);
	}

}
