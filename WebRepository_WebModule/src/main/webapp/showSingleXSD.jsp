<%-- 
    Document   : showSinlgeXSD
    Created on : Jun 1, 2013, 3:14:25 PM
    Author     : Andrej Makovicky
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Show Single XSD Scheme</title>
    </head>
    <body>
        <h2>XSD archiver</h2>
        </br>
        <div id="topNav">
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="showXSD.jsp">Show All XSDs</a></li>
                <li><a href="insertXSD.jsp">Insert XSD</a></li>
                <li><a href="findXSD.jsp">Find XSD</a></li>
            </ul>
        </div> 
        <div id="viewer">
            <textarea contenteditable="no" cols="120" rows="30">${actionBean.getDocument()}
            </textarea> 
        </div>
    </body>
</html>
