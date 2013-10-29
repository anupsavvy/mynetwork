<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="../../assets/ico/favicon.png">

<title>My Network</title>

<!-- styles -->
<link href="css/mynetwork.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="../../assets/js/html5shiv.js"></script>
      <script src="../../assets/js/respond.min.js"></script>
    <![endif]-->
</head>

<body onload="generateLink();">
<%
	HttpSession ses = (HttpSession) request.getSession();
	ses.setAttribute("progress",5.0);
%>
	<div class="container">
		<div class="header">
			<h3 class="text-muted">C-IKNOW</h3>
		</div>

		<div class="jumbotron">
			<h1>My Network</h1>
			<p class="lead">'My Network' would get your network json from
				LinkedIn through API calls and convert it to csv format compatible
				to your network analysis tool.</p>
			<table id="steps" class="well">
				<tbody>
					<tr>
						<td class="col-md-2"><img src="img/one.png" height="60"
							width="60"></img><br></td>
						<td><a id="link" href="#" target="_blank"
							style="cursor: pointer;"> Authenticate yourself through
								LinkedIn</a></td>
					</tr>
					<tr>
						<td><br> <img src="img/two.png" height="50" width="50"></img><br></td>
						<!-- <td><input id="code" type="email" class="form-control col-md-offset-3"
							placeholder="Enter verification code"></input></td> -->
						<td><div class="input-group col-md-offset-1">
								<input id="codein" type="text" class="form-control"> <span
									class="input-group-btn">
									<a id="codebtn" class="btn btn-default"
										data-bind="click: sendCode">Submit</a>
								</span>
							</div></td>
					</tr>
					<tr>
						<td><br> <img src="img/three.png" height="60" width="60"></img></td>
						<td>
							<form method="GET" action="download">
								<button id="download" type="submit"
									class="btn btn-lg btn-primary">Download the data  <img id="handimg" src="img/hand-left.png"/></button>
							</form>
						</td>
					</tr>
				</tbody>
			</table>
		</div>

		<div class="footer">
			<p>&copy; SONIC @ Northwestern University.</p>
		</div>

	</div>
	<!-- /container -->
	<!-- Modal -->
	<div class="modal fade" id="progressbar" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Importing your network</h4>
				</div>
				<div class="modal-body">
				<%
					Double progress = (Double) ses.getAttribute("progress");
				%>
					<!-- <div class="progress">
						<div class="progress-bar progress-bar-success" role="progressbar" aria-valuemin="0" aria-valuemax="100"
							style="width: <%=progress.doubleValue()%>%">
						</div>
					</div> -->
					<h3><img src="img/wait.gif" />  Please wait while we process your data...</h3>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<!-- <pre data-bind="text: ko.toJSON($root, null, 2)"></pre> -->
</body>
<!-- Bootstrap JS: compiled and minified -->
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/knockout-2.2.0.js"></script>
<script src="js/index.js"></script>
<script>
function loadModal() {
		$('#progressbar').modal({show: true, 
	        backdrop: 'static'});
	}
</script>
</html>
