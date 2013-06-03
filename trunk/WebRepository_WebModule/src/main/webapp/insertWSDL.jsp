<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Insert WSDL</title>
    </head>
    <body>
        <h2>Insert WSDL</h2>
        </br>
        <div id="topNav">
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="showWSDL.jsp">Show all</a></li>
                <li><a href="findWSDL.jsp">Search</a></li>
            </ul>
        </div> 
        
        <s:form action="wsdlUpload"  beanclass="org.cz.muni.fi.pb138.webrep_A.ActionBeans.WSDLActionBean" method="POST" >
            <s:file name="wsdlInput"/>
            </br>
            <s:submit name="wsdlUpload">Submit</s:submit>
        </s:form>
        </br>
    </body>
</html>
