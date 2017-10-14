package com.sml.sz.continent.service;

import java.util.List;


import com.sml.sz.common.persistence.Page;
import com.sml.sz.continent.entity.Continent;

public interface ContinentService {

	
		/**
		 * 大区 通过ID 获取
		 * @param String id
		 * @return Continent
		 */
		public Continent get(String id);
		
		/**
		 * 大区 查询不分页
		 * @param Continent
		 * @return List<Continent>
		 */
		public List<Continent> findList(Continent continent);
		
		/**
		 * 大区 查询分页
		 * @param Page<Continent> page,Continent
		 * @return Page<Continent>
		 */
		public Page<Continent> findPage(Page<Continent> page, Continent continent);
		
		/**
		 * 大区 保存
		 * @param Continent
		 * @return void
		 */
		public void save(Continent continent);
		
		/**
		 * 大区 删除
		 * @param Continent
		 * @return void
		 */
		public void delete(Continent continent);
}
