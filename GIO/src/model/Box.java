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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getOrder_origin() {
		return order_origin;
	}

	public void setOrder_origin(int order_origin) {
		this.order_origin = order_origin;
	}
	
}
