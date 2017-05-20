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
  			<div class="panel-body"><h4>Download and install</h4>
		      	<ul class="nav nav-tabs" >
		  			<li role="template00" class="active">	<a href="#template00" id="template00-tab" role="template00" data-toggle="tab" aria-controls="template00" aria-expanded="true">Direct download</a></li>
		  			<li role="template01" >					<a href="#template01" id="template01-tab" role="template01" data-toggle="tab" aria-controls="template01" aria-expanded="true">Maven integration 1.3.10</a></li>
		  			<li role="template02" >					<a href="#template02" id="template02-tab" role="template02" data-toggle="tab" aria-controls="template02" aria-expanded="true">Maven integration 5.2.8</a></li>
		  		</ul>	
		  		<div id="networkTabContent" class="tab-content" >
					
					<div role="template00" class="tab-pane fade active in" id="template00" aria-labelledby="template00-tab" >
						<ul>
			       			<li>
								<h4>The latest version of NEOHORT library is available for download from:</h4>
				       			<ul>
									<li><h4><a href="https://sourceforge.net/projects/neohort/files/neoHort1.3/neoHort.1.3.10/">SorceForge repository (support iText 2.1.7)</a></h4></li>
									<li><h4><a href="https://sourceforge.net/projects/neohort5/files/neoHort5/5.2.8/">SorceForge repository (support iText 5.2.5)</a></h4></li>
				       			</ul>
				       		</li>
				       		<li>	
				       			<h4>In the case if you prefer to use the MPL version of iText 2.1.7:</h4>
				       			<ul>
				       				<li><h4><a href="http://central.maven.org/maven2/com/lowagie/itext/2.1.7/itext-2.1.7.jar">iText 2.1.7</a></h4></li>
				       				<li><h4><a href="http://central.maven.org/maven2/com/lowagie/itext-rtf/2.1.7/itext-rtf-2.1.7.jar">iText RTF 2.1.7</a></h4></li>
				       				<li><h4><a href="http://www.java2s.com/Code/JarDownload/itext/itext-asian.jar.zip">iText Asian 2.1.7</a></h4></li>				       			
				       			</ul>
							</li>
				       		<li>	
				       			<h4>In the case if you prefer to use the Affero GNU Public License version of iText 5.5.*:</h4>
				       			<ul>
				       				<li><h4><a href="https://sourceforge.net/projects/itext/files/5.5.9/">iText 5.5.*</a></h4></li>
				       			</ul>
							</li>	
							<li><h4>Excel provided library <a href="https://poi.apache.org/download.html">Apache POI 3.16</a></h4></li>						
							<li><h4>The source code of NEOHORT can be found into the <a href="https://github.com/surban1974/neohort"> GitHub repository </a></h4></li>
						</ul>		       					      			
		      			
		      			
		      		</div>

					<div role="template01" class="tab-pane fade in" id="template01" aria-labelledby="template01-tab" >
		      			<pre ><xmp><repositories>
	<repository>
		<id>neohort-mvn-repo</id> 
		<url>https://github.com/surban1974/neohort/raw/mvn-repo/</url>
		<snapshots>
			<enabled>true</enabled>
			<updatePolicy>always</updatePolicy>
		</snapshots>
	</repository>
</repositories>

<dependency>
	<groupId>com.github.surban1974.neohort</groupId>
	<artifactId>neoHort</artifactId>
	<version>1.3.10</version>
</dependency></xmp></pre>
		      		</div>

					<div role="template02" class="tab-pane fade in" id="template02" aria-labelledby="template02-tab" >
		      			<pre ><xmp><repositories>
	<repository>
		<id>neohort-mvn-repo</id> 
		<url>https://github.com/surban1974/neohort/raw/mvn-repo/</url>
		<snapshots>
			<enabled>true</enabled>
			<updatePolicy>always</updatePolicy>
		</snapshots>
	</repository>
</repositories>

<dependency>
	<groupId>com.github.surban1974.neohort</groupId>
	<artifactId>neoHort5</artifactId>
	<version>5.2.8</version>
</dependency></xmp></pre>
		      		</div>
		      	</div>			      			      			
      		</div>
      	</div>	
      	
 
       
      	<div class="panel panel-default" style="padding: 4px;">
  			<div class="panel-body">
  				<h4>Integrate in your project</h4>
		      	<ul class="nav nav-tabs" >
		  			<li role="template10" class="active">	<a href="#template10" id="template10-tab" role="template10" data-toggle="tab" aria-controls="template10" aria-expanded="true">Register in web.xml</a></li>
		  			<li role="template11" >					<a href="#template11" id="template11-tab" role="template11" data-toggle="tab" aria-controls="template11" aria-expanded="true">Extend servlet with annotation</a></li>
		  			<li role="template12" >					<a href="#template12" id="template12-tab" role="template12" data-toggle="tab" aria-controls="template12" aria-expanded="true">Extend filter with annotation</a></li>
		  		</ul>	
		  		<div id="networkTabContent" class="tab-content" >
					
					<div role="template10" class="tab-pane fade active in" id="template10" aria-labelledby="template10-tab" >
						<pre ><xmp><!--  Filter mapping in case of rendered jsp -->
<filter>
	<filter-name>filter_jsp</filter-name>
	<display-name>filter_jsp</display-name>
	<description></description>
	<filter-class>neohort.service.filter.filter_jsp</filter-class>
</filter>
<filter-mapping>
	<filter-name>filter_jsp</filter-name>
	<url-pattern>*.jsp</url-pattern>
</filter-mapping>
<filter-mapping>
	<filter-name>filter_jsp</filter-name>
	<url-pattern>/report_creator</url-pattern>
</filter-mapping>

<!-- Servlet mapping -->
<servlet>
	<servlet-name>creator_iHort</servlet-name>
	<display-name>creator_iHort</display-name>
	<servlet-class>neohort.universal.output.creator_iHort</servlet-class>
</servlet>
<servlet-mapping>
	<servlet-name>creator_iHort</servlet-name>
	<url-pattern>/report_creator</url-pattern>
</servlet-mapping>
						</xmp></pre>
		      		</div>

					<div role="template11" class="tab-pane fade in" id="template11" aria-labelledby="template11-tab" >
		      			<pre ><xmp>
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import neohort.universal.output.creator_iHort;

@WebServlet(
		name="creator_iHort",
		displayName="creator_iHort",
		urlPatterns = {"/report_creator"},
		loadOnStartup=1)

public class Wrapper extends creator_iHort{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		super.doGet(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		super.doPost(req, res);
	}
}		      			
		      			</xmp></pre>
		      		</div>

					<div role="template12" class="tab-pane fade in" id="template12" aria-labelledby="template12-tab" >
					
		      			<pre ><xmp>import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import neohort.service.filter.filter_jsp;

@WebFilter(
		filterName="filter_jsp",
		value={
				"*.jsp",
				"/report_creator"
		},		
		dispatcherTypes={
				DispatcherType.REQUEST,
				DispatcherType.FORWARD,
				DispatcherType.INCLUDE
		}
	)
public class JspRender extends filter_jsp{
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
			super.doFilter(req, resp, chain);
	}
}</xmp></pre>
		      		</div>
		      	</div>			      			      			
      		</div>
      	</div>
      	
      	<div class="panel panel-default" style="padding: 4px;">
  			<div class="panel-body">
  				<h4>Use</h4>
 		      	<ul class="nav nav-tabs" >
		  			<li role="template20" class="active">	<a href="#template20" id="template20-tab" role="template20" data-toggle="tab" aria-controls="template20" aria-expanded="true">Static template</a></li>
		  			<li role="template21" >					<a href="#template21" id="template21-tab" role="template21" data-toggle="tab" aria-controls="template21" aria-expanded="true">JSP rendering</a></li>
		  			<li role="template22" >					<a href="#template22" id="template22-tab" role="template22" data-toggle="tab" aria-controls="template22" aria-expanded="true">Integrate into java class</a></li>
		  		</ul>	
		  		<div id="networkTabContent" class="tab-content" >
					
					<div role="template20" class="tab-pane fade active in" id="template20" aria-labelledby="template20-tab" >
						<pre ><xmp>
Xml templates for the next report processing can be prepared and placed under /WebContent folder (or subfolder) of your project.
In this case if you want to tie your templates with j2ee environment - is essential to insert the specifics tags (ex. <BEAN ...>, <OBJECT_JAVA ...>)
and map java objects which yuo want to use for the repport processing.
PDF/XLS/RTF report will be processed via servlet url request where parameter $source must contain the relative path of your template.
The another parameters: $lib, $type must be set only if into tag <general ...> of your template is expected external values:
<general 
	id="guide.1.1"
	type_document="%EXECUTEBEAN.SYSTEM:Request.getParameter(java.lang.String:'$type')%" 
	orientation="landscape" 
	lib="%EXECUTEBEAN.SYSTEM:Request.getParameter(java.lang.String:'$lib')%"> 
	
Use:
../report_creator?$source=/chart/table1.xml&$lib=pdf&$type=stream</xmp></pre>
					</div>
					<div role="template21" class="tab-pane fade in" id="template21" aria-labelledby="template21-tab" >
						<pre ><xmp>
It's possible to use your jsp as xml template for the report processing.
In this case your can put your xml source into the jsp and nex use jsp adapted syntax (including any mechanism of data elaboration expected from jsp)
Example:
<GENERAL 
	ID="example.jsp.integrated"
	TYPE_DOCUMENT="<%=request.getParameter("$type")%>"
	ORIENTATION="PORTRAIT"
	LIB="<%=request.getParameter("$lib")%>"
	>
PDF/XLS/RTF report will be processed via request of jsp with specific parameters:
 	ReportProvider=neoHort
The another parameters: $lib, $type must be set only if into tag <general ...> of your template is expected external values:

Use:
/chart/jsp/log_view.jsp?ReportProvider=neoHort&$lib=xls&$type=attachment							
						</xmp></pre>
					</div>										
 					<div role="template22" class="tab-pane fade in" id="template22" aria-labelledby="template22-tab" >
						<pre ><xmp>
import neohort.universal.output.iHortService;
import neohort.universal.output.lib.bean;
...

String xmlTemplate = "<xml ...."; 

ByteArrayOutputStream out=new ByteArrayOutputStream(); 
			
Hashtable _beanLibrary = new Hashtable();
bean _external_keywords = new bean();
_external_keywords.setContent("...");
_beanLibrary.put("BEAN:external_keywords",_external_keywords);	

iHortService ihs = new iHortService(_beanLibrary);
ihs.transformXMLtoReport(xmlTemplate,out);
	
out.close();
						
						</xmp></pre>
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
				document.getElementById("top_menu_use").className += " active";
			})
			.request("POST");
	
	</script>
  </body>
</html>

