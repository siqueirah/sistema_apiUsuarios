package br.com.cotiinformatica.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	@Autowired // inicialização automática!
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}") // application.properties
	private String from;

	// método para realizar o envio dos emails
	public void send(String to, String subject, String body) throws Exception {

		//montando o conteúdo do email que será enviado..
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
		
		messageHelper.setFrom(from);
		messageHelper.setTo(to);
		messageHelper.setSubject(subject);
		messageHelper.setText(body);
		
		//enviando o email
		javaMailSender.send(mimeMessage);
	}

}
