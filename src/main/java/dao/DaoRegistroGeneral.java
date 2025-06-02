package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import model.Paciente;
import model.RegistroGeneral;

public class DaoRegistroGeneral extends BaseJPADao{

	
	// 1. Crear o actualizar registro general (upsert)
    public static boolean guardarRegistroGeneral(Integer idPaciente, Integer altura, Integer edad, String sexo) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            
            // Buscar si ya existe un registro para el paciente
            RegistroGeneral registro = em.createQuery(
                "SELECT r FROM RegistroGeneral r WHERE r.paciente.idPaciente = :idPaciente", 
                RegistroGeneral.class)
                .setParameter("idPaciente", idPaciente)
                .getResultStream()
                .findFirst()
                .orElse(new RegistroGeneral());

            // Actualizar o crear datos
            Paciente paciente = em.find(Paciente.class, idPaciente);
            if (paciente == null) {
                return false;
            }
            
            registro.setAltura(altura);
            registro.setEdad(edad);
            registro.setSexo(sexo);
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

    // 2. Obtener registro por ID de paciente
    public static RegistroGeneral getRegistroPorPaciente(Integer idPaciente) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                "SELECT r FROM RegistroGeneral r WHERE r.paciente.idPaciente = :idPaciente", 
                RegistroGeneral.class)
                .setParameter("idPaciente", idPaciente)
                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    // 3. Eliminar registro por ID de paciente
    public static boolean eliminarRegistro(Integer idPaciente) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            
            int deleted = em.createQuery(
                "DELETE FROM RegistroGeneral r WHERE r.paciente.idPaciente = :idPaciente")
                .setParameter("idPaciente", idPaciente)
                .executeUpdate();
            
            tx.commit();
            return deleted > 0;
            
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            return false;
        } finally {
            em.close();
        }
    }

    // 4. Obtener estadísticas básicas (ejemplo: promedio de altura por sexo)
    public static Double getAlturaPromedioPorSexo(String sexo) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                "SELECT AVG(r.altura) FROM RegistroGeneral r WHERE r.sexo = :sexo", 
                Double.class)
                .setParameter("sexo", sexo)
                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
	
}
