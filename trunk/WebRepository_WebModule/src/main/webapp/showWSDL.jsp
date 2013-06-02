<%-- 
    Document   : showWSDL
    Created on : May 30, 2013, 8:55:33 PM
    Author     : Andrej Makovicky
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Show WSDLs </title>
    </head>
    <body>
        <h2>WSDL archiver</h2>
        </br>
        <div id="topNav">
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="insertWSDL.jsp">Insert WSDL</a></li>
                <li><a href="findWSDL.jsp">Find WSDL</a></li>
            </ul>
        </div> 
        <div id="tabulka">
            <table class="zakladni" border="1">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Version</th>


                    </tr>
                </thead>
                <c:forEach items="${WSDLs}" var="WSDLDoc">
                    <tr>
                        <td><c:out value="${WSDLDoc.id}"/></td>
                        <td><c:out value="${WSDLDoc.name}"/></td>
                        <td><c:out value="${WSDLDoc.date}"/></td>


                        <td><form method="post" action="${pageContext.request.contextPath}/customers/delete?id=${customer.id}"
                                  style="margin-bottom: 0;"><input type="submit" value="Smazat"></form></td>
                        <td><form method="post" action="${pageContext.request.contextPath}/customers/show?id=${customer.id}"
                                  style="margin-bottom: 0;"><input type="submit" value="Editovat"></form>
                        </td>

                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
