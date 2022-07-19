<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
response.addHeader("Pragma", "no-cache");
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.addHeader("Cache-Control", "pre-check=0, post-check=0");
response.setDateHeader("Expires", 0);

if (session.getAttribute("sessionEmail") == null)
	response.sendRedirect("/khairat/login.jsp");

String sesRol = "";
if(session.getAttribute("sessionRole") != null){
	sesRol = (String)session.getAttribute("sessionRole");
	if (sesRol.equalsIgnoreCase("admin"))
		response.sendRedirect("/khairat/login.jsp");
}
int sessionId = (Integer)session.getAttribute("sessionId");
String sesEmail = (String)session.getAttribute("sessionEmail");
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>KARIAH & DEATH BENEFICIARY SYSTEM</title>
<meta charset="UTF-8">
<meta charset="ISO-8859-1">
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet">

<style>
#customers {
	font-family: Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

#customers td, #customers th {
	border: 1px solid #ddd;
	padding: 8px;
}

#customers tr:nth-child(even) {
	background-color: #f2f2f2;
}

#customers tr:hover {
	background-color: #ddd;
}

#customers th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: left;
	background-color: #2a8ffa;
	color: white;
}
</style>
</head>
<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->

		<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">

			<!-- Sidebar - Brand -->
			<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
				rel="stylesheet">
			<a
				class="sidebar-brand d-flex align-items-center justify-content-center"
				href="KariahController?action=homepage&userid=<%= sessionId %>&email=<%= sesEmail %>"> <i class="small material-icons">account_circle</i>
				<div class="sidebar-brand-text mx-3">KARIAH & DEATH
					BENEFICIARY</div>
			</a>

			<!-- Divider -->
			<hr class="sidebar-divider my-0">

			<!-- Heading -->
			<div class="sidebar-heading">Menu</div>

			<!-- Nav Item - Dashboard -->
			<li class="nav-item"><a class="nav-link"
				href="KariahController?action=homepage&userid=<%= sessionId %>&email=<%= sesEmail %>"> <i
					class="fas fa-fw fa-tachometer-alt"></i> <span>User Dashboard</span></a> <!-- Nav Item - Pages Collapse Menu -->
			<li class="nav-item active">
			<a class="nav-link" href="#" data-toggle="collapse" data-target="#collapsePages"aria-expanded="true" aria-controls="collapsePages"> 
			<i class="fas fa-fw fa-folder"></i> <span>Pages</span>
			</a>
				<div id="collapsePages" class="collapse show" aria-labelledby="headingPages" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
					<a class="collapse-item" href="PaymentController?action=listPayment&userid=<%= sessionId %>">Payment List</a>
					<a class="collapse-item" href="DependentController?action=listDependent&userid=<%= sessionId %>">Dependent List</a>
					</div>
				</div></li>
		</ul>
		<!-- End of Sidebar -->
		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<nav
					class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

					<!-- Sidebar Toggle (Topbar) -->
					<button id="sidebarToggleTop"
						class="btn btn-link d-md-none rounded-circle mr-3">
						<i class="fa fa-bars"></i>
					</button>

					<!-- Topbar Navbar -->
					<ul class="navbar-nav ml-auto">

						<div class="topbar-divider d-none d-sm-block"></div>
						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow"><a
							class="nav-link dropdown-toggle" href="#" id="userDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <span
								class="mr-2 d-none d-lg-inline text-gray-600 small"><c:out value="${user.name}" /></span> <img class="img-profile rounded-circle"
								src="img/undraw_profile.svg">
						</a> <!-- Dropdown - User Information -->
							<div
								class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="userDropdown">
								<a class="dropdown-item" href="#" data-toggle="modal"
									data-target="#logoutModal"> <i
									class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
									Logout
								</a>
							</div></li>

					</ul>
				</nav>
				<!-- End of Topbar -->
					<div class="container">
<br>
<fieldset>
<legend><h1 align="center">DEPENDENT LIST</h1></legend>
<br><br>
<a href="addDependent.jsp" class="btn btn-success" style="float:right">ADD DEPENDENT</a><br><br>
<table style="width:100%">
  <tr>
    <th>Dependent Id</th>
    <th>Dependent Name</th>
    <th>Ic No</th>
    <th>Gender</th> 
    <th>Phone No</th> 
    <th>Relation</th> 
    <th colspan="3">Actions</th>
  </tr>
 <c:forEach items="${dependents}" var="dependent" varStatus="dependents">
  <tr>
    <td><c:out value="${dependent.depid}" /></td>
    <td><c:out value="${dependent.depName}" /></td>
    <td><c:out value="${dependent.depIcNo}" /></td>       
    <td><c:out value="${dependent.depGender}" /></td>
    <td><c:out value="${dependent.depPhoneNo}" /></td>
    <td><c:out value="${dependent.relation}" /></td>
    <td><a href="DependentController?action=viewDependent&depid=<c:out value="${dependent.depid}" />&userid=<c:out value="${dependent.userid}" />" class="btn btn-warning">View</a></td>
    <td><a href="DependentController?action=updateDependent&depid=<c:out value="${dependent.depid}" />" class="btn btn-primary">Update</a></td>    
    <td><input type="hidden" id="depid-${dependents.index}" value="<c:out value="${dependent.depid}"/>"><button class="btn btn-danger" onclick="confirmation('${dependents.index}')">Delete</button></td>    
  </c:forEach>
</table>
</fieldset>
</div>
					<br>
				</div>
					<!-- Logout Modal-->
					<div class="modal fade" id="logoutModal" tabindex="-1"
						role="dialog" aria-labelledby="exampleModalLabel"
						aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel">Ready to
										Leave?</h5>
									<button class="close" type="button" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">X</span>
									</button>
								</div>
								<div class="modal-body">Select "Logout" below if you are
									ready to end your current session.</div>
								<div class="modal-footer">
									<button class="btn btn-secondary" type="button"
										data-dismiss="modal">Cancel</button>
									<a class="btn btn-primary" href="LogoutController">Logout</a>
								</div>
							</div>
						</div>
					</div>
	<script>
		function confirmation(index){
			  var depid = $("#depid-" + index).val();
			 
			  console.log(depid);
			  var r = confirm("Are you sure you want to delete?");
			  if (r == true) {				 		  
				  location.href = 'DependentController?action=deleteDependent&depid=' + depid;
				  alert("Dependent successfully deleted");			
			  } else {				  
			      return false;	
			  }
		}
	</script>
				<!-- Bootstrap core JavaScript-->
				<script src="vendor/jquery/jquery.min.js"></script>
				<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

				<!-- Core plugin JavaScript-->
				<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

				<!-- Custom scripts for all pages-->
				<script src="js/sb-admin-2.min.js"></script>
			</body>
</html>
