<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Insert WAR</title>
    </head>
    <body>
        <h2>Insert WAR</h2>
        </br>
        <div id="topNav">
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="showWAR.jsp">Show all</a></li>
                <li><a href="findWAR.jsp">Search</a></li>
            </ul>
        </div> 
        
        <s:form action="warUpload" method="POST" beanclass="org.cz.muni.fi.pb138.webrep_A.ActionBeans.WARActionBean" >
            <s:file name="warInput"/>
            </br>
            <s:submit name="warUpload">Submit</s:submit>
        </s:form>
        </br>
    </body>
</html>

