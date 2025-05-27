package listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import dao.PersistenceManagerSingleton;

@WebListener
public class PersistenceAppListener implements ServletContextListener {

   
    public PersistenceAppListener() {
    }

	
    public void contextInitialized(ServletContextEvent sce)  { 
    }

	
    //cuando se destruye el contexto(se vueve a publicar el proyecto), se cierra la Factory 
    public void contextDestroyed(ServletContextEvent sce)  { 
         PersistenceManagerSingleton.getInstance().closeEntityManagerFactory();
    }
	
}
