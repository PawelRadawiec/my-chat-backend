package info.mychatbackend.modules.chat.systemUser.validator;

import info.mychatbackend.generic.model.GenericValidator;
import info.mychatbackend.modules.chat.systemUser.model.Address;
import info.mychatbackend.modules.chat.systemUser.model.ChatSystemUser;
import info.mychatbackend.modules.chat.systemUser.model.Registration;
import info.mychatbackend.modules.chat.systemUser.model.RegistrationStep;
import info.mychatbackend.modules.chat.systemUser.repository.ChatSystemUserRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RegistrationValidator extends GenericValidator implements Validator {

    private ChatSystemUserRepository userRepository;

    public RegistrationValidator(ChatSystemUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Registration.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Registration registration = (Registration) target;
        if (registration.getCurrentStep().equals(RegistrationStep.ACCOUNT)) {
            validateAccountStep(registration, errors);
        }
        if (registration.getCurrentStep().equals(RegistrationStep.ADDRESS)) {
            validateAddressStep(registration, errors);
        }

    }

    private void validateAccountStep(Registration registration, Errors errors) {
        ChatSystemUser user = registration.getUser();
        if (registration.getUser() == null) {
            return;
        }
        validateIfTrue(StringUtils.isEmpty(user.getUsername()), "user.username", ValidationCode.REQUIRED.getValue(), errors);
        validateIfTrue(StringUtils.isEmpty(user.getPassword()), "user.password", ValidationCode.REQUIRED.getValue(), errors);
        validateIfTrue(StringUtils.isEmpty(user.getEmail()), "user.email", ValidationCode.REQUIRED.getValue(), errors);
        validateIfTrue(StringUtils.isEmpty(user.getFirstName()), "user.firstName", ValidationCode.REQUIRED.getValue(), errors);
        validateIfTrue(StringUtils.isEmpty(user.getLastName()), "user.lastName", ValidationCode.REQUIRED.getValue(), errors);

        validateEmailUniqueness(user, errors);
        validateUsernameUniqueness(user, errors);
    }

    private void validateEmailUniqueness(ChatSystemUser user, Errors errors) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            boolean emailExist = this.userRepository.checkIfEmailExist(user.getEmail()).isPresent();
            validateIfTrue(emailExist, "user.email", ValidationCode.UNIQUE.getValue(), errors);
        }
    }

    private void validateUsernameUniqueness(ChatSystemUser user, Errors errors) {
        if (!StringUtils.isEmpty(user.getUsername())) {
            boolean usernameExist = this.userRepository.checkIfUsernameExist(user.getUsername()).isPresent();
            validateIfTrue(usernameExist, "user.username", ValidationCode.UNIQUE.getValue(), errors);
        }
    }

    private void validateAddressStep(Registration registration, Errors errors) {
        if (registration.getUser() == null) {
            return;
        }
        Address address = registration.getUser().getAddress();
        if (address == null) {
            return;
        }
        validateIfTrue(StringUtils.isEmpty(address.getCity()), "user.address.city", ValidationCode.REQUIRED.getValue(), errors);
        validateIfTrue(StringUtils.isEmpty(address.getCountry()), "user.address.country", ValidationCode.REQUIRED.getValue(), errors);
        validateIfTrue(StringUtils.isEmpty(address.getPostalCode()), "user.address.postalCode", ValidationCode.REQUIRED.getValue(), errors);
        validateIfTrue(StringUtils.isEmpty(address.getStreet()), "user.address.street", ValidationCode.REQUIRED.getValue(), errors);
    }

}
