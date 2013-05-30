<%-- 
    Document   : mainLayout
    Created on : May 30, 2013, 1:57:29 PM
    Author     : Andrej Makovicky
--%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<s:layout-definition>
    <html>
        <head>
            <title><c:out value="Archivator 3000+" /></title>
            <link rel="stylesheet" type="text/css" href="/style.css" />
<!--            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-ui-1.9.2.custom/css/smoothness/jquery-ui-1.9.2.custom.min.css" />
            <script src="${pageContext.request.contextPath}/jquery-1.8.3.min.js" ></script>
            <script src="${pageContext.request.contextPath}/jquery-ui-1.9.2.custom/js/jquery-ui-1.9.2.custom.min.js" ></script>-->

            <!--            <script>
                            $(function() {
                                $( ".datepicker" ).datepicker({ dateFormat: "dd. mm. yy" });
                            });
                        </script>-->

            <s:layout-component name="hlavicka"/>
        </head>
        <body>
            <h1><c:out value="${nadpis}" /></h1>
            <div id="navigace">
                <ul>
                    <li><s:link href="/index.jsp"><f:message key="mainLayout.home"/></s:link></li>
                    <li><s:link href="/players"><f:message key="mainLayout.players"/></s:link></li>
                    <li><s:link href="/teams"><f:message key="mainLayout.teams"/></s:link></li>
                    <li><s:link href="/rosters"><f:message key="mainLayout.rosters"/></s:link></li>
                    </ul>
                </div>
                <div id="obsah">
                <s:layout-component name="telo"/>
                
            </div>

        </body>
    </html>
</s:layout-definition>
