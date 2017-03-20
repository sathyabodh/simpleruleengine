package org.simpleruleengine.loader.yaml;

import java.util.List;
import java.util.Map;

public class RuleSetConfig {
	
	private List<Map<String, List<RuleConfig>>> ruleSets;

	public List<Map<String, List<RuleConfig>>> getRuleSets() {
		return ruleSets;
	}


	public void setRuleSets(List<Map<String, List<RuleConfig>>> ruleSets) {
		this.ruleSets = ruleSets;
	}
}
