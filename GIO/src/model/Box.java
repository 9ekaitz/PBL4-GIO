package model;

public class Box {
	private String date;
	private int id, weight, order_origin;
	
	public Box(int id, String date, int weight, int order_origin) {
		this.id = id;
		this.date=date;
		this.weight=weight;
		this.order_origin=order_origin;
	}
	
}
