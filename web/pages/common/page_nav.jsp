<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2022/1/4
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--分页条的开始-->
<div id="page_nav">
    <%--大于首页才显示--%>
    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
    </c:if>

    <%--			<a href="#">3</a>--%>
    <%--			【${requestScope.page.pageNo}】--%>
    <%--			<a href="#">5</a>--%>

    <c:choose>
        <%--情况1：如果总页码小于等于5 页码范围：1~总页码--%>
        <c:when test="${requestScope.page.pageTotal <= 5}">
            <c:set var="begin" value="1"></c:set>
            <c:set var="end" value="${requestScope.page.pageTotal}"></c:set>
        </c:when>
        <%--情况2：如果总页码大于5--%>
        <c:when test="${requestScope.page.pageTotal > 5}">
            <c:choose>
                <%--（1）当前页码是前面3个的情况--%>
                <c:when test="${requestScope.page.pageNo <= 3}">
                    <c:set var="begin" value="1"></c:set>
                    <c:set var="end" value="5"></c:set>
                </c:when>
                <%--（2）当前页码是后面3个的情况--%>
                <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal -
						3}">
                    <c:set var="begin" value="${requestScope.page.pageTotal - 4}"></c:set>
                    <c:set var="end" value="${requestScope.page.pageTotal}"></c:set>
                </c:when>
                <%--(3)其他页码 页码范围：当前页码-2 ~ 当前页码+2--%>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.page.pageNo - 2}"></c:set>
                    <c:set var="end" value="${requestScope.page.pageNo + 2}"></c:set>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>

    <c:forEach begin="${begin}"
               end="${end}" var="i">
        <c:if test="${i == requestScope.page.pageNo}">
            【${i}】
        </c:if>
        <c:if test="${i != requestScope.page.pageNo}">
            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>


    <%--末页小于最后一页才显示--%>
    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>

    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录 到第<input
        value="${param.pageNo}" name="pn" id="pn_input"/>页
    <input id="searchPageButton" type="button" value="确定">

    <script type="text/javascript">
        $(function (){
            //跳到指定的页码
            $("#searchPageButton").click(function (){
                var pageNo = $("#pn_input").val();
                var pageTotal = ${requestScope.page.pageTotal};
                if(pageNo <= pageTotal && pageNo >= 1){
                    location.href =
                        "${pageScope.bathPath}${requestScope.page.url}&pageNo="+pageNo;
                }
            });
        });
    </script>

</div>
<!--分页条的结束-->
