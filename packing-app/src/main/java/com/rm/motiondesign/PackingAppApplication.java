package com.rm.motiondesign;

import com.rm.packplanner.service.PackPlannerService;
import com.rm.packplanner.service.impl.PackPlannerServiceImpl;

public class PackingAppApplication {

	private static PackPlannerService packPlannerService = new PackPlannerServiceImpl();

	public static void main(String[] args) {

		packPlannerService.parseInputForPack();
		packItems();
	}

	public static void packItems() {
		packPlannerService.packingItemsBySortOrder(packPlannerService.getOrderMaster(),
				packPlannerService.getPackConfig());
	}

}
