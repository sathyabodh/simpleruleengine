package org.simpleruleengine.rule.condition

import org.simpleruleengine.rule.RuleContext;

import spock.lang.Specification

class AndConditionTest extends Specification {

	def condition1 = new MVELCondition("price < maxPrice");
	def condition2 = new MVELCondition("quantity > minQuantity");
	def condition3 = new MVELCondition("location == place");
	def context = new RuleContext();
	
	def "Test AndCondition"(){
		context.add("price", price);
		context.add("quantity",quantity);
		context.add("location",location);
		
		context.add("minQuantity",minQuantity);
		context.add("maxPrice",maxPrice);
		context.add("place",place);

		Conditions conditions= new Conditions(condition1);
		conditions.and(condition2,condition3);
		
		expect:
				conditions.evaluate(context) == result;
		where:
				result	|price	|quantity	|location	|maxPrice	|minQuantity	|place
				true	|1000	|25			|"Bangalore"|2000		|15				|"Bangalore"
				false	|1000	|25			|"NewDelhi"|2000		|15				|"Bangalore"
	}
	
	def "Test ORCondition"(){
		given:
				context.add("price", price);
				context.add("quantity",quantity);
				context.add("location",location);
		
				context.add("minQuantity",minQuantity);
				context.add("maxPrice",maxPrice);
				context.add("place",place);

				Conditions conditions= new Conditions(condition1);
				conditions.or(condition2,condition3);
		
		when:
				def retVal = conditions.evaluate(context) ;
		then:
				retVal == result;
//				1	* condition.evaluate(context);
		where:
				result	|price	|quantity	|location	|maxPrice	|minQuantity	|place
				true	|1000	|5			|"Bangalore"|2000		|15				|"Bangalore"
				false	|3000	|5			|"NewDelhi" |2000		|15				|"Bangalore"
	}

	def "Test Composite"(){
		given:
				context.add("price", price);
				context.add("quantity",quantity);
				context.add("location",location);
		
				context.add("minQuantity",minQuantity);
				context.add("maxPrice",maxPrice);
				context.add("place",place);

				Conditions conditions= new Conditions(condition1);
				conditions.and(condition2).or(condition3);
		
		when:
				def retVal = conditions.evaluate(context) ;
		then:
				retVal == result;
//				1	* condition.evaluate(context);
		where:
				result	|price	|quantity	|location	|maxPrice	|minQuantity	|place
				true	|1000	|5			|"Bangalore"|2000		|15				|"Bangalore"
				true	|1000	|25			|"NewDelhi" |2000		|15				|"Bangalore"
				false	|1000	|5			|"NewDelhi" |2000		|15				|"Bangalore"
	}

}
