package com.example.myactivityresult;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
			.add(R.id.container, new MyFragment()).commit();
			getFragmentManager().beginTransaction()
			.add(R.id.container, new MyFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
	// Fragment
	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
	public static class MyFragment extends Fragment {

		public MyFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// ビュー生成
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			
			// イベント設定
			Button button = (Button)rootView.findViewById(R.id.button1);
			button.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(getActivity(), SubActivity.class);
					startActivityForResult(intent, 1);
				}
			});

			// 返す
			return rootView;
		}

		@Override
		public void onActivityResult(int requestCode, int resultCode,
				Intent data) {
			if(requestCode == 1){
				TextView textView = (TextView)getView().findViewById(R.id.textView1);
				if(resultCode == RESULT_OK){
					textView.setText(data.getAction());
				}
				else if(resultCode == RESULT_CANCELED){
					textView.setText("CANCELED");
				}
			}
			// TODO Auto-generated method stub
			super.onActivityResult(requestCode, resultCode, data);
		}
		
	}
}
