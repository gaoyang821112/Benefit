/*******************************************************************************
 * Copyright 2011-2013 Sergey Tarasevich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.gaoyang.common;

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public final class Constants {
	
	public static final String SUCCESS_CODE = "200";
	
	/**
	 * Messsge what 属�?��??-- 成功
	 */
	public static final int MSG_WHAT_SUCCESS = 1;
	/**
	 * Messsge what 属�?��??-- 失败
	 */
	public static final int MSG_WHAT_FAIL = 2;
	/**
	 * Messsge what 属�?��??-- 异常
	 */
	public static final int MSG_WHAT_EXCEPTION = 3;
	/**
	 * Messsge what 属�?��??-- 无网络链�?
	 */
	public static final int MSG_WHAT_NOT_NETWORK = 4;
	
	/**
	 * 访问网络时返回�?�状态码节点
	 */
	public static final String RESPONSE_CODE_NODE = "resCode" ;
	/**
	 * 访问网络时返回�?�对象节�?
	 */
	public static final String RESPONSE_DATA_NODE = "resData" ;
	/**
	 * 访问网络时返回�?�信息描述节�?
	 */
	public static final String RESPONSE_MSG_NODE = "msg" ;
	/**
	 * 访问网络时用户ID参数
	 */
	public final static String POST_PARAM_USER_ID = "userId" ;
	/**
	 * 访问网络时设备ID参数
	 */
	public final static String POST_PARAM_DEVICE_ID = "deviceId" ;
	
	/**
	 * 设备命令
	 */
	public final static String DEVICE_CMD = "cmd";
	/**
	 * 颜色变更命令
	 */
	public final static String DEVICE_CMD_COLOR = "COLOR";
	/**
	 * 灯打�?命令
	 */
	public final static String DEVICE_CMD_ON = "ON";
	/**
	 * 灯关闭命�?
	 */
	public final static String DEVICE_CMD_OFF = "OFF";
	
	/**
	 * 变更设备名称命令
	 */
	public final static String DEVICE_CMD_TITLE = "TITLE" ;
	
	/**
	 * 电量-正常
	 */
	public final static String BATTERY_NOR = "NOR" ;
	/**
	 * 电量-�?
	 */
	public final static String BATTERY_LOW = "LOW";
}
