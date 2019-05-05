package com.rm.packplanner.repository;

import java.util.List;

import com.rm.packplanner.entities.OrderMaster;
import com.rm.packplanner.utils.ItemConfig;

public interface OrderRepository {

	OrderMaster generateOrder(List<ItemConfig> itemConfigs);

}
