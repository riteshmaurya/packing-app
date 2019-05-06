package com.rm.packplanner.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rm.packplanner.constants.PackPlannerConstants.SortOrder;
import com.rm.packplanner.entities.Item;
import com.rm.packplanner.entities.OrderMaster;
import com.rm.packplanner.entities.Pack;
import com.rm.packplanner.repository.OrderRepository;
import com.rm.packplanner.service.PackPlannerService;
import com.rm.packplanner.utils.ItemConfig;
import com.rm.packplanner.utils.PackConfig;

@Service
public class PackPlannerServiceImpl implements PackPlannerService {

	@Autowired
	private OrderRepository orderRepository;

	private PackConfig packConfig;

	private OrderMaster orderMaster;

	@Override
	public List<Pack> packingItemsBySortOrder(OrderMaster orderMaster, PackConfig packConfig) {
		List<Pack> packs = new ArrayList<>();

		if (!packConfig.getSortOrder().equals(SortOrder.NATURAL)) {
			Comparator<Item> SHORT_TO_LONG = (s1, s2) -> {
				return (int) (s1.getLength() - s2.getLength());
			};

			Comparator<Item> LONG_TO_SHORT = (s1, s2) -> {
				return (int) (s2.getLength() - s1.getLength());
			};
			List<Item> sortedItems = orderMaster.getItems().stream()
					.sorted(packConfig.getSortOrder().equals(SortOrder.SHORT_TO_LONG) ? SHORT_TO_LONG : LONG_TO_SHORT)
					.collect(Collectors.toList());
			orderMaster.setItems(sortedItems);
		}

		// create start filling packs based on number of items from packConfig
		// and maxweight from packConfig
		while (orderMaster != null && orderMaster.getItems().size() > 0) {
			// create pack
			Pack pack = new Pack(packConfig);
			// fill length wise sorted items
			// int i = 0;
			while (!pack.isFull() && orderMaster.getItems().size() > 0) {
				if (pack.getRemainingWeight() >= orderMaster.getItems().get(0).getWeight()) {
					pack.push(orderMaster.getItems().get(0));
					orderMaster.getItems().remove(0);
					// i++;
					// System.out.println("cycle " +i);
				} else
					break;

			}
			Item maxLengthItem = Arrays.stream(pack.getItemArray()).max((s1, s2) -> {
				return (int) (s1.getLength() - ((s2 != null) ? s2.getLength() : 0));
			}).orElseThrow(NoSuchElementException::new);

			pack.setPackLength(maxLengthItem.getLength());
			// System.out.println(pack);
			packs.add(pack);
		}
		packs.stream().forEach(System.out::println);
		return packs;
	}

	@Override
	public void parseInputForPack() {
		String sortOrder, maxPiecesPerPack, maxWeightPerPack;
		Scanner sc = new Scanner(System.in);

		System.out.println("Sort order input text options: NATURAL ,  SHORT_TO_LONG , LONG_TO_SHORT ");
		System.out.println(
				"Enter pack specification in this order: [Sort order],[max pieces per pack],[max weight per pack] ");
		String input = sc.nextLine();
		System.out.println(input);
		try {
			sortOrder = input.split(",")[0].trim();
			maxPiecesPerPack = input.split(",")[1].trim();
			maxWeightPerPack = input.split(",")[2].trim();
			// create pack config
			packConfig = new PackConfig(SortOrder.valueOf(sortOrder.toUpperCase()), Integer.valueOf(maxPiecesPerPack),
					Double.valueOf(maxWeightPerPack));
		} catch (Exception e) {
			System.err.println("Input not valid. please correct and re enter!");
			parseInputForPack();
		}

		System.out.println(packConfig);

		// create list of items for packing
		List<ItemConfig> itemConfig = new ArrayList<>();
		parseInputForItems(itemConfig, packConfig);
		if (itemConfig.size() > 0) {
			orderMaster = orderRepository.generateOrder(itemConfig);

			System.out.println(orderMaster);

			orderMaster.getItems().stream().forEach(System.out::println);

		}

		sc.close();
	}

	@Override
	public List<ItemConfig> parseInputForItems(List<ItemConfig> itemConfigs, PackConfig packConfig) {
		System.out.println("Enter Items in this order:: itemLength, itemQuantity, pieceWeight,");
		String itemLength, itemQuantity, pieceWeight;
		Scanner sc = new Scanner(System.in);
		String input = sc.hasNextLine() ? sc.nextLine() : "@@@@@";
		System.out.println(input);
		ItemConfig itemConfig = new ItemConfig();
		try {
			itemLength = input.split(",")[0].trim();
			itemQuantity = input.split(",")[1].trim();
			pieceWeight = input.split(",")[2].trim();

			itemConfig.setLength(Double.valueOf(itemLength));
			itemConfig.setQuantity(Integer.valueOf(itemQuantity));
			itemConfig.setWeight(Double.valueOf(pieceWeight));
		} catch (Exception ex) {
			System.err.println("Input not valid. please correct and re enter!");
			parseInputForItems(itemConfigs, packConfig);
		}

		if (!(itemConfig.getWeight() > Double.valueOf(packConfig.getMaxWeight()))) {
			itemConfigs.add(itemConfig);
			System.out.println(itemConfig);
		} else
			System.err.println("Weigth of an item should be less than max weight of Pack. This item is rejected");

		System.out.println("Do you want to add more items? Enter Y or N: ");

		String flag = sc.hasNext() ? sc.next() : "no";
		if (("Y").equalsIgnoreCase(flag)) {
			parseInputForItems(itemConfigs, packConfig);
		}
		sc.close();
		return itemConfigs;
	}

	public PackConfig getPackConfig() {
		return packConfig;
	}

	public OrderMaster getOrderMaster() {
		return orderMaster;
	}

	public void packItems(PackPlannerService packPlannerService) {
		packPlannerService.packingItemsBySortOrder(packPlannerService.getOrderMaster(),
				packPlannerService.getPackConfig());
	}
}
