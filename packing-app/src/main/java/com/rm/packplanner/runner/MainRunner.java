package com.rm.packplanner.runner;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.rm.packplanner.constants.PackPlannerConstants.SortOrder;
import com.rm.packplanner.entities.Item;
import com.rm.packplanner.entities.OrderMaster;
import com.rm.packplanner.repository.OrderRepository;
import com.rm.packplanner.repository.impl.OrderRepositroyImpl;
import com.rm.packplanner.service.PackPlannerService;
import com.rm.packplanner.service.impl.PackPlannerServiceImpl;
import com.rm.packplanner.utils.ItemConfig;
import com.rm.packplanner.utils.PackConfig;

public class MainRunner {
	static OrderRepository orderRepository = new OrderRepositroyImpl();
	static PackConfig packConfig = null;
	static OrderMaster orderMaster = null;
	private PackPlannerService packPlannerService = new PackPlannerServiceImpl();

	public void packItems() {
		packPlannerService.packingItemsBySortOrder(orderMaster, packConfig);
	}

	public static void main(String[] args) {

		parseInputForPack();

		MainRunner m1 = new MainRunner();
		m1.packItems();
	}

	private static void parseInputForPack() {
		String sortOrder, maxPiecesPerPack, maxWeightPerPack;
		Scanner sc = new Scanner(System.in);

		System.out.println("Sort order input text options: NATURAL ,  SHORT_TO_LONG , LONG_TO_SHORT ");
		System.out.println(
				"Enter pack specification in this order: [Sort order],[max pieces per pack],[max weight per pack] ");
		String input = sc.nextLine();
		System.out.println(input);
		sortOrder = input.split(",")[0].trim();
		maxPiecesPerPack = input.split(",")[1].trim();
		maxWeightPerPack = input.split(",")[2].trim();

		// create pack config

		packConfig = new PackConfig(SortOrder.valueOf(sortOrder.toUpperCase()), Integer.valueOf(maxPiecesPerPack),
				Double.valueOf(maxWeightPerPack));

		System.out.println(packConfig);

		// create list of items for packing
		List<ItemConfig> itemConfig = new ArrayList<>();
		parseInputForItems(itemConfig);

		orderMaster = orderRepository.generateOrder(itemConfig);

		System.out.println(orderMaster);

		for (Item item : orderMaster.getItems()) {
			System.out.println(item);
		}

		sc.close();
	}

	private static List<ItemConfig> parseInputForItems(List<ItemConfig> itemConfigs) {
		System.out.println("Enter Items in this order:: itemLength, itemQuantity, pieceWeight,");
		String itemLength, itemQuantity, pieceWeight;
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		System.out.println(input);

		itemLength = input.split(",")[0].trim();
		itemQuantity = input.split(",")[1].trim();
		pieceWeight = input.split(",")[2].trim();

		// orderMaster

		ItemConfig itemConfig = new ItemConfig();
		itemConfig.setLength(Double.valueOf(itemLength));
		itemConfig.setQuantity(Integer.valueOf(itemQuantity));
		itemConfig.setWeight(Double.valueOf(pieceWeight));

		if (!(itemConfig.getWeight() > Double.valueOf(packConfig.getMaxWeight())))
			itemConfigs.add(itemConfig);
		else
			System.out.println("Weigth of each item should be less than weigh of Pack. This item is rejected");

		System.out.println(itemConfig);
		System.out.println("Do you want to add more items? Enter Y or N: ");

		String flag = sc.next();
		if (("Y").equalsIgnoreCase(flag)) {
			parseInputForItems(itemConfigs);
		}
		sc.close();
		return itemConfigs;
	}

}
