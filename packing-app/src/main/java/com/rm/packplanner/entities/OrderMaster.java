package com.rm.packplanner.entities;

import java.util.List;

public class OrderMaster {

	private Long orderId;

	private List<Item> items;

	/**
	 * @param orderId
	 * @param items
	 */
	public OrderMaster(Long orderId, List<Item> items) {
		super();
		this.orderId = orderId;
		this.items = items;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "OrderMaster [orderId=" + orderId + ", items=" + items + "]";
	}

}
