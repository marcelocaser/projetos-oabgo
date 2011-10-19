package br.org.oabgo.saeo.negocio.controle.negocio.interfaces;

import java.util.List;

import br.org.oabgo.saeo.negocio.controle.entidade.CertameTO;
import br.org.oabgo.saeo.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

public interface Certame {
	
	public void alterar(CertameTO certame,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public void excluir(CertameTO certame,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public void incluir(CertameTO certame,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public CertameTO consultar(CertameTO certame)
			throws BDException;

	public List<TransferObject> listar(CertameTO certame)
			throws BDException;

	public List<TransferObject> listar(CertameTO certame,
			String orderBy) throws BDException;
	
	public void retirarObjetoSessao(CertameTO certame);

}
