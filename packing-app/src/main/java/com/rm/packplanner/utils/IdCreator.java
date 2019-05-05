package com.rm.packplanner.utils;

public class IdCreator<T> {
	
	private static long idCounter = 1001;
//	private T t;
//	private static String prefix;
//	{
//		if(t instanceof Item){
//			prefix = "IT";
//		}else if(t instanceof Pack){
//			prefix = "PA";
//		}else if(t instanceof OrderMaster ){
//			prefix = "OM";
//		}
//	}
	public static synchronized Long createID()
	{
		
	    return idCounter++;
	}
}
