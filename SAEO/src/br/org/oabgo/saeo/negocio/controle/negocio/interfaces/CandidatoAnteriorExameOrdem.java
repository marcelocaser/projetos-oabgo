package br.org.oabgo.saeo.negocio.controle.negocio.interfaces;

import java.util.List;

import br.org.oabgo.saeo.negocio.controle.entidade.CandidatoAnteriorExameOrdemTO;
import br.org.oabgo.saeo.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.enumeration.OrderType;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

public interface CandidatoAnteriorExameOrdem {
	
	public void alterar(CandidatoAnteriorExameOrdemTO candidatoAnteriorExameOrdem,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public void excluir(CandidatoAnteriorExameOrdemTO candidatoAnteriorExameOrdem,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public void incluir(CandidatoAnteriorExameOrdemTO candidatoAnteriorExameOrdem,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public CandidatoAnteriorExameOrdemTO consultar(CandidatoAnteriorExameOrdemTO candidatoAnteriorExameOrdem)
			throws BDException;

	public List<TransferObject> listar(CandidatoAnteriorExameOrdemTO candidatoAnteriorExameOrdem)
			throws BDException;

	public List<TransferObject> listar(CandidatoAnteriorExameOrdemTO candidatoAnteriorExameOrdem,
			String orderBy) throws BDException;
	
	public List<TransferObject> listar(CandidatoAnteriorExameOrdemTO candidatoAnteriorExameOrdem,
			String orderBy, OrderType orderType) throws BDException;
	
	public List<TransferObject> listarDezUltimos() throws BDException;
	
	public void retirarObjetoSessao(CandidatoAnteriorExameOrdemTO candidatoAnteriorExameOrdem);

}
