<%-- 
    Document   : insertWAR
    Created on : May 30, 2013, 10:25:46 PM
    Author     : Andrej Makovicky
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>WSDLInsert</title>
    </head>
    <body>
        <h2>Insert WSDL</h2>
        </br>
        <div id="topNav">
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="showWSDL.jsp">Show WSDLs</a></li>
                <li><a href="findWSDL.jsp">Find WSDL</a></li>
            </ul>
        </div> 
        
        <s:form action="wsdlUpload" beanclass="WSDLActionBean">
            <s:file name="WSDL"/> 
        </s:form>
        


    </body>
</html>
