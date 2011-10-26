import javax.swing.JOptionPane;

import br.com.scode.util.Exportador;

public class ScodeRm {

	public static void main(String[] args) {

		Exportador exportador = new Exportador();

		exportador.start();

		JOptionPane.showMessageDialog(null, "Favor, não fechar!",
				"Integração SCODE/TOTVS - RM - v.1.0",
				JOptionPane.INFORMATION_MESSAGE);

		switch (JOptionPane.OK_OPTION) {
		case 0:
			exportador.stop();
			break;
		default:
			break;
		}
	}
}
