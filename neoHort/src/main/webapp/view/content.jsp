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
  			<div class="panel-body"><h4>Create document with simple text block</h4>
		      	<ul class="nav nav-tabs">
		  			<li role="template" class="active"><a href="#">Xml template</a></li>
				    <li class="pull-right">
						<table style="width:100%" >
					 		<tr>
					 			<td style="width:200px;height: 25px;" align="center">
					  				<div class="btn-group" role="group" aria-label="...">      	
					  					<button type="button" class="btn btn-default"
					  						onclick="showPdf('../report_creator?$source=/chart/guide/guide1.1.xml&$lib=pdf&$type=stream')"
					  					>PDF</button>
					  					<button type="button" class="btn btn-default" 
					  						onclick="location.replace('../report_creator?$source=/chart/guide/guide1.1.xml&$lib=xls&$type=attachment')"
					  					>XLS</button>
					  					<button type="button" class="btn btn-default"
					  						onclick="location.replace('../report_creator?$source=/chart/guide/guide1.1.xml&$lib=rtf&$type=attachment')"
					  					>RTF</button>
									</div>
					 			</td>
					 		</tr>
						</table>
				    </li>
		  		</ul>	
		      	<pre ><xmp><jsp:include page="../chart/guide/guide1.1.xml"/></xmp></pre>
      		</div>
      	</div>	
      	
      	<div class="panel panel-default" style="padding: 4px;">
  			<div class="panel-body"><h4>Set pagelayout as portrait</h4>
		      	<ul class="nav nav-tabs">
		  			<li role="template" class="active"><a href="#">Xml template</a></li>
				    <li class="pull-right">
						<table style="width:100%" >
					 		<tr>
					 			<td style="width:200px;height: 25px;" align="center">
					  				<div class="btn-group" role="group" aria-label="...">      	
					  					<button type="button" class="btn btn-default"
					  						onclick="showPdf('../report_creator?$source=/chart/guide/guide1.2.xml&$lib=pdf&$type=stream')"
					  					>PDF</button>
					  					<button type="button" class="btn btn-default" 
					  						onclick="location.replace('../report_creator?$source=/chart/guide/guide1.2.xml&$lib=xls&$type=attachment')"
					  					>XLS</button>
					  					<button type="button" class="btn btn-default"
					  						onclick="location.replace('../report_creator?$source=/chart/guide/guide1.2.xml&$lib=rtf&$type=attachment')"
					  					>RTF</button>
									</div>
					 			</td>
					 		</tr>
						</table>
				    </li>
		  		</ul>	
		      	<pre ><xmp><jsp:include page="../chart/guide/guide1.2.xml"/></xmp></pre>
      		</div>
      	</div>   
      	
     	<div class="panel panel-default" style="padding: 4px;">
  			<div class="panel-body"><h4>Change margins of document</h4>
		      	<ul class="nav nav-tabs">
		  			<li role="template" class="active"><a href="#">Xml template</a></li>
				    <li class="pull-right">
						<table style="width:100%" >
					 		<tr>
					 			<td style="width:200px;height: 25px;" align="center">
					  				<div class="btn-group" role="group" aria-label="...">      	
					  					<button type="button" class="btn btn-default"
					  						onclick="showPdf('../report_creator?$source=/chart/guide/guide1.4.xml&$lib=pdf&$type=stream')"
					  					>PDF</button>
					  					<button type="button" class="btn btn-default" 
					  						onclick="location.replace('../report_creator?$source=/chart/guide/guide1.4.xml&$lib=xls&$type=attachment')"
					  					>XLS</button>
					  					<button type="button" class="btn btn-default"
					  						onclick="location.replace('../report_creator?$source=/chart/guide/guide1.4.xml&$lib=rtf&$type=attachment')"
					  					>RTF</button>
									</div>
					 			</td>
					 		</tr>
						</table>
				    </li>
		  		</ul>	
		      	<pre ><xmp><jsp:include page="../chart/guide/guide1.4.xml"/></xmp></pre>
      		</div>
      	</div>    
      	
     	<div class="panel panel-default" style="padding: 4px;">
  			<div class="panel-body"><h4>Text formatting</h4>
		      	<ul class="nav nav-tabs">
		  			<li role="template" class="active"><a href="#">Xml template</a></li>
				    <li class="pull-right">
						<table style="width:100%" >
					 		<tr>
					 			<td style="width:200px;height: 25px;" align="center">
					  				<div class="btn-group" role="group" aria-label="...">      	
					  					<button type="button" class="btn btn-default"
					  						onclick="showPdf('../report_creator?$source=/chart/guide/guide1.5.xml&$lib=pdf&$type=stream')"
					  					>PDF</button>
					  					<button type="button" class="btn btn-default" 
					  						onclick="location.replace('../report_creator?$source=/chart/guide/guide1.5.xml&$lib=xls&$type=attachment')"
					  					>XLS</button>
					  					<button type="button" class="btn btn-default"
					  						onclick="location.replace('../report_creator?$source=/chart/guide/guide1.5.xml&$lib=rtf&$type=attachment')"
					  					>RTF</button>
									</div>
					 			</td>
					 		</tr>
						</table>
				    </li>
		  		</ul>	
		      	<pre ><xmp><jsp:include page="../chart/guide/guide1.5.xml"/></xmp></pre>
      		</div>
      	</div>       	     	   	      	         	   	

     	<div class="panel panel-default" style="padding: 4px;">
  			<div class="panel-body"><h4>Create header of document (# 1)</h4>
		      	<ul class="nav nav-tabs">
		  			<li role="template" class="active"><a href="#">Xml template</a></li>
				    <li class="pull-right">
						<table style="width:100%" >
					 		<tr>
					 			<td style="width:200px;height: 25px;" align="center">
					  				<div class="btn-group" role="group" aria-label="...">      	
					  					<button type="button" class="btn btn-default"
					  						onclick="showPdf('../report_creator?$source=/chart/guide/guide2.1.xml&$lib=pdf&$type=stream')"
					  					>PDF</button>
					  					<button type="button" class="btn btn-default" 
					  						onclick="location.replace('../report_creator?$source=/chart/guide/guide2.1.xml&$lib=xls&$type=attachment')"
					  					>XLS</button>
					  					<button type="button" class="btn btn-default"
					  						onclick="location.replace('../report_creator?$source=/chart/guide/guide2.1.xml&$lib=rtf&$type=attachment')"
					  					>RTF</button>
									</div>
					 			</td>
					 		</tr>
						</table>
				    </li>
		  		</ul>	
		      	<pre ><xmp><jsp:include page="../chart/guide/guide2.1.xml"/></xmp></pre>
      		</div>
      	</div> 
     
     	<div class="panel panel-default" style="padding: 4px;">
  			<div class="panel-body"><h4>Create footer of document (# 1)</h4>
		      	<ul class="nav nav-tabs">
		  			<li role="template" class="active"><a href="#">Xml template</a></li>
				    <li class="pull-right">
						<table style="width:100%" >
					 		<tr>
					 			<td style="width:200px;height: 25px;" align="center">
					  				<div class="btn-group" role="group" aria-label="...">      	
					  					<button type="button" class="btn btn-default"
					  						onclick="showPdf('../report_creator?$source=/chart/guide/guide2.2.xml&$lib=pdf&$type=stream')"
					  					>PDF</button>
					  					<button type="button" class="btn btn-default" 
					  						onclick="location.replace('../report_creator?$source=/chart/guide/guide2.2.xml&$lib=xls&$type=attachment')"
					  					>XLS</button>
					  					<button type="button" class="btn btn-default"
					  						onclick="location.replace('../report_creator?$source=/chart/guide/guide2.2.xml&$lib=rtf&$type=attachment')"
					  					>RTF</button>
									</div>
					 			</td>
					 		</tr>
						</table>
				    </li>
		  		</ul>	
		      	<pre ><xmp><jsp:include page="../chart/guide/guide2.2.xml"/></xmp></pre>
      		</div>
      	</div> 
   
     
     
	     <div class="panel panel-default" style="padding: 4px;">
	  		<div class="panel-body"><h4>Create header of document (# 2)</h4>
			      <ul class="nav nav-tabs">
		  			<li role="template" class="active"><a href="#">Xml template</a></li>
				    <li class="pull-right">
						<table style="width:100%" >
					 		<tr>
					 			<td style="width:200px;height: 25px;" align="center">
					  				<div class="btn-group" role="group" aria-label="...">      	
					  					<button type="button" class="btn btn-default"
					  						onclick="showPdf('../report_creator?$source=/chart/guide/guide2.3.xml&$lib=pdf&$type=stream')"
					  					>PDF</button>
					  					<button type="button" class="btn btn-default" 
					  						onclick="location.replace('../report_creator?$source=/chart/guide/guide2.3.xml&$lib=xls&$type=attachment')"
					  					>XLS</button>
					  					<button type="button" class="btn btn-default"
					  						onclick="location.replace('../report_creator?$source=/chart/guide/guide2.3.xml&$lib=rtf&$type=attachment')"
					  					>RTF</button>
									</div>
					 			</td>
					 		</tr>
						</table>
				    </li>
		  		</ul>	
		      	<pre ><xmp><jsp:include page="../chart/guide/guide2.3.xml"/></xmp></pre>
      		</div>
      	</div> 
 
      	<div class="panel panel-default" style="padding: 4px;">
  			<div class="panel-body"><h4>Create footer of document (# 2)</h4>
		      	<ul class="nav nav-tabs">
		  			<li role="template" class="active"><a href="#">Xml template</a></li>
				    <li class="pull-right">
						<table style="width:100%" >
					 		<tr>
					 			<td style="width:200px;height: 25px;" align="center">
					  				<div class="btn-group" role="group" aria-label="...">      	
					  					<button type="button" class="btn btn-default"
					  						onclick="showPdf('../report_creator?$source=/chart/guide/guide2.4.xml&$lib=pdf&$type=stream')"
					  					>PDF</button>
					  					<button type="button" class="btn btn-default" 
					  						onclick="location.replace('../report_creator?$source=/chart/guide/guide2.4.xml&$lib=xls&$type=attachment')"
					  					>XLS</button>
					  					<button type="button" class="btn btn-default"
					  						onclick="location.replace('../report_creator?$source=/chart/guide/guide2.4.xml&$lib=rtf&$type=attachment')"
					  					>RTF</button>
									</div>
					 			</td>
					 		</tr>
						</table>
				    </li>
		  		</ul>	
		      	<pre ><xmp><jsp:include page="../chart/guide/guide2.4.xml"/></xmp></pre>
      		</div>
      	</div> 
     
      	<div class="panel panel-default" style="padding: 4px;">
  			<div class="panel-body"><h4>Styles of document</h4>
		      	<ul class="nav nav-tabs">
		  			<li role="template" class="active"><a href="#">Xml template</a></li>
				    <li class="pull-right">
						<table style="width:100%" >
					 		<tr>
					 			<td style="width:200px;height: 25px;" align="center">
					  				<div class="btn-group" role="group" aria-label="...">      	
					  					<button type="button" class="btn btn-default"
					  						onclick="showPdf('../report_creator?$source=/chart/guide/guide3.1.xml&$lib=pdf&$type=stream')"
					  					>PDF</button>
					  					<button type="button" class="btn btn-default" 
					  						onclick="location.replace('../report_creator?$source=/chart/guide/guide3.1.xml&$lib=xls&$type=attachment')"
					  					>XLS</button>
					  					<button type="button" class="btn btn-default"
					  						onclick="location.replace('../report_creator?$source=/chart/guide/guide3.1.xml&$lib=rtf&$type=attachment')"
					  					>RTF</button>
									</div>
					 			</td>
					 		</tr>
						</table>
				    </li>
		  		</ul>	
		      	<pre ><xmp><jsp:include page="../chart/guide/guide3.1.xml"/></xmp></pre>
      		</div>
      	</div> 
      	
      	<div class="panel panel-default" style="padding: 4px;">
  			<div class="panel-body"><h4>Barcodes</h4>
		      	<ul class="nav nav-tabs">
		  			<li role="template" class="active"><a href="#">Xml template</a></li>
				    <li class="pull-right">
						<table style="width:100%" >
					 		<tr>
					 			<td style="width:200px;height: 25px;" align="center">
					  				<div class="btn-group" role="group" aria-label="...">      	
					  					<button type="button" class="btn btn-default"
					  						onclick="showPdf('../report_creator?$source=/chart/guide/guide3.2.xml&$lib=pdf&$type=stream')"
					  					>PDF</button>
					  					<button type="button" class="btn btn-default" 
					  						onclick="location.replace('../report_creator?$source=/chart/guide/guide3.2.xml&$lib=xls&$type=attachment')"
					  					>XLS</button>
					  					<button type="button" class="btn btn-default"
					  						onclick="location.replace('../report_creator?$source=/chart/guide/guide3.2.xml&$lib=rtf&$type=attachment')"
					  					>RTF</button>
									</div>
					 			</td>
					 		</tr>
						</table>
				    </li>
		  		</ul>	
		      	<pre ><xmp><jsp:include page="../chart/guide/guide3.2.xml"/></xmp></pre>
      		</div>
      	</div>       	
 
      	<div class="panel panel-default" style="padding: 4px;">
  			<div class="panel-body"><h4>Simple table</h4>
		      	<ul class="nav nav-tabs">
		  			<li role="template" class="active"><a href="#">Xml template</a></li>
				    <li class="pull-right">
						<table style="width:100%" >
					 		<tr>
					 			<td style="width:200px;height: 25px;" align="center">
					  				<div class="btn-group" role="group" aria-label="...">      	
					  					<button type="button" class="btn btn-default"
					  						onclick="showPdf('../report_creator?$source=/chart/guide/guide4.1.xml&$lib=pdf&$type=stream')"
					  					>PDF</button>
					  					<button type="button" class="btn btn-default" 
					  						onclick="location.replace('../report_creator?$source=/chart/guide/guide4.1.xml&$lib=xls&$type=attachment')"
					  					>XLS</button>
					  					<button type="button" class="btn btn-default"
					  						onclick="location.replace('../report_creator?$source=/chart/guide/guide4.1.xml&$lib=rtf&$type=attachment')"
					  					>RTF</button>
									</div>
					 			</td>
					 		</tr>
						</table>
				    </li>
		  		</ul>	
		      	<pre ><xmp><jsp:include page="../chart/guide/guide4.1.xml"/></xmp></pre>
      		</div>
      	</div>  
      	
       	<div class="panel panel-default" style="padding: 4px;">
  			<div class="panel-body"><h4>Simple table with dynamical count of row</h4>
		      	<ul class="nav nav-tabs">
		  			<li role="template" class="active"><a href="#">Xml template</a></li>
				    <li class="pull-right">
						<table style="width:100%" >
					 		<tr>
					 			<td style="width:200px;height: 25px;" align="center">
					  				<div class="btn-group" role="group" aria-label="...">      	
					  					<button type="button" class="btn btn-default"
					  						onclick="showPdf('../report_creator?$source=/chart/guide/guide4.2.xml&$lib=pdf&$type=stream')"
					  					>PDF</button>
					  					<button type="button" class="btn btn-default" 
					  						onclick="location.replace('../report_creator?$source=/chart/guide/guide4.2.xml&$lib=xls&$type=attachment')"
					  					>XLS</button>
					  					<button type="button" class="btn btn-default"
					  						onclick="location.replace('../report_creator?$source=/chart/guide/guide4.2.xml&$lib=rtf&$type=attachment')"
					  					>RTF</button>
									</div>
					 			</td>
					 		</tr>
						</table>
				    </li>
		  		</ul>	
		      	<pre ><xmp><jsp:include page="../chart/guide/guide4.2.xml"/></xmp></pre>
      		</div>
      	</div>     	
 
       	<div class="panel panel-default" style="padding: 4px;">
  			<div class="panel-body"><h4>Simple table with conditions</h4>
		      	<ul class="nav nav-tabs">
		  			<li role="template" class="active"><a href="#">Xml template</a></li>
				    <li class="pull-right">
						<table style="width:100%" >
					 		<tr>
					 			<td style="width:200px;height: 25px;" align="center">
					  				<div class="btn-group" role="group" aria-label="...">      	
					  					<button type="button" class="btn btn-default"
					  						onclick="showPdf('../report_creator?$source=/chart/guide/guide4.3.xml&$lib=pdf&$type=stream')"
					  					>PDF</button>
					  					<button type="button" class="btn btn-default" 
					  						onclick="location.replace('../report_creator?$source=/chart/guide/guide4.3.xml&$lib=xls&$type=attachment')"
					  					>XLS</button>
					  					<button type="button" class="btn btn-default"
					  						onclick="location.replace('../report_creator?$source=/chart/guide/guide4.3.xml&$lib=rtf&$type=attachment')"
					  					>RTF</button>
									</div>
					 			</td>
					 		</tr>
						</table>
				    </li>
		  		</ul>	
		      	<pre ><xmp><jsp:include page="../chart/guide/guide4.3.xml"/></xmp></pre>
      		</div>
      	</div>   
      	
       	<div class="panel panel-default" style="padding: 4px;">
  			<div class="panel-body"><h4>Complex table with images</h4>
		      	<ul class="nav nav-tabs">
		  			<li role="template" class="active"><a href="#">Xml template</a></li>
				    <li class="pull-right">
						<table style="width:100%" >
					 		<tr>
					 			<td style="width:200px;height: 25px;" align="center">
					  				<div class="btn-group" role="group" aria-label="...">      	
					  					<button type="button" class="btn btn-default"
					  						onclick="showPdf('../report_creator?$source=/chart/guide/guide4.5.xml&$lib=pdf&$type=stream')"
					  					>PDF</button>
					  					<button type="button" class="btn btn-default" 
					  						onclick="location.replace('../report_creator?$source=/chart/guide/guide4.5.xml&$lib=xls&$type=attachment')"
					  					>XLS</button>
					  					<button type="button" class="btn btn-default"
					  						onclick="location.replace('../report_creator?$source=/chart/guide/guide4.5.xml&$lib=rtf&$type=attachment')"
					  					>RTF</button>
									</div>
					 			</td>
					 		</tr>
						</table>
				    </li>
		  		</ul>	
		      	<pre ><xmp><jsp:include page="../chart/guide/guide4.5.xml"/></xmp></pre>
      		</div>
      	</div>       	  	
 
       	<div class="panel panel-default" style="padding: 4px;">
  			<div class="panel-body"><h4>Complex document with included sub-templates</h4>
		      	<ul class="nav nav-tabs">
		  			<li role="template" class="active"><a href="#">Xml template</a></li>
				    <li class="pull-right">
						<table style="width:100%" >
					 		<tr>
					 			<td style="width:200px;height: 25px;" align="center">
					  				<div class="btn-group" role="group" aria-label="...">      	
					  					<button type="button" class="btn btn-default"
					  						onclick="showPdf('../report_creator?$source=/chart/guide/guide5.1.xml&$lib=pdf&$type=stream')"
					  					>PDF</button>
					  					<button type="button" class="btn btn-default" 
					  						onclick="location.replace('../report_creator?$source=/chart/guide/guide5.1.xml&$lib=xls&$type=attachment')"
					  					>XLS</button>
					  					<button type="button" class="btn btn-default"
					  						onclick="location.replace('../report_creator?$source=/chart/guide/guide5.1.xml&$lib=rtf&$type=attachment')"
					  					>RTF</button>
									</div>
					 			</td>
					 		</tr>
						</table>
				    </li>
		  		</ul>	
		      	<pre ><xmp><jsp:include page="../chart/guide/guide5.1.xml"/></xmp></pre>
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
				document.getElementById("top_menu_steps").className += " active";
			})
			.request("POST");
	
	</script>
  </body>
</html>

