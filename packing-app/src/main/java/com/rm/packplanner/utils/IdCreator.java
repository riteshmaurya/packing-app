package com.rm.packplanner.utils;

public class IdCreator {

	private static long idCounter = 1001;

	public static synchronized Long createID() {
		
		return idCounter++;
	}
}
