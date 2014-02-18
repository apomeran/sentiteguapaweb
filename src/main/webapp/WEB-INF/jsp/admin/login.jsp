<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>


<c:import url="../headerlogin.jsp" />

<br><br>
<form:form class="loginForm form-horizontal" action="${pageContext.request.contextPath}/bin/index/login2" method="POST" commandName="loginForm">
	<form:errors path="*" cssClass="text-error" element="div" />

            <div class="loginUsername control-group ${loginUsernameClass}">
                <label class="control-label">User:</label>
                <div class="controls">
                    <p>
                        <form:input path="email"/>
                    </p>
                </div>
            </div>
            <div class="loginPassword control-group ${loginPasswordClass}">
                <label class="control-label">Password:</label>
                <div class="controls">
                    <p>
                        <form:input type="password" path="password"/>
                    </p>
                </div>
            </div>

            <div class="control-group controls">
                <input class="btn btn-info" type="submit" value="Login">
            </div>

  </form:form>

<c:import url="../footer.jsp" />
