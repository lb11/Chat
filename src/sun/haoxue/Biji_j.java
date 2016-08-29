package sun.haoxue;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Biji_j extends Activity{
	TextView mtext_back;
	TextView mtext_finish;
	TextView mtext_delete;
	TextView mtext_xiugai;
	EditText mdit;
	Databaseh helper;
	SQLiteDatabase db;
	Cursor c;
	
	int index;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.biji_b);
		Bundle bundle=this.getIntent().getExtras();
		index=bundle.getInt("index");
		init();
		mdit.setFocusable(false);
		for(c.moveToFirst();(c.getInt(1)!=index)&&!(c.isAfterLast());c.moveToNext())
		{
		}
		mdit.setText(c.getString(3));
		onclick();
	}
	private void onclick() {
		// TODO Auto-generated method stub
		OnClickListener listener=new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (v.getId()) {
				case R.id.text_biji_b_back:
					finish();
					break;
				case R.id.text_biji_b_delete:
					db.execSQL("delete from biji where index2=?",new Object[]{index});
					Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_SHORT).show();
					finish();
					break;
				case R.id.text_biji_b_finish:
					db.execSQL("update biji set neirong=? where index2=?",new Object[]{mdit.getText().toString(),index});
					Toast.makeText(getApplicationContext(), "已保存", Toast.LENGTH_SHORT).show();
					finish();
					break;
				case R.id.text_biji_b_xiugai:
					mdit.setFocusableInTouchMode(true);
					break;

				default:
					break;
				}
			}
		};
		mtext_back.setOnClickListener(listener);
		mtext_delete.setOnClickListener(listener);
		mtext_finish.setOnClickListener(listener);
		mtext_xiugai.setOnClickListener(listener);
	}
	private void init() {
		// TODO Auto-generated method stub
		mtext_back=(TextView)findViewById(R.id.text_biji_b_back);
		mtext_delete=(TextView)findViewById(R.id.text_biji_b_delete);
		mtext_finish=(TextView)findViewById(R.id.text_biji_b_finish);
		mtext_xiugai=(TextView)findViewById(R.id.text_biji_b_xiugai);
		mdit=(EditText)findViewById(R.id.edit_biji_b);
		helper=new Databaseh(this);
		db=helper.getWritableDatabase();
		c=db.query("biji", null, null, null, null, null, null);
	}

}
