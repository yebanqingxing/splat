/**
 * Copyright &copy; 2016 splat  All rights reserved.
 */
package com.sml.sz.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.common.service.CrudService;
import com.sml.sz.order.dao.TicketorderMessageDao;
import com.sml.sz.order.dao.TicketorderPnrDao;
import com.sml.sz.order.entity.TicketorderMessage;
import com.sml.sz.order.entity.TicketorderPnr;
import com.sun.org.glassfish.external.arc.Taxonomy;

/**
 * 机票订单pnrService
 * 
 * @author 李千超
 * @version 2016-03-10
 */
@Service(value = "ticketorderMessageServiceFacade")
public class TicketorderMessageService extends CrudService<TicketorderMessageDao, TicketorderMessage>
		implements TicketorderMessageServiceFacade {

	@Autowired
	TicketorderMessageDao TicketorderMessageDao;

	@Override
	public TicketorderMessage get(String id) {
		// TODO Auto-generated method stub
		return super.get(id);
	}

	@Override
	public TicketorderMessage get(TicketorderMessage entity) {
		// TODO Auto-generated method stub
		return super.get(entity);
	}

	@Override
	public List<TicketorderMessage> findList(TicketorderMessage entity) {
		// TODO Auto-generated method stub
		return super.findList(entity);
	}

	@Override
	public Page<TicketorderMessage> findPage(Page<TicketorderMessage> page, TicketorderMessage entity) {
		// TODO Auto-generated method stub
		return super.findPage(page, entity);
	}

	@Override
	@Transactional(readOnly = false)
	public void save(TicketorderMessage entity) {
		// TODO Auto-generated method stub
		super.save(entity);
	}

	@Override
	public void delete(TicketorderMessage entity) {
		// TODO Auto-generated method stub
		super.delete(entity);
	}

	@Override
	public void saveList(List<TicketorderMessage> list) {
		// TODO Auto-generated method stub
		super.saveList(list);
	}

	public List<TicketorderMessage> findTicketorderMessage(String order) {
		// TODO Auto-generated method stub
		return null;
	}

}