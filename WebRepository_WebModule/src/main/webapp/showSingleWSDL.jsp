<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Show Single WSDL</title>
    </head>
    <body>
        <h2>WSDL archiver</h2>
        </br>
        <div id="topNav">
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="showWSDL.jsp">Show All WSDLs</a></li>
                <li><a href="insertWSDL.jsp">Insert WSDL</a></li>
                <li><a href="findWSDL.jsp">Find WSDL</a></li>
            </ul>
        </div> 
        <div id="viewer">
           <textarea contenteditable="no" cols="120" rows="30">${actionBean.getDocument()}
            </textarea>
        </div>
    </body>
</html>