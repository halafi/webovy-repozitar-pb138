<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>XSD Search</title>
    </head>
    <body>
        <h2>Search in XSDs</h2>
        </br>
        <div id="topNav">
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="showXSD.jsp">Show XSDs</a></li>
                <li><a href="insertXSD.jsp">Insert XSD</a></li>
            </ul>
        </div> 

        <div>        
            <s:form beanclass="org.cz.muni.fi.pb138.webrep_A.ActionBeans.XSDActionBean" method="GET">
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

            <s:form beanclass="org.cz.muni.fi.pb138.webrep_A.ActionBeans.XSDActionBean" method="GET">
                <fieldset>
                    <table>
                        <tr>
                            <th>Input data:</th>
                            <th><s:text id="data" name="dataInput"/> </th>
                        </tr>
                    </table>
                    <s:submit name="searchData">Search by data</s:submit>
                    </fieldset>
            </s:form>
        </div>
    </body>
</html>
