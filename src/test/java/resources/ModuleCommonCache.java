package resources;

import java.util.HashMap;

public class ModuleCommonCache {
	
	private static HashMap<String,Object> moduleCommonCache = new HashMap<String,Object>();

	public static Object getModuleCommonCache(String pKey) {
		return moduleCommonCache.get(pKey);
	}

	public static void setModuleCommonCache(String pKey, Object pObject) {
		moduleCommonCache.put(pKey, pObject);
	}

}
