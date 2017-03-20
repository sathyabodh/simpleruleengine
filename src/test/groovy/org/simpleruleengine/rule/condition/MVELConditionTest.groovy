package org.simpleruleengine.rule.condition

import org.simpleruleengine.rule.RuleContext

import spock.lang.Specification

class MVELConditionTest extends Specification {

	def "Test Simple MVEL Expressions"(){
		MVELCondition cond = new MVELCondition("value1 == 2 && value2 == 3");
		RuleContext context = new RuleContext();
		context.add("value1", value1);
		context.add("value2", value2);
		
		expect:
				cond.evaluate(context) == result;
		where:
				value1	<< [2, 3]
				value2  << [3, 2]
				result 	<< [true, false]
	}
	
	def "Test MVEL Expressions with > & < operator"(){
		MVELCondition cond = new MVELCondition("price >= minPrice  && price < maxPrice");
		RuleContext context = new RuleContext();
		context.add("price", price);
		context.add("minPrice", minPrice);
		context.add("maxPrice", maxPrice);

		expect:
				cond.evaluate(context) == result;
		where:
				price	|minPrice	|maxPrice	|result
				2		|2			|10			|true
				3		|5			|6			|false
				10		|7			|9			|false
	}

	def "Test MVEL Expressions with > or < operator"(){
		MVELCondition cond = new MVELCondition("price >= minPrice  || price < maxPrice");
		RuleContext context = new RuleContext();
		context.add("price", price);
		context.add("minPrice", minPrice);
		context.add("maxPrice", maxPrice);

		expect:
				cond.evaluate(context) == result;
		where:
				price	|minPrice	|maxPrice	|result
				2		|2			|10			|true
				3		|5			|2			|false
	}

}
