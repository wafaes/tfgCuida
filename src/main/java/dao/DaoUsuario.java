package dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import model.Medico;
import model.Paciente;
import model.Usuario;

public class DaoUsuario extends BaseJPADao{

	// Autenticación para Pacientes
    public static Paciente autenticarPaciente(String claveAcceso, String contrasena) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Paciente> q = em.createQuery(
                "SELECT p FROM Paciente p WHERE p.claveAcceso = :clave AND p.contrasena = :pass", 
                Paciente.class);
            q.setParameter("clave", claveAcceso);
            q.setParameter("pass", contrasena);
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    // Autenticación para Médicos
   

	public static Medico autenticarMedico(String claveAcceso, String contrasena) {
    	
    	

      

     
    	
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Medico> q = em.createQuery(
                "SELECT m FROM Medico m WHERE m.claveAcceso = :clave AND m.contrasena = :pass", 
                Medico.class);
            q.setParameter("clave", claveAcceso);
            q.setParameter("pass", contrasena);
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    // Obtener todos los pacientes de un médico
    public static List<Paciente> getPacientesPorMedico(Integer idMedico) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Paciente> q = em.createQuery(
                "SELECT r.paciente FROM RelacionMedicoPaciente r WHERE r.medico.idMedico = :idMedico", 
                Paciente.class);
            q.setParameter("idMedico", idMedico);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
	
}
