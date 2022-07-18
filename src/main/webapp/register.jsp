<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>MASJID AL-FIRDAUS KARIAH & DEATH BENEFICIARY SYSTEM</title>
    
        <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
</head>
<body class="bg-gradient-primary">

	<div class="container">

		<div class="card o-hidden border-0 shadow-lg my-5">
			<div class="card-body p-5">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-12">
						<div class="p-5">
							<div class="text-center">
								<h1 class="h4 text-gray-900 mb-4">Create Your Account</h1>
							</div>
							<form class="user" action="RegisterController" method="post">
							<input type="hidden" name="user_type" value="Kariah"> 
								<p> Full Name :
                                <div class="form-group row">
                                    <div class="col-sm-12 mb-3 mb-sm-0">
                                        <input type="text" class="form-control form-control-user" id="username"  placeholder="Enter Your Full Name" name="username" required>
                                    </div>
                                </div>
								<p>E-Mail :
								<div class="form-group">
									<input type="email" class="form-control form-control-user" id="email" placeholder="Enter Your Email Address" name="email" required>
								</div>
								<p>Password :
								<div class="form-group">
									<input type="password" class="form-control form-control-user" id="password" placeholder="Enter password" name="password" required>
								</div>
								<p> IC Number :
								<div class="form-group">
                                    <input type="text" class="form-control form-control-user" id="icNo"
                                        placeholder="Enter Your IC No." name="icNo" required>      
                                </div>
                                <p> Address :
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-user" id="address"
                                        placeholder="Enter Address" name="address" required>      
                                </div>
								<p> Phone Number :
                                <div class="form-group">
                                    <input type="tel" class="form-control form-control-user" id="phoneno"
                                        placeholder="Enter Your Phone Number" name="phoneno" required>      
                                </div>
                                <p>Marital Status :</p>
                                 <div class="form-check">
                                    <label class="form-check-label" for="maritalstat">
                                         <input type="radio" class="form-check-input" id="maritalstat" name="maritalstat" value="Single" required>Single
                                              </label>&nbsp &nbsp &nbsp &nbsp 
                                    <label class="form-check-label" for="maritalstat">
                                         <input type="radio" class="form-check-input" id="maritalstat" name="maritalstat" value="Married">Married
                                              </label>&nbsp &nbsp &nbsp &nbsp 
                                    <label class="form-check-label" for="maritalstat">
                                         <input type="radio" class="form-check-input" id="maritalstat" name="maritalstat" value="Divorced">Divorced
                                              </label>&nbsp &nbsp &nbsp &nbsp 
                                    <label class="form-check-label" for="maritalstat">
                                         <input type="radio" class="form-check-input" id="maritalstat" name="maritalstat" value="Widowed">Widowed
                                              </label>&nbsp &nbsp &nbsp &nbsp           
                                 </div><br>
                                <p>Gender</p>
                                 <div class="form-check">
                                    <label class="form-check-label" for="gender">
                                         <input type="radio" class="form-check-input" id="gender" name="gender" value="Male" required>Male
                                              </label>&nbsp &nbsp &nbsp &nbsp 
                                    <label class="form-check-label" for="gender">
                                         <input type="radio" class="form-check-input" id="gender" name="gender" value="Female">Female
                                              </label>
                                 </div><br>
								<label class="labels">Qariah of Mosque:</label><br>
								<select type="dropdown" name="mosqueId" id="mosqueId" class="form-check-input"
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
					</div>
					<input type="submit" name="submit" value="Register" class="btn btn-primary btn-user btn-block">
					<hr>
					</form>
					<hr>
					<div class="text-center">
						<a class="small" href="LoginController">Already have an account?
							Login</a>
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