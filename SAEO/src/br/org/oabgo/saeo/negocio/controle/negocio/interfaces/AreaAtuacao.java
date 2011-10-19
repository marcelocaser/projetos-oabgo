package br.org.oabgo.saeo.negocio.controle.negocio.interfaces;

import java.util.List;

import br.org.oabgo.saeo.negocio.controle.entidade.AreaAtuacaoTO;
import br.org.oabgo.saeo.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

public interface AreaAtuacao {
	
	public void alterar(AreaAtuacaoTO areaAtuacao,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public void excluir(AreaAtuacaoTO areaAtuacao,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public void incluir(AreaAtuacaoTO areaAtuacao,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public AreaAtuacaoTO consultar(AreaAtuacaoTO areaAtuacao)
			throws BDException;

	public List<TransferObject> listar(AreaAtuacaoTO areaAtuacao)
			throws BDException;

	public List<TransferObject> listar(AreaAtuacaoTO areaAtuacao,
			String orderBy) throws BDException;
	
	public List<TransferObject> listarPorStatus(Boolean status) throws BDException;
	
	public void retirarObjetoSessao(AreaAtuacaoTO areaAtuacao);

}
