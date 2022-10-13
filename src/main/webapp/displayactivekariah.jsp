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
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>KARIAH and DEATH BENEFICIARY SYSTEM</title>
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
<script>
window.onload = function () {
    var request = new XMLHttpRequest();
    request.open("GET", "https://floating-hamlet-51262.herokuapp.com/displayactivekariah", true);
    request.onload = function () {
      var members_obj = JSON.parse(this.response);

      var table = document.createElement('table');

      for (var i = 0; i < members_obj.length; i++) {
        var row = table.insertRow(i);
        if (i === 0) {
          row.style.fontWeight = 'bold';
          var cell0 = row.insertCell(0).innerHTML = 'User ID';
			var cell1 = row.insertCell(1).innerHTML = 'Username';
			var cell2 = row.insertCell(2).innerHTML = 'Email';
			var cell3 = row.insertCell(3).innerHTML = 'User Type';
			var cell4 = row.insertCell(4).innerHTML = 'IC No.';
			var cell5 = row.insertCell(5).innerHTML = 'Date of Birth';
			var cell6 = row.insertCell(6).innerHTML = 'Phone No.';
			var cell7 = row.insertCell(7).innerHTML = 'Marital Status';
			var cell8 = row.insertCell(8).innerHTML = 'Gender';
			var cell9 = row.insertCell(9).innerHTML = 'Kariah of Mosque';

         
        }
        else {
          var cell0 = row.insertCell(0);
          var cell1 = row.insertCell(1);
          var cell2 = row.insertCell(2);
          var cell3 = row.insertCell(3);
          var cell4 = row.insertCell(4);
          var cell5 = row.insertCell(5);
          var cell6 = row.insertCell(6);
          var cell7 = row.insertCell(7);
          var cell8 = row.insertCell(8);
          var cell9 = row.insertCell(9);
          

          cell0.innerHTML = members_obj[i].userid;
			cell1.innerHTML = members_obj[i].name;
			cell2.innerHTML = members_obj[i].email;
			cell3.innerHTML = members_obj[i].user_type;
			cell4.innerHTML = members_obj[i].icNo;
			cell5.innerHTML = (members_obj[i].dob).slice(0,10);
			cell6.innerHTML = members_obj[i].phoneNo;
			cell7.innerHTML = members_obj[i].maritalStat;
			cell8.innerHTML = members_obj[i].gender;
			cell9.innerHTML = members_obj[i].mosqueName;
        }
      }
      document.getElementById('customers').appendChild(table);
    };
    request.send();
  }
</script>
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
<<<<<<< HEAD
				href="AdminController?action=admindashboard&userid=<%= sessionId %>&email=<%= sesEmail %>"> <i class="small material-icons">account_circle</i>
=======
				href="AdminController?action=admindashboard&userid=<%= sessionId %>"> <i class="small material-icons">account_circle</i>
>>>>>>> parent of 3fd23b3 (Initialized)
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
								class="mr-2 d-none d-lg-inline text-gray-600 small"></span>
								<img class="img-profile rounded-circle"
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
					<h1 align="center">ACTIVE KARIAH LIST</h1>
					<br>
					<br>

					<!-- display active kariah table -->
					<div id="customers"></div>
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