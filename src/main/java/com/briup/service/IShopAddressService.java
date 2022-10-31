package com.briup.service;

import java.util.List;

import com.briup.bean.ShopAddress;

public interface IShopAddressService {
	//根据用户ID查找收货地址
	List<ShopAddress> findAddressByCustomerId(Integer id);
	//新增收货地址
	void saveAddress(ShopAddress sd);
	//根据address表id来查询一个地址信息
	ShopAddress findShopAddressById(Integer id);
}
