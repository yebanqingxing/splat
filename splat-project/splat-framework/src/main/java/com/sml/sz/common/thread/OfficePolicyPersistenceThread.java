package com.sml.sz.common.thread;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.sml.sz.common.jdbc.JdbcTemplate;

/**
 * office政策信息持久化
 * @author yues
 *
 */
public class OfficePolicyPersistenceThread implements Runnable{

	//office号
	protected String office;
	
	//线程名
	protected String name;
	
	//office数据
	protected List<String[]> data;
	
	//线程终止标志
	protected boolean stopFlag = true;
	
	public OfficePolicyPersistenceThread(String office , List<String[]> data , String name){
		this.office = office.toUpperCase().trim();
		this.data = data;
		this.name = this.office + "_" + name;
	}
	
	/**
	 * 执行Office数据持久化操作(通过SQL进行批量插入,需要考虑StringBuffer容量为题)
	 * @param data
	 */
	public void persistenceOfficeData(){
		Connection conn = null;
		PreparedStatement ps = null;
		
		long begin = System.currentTimeMillis();
		//System.out.println("office：" + office + "\t dataSize：" + data.size() + "\t线程：" + this.name
		//+"\t持久化数据操作开始.. ");
		
		//入库SQL
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into tb_policy_rules_").append(this.office)
		.append("(pr_code , policy_name , policy_status , supplier_id , office , passager_pid , tkt_airline , ")
		.append("passager_count , travel_Type , out_org , out_org_ex , out_des , out_des_ex , return_des , ")
		.append("return_des_ex , out_unable_pass , out_must_pass , return_unable_pass , return_must_pass , ")
		.append("allow_filght_no , exclude_filght_no , cabin , rebate , billing_fee , agency_fee , ")
		.append("children_reward , children_rebate , children_poundage , children_poundage_choice , ")
		.append("children_open_no_com , bady_reward , first_date_start , first_date_end , last_date_start , ")
		.append("last_date_end , tkt_time_start , tkt_time_end , share_policy , policy_remark)")
		.append(" values");
		
		try {
			//单行数据信息
			String[] temp = null;
			
			//遍历数据集合,拼装SQL
			for(int i = 0; i < data.size(); i++){
				
				temp = data.get(i);
				
				//拼接SQL
				if(i > 0){
					buffer.append(",");
				}
				
				buffer
				.append("(")
				.append("'6c70c0f5d22b0d421fe766e144384ba2'").append(",") //pr_code 32位UUID
				.append("'").append((null == temp[0]) ? "未命名政策" : temp[0].trim()).append("',") //policy_name 政策名称
				.append("'").append((null == temp[1]) ? '0' : temp[1].trim().charAt(0)).append("',") //policy_status 政策状态 0 启用，1禁用，2挂起
				.append("''").append(",") //supplier_id 投放分销商
				.append("'").append(this.office).append("',") //office
				.append("'").append((null == temp[4]) ? "0" : temp[4].trim()).append(",") //passager_pid 普通
				.append((null == temp[5]) ? "0" : temp[5].trim()).append(",") //passager_pid 留学生
				.append((null == temp[6]) ? "0" : temp[6].trim()).append(",") //passager_pid 移民
				.append((null == temp[7]) ? "0" : temp[7].trim()).append(",").append("',") //passager_pid 劳工
				
				.append("'").append((null == temp[3]) ? "" : temp[3].trim()).append("',") //tkt_airline 出票航司
				.append((null == temp[8]) ? "1" : temp[8].trim()).append(",") //passager_count 旅客人数下限
				.append("'").append((null == temp[9]) ? "0" : getTravelType(temp[9].trim())).append("',") //travel_Type 行程类型,单程、往返、单程/往返
				.append("'").append((null == temp[10]) ? "" : temp[10].trim()).append("',") //out_org 去程起点
				.append("'").append((null == temp[11]) ? "" : temp[11].trim()).append("',") //out_org_ex 去程起点除外
				.append("'").append((null == temp[12]) ? "" : temp[12].trim()).append("',") //out_des 去程终点/折返点
				.append("'").append((null == temp[13]) ? "" : temp[13].trim()).append("',") //out_des_ex 去程终点（折返点）除外
				.append("'").append((null == temp[14]) ? "" : temp[14].trim()).append("',") //return_des 回程起点
				.append("'").append((null == temp[15]) ? "" : temp[15].trim()).append("',") //return_des_ex 回程起点除外
				.append("'").append((null == temp[16]) ? "" : temp[16].trim()).append("',") //out_unable_pass 去程不允许经过
				.append("'").append((null == temp[17]) ? "" : temp[17].trim()).append("',") //out_must_pass 去程必须经过
				.append("'").append((null == temp[18]) ? "" : temp[18].trim()).append("',") //return_unable_pass 回程不允许经过
				.append("'").append((null == temp[19]) ? "" : temp[19].trim()).append("',") //return_must_pass 回程必须经过
				.append("'").append((null == temp[21]) ? "" : temp[21].trim()).append("',") //allow_filght_no 仅限航班号
				.append("'").append((null == temp[20]) ? "" : temp[20].trim()).append("',") //exclude_filght_no 排除航班号
				.append("'").append((null == temp[22]) ? "" : temp[22].trim()).append("',") //cabin 舱位
				.append((null == temp[23]) ? 0.00 : temp[23].trim()).append(",") //rebate 返点
				.append((null == temp[24]) ? null : temp[24].trim()).append(",") //billing_fee 开票费
				.append((null == temp[25]) ? null : temp[25].trim()).append(",") //agency_fee 代理费
				.append("'").append((null == temp[26]) ? "" : temp[26].trim()).append("',") //<?>children_reward 儿童奖励情况 :01后返与成人一至，02可开无后返，03不可开,04指定奖励,
				.append((null == temp[32]) ? 0.00 : temp[32].trim()).append(",") //children_rebate 儿童返点
				.append((null == temp[33]) ? null : temp[33].trim()).append(",") //children_poundage 儿童手续费
				.append("'").append((null == temp[33]) ? "N" : "Y").append("',") //children_poundage_choice 儿童加手续费 Y/N
				.append("'").append((null == temp[34]) ? "N" : "Y").append("',") //children_open_no_com 儿童可开无代理费 Y/N
				.append("'").append((null == temp[26]) ? "0" : temp[26].trim().charAt(0))
				.append((null == temp[27]) ? "0" : temp[27].trim().charAt(0)).append("',") //bady_reward 婴儿奖励情况 0 可开无奖励，1 不可开
				.append((null == temp[36]) ? null : "'" + temp[36].trim() + "'").append(",") //first_date_start 第一国际段旅行日期 开始
				.append((null == temp[37]) ? null : "'" + temp[37].trim() + "'").append(",") //first_date_end 第一国际段旅行日期 结束
				.append((null == temp[38] || temp[38].isEmpty()) ? null : "'" + temp[38].trim() + "'").append(",") //last_date_start 最后国际段旅行日期 开始
				.append((null == temp[39] || temp[39].isEmpty()) ? null : "'" + temp[39].trim() + "'").append(",") //last_date_end 最后国际段旅行日期 结束
				
				.append((null == temp[40]) ? null : "'" + temp[40].trim() + "'").append(",") //tkt_time_start 出票日期 开始
				.append((null == temp[41]) ? null : "'" + temp[41].trim() + "'").append(",") //tkt_time_end 出票日期  结束
				.append("'").append((null == temp[35]) ? "N" : temp[35].trim()).append("',") //share_policy 是否允许与其他政策组合 Y/N
				.append("'").append((null == temp[42]) ? null : temp[42].trim()).append("'") //policy_remark 备注
				.append(")");
			}
			
			//System.out.println("Buffer：" + buffer.toString());
			
			System.out.println("office：" + office + "\t dataSize：" + data.size() + "\t线程：" + this.name
					+"\tSQL创建完成时间：" + (System.currentTimeMillis()-begin));
			begin = System.currentTimeMillis();
			
			//封装连接,填充查询信息
			conn = JdbcTemplate.getConnInstance();
			ps = conn.prepareStatement("");
			
			//关闭自动提交
			conn.setAutoCommit(false);
			
			//提交更新
			ps.executeUpdate(buffer.toString());
			
			//提交更新
			conn.commit();

			System.out.println("office：" + office + "\t dataSize：" + data.size() + "\t线程：" + this.name
					+"\t完成数据提交时间：" + (System.currentTimeMillis()-begin));
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			
			//关闭连接
			JdbcTemplate.closeCon(conn, ps, null);
		}
		
		this.stopFlag = false;
	}
	
	/**
	 * 执行Office数据持久化操作(批处理添加,性能优于SQL,但需要注意内存的使用情况防止溢出)
	 * @param data
	 */
	public void persistenceOfficeDataByBatch(){
		Connection conn = null;
		PreparedStatement ps = null;
		
		long begin = System.currentTimeMillis();
		//System.out.println("office：" + office + "\t dataSize：" + data.size() + "\t线程：" + this.name
		//+"\t持久化数据操作开始.. ");
		
		//入库SQL
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into tb_policy_rules_").append(this.office)
		.append("(pr_code , policy_name , policy_status , supplier_id , office , passager_pid , tkt_airline , ")
		.append("passager_count , travel_Type , out_org , out_org_ex , out_des , out_des_ex , return_des , ")
		.append("return_des_ex , out_unable_pass , out_must_pass , return_unable_pass , return_must_pass , ")
		.append("allow_filght_no , exclude_filght_no , cabin , rebate , billing_fee , agency_fee , ")
		.append("children_reward , children_rebate , children_poundage , children_poundage_choice , ")
		.append("children_open_no_com , bady_reward , first_date_start , first_date_end , last_date_start , ")
		.append("last_date_end , tkt_time_start , tkt_time_end , share_policy , policy_remark)")
		.append("values(")
		.append("? , ? , ? , ? , ? , ? , ? , ")
		.append("? , ? , ? , ? , ? , ? , ? , ")
		.append("? , ? , ? , ? , ? , ")
		.append("? , ? , ? , ? , ? , ? , ")
		.append("? , ? , ? , ? , ")
		.append("? , ? , ? , ? , ? , ")
		.append("? , ? , ? , ? , ?)");
		
		
		try {
			//单行数据信息
			String[] temp = null;
			
			//封装连接,填充查询信息
			conn = JdbcTemplate.getConnInstance();
			ps = conn.prepareStatement(buffer.toString());
			
			//关闭自动提交
			conn.setAutoCommit(false);
			
			//参数索引
			int tempIndex = 1;
			
			//类型设置
			String passager_pid = "";
			
			//遍历数据集合,拼装SQL
			for(int i = 0 , count = data.size(); i < count; i++){
				
				temp = data.get(i);
				tempIndex = 1;
				
				//pr_code 32位UUID
				ps.setString(tempIndex , "6c70c0f5d22b0d421fe766e144384ba2"); 
				
				//policy_name 政策名称
				ps.setString(++tempIndex , (null == temp[0]) ? "未命名政策" : temp[0].trim()); 
				
				//policy_status 政策状态 0 启用，1禁用，2挂起
				ps.setString(++tempIndex , (null == temp[1]) ? "0" : temp[1].trim().substring(0)); 
				
				//supplier_id 投放分销商
				ps.setString(++tempIndex , "");
				
				//office
				ps.setString(++tempIndex , this.office);
				
				
				passager_pid = new StringBuffer((null == temp[4]) ? "0" : temp[4].trim()).append(",")
						.append((null == temp[5]) ? "0" : temp[5].trim()).append(",")
						.append((null == temp[6]) ? "0" : temp[6].trim()).append(",")
						.append((null == temp[7]) ? "0" : temp[7].trim())
						.toString();
				
				 //passager_pid 普通/留学生/移民/劳工
				ps.setString(++tempIndex , passager_pid);
				
				//tkt_airline 出票航司
				ps.setString(++tempIndex , (null == temp[3]) ? "" : temp[3].trim());
				
				//passager_count 旅客人数下限
				ps.setString(++tempIndex , (null == temp[8]) ? "1" : temp[8].trim());
				
				//travel_Type 行程类型,单程、往返、单程/往返
				ps.setString(++tempIndex , (null == temp[9]) ? "0" : getTravelType(temp[9].trim()));
				
				//out_org 去程起点
				ps.setString(++tempIndex , (null == temp[10]) ? "" : temp[10].trim());
				
				 //out_org_ex 去程起点除外
				ps.setString(++tempIndex , (null == temp[11]) ? "" : temp[11].trim());
				
				//out_des 去程终点/折返点
				ps.setString(++tempIndex , (null == temp[12]) ? "" : temp[12].trim());
				
				//out_des_ex 去程终点（折返点）除外
				ps.setString(++tempIndex , (null == temp[13]) ? "" : temp[13].trim());
				
				//return_des 回程起点
				ps.setString(++tempIndex , (null == temp[14]) ? "" : temp[14].trim());
				
				//return_des_ex 回程起点除外
				ps.setString(++tempIndex , (null == temp[15]) ? "" : temp[15].trim());
				
				//out_unable_pass 去程不允许经过
				ps.setString(++tempIndex , (null == temp[16]) ? "" : temp[16].trim());
				
				//out_must_pass 去程必须经过
				ps.setString(++tempIndex , (null == temp[17]) ? "" : temp[17].trim());
				
				//return_unable_pass 回程不允许经过
				ps.setString(++tempIndex , (null == temp[18]) ? "" : temp[18].trim());
				
				//return_must_pass 回程必须经过
				ps.setString(++tempIndex , (null == temp[19]) ? "" : temp[19].trim());
				
				//allow_filght_no 仅限航班号
				ps.setString(++tempIndex , (null == temp[21]) ? "" : temp[21].trim());
				
				//exclude_filght_no 排除航班号
				ps.setString(++tempIndex , (null == temp[20]) ? "" : temp[20].trim());
				
				//cabin 舱位
				ps.setString(++tempIndex , (null == temp[22]) ? "" : temp[22].trim());
				
				//rebate 返点
				ps.setDouble(++tempIndex , (null == temp[23]) ? 0.00 : Double.valueOf(temp[23].trim()));
				
				//billing_fee 开票费
				ps.setString(++tempIndex , (null == temp[24]) ? null : temp[24].trim());
				
				//agency_fee 代理费
				ps.setDouble(++tempIndex , (null == temp[25] || temp[25].isEmpty()) ? 0.00 : Double.valueOf(temp[25].trim()));
				
				//<?>children_reward 儿童奖励情况 :01后返与成人一至，02可开无后返，03不可开,04指定奖励,
				ps.setString(++tempIndex , (null == temp[26]) ? "" : temp[26].trim());
				
				//children_rebate 儿童返点
				ps.setDouble(++tempIndex , (null == temp[32]) ? 0.00 : Double.valueOf(temp[32].trim()));
				
				//children_poundage 儿童手续费
				ps.setString(++tempIndex , (null == temp[33]) ? null : temp[33].trim());
				
				//children_poundage_choice 儿童加手续费 Y/N
				ps.setString(++tempIndex , (null == temp[33]) ? "N" : "Y");
				
				//children_open_no_com 儿童可开无代理费 Y/N
				ps.setString(++tempIndex , (null == temp[34]) ? "N" : "Y");
								
				//bady_reward 婴儿奖励情况 0 可开无奖励，1 不可开
				ps.setString(++tempIndex , (null == temp[27]) ? "0" : temp[27].trim().substring(0));
				
				//first_date_start 第一国际段旅行日期 开始
				ps.setString(++tempIndex , (null == temp[36]) ? null : temp[36].trim());
				
				//first_date_end 第一国际段旅行日期 结束
				ps.setString(++tempIndex , (null == temp[37]) ? null : temp[37].trim());
				
				//last_date_start 最后国际段旅行日期 开始
				ps.setString(++tempIndex , (null == temp[38] || temp[38].isEmpty()) ? null : temp[38].trim());
				
				//last_date_end 最后国际段旅行日期 结束
				ps.setString(++tempIndex , (null == temp[39] || temp[39].isEmpty()) ? null : temp[39].trim());
				
				//tkt_time_start 出票日期 开始
				ps.setString(++tempIndex , (null == temp[40]) ? null : temp[40].trim());
				
				//tkt_time_end 出票日期  结束
				ps.setString(++tempIndex , (null == temp[41]) ? null : temp[41].trim());
				
				//share_policy 是否允许与其他政策组合 Y/N
				ps.setString(++tempIndex , (null == temp[35]) ? "N" : temp[35].trim());
				
				//policy_remark 备注
				ps.setString(++tempIndex , (null == temp[42]) ? null : temp[42].trim());
				
				//加入批处理
				ps.addBatch();
			}
			
			//执行批处理
			ps.executeBatch();
			
			//提交更新
			conn.commit();

			System.out.println("office：" + office + "\t dataSize：" + data.size() + "\t 线程：" + this.name
					+"\t 完成数据提交时间：" + (System.currentTimeMillis()-begin));
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			
			//关闭连接
			JdbcTemplate.closeCon(conn, ps, null);
		}
		
		this.stopFlag = false;
	}
	
	/**
	 * 获取航程类型
	 * @param travel_Type OW-单程 RT-往返 OW/RT-单程/往返
	 * @return
	 */
	public String getTravelType(String travel_Type){
		String travelType = "0";
		
		if(null != travel_Type){
			
			if(travel_Type.equalsIgnoreCase("OW")){
				//单程
				travelType = "0";
			}else if(travel_Type.equalsIgnoreCase("RT")){
				//往返
				travelType = "1";
			}else{
				//单程/往返
				travelType = "2";
			}
		}
		
		return travelType;
	}
	
	/**
	 * 开启线程,执行持久化操作
	 */
	public void run() {
		
		while(stopFlag){
			persistenceOfficeDataByBatch();
		}
	}

}
