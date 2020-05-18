package info.mychatbackend.modules.chat.systemUser.service;

import info.mychatbackend.modules.chat.contact.model.ChatContact;
import info.mychatbackend.modules.chat.contact.repository.ChatContactRepository;
import info.mychatbackend.modules.chat.contact.service.ContentContactsService;
import info.mychatbackend.modules.chat.systemUser.helper.SystemUserHelper;
import info.mychatbackend.modules.chat.systemUser.model.ChatSystemUser;
import info.mychatbackend.modules.chat.systemUser.model.Registration;
import info.mychatbackend.modules.chat.systemUser.model.RegistrationStep;
import info.mychatbackend.modules.chat.systemUser.repository.ChatSystemUserRepository;
import info.mychatbackend.modules.email.service.ChatEmailService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.List;

@Service
public class ChatSystemUserService implements ChatSystemUserOperations {

    private ChatSystemUserRepository repository;
    private SystemUserHelper systemUserHelper;
    private ContentContactsService contactsService;
    private ChatContactRepository contactRepository;
    private ChatEmailService emailService;

    public ChatSystemUserService(ChatSystemUserRepository repository, SystemUserHelper systemUserHelper, ContentContactsService contactsService, ChatContactRepository contactRepository, ChatEmailService emailService) {
        this.repository = repository;
        this.systemUserHelper = systemUserHelper;
        this.contactsService = contactsService;
        this.contactRepository = contactRepository;
        this.emailService = emailService;
    }

    @Override
    @Transactional
    public ChatSystemUser save(ChatSystemUser systemUser) {
        systemUserHelper.setPasswordHash(systemUser);
        repository.save(systemUser);
        contactsService.create(systemUser);
        contactRepository.create(new ChatContact(systemUser.getUsername()));
        return systemUser;
    }

    @Override
    public ChatSystemUser getByUsername(String username) {
        return repository.getByUsername(username).orElse(new ChatSystemUser());
    }

    @Override
    public List<ChatSystemUser> getUserList() {
        return repository.getUserList();
    }

    @Override
    public List<ChatSystemUser> search(String username) {
        return repository.search(username);
    }

    @Override
    public Registration save(Registration registration) {
        switch (registration.getCurrentStep()) {
            case ACCOUNT:
                accountStep(registration);
                break;
            case ADDRESS:
                addressSteps(registration);
                break;
            case ACTIVATION:
                activationStep(registration);
            default:
        }
        return registration;
    }

    private void accountStep(Registration registration) {
        registration.setCurrentStep(RegistrationStep.ADDRESS);
        registration.setNextStep(RegistrationStep.ACTIVATION);
        repository.save(registration.getUser());
    }

    private void addressSteps(Registration registration) {
        registration.setCurrentStep(RegistrationStep.ACTIVATION);
        registration.setPreviousStep(RegistrationStep.ADDRESS);
        repository.save(registration.getUser());
    }

    private void activationStep(Registration registration) {
        registration.setPreviousStep(RegistrationStep.ADDRESS);
        try {
            registration.getUser().setActivationCode(RandomStringUtils.random(20, false, true));
            emailService.sendRegistrationMail(registration.getUser());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
