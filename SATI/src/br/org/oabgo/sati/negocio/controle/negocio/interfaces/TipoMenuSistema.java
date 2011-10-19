package br.org.oabgo.sati.negocio.controle.negocio.interfaces;

import java.util.List;

import br.org.oabgo.sati.negocio.controle.entidade.TipoMenuSistemaTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

/**
 * <b>Classe:</b> TipoMenuSistema.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> SATI <br>
 * <b>Pacote:</b> br.org.oabgo.sati.negocio.controle.negocio.interfaces <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Marcelo Caser
 * @version Revision: $ Date: $
 */
public interface TipoMenuSistema {
	
	public void alterar(TipoMenuSistemaTO tipoMenuSistema,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public void excluir(TipoMenuSistemaTO tipoMenuSistema,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public void incluir(TipoMenuSistemaTO tipoMenuSistema,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	public TipoMenuSistemaTO consultar(TipoMenuSistemaTO tipoMenuSistema)
			throws BDException;

	public List<TransferObject> listar(TipoMenuSistemaTO tipoMenuSistema)
			throws BDException;

	public List<TransferObject> listar(TipoMenuSistemaTO tipoMenuSistema,
			String orderBy) throws BDException;
	
	public void retirarObjetoSessao(TipoMenuSistemaTO tipoMenuSistema);

}
