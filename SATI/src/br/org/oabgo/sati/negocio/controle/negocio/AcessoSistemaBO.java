package br.org.oabgo.sati.negocio.controle.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.org.oabgo.sati.negocio.controle.entidade.AcessoSistemaTO;
import br.org.oabgo.sati.negocio.controle.entidade.SistemaTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.AcessoSistema;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.Sistema;
import br.org.oabgo.sati.negocio.controle.persistencia.AcessoSistemaPO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;
import core.utilitario.BeanUtil;
import core.utilitario.Data;

/**
 * <b>Classe:</b> AcessoSistemaBO.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> SATI <br>
 * <b>Pacote:</b> br.org.oabgo.sati.negocio.controle.negocio <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Leonardo Peixoto
 * @version Revision: $ Date: $
 */
@Service
public class AcessoSistemaBO implements AcessoSistema {

	@Autowired
	private AcessoSistemaPO	persistencia;
	
	@Autowired
	private Sistema		negocioSistema;
	
	/**
	 * Persiste as alterações no objeto passado como parâmetro.
	 * 
	 * @param acessoSistema - AcessoSistemaTO
	 * @param usuarioLogado - UsuarioTO
	 * @throws BDException
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void alterar(AcessoSistemaTO acessoSistema, UsuarioTO usuarioLogado) throws BDException {
		this.persistencia.alterar(acessoSistema, usuarioLogado);
	}
	
	/**
	 * Exclui um objeto AcessoSistemaTO.
	 * 
	 * @param acessoSistema - AcessoSistemaTO
	 * @param usuarioLogado - UsuarioTO
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void excluir(AcessoSistemaTO acessoSistema, UsuarioTO usuarioLogado)throws RegraNegocioException, BDException {
		this.persistencia.excluir(acessoSistema, usuarioLogado);
	}
	
	/**
	 * Persiste as informações no objeto passado como parâmetro.
	 * 
	 * @param acessoSistema - AcessoSistemaTO
	 * @param usuarioLogado - UsuarioTO
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void incluir(AcessoSistemaTO acessoSistema, UsuarioTO usuarioLogado)throws BDException {
		this.persistencia.incluir(acessoSistema, usuarioLogado);
	}

	/**
	 * Consulta um objeto AcessoSistemaTO com base no ID.
	 * 
	 * @param acessoSistema - AcessoSistemaTO
	 * @param usuarioLogado - UsuarioTO
	 * @return AcessoSistemaTO
	 * 
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public AcessoSistemaTO consultar(AcessoSistemaTO acessoSistema)throws BDException {
		return this.persistencia.consultar(acessoSistema);
	}
	
	/**
	 * Retorna uma lista de objetos AcessoSistemaTO.
	 * 
	 * @param acessoSistema - AcessoSistemaTO
	 * @param usuarioLogado - UsuarioTO
	 * @return List<TransferObject>
	 * 
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public List<TransferObject> listar(AcessoSistemaTO acessoSistema, String orderBy)throws BDException {
		return this.persistencia.listar(acessoSistema, orderBy);
	}

	/**
	 * Retorna o primeiro objeto que satisfaça os parâmetros de busca.
	 * 
	 * @param acessoSistema - AcessoSistemaTO
	 * @param usuarioLogado - UsuarioTO
	 * @return AcessoSistemaTO
	 * 
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public AcessoSistemaTO buscarObjeto(AcessoSistemaTO acessoSistema)throws BDException {
		return this.persistencia.buscarObjeto(acessoSistema);
	}
	
	/**
	 * Remove o objeto atual da Session do Hibernate.
	 * @param sistema - AcessoSistemaTO
	 * @throws BDException
	 */
	public void retirarObjetoSessao(AcessoSistemaTO acessoSistema) throws BDException{
		this.persistencia.retirarObjetoSessao(acessoSistema);
	}

	/**
	 * Exclui todos os acessoSistemas realizados em um período menor ou igual ao
	 * da data informada.
	 * 
	 * @param dataLimite - Date
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void excluirAcessosAntigos(Date dataLimite)throws RegraNegocioException, BDException {
		this.persistencia.excluirAcessosAntigos(dataLimite);
	}

	/**
	 * Exclui todos os acessoSistema vinculados a usuário passado como parametro.
	 * 
	 * @param acessoSistema - AcessoSistemaTO
	 * @param usuarioLogado - UsuarioTO
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void excluirAcessoUsuario(UsuarioTO usuario, UsuarioTO usuarioLogado)throws RegraNegocioException, BDException {
		AcessoSistemaTO acessoSistema = new AcessoSistemaTO();
		acessoSistema.setUsuario(new UsuarioTO(usuario.getId()));
		List<TransferObject> listaAcesso = this.listar(acessoSistema, "id");
		this.persistencia.excluir(listaAcesso, usuarioLogado);
	}

	/**
	 * Retorna a quantidade de registros para encontrada para o objeto.
	 * 
	 * @param acessoSistema - AcessoSistemaTO
	 * @return Integer
	 * @throws BDException
	 */
	public Integer count(AcessoSistemaTO acessoSistema) throws BDException{
		return this.persistencia.count(acessoSistema);
	}
	
	/**
	 * Retorna uma lista contendo a quantidade de usuários on-line
	 * nas aplicações gerênciadas pelo ADM.
	 * @return List<BeanUtil>
	 */
	public List<BeanUtil> gerarListaAcessoAtivo(){
		List<BeanUtil> retorno = new ArrayList<BeanUtil>();
		List<TransferObject> listaSistema = this.negocioSistema.listar(new SistemaTO(), "id");
		for(TransferObject bean : listaSistema){
			SistemaTO sistema = (SistemaTO)bean;
			//busca todos os acessoSistemas ativos de todos os sistemas
			AcessoSistemaTO acessoSistemaTmp = new AcessoSistemaTO();
			acessoSistemaTmp.setBitAcessoAtivo(Boolean.TRUE);
			acessoSistemaTmp.setSistema(new SistemaTO(sistema.getId()));
			
			BeanUtil util = new BeanUtil();
			util.setBean(sistema);
			retorno.add(util);
		}
		return retorno;
	}
	
	/**
	 * Registra o acessoSistema do usuário no Controle de acessoSistema.
	 *
	 * @param usuario - {@link UsuarioTO}
	 * @param request - {@link HttpServletRequest}
	 * @return AcessoSistemaTO
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public AcessoSistemaTO registrarAcesso(UsuarioTO usuario, HttpServletRequest request, UsuarioTO usuarioAdministrador) throws RegraNegocioException, BDException{
		//consulta o sistema ao qual o ACESSO esta sendo realizado
		SistemaTO sistema = negocioSistema.consultarSistemaContexto(request.getContextPath());
		if(sistema == null){
			throw new RegraNegocioException("Sistema com contexto ["+request.getContextPath()+"] não reconhecido pelo ADM, verifique a propriedade 'CONTEXTO' da tabela de MENU.");
		}
		
		AcessoSistemaTO acessoSistema = new AcessoSistemaTO();
		acessoSistema.setVchCookie(request.getRequestedSessionId());
		acessoSistema.setVchIp(request.getRemoteAddr());
		acessoSistema.setBitAcessoAtivo(Boolean.TRUE);
		//verifica se já existe um acessoSistema com o mesmo id da sessão
		AcessoSistemaTO acessoSistemaTmp = this.buscarObjeto(acessoSistema);
		if((acessoSistemaTmp != null && acessoSistemaTmp.getUsuario().getId().equals(usuario.getId())) || acessoSistemaTmp == null){
			acessoSistema.setUsuario(usuario);
			acessoSistema.setSistema(sistema);
			acessoSistema.setDatDataAcesso(Data.formatDate(new Date(), Data.DIA_HORAS));			
			this.incluir(acessoSistema, usuarioAdministrador);
		}else{
			acessoSistemaTmp.setBitAcessoAtivo(Boolean.FALSE);
			this.alterar(acessoSistemaTmp, usuarioAdministrador);
			
			//registra o novo acessoSistema
			acessoSistema.setUsuario(usuario);
			acessoSistema.setSistema(sistema);
			acessoSistema.setDatDataAcesso(Data.formatDate(new Date(), Data.DIA_HORAS));			
			this.incluir(acessoSistema, usuarioAdministrador);
		}
		return acessoSistema;
	}
	
	/**
	 * Desativa o acessoSistema do usuário às aplicações gerenciadas pelo ADM.
	 * 
	 * @param usuario - {@link UsuarioTO}
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void desativarAcessoUsuario(UsuarioTO usuario)throws BDException {
		AcessoSistemaTO acessoSistema = new AcessoSistemaTO();
        acessoSistema.setUsuario(new UsuarioTO(usuario.getId()));
        acessoSistema.setBitAcessoAtivo(new Boolean(true));
        
        List<TransferObject> listaAcessoAtivo = this.listar(acessoSistema, "id");
        //busca o usuário com perfil de administrador
        for(TransferObject bean : listaAcessoAtivo){
        	AcessoSistemaTO acessoSistemaAtivo = (AcessoSistemaTO)bean;
        	acessoSistemaAtivo.setBitAcessoAtivo(Boolean.FALSE);
        	this.alterar(acessoSistemaAtivo, usuario);	
        }
	}

	/**
	 * Desativa o acessoSistema do usuário às aplicações gerenciadas pelo ADM.
	 * 
	 * @param usuario - {@link UsuarioTO}
	 * @param idSessao - {@link String}
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void desativarAcessoUsuario(UsuarioTO usuario, String sessionId)throws BDException {
		AcessoSistemaTO acessoSistema = new AcessoSistemaTO();
		acessoSistema.setUsuario(new UsuarioTO(usuario.getId()));
		acessoSistema.setBitAcessoAtivo(Boolean.TRUE);
		acessoSistema.setVchCookie(sessionId);
		
		List<TransferObject> listaAcessoAtivo = this.listar(acessoSistema, "id");
		//busca o usuário com perfil de administrador
		for(TransferObject bean : listaAcessoAtivo){
			AcessoSistemaTO acessoSistemaAtivo = (AcessoSistemaTO)bean;
			acessoSistemaAtivo.setBitAcessoAtivo(Boolean.FALSE);
			this.alterar(acessoSistemaAtivo, usuario);	
		}
	}
}
