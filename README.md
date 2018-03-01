neohort
=======

Java PDF&amp;XLS runtime builder-based iText &amp; JExcelAPI &amp; Apache POI library.As input use xml-based source with integrated WebJava environment objects (beans e.c.)Include dynamical tag's structures: cycle,condition,e.c. Compatibility with iText 5.0.*. http://demo-neohort.7e14.starter-us-west-2.openshiftapps.com
<br>
Maven:<br>
&lt;repositories&gt;<br>
	&lt;repository&gt;<br>
		&lt;id&gt;<br>neohort-mvn-repo&lt;/id&gt;<br>
		&lt;url&gt;<br>https://github.com/surban1974/neohort/raw/mvn-repo/&lt;/url&gt;<br>
		&lt;snapshots&gt;<br>
			&lt;enabled&gt;<br>true&lt;/enabled&gt;<br>
			&lt;updatePolicy&gt;<br>always&lt;/updatePolicy&gt;<br>
		&lt;/snapshots&gt;<br>
	&lt;/repository&gt;<br>
&lt;/repositories&gt;<br> 

&lt;dependencies&gt;<br> 
&lt;!-- PDF --&gt;<br>
&lt;!-- In case if you want to use iText 5.* support for PDF (AGPL) --&gt;<br>
	&lt;dependency&gt;<br>
		&lt;groupId&gt;<br>com.github.surban1974.neohort&lt;/groupId&gt;<br>
		&lt;artifactId&gt;<br>neohort-pdf-itext500&lt;/artifactId&gt;<br>
		&lt;version&gt;<br>1.4.1&lt;/version&gt;<br>
	&lt;/dependency&gt;<br>
&lt;!-- In case if you want to use librepdf OpenPdf support for PDF --&gt;<br>
	&lt;dependency&gt;<br>
		&lt;groupId&gt;<br>com.github.surban1974.neohort&lt;/groupId&gt;<br>
		&lt;artifactId&gt;<br>neohort-pdf-openpdf&lt;/artifactId&gt;<br>
		&lt;version&gt;<br>1.4.1&lt;/version&gt;<br>
	&lt;/dependency&gt;<br>
&lt;!-- In case if you want to use iText 2.1.7 support for PDF --&gt;<br>
	&lt;dependency&gt;<br>
		&lt;groupId&gt;<br>com.github.surban1974.neohort&lt;/groupId&gt;<br>
		&lt;artifactId&gt;<br>neohort-pdf-itext217&lt;/artifactId&gt;<br>
		&lt;version&gt;<br>1.4.1&lt;/version&gt;<br>
	&lt;/dependency&gt;<br>

&lt;!-- EXCEL --&gt;<br>
&lt;!-- In case if you want to use JExcel API support for XLS --&gt;<br>	
	&lt;dependency&gt;<br>
		&lt;groupId&gt;<br>com.github.surban1974.neohort&lt;/groupId&gt;<br>
		&lt;artifactId&gt;<br>neohort-xls-jxl&lt;/artifactId&gt;<br>
		&lt;version&gt;<br>1.4.1&lt;/version&gt;<br>
	&lt;/dependency&gt;<br>
&lt;!-- In case if you want to use Apache POI support for XLSX --&gt;<br>	
	&lt;dependency&gt;<br>
		&lt;groupId&gt;<br>com.github.surban1974.neohort&lt;/groupId&gt;<br>
		&lt;artifactId&gt;<br>neohort-xlsx-poi&lt;/artifactId&gt;<br>
		&lt;version&gt;<br>1.4.1&lt;/version&gt;<br>
	&lt;/dependency&gt;<br>
	
&lt;!-- RTF --&gt;<br>	
&lt;!-- In case if you want to use librepdf pdf.rtf support for RTF --&gt;<br>	
	&lt;dependency&gt;<br>
		&lt;groupId&gt;<br>com.github.surban1974.neohort&lt;/groupId&gt;<br>
		&lt;artifactId&gt;<br>neohort-rtf&lt;/artifactId&gt;<br>
		&lt;version&gt;<br>1.4.1&lt;/version&gt;<br>
	&lt;/dependency&gt;<br>
&lt;!-- In case if you want to use iText 2.1.7 support for RTF --&gt;<br>
	&lt;dependency&gt;<br>
		&lt;groupId&gt;<br>com.github.surban1974.neohort&lt;/groupId&gt;<br>
		&lt;artifactId&gt;<br>neohort-rtf-itext217&lt;/artifactId&gt;<br>
		&lt;version&gt;<br>1.4.1&lt;/version&gt;<br>
	&lt;/dependency&gt;<br>	
&lt;/dependencies&gt;
