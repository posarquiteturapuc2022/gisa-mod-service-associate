package br.com.posarquiteturapuc2022.resources.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.posarquiteturapuc2022.domain.Associado;
import br.com.posarquiteturapuc2022.resources.ServicosAssociadosResource;
import br.com.posarquiteturapuc2022.services.ServicosAssociadosService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController 
@EnableAutoConfiguration 
@RequestMapping(value = "/v1/api/servicosassociados")
@Component
public class ServicosAssociadosResourceImpl implements ServicosAssociadosResource {

	@Autowired
	private final ServicosAssociadosService servicosAssociadosService;
	
	@Override
	public ResponseEntity<Associado> solicitarNumeroCarteirinhaCpf(String cpf) {
		return ResponseEntity.ok().body(servicosAssociadosService.solicitarNumeroCarteirinhaCpf(cpf));
	}
	
	@Override
	public Associado aprovarNumeroCarteirinhaCpf(String cpf) {
		return servicosAssociadosService.aprovarNumeroCarteirinhaCpf(cpf);
	}
	
	@Override
	public ResponseEntity<Associado> gerarNumeroCarteirinhaCpf(String cpf) {
		return ResponseEntity.ok().body(servicosAssociadosService.gerarNumeroCarteirinhaCpf(cpf));
	}
	
	@Override
	public ResponseEntity<Associado> solicitarNumeroTokenCpf(String cpf) {
		return ResponseEntity.ok().body(servicosAssociadosService.solicitarNumeroTokenCpf(cpf));
	}
	
	@Override
	public ResponseEntity<Associado> gerarNumeroTokenCpf(String cpf) {
		return ResponseEntity.ok().body(servicosAssociadosService.geradorNumeroTokenCpf(cpf));
	}
	
	@Override
	public Associado aprovarNumeroTokenCpf(String cpf) {
		return servicosAssociadosService.aprovarNumeroTokenCpf(cpf);
	}
	
	@Override
	public ResponseEntity<Associado> solicitarExameCpf(String cpf) {
		return ResponseEntity.ok().body(servicosAssociadosService.solicitarExameCpf(cpf));
	}
	
	@Override
	public Associado aprovarExameCpf(String cpf) {
		return servicosAssociadosService.aprovarExameCpf(cpf);
	}
	
	@Override
	public ResponseEntity<Associado> solicitarConsultaCpf(String cpf) {
		return ResponseEntity.ok().body(servicosAssociadosService.solicitarConsultaCpf(cpf));
	}
	
	@Override
	public Associado aprovarConsultaCpf(String cpf) {
		return servicosAssociadosService.aprovarConsultaCpf(cpf);
	}
	
	@Override
	public ResponseEntity<Associado> solicitarProcedimentoCpf(String cpf) {
		return ResponseEntity.ok().body(servicosAssociadosService.solicitarProcedimentoCpf(cpf));
	}
	
	@Override
	public Associado aprovarProcedimentoCpf(String cpf) {
		return servicosAssociadosService.aprovarProcedimentoCpf(cpf);
	}
	
	@Override
	public ResponseEntity<Associado> validarDadosAssociado(String cpf) {
		return ResponseEntity.ok().body(servicosAssociadosService.validarDadosAssociado(cpf));
	}
}
