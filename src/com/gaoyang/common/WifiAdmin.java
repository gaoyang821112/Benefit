package com.gaoyang.common;

import java.util.List;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiConfiguration.AuthAlgorithm;
import android.net.wifi.WifiConfiguration.KeyMgmt;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;
import android.text.TextUtils;

public class WifiAdmin {
	// 定义WifiManager对象
	private WifiManager mWifiManager;
	// 定义WifiInfo对象
	private WifiInfo mWifiInfo;
	// 扫描出的网络连接列表
	private List<ScanResult> mWifiList;
	// 网络连接列表
	private List<WifiConfiguration> mWifiConfiguration;
	// 定义�?个WifiLock
	WifiLock mWifiLock;

	// 构�?�器
	public WifiAdmin(Context context) {
		// 取得WifiManager对象
		mWifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		// 取得WifiInfo对象
		mWifiInfo = mWifiManager.getConnectionInfo();
	}

	// 打开WIFI
	public void OpenWifi() {
		if (!mWifiManager.isWifiEnabled()) {
			mWifiManager.setWifiEnabled(true);

		}
	}

	// 关闭WIFI
	public void CloseWifi() {
		if (!mWifiManager.isWifiEnabled()) {
			mWifiManager.setWifiEnabled(false);
		}
	}

	// 锁定WifiLock，当下载大文件时�?要锁�?
	public void AcquireWifiLock() {
		mWifiLock.acquire();
	}

	// 解锁WifiLock
	public void ReleaseWifiLock() {
		// 判断时�?�锁�?
		if (mWifiLock.isHeld()) {
			mWifiLock.acquire();
		}
	}

	// 创建�?个WifiLock
	public void CreatWifiLock() {
		mWifiLock = mWifiManager.createWifiLock("Test");
	}

	// 得到配置好的网络
	public List<WifiConfiguration> GetConfiguration() {
		return mWifiConfiguration;
	}

	// 指定配置好的网络进行连接
	public void ConnectConfiguration(int index) {
		// 索引大于配置好的网络索引返回
		if (index > mWifiConfiguration.size()) {
			return;
		}
		// 连接配置好的指定ID的网�?
		mWifiManager.enableNetwork(mWifiConfiguration.get(index).networkId,
				true);
	}

	public void StartScan() {
		mWifiManager.startScan();
		// 得到扫描结果
		mWifiList = mWifiManager.getScanResults();
		// 得到配置好的网络连接
		mWifiConfiguration = mWifiManager.getConfiguredNetworks();
	}

	// 得到网络列表
	public List<ScanResult> GetWifiList() {
		return mWifiList;
	}

	// 查看扫描结果
	public StringBuilder LookUpScan() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < mWifiList.size(); i++) {
			stringBuilder
					.append("Index_" + new Integer(i + 1).toString() + ":");
			// 将ScanResult信息转换成一个字符串�?
			// 其中把包括：BSSID、SSID、capabilities、frequency、level
			stringBuilder.append((mWifiList.get(i)).toString());
			stringBuilder.append("\n");
		}
		return stringBuilder;
	}

	// 得到MAC地址
	public String GetMacAddress() {
		return (mWifiInfo == null) ? "NULL" : mWifiInfo.getMacAddress();
	}

	// 得到接入点的BSSID
	public String GetBSSID() {
		return (mWifiInfo == null) ? "NULL" : mWifiInfo.getBSSID();
	}

	// 得到接入点的BSSID
	public String GetSSID() {
		return (mWifiInfo == null) ? "NULL" : mWifiInfo.getSSID();
	}

	// 得到IP地址
	public int GetIPAddress() {
		return (mWifiInfo == null) ? 0 : mWifiInfo.getIpAddress();
	}

	// 得到连接的ID
	public int GetNetworkId() {
		return (mWifiInfo == null) ? 0 : mWifiInfo.getNetworkId();
	}

	// 得到WifiInfo的所有信息包
	public String GetWifiInfo() {
		return (mWifiInfo == null) ? "NULL" : mWifiInfo.toString();
	}

	// 添加�?个网络并连接
	public void AddNetwork(WifiConfiguration wcg) {
		int wcgID = mWifiManager.addNetwork(wcg);
		mWifiManager.enableNetwork(wcgID, true);
	}

	// 断开指定ID的网�?
	public void DisconnectWifi(int netId) {
		mWifiManager.disableNetwork(netId);
		mWifiManager.disconnect();
	}

	// 然后是一个实际应用方法，只验证过没有密码的情况：

	// public WifiConfiguration CreateWifiInfo(String SSID, String Password,
	// int Type) {
	// WifiConfiguration config = new WifiConfiguration();
	// config.allowedAuthAlgorithms.clear();
	// config.allowedGroupCiphers.clear();
	// config.allowedKeyManagement.clear();
	// config.allowedPairwiseCiphers.clear();
	// config.allowedProtocols.clear();
	// config.SSID = "\"" + SSID + "\"";
	//
	// WifiConfiguration tempConfig = this.IsExsits(SSID);
	// if (tempConfig != null) {
	// mWifiManager.removeNetwork(tempConfig.networkId);
	// }
	//
	// if (Type == 1) // WIFICIPHER_NOPASS
	// {
	// config.wepKeys[0] = "";
	// config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
	// config.wepTxKeyIndex = 0;
	// }
	// if (Type == 2) // WIFICIPHER_WEP
	// {
	// config.hiddenSSID = true;
	// config.wepKeys[0] = "\"" + Password + "\"";
	// config.allowedAuthAlgorithms
	// .set(WifiConfiguration.AuthAlgorithm.SHARED);
	// config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
	// config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
	// config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
	// config.allowedGroupCiphers
	// .set(WifiConfiguration.GroupCipher.WEP104);
	// config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
	// config.wepTxKeyIndex = 0;
	// }
	// if (Type == 3) // WIFICIPHER_WPA
	// {
	// config.preSharedKey = "\"" + Password + "\"";
	// config.hiddenSSID = true;
	// config.allowedAuthAlgorithms
	// .set(WifiConfiguration.AuthAlgorithm.OPEN);
	// config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
	// config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
	// config.allowedPairwiseCiphers
	// .set(WifiConfiguration.PairwiseCipher.TKIP);
	// // config.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
	// config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
	// config.allowedPairwiseCiphers
	// .set(WifiConfiguration.PairwiseCipher.CCMP);
	// config.status = WifiConfiguration.Status.ENABLED;
	// }
	// return config;
	// }
	//
	// private WifiConfiguration IsExsits(String SSID) {
	// List<WifiConfiguration> existingConfigs = mWifiManager
	// .getConfiguredNetworks();
	// for (WifiConfiguration existingConfig : existingConfigs) {
	// if (existingConfig.SSID.equals("\"" + SSID + "\"")) {
	// return existingConfig;
	// }
	// }
	// return null;
	// }

	public enum WifiCipherType {
		WIFICIPHER_WEP, WIFICIPHER_WPA, WIFICIPHER_NOPASS, WIFICIPHER_INVALID
	}

	// 提供�?个外部接口，传入要连接的无线�?
	public void connect(String ssid, String password, WifiCipherType type) {
		Thread thread = new Thread(new ConnectRunnable(ssid, password, type));
		thread.start();
	}

	// 查看以前是否也配置过这个网络
	private WifiConfiguration isExsits(String SSID) {
		List<WifiConfiguration> existingConfigs = mWifiManager
				.getConfiguredNetworks();
		for (WifiConfiguration existingConfig : existingConfigs) {
			if (existingConfig.SSID.equals("\"" + SSID + "\"")) {
				return existingConfig;
			}
		}
		return null;
	}

	public WifiConfiguration createWifiInfo(String SSID, String Password,
			WifiCipherType Type) {
		WifiConfiguration config = new WifiConfiguration();
		config.allowedAuthAlgorithms.clear();
		config.allowedGroupCiphers.clear();
		config.allowedKeyManagement.clear();
		config.allowedPairwiseCiphers.clear();
		config.allowedProtocols.clear();
		config.SSID = "\"" + SSID + "\"";
		// nopass
		if (Type == WifiCipherType.WIFICIPHER_NOPASS) {
			config.wepKeys[0] = "";
			config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
			config.wepTxKeyIndex = 0;
		}
		// wep
		if (Type == WifiCipherType.WIFICIPHER_WEP) {
			if (!TextUtils.isEmpty(Password)) {
				if (isHexWepKey(Password)) {
					config.wepKeys[0] = Password;
				} else {
					config.wepKeys[0] = "\"" + Password + "\"";
				}
			}
			config.allowedAuthAlgorithms.set(AuthAlgorithm.OPEN);
			config.allowedAuthAlgorithms.set(AuthAlgorithm.SHARED);
			config.allowedKeyManagement.set(KeyMgmt.NONE);
			config.wepTxKeyIndex = 0;
		}
		// wpa
		if (Type == WifiCipherType.WIFICIPHER_WPA) {
			config.preSharedKey = "\"" + Password + "\"";
			config.hiddenSSID = true;
			config.allowedAuthAlgorithms
					.set(WifiConfiguration.AuthAlgorithm.OPEN);
			config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
			config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
			config.allowedPairwiseCiphers
					.set(WifiConfiguration.PairwiseCipher.TKIP);
			// 此处�?要修改否则不能自动重�?
			// config.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
			config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
			config.allowedPairwiseCiphers
					.set(WifiConfiguration.PairwiseCipher.CCMP);
			config.status = WifiConfiguration.Status.ENABLED;
		}
		return config;
	}

	// 打开wifi功能
	private boolean openWifi() {
		boolean bRet = true;
		if (!mWifiManager.isWifiEnabled()) {
			bRet = mWifiManager.setWifiEnabled(true);
		}
		return bRet;
	}

	class ConnectRunnable implements Runnable {
		private String ssid;

		private String password;

		private WifiCipherType type;

		public ConnectRunnable(String ssid, String password, WifiCipherType type) {
			this.ssid = ssid;
			this.password = password;
			this.type = type;
		}

		@Override
		public void run() {
			// 打开wifi
			openWifi();
			// �?启wifi功能�?要一段时�?(我在手机上测试一般需�?1-3秒左�?)，所以要等到wifi
			// 状�?�变成WIFI_STATE_ENABLED的时候才能执行下面的语句
			while (mWifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLING) {
				try {
					// 为了避免程序�?直while循环，让它睡�?100毫秒�?测�?��??
					Thread.sleep(100);
				} catch (InterruptedException ie) {
				}
			}

			WifiConfiguration wifiConfig = createWifiInfo(ssid, password, type);
			//
			if (wifiConfig == null) {
				return;
			}

			WifiConfiguration tempConfig = isExsits(ssid);

			if (tempConfig != null) {
				mWifiManager.removeNetwork(tempConfig.networkId);
			}

			int netID = mWifiManager.addNetwork(wifiConfig);
			boolean enabled = mWifiManager.enableNetwork(netID, true);
			boolean connected = mWifiManager.reconnect();
		}
	}

	private static boolean isHexWepKey(String wepKey) {
		final int len = wepKey.length();

		// WEP-40, WEP-104, and some vendors using 256-bit WEP (WEP-232?)
		if (len != 10 && len != 26 && len != 58) {
			return false;
		}

		return isHex(wepKey);
	}

	private static boolean isHex(String key) {
		for (int i = key.length() - 1; i >= 0; i--) {
			final char c = key.charAt(i);
			if (!(c >= '0' && c <= '9' || c >= 'A' && c <= 'F' || c >= 'a'
					&& c <= 'f')) {
				return false;
			}
		}

		return true;
	}
}
