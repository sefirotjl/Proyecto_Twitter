<%-- 
    Document   : perfil
    Created on : 11-mar-2013, 13:45:33
    Author     : Juancho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="recursos.Usuario" %>
<%@ page import="recursos.Consultas" %>
<!DOCTYPE html>
<%
    String me = "";
    String nombre = "";
    Consultas con = new Consultas();
    //verificamos que ya haya iniciado secion

    if (application.getAttribute("login") != null) {

        if ((Boolean) application.getAttribute("login")) {
            //ya inicio secion, procedemos con el procesamiento
            me = (String) application.getAttribute("email");
            nombre = (String) application.getAttribute("nombre");
        } else {
            response.sendRedirect("index.jsp?resp=Es necesario iniciar secion para acceder al area de busquedas");
        }
    } else {
        response.sendRedirect("index.jsp?resp=Es necesario iniciar secion para acceder al area de busquedas");
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body background="black.jpg">
        <table width="800" border="0" align="center">
            <tr>
                <td colspan="2" style="background-color:#EEEEEE;" align ="right">
                    <h1 align ="left">Busqueda de Personas</h1>
                    <form name='frmBuscar' action='buscar' method='post'>
                        Buscar: <input type='text' name='buscar' value=''></br>
                        <input type="radio" name="tipoBusqueda" checked="checked" value="usuario"> Usuarios
                        <input type="radio" name="tipoBusqueda" value="hashtag"> Hashtags
                        <Input type='submit' name='btnBuscar' value='Iniciar Busqueda'/>
                    </form>
                </td>
            </tr>

            <tr>
                <td style="background-color:#EEEEEE;width:80px;">
                    <b>Menu</b><br>
                    <a href='trending.jsp'>Trending Topics</a><br>
                    <%
                        out.println("<a href='perfil.jsp?user=" + me + "'>Mi perfil</a>");
                    %>
                    <%
                        out.println("<form name='frmCerrar' action='cerrarSesion' method='post'>");
                        out.println("<Input type='submit' name='cerrar' value='Cerrar Sesion'/></form>");
                    %>

                </td>
                <td style="background-color:#FFFFFF;height:100px;width:500px;align:center;">
                    <%
                        if (request.getParameter("buscar") != null && request.getParameter("buscar") != "") {
                            Usuario[] usuarios = con.getUsersByName(request.getParameter("buscar"));
                            if (usuarios != null) {
                                for (Usuario u : usuarios) {
                                    if (u != null) {
                                        out.println("<table border='0'>");
                                        out.println("<tr>");
                                        out.println("<th></th>");
                                        out.println("<td>" + "<a href='perfil.jsp?email=" + u.getEmail()
                                                + "'>" + u.getNombre() + "</a></td>");
                                        out.println("</tr>");
                                        out.println("</table>");
                                    }
                                }
                            } else {
                                out.println("No se encontro ninguna respuesta para la busqueda de: " + request.getParameter("buscar"));
                            }
                        } 
                        if (request.getParameter("xbuscar") != null && request.getParameter("xbuscar") != "") {
                            Usuario[] usuarios = con.getUsersByExactName(request.getParameter("xbuscar"));
                            if (usuarios != null) {
                                for (Usuario u : usuarios) {
                                    if (u != null) {
                                        out.println("<table border='0'>");
                                        out.println("<tr>");
                                        out.println("<th></th>");
                                        out.println("<td>" + "<a href='perfil.jsp?user=" + u.getEmail()
                                                + "'>" + u.getNombre() + "</a></td>");
                                        out.println("</tr>");
                                        out.println("</table>");
                                    }
                                }
                            } else {
                                out.println("No se encontro ninguna respuesta para la busqueda de: " + request.getParameter("buscar"));
                            }
                        }
                        else {
                            out.println("<table border='0'>");
                            out.println("<tr>");
                            out.println("<th></th>");
                            out.println("<td>" + "Los parametro de busqueda fueron incorrectos" + "</td>");
                            out.println("</tr>");
                        }
                    %>
                </td>
            </tr>

            <tr>
                <td colspan="2" style="background-color:#EEEEEE;text-align:center;">
                    Proyecto 1 - Lab. Sistemas Distribuidos</td>
            </tr>
        </table>

    </body>
</html>
