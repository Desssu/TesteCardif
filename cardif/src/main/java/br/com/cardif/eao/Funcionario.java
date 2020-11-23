package br.com.cardif.eao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "funcionario")
@Getter
@Setter
public class Funcionario {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqFuncionario")
	@SequenceGenerator(name="seqFuncionario", sequenceName="seq_funcionario", initialValue = 7, allocationSize = 1)
	@Column(name = "funcionario_id")
	private Long id;

	@Column(name = "funcionario_name", length = 50)
	private String nome;

	@Column(name = "funcionario_age")
	private Integer idade;

	@Column(name = "funcionario_birthday")
	private LocalDate dataNascimento;

	@Column(name = "funcionario_document", length = 50)
	private String documento;

	@ManyToOne
	@JoinColumn(name = "cargo_id")
	private Cargo cargo;

	@Fetch(FetchMode.SELECT)
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "funcionario_departamento", joinColumns = {
			@JoinColumn(name = "funcionario_id") }, inverseJoinColumns = { @JoinColumn(name = "departamento_id") })
	private List<Departamento> listaDepartamentos;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Funcionario))
			return false;
		Funcionario other = (Funcionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
