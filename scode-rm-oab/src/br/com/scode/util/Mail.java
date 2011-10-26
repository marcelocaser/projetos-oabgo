package br.com.scode.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

	public class SMTPAuthenticator extends Authenticator {
		protected PasswordAuthentication getPasswordAuthentication() {
			String username = "webmaster@oabgo.org.br";
			String password = "webmaster00";
			return new PasswordAuthentication(username, password);
		}
	}

	/**
	 * Dispara e-mail de lembrete de senha para os usu�rios do SIGED.
	 *
	 * @param usuario
	 * @throws AddressException
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException - 
	 * @return void
	 */
	public void sendMail(String mensagemErro) throws AddressException, MessagingException, UnsupportedEncodingException {

		Properties properties = new Properties();

		properties.put("mail.smtp.host", "smtp.oabgo.org.br");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true"); 

		Authenticator authenticator = new SMTPAuthenticator();
		Session session = Session.getDefaultInstance(properties, authenticator);
		
		Message message = new MimeMessage(session);
		
		InternetAddress destinatario = new InternetAddress("ti@oabgo.org.br");
		InternetAddress destinatario2 = new InternetAddress("fabricio@scode.com.br");
		InternetAddress destinatario3 = new InternetAddress("leonardop@oabgo.org.br");
		InternetAddress remetente = new InternetAddress("webmaster@oabgo.org.br");

		remetente.setPersonal("ORDEM DOS ADVOGADOS DO BRASIL - SEÇÃO DE GOIÁS");
		
		message.setFrom(remetente);
		
		message.setRecipient(Message.RecipientType.TO, destinatario);
		message.setRecipient(Message.RecipientType.CC, destinatario2);
		message.setRecipient(Message.RecipientType.BCC, destinatario3);
		
		message.setSubject("Erro no ScodeRm");
		
		StringBuffer mensagem = new StringBuffer();
		
		mensagem.append("<img src='http://web.oabgo.org.br/siged/imagens/imagemEmailUrgente.jpg'/>");
		mensagem.append("<p>");
		mensagem.append("Erro ao tentar executar o ScodeRm:");
		mensagem.append("<p>");
		mensagem.append(mensagemErro);
		mensagem.append("<p><p>");
		mensagem.append("Cordialmente,");
		mensagem.append("<p>");
		mensagem.append("<em>Tecnologia da Informacao");
		mensagem.append("<br>");
		mensagem.append("(62) 3238-2025");
		
		message.setDataHandler(message.getDataHandler());

		message.setContent(mensagem.toString(), "text/html");

		Transport.send(message);
	}

}
