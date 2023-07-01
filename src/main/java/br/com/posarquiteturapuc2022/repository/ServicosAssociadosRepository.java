package br.com.posarquiteturapuc2022.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.posarquiteturapuc2022.domain.Associado;

@Repository
public interface ServicosAssociadosRepository  extends JpaRepository<Associado, UUID>{

}
