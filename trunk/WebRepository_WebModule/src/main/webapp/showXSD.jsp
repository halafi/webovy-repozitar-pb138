<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<jsp:useBean id="actionBean" scope="page"
             class="org.cz.muni.fi.pb138.webrep_A.ActionBeans.XSDActionBean"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Show XSDs</title>
    </head>
    <body>
        <h2>XSD archivation</h2>
        </br>
        <div id="topNav">
            <ul>
                <li><a href="index.jsp">Home</a></li>
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
                <c:forEach items="${actionBean.getXSDs()}" var="XSD">
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
