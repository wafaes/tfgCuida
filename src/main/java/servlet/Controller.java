package servlet;

import java.io.IOException;

import dao.DaoMedico;
import dao.DaoPaciente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Medico;
import model.Paciente;

public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String op = request.getParameter("op");
        if (op == null) op = "inicio"; // Valor por defecto si no hay parámetro

        switch(op) {
            case "inicio":
                // Redirige a index.html (ubicado en webapp/)
                RequestDispatcher dispatcher = request.getRequestDispatcher("/index.html");
                dispatcher.forward(request, response);
                break;
                
            
                
            case "login":  
            	String identificador = request.getParameter("identificador");
                String password = request.getParameter("password");
                String tipoUsuario = request.getParameter("tipo_usuario"); // "paciente" o "medico"

                try {
                    if (tipoUsuario.equals("paciente")) {
                        // Autenticar paciente
                        Paciente paciente = DaoPaciente.autenticarPaciente(identificador, password);
                        if (paciente != null) {
                            HttpSession session = request.getSession();
                            session.setAttribute("usuario", paciente);
                            session.setAttribute("tipo", "paciente");
                            dispatcher = request.getRequestDispatcher("paciente/inicio.jsp");
                        } else {
                            request.setAttribute("error", "Credenciales de paciente incorrectas");
                            dispatcher = request.getRequestDispatcher("index.jsp");
                        }
                    } else if (tipoUsuario.equals("medico")) {
                        // Autenticar médico
                        Medico medico = DaoMedico.autenticarMedico(identificador, password);
                        if (medico != null) {
                            HttpSession session = request.getSession();
                            session.setAttribute("usuario", medico);
                            session.setAttribute("tipo", "medico");
                            dispatcher = request.getRequestDispatcher("medico/inicio.jsp");
                        } else {
                            request.setAttribute("error", "Credenciales de médico incorrectas");
                            dispatcher = request.getRequestDispatcher("index.jsp");
                        }
                    } else {
                        throw new IllegalArgumentException("Tipo de usuario no válido");
                    }
                } catch (Exception e) {
                    request.setAttribute("error", "Error en el login: " + e.getMessage());
                    dispatcher = request.getRequestDispatcher("index.jsp");
                }
                dispatcher.forward(request, response);
                break;
        }
    }
}