package com.itant.getip;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tv_ip;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tv_ip = (TextView) findViewById(R.id.tv_ip);
		tv_ip.setText(getLocalIpAddress());
	}

	private String getLocalIpAddress() {

		try {
			Enumeration<NetworkInterface> networkInterfaceEnumeration = NetworkInterface.getNetworkInterfaces();
			while (networkInterfaceEnumeration.hasMoreElements()) {
				NetworkInterface networkInterface = networkInterfaceEnumeration.nextElement();
				Enumeration<InetAddress> inetAddressEnumeration = networkInterface.getInetAddresses();
				while (inetAddressEnumeration.hasMoreElements()) {
					InetAddress inetAddress = inetAddressEnumeration.nextElement();
					if (!inetAddress.isLoopbackAddress() && isIPv4Address(inetAddress.getHostAddress())) {
						return inetAddress.getHostAddress();
					}
				}
			}
		} catch (SocketException e) {
			
		}

		return "IP not found";
	}
	
	/**
	 * ≈–∂œ «∑ÒIPµÿ÷∑
	 * @param unknownAddress
	 * @return
	 */
	private boolean isIPv4Address(String unknownAddress) {
		String regex = "\\b(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(unknownAddress);
		while (matcher.find()) {
			return true;
		}
		return false;
	}
}
