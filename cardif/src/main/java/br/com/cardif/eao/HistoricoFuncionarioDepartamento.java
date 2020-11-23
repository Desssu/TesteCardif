package br.com.cardif.eao;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "historico_func_depart")
@Getter
@Setter
public class HistoricoFuncionarioDepartamento {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqHistFuncDepart")
	@SequenceGenerator(name="seqHistFuncDepart", sequenceName="seq_historico_func_depart", initialValue = 1, allocationSize = 1)
	@Column(name = "hist_func_dept_id")
	private Long id;
	
	@Column(name = "flag_func_chefe_departamento")
	private String funcionarioChefDepartamento;
	
	@Column(name = "departamento_id")
	private Long idDepartamento;

	@Column(name = "funcionario_id")
	private Long idFuncionario;
	
	@Column(name = "data_criacao")
	private LocalDateTime dataCriacao;

}
