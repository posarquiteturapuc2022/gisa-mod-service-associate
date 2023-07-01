package br.com.posarquiteturapuc2022.services;

import br.com.posarquiteturapuc2022.domain.Associado;

public interface ServicosAssociadosService {

	public Associado solicitarNumeroCarteirinhaCpf(String cpf);
	
	public Associado aprovarNumeroCarteirinhaCpf(String cpf);
	
	public Associado gerarNumeroCarteirinhaCpf(String cpf);
	
	public Associado consultarNumeroCarteirinhaCpf(String cnpj);
	
	public Associado solicitarNumeroTokenCpf(String cpf);
	
	public Associado aprovarNumeroTokenCpf(String cpf);
	
	public Associado geradorNumeroTokenCpf(String cpf);

	public Associado solicitarExameCpf(String cpf);
	
	public Associado aprovarExameCpf(String cpf);

	public Associado solicitarConsultaCpf(String cpf);
	
	public Associado aprovarConsultaCpf(String cpf);

	public Associado solicitarProcedimentoCpf(String cpf);
	
	public Associado aprovarProcedimentoCpf(String cpf);

	public Associado validarDadosAssociado(String cpf);
}
