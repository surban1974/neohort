neohort
=======

Java PDF&amp;XLS runtime builder-based OpenPdf &amp; iText &amp; JExcelAPI &amp; Apache POI library.As input use xml-based source with integrated WebJava environment objects (beans e.c.)Include dynamical tag's structures: cycle,condition,e.c. Compatibility with iText 5.*. http://demo-neohort.7e14.starter-us-west-2.openshiftapps.com

<br>
Maven:<br>
&lt;repositories&gt;<br>
&#9;	&lt;repository&gt;<br>
&#9;&#9;	&lt;id&gt;neohort-mvn-repo&lt;/id&gt;<br>
&#9;&#9;	&lt;url&gt;https://github.com/surban1974/neohort/raw/mvn-repo/&lt;/url&gt;<br>
&#9;&#9;	&lt;snapshots&gt;<br>
&#9;&#9;&#9;		&lt;enabled&gt;true&lt;/enabled&gt;<br>
&#9;&#9;&#9;		&lt;updatePolicy&gt;always&lt;/updatePolicy&gt;<br>
&#9;&#9;	&lt;/snapshots&gt;<br>
&#9;	&lt;/repository&gt;<br>
&lt;/repositories&gt;<br> 
<br>
&lt;dependencies&gt;<br> 
&lt;!-- PDF --&gt;<br>
&lt;!-- In case if you want to use iText 5.* support for PDF (AGPL) --&gt;<br>
&#9;	&lt;dependency&gt;<br>
&#9;&#9;	&lt;groupId&gt;com.github.surban1974.neohort&lt;/groupId&gt;<br>
&#9;&#9;	&lt;artifactId&gt;neohort-pdf-itext500&lt;/artifactId&gt;<br>
&#9;&#9;	&lt;version&gt;1.4.1&lt;/version&gt;<br>
&#9;	&lt;/dependency&gt;<br>
&lt;!-- In case if you want to use librepdf OpenPdf support for PDF --&gt;<br>
&#9;	&lt;dependency&gt;<br>
&#9;&#9;	&lt;groupId&gt;com.github.surban1974.neohort&lt;/groupId&gt;<br>
&#9;&#9;	&lt;artifactId&gt;neohort-pdf-openpdf&lt;/artifactId&gt;<br>
&#9;&#9;	&lt;version&gt;1.4.1&lt;/version&gt;<br>
&#9;	&lt;/dependency&gt;<br>
&lt;!-- In case if you want to use iText 2.1.7 support for PDF --&gt;<br>
&#9;	&lt;dependency&gt;<br>
&#9;&#9;	&lt;groupId&gt;com.github.surban1974.neohort&lt;/groupId&gt;<br>
&#9;&#9;	&lt;artifactId&gt;neohort-pdf-itext217&lt;/artifactId&gt;<br>
&#9;&#9;	&lt;version&gt;1.4.1&lt;/version&gt;<br>
&#9;	&lt;/dependency&gt;<br>
<br>
&lt;!-- EXCEL --&gt;<br>
&lt;!-- In case if you want to use JExcel API support for XLS --&gt;<br>	
&#9;	&lt;dependency&gt;<br>
&#9;&#9;	&lt;groupId&gt;com.github.surban1974.neohort&lt;/groupId&gt;<br>
&#9;&#9;	&lt;artifactId&gt;neohort-xls-jxl&lt;/artifactId&gt;<br>
&#9;&#9;	&lt;version&gt;1.4.1&lt;/version&gt;<br>
&#9;	&lt;/dependency&gt;<br>
&lt;!-- In case if you want to use Apache POI support for XLSX --&gt;<br>	
&#9;	&lt;dependency&gt;<br>
&#9;&#9;	&lt;groupId&gt;com.github.surban1974.neohort&lt;/groupId&gt;<br>
&#9;&#9;	&lt;artifactId&gt;neohort-xlsx-poi&lt;/artifactId&gt;<br>
&#9;&#9;	&lt;version&gt;1.4.1&lt;/version&gt;<br>
&#9;	&lt;/dependency&gt;<br>
<br>	
&lt;!-- RTF --&gt;<br>	
&lt;!-- In case if you want to use librepdf pdf.rtf support for RTF --&gt;<br>	
&#9;	&lt;dependency&gt;<br>
&#9;&#9;	&lt;groupId&gt;com.github.surban1974.neohort&lt;/groupId&gt;<br>
&#9;&#9;	&lt;artifactId&gt;neohort-rtf&lt;/artifactId&gt;<br>
&#9;&#9;	&lt;version&gt;1.4.1&lt;/version&gt;<br>
&#9;	&lt;/dependency&gt;<br>
&lt;!-- In case if you want to use iText 2.1.7 support for RTF --&gt;<br>
&#9;	&lt;dependency&gt;<br>
&#9;&#9;	&lt;groupId&gt;com.github.surban1974.neohort&lt;/groupId&gt;<br>
&#9;&#9;	&lt;artifactId&gt;neohort-rtf-itext217&lt;/artifactId&gt;<br>
&#9;&#9;	&lt;version&gt;1.4.1&lt;/version&gt;<br>
&#9;	&lt;/dependency&gt;<br>	
&lt;/dependencies&gt;   
