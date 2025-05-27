package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;


/**
 * The persistent class for the registro_diario database table.
 * 
 */
@Entity
@Table(name="registro_diario")
@NamedQuery(name="RegistroDiario.findAll", query="SELECT r FROM RegistroDiario r")
public class RegistroDiario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_registro_diario")
	private Integer idRegistroDiario;

	private Integer agua;

	private String dieta;

	private Integer ejercicio;

	@Column(name="estado_animo")
	private String estadoAnimo;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name="horas_sueno")
	private double horasSueno;

	private Integer medicacion;

	@Column(name="nivel_glucosa")
	private Integer nivelGlucosa;

	private String sintomatologia;

	@Column(name="temperatura_corporal")
	private double temperaturaCorporal;

	@Column(name="tension_arterial")
	private String tensionArterial;

	//bi-directional many-to-one association to Paciente
	@ManyToOne
	@JoinColumn(name="id_paciente")
	private Paciente paciente;

	public RegistroDiario() {
	}

	public Integer getIdRegistroDiario() {
		return this.idRegistroDiario;
	}

	public void setIdRegistroDiario(Integer idRegistroDiario) {
		this.idRegistroDiario = idRegistroDiario;
	}

	public Integer getAgua() {
		return this.agua;
	}

	public void setAgua(Integer agua) {
		this.agua = agua;
	}

	public String getDieta() {
		return this.dieta;
	}

	public void setDieta(String dieta) {
		this.dieta = dieta;
	}

	public Integer getEjercicio() {
		return this.ejercicio;
	}

	public void setEjercicio(Integer ejercicio) {
		this.ejercicio = ejercicio;
	}

	public String getEstadoAnimo() {
		return this.estadoAnimo;
	}

	public void setEstadoAnimo(String estadoAnimo) {
		this.estadoAnimo = estadoAnimo;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getHorasSueno() {
		return this.horasSueno;
	}

	public void setHorasSueno(double horasSueno) {
		this.horasSueno = horasSueno;
	}

	public Integer getMedicacion() {
		return this.medicacion;
	}

	public void setMedicacion(Integer medicacion) {
		this.medicacion = medicacion;
	}

	public Integer getNivelGlucosa() {
		return this.nivelGlucosa;
	}

	public void setNivelGlucosa(Integer nivelGlucosa) {
		this.nivelGlucosa = nivelGlucosa;
	}

	public String getSintomatologia() {
		return this.sintomatologia;
	}

	public void setSintomatologia(String sintomatologia) {
		this.sintomatologia = sintomatologia;
	}

	public double getTemperaturaCorporal() {
		return this.temperaturaCorporal;
	}

	public void setTemperaturaCorporal(double temperaturaCorporal) {
		this.temperaturaCorporal = temperaturaCorporal;
	}

	public String getTensionArterial() {
		return this.tensionArterial;
	}

	public void setTensionArterial(String tensionArterial) {
		this.tensionArterial = tensionArterial;
	}

	public Paciente getPaciente() {
		return this.paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

}