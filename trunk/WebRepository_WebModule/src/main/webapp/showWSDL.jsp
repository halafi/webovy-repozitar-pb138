<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<jsp:useBean id="actionBean" scope="page"
             class="org.cz.muni.fi.pb138.webrep_A.ActionBeans.WSDLActionBean"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Show WSDLs </title>
    </head>
    <body>
        <h2>WSDL archivation</h2>
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
                <s:useActionBean beanclass="org.cz.muni.fi.pb138.webrep_A.ActionBeans.WSDLActionBean" var="actionBean"/>
                <c:forEach items="${actionBean.getWSDLs()}" var="WSDLDoc">
                    <tr>
                        <td><c:out value="${WSDLDoc.getId()}"/></td>
                        <td><c:out value="${WSDLDoc.getFileName()}"/></td>
                        <td><c:out value="${WSDLDoc.getTimestamp()}"/></td>
                        <td>
                            <form action="${actionBean.setId(WSDLDoc.getId())}" method="post" onSubmit="history.go(0)">
                                <input type="hidden" name="id" value="<c:out value="${WSDLDoc.getId()}"/>"/>
                                <input type="Submit" value="View" name="submit"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div id="viewer">
            <textarea contenteditable="no" cols="120" rows="30">
                ${actionBean.getDocument().getId()}
            </br>
                ${actionBean.getDocument()}
            </textarea>
        </div>
    </body>
</html>