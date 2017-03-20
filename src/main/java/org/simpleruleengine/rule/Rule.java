package org.simpleruleengine.rule;

@FunctionalInterface
public interface Rule {
	public static int DEFAULT_PRIORITY = 1 ;
	
	public boolean evaulate(RuleContext context);
	
	public default int priority(){
		return DEFAULT_PRIORITY;
	}
}
