package com.rm.packplanner.repository.impl;

import java.util.ArrayList;
import java.util.List;

import com.rm.packplanner.entities.Item;
import com.rm.packplanner.entities.OrderMaster;
import com.rm.packplanner.repository.OrderRepository;
import com.rm.packplanner.utils.IdCreator;
import com.rm.packplanner.utils.ItemConfig;

public class OrderRepositroyImpl implements OrderRepository {

	@Override
	public OrderMaster generateOrder(List<ItemConfig> itemConfigs) {
		Long orderId = IdCreator.createID();
		List<Item> allItems = new ArrayList<>();
		for (ItemConfig itemConfig : itemConfigs) {
			for (int i = 0; i < itemConfig.getQuantity(); i++) {
				Item item = new Item(IdCreator.createID(), itemConfig.getLength(), 0, itemConfig.getWeight());
				allItems.add(item);
			}
		}
		return new OrderMaster(orderId, allItems);
	}

}
