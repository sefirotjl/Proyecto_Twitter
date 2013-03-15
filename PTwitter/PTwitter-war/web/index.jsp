<%-- 
    Document   : index
    Created on : 11-mar-2013, 13:03:04
    Author     : Juancho
--%>
<%
    if (application.getAttribute("login") != null) {
        if ((Boolean) application.getAttribute("login")) {
            response.sendRedirect("perfil.jsp");
        }
    } else {
        application.setAttribute("login", false);
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body background="black.jpg">
        <table width="800" border="0" align="center" >
            <tr>
                <td colspan="2" style="background-color:#EEEEEE;" align ="right">
                    <h1 align ="left">Iniciar secion</h1>
                </td>
            </tr>
            <tr>
                <td style="background-color:#EEEEEE;width:80px;">

                </td>
                <td style="background-color:#FFFFFF;height:525px;width:500px; align:top">
                    <form name='inicio' action='login' method='post'>
                        Usuario(e-mail): <input type='email' name='email' value=''></br>
                        Contraseña: <input type='password' name='password' value=''></br>
                        Nombre de Usuario*: <input type='text' name='nombre' value=''></br>
                        <Input type='submit' name='login' value='Login'/>
                        <Input type='submit' name='register' value='Registrarse'/></br>
                    </form>
                    *Este parametro es unicamente necesario para cuando quieres registrarte.</br>
                    **Todo error ortografico en este sitio es intencional.
                    <%
                        if (request.getParameter("resp") != null) {
                            out.print("<h1>!!!" + request.getParameter("resp") + "¡¡¡</h1>");
                        }
                    %></td>
            </tr>


            <tr>
                <td colspan="2" style="background-color:#EEEEEE;text-align:center;">
                    Proyecto 1 - Lab. Sistemas Distribuidos</td>
            </tr>
        </table>
    </body>
</html>
