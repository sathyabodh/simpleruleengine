package org.simpleruleengine.rule;

import java.util.HashMap;
import java.util.Map;

public class RuleContext {

	private Map<String, Object> context = new HashMap<>();
	
	public void add(String key, Object value){
		context.put(key, value);
	}
	
	public <T> T get(String key){
		return (T)context.get(key);
	}
	
	public Map<String, Object> getContext() {
		return context;
	}
}
