package com.gaoyang.common;



public class API_URL {
	
	public static String ZSYH_ADDRESS = "http://mlife.cmbchina.com/NeptuneApp/";
	
	public static String ZSYH_ADDRESS2 = "http://piao.cmbchina.com/";
	/**
	 * 用户
	 */
	public class ZSYH {
		
		/**
		 * 获取抢购产品列表
		 */
		public static final String PRODUCT_LIST_URL =  "queryProductList.json";
		
		/**
		 * 创建订单
		 */
		public static final String CREATE_ORDER_URL =  "Cashier/receiveMerchantOrder.json";
		
		/**
		 * 创建订单2
		 */
		public static final String PRODUCT_9POINT_LIST_URL2 = "yummy-portal/JSONServer/execute.do";

		/**
		 * 创建订单3
		 */
		public static final String PRODUCT_9POINT_LIST_URL3 = "createOrderV5.json";

		
	}
	
}
