package info.mychatbackend.modules.chatSystemUser.validator;

import info.mychatbackend.modules.chatSystemUser.service.ChatSystemUserService;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<UsernameUnique, String> {

    private ChatSystemUserService userService;

    public UsernameValidator(ChatSystemUserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(UsernameUnique constraintAnnotation) {

    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isEmpty(username)) {
            return true;
        }
        return userService.getByUsername(username) == null;
    }


}
