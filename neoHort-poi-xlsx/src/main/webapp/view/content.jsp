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
	      <div class="col-sm-9" data-spy="scroll" data-target="#nav-scrollspy">
	      
	      <div  style="padding: 4px;" id="examples">
					<h2>Examples</h2>
		  </div>				
	      
	      	<div class="panel panel-default" style="padding: 4px;" id="example.1.1">
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
						  						onclick="location.replace('../report_creator?$source=/chart/guide/guide1.1.xml&$lib=xlsx&$type=attachment')"
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
	      	
	      	<div class="panel panel-default" style="padding: 4px;" id="example.1.2">
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
						  						onclick="location.replace('../report_creator?$source=/chart/guide/guide1.2.xml&$lib=xlsx&$type=attachment')"
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
	      	
	     	<div class="panel panel-default" style="padding: 4px;" id="example.1.4">
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
						  						onclick="location.replace('../report_creator?$source=/chart/guide/guide1.4.xml&$lib=xlsx&$type=attachment')"
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
	      	
	     	<div class="panel panel-default" style="padding: 4px;" id="example.1.5">
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
						  						onclick="location.replace('../report_creator?$source=/chart/guide/guide1.5.xml&$lib=xlsx&$type=attachment')"
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
	
	     	<div class="panel panel-default" style="padding: 4px;" id="example.2.1">
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
						  						onclick="location.replace('../report_creator?$source=/chart/guide/guide2.1.xml&$lib=xlsx&$type=attachment')"
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
	     
	     	<div class="panel panel-default" style="padding: 4px;" id="example.2.2">
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
						  						onclick="location.replace('../report_creator?$source=/chart/guide/guide2.2.xml&$lib=xlsx&$type=attachment')"
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
	   
	     
	     
		     <div class="panel panel-default" style="padding: 4px;" id="example.2.3">
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
						  						onclick="location.replace('../report_creator?$source=/chart/guide/guide2.3.xml&$lib=xlsx&$type=attachment')"
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
	 
	      	<div class="panel panel-default" style="padding: 4px;" id="example.2.4">
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
						  						onclick="location.replace('../report_creator?$source=/chart/guide/guide2.4.xml&$lib=xlsx&$type=attachment')"
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
	     
	      	<div class="panel panel-default" style="padding: 4px;" id="example.3.1">
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
						  						onclick="location.replace('../report_creator?$source=/chart/guide/guide3.1.xml&$lib=xlsx&$type=attachment')"
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
	      	
	      	<div class="panel panel-default" style="padding: 4px;" id="example.3.2">
	  			<div class="panel-body"><h4>Barcodes</h4>
			      	<ul class="nav nav-tabs">
			  			<li role="template" class="active"><a href="#">Xml template</a></li>
					    <li class="pull-right">
							<table style="width:100%" >
						 		<tr>
						 			<td style="width:200px;height: 25px;" align="left">
						  				<div class="btn-group" role="group" aria-label="...">      	
						  					<button type="button" class="btn btn-default"
						  						onclick="showPdf('../report_creator?$source=/chart/guide/guide3.2.xml&$lib=pdf&$type=stream')"
						  					>PDF</button>
						  					
										</div>
						 			</td>
						 		</tr>
							</table>
					    </li>
			  		</ul>	
			      	<pre ><xmp><jsp:include page="../chart/guide/guide3.2.xml"/></xmp></pre>
	      		</div>
	      	</div>  
	      	
	      	<div class="panel panel-default" style="padding: 4px;" id="example.3.3">
	  			<div class="panel-body"><h4>Asian fonts</h4>
			      	<ul class="nav nav-tabs">
			  			<li role="template" class="active"><a href="#">Xml template</a></li>
					    <li class="pull-right">
							<table style="width:100%" >
						 		<tr>
						 			<td style="width:200px;height: 25px;" align="center">
						  				<div class="btn-group" role="group" aria-label="...">      	
						  					<button type="button" class="btn btn-default"
						  						onclick="showPdf('../report_creator?$source=/chart/guide/guide3.3.xml&$lib=pdf&$type=stream')"
						  					>PDF</button>
						  					<button type="button" class="btn btn-default" 
						  						onclick="location.replace('../report_creator?$source=/chart/guide/guide3.3.xml&$lib=xlsx&$type=attachment')"
						  					>XLS</button>
						  					<button type="button" class="btn btn-default"
						  						onclick="location.replace('../report_creator?$source=/chart/guide/guide3.3.xml&$lib=rtf&$type=attachment')"
						  					>RTF</button>
										</div>
						 			</td>
						 		</tr>
							</table>
					    </li>
			  		</ul>	
			      	<pre ><xmp><jsp:include page="../chart/guide/guide3.3.xml"/></xmp></pre>
	      		</div>
	      	</div>	      	     	
	 
	      	<div class="panel panel-default" style="padding: 4px;" id="example.4.1">
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
						  						onclick="location.replace('../report_creator?$source=/chart/guide/guide4.1.xml&$lib=xlsx&$type=attachment')"
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
	      	
	       	<div class="panel panel-default" style="padding: 4px;" id="example.4.2">
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
						  						onclick="location.replace('../report_creator?$source=/chart/guide/guide4.2.xml&$lib=xlsx&$type=attachment')"
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
	 
	       	<div class="panel panel-default" style="padding: 4px;" id="example.4.3">
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
						  						onclick="location.replace('../report_creator?$source=/chart/guide/guide4.3.xml&$lib=xlsx&$type=attachment')"
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
	      	
	       	<div class="panel panel-default" style="padding: 4px;" id="example.4.5">
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
						  						onclick="location.replace('../report_creator?$source=/chart/guide/guide4.5.xml&$lib=xlsx&$type=attachment')"
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
	 
	       	<div class="panel panel-default" style="padding: 4px;" id="example.5.1">
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
						  						onclick="location.replace('../report_creator?$source=/chart/guide/guide5.1.xml&$lib=xlsx&$type=attachment')"
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
	      
	    </div>
    
    
		<div id="side-nav" class="col-sm-3">
			<h3 class="visible-xs">Navigation</h3>
				<ul id="nav-scrollspy" class="nav nav-stacked" data-spy="affix" data-target="#install" data-offset-bottom="200">
					<li>
						<a href="#examples">Examples:</a>
						<ul class="nav nav-stacked">
							<li><a href="#example.1.1">&nbsp;&nbsp;simple text block</a></li>
							<li><a href="#example.1.2">&nbsp;&nbsp;page layout</a></li>
							<li><a href="#example.1.4">&nbsp;&nbsp;margins of document</a></li>
							<li><a href="#example.1.5">&nbsp;&nbsp;text formatting</a></li>
							<li><a href="#example.2.1">&nbsp;&nbsp;header of document #1</a></li>
							<li><a href="#example.2.2">&nbsp;&nbsp;footer of document #1</a></li>
							<li><a href="#example.2.3">&nbsp;&nbsp;header of document #2</a></li>
							<li><a href="#example.2.4">&nbsp;&nbsp;footer of document #2</a></li>
							<li><a href="#example.3.1">&nbsp;&nbsp;styles</a></li>
							<li><a href="#example.3.2">&nbsp;&nbsp;bar-codes</a></li>
							<li><a href="#example.3.3">&nbsp;&nbsp;asian fonts</a></li>
							<li><a href="#example.4.1">&nbsp;&nbsp;simple table</a></li>
							<li><a href="#example.4.5">&nbsp;&nbsp;complex table</a></li>
							<li><a href="#example.5.1">&nbsp;&nbsp;complex document</a></li>
	
						</ul>
					</li>
				</ul>
		</div>
      	
    </div> <!-- /container -->


    <!-- Navbar JavaScript -->
    <script src="../js/bootstrap-native.patched.js"></script>
	
	<!--  ClassHidra Ajax JavaScript -->
	<script src='../js/clAjax.js'></script>  	 

	<!--  Utils JavaScript -->
	<oldscript src='../js/utils.js'></oldscript> 	
	
	<script>  
	

	new clajax()
			.setUrl('top_menu.html')
			.setTarget(document.getElementById('top_menu'))
			.setSuccess(function(){ 
				document.getElementById('top_menu_steps').className += ' active';
				new clajax().load('../js/utils.js');
/*				
				new clajax()
					.setSuccess(function(){			
						new clajax().load('../css/bootstrap.min.css');
						new clajax().load('../js/utils.js'); 						
					
					})
					.load('../js/bootstrap-native.patched.js');	
*/				
			})
			.request("POST");
	
	</script>
  </body>
</html>

