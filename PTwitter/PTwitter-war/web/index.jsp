<%-- 
    Document   : index
    Created on : 11-mar-2013, 13:03:04
    Author     : Juancho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Iniciar secion</h1>
        <form name='inicio' action='login' method='post'>
            Usuario(e-mail): <input type='text' name='email' value=''></br>
            Contraseña: <input type='password' name='password' value=''></br>
            Nombre*: <input type='text' name='name' value=''></br>
            <Input type='submit' name='login' value='Login'/>
            <Input type='submit' name='register' value='Registrarse'/></br>
        </form>
        *Este parametro es unicamente necesario para cuando quieres registrarte</br>
        <%
            if (request.getParameter("resp") != null) {
                out.print("<h1>!!!" + request.getParameter("resp") + "¡¡¡</h1>");
            }
        %>
    </body>
</html>
