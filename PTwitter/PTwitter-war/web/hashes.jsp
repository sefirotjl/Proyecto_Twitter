<%-- 
    Document   : perfil
    Created on : 11-mar-2013, 13:45:33
    Author     : Juancho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="recursos.Comment" %>
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
                    <h1 align ="left">Busqueda por Hash</h1>
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
                            Comment[] comments = con.buscaMensajesPorHash(request.getParameter("buscar"));
                            if (comments != null) {
                                for (Comment c : comments) {
                                    if (c != null) {
                                        out.println("<table border='0'>");
                                        out.println("<tr>");
                                        out.println("<th></th>");
                                        out.println("<td border='0' ><a href='perfil.jsp?email=" + c.getEmail() + "'>");
                                        out.println(c.getNombre());
                                        out.println("</a></td>");
                                        out.println("</tr>");
                                        out.println("<tr>");
                                        out.println("<th></th>");
                                        out.println("<td>" + c.getComment() + "</td>");
                                        out.println("</tr>");
                                        out.println("</table>");
                                    }
                                }
                            } else {
                                out.println("No se encontro ninguna respuesta para la busqueda de: " + request.getParameter("buscar"));
                            }
                        }
                        if (request.getParameter("xbuscar") != null && request.getParameter("xbuscar") != "") {
                            Comment[] comments = con.buscaMensajesPorHashExacto(request.getParameter("xbuscar"));
                            if (comments != null) {
                                for (Comment c : comments) {
                                    if (c != null) {
                                        out.println("<table border='0'>");
                                        out.println("<tr>");
                                        out.println("<th></th>");
                                        out.println("<td border='0' ><a href='perfil.jsp?email=" + c.getEmail() + "'>");
                                        out.println(c.getNombre());
                                        out.println("</a></td>");
                                        out.println("</tr>");
                                        out.println("<tr>");
                                        out.println("<th></th>");
                                        out.println("<td>" + c.getComment() + "</td>");
                                        out.println("</tr>");
                                        out.println("</table>");
                                    }
                                }
                            } else {
                                out.println("No se encontro ninguna respuesta para la busqueda de: " + request.getParameter("buscar"));
                            }
                        } else {
                            out.println("<table border='0'>");
                            out.println("<tr>");
                            out.println("<th></th>");
                            out.println("<td>" + "Los parametro de busquedafueron incorrectos" + "</td>");
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
