package com.bettervote.resultcalculation;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.*;


public class LambdaFunctionHandler implements RequestHandler<Object, String> {
	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@Override
	public String handleRequest(Object event, Context context) {
		ResultCalculationInput input = gson.fromJson((String) event, ResultCalculationInput.class);
		ResultCalculationOutput output = Calculator.calculate(input.system, input.choices, input.votes);
		return gson.toJson(output);
	}

}

