package model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the registro_general database table.
 * 
 */
@Entity
@Table(name="registro_general")
@NamedQuery(name="RegistroGeneral.findAll", query="SELECT r FROM RegistroGeneral r")
public class RegistroGeneral implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_registro_general")
	private Integer idRegistroGeneral;

	private Integer altura;

	private Integer edad;

	private String sexo;

	//bi-directional many-to-one association to Paciente
	@ManyToOne
	@JoinColumn(name="id_paciente")
	private Paciente paciente;

	public RegistroGeneral() {
	}

	public Integer getIdRegistroGeneral() {
		return this.idRegistroGeneral;
	}

	public void setIdRegistroGeneral(Integer idRegistroGeneral) {
		this.idRegistroGeneral = idRegistroGeneral;
	}

	public Integer getAltura() {
		return this.altura;
	}

	public void setAltura(Integer altura) {
		this.altura = altura;
	}

	public Integer getEdad() {
		return this.edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Paciente getPaciente() {
		return this.paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

}