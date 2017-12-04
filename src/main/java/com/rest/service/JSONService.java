package com.rest.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.rest.model.Request;

@Path("/json")
public class JSONService {

	@Path("/calculate")
	@Consumes(value = "application/json")
	@Produces(value = "application/json")
	@POST
	public com.rest.model.Result calculate(Request request) {
		com.rest.model.Result result = new com.rest.model.Result();
		Object input1 = request.getValue1();
		Object input2 = request.getValue2();
		List<String> errors = new ArrayList<>();
		if (String.valueOf(input1).matches("[0-9]+") == false || String.valueOf(input2).matches("[0-9]+") == false) {
			errors.add("input should contains only digits");
		} else if (String.valueOf(input1).matches("[a-zA-Z0-9 ]*") == false) {
			errors.add("input should not contains any special character");

		}
		if (errors.isEmpty()) {
			result.setInput1((int) input1);
			result.setInput2((int) input2);
			result.setResult((int) input1 + (int) input2);
		} else {
			result.setErrorMessage(errors);
		}

		return result;
	}
}