<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<jsp:useBean id="actionBean" scope="page"
             class="org.cz.muni.fi.pb138.webrep_A.ActionBeans.WARActionBean"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Show Webs </title>
    </head>
    <body>
        <h2>WAR archiver </h2>
        </br>
        <div id="topNav">
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="insertWeb.jsp">Extract web.xml</a></li>
                <li><a href="findWeb.jsp">Find web.xml</a></li>
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
                <s:useActionBean beanclass="org.cz.muni.fi.pb138.webrep_A.ActionBeans.WARActionBean" var="actionBean"/>
                <c:forEach items="${actionBean.getWARs()}" var="WAR">
                    <tr>
                        <td><c:out value="${WAR.getId()}"/></td>
                        <td><c:out value="${WAR.getFileName()}"/></td>
                        <td><c:out value="${WAR.getTimestamp()}"/></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
