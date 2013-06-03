<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Show XSD search result</title>
    </head>
    <body>
        <h2>XSD archiver</h2>
        </br>
        <div id="topNav">
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="showXSD.jsp">Show All</a></li>
                <li><a href="insertXSD.jsp">Upload</a></li>
                <li><a href="findXSD.jsp">Search</a></li>
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
                <s:useActionBean beanclass="org.cz.muni.fi.pb138.webrep_A.ActionBeans.XSDActionBean" var="actionBean"/>
                <c:forEach items="${actionBean.getResultList()}" var="XSD">
                    <tr>
                        <td><c:out value="${XSD.getId()}"/></td>
                        <td><c:out value="${XSD.getFileName()}"/></td>
                        <td><c:out value="${XSD.getTimestamp()}"/></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
