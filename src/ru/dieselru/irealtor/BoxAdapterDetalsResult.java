package ru.dieselru.irealtor;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BoxAdapterDetalsResult extends BaseAdapter {
	Context ctx;
	LayoutInflater lInflater;
	ArrayList<DetalsResultObject> objects;

	BoxAdapterDetalsResult(Context context, ArrayList<DetalsResultObject> detalsResultObject) {
		ctx = context;
		objects = detalsResultObject;
		lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	// ���-�� ���������
	@Override
	public int getCount() {
		return objects.size();
	}

	// ������� �� �������
	@Override
	public Object getItem(int position) {
		return objects.get(position);
	}

	// id �� �������
	@Override
	public long getItemId(int position) {
		return position;
	}

	// ����� ������
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// ���������� ���������, �� �� ������������ view
		View view = convertView;
		if (view == null) {
			view = lInflater.inflate(R.layout.item_detalresult, parent, false);
		}

		DetalsResultObject p = getDetalsResultObject(position);

		// ��������� View � ������ ������ ������� �� �������: ������������, ����
		// � ��������
		((TextView) view.findViewById(R.id.textKey)).setText(p.key);
		((TextView) view.findViewById(R.id.textValue)).setText(p.value);

		//CheckBox cbBuy = (CheckBox) view.findViewById(R.id.cbBox);
		// ����������� �������� ����������
		//cbBuy.setOnCheckedChangeListener(myCheckChangList);
		// ����� �������
		//cbBuy.setTag(position);
		// ��������� ������� �� �������: � ������� ��� ���
		//cbBuy.setChecked(p.box);
		return view;
	}

	// ����� �� �������
	DetalsResultObject getDetalsResultObject(int position) {
		return ((DetalsResultObject) getItem(position));
	}
}