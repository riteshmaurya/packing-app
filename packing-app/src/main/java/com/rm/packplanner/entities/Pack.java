package com.rm.packplanner.entities;

import java.util.Arrays;

import com.rm.packplanner.constants.PackPlannerConstants.SortOrder;
import com.rm.packplanner.utils.IdCreator;
import com.rm.packplanner.utils.PackConfig;

public class Pack {

	private Long packId;

	private SortOrder sortOrder;

	private double remainingWeight;

	private double packLength;

	private int maxSize;

	private Item[] itemArray;

	private int top;

	public Pack(PackConfig s) {
		this.packId = IdCreator.createID();
		this.sortOrder = s.getSortOrder();
		this.maxSize = s.getMaxItems();
		this.remainingWeight = Double.valueOf(s.getMaxWeight());
		this.itemArray = new Item[s.getMaxItems()];
		this.top = -1;
	}

	public void push(Item item) {
		itemArray[++top] = item;
		this.remainingWeight = this.remainingWeight - item.getWeight();
	}

	public Item pop() {
		this.remainingWeight = this.remainingWeight + itemArray[top].getWeight();
		return itemArray[top--];
	}

	public Item peek() {
		return itemArray[top];
	}

	public boolean isEmpty() {
		return (top == -1);
	}

	public boolean isFull() {
		return (top == maxSize - 1);
	}

	public boolean hasWeight() {
		return remainingWeight > 0;
	}

	public Item[] getItemArray() {
		return itemArray;
	}

	public Long getPackId() {
		return packId;
	}

	public void setPackId(Long packId) {
		this.packId = packId;
	}

	public SortOrder getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	public double getRemainingWeight() {
		return remainingWeight < 0 ? 0 : remainingWeight;
	}

	public void setRemainingWeight(double remainingWeight) {
		this.remainingWeight = remainingWeight;
	}

	public double getPackLength() {
		return packLength;
	}

	public void setPackLength(double packLength) {
		this.packLength = packLength;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((packId == null) ? 0 : packId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pack other = (Pack) obj;
		if (packId == null) {
			if (other.packId != null)
				return false;
		} else if (!packId.equals(other.packId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pack [packId=" + packId + ", sortOrder=" + sortOrder + ", remainingWeight=" + remainingWeight
				+ ", packLength=" + packLength + ", maxSize=" + maxSize + ", itemArray=" + Arrays.toString(itemArray)
				+ ", top=" + top + "]";
	}

}
