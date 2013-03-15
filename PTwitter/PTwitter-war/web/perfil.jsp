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
    /*    
     boolean miPerfil = true;
     String me = "jorge@renteria.com.mx";
     String userProfile = "jorge@renteria.com.mx";
     Consultas con = new Consultas();
     * */


    //Declaracion de variables globales que seran usadas en esta pagina
    boolean miPerfil = false;
    boolean siguiendo = false;
    String me = "";
    String userProfile = "";
    String nombre = "";
    Consultas con = new Consultas();
    //verificamos que ya haya iniciado secion

    if (application.getAttribute("login") != null) {

        if ((Boolean) application.getAttribute("login")) {
            //ya inicio secion, procedemos con el procesamiento
            me = (String) application.getAttribute("email");
            nombre = (String) application.getAttribute("nombre");
            if (request.getParameter("email") != null) {
                userProfile = request.getParameter("email");
                if (me.equals(userProfile)) {
                    //estoy en mi perfil
                    miPerfil = true;

                } else {
                    //estoy viendo el perfil de alguien mas
                    miPerfil = false;
                    if (!con.getNombreByCorreo(userProfile).equals("")) {
                        nombre = con.getNombreByCorreo(userProfile);
                        siguiendo = con.siguiendo(me, userProfile);
                    } else {
                        String mensaje = "El usuario que deseas ver, no existe. ¿Que estas tratando de hacer?";
                        response.sendRedirect("error.jsp?mensaje=" + mensaje);
                    }
                }
            } else {
                miPerfil = true;
                userProfile = me;
            }
        } else {
            response.sendRedirect("index.jsp?resp=Es necesario iniciar secion para acceder al area de perfiles");
        }
    } else {
        response.sendRedirect("index.jsp?resp=Es necesario iniciar secion para acceder al area de perfiles");
    }

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body background="black.jpg">
        <table width="800" border="0" align ="center">
            <tr style="height:100px;">
                <td colspan="2" style="background-color:#EEEEEE;" align ="right">
                    <h1 align ="left">
                        <%
                            out.print(nombre);
                        %>
                    </h1>

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
                        if (!miPerfil && !siguiendo) {
                            out.println("<form name='frmSeguir' action='seguir' method='post'>");
                            out.print("<Input type='hidden' name='email' value='" + userProfile + "'/>");
                            out.println("<Input type='submit' name='seguir' value='Seguir'/></form>");
                        }
                        out.println("<form name='frmCerrar' action='cerrarSesion' method='post'>");
                        out.println("<Input type='submit' name='cerrar' value='Cerrar Sesion'/></form>");
                    %>

                </td>
                <td style="height:500px; background-color:#FFFFFF;width:500px; align:top">
                    <%
                        if (miPerfil) {
                            out.println("<table border='0'>");
                            out.println("<tr>");
                            out.println("<th></th>");
                            out.println("<td>" + me + "</td>");
                            out.println("</tr>");
                            out.println("<tr>");
                            out.println("<th></th>");
                            out.println("<td>");
                            out.println("<form name='frmComentar' action='comentar' method='post'>");
                            out.println("<input type='text' name='comentario' value=''></br>");
                            out.println("<Input type='submit' name='mandar' value='Mandar'/></form>");
                            if (request.getParameter("merror") != null) {
                                out.println("</br>" + request.getParameter("merror"));
                            }
                            out.println("</td>");
                            out.println("</tr>");
                            out.println("</table>");
                        } /*else {
                         if (!miPerfil) {
                         out.println("<table border='0'>");
                         out.println("<tr>");
                         out.println("<th></th>");
                         out.println("<td> Enviar mensaje a: " + userProfile + "</td>");
                         out.println("</tr>");
                         out.println("<tr>");
                         out.println("<th></th>");
                         out.println("<td>");
                         out.println("<form name='frmComentar' action='otroComment' method='post'>");
                         out.println("<Input type='text' style = 'height:40px;' size='50' maxlength='179' name='comentario' value='' /></form>");
                         out.println("<Input type='submit' name='comentar' value='Agregar Comentario'/></form>");
                         out.println("</td>");
                         out.println("</tr>");
                         out.println("</table>");
                         }
                         }
                         */
                        /*
                         Comment c1 = new Comment("jorge@renteria.com.mx", "Jorge Perez", "Hola1");
                         Comment c2 = new Comment("jorge@renteria.com.mx", "Jorge Perez", "Hola2");
                         Comment c3 = new Comment("jorge@renteria.com.mx", "Jorge Perez", "Hola3");
                         Comment[] comments = new Comment[20]; //con.getCommentsByUser(userProfile);
                         comments[0] = c1;
                         comments[1] = c2;
                         comments[2] = c3;
                         */
                        Comment[] comments = con.getCommentsByUser(userProfile);
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
                            out.println("Hubo un erro al tratar de obtener los comentarios");
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
