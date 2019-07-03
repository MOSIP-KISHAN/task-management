/**
 * 
 */
package com.mindtree.taskManagement.vaildator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author M1043226
 *
 */
public class UserValidator implements Validator {

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class<?> arg0) {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "project", "project.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "description.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startDate", "startDate.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endDate", "endDate.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "employee", "employee.required");

	}

}
