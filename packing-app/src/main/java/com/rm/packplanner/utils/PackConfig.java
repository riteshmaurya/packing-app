package com.rm.packplanner.utils;

import com.rm.packplanner.constants.PackPlannerConstants.SortOrder;

public class PackConfig {

	private SortOrder sortOrder;

	private int maxItems;

	private double maxWeight;

	/**
	 * @param sortOrder
	 * @param maxItems
	 * @param maxWeight
	 */
	public PackConfig(SortOrder sortOrder, int maxItems, double maxWeight) {
		super();
		this.sortOrder = sortOrder;
		this.maxItems = maxItems;
		this.maxWeight = maxWeight;
	}

	public SortOrder getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	public int getMaxItems() {
		return maxItems;
	}

	public void setMaxItems(int maxItems) {
		this.maxItems = maxItems;
	}

	public double getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(double maxWeight) {
		this.maxWeight = maxWeight;
	}

	@Override
	public String toString() {
		return "PackConfig [sortOrder=" + sortOrder + ", maxItems=" + maxItems + ", maxWeight=" + maxWeight + "]";
	}

}
