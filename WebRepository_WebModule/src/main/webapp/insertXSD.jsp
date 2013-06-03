<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Insert XSD</title>
    </head>
    <body>
        <h2>Insert XSD</h2>
        </br>
        <div id="topNav">
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="showXSD.jsp">Show all</a></li>
                <li><a href="findXSD.jsp">Search</a></li>
            </ul>
        </div> 

        <s:form action="xsdUpload" method="POST" beanclass="org.cz.muni.fi.pb138.webrep_A.ActionBeans.XSDActionBean">
            <s:file name="xsdInput"/>
            </br>
            <s:submit name="xsdUpload">Submit</s:submit>
        </s:form>  
        </br> 
    </body>
</html>