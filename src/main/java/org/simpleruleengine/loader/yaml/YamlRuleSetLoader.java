package org.simpleruleengine.loader.yaml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.simpleruleengine.loader.RuleSetLoader;
import org.simpleruleengine.rule.SimpleRule;
import org.simpleruleengine.rule.action.OnFailAction;
import org.simpleruleengine.rule.action.OnSuccessAction;
import org.simpleruleengine.rule.condition.Condition;
import org.simpleruleengine.rule.condition.MVELCondition;
import org.simpleruleengine.ruleset.RuleSet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class YamlRuleSetLoader implements RuleSetLoader {

	private ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
	@Override
	public List<RuleSet> load(File file) {
		try {
			RuleSetConfig config = mapper.readValue(file, RuleSetConfig.class);
			List<RuleSet> ruleSets = new ArrayList<>();
			for(Map<String, List<RuleConfig>> value : config.getRuleSets()){
				for(Map.Entry<String, List<RuleConfig>> entry : value.entrySet()){
					RuleSet ruleSet = new RuleSet(entry.getKey());
					for(RuleConfig ruleConfig : entry.getValue()){
						Condition cond = new MVELCondition(ruleConfig.getCondition());
						OnSuccessAction successAction = (OnSuccessAction) Class.forName(ruleConfig.getSuccessActionClass()).newInstance();
						OnFailAction failAction = null;
						if(ruleConfig.getFailureActionClass() != null && !ruleConfig.getFailureActionClass().isEmpty()){
							failAction = (OnFailAction)Class.forName(ruleConfig.getFailureActionClass()).newInstance();
						}
						SimpleRule rule = new SimpleRule(ruleConfig.getName(),cond, Optional.of(successAction), Optional.ofNullable(failAction));
						ruleSet.addRules(rule);
					}
					ruleSets.add(ruleSet);
				}
			}
			return ruleSets;
		} catch (IOException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public static void main(String[] args){
		YamlRuleSetLoader l = new YamlRuleSetLoader();
		l.load(new File("/Users/i322094/learning/RuleEngine/simpleruleengine/RuleEngine/src/test/resources/test-rule.yaml"));
		
	}
}
