package com.rm.packplanner.repository;

import com.rm.packplanner.entities.Pack;
import com.rm.packplanner.utils.PackConfig;

public interface PackRepository {
	
	Pack createPack(PackConfig packConfig );

}
