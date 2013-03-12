<%-- 
    Document   : perfil
    Created on : 11-mar-2013, 13:45:33
    Author     : Juancho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Consultas con = new Consultas();
    String me = pageContext.getAttribute("user").toString();
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
                </td>
                <td style="background-color:#EEEEEE;height:200px;width:400px;">
                    <%

                        Usuario[] usuarios = con.getUsersByName(request.getParameter("buscar"));
                        if (usuarios != null) {
                            for (Usuario u : usuarios) {
                                out.println("<table border='0'>");
                                out.println("<tr>");
                                out.println("<th></th>");
                                out.println("<td>" + "<a href='perfil.jsp?user=" + u.getEmail()
                                        + "'>" + u.getNombre() + "</a></td>");
                                out.println("</tr>");
                                out.println("</table>");
                            }
                        } else {
                            out.println("No se encontro ninguna respuesta para la busqueda de: " + request.getParameter("buscar"));
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
