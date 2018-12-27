package com.source.tools;

import java.util.UUID;

public class Uuid {
	public static synchronized String getUUID(){
		UUID uuid = UUID.randomUUID();
		String uuidStr = uuid.toString();
		String uuidString = uuidStr.replace("-", "");
		return uuidString;
	}

}
