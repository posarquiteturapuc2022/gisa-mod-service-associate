package br.com.posarquiteturapuc2022.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import br.com.posarquiteturapuc2022.domain.enums.NivelFormacao;
import br.com.posarquiteturapuc2022.domain.enums.TipoCategoria;
import br.com.posarquiteturapuc2022.domain.enums.TipoCobertura;
import br.com.posarquiteturapuc2022.domain.enums.TipoPlanoSaude;
import br.com.posarquiteturapuc2022.domain.enums.TipoStatus;
import br.com.posarquiteturapuc2022.domain.enums.TipoUsuario;
import br.com.posarquiteturapuc2022.utils.EntityAbstract;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ASSOCIADO")
@Table(schema = "gisamodserviceassociatedb")
@AttributeOverride(name = "id", column = @Column(name = "id_associado"))
public class Associado extends EntityAbstract implements Serializable, Comparable<Associado>{
	
	private static final long serialVersionUID = 6089823561002678264L;

	@Include
	@Id
	@GeneratedValue(strategy = IDENTITY)
    private UUID id;

    private String nomeAssociado;
    private String cpfAssociado;
    private String numeroCarteirinhaAssociado;
    private String emailAssociado;
    private String sexo;
    private String cep;
    private String endereco;
    private String numero;
    private String bairro;
    private String cidade;
    private String uf;
    private String estadoCivil;
    private NivelFormacao nivelFormacao;
	
    @ManyToOne
	@JoinColumn(name = "id_profissao")
    private Profissao profissao;
    
    private LocalDate dataCadastro;
    private LocalDate dataNascimento;
    	
    private Integer idade;
    private Integer codigoPlano;
    private BigDecimal valorPlanoMensal;
	private TipoCobertura tipoCobertura;
	private TipoStatus tipoStatus ;
	private TipoCategoria tipoCategoria;
	private TipoPlanoSaude tipoPlanoSaude;
	private TipoUsuario tipoUsuario;  
    private boolean odontologico;
    
    private boolean situacao;
    
	@PrePersist
	public void prepersist() {
		this.setDataCadastro(LocalDate.now());
	}

	public void planoOdontologico(boolean planoOdontologico, BigDecimal valorPlano) {
		if (planoOdontologico) {
			setValorPlanoMensal(valorPlano);
	        NumberFormat df2 = NumberFormat.getInstance();
	        BigDecimal percent15 = new BigDecimal ("0.15");
	        System.out.println("Valor Fixo do Plano: " + getValorPlanoMensal());
	        System.out.println ("15% de Valor do Plano Mensal: " + df2.format(getValorPlanoMensal().multiply(percent15)).replace(",","."));
	        BigDecimal vpm = new BigDecimal(String.valueOf(df2.format(getValorPlanoMensal().multiply(percent15)).replace(",",".")));
			setValorPlanoMensal(valorPlano.add(vpm));
			System.out.println("setValorPlanoMensal(): " + valorPlano.add(vpm));
		}
	}

	@Override
	public int compareTo(Associado o) {
		return  o.getCreatedAt().compareTo(getCreatedAt());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Associado other = (Associado) obj;
		return Objects.equals(bairro, other.bairro) && Objects.equals(cep, other.cep)
				&& Objects.equals(cidade, other.cidade) && Objects.equals(codigoPlano, other.codigoPlano)
				&& Objects.equals(cpfAssociado, other.cpfAssociado) && Objects.equals(dataCadastro, other.dataCadastro)
				&& Objects.equals(dataNascimento, other.dataNascimento)
				&& Objects.equals(emailAssociado, other.emailAssociado) && Objects.equals(endereco, other.endereco)
				&& Objects.equals(estadoCivil, other.estadoCivil) && Objects.equals(id, other.id)
				&& Objects.equals(idade, other.idade)
				&& Objects.equals(nomeAssociado, other.nomeAssociado) && Objects.equals(numero, other.numero)
				&& Objects.equals(numeroCarteirinhaAssociado, other.numeroCarteirinhaAssociado)
				&& odontologico == other.odontologico && Objects.equals(profissao, other.profissao)
				&& Objects.equals(sexo, other.sexo) && situacao == other.situacao
				&& tipoCategoria == other.tipoCategoria && tipoCobertura == other.tipoCobertura
				&& tipoPlanoSaude == other.tipoPlanoSaude && tipoStatus == other.tipoStatus
				&& tipoUsuario == other.tipoUsuario && Objects.equals(uf, other.uf)
				&& Objects.equals(valorPlanoMensal, other.valorPlanoMensal);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(bairro, cep, cidade, codigoPlano, cpfAssociado, dataCadastro,
				dataNascimento, emailAssociado, endereco, estadoCivil, id, idade, nomeAssociado, numero,
				numeroCarteirinhaAssociado, odontologico, profissao, sexo, situacao, tipoCategoria, tipoCobertura,
				tipoPlanoSaude, tipoStatus, tipoUsuario, uf, valorPlanoMensal);
		return result;
	}
	
}
