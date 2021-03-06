/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import recursos.Scanner;
import recursos.Consultas;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Juancho
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //verificamos que este bien escrito el mail
            Scanner sc = new Scanner();

            String email = request.getParameter("email");

            Consultas con = new Consultas();
            if (request.getParameter("login") != null) {
                String res;
                out.println("voy a hacer el login");
                res = con.login(email, request.getParameter("password"));
                out.println(res);
                if (res == "OK") {
                    out.println("voy a agregar las variables de entorno");
                    this.getServletConfig().getServletContext().setAttribute("email", email);
                    String nombre = con.getNombreByCorreo(email);
                    this.getServletConfig().getServletContext().setAttribute("nombre", nombre);
                    this.getServletConfig().getServletContext().setAttribute("login", true);
                    out.println("voy a redireccionar");
                    response.sendRedirect("perfil.jsp?email=" + email);
                } else {
                    response.sendRedirect("index.jsp?resp=" + res);
                }

            } else {

                String nombre = request.getParameter("nombre");
                String password = request.getParameter("password");
                out.println("voy a agregar a la base de datos");
                String respc = con.registraUsuario(email, password, nombre);
                out.println("logre agregar, voy a redireccionar");
                if (respc == "OK") {
                    response.sendRedirect("index.jsp?resp=El usuario se agrego con exito, ya puedes iniciar sesion");
                } else {
                    response.sendRedirect("index.jsp?resp=" + respc);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            out.println("Error tipo 1: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            out.println("Error tipo " + login.class.getName() + ": " + ex.getMessage());
        } catch (InstantiationException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            out.println("Error tipo 3: " + ex.getMessage());
        } catch (IllegalAccessException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            out.println("Error tipo 4: " + ex.getMessage());
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
