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
        boolean miPerfil = false;
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
        <table width="800" border="0" align="center">
            <tr>
               <td colspan="2" style="background-color:#EEEEEE;" align ="right">
                    <h1 align ="left">Trending Topics</h1>
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
                </td>
                <td style="background-color:#EEEEEE;height:100px;width:500px;">
                    <%
                        String[] hashes = con.trendingTopics();
                        //String[] hashes = new String[20];
                        //hashes[0]="GEORGEESGAY";hashes[1]="Dos";hashes[2]="Tres";
                        if (hashes != null) {
                            int i = 1;
                            for (String s : hashes) {
                                if (s != null) {
                                    out.println("<table border='0'>");
                                    out.println("<tr>");
                                    out.println("<th></th>");
                                    out.println("<td><a href='buscar?tipoBusqueda=xhashtag&xbuscar=" + s + "'>" + i + ". #" + s + "</a></td>");
                                    out.println("</tr>");
                                    out.println("</table>");
                                    i++;
                                }

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

