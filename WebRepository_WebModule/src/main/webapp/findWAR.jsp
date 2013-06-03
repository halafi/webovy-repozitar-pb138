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
            <s:form beanclass="org.cz.muni.fi.pb138.webrep_A.ActionBeans.WARActionBean" method="GET">
                <fieldset>
                    <table>
                        <tr>
                            <th> Id : </th>
                            <th><s:text id="id" name="idInput"/></th> 
                        </tr>
                    </table>
                    <s:submit name="searchId">Search by ID</s:submit>
                    </fieldset>
            </s:form>   

            <s:form beanclass="org.cz.muni.fi.pb138.webrep_A.ActionBeans.WARActionBean" method="GET">
                <fieldset>
                    <table>
                        <tr>
                            <th>Input data:</th>
                            <th><s:text id="data" name="dataInput"/> </th>
                        </tr>
                    </table>
                    <s:submit name="searchData">Search by web.xml filter name</s:submit>
                    </fieldset>
            </s:form>
        </div>




    </body>
</html>
