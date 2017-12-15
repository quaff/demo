<!DOCTYPE html>
<#escape x as x?html><html>
<head>
<title><#if user.new>${getText('create')}<#else>${getText('edit')}</#if>${getText('user')}</title>
</head>
<body>
<@s.form id="user_input" action="${actionBaseUrl}/save" method="post" class="ajax form-horizontal sequential_create">
	<#if !user.new>
		<@s.hidden name="user.id" />
		<@s.textfield name="user.username" readonly="true"/>
		<@s.password name="password"/>
		<@s.password name="confirmPassword"/>
	<#else>
		<@s.textfield name="user.username" class="required checkavailable regex conjunct" data\-replacement="controls-user-roles" data\-regex="${statics['org.ironrhino.security.model.User'].USERNAME_REGEX}" data\-checkurl="${actionBaseUrl}/checkavailable"/>
		<@s.password name="password" class="required"/>
		<@s.password name="confirmPassword" class="required"/>
	</#if>
	<@s.textfield name="user.name" class="required"/>
	<@s.textfield name="user.email" type="email" class="email checkavailable" data\-checkurl="${actionBaseUrl}/checkavailable"/>
	<@s.textfield name="user.phone"/>
	<@s.checkbox name="user.enabled" class="custom" />
	<@s.checkboxlist name="user.roles" list="roles" listKey="value" listValue="label" class="custom">
	<#if hiddenRoles??&&hiddenRoles?size gt 0>
	<@s.param name="after">
		<#list hiddenRoles as role>
			<input type="hidden" name="user.roles" value="${role}"/>
		</#list>
	</@s.param>
	</#if>
	</@s.checkboxlist>
	<@s.textfield name="user.department" class="treeselect-inline" data\-url="/department/children" data\-separator="/" data\-text=(user.department.fullname)!/>
	<@s.submit value=getText('save') class="btn-primary"/>
</@s.form>
</body>
</html></#escape>


