package com.sml.sz.common.thread;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sml.sz.common.jdbc.JdbcTemplate;
import com.sml.sz.sys.utils.excel.XLSXCovertCSVReader;

/**
 * office表初始化类
 * @author yues
 * <p>判断office对应的数据库表是否存在,不存在则创建对应的表,并加状态更新到office序列</p>
 */
public class OfficeTabOperate{
	
	//Office
	protected String office;
	
	public OfficeTabOperate(String office){
		this.office = office.trim().toUpperCase();
	}
	
	/**
	 * 判断office表是否存在(tb_policy_rules_bjs119)
	 * @return
	 */
	public boolean officeExistsCheck(){
		
		//office序列获取office信息
		Boolean exists = XLSXCovertCSVReader.officeExists.get(this.office);
		
		//office序列不存在,但数据库存在,则直接添加
		if(null == exists){
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;

			//获取splat库下所有表名
			String sql = "show tables like ?;";
			try {
				
				//封装连接,填充查询信息
				conn = JdbcTemplate.getConnInstance();
				ps = conn.prepareStatement(sql);
				ps.setString(1, "tb_policy_rules_" + this.office);
				
				//执行表名查询
				rs = ps.executeQuery();
				
				exists = rs.next();

				//根据查询结果,封装office存在状态
				XLSXCovertCSVReader.officeExists.put(this.office, exists);
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				JdbcTemplate.closeCon(conn, ps, rs);
			}	
		}
		
		System.out.println(office + "表已经存在状态：" + exists);
		return exists;
	}
	
	/**
	 * 创建office表(tb_policy_rules_bjs119)
	 */
	public void createOfficeTable(){

    	//office序列获取office信息
		Boolean exists = XLSXCovertCSVReader.officeExists.get(this.office);
		
		//office信息为NULL(尚未进行添加操作),执行建表操作
		if(null == exists){
			
			//查询库表判断是否存在
			exists = officeExistsCheck();
			
			//office信息为False(查询不到库表) ,执行建表操作
			if(!exists){
				
				Connection conn = null;
				PreparedStatement ps = null;
				boolean createResult = false;
				
				//获取splat库下所有表名
				String sql = "CREATE TABLE tb_policy_rules_" + this.office + " SELECT * FROM tb_policy_rules WHERE 1=2;";
				try {
					
					//封装连接,填充查询信息
					conn = JdbcTemplate.getConnInstance();
					ps = conn.prepareStatement(sql);
					
					//复制/创建office表
					ps.execute(sql);
					createResult = true;	
					
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					JdbcTemplate.closeCon(conn, ps, null);
				}
				
				//根据查询结果,封装office存在状态
				XLSXCovertCSVReader.officeExists.put(this.office, createResult);
				System.out.println(office + "表创建结果:" + createResult);
			}
		}			
	}
}
