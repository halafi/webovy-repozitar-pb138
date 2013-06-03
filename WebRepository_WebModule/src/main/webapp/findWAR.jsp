<%-- 
    Document   : findWeb
    Created on : May 30, 2013, 10:25:23 PM
    Author     : Andrej Makovicky
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>WAR Search</title>
    </head>
    <body>
        <h2>Search in WARs</h2>
        </br>
        <div id="topNav">
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="showWAR.jsp">Show all</a></li>
                <li><a href="insertWAR.jsp">Upload</a></li>
            </ul>
        </div> 

        <div>
        <fieldset>
            <table>
            <s:form beanclass="org.cz.muni.fi.pb138.webrep_A.ActionBeans.WARActionBean" method="GET">
                        <tr>
                            <th>Id:</th>
                            <th><s:text id="id" name="idInput"/></th> 
                            <th><s:submit name="searchId">Search</s:submit></th>
                        </tr>
            </s:form>   
            <s:form beanclass="org.cz.muni.fi.pb138.webrep_A.ActionBeans.WARActionBean" method="GET">
                        <tr>
                            <th>Web.xml filter name:</th>
                            <th><s:text id="data" name="dataInput"/> </th>
                            <th><s:submit name="searchData">Search</s:submit> </th>
                        </tr>
            </s:form>
            </table>
        </fieldset>
        </div>




    </body>
</html>
