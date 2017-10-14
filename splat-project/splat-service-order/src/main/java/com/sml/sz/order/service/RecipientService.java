/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.service.CrudService;
import com.sml.sz.order.entity.Recipient;
import com.sml.sz.order.entity.model.RecipientModel;
import com.sml.sz.sys.entity.User;
import com.sml.sz.sys.utils.UserUtils;
import com.sml.sz.order.dao.RecipientDao;

/**
 * 收件人Service
 * @author 李千超
 * @version 2016-03-30
 */
@Service(value="recipientServiceFacade")
@Transactional(readOnly = true)
public class RecipientService extends CrudService<RecipientDao, Recipient> implements RecipientServiceFacade{

	@Autowired
	RecipientDao recipientDao;
	/**
	 * 收件人 通过ID 获取
	 * @param String id
	 * @return Recipient
	 */
	public Recipient get(String id) {
		return super.get(id);
	}
	
	/**
	 * 收件人 查询不分页
	 * @param Recipient
	 * @return List<Recipient>
	 */
	public List<Recipient> findList(Recipient recipient) {
		return super.findList(recipient);
	}
	
	/**
	 * 收件人 查询分页
	 * @param Page<Recipient> page,Recipient
	 * @return Page<Recipient>
	 */
	public Page<Recipient> findPage(Page<Recipient> page, Recipient recipient) {
		return super.findPage(page, recipient);
	}
	
	/**
	 * 收件人 保存
	 * @param Recipient
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void save(Recipient recipient) {
		super.save(recipient);
	}
	
	/**
	 * 收件人 删除
	 * @param Recipient
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void delete(Recipient recipient) {
		super.delete(recipient);
	}

	/**
	 * 查询收件人 
	 */
	public List<Recipient> findRecipientList(String orderNo) {
		return recipientDao.findRecipientList(orderNo);
	}

	/**
	 * 添加收件人做个收件人
	 */
	@Transactional(readOnly = false)
	public void saveRecipient(RecipientModel recipient,String orderNo) {
		List<Recipient> recipientInfo=recipient.getRecipient();
		for (Recipient recipient2 : recipientInfo) {
			recipient2.setOrderNo(orderNo);
			//假如没有名字就不会添加
			if(null != recipient2.getRecipientName() && !"".equals(recipient2.getRecipientName()))
				super.save(recipient2);
		}
			
	}
	
}