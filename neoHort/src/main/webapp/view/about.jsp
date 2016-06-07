<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>neoHort</title>
	<link rel="SHORTCUT ICON" href="../images/application.ico">



    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="../css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <style>
      body {
        padding-top: 20px;
        padding-bottom: 20px;
      }

      .navbar {
        margin-bottom: 20px;
      }
    </style>
    
    <!-- Styles for avoiding jQuery -->
    <link rel="stylesheet" href="../css/navbar.css">

  </head>

  <body>

    <div class="container" style="padding-top: 50px">

      <!-- Navbar -->
      <nav id="top_menu" class="navbar navbar-default navbar-fixed-top"></nav>
	  
     <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron" style="padding:5px;background-color: white;">
	  	

		<h2 style="100%;text-align: right;"><b>Java PDF, XLS, RTF runtime creator</b></h2>
		       	<ul>
		       		<li><h4><img src="../images/logo_small.gif" height="25px"/>&nbsp;<b>NeoHort</b> is WEB integrated service (based on servlet's architecture) compatible with:</h4>
		       			<ul>
		       				<li><h4>iText library (by Bruno Lowagie and Paulo Soares)</h4></li>
		       				<li><h4>JexcelApi (by andyk@andykhan.freeserve.co.uk )</h4></li>
		       			</ul>
		       		</li>	
		       		<li><h4><img src="../images/logo_small.gif" height="25px"/>&nbsp;<b>NeoHort</b> allows you:</h4>
		       			<ul>
		       				<li><h4>to design the complex and precise layout for PDF, XLS and RTF reports/documents</h4></li>
		       				<li><h4>to include dynamic tag's structures: cycle,condition,e.c. </h4></li>
		       				<li><h4>to bind and integrate your previously created java objects (bean)</h4></li>
		       				<li><h4>additional support:</h4></li>
		       				<ul>
		       				<li><h4>JSP rendering (filter architecture)</h4></li>
		       				<li><h4>vectorized Chart integration</h4></li>
		       				<li><h4>multilanguge support including arabic (rtl) and asian languages</h4></li>
		       				</ul>
		       			</ul>
		       		</li>	
		       		<li><h4>Providers:</h4>
		       		    <ul>
		       				<li><h4>neoHort: 1.3.7.3</h4></li>
		       				<li><h4>iText: 2.1.7</h4></li>
		       				<li><h4>iText-rtf: 2.1.7</h4></li>
		       				<li><h4>JExcelApi: 2.6.12</h4></li>
		       			</ul>
		       		</li>
		      		<li><h4>Author: <a href="http://it.linkedin.com/pub/svyatoslav-urbanovych/2/241/754">Svyatoslav Urbanovych</a></h4></li>
		       		<li><h4>email support: svyatoslav.urbanovych@gmail.com</h4></li>
		       		<li><h4>GNU General Public License version 2.0 (GPLv2)</h4></li>

		       	</ul>


      </div>

    </div> <!-- /container -->


    <!-- Navbar JavaScript -->
    <script src="../js/bootstrap-native.patched.js"></script>
	
	<!--  ClassHidra Ajax JavaScript -->
	<script src='../js/clAjax.js'></script>  	 
	
	
	<script>

	new clajax()
			.setUrl("top_menu.html")
			.setTarget(document.getElementById("top_menu"))
			.setSuccess(function(){ 
				document.getElementById("top_menu_about").className += " active";
			})
			.request("POST");
	
	</script>
  </body>
</html>

