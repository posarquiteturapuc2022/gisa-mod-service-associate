package br.com.posarquiteturapuc2022.feignServicesAssociates;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.posarquiteturapuc2022.domain.Associado;

@FeignClient(name = "gisa-mod-info-cad", url = "http://localhost:8001/gisa-mod-info-cad/api/associados")
public interface ServicosAssociadoApiFeign {

	@RequestMapping(value = "/cpf/{cpf}", method = RequestMethod.GET)
	ResponseEntity<Associado> findByCpf(@RequestParam(value="cpf") String cpf);
}
