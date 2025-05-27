package model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the relacion_medico_paciente database table.
 * 
 */
@Entity
@Table(name="relacion_medico_paciente")
@NamedQuery(name="RelacionMedicoPaciente.findAll", query="SELECT r FROM RelacionMedicoPaciente r")
public class RelacionMedicoPaciente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_relacion")
	private Integer idRelacion;

	//bi-directional many-to-one association to Medico
	@ManyToOne
	@JoinColumn(name="id_medico")
	private Medico medico;

	//bi-directional many-to-one association to Paciente
	@ManyToOne
	@JoinColumn(name="id_paciente")
	private Paciente paciente;

	public RelacionMedicoPaciente() {
	}

	public Integer getIdRelacion() {
		return this.idRelacion;
	}

	public void setIdRelacion(Integer idRelacion) {
		this.idRelacion = idRelacion;
	}

	public Medico getMedico() {
		return this.medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return this.paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

}