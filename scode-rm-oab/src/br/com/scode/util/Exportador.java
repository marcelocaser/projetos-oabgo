package br.com.scode.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.commons.httpclient.HttpException;

public class Exportador implements Runnable {
	
	Mail mail = new Mail();
	
	private Thread clockThread = null;
	
	public void start() {
        if (clockThread == null) {
            clockThread = new Thread(this, "Exportador");
            clockThread.start();
        }
    }

	public void run() {
		Thread myThread = Thread.currentThread();
		while (clockThread == myThread) {
			try {
				ImportarRequisicoesDoRm();
				RetornarConteudo();
			} catch (Throwable e) {
				try {
					mail.sendMail(e.toString());
					//JOptionPane.showMessageDialog(null, e, "Ops...", JOptionPane.ERROR_MESSAGE);
				} catch (AddressException e1) {
					//JOptionPane.showMessageDialog(null, e, "Ops...", JOptionPane.ERROR_MESSAGE);
				} catch (UnsupportedEncodingException e1) {
					//JOptionPane.showMessageDialog(null, e, "Ops...", JOptionPane.ERROR_MESSAGE);
				} catch (MessagingException e1) {
					//JOptionPane.showMessageDialog(null, e, "Ops...", JOptionPane.ERROR_MESSAGE);
				}
			}
			try {
				Thread.sleep(40000);
			} catch (InterruptedException  e) {
				try {
					mail.sendMail(e.toString());
					//JOptionPane.showMessageDialog(null, e, "Ops...", JOptionPane.ERROR_MESSAGE);
				} catch (AddressException e1) {
					//JOptionPane.showMessageDialog(null, e, "Ops...", JOptionPane.ERROR_MESSAGE);
				} catch (UnsupportedEncodingException e1) {
					//JOptionPane.showMessageDialog(null, e, "Ops...", JOptionPane.ERROR_MESSAGE);
				} catch (MessagingException e1) {
					//JOptionPane.showMessageDialog(null, e, "Ops...", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	public void stop() {
		clockThread = null;
	}

	public Exportador() {
	}
	
	public void ImportarRequisicoesDoRm() throws Throwable {
		new Importador().importarRequisicoesDoRm();
	}
	
	public void RetornarConteudo() throws HttpException, IOException, Throwable {
		Requisicao req = new Requisicao();
		new ComunicadorComScode().retornarConteudo(req.getParametros());
	}

}