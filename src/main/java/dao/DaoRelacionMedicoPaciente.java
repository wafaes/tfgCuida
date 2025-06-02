package dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Medico;
import model.Paciente;
import model.RelacionMedicoPaciente;

public class DaoRelacionMedicoPaciente extends BaseJPADao{

	// 1. Crear relación médico-paciente
    public static void crearRelacion(Integer idMedico, Integer idPaciente) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            RelacionMedicoPaciente relacion = new RelacionMedicoPaciente();
            relacion.setMedico(em.find(Medico.class, idMedico)); // Asocia entidades existentes
            relacion.setPaciente(em.find(Paciente.class, idPaciente));
            em.persist(relacion);
            tx.commit();
        } finally {
            if (tx.isActive()) tx.rollback();
            em.close();
        }
    }

    // 2. Listar pacientes de un médico
    public static List<Paciente> getPacientesPorMedico(Integer idMedico) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                "SELECT r.paciente FROM RelacionMedicoPaciente r WHERE r.medico.idMedico = :idMedico", 
                Paciente.class)
                .setParameter("idMedico", idMedico)
                .getResultList();
        } finally {
            em.close();
        }
    }

    // 3. Listar médicos de un paciente
    public static List<Medico> getMedicosPorPaciente(Integer idPaciente) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                "SELECT r.medico FROM RelacionMedicoPaciente r WHERE r.paciente.idPaciente = :idPaciente", 
                Medico.class)
                .setParameter("idPaciente", idPaciente)
                .getResultList();
        } finally {
            em.close();
        }
    }
	
}
