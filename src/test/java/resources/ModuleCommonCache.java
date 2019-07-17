package resources;

import java.util.HashMap;
import java.util.Map;

public class ModuleCommonCache {
	
	private static HashMap<String,Object> moduleCommonCache = new HashMap<String,Object>();

	public static Object getModuleCommonCache(String pKey) {
		return moduleCommonCache.get(pKey);
	}

	public static void setModuleCommonCache(String pKey, Object pObject) {
		moduleCommonCache.put(pKey, pObject);
	}
	
		// Cache Map Structure 	::  ThreadId   -->   [key : value]
		// example				::  ThreadID  --> ListingUrl : Object

		private static  Map<String, Map<String, Object>> commonCache = new HashMap<String, Map<String, Object>>();
		
		public static Map<String, Object> getModuleMap(String modulePath) {
			Map<String, Object> moduleMap = commonCache.get(modulePath);
			
			if(moduleMap == null) {
				moduleMap = new HashMap<String,Object>();
				commonCache.put(modulePath, moduleMap);
			}
			
			return moduleMap;
		}
		
		public static Object getCachedModuleObject(String modulePath, String objectKey) {
			Map<String, Object> moduleMap = getModuleMap(modulePath);
			Object moduleObject = moduleMap.get(objectKey);
			return moduleObject;
		}
		
		public static void updateCacheForModuleObject(String pThreadID, String objectKey, Object object) {
			Map<String, Object> moduleMap = getModuleMap(pThreadID);
			moduleMap.put(objectKey, object);
		}
		
		// For Generic Parameterized return types
		
		@SuppressWarnings("unchecked")
		public static <T> T getElement(String modulePath) {
			return (T) getModuleMap(modulePath);
		}
		
		@SuppressWarnings("unchecked")
		public static <T> T getElement(String pThreadId, String objectKey) {
			Map<String, Object> moduleMap = getModuleMap(pThreadId);
			
			return (T) moduleMap.get(objectKey);
		}
		
		
		public static boolean isModuleMapEmpty(String modulePath) {
			Map<String, Object> moduleMap = getModuleMap(modulePath);
			boolean isMapEmpty = (moduleMap == null || moduleMap.isEmpty());
			
			return isMapEmpty;
		}

}
