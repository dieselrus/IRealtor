package ru.dieselru.irealtor;

public class RealtyObject {
	  String name;
	  String cost;
	  String room;
	  String id;
	  //int image;
	  //boolean box;
	  

	  RealtyObject(String _describe, String _room, String _cost, String _id) {
	    name = _describe;
	    cost = _cost;
	    room = _room;
	    id = _id;
	    //box = _box;
	  }
}
