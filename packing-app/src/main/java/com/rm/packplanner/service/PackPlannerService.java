package com.rm.packplanner.service;

import java.util.List;

import com.rm.packplanner.entities.OrderMaster;
import com.rm.packplanner.entities.Pack;
import com.rm.packplanner.utils.PackConfig;

public interface PackPlannerService {

	List<Pack> packingItemsBySortOrder(OrderMaster orderMaster, PackConfig packConfig);

}
