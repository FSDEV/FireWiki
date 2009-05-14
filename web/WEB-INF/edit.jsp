<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form action="<c:url value="/edit"/>?page=<%= request.getParameter("page") %>" method="POST">
    <table align="center" width="80%">
        <tr align="center">
            <td>page path</td>
            <td><input type="text" name="newPage" value="${newPage}" size="25"/></td>
            <td>use this to change the name of the page</td>
        </tr>
        <tr align="center">
            <td colspan="3"><textarea cols="90" rows="15" name="editText"><%= request.getAttribute("editText")%></textarea></td>
        </tr>
        <tr align="center">
            <td><input type="submit" name="preview" value="preview"/></td>
            <td></td>
            <td><input type="submit" value="save" name="save"/></td>
        </tr>
        <tr>
            <td colspan="3">
                <center>
                    <input type="submit" name="delete" value="delete"/>
                </center>
            </td>
        </tr>
    </table>
</form>