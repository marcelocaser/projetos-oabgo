package br.org.oabgo.sati.negocio.controle.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.org.oabgo.sati.negocio.controle.entidade.TipoMenuSistemaTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.TipoMenuSistema;
import br.org.oabgo.sati.negocio.controle.persistencia.TipoMenuSistemaPO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

/**
 * <b>Classe:</b> TipoMenuSistemaBO.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> SATI <br>
 * <b>Pacote:</b> br.org.oabgo.sati.negocio.controle.negocio <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Marcelo Caser
 * @version Revision: $ Date: $
 */
@Service
public class TipoMenuSistemaBO implements TipoMenuSistema {

	@Autowired
	private TipoMenuSistemaPO persistencia;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void alterar(TipoMenuSistemaTO tipoMenuSistema,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException {
		this.persistencia.alterar(tipoMenuSistema, usuarioLogado);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void excluir(TipoMenuSistemaTO tipoMenuSistema,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException {
		this.persistencia.excluir(tipoMenuSistema, usuarioLogado);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void incluir(TipoMenuSistemaTO tipoMenuSistema,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException {
		this.persistencia.incluir(tipoMenuSistema, usuarioLogado);
	}

	public TipoMenuSistemaTO consultar(TipoMenuSistemaTO tipoMenuSistema)
			throws BDException {
		return this.persistencia.consultar(tipoMenuSistema);
	}

	public List<TransferObject> listar(TipoMenuSistemaTO tipoMenuSistema)
			throws BDException {
		return this.persistencia.listar(tipoMenuSistema);
	}

	public List<TransferObject> listar(TipoMenuSistemaTO tipoMenuSistema,
			String orderBy) throws BDException {
		return this.persistencia.listar(tipoMenuSistema, orderBy);
	}

	public void retirarObjetoSessao(TipoMenuSistemaTO tipoMenuSistema) {
		this.persistencia.retirarObjetoSessao(tipoMenuSistema);
	}

}
