<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<f:message var="nadpis" key="top.main"/>
<f:message var="welcome" key="welcome"/>



<s:layout-render name="/mainLayout.jsp" nadpis="${nadpis}">
    <s:layout-component name="telo">

        <s:label name="${welcome}"/>
        <br/>
    </s:layout-component>
</s:layout-render>