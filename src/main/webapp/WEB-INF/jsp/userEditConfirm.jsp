<%@ page contentType="text/html; charset=utf-8" session="false" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Confirm</title>
    </head>    
    <body>

        <form:form action="complete.html" method="POST" modelAttribute="userForm">
            <form:hidden path="user.id"/>
            <table border="0">
                <tr>
                    <th><spring:message code="model.user.name" /><br></th>
                    <td><form:input path="user.name" readonly="true"/><br><form:errors path="user.name" cssStyle="color:red" /></td>
                </tr>
                <tr>
                    <th><spring:message code="model.user.age"/><br></th>
                    <td><form:input path="user.age" readonly="true" /><br><form:errors path="user.age" cssStyle="color:red"/></td>
                </tr>
            </table>
            <br>
            <input type="submit" value="完了" />
        </form:form>

    </body>
</html>
