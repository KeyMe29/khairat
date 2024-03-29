<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
response.addHeader("Pragma", "no-cache");
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.addHeader("Cache-Control", "pre-check=0, post-check=0");
response.setDateHeader("Expires", 0);

if (session.getAttribute("sessionEmail") == null)
	response.sendRedirect("/khairat/login.jsp");

if(session.getAttribute("sessionRole") != null){
	String sesRol = (String)session.getAttribute("sessionRole");
	if (sesRol.equalsIgnoreCase("kariah"))
		response.sendRedirect("/khairat/login.jsp");
}
int sessionId = (Integer)session.getAttribute("sessionId");
String sesEmail = (String)session.getAttribute("sessionEmail");
String sesName = (String)session.getAttribute("sessionName");
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
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
				href="AdminController?action=admindashboard&userid=<%= sessionId %>&email=<%= sesEmail %>"> <i class="small material-icons">account_circle</i>
				<div class="sidebar-brand-text mx-3">KARIAH & DEATH
					BENEFICIARY</div>
			</a>

			<!-- Divider -->
			<hr class="sidebar-divider my-0">

			<!-- Heading -->
			<div class="sidebar-heading">Menu</div>

			<!-- Nav Item - Dashboard -->
			<li class="nav-item"><a class="nav-link"
				href="AdminController?action=admindashboard&userid=<%= sessionId %>&email=<%= sesEmail %>"> <i
					class="fas fa-fw fa-tachometer-alt"></i> <span>Admin
						Dashboard</span></a> <!-- Nav Item - Pages Collapse Menu -->
			<li class="nav-item active"><a class="nav-link" href="#"
				data-toggle="collapse" data-target="#collapsePages"
				aria-expanded="true" aria-controls="collapsePages"> <i
					class="fas fa-fw fa-folder"></i> <span>Pages</span>
			</a>
				<div id="collapsePages" class="collapse show"
					aria-labelledby="headingPages" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<a class="collapse-item" href="BillController?action=listBill">Bill List</a> 
						<a class="collapse-item" href="PaymentController?action=listAllPayment">Payment List</a>
						<a class="collapse-item" href="MosqueController?action=listMosque">Mosque List</a>
						<a class="collapse-item" href="AdminRegisterController">Register Admin</a>
						<a class="collapse-item" href="ReportController">Report Analysis</a>
					</div>
				</div></li>

			<!-- Nav Item - Tables -->
			<li class="nav-item"><a class="nav-link" href="UserListController?action=listAllUser">
					<i class="fas fa-fw fa-table"></i> <span>User List</span>
			</a></li>
			<li class="nav-item"><a class="nav-link" href="UserListController?action=displayactivekariah">
					<i class="fas fa-fw fa-table"></i> <span>Active Kariah List</span>
			</a></li>
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
								class="mr-2 d-none d-lg-inline text-gray-600 small"><%=sesName %></span> <img class="img-profile rounded-circle"
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


				<!-- Custom styles for this template-->
				<link href="css/sb-admin-2.min.css" rel="stylesheet">
				<style>
::-webkit-scrollbar {
	width: 8px;
}
/* Track */
::-webkit-scrollbar-track {
	background: #f1f1f1;
}

/* Handle */
::-webkit-scrollbar-thumb {
	background: #888;
}

/* Handle on hover */
::-webkit-scrollbar-thumb:hover {
	background: #555;
}

body {
	background: rgb(99, 39, 120)
}

.back:hover {
	color: #682773;
	cursor: pointer
}

.add-experience:hover {
	background: #BA68C8;
	color: #fff;
	cursor: pointer;
	border: solid 1px #BA68C8
}
</style>
</head>


				<!-- Begin Page Content -->
				<!-- /.container-fluid -->
				<div class="container rounded bg-white">


					<div class="p-3 py-5">
						<div
							class="d-flex justify-content-between align-items-center mb-3">
							<h1 class="text-right">ADD MOSQUE</h1>
						</div>
						<form action="MosqueController" method="post">
							<div class="col-md-12">
								<label class="labels">MOSQUE NAME</label><input type="text"
									class="form-control" name="mosqueName" required>
							</div>
							<div class="col-md-12">
								<label class="labels">ADDRESS</label><input type="text"
									class="form-control" name="mosqueAddress" required>
							</div>
							<div class="col-md-12"><br>
								<label class="labels">SUPERVISOR MOSQUE:</label><br>
								<select type="dropdown" name="supervisorId" id="supervisorId"
									style="color: grey; border-radius: 8px; background-color: white; padding: 13px; border: 1px solid #ccc;">
									<option value="">SELECT MOSQUE</option>
									<c:forEach items="${mosques}" var="mosque">
										<option style="color: black"
											value="<c:out value='${mosque.mosqueId}'/>">
											<c:out value="${mosque.mosqueId}" />.
											<c:out value="${mosque.mosqueName}" />
										</option>
									</c:forEach>
								</select><br>
							</div>
							<br> 
							<div class="col-md-12">
								<input class="btn btn-primary" type="submit" value="ADD MOSQUE"">
							</div>
						</form>
						<!-- End Page Content -->
					</div>
					
									<!-- Logout Modal-->
				<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
					aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
								<button class="close" type="button" data-dismiss="modal" aria-label="Close">
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

		<!-- Bootstrap core JavaScript-->
		<script src="vendor/jquery/jquery.min.js"></script>
		<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

		<!-- Core plugin JavaScript-->
		<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

		<!-- Custom scripts for all pages-->
		<script src="js/sb-admin-2.min.js"></script>
</body>
</html>