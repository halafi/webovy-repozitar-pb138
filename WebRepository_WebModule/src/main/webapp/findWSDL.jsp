<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>WSDL Search</title>
    </head>
    <body>
        <h2>Search in WSDL</h2>
        </br>
        <div id="topNav">
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="showWSDL.jsp">Show WSDLs</a></li>
                <li><a href="insertWSDL.jsp">Insert WSDL</a></li>
            </ul>
        </div> 
        
        <p>Search by ID</p> </br>
        
        <s:form action="search" beanclass="WSDLActionBean">
            <s:text name="textInput"/> 
        </s:form> </br>
        
        <p>Search by data</p> </br>
        
        <s:form action="search" beanclass="WSDLActionBean">
            <s:text name="textInput"/> 
        </s:form>
        
        
        


    </body>
</html>
