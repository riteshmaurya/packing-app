package com.rm.packplanner.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.rm.packplanner.constants.PackPlannerConstants.SortOrder;
import com.rm.packplanner.entities.Item;
import com.rm.packplanner.entities.OrderMaster;
import com.rm.packplanner.entities.Pack;
import com.rm.packplanner.service.PackPlannerService;
import com.rm.packplanner.utils.PackConfig;

public class PackPlannerServiceImpl implements PackPlannerService{
	

	

	@Override
	public List<Pack> packingItemsBySortOrder(OrderMaster orderMaster, PackConfig packConfig) {
		List<Pack> packs= new ArrayList<>();
		
			if(!packConfig.getSortOrder().equals(SortOrder.NATURAL)){
				Comparator<Item> SHORT_TO_LONG = (s1, s2)->{
					return (int) (s1.getLength() - s2.getLength());
					};
					
				Comparator<Item> LONG_TO_SHORT = (s1, s2)->{
					return (int) (s2.getLength() - s1.getLength());
					};
				List<Item> sortedItems = orderMaster.getItems()
						.stream()
						.sorted(packConfig.getSortOrder().equals(SortOrder.SHORT_TO_LONG) ? SHORT_TO_LONG : LONG_TO_SHORT)
						.collect(Collectors.toList());
				orderMaster.setItems(sortedItems);
			}

		//create start filling packs based on number of items from packConfig and maxweight from packConfig
		while(orderMaster.getItems().size()>0){
			//create pack
			Pack pack = new Pack(packConfig);
			//fill length wise sorted items
			int i = 0;
			while(!pack.isFull() && orderMaster.getItems().size()>0 ){
				if(pack.getRemainingWeight() >= orderMaster.getItems().get(0).getWeight()){
					pack.push(orderMaster.getItems().get(0));
					orderMaster.getItems().remove(0);
					i++;
					//System.out.println("cycle " +i);					
				}else
					break;

			}
			Item maxLengthItem = Arrays
					.stream(pack.getItemArray())
					.max((s1,s2)->{return (int) (s1.getLength() - ((s2!=null)? s2.getLength():0));})
					.orElseThrow(NoSuchElementException::new);
			
			pack.setPackLength(maxLengthItem.getLength());
			//System.out.println(pack);
			packs.add(pack);
		}
		packs.stream().forEach(System.out::println);		
		return packs;
	}
}
