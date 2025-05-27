package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;


/**
 * The persistent class for the registro_semanal database table.
 * 
 */
@Entity
@Table(name="registro_semanal")
@NamedQuery(name="RegistroSemanal.findAll", query="SELECT r FROM RegistroSemanal r")
public class RegistroSemanal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_registro_semanal")
	private Integer idRegistroSemanal;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private String observaciones;

	//bi-directional many-to-one association to Paciente
	@ManyToOne
	@JoinColumn(name="id_paciente")
	private Paciente paciente;

	public RegistroSemanal() {
	}

	public Integer getIdRegistroSemanal() {
		return this.idRegistroSemanal;
	}

	public void setIdRegistroSemanal(Integer idRegistroSemanal) {
		this.idRegistroSemanal = idRegistroSemanal;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Paciente getPaciente() {
		return this.paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

}