<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/head.jsp" %>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>用户管理页面</span>
    </div>
    <div class="search">
        <form method="get" action="/user/userlist.html">
            <input name="method" value="query" class="input-text" type="hidden">
            <span>用户名：</span>
            <input name="queryname" class="input-text" type="text" value="${queryUserName }">

            <span>用户角色：</span>
            <select name="queryUserRole">
                <c:if test="${roleList != null }">
                    <option value="0">--请选择--</option>
                    <c:forEach var="role" items="${roleList}">
                        <option
                                <c:if test="${role.id == queryUserRole }">selected="selected"</c:if>
                                value="${role.id}">${role.roleName}</option>
                    </c:forEach>
                </c:if>
            </select>

            <input type="hidden" name="pageIndex" value="1"/>
            <input value="查 询" type="submit" id="searchbutton">
            <a href="/user/addUser.html">添加用户</a>
        </form>
    </div>
    <!--用户-->
    <table class="providerTable" cellpadding="0" cellspacing="0">
        <tr class="firstTr">
            <th width="10%">用户编码</th>
            <th width="20%">用户名称</th>
            <th width="10%">性别</th>
            <th width="10%">年龄</th>
            <th width="10%">电话</th>
            <th width="10%">用户角色</th>
            <th width="30%">操作</th>
        </tr>
        <c:forEach var="user" items="${userList }" varStatus="status">
            <tr>
                <td>
                    <span>${user.userCode }</span>
                </td>
                <td>
                    <span>${user.userName }</span>
                </td>
                <td>
							<span>
								<c:if test="${user.gender==1}">男</c:if>
								<c:if test="${user.gender==2}">女</c:if>
							</span>
                </td>
                <td>
                    <span>${user.age}</span>
                </td>
                <td>
                    <span>${user.phone}</span>
                </td>
                <td>
                    <span>${user.userRoleName}</span>
                </td>
                <td>
                    <span><a class="viewUser" href="javascript:;" userid=${user.id } username=${user.userName }><img
                            src="/statics/images/read.png" alt="查看" title="查看"/></a></span>
                    <span><a class="modifyUser" href="javascript:;" userid=${user.id } username=${user.userName }><img
                            src="/statics/images/xiugai.png" alt="修改" title="修改"/></a></span>
                    <span><a class="deleteUser" href="javascript:;" userid=${user.id } username=${user.userName }><img
                            src="/statics/images/schu.png" alt="删除" title="删除"/></a></span>
                </td>
            </tr>
        </c:forEach>
    </table>
    <input type="hidden" id="totalPageCount" value="${totalPageCount}"/>
    <c:import url="rollpage.jsp">
        <c:param name="totalCount" value="${totalCount}"/>
        <c:param name="currentPageNo" value="${currentPageNo}"/>
        <c:param name="totalPageCount" value="${totalPageCount}"/>
    </c:import>


    <%-------------%>
    <div class="providerAdd">
        <!--div的class 为error是验证错误，ok是验证成功-->
        <div>
            <label>用户编码：</label>
            <input type="text" id="v_userCode" value="" readonly="readonly">
            <!-- 放置提示信息 -->
            <font color="red"></font>
        </div>
        <div>
            <label>用户名称：</label>
            <input type="text" id="v_userName" value="" readonly="readonly">
            <font color="red"></font>
        </div>
        <div>
            <label>用户密码：</label>
            <input type="password" id="v_userPassword" value="" readonly="readonly">
            <font color="red"></font>
        </div>
        <div>
            <label>用户性别：</label>
            <input type="text" id="v_gender" value="">
        </div>
        <div>
            <label>用户角色：</label>
            <!-- 列出所有的角色分类 -->
            <input type="text" id="v_userRole" value="" readonly="readonly">
                <%--<option value="1">系统管理员</option>
                <option value="2">经理</option>
                <option value="3">普通员工</option>--%>
            <font color="red"></font>
        </div>
        <div>
            <label>出生日期：</label>
            <input type="text" Class="Wdate" id="v_birthday"
                   readonly="readonly" onclick="WdatePicker();">
            <font color="red"></font>
        </div>
        <div>
            <label>用户电话：</label>
            <input type="text" id="v_phone" value="" readonly="readonly">
            <font color="red"></font>
        </div>
        <div>
            <label>用户地址：</label>
            <input id="v_address" value="" readonly="readonly">
        </div>
        <div>
            <label>创建日期：</label>
            <input type="text" Class="Wdate" id="v_creationDate"
                   readonly="readonly" onclick="WdatePicker();">
            <font color="red"></font>
        </div>
    </div>
</div>

</div>
</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeUse">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该用户吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

<%@include file="common/foot.jsp" %>
<script type="text/javascript" src="/statics/js/userlist.js"></script>
