package org.simpleruleengine.loader.yaml;

import org.simpleruleengine.rule.Rule;

public class RuleConfig {

	private String name;
	private String condition;
	private String successActionClass;
	private String failureActionClass;
	private int priority = Rule.DEFAULT_PRIORITY;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getSuccessActionClass() {
		return successActionClass;
	}
	public void setSuccessActionClass(String successActionClass) {
		this.successActionClass = successActionClass;
	}
	public String getFailureActionClass() {
		return failureActionClass;
	}
	public void setFailureActionClassName(String failureActionClass) {
		this.failureActionClass = failureActionClass;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
}
