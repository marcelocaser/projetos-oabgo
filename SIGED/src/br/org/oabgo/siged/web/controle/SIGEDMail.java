package br.org.oabgo.siged.web.controle;

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

import br.org.oabgo.siged.negocio.controle.entidade.UsuarioTO;

public class SIGEDMail {

	public class SMTPAuthenticator extends Authenticator {
		protected PasswordAuthentication getPasswordAuthentication() {
			String username = "webmaster@oabgo.org.br";
			String password = "webmaster00";
			return new PasswordAuthentication(username, password);
		}
	}

	/**
	 * Dispara e-mail de lembrete de senha para os usuários do SIGED.
	 *
	 * @param usuario
	 * @throws AddressException
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException - 
	 * @return void
	 */
	public void sendMail(UsuarioTO usuario) throws AddressException, MessagingException, UnsupportedEncodingException {

		Properties properties = new Properties();

		properties.put("mail.smtp.host", "smtp.oabgo.org.br");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true"); 

		Authenticator authenticator = new SMTPAuthenticator();
		Session session = Session.getDefaultInstance(properties, authenticator);
		
		Message message = new MimeMessage(session);
		
		InternetAddress destinatario = new InternetAddress(usuario.getVchEmail());
		InternetAddress remetente = new InternetAddress("webmaster@oabgo.org.br");

		remetente.setPersonal("ORDEM DOS ADVOGADOS DO BRASIL - SEÇÃO DE GOIÁS");
		
		message.setFrom(remetente);
		
		message.setRecipient(Message.RecipientType.TO, destinatario);
		
		message.setSubject("Reenvio de Senha");
		
		StringBuffer mensagem = new StringBuffer();
		
		mensagem.append("<img src='http://web.oabgo.org.br/siged/imagens/imagemEmailUrgente.jpg'/>");
		mensagem.append("<p>");
		mensagem.append("O seu login e senha de acesso ao SIGED:");
		mensagem.append("<p>");
		mensagem.append("<b>Nome: </b>");
		mensagem.append(usuario.getVchNome());
		mensagem.append("<br>");
		mensagem.append("<b>Login/Usuário: </b>");
		mensagem.append(usuario.getVchLogin());
		mensagem.append("<br>");
		mensagem.append("<b>Senha: </b>");
		mensagem.append(usuario.getVchSenha());
		mensagem.append("<p><p>");
		mensagem.append("Cordialmente,");
		mensagem.append("<p>");
		mensagem.append("<em>Tecnologia da Informação</em>");
		mensagem.append("<p>");
		mensagem.append("OAB-GO");
		mensagem.append("<br>");
		mensagem.append("(62) 3238-2000");
		mensagem.append("<p>");
		mensagem.append("http://web.oabgo.org.br/siged");
		
		message.setDataHandler(message.getDataHandler());

		message.setContent(mensagem.toString(), "text/html");

		Transport.send(message);
	}

}
