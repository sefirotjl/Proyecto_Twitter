<%-- 
    Document   : perfil
    Created on : 11-mar-2013, 13:45:33
    Author     : Juancho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String me = pageContext.getAttribute("user").toString();
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
                </td>
                <td style="background-color:#EEEEEE;height:200px;width:400px;">
                    <%
                        String[] hashes = con.getTrendingTopics();
                        if (hashes != null) {
                            int i=1;
                            for (String s : hashes) {
                                out.println("<table border='0'>");
                                out.println("<tr>");
                                out.println("<th></th>");
                                out.println("<td><a href='buscar?tipoBusqueda=hashtag&buscar="+ s +"'>" + i +". "+ s + "</a></td>");
                                out.println("</tr>");
                                out.println("</table>");
                            i++;
                            }
                        } else {
                            out.println("Hubo un erro al tratar de obtener los hash");
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

