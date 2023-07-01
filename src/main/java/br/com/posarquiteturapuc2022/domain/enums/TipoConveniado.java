package br.com.posarquiteturapuc2022.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoConveniado {
	CONSULTORIO(0, "Consultório"), 
	LABORATORIO(1, "Laboratório"), 
	CLINICA(2, "Clínica"), 
	HOSPITAL(3, "Hospital");
	
	private Integer codigo;
	private String descricao;
	
	private TipoConveniado(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoConveniado toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(TipoConveniado x : TipoConveniado.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Tipo Conveniado inválido");
	}

}
