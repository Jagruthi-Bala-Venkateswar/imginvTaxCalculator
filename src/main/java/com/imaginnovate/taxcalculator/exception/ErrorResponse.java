package com.imaginnovate.taxcalculator.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The type Error response.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

	private String message;
	
	private Integer statusCode;

}
