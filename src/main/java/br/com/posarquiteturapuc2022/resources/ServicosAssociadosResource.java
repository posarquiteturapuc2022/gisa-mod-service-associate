package br.com.posarquiteturapuc2022.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.posarquiteturapuc2022.domain.Associado;

public interface ServicosAssociadosResource {
	
	@PostMapping(value = "/solicita/solicitarNumeroCarteirinhaCpf")
	ResponseEntity<Associado> solicitarNumeroCarteirinhaCpf(@PathVariable String cpf);
	
	@PostMapping(value = "/aprova/aprovarNumeroCarteirinhaCpf")
	Associado aprovarNumeroCarteirinhaCpf(@PathVariable String cpf);
	
	@PostMapping(value = "/gera/gerarNumeroCarteirinhaCpf")
	ResponseEntity<Associado> gerarNumeroCarteirinhaCpf(@PathVariable String cpf);

	@PostMapping(value = "/solicita/solicitarNumeroTokenCpf")
	ResponseEntity<Associado> solicitarNumeroTokenCpf(@PathVariable String cpf);
	
	@PostMapping(value = "/aprova/aprovarNumeroTokenCpf")
	Associado aprovarNumeroTokenCpf(@PathVariable String cpf);
	
	@PostMapping(value = "/gera/gerarNumeroTokenCpf")
	ResponseEntity<Associado> gerarNumeroTokenCpf(@PathVariable String cnpj);

	@PostMapping(value = "/solicita/solicitarExameCpf")
	ResponseEntity<Associado> solicitarExameCpf(@PathVariable String cpf);
	
	@PostMapping(value = "/aprova/aprovarExameCpf")
	Associado aprovarExameCpf(@PathVariable String cpf);

	@PostMapping(value = "/solicita/solicitarConsultaCpf")
	ResponseEntity<Associado> solicitarConsultaCpf(@PathVariable String cpf);
	
	@PostMapping(value = "/aprova/aprovarConsultaCpf")
	Associado aprovarConsultaCpf(@PathVariable String cpf);

	@PostMapping(value = "/solicita/solicitarProcedimentoCpf")
	ResponseEntity<Associado> solicitarProcedimentoCpf(@PathVariable String cpf);
	
	@PostMapping(value = "/aprova/aprovarProcedimentoCpf")
	Associado aprovarProcedimentoCpf(@PathVariable String cpf);

	@PostMapping(value = "/valida/validarDadosAssociado")
	ResponseEntity<Associado> validarDadosAssociado(@PathVariable String cpf);
}
