/**
* Creation date: (14/12/2005)
* @author: Svyatoslav Urbanovych surban@bigmir.net  svyatoslav.urbanovych@gmail.com
*/

/********************************************************************************
*
*	Copyright (C) 2005  Svyatoslav Urbanovych
*
* This program is free software; you can redistribute it and/or
* modify it under the terms of the GNU General Public License
* as published by the Free Software Foundation; either version 2
* of the License, or (at your option) any later version.

* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.

* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
*********************************************************************************/

package neohort.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class util_xml {
	
public util_xml() {
	super();
}
public static Document readXML(String uriXML, boolean valid) throws Exception{
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	dbf.setValidating(valid);
	if(	uriXML.toUpperCase().trim().indexOf("HTTP:")==-1 &&
		uriXML.toUpperCase().trim().indexOf("HTTPS:")==-1 &&
		uriXML.toUpperCase().trim().indexOf("FTP:")==-1 &&
		uriXML.toUpperCase().trim().indexOf("FTPS:")==-1)
		return  dbf.newDocumentBuilder().parse(new File(uriXML));
	else return  DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(uriXML);
}
public static Document readXMLData(String dataXML, boolean valid) throws Exception{
	if(dataXML==null) return null;
	ByteArrayInputStream xmlSrcStream = new	ByteArrayInputStream(dataXML.getBytes());
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(valid);
	return dbf.newDocumentBuilder().parse(xmlSrcStream);
}

public static Document readXML(String uriXML) throws Exception{
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	dbf.setValidating(false);
	if(	uriXML.toUpperCase().trim().indexOf("HTTP:")==-1 &&
		uriXML.toUpperCase().trim().indexOf("HTTPS:")==-1 &&
		uriXML.toUpperCase().trim().indexOf("FTP:")==-1 &&
		uriXML.toUpperCase().trim().indexOf("FTPS:")==-1)
		return  dbf.newDocumentBuilder().parse(new File(uriXML));
	else return  DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(uriXML);
}
public static Document readXMLData(String dataXML) throws Exception{
	if(dataXML==null) return null;
	ByteArrayInputStream xmlSrcStream = new	ByteArrayInputStream(dataXML.getBytes());
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
	return  dbf.newDocumentBuilder().parse(xmlSrcStream);
}

public static Document readXMLData(InputStream is) throws Exception{
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
	return  dbf.newDocumentBuilder().parse(is);
}
}
