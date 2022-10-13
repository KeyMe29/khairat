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
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
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
					class="fas fa-fw fa-tachometer-alt"></i> <span>Admin Dashboard</span></a> <!-- Nav Item - Pages Collapse Menu -->
			<li class="nav-item active">
			<a class="nav-link" href="#" data-toggle="collapse" data-target="#collapsePages"aria-expanded="true" aria-controls="collapsePages"> 
			<i class="fas fa-fw fa-folder"></i> <span>Pages</span>
			</a>
				<div id="collapsePages" class="collapse show" aria-labelledby="headingPages" data-parent="#accordionSidebar">
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
				<div class="container-fluid">
                    <div class="row">
                	<div class="col"><h1 class="h3 mb-2 text-gray-800">Payment Details</h1></div>
                	<!-- <div class="col text-right"><a href="#" class="btn btn-success btn-icon-split">
							<span class="icon text-white-50"> 
								<i class="fas fa-check"></i>
							</span> <span class="text">Split Button Success</span>
							</a></div>-->
                </div>
                    <div class="card shadow mb-4">
						<!-- Card Header - Accordion -->
						<a href="#collapseCard1" class="d-block card-header py-3" data-toggle="collapse" role="button" aria-expanded="true"
							aria-controls="collapseCard1">
							<h6 class="m-0 font-weight-bold text-primary">Pending Approval</h6>
						</a>
						<!-- Card Content - Collapse -->
						<div class="collapse show" id="collapseCard1">
							<div class="card-body">
								<form action="" >
								<div class="table-responsive">
									<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
										<thead>
											<tr>
												<th>USER ID</th>
												<th>METHOD</th>
												<th>PAYMENT DATE</th>
												<th>PAYMENT STATUS</th>
												<th colspan="3">ACTIONS</th>
											</tr>
										</thead>
											<c:forEach items="${payments}" var="payment"
												varStatus="payments">
												<c:if test="${fn:contains(payment.payStatus, 'G') }">
													<!-- display pending only -->
													<tr>
														<td><c:out value="${payment.userid}" /></td>
														<td><c:out value="${payment.method}" /></td>
														<td><c:out value="${payment.payDate}" /></td>
														<td><c:out value="${payment.payStatus}" /></td>
														<td><a
															href="PaymentController?action=viewPayment&userid=<c:out value="${payment.userid}" />&bid=<c:out value="${payment.bid}" />"
															class="btn btn-info btn-icon-split"> <span
																class="icon text-white-50"> <i
																	class="fas fa-info-circle"></i>
															</span> <span class="text">VIEW PAYMENT DETAILS</span>
														</a></td>
													</tr>
												</c:if>
											</c:forEach>
										</tbody>
									</table>
								</div>
								</form>
							</div>
						</div>
					</div>
					
					<div class="card shadow mb-4">
						<!-- Card Header - Accordion -->
						<a href="#collapseCard2" class="d-block card-header py-3" data-toggle="collapse" role="button" aria-expanded="true"
							aria-controls="collapseCard2">
							<h6 class="m-0 font-weight-bold text-primary">Approved Payment</h6>
							<!-- display approved only -->
						</a>
						<!-- Card Content - Collapse -->
						<div class="collapse show" id="collapseCard2">
							<div class="card-body">
								<div class="table-responsive">
									<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
										<thead>
											<tr>
												<th>USER ID</th>
												<th>METHOD</th>
												<th>PAYMENT DATE</th>
												<th>PAYMENT STATUS</th>
												<th colspan="3">ACTIONS</th>
											</tr>
										</thead>
											<c:forEach items="${payments}" var="payment"
												varStatus="payments">
												<c:if test="${fn:contains(payment.payStatus, 'A') }">
													<!-- display approved only -->
													<tr>
														<td><c:out value="${payment.userid}" /></td>
														<td><c:out value="${payment.method}" /></td>
														<td><c:out value="${payment.payDate}" /></td>
														<td><c:out value="${payment.payStatus}" /></td>
														<td><a
															href="PaymentController?action=viewPayment&userid=<c:out value="${payment.userid}" />&bid=<c:out value="${payment.bid}" />"
															class="btn btn-info btn-icon-split"> <span
																class="icon text-white-50"> <i
																	class="fas fa-info-circle"></i>
															</span> <span class="text">VIEW PAYMENT DETAILS</span>
														</a></td>
													</tr>
												</c:if>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
					<div class="card shadow mb-4">
						<!-- Card Header - Accordion -->
						<a href="#collapseCard3" class="d-block card-header py-3" data-toggle="collapse" role="button" aria-expanded="true"
							aria-controls="collapseCard3">
							<h6 class="m-0 font-weight-bold text-primary">Rejected Payment Proof</h6>
						</a>
						<!-- Card Content - Collapse -->
						<div class="collapse show" id="collapseCard3">
							<div class="card-body">
								<div class="table-responsive">
									<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
										<thead>
											<tr>
												<th>USER ID</th>
												<th>METHOD</th>
												<th>PAYMENT DATE</th>
												<th>PAYMENT STATUS</th>
												<th colspan="3">ACTIONS</th>
											</tr>
										</thead>
											<c:forEach items="${payments}" var="payment"
												varStatus="payments">
												<c:if test="${fn:contains(payment.payStatus, 'C') }">
													<!-- display approved only -->
													<tr>
														<td><c:out value="${payment.userid}" /></td>
														<td><c:out value="${payment.method}" /></td>
														<td><c:out value="${payment.payDate}" /></td>
														<td><c:out value="${payment.payStatus}" /></td>
														<td><a
															href="PaymentController?action=viewPayment&userid=<c:out value="${payment.userid}" />&bid=<c:out value="${payment.bid}" />"
															class="btn btn-info btn-icon-split"> <span
																class="icon text-white-50"> <i
																	class="fas fa-info-circle"></i>
															</span> <span class="text">VIEW PAYMENT DETAILS</span>
														</a></td>
													</tr>
												</c:if>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
                   </div>
                <!-- /.container-fluid -->
				<div class="container">

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
					</div>

					<!-- Bootstrap core JavaScript-->
					<script src="vendor/jquery/jquery.min.js"></script>
					<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

					<!-- Core plugin JavaScript-->
					<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

					<!-- Custom scripts for all pages-->
					<script src="js/sb-admin-2.min.js"></script></body>
</html>