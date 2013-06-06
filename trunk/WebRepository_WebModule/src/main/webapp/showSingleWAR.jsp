<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Show WAR and web.xml</title>
    </head>
    <body>
        <h2>WAR archiver</h2>
        <script src="https://google-code-prettify.googlecode.com/svn/loader/run_prettify.js"></script>
        </br>
        <div id="topNav">
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="showWAR.jsp">Show All</a></li>
                <li><a href="insertWAR.jsp">Upload</a></li>
                <li><a href="findWAR.jsp">Search</a></li>
            </ul>
        </div> 
        <div id="viewer">
           Id: ${actionBean.getDocument().getId()}
           File: ${actionBean.getDocument().getFileName()}
           Date: ${actionBean.getDocument().getTimestamp()}
           </br></br>
           Web.xml: </br>
           <pre class="prettyprint">${actionBean.getDocument().getWebXml()}
           </pre> 
           Filters and listeners:
           <pre class="prettyprint">${actionBean.getDocument().getExtract()}
           </pre> 
        </div>
    </body>
</html>
