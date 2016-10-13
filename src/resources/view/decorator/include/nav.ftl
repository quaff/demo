<ul class="nav">
  <li><a href="<@url value="/"/>" class="ajax view">${getText("index")}</a></li>
  <@authorize ifAnyGranted="ROLE_ADMINISTRATOR">
  <li><a href="<@url value="/user"/>" class="ajax view">${getText("user")}</a></li>
	</@authorize>
</ul>