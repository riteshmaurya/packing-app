package com.rm.packplanner.repository.impl;

import org.springframework.stereotype.Repository;

import com.rm.packplanner.entities.Pack;
import com.rm.packplanner.repository.PackRepository;
import com.rm.packplanner.utils.PackConfig;

@Repository
public class PackRepositoryImpl implements PackRepository {

	@Override
	public Pack createPack(PackConfig packConfig) {
		return new Pack(packConfig);
	}

}
