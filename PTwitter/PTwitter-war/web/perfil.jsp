<%-- 
    Document   : perfil
    Created on : 11-mar-2013, 13:45:33
    Author     : Juancho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    boolean miPerfil = false;
    String userProfile = request.getParameter("user");
    String me = pageContext.getAttribute("user").toString();
    if (me.equals(userProfile)) {
        //estoy en mi perfil
        miPerfil = true;
    }
    Consultas con = new Consultas();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table width="500" border="0">
            <tr>
                <td colspan="2" style="background-color:#EEEEEE;">
                    <h1>Main Title of Web Page</h1>
                    <form name='frmBuscar' action='buscar' method='post'>
                        Buscar: <input type='text' name='buscar' value=''></br>
                        <input type="radio" name="tipoBusqueda" value="usuario"> Usuarios
                        <input type="radio" name="tipoBusqueda" value="hashtag"> Hashtags
                        <Input type='submit' name='btnBuscar' value='Iniciar Busqueda'/>
                    </form>
                </td>
            </tr>

            <tr>
                <td style="background-color:#EEEEEE;width:100px;">
                    <b>Menu</b><br>
                    <a href='trending.jsp'>Trending Topics</a><br>
                    <%
                        out.println("<a href='perfil.jsp?user=" + me + "'>Mi perfil</a>");
                    %>
                    <%
                        if (!miPerfil && !con.siguiendo(me, userProfile)) {
                            out.println("<form name='frmSeguir' action='seguir' method='post'>");
                            out.println("<Input type='submit' name='seguir' value='Seguir'/></form>");
                        }
                    %>

                </td>
                <td style="background-color:#EEEEEE;height:200px;width:400px;">
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
                            out.println("<form name='frmComentar' action='miComment' method='post'>");
                            out.println("<Input type='text' rows='4' cols='50' name='comentario' value='' /></form>");
                            out.println("<Input type='submit' name='comentar' value='Agregar Comentario'/></form>");
                            out.println("</td>");
                            out.println("</tr>");
                            out.println("</table>");
                        } else {
                            if (!miPerfil && con.siguiendo(me, userProfile)) {
                                out.println("<table border='0'>");
                                out.println("<tr>");
                                out.println("<th></th>");
                                out.println("<td>" + userProfile + "</td>");
                                out.println("</tr>");
                                out.println("<tr>");
                                out.println("<th></th>");
                                out.println("<td>");
                                out.println("<form name='frmComentar' action='otrocComment' method='post'>");
                                out.println("<Input type='text' rows='4' cols='50' name='comentario' value='' /></form>");
                                out.println("<Input type='submit' name='comentar' value='Agregar Comentario'/></form>");
                                out.println("</td>");
                                out.println("</tr>");
                                out.println("</table>");
                            }
                        }

                        Comment[] comments = con.getCommentsByUser(userProfile);
                        if (comments != null) {
                            for (Comment c : comments) {
                                out.println("<table border='0'>");
                                out.println("<tr>");
                                out.println("<th></th>");
                                out.println("<td>" + c.getNombre() + "</td>");
                                out.println("</tr>");
                                out.println("<tr>");
                                out.println("<th></th>");
                                out.println("<td>" + c.getComment() + "</td>");
                                out.println("</tr>");
                                out.println("</table>");
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
