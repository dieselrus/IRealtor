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

	// кол-во элементов
	@Override
	public int getCount() {
		return objects.size();
	}

	// элемент по позиции
	@Override
	public Object getItem(int position) {
		return objects.get(position);
	}

	// id по позиции
	@Override
	public long getItemId(int position) {
		return position;
	}

	// пункт списка
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// используем созданные, но не используемые view
		View view = convertView;
		if (view == null) {
			view = lInflater.inflate(R.layout.item_detalresult, parent, false);
		}

		DetalsResultObject p = getDetalsResultObject(position);

		// заполняем View в пункте списка данными из товаров: наименование, цена
		// и картинка
		((TextView) view.findViewById(R.id.textKey)).setText(p.key);
		((TextView) view.findViewById(R.id.textValue)).setText(p.value);

		//CheckBox cbBuy = (CheckBox) view.findViewById(R.id.cbBox);
		// присваиваем чекбоксу обработчик
		//cbBuy.setOnCheckedChangeListener(myCheckChangList);
		// пишем позицию
		//cbBuy.setTag(position);
		// заполняем данными из товаров: в корзине или нет
		//cbBuy.setChecked(p.box);
		return view;
	}

	// товар по позиции
	DetalsResultObject getDetalsResultObject(int position) {
		return ((DetalsResultObject) getItem(position));
	}
}