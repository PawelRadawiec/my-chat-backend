package info.mychatbackend.generic.model;

import org.springframework.validation.Errors;

public class GenericValidator {

    protected Errors errors;

    public enum ValidationCode {
        REQUIRED,
        UNIQUE
    }

    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
    }

    protected void validateIfTrue(Boolean statement, String field, String code, Errors errors) {
        if (statement) {
            errors.rejectValue(field, code, code);
        }
    }


}
