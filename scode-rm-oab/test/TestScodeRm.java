import java.io.IOException;
import java.sql.Connection;

import junit.framework.TestCase;

import org.apache.commons.httpclient.HttpException;

import br.com.scode.util.ComunicadorComScode;
import br.com.scode.util.DatabaseUtil;
import br.com.scode.util.Importador;
import br.com.scode.util.Requisicao;

public class TestScodeRm extends TestCase {

	public void testConecta() throws Throwable {
		Connection cn = DatabaseUtil.getDb().conecta();
		assertFalse(cn.isClosed());
	}

	public void testImportarRequisicoesDoRm() throws Throwable {
		new Importador().importarRequisicoesDoRm();
	}

	public void testRetornarConteudo() throws HttpException, IOException, Throwable {
		Requisicao req = new Requisicao();
		new ComunicadorComScode().retornarConteudo(req.getParametros());
	}
}
