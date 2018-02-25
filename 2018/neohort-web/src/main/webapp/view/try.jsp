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
  			<div class="panel-body"><h4>Write your xml template below and generate reports</h4>
		      	<ul class="nav nav-tabs" >
		  			<li role="template00" class="active">	<a href="#template00" id="template00-tab" role="template00" data-toggle="tab" aria-controls="template00" aria-expanded="true">User template</a></li>
		  			<li role="template01" >					<a href="#template01" id="template01-tab" role="template01" data-toggle="tab" aria-controls="template01" aria-expanded="true">DTD</a></li>
				    <li class="pull-right">
						<table style="width:100%" >
					 		<tr>
					 			<td style="width:200px;height: 25px;" align="center">
					  				<div class="btn-group" role="group" aria-label="...">      	
					  					<button type="button" class="btn btn-default"
					  						onclick="showPdf('../view/try_view.jsp?ReportProvider=neoHort&template='+encodeURIComponent(document.getElementById('template').value))"
					  					>PDF</button>					  					
									</div>
					 			</td>
					 		</tr>
						</table>
				    </li>
		  		</ul>	
		  		<div id="networkTabContent" class="tab-content" >
					
					<div role="template00" class="tab-pane fade active in" id="template00" aria-labelledby="template00-tab" >
		      			<textarea id="template" name="template" style="width:100%;height: 350px; border: none;"><jsp:include page="../chart/guide/try.xml"/></textarea>	
		      		</div>

					<div role="template01" class="tab-pane fade in" id="template01" aria-labelledby="template01-tab" >
		      			<pre ><xmp><jsp:include page="../chart/iReport.txt"/></xmp></pre>
		      		</div>

		      	</div>			      			      			
      		</div>
      	</div>	      		
      	</div>	
      

      	
 
       	
    </div> <!-- /container -->


    <!-- Navbar JavaScript -->
    <script src="../js/bootstrap-native.patched.js"></script>
	
	<!--  ClassHidra Ajax JavaScript -->
	<script src='../js/clAjax.js'></script>  	 

	<!--  Utils JavaScript -->
	<script src='../js/utils.js'></script> 	
	
	<script>  


	new clajax()
			.setUrl("top_menu.html")
			.setTarget(document.getElementById("top_menu"))
			.setSuccess(function(){ 
				document.getElementById("top_menu_try").className += " active";
			})
			.request("POST");
	
	</script>
  </body>
</html>

