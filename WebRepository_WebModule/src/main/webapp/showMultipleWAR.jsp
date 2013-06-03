<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Show WAR search result</title>
    </head>
    <body>
        <h2>WAR archiver</h2>
        </br>
        <div id="topNav">
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="showWAR.jsp">Show All WARs</a></li>
                <li><a href="insertWAR.jsp">Insert WAR</a></li>
                <li><a href="findWAR.jsp">Search WARs by web.xml</a></li>
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
                <c:forEach items="${actionBean.getResultList()}" var="WAR">
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