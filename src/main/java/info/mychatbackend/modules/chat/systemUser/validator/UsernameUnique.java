package info.mychatbackend.modules.chat.systemUser.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UsernameValidator.class})
@Documented
public @interface UsernameUnique {

    String message() default "username must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
