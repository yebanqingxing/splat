/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sml.sz.order.dao.OrderDemoDao;
import com.sml.sz.order.entity.OrderDemo;
import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.service.CrudService;

/**
 * 示例功能Service
 * @author 黄诗源
 * @version 2016-03-06
 */
@Component("orderDemoFacade")
@Transactional(readOnly = true)
public class OrderDemoFacadeImpl extends CrudService<OrderDemoDao, OrderDemo> implements OrderDemoFacade{
	@Autowired
	OrderDemoDao orderDemoDao;
	
	public void save(String msg) {
		// TODO Auto-generated method stub
		System.out.println("测试——msg"+msg);
	}
	
}