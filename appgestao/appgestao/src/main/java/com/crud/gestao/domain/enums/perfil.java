package com.crud.gestao.domain.enums;

public enum perfil {
	
	ADMIN(0, "ROLE_ADMIN"), CLIENTE(1, "ROLE_CLIENTE"), COLABORADOR(2, "ROLE_COLABORADOR");

	private Integer codigo;
	private String descricao;
	
	private perfil(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	public static perfil toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(perfil x : perfil.values()) {
			if(cod.equals(x.getCodigo())) {
				return x; 
			}
		}
		
		throw new IllegalArgumentException("Perfl inválido");
	}
	
	
	
}
