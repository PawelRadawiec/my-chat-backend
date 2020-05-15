package info.mychatbackend.modules.chat.systemUser.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Registration {

    private ChatSystemUser user;

    private RegistrationStep previousStep;

    private RegistrationStep currentStep;

    private RegistrationStep nextStep;

}
