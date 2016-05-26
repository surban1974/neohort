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
      
		.modal-wide .modal-dialog {
		  	width: 100%; 
		  	height: 92%;
		}
		.modal-dialog,
		.modal-content {
		    height: 92%;
		    width: 100%;
		}
		
		.modal-body {
		    height: 90%;
		}				
		     
      	pre {
      		height: 300px;
      		border: none;
      		background-color: white;
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
      
      	<div class="panel panel-default" style="padding: 4px;">
  			<div class="panel-body"><h4>Chart Exampe # 1</h4>
		      	<ul class="nav nav-tabs">
		  			<li role="template" class="active"><a href="#">Xml template</a></li>
				    <li class="pull-right">
						<table style="width:100%" >
					 		<tr>
					 			<td style="width:70px;height: 25px;" align="center">
					  				<div class="btn-group" role="group" aria-label="...">      	
					  					<button type="button" class="btn btn-default"
					  						onclick="showPdf('../report_creator?$source=/chart/chart0.xml&$lib=pdf&$type=stream')"
					  					>PDF</button>
					  					
									</div>
					 			</td>
					 		</tr>
						</table>
				    </li>
		  		</ul>	
		      	<pre ><xmp><jsp:include page="../chart/chart0.xml"/></xmp></pre>
      		</div>
      	</div>	
      	
       	<div class="panel panel-default" style="padding: 4px;">
  			<div class="panel-body"><h4>Chart Exampe # 2</h4>
		      	<ul class="nav nav-tabs">
		  			<li role="template" class="active"><a href="#">Xml template</a></li>
				    <li class="pull-right">
						<table style="width:100%" >
					 		<tr>
					 			<td style="width:70px;height: 25px;" align="center">
					  				<div class="btn-group" role="group" aria-label="...">      	
					  					<button type="button" class="btn btn-default"
					  						onclick="showPdf('../report_creator?$source=/chart/chart1.xml&$lib=pdf&$type=stream')"
					  					>PDF</button>
					  					
									</div>
					 			</td>
					 		</tr>
						</table>
				    </li>
		  		</ul>	
		      	<pre ><xmp><jsp:include page="../chart/chart1.xml"/></xmp></pre>
      		</div>
      	</div>	
      	      	
       	<div class="panel panel-default" style="padding: 4px;">
  			<div class="panel-body"><h4>Chart Exampe # 3</h4>
		      	<ul class="nav nav-tabs">
		  			<li role="template" class="active"><a href="#">Xml template</a></li>
				    <li class="pull-right">
						<table style="width:100%" >
					 		<tr>
					 			<td style="width:70px;height: 25px;" align="center">
					  				<div class="btn-group" role="group" aria-label="...">      	
					  					<button type="button" class="btn btn-default"
					  						onclick="showPdf('../report_creator?$source=/chart/chart2.xml&$lib=pdf&$type=stream')"
					  					>PDF</button>
					  					
									</div>
					 			</td>
					 		</tr>
						</table>
				    </li>
		  		</ul>	
		      	<pre ><xmp><jsp:include page="../chart/chart2.xml"/></xmp></pre>
      		</div>
      	</div>	
      	      	
       	<div class="panel panel-default" style="padding: 4px;">
  			<div class="panel-body"><h4>Chart Exampe # 4</h4>
		      	<ul class="nav nav-tabs">
		  			<li role="template" class="active"><a href="#">Xml template</a></li>
				    <li class="pull-right">
						<table style="width:100%" >
					 		<tr>
					 			<td style="width:70px;height: 25px;" align="center">
					  				<div class="btn-group" role="group" aria-label="...">      	
					  					<button type="button" class="btn btn-default"
					  						onclick="showPdf('../report_creator?$source=/chart/chart3.xml&$lib=pdf&$type=stream')"
					  					>PDF</button>
					  					
									</div>
					 			</td>
					 		</tr>
						</table>
				    </li>
		  		</ul>	
		      	<pre ><xmp><jsp:include page="../chart/chart3.xml"/></xmp></pre>
      		</div>
      	</div>	
      	
       	<div class="panel panel-default" style="padding: 4px;">
  			<div class="panel-body"><h4>Chart Exampe # 5</h4>
		      	<ul class="nav nav-tabs">
		  			<li role="template" class="active"><a href="#">Xml template</a></li>
				    <li class="pull-right">
						<table style="width:100%" >
					 		<tr>
					 			<td style="width:70px;height: 25px;" align="center">
					  				<div class="btn-group" role="group" aria-label="...">      	
					  					<button type="button" class="btn btn-default"
					  						onclick="showPdf('../report_creator?$source=/chart/chart4.xml&$lib=pdf&$type=stream')"
					  					>PDF</button>
					  					
									</div>
					 			</td>
					 		</tr>
						</table>
				    </li>
		  		</ul>	
		      	<pre ><xmp><jsp:include page="../chart/chart4.xml"/></xmp></pre>
      		</div>
      	</div>	     
 
      	
    </div> <!-- /container -->


    <!-- Navbar JavaScript -->
    <script src="../js/bootstrap-native.patched.js"></script>
	
	<!--  ClassHidra Ajax JavaScript -->
	<script src='../js/classhidra-ajax.js'></script>  	 

	<!--  Utils JavaScript -->
	<script src='../js/utils.js'></script> 	
	
	<script>  

	new clajax()
			.setUrl("top_menu.html")
			.setTarget(document.getElementById("top_menu"))
			.setSuccess(function(){ 
				document.getElementById("top_menu_charts").className += " active";
			})
			.request("POST");
	
	</script>
  </body>
</html>

