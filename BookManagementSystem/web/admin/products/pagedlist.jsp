<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<HTML>
<HEAD>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${pageContext.request.contextPath}/admin/css/Style.css"
          rel="stylesheet" type="text/css" />
    <script language="javascript" src="${pageContext.request.contextPath}/admin/js/public.js"></script>
</HEAD>
<body>
<br>
<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
        <TBODY>
        <tr>
            <td class="ta_01" align="center" bgColor="#afd1f3"><strong>商品列表</strong>
            </TD>
        </tr>
        <tr>
            <td class="ta_01" align="center" bgColor="#f5fafe">
                <table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray" border="1" id="DataGrid1"
                       style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
                    <tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
                        <td align="center" width="24%">商品编号</td>
                        <td align="center" width="18%">商品名称</td>
                        <td align="center" width="9%">商品价格</td>
                        <td align="center" width="9%">商品数量</td>
                        <td width="8%" align="center">商品类别</td>
                    </tr>

                    <c:forEach items="${bookList.getList()}" var="book">
                        <tr onmouseover="this.style.backgroundColor = 'white'"
                            onmouseout="this.style.backgroundColor = '#F5FAFE';">
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="23">${book.id}</td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="18%">${book.name}</td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="8%">${book.price}</td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="8%">${book.pnum}</td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center">${book.category}</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="5" align="right">
                           当前页 &nbsp;${bookList.getCurrentPage()}/${bookList.getTotalPage()} &nbsp;&nbsp;总条数&nbsp;${bookList.getTotalCount()}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td colspan="5" align="center">
                            <c:if test="${bookList.getCurrentPage()-1 != 0}">
                                <a href="${pageContext.request.contextPath}/PagedBookListServlet?pageIndex=${bookList.getCurrentPage()-1}">上一页</a>
                            </c:if>
                            <c:forEach begin="1" end="${bookList.getTotalPage()}" var="p">
                                <a href="${pageContext.request.contextPath}/PagedBookListServlet?pageIndex=${p}">${p}&nbsp;&nbsp;</a>
                            </c:forEach>
                            <c:if test="${bookList.getCurrentPage() < bookList.getTotalPage()}">
                                <a href="${pageContext.request.contextPath}/PagedBookListServlet?pageIndex=${bookList.getCurrentPage()+1}">下一页</a>
                            </c:if>
                        </td>
                    </tr>


                </table>
            </td>
        </tr>
        </TBODY>
    </table>
</form>
</body>
</HTML>

