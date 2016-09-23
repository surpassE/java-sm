package com.sirding.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sirding.mybatis.mapper.AddresInfoMapper;
import com.sirding.mybatis.model.AddresInfo;
import com.sirding.service.AddresService;

@Service
public class AddresServiceImpl implements AddresService {

	@Autowired
	private AddresInfoMapper addresInfoMapper;
	
	@Override
	public int insertAddres(AddresInfo obj) {
		int i = -1;
		i = addresInfoMapper.insert(obj);
		if("error".equals(obj.getAddress())){
			String tmp = null;
			System.out.println(tmp.length());
		}
		return i;
	}

}
