package br.com.posarquiteturapuc2022.services.impl;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.posarquiteturapuc2022.domain.Associado;
import br.com.posarquiteturapuc2022.domain.enums.TipoStatus;
import br.com.posarquiteturapuc2022.exception.AssociadoCadastradoException;
import br.com.posarquiteturapuc2022.exception.AssociadoFeignNotFoundException;
import br.com.posarquiteturapuc2022.exception.ObjectNotFoundException;
import br.com.posarquiteturapuc2022.feignServicesAssociates.ServicosAssociadoApiFeign;
import br.com.posarquiteturapuc2022.repository.ServicosAssociadosRepository;
import br.com.posarquiteturapuc2022.services.ServicosAssociadosService;
import br.com.posarquiteturapuc2022.utils.GeradorNumeroCarterinha;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ServicosAssociadosServiceImpl implements ServicosAssociadosService{
	
	private final Logger logger = LoggerFactory.getLogger(ServicosAssociadosServiceImpl.class);
	
	private ServicosAssociadoApiFeign associadoApiFeign;

	private ServicosAssociadosRepository servicosAssociadosRepository;

	private Associado associado =  new Associado();
	
	
	private Associado CACHE = new Associado();
	
	@CircuitBreaker(name = "associadoCB", fallbackMethod = "buscaAssociadoApiCpfNoCACHE")
	public Associado buscaAssociadoApiCpf(String cpf) {
		Associado associado = executarRequisicao(cpf);		
		return associado;		
	}

	private Associado executarRequisicao(String cpf) {
		final Associado associado;
		try {
			logger.info("Buscando da API de Usuários");
			associado = associadoApiFeign.findByCpf(cpf).getBody();
			logger.info("Alimentando CACHE");
			CACHE = associado;
		} catch (FeignException.NotFound ex) {
			logger.error("Erro ao buscar novos Usuários!");
			throw new AssociadoFeignNotFoundException("Usuário não encontrado na API de Usuários!");
		}
		logger.info("Erro ao buscar Usuários!");
		return carregaAssociadoConstrutor(associado);
	}
	
	
	@Override
	public Associado solicitarNumeroCarteirinhaCpf(String cpf) {
		Associado associadoRetornoApi = buscaAssociadoApiCpf(cpf);
		
		if (!associadoRetornoApi.getCpfAssociado().equals(cpf)) {
			throw new ObjectNotFoundException("Usuário não encontrado!");
		}
		
		try {
			associadoRetornoApi.setTipoStatus(TipoStatus.SOLICITADO);
			this.associado = associadoRetornoApi;
			return servicosAssociadosRepository.saveAndFlush(this.associado);
		}catch (Exception e) {
			logger.info("Solicitação não pode ser enviada com sucesso!");
			throw new AssociadoCadastradoException("Solicitação não pode ser enviada com sucesso!");
		}
	}
	
	@Override
	public Associado aprovarNumeroCarteirinhaCpf(String cpf) {
		Associado associadoRetornoApi = buscaAssociadoApiCpf(cpf);
		
		if (!associadoRetornoApi.getCpfAssociado().equals(cpf)) {
			throw new ObjectNotFoundException("Usuário não encontrado!");
		}
		
		try {
			associadoRetornoApi.setTipoStatus(TipoStatus.APROVADO);
			this.associado = associadoRetornoApi;
			return servicosAssociadosRepository.saveAndFlush(this.associado);
		}catch (Exception e) {
			logger.info("Solicitação não pode ser enviada com sucesso!");
			throw new AssociadoCadastradoException("Solicitação não pode ser enviada com sucesso!");
		}
	}
	
	@Override
	public Associado gerarNumeroCarteirinhaCpf(String cpf) {
		Associado associadoRetornoApi = buscaAssociadoApiCpf(cpf);
		
		if (!associadoRetornoApi.getCpfAssociado().equals(cpf)) {
			throw new ObjectNotFoundException("Usuário não encontrado!");
		}
		
		try {
			String numeroCarteirinha = GeradorNumeroCarterinha.gerarNumeroAleatorio();
			this.associado = associadoRetornoApi;
			associadoRetornoApi.setNumeroCarteirinhaAssociado(numeroCarteirinha);
			associadoRetornoApi.setTipoStatus(TipoStatus.GERADO);
			return servicosAssociadosRepository.saveAndFlush(this.associado);
		}catch (Exception e) {
			logger.info("Solicitação não pode ser enviada com sucesso!");
			throw new AssociadoCadastradoException("Solicitação não pode ser enviada com sucesso!");
		}
	}
	
	private void gerarNumeroCarteirinha() {
		
	}

	@Override
	public Associado consultarNumeroCarteirinhaCpf(String cpf) {
		Associado associadoRetornoApi = buscaAssociadoApiCpf(cpf);
		
		if (!associadoRetornoApi.getCpfAssociado().equals(cpf)) {
			throw new ObjectNotFoundException("Usuário não encontrado!");
		}
		
		try {
			consultarNumeroCarteirinha();
			associadoRetornoApi.setTipoStatus(TipoStatus.GERADO);
			this.associado = associadoRetornoApi;
			return servicosAssociadosRepository.saveAndFlush(this.associado);
		}catch (Exception e) {
			logger.info("Solicitação não pode ser enviada com sucesso!");
			throw new AssociadoCadastradoException("Solicitação não pode ser enviada com sucesso!");
		}
	}

	private void consultarNumeroCarteirinha() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Associado solicitarNumeroTokenCpf(String cpf) {
		Associado associadoRetornoApi = buscaAssociadoApiCpf(cpf);
		if (!associadoRetornoApi.getCpfAssociado().equals(cpf)) {
			throw new ObjectNotFoundException("Usuário não encontrado!");
		}
		try {
			consultarNumeroCarteirinha();
			associadoRetornoApi.setTipoStatus(TipoStatus.GERADO);
			this.associado = associadoRetornoApi;
			return servicosAssociadosRepository.saveAndFlush(this.associado);
		}catch (Exception e) {
			logger.info("Solicitação não pode ser enviada com sucesso!");
			throw new AssociadoCadastradoException("Solicitação não pode ser enviada com sucesso!");
		}
	}

	@Override
	public Associado aprovarNumeroTokenCpf(String cpf) {
		Associado associadoRetornoApi = buscaAssociadoApiCpf(cpf);
		if (!associadoRetornoApi.getCpfAssociado().equals(cpf)) {
			throw new ObjectNotFoundException("Usuário não encontrado!");
		}
		try {
			consultarNumeroCarteirinha();
			associadoRetornoApi.setTipoStatus(TipoStatus.GERADO);
			this.associado = associadoRetornoApi;
			return servicosAssociadosRepository.saveAndFlush(this.associado);
		}catch (Exception e) {
			logger.info("Solicitação não pode ser enviada com sucesso!");
			throw new AssociadoCadastradoException("Solicitação não pode ser enviada com sucesso!");
		}
	}
	
	@Override
	public Associado geradorNumeroTokenCpf(String cpf) {
		Associado associadoRetornoApi = buscaAssociadoApiCpf(cpf);
		if (!associadoRetornoApi.getCpfAssociado().equals(cpf)) {
			throw new ObjectNotFoundException("Usuário não encontrado!");
		}
		try {
			consultarNumeroCarteirinha();
			associadoRetornoApi.setTipoStatus(TipoStatus.GERADO);
			this.associado = associadoRetornoApi;
			return servicosAssociadosRepository.saveAndFlush(this.associado);
		}catch (Exception e) {
			logger.info("Solicitação não pode ser enviada com sucesso!");
			throw new AssociadoCadastradoException("Solicitação não pode ser enviada com sucesso!");
		}
	}

	@Override
	public Associado solicitarExameCpf(String cpf) {
		Associado associadoRetornoApi = buscaAssociadoApiCpf(cpf);
		if (!associadoRetornoApi.getCpfAssociado().equals(cpf)) {
			throw new ObjectNotFoundException("Usuário não encontrado!");
		}
		try {
			consultarNumeroCarteirinha();
			associadoRetornoApi.setTipoStatus(TipoStatus.GERADO);
			this.associado = associadoRetornoApi;
			return servicosAssociadosRepository.saveAndFlush(this.associado);
		}catch (Exception e) {
			logger.info("Solicitação não pode ser enviada com sucesso!");
			throw new AssociadoCadastradoException("Solicitação não pode ser enviada com sucesso!");
		}
	}

	@Override
	public Associado aprovarExameCpf(String cpf) {
		Associado associadoRetornoApi = buscaAssociadoApiCpf(cpf);
		if (!associadoRetornoApi.getCpfAssociado().equals(cpf)) {
			throw new ObjectNotFoundException("Usuário não encontrado!");
		}
		try {
			consultarNumeroCarteirinha();
			associadoRetornoApi.setTipoStatus(TipoStatus.GERADO);
			this.associado = associadoRetornoApi;
			return servicosAssociadosRepository.saveAndFlush(this.associado);
		}catch (Exception e) {
			logger.info("Solicitação não pode ser enviada com sucesso!");
			throw new AssociadoCadastradoException("Solicitação não pode ser enviada com sucesso!");
		}
	}

	@Override
	public Associado solicitarConsultaCpf(String cpf) {
		Associado associadoRetornoApi = buscaAssociadoApiCpf(cpf);
		if (!associadoRetornoApi.getCpfAssociado().equals(cpf)) {
			throw new ObjectNotFoundException("Usuário não encontrado!");
		}
		try {
			consultarNumeroCarteirinha();
			associadoRetornoApi.setTipoStatus(TipoStatus.GERADO);
			this.associado = associadoRetornoApi;
			return servicosAssociadosRepository.saveAndFlush(this.associado);
		}catch (Exception e) {
			logger.info("Solicitação não pode ser enviada com sucesso!");
			throw new AssociadoCadastradoException("Solicitação não pode ser enviada com sucesso!");
		}
	}
	@Override
	public Associado aprovarConsultaCpf(String cpf) {
		Associado associadoRetornoApi = buscaAssociadoApiCpf(cpf);
		
		if (!associadoRetornoApi.getCpfAssociado().equals(cpf)) {
			throw new ObjectNotFoundException("Usuário não encontrado!");
		}
		try {
			consultarNumeroCarteirinha();
			associadoRetornoApi.setTipoStatus(TipoStatus.GERADO);
			this.associado = associadoRetornoApi;
			return servicosAssociadosRepository.saveAndFlush(this.associado);
		}catch (Exception e) {
			logger.info("Solicitação não pode ser enviada com sucesso!");
			throw new AssociadoCadastradoException("Solicitação não pode ser enviada com sucesso!");
		}
	}

	@Override
	public Associado solicitarProcedimentoCpf(String cpf) {
		Associado associadoRetornoApi = buscaAssociadoApiCpf(cpf);
		
		if (!associadoRetornoApi.getCpfAssociado().equals(cpf)) {
			throw new ObjectNotFoundException("Usuário não encontrado!");
		}
		try {
			consultarNumeroCarteirinha();
			associadoRetornoApi.setTipoStatus(TipoStatus.GERADO);
			this.associado = associadoRetornoApi;
			return servicosAssociadosRepository.saveAndFlush(this.associado);
		}catch (Exception e) {
			logger.info("Solicitação não pode ser enviada com sucesso!");
			throw new AssociadoCadastradoException("Solicitação não pode ser enviada com sucesso!");
		}
	}
	
	@Override
	public Associado aprovarProcedimentoCpf(String cpf) {
		Associado associadoRetornoApi = buscaAssociadoApiCpf(cpf);
		if (!associadoRetornoApi.getCpfAssociado().equals(cpf)) {
			throw new ObjectNotFoundException("Usuário não encontrado!");
		}
		try {
			consultarNumeroCarteirinha();
			associadoRetornoApi.setTipoStatus(TipoStatus.GERADO);
			this.associado = associadoRetornoApi;
			return servicosAssociadosRepository.saveAndFlush(this.associado);
		}catch (Exception e) {
			logger.info("Solicitação não pode ser enviada com sucesso!");
			throw new AssociadoCadastradoException("Solicitação não pode ser enviada com sucesso!");
		}
	}
	
	@Override
	public Associado validarDadosAssociado(String cpf) {
		Associado associadoRetornoApi = buscaAssociadoApiCpf(cpf);
		if (!associadoRetornoApi.getCpfAssociado().equals(cpf)) {
			throw new ObjectNotFoundException("Usuário não encontrado!");
		}
		try {
			consultarNumeroCarteirinha();
			associadoRetornoApi.setTipoStatus(TipoStatus.GERADO);
			this.associado = associadoRetornoApi;
			return servicosAssociadosRepository.saveAndFlush(this.associado);
		}catch (Exception e) {
			logger.info("Solicitação não pode ser enviada com sucesso!");
			throw new AssociadoCadastradoException("Solicitação não pode ser enviada com sucesso!");
		}
	}

	private Associado carregaAssociadoConstrutor(Associado associado) {
		if (Objects.nonNull(associado)) {
			logger.info("Buscando da API de Usuários");
			return new Associado( 
					associado.getId(), 
					associado.getNomeAssociado(), 
					associado.getCpfAssociado(), 
					associado.getNumeroCarteirinhaAssociado(),
					associado.getEmailAssociado(),
					associado.getSexo(),
					associado.getCep(), 
					associado.getEndereco(), 
					associado.getNumero(), 
					associado.getBairro(), 
					associado.getCidade(), 
					associado.getUf(), 
					associado.getEstadoCivil(), 
					associado.getNivelFormacao(), 
					associado.getProfissao(), 
					associado.getDataNascimento(), 
					associado.getDataCadastro(), 
					associado.getIdade(), 
					associado.getCodigoPlano(), 
					associado.getValorPlanoMensal(), 
					associado.getTipoCobertura(), 
					associado.getTipoStatus(),
					associado.getTipoCategoria(), 
					associado.getTipoPlanoSaude(), 
					associado.getTipoUsuario(),
					associado.isOdontologico(), 
					associado.isSituacao()
				);
		}else {
			return CACHE;
		}
	}
	
	private Associado buscaAssociadoApiCpfNoCACHE(String cpf, Throwable e){
		logger.info("Buscando Usuário no CACHE");
		return CACHE;
	}
}
