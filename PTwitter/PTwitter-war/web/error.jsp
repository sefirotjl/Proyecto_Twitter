<%-- 
    Document   : error
    Created on : 13-mar-2013, 10:03:36
    Author     : Juancho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body background="black.jpg">
        <%

            if (request.getAttribute("mensaje") != null) {
                out.println("<h1><font color='FFFFFF'>Se detecto un error:</br>");
                out.println(request.getAttribute("mensaje") + "!</font></h1>");
            } else {
                out.println("<h1><font color='FFFFFF'>Se detecto un error desconocido, porfavor, no hagas eso de nuevo!</font></h1>");
            }
        %>
        <h2><font color='FFFFFF'>
            </br>
            ,pero te dejamos un video para que no te enojes.
            </font>
        </h2>
        <%  if ( Math.random() > 0.5) {
                out.print("<iframe  width = '560' height = '315' src = 'http://www.youtube.com/embed/HLI4EuDckgM' frameborder = '0' autoplay> < / iframe >");
            } else {
                out.print("<iframe  width = '560' height = '315' src = 'http://www.youtube.com/embed/eyqUj3PGHv4' frameborder = '0' autoplay> < / iframe >");
            }
        %>
    </body>
</html>
