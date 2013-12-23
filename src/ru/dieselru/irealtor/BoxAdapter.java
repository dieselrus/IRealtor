package ru.dieselru.irealtor;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class BoxAdapter extends BaseAdapter {
	Context ctx;
	LayoutInflater lInflater;
	ArrayList<RealtyObject> objects;

	BoxAdapter(Context context, ArrayList<RealtyObject> RealtyObjects) {
		ctx = context;
		objects = RealtyObjects;
		lInflater = (LayoutInflater) ctx
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
			view = lInflater.inflate(R.layout.item, parent, false);
		}

		RealtyObject p = getRealtyObject(position);

		// ��������� View � ������ ������ ������� �� �������: ������������, ����
		// � ��������
		((TextView) view.findViewById(R.id.textObject)).setText(p.name);
		((TextView) view.findViewById(R.id.textRoom)).setText(p.room);
		((TextView) view.findViewById(R.id.textCost)).setText(p.cost);

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
	RealtyObject getRealtyObject(int position) {
		return ((RealtyObject) getItem(position));
	}

//	// ���������� �������
//	ArrayList<RealtyObject> getBox() {
//		ArrayList<RealtyObject> box = new ArrayList<RealtyObject>();
//		for (RealtyObject p : objects) {
//			// ���� � �������
//			if (p.box)
//				box.add(p);
//		}
//		return box;
//	}

	// ���������� ��� ���������
//	OnCheckedChangeListener myCheckChangList = new OnCheckedChangeListener() {
//		public void onCheckedChanged(CompoundButton buttonView,
//				boolean isChecked) {
//			// ������ ������ ������ (� ������� ��� ���)
//			//getRealtyObject((Integer) buttonView.getTag()).box = isChecked;
//		}
//	};
}