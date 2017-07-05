package com.itahm.snmp;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddrMap {

	private final static long TIME_OUT = 60 * 1000;
	
	private final Map<String, Integer> indexMapping = new HashMap<String, Integer>();
	private final Map<String, Long> lastMapping = new HashMap<String, Long> ();
	
	public AddrMap() {
		
	}
	
	public void update(int index, String mac) {
		indexMapping.put(mac, index);
		lastMapping.put(mac, Calendar.getInstance().getTimeInMillis());
	}
	
	public int getIndex(String mac) {
		Integer index = this.indexMapping.get(mac);
		
		if (index != null) {
			if (Calendar.getInstance().getTimeInMillis() - this.lastMapping.get(mac) < TIME_OUT) {
				return index;
			}
			else {
				this.indexMapping.remove(mac);
				this.lastMapping.remove(mac);
			}
		}
		
		return -1;
	}
}
