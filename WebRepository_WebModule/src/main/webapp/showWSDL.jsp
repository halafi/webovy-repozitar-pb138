<%-- 
    Document   : showWSDL
    Created on : May 30, 2013, 8:55:33 PM
    Author     : Andrej Makovicky
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border="1">
    <thead>
    <tr>
        <th>jmeno</th>
        <th>adresa</th>
        <th>email</th>
        <th>telefon</th>
        
    </tr>
    </thead>
    <c:forEach items="${customers}" var="customer">
        <tr>
            <td><c:out value="${customer.name}"/></td>
            <td><c:out value="${customer.address}"/></td>
            <td><c:out value="${customer.email}"/></td>
            <td><c:out value="${customer.phone}"/></td>
            
        <td><form method="post" action="${pageContext.request.contextPath}/customers/delete?id=${customer.id}"
                      style="margin-bottom: 0;"><input type="submit" value="Smazat"></form></td>
        <td><form method="post" action="${pageContext.request.contextPath}/customers/show?id=${customer.id}"
                      style="margin-bottom: 0;"><input type="submit" value="Editovat"></form>
        </td>
                      
        </tr>
    </c:forEach>
</table>		
    </body>
</html>
