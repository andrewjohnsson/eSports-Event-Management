<%--
  Created by IntelliJ IDEA.
  User: andrewjohnsson
  Date: 29.02.16
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<p>Users</p>
<s:if test="persons.size > 0">
  <table>
    <s:iterator value="persons">
      <tr id="row_<s:property value="id"/>">
        <td>
          <s:property value="name" />
        </td>
        <td>
          <s:property value="login" />
        </td>
        <td>
          <s:url id="removeUrl" action="delete">
            <s:param name="id" value="id" />
          </s:url>
          <s:a href="%{removeUrl}" theme="ajax" targets="persons">Remove</s:a>
          <s:a id="a_%{id}" theme="ajax" notifyTopics="/edit">Edit</s:a>
        </td>
      </tr>
    </s:iterator>
  </table>
</s:if>
