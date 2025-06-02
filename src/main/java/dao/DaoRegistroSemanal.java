package dao;

import java.util.Date;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Paciente;
import model.RegistroSemanal;

public class DaoRegistroSemanal extends BaseJPADao{

	
	// 1. Crear registro semanal (con fecha automática)
    public static boolean crearRegistroSemanal(String observaciones, Integer idPaciente) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            
            Paciente paciente = em.find(Paciente.class, idPaciente);
            if (paciente == null) {
                return false;
            }
            
            RegistroSemanal registro = new RegistroSemanal();
            registro.setFecha(new Date()); // Fecha actual
            registro.setObservaciones(observaciones);
            registro.setPaciente(paciente);
            
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

    // 2. Obtener registros por paciente (ordenados por fecha descendente)
    public static List<RegistroSemanal> getRegistrosPorPaciente(Integer idPaciente) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                "SELECT r FROM RegistroSemanal r " +
                "WHERE r.paciente.idPaciente = :idPaciente " +
                "ORDER BY r.fecha DESC", 
                RegistroSemanal.class)
                .setParameter("idPaciente", idPaciente)
                .getResultList();
        } finally {
            em.close();
        }
    }

    // 3. Buscar registros por contenido en observaciones
    public static List<RegistroSemanal> buscarPorObservacion(Integer idPaciente, String keyword) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                "SELECT r FROM RegistroSemanal r " +
                "WHERE r.paciente.idPaciente = :idPaciente " +
                "AND LOWER(r.observaciones) LIKE LOWER(:keyword) " +
                "ORDER BY r.fecha DESC",
                RegistroSemanal.class)
                .setParameter("idPaciente", idPaciente)
                .setParameter("keyword", "%" + keyword + "%")
                .getResultList();
        } finally {
            em.close();
        }
    }

    // 4. Eliminar registro (con validación de pertenencia al paciente)
    public static boolean eliminarRegistro(Integer idRegistro, Integer idPaciente) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            
            RegistroSemanal registro = em.find(RegistroSemanal.class, idRegistro);
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
