<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- prefix를 지정해주면 이제 아래에에서 sf.form을 사용하면 html이 아닌 spring의 form을 사용하겠다는 의미 --%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
  <title>Title</title>
  <link rel="stylesheet" type="text/css"
        href="${pageContext.request.contextPath}/resources/css/main.css" >

</head>
<body>


<%-- 현재페이지 url에 뒤에 /docreate 붙여서 요청                                   모델에 들어가있는 offer라는 객체로부터 가져오겠다  --%>
<sf:form method="post" action="${pageContext.request.contextPath}/docreate" modelAttribute="offer">
  <table class="formtable">
    <tr>
      <td class="label"> Name:</td>                                   <%-- 이 입력받은 값들을 위에 modelAttribute에서 지정해준 객체에 저장하겠다ㅣ --%>
      <td><sf:input class="control" type="text" path="name"/> <br/>
        <sf:errors path="name" class="error"/>   <%-- 에러가 있으면 해당 레이블에 출력 (모델에서 예외처리에 적었던 message)  --%>
      </td>
    </tr>
    <tr>
      <td class="label"> Email:</td>
      <td><sf:input class="control" type="text" path="email"/> <br/>
        <sf:errors path="email" class="error"/>
      </td>
    </tr>
    <tr>
      <td class="label"> Offer:</td>
      <td><sf:textarea class="control" rows="10" cols="10" path="text"></sf:textarea>  <br/>
        <sf:errors path="text" class="error"/>
      </td>
    </tr>
    <tr>
      <td></td>
      <td><input type="submit" value="새 제안"/> </td>
    </tr>
  </table>
</sf:form>

</body>
</html>