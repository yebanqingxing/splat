package com.sml.sz.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.order.entity.Recipient;
import com.sml.sz.order.entity.model.RecipientModel;

/**
 * 收件人信息
 * 
 * @author lqc
 *
 */
public interface RecipientServiceFacade {
	/**
	 * 收件人 通过ID 获取
	 * @param String id
	 * @return Recipient
	 */
	public Recipient get(String id);

	/**
	 * 收件人 查询不分页
	 * @param Recipient
	 * @return List<Recipient>
	 */
	public List<Recipient> findList(Recipient recipient);

	/**
	 * 收件人 查询分页
	 * @param Page<Recipient> page,Recipient
	 * @return Page<Recipient>
	 */
	public Page<Recipient> findPage(Page<Recipient> page, Recipient recipient);

	/**
	 * 收件人 保存
	 * @param Recipient
	 * @return void
	 */
	public void save(Recipient recipient);

	/**
	 * 收件人 删除
	 * @param Recipient
	 * @return void
	 */
	public void delete(Recipient recipient);
	
	/**
	 * 查询收件人
	 * @param orderNo
	 * @return
	 */
	public List<Recipient> findRecipientList(String orderNo);

	/**
	 * 添加收件人
	 * @param recipient
	 * @param orderNum 
	 */
	public void saveRecipient(RecipientModel recipient, String orderNum);
}
