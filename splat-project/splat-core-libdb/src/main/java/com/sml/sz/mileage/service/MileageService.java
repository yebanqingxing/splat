package com.sml.sz.mileage.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sml.sz.common.persistence.Page;
import com.sml.sz.mileage.entity.Mileage;

public interface MileageService {

	/**
	 * 里程 通过ID 获取
	 * @param String id
	 * @return Mileage
	 */
	public Mileage get(String id);
	
	/**
	 * 里程 查询不分页
	 * @param Mileage
	 * @return List<Mileage>
	 */
	public List<Mileage> findList(Mileage mileage);
	
	/**
	 * 里程 查询分页
	 * @param Page<Mileage> page,Mileage
	 * @return Page<Mileage>
	 */
	public Page<Mileage> findPage(Page<Mileage> page, Mileage mileage);
	
	/**
	 * 里程 保存
	 * @param Mileage
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void save(Mileage mileage);
	
	/**
	 * 里程 删除
	 * @param Mileage
	 * @return void
	 */
	@Transactional(readOnly = false)
	public void delete(Mileage mileage);
	
}
