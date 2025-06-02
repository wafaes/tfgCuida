package dao;

import java.util.Date;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import model.Paciente;
import model.RegistroDiario;

public class DaoRegistroDiario extends BaseJPADao{

	
	
	// 1. Insertar registro diario (con validación de paciente)
    public static boolean insertarRegistro(RegistroDiario registro, Integer idPaciente) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            
            // Verifica que el paciente existe
            Paciente paciente = em.find(Paciente.class, idPaciente);
            if (paciente == null) {
                return false;
            }
            
            registro.setPaciente(paciente);
            registro.setFecha(new Date()); // Fecha actual automática
            em.persist(registro);
            
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            return false;
        } finally {
            em.close();
        }
    }

    // 2. Obtener registros por paciente y rango de fechas
    public static List<RegistroDiario> getRegistrosPorPacienteYFecha(
        Integer idPaciente, 
        Date fechaInicio, 
        Date fechaFin) {
        
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                "SELECT r FROM RegistroDiario r " +
                "WHERE r.paciente.idPaciente = :idPaciente " +
                "AND r.fecha BETWEEN :fechaInicio AND :fechaFin " +
                "ORDER BY r.fecha DESC", 
                RegistroDiario.class)
                .setParameter("idPaciente", idPaciente)
                .setParameter("fechaInicio", fechaInicio)
                .setParameter("fechaFin", fechaFin)
                .getResultList();
        } finally {
            em.close();
        }
    }

    // 3. Obtener último registro de un paciente
    public static RegistroDiario getUltimoRegistro(Integer idPaciente) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                "SELECT r FROM RegistroDiario r " +
                "WHERE r.paciente.idPaciente = :idPaciente " +
                "ORDER BY r.fecha DESC, r.idRegistroDiario DESC", 
                RegistroDiario.class)
                .setParameter("idPaciente", idPaciente)
                .setMaxResults(1)
                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    // 4. Eliminar registro (solo si pertenece al paciente)
    public static boolean eliminarRegistro(Integer idRegistro, Integer idPaciente) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            
            RegistroDiario registro = em.find(RegistroDiario.class, idRegistro);
            if (registro == null || !registro.getPaciente().getIdPaciente().equals(idPaciente)) {
                return false;
            }
            
            em.remove(registro);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            return false;
        } finally {
            em.close();
        }
    }
}
