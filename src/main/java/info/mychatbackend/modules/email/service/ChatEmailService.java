package info.mychatbackend.modules.email.service;

import info.mychatbackend.modules.chat.systemUser.model.ChatSystemUser;
import info.mychatbackend.modules.email.model.ChatEmail;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class ChatEmailService {

    private JavaMailSender mailSender;
    private TemplateEngine templateEngine;

    public ChatEmailService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public ChatEmail sendRegistrationMail(ChatSystemUser user) throws MessagingException {
        ChatEmail email = prepareRegistrationMail(user);
        Context context = prepareRegistrationContext(user);
        MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        MimeMessageHelper message = prepareMessage(mimeMessage, email);

        String htmlContent = this.templateEngine.process(email.getTemplateName(), context);
        message.setText(htmlContent, true);
        email.setEmailedMessage(htmlContent);
        this.mailSender.send(mimeMessage);

        this.templateEngine.clearTemplateCache();

        return email;
    }

    private ChatEmail prepareRegistrationMail(ChatSystemUser user) {
        ChatEmail email = new ChatEmail();
        email.setTo(user.getEmail());
        email.setSubject("todo app - confirm registration");
        email.setFrom("todo@app");
        email.setTemplateName("registration");
        return email;
    }

    private Context prepareRegistrationContext(ChatSystemUser user) {
        Context context = new Context();
        context.setVariable("name", user.getFirstName().concat(" ".concat(user.getLastName())));
        context.setVariable("activationLink", "http://localhost:4200/activation/".concat(user.getActivationCode()));
        return context;
    }

    private MimeMessageHelper prepareMessage(MimeMessage mimeMessage, ChatEmail chatEmail) throws MessagingException {
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, "UTF-8");
        message.setSubject(chatEmail.getSubject());
        message.setFrom(chatEmail.getFrom());
        message.setTo(chatEmail.getTo());
        return message;
    }

}
