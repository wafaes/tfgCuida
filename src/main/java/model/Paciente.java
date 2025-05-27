package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the paciente database table.
 * 
 */
@Entity
@NamedQuery(name="Paciente.findAll", query="SELECT p FROM Paciente p")
public class Paciente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_paciente")
	private Integer idPaciente;

	@Column(name="clave_acceso")
	private String claveAcceso;

	private String contrasena;

	private Integer edad;

	private String nombre;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	//bi-directional many-to-one association to RegistroDiario
	@OneToMany(mappedBy="paciente")
	private List<RegistroDiario> registroDiarios;

	//bi-directional many-to-one association to RegistroGeneral
	@OneToMany(mappedBy="paciente")
	private List<RegistroGeneral> registroGenerals;

	//bi-directional many-to-one association to RegistroSemanal
	@OneToMany(mappedBy="paciente")
	private List<RegistroSemanal> registroSemanals;

	//bi-directional many-to-one association to RelacionMedicoPaciente
	@OneToMany(mappedBy="paciente")
	private List<RelacionMedicoPaciente> relacionMedicoPacientes;

	public Paciente() {
	}

	public Integer getIdPaciente() {
		return this.idPaciente;
	}

	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getClaveAcceso() {
		return this.claveAcceso;
	}

	public void setClaveAcceso(String claveAcceso) {
		this.claveAcceso = claveAcceso;
	}

	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Integer getEdad() {
		return this.edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<RegistroDiario> getRegistroDiarios() {
		return this.registroDiarios;
	}

	public void setRegistroDiarios(List<RegistroDiario> registroDiarios) {
		this.registroDiarios = registroDiarios;
	}

	public RegistroDiario addRegistroDiario(RegistroDiario registroDiario) {
		getRegistroDiarios().add(registroDiario);
		registroDiario.setPaciente(this);

		return registroDiario;
	}

	public RegistroDiario removeRegistroDiario(RegistroDiario registroDiario) {
		getRegistroDiarios().remove(registroDiario);
		registroDiario.setPaciente(null);

		return registroDiario;
	}

	public List<RegistroGeneral> getRegistroGenerals() {
		return this.registroGenerals;
	}

	public void setRegistroGenerals(List<RegistroGeneral> registroGenerals) {
		this.registroGenerals = registroGenerals;
	}

	public RegistroGeneral addRegistroGeneral(RegistroGeneral registroGeneral) {
		getRegistroGenerals().add(registroGeneral);
		registroGeneral.setPaciente(this);

		return registroGeneral;
	}

	public RegistroGeneral removeRegistroGeneral(RegistroGeneral registroGeneral) {
		getRegistroGenerals().remove(registroGeneral);
		registroGeneral.setPaciente(null);

		return registroGeneral;
	}

	public List<RegistroSemanal> getRegistroSemanals() {
		return this.registroSemanals;
	}

	public void setRegistroSemanals(List<RegistroSemanal> registroSemanals) {
		this.registroSemanals = registroSemanals;
	}

	public RegistroSemanal addRegistroSemanal(RegistroSemanal registroSemanal) {
		getRegistroSemanals().add(registroSemanal);
		registroSemanal.setPaciente(this);

		return registroSemanal;
	}

	public RegistroSemanal removeRegistroSemanal(RegistroSemanal registroSemanal) {
		getRegistroSemanals().remove(registroSemanal);
		registroSemanal.setPaciente(null);

		return registroSemanal;
	}

	public List<RelacionMedicoPaciente> getRelacionMedicoPacientes() {
		return this.relacionMedicoPacientes;
	}

	public void setRelacionMedicoPacientes(List<RelacionMedicoPaciente> relacionMedicoPacientes) {
		this.relacionMedicoPacientes = relacionMedicoPacientes;
	}

	public RelacionMedicoPaciente addRelacionMedicoPaciente(RelacionMedicoPaciente relacionMedicoPaciente) {
		getRelacionMedicoPacientes().add(relacionMedicoPaciente);
		relacionMedicoPaciente.setPaciente(this);

		return relacionMedicoPaciente;
	}

	public RelacionMedicoPaciente removeRelacionMedicoPaciente(RelacionMedicoPaciente relacionMedicoPaciente) {
		getRelacionMedicoPacientes().remove(relacionMedicoPaciente);
		relacionMedicoPaciente.setPaciente(null);

		return relacionMedicoPaciente;
	}

}