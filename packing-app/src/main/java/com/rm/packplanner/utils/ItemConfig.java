package com.rm.packplanner.utils;

public class ItemConfig {

	private double length;

	private int quantity;

	private double weight;

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "ItemConfig [length=" + length + ", quantity=" + quantity + ", weight=" + weight + "]";
	}

}
