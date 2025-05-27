package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the medico database table.
 * 
 */
@Entity
@NamedQuery(name="Medico.findAll", query="SELECT m FROM Medico m")
public class Medico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_medico")
	private Integer idMedico;

	@Column(name="clave_acceso")
	private String claveAcceso;

	private String contrasena;

	private String especialidad;

	private String nombre;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	//bi-directional many-to-one association to RelacionMedicoPaciente
	@OneToMany(mappedBy="medico")
	private List<RelacionMedicoPaciente> relacionMedicoPacientes;

	public Medico() {
	}

	public Integer getIdMedico() {
		return this.idMedico;
	}

	public void setIdMedico(Integer idMedico) {
		this.idMedico = idMedico;
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

	public String getEspecialidad() {
		return this.especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
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

	public List<RelacionMedicoPaciente> getRelacionMedicoPacientes() {
		return this.relacionMedicoPacientes;
	}

	public void setRelacionMedicoPacientes(List<RelacionMedicoPaciente> relacionMedicoPacientes) {
		this.relacionMedicoPacientes = relacionMedicoPacientes;
	}

	public RelacionMedicoPaciente addRelacionMedicoPaciente(RelacionMedicoPaciente relacionMedicoPaciente) {
		getRelacionMedicoPacientes().add(relacionMedicoPaciente);
		relacionMedicoPaciente.setMedico(this);

		return relacionMedicoPaciente;
	}

	public RelacionMedicoPaciente removeRelacionMedicoPaciente(RelacionMedicoPaciente relacionMedicoPaciente) {
		getRelacionMedicoPacientes().remove(relacionMedicoPaciente);
		relacionMedicoPaciente.setMedico(null);

		return relacionMedicoPaciente;
	}

}