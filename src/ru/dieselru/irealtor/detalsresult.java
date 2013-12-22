package ru.dieselru.irealtor;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class detalsresult extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detalsviewrowlayout);
		
		// Использование собственного шаблона
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.detalsviewrowlayout, R.id.label, values);
        setListAdapter(adapter);
	}

	private void setListAdapter(ArrayAdapter<String> adapter) {
		// TODO Auto-generated method stub
		
	}

}
