<%@ include file="/init.jsp"%>

<p>
	<liferay-portlet:resourceURL id="downloadaction" var="downloadaction">
		<portlet:param name="path" value="PARAM_PATH" />
	</liferay-portlet:resourceURL>

	<input type="text" id="pathinput" value="" style="min-width: 500px;"
		placeholder="digite o caminho do arquivo no servidor" />
		<br/><br/><br/><br/>
	<button type="button" id="dowloadbtn">Download</button>


	<script>
	var downloadaction = "<%=downloadaction%>";

		$("#dowloadbtn").click(function() {
			var path = $("#pathinput").val();
			window.open(downloadaction.replace("PARAM_PATH", path), "_blank");
		});
	</script>

</p>