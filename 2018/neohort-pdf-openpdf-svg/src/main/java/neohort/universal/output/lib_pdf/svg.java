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

package neohort.universal.output.lib_pdf;

import java.awt.Graphics2D;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Hashtable;

import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.GVTBuilder;
import org.apache.batik.bridge.UserAgentAdapter;
//import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.svg.SVGDocument;

import com.lowagie.text.Image;
import com.lowagie.text.ImgTemplate;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfGraphics2D;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.lib.report_element_base;
import neohort.universal.output.lib.style;
import neohort.util.util_reflect;
import neohort.util.util_web;

public class svg extends element{
	private static final long serialVersionUID = -1L;
	private String IMAGE_SOURCE;
	private String IMAGE_CREATOR;
	private String IMAGE_LOADER;
	private String IMAGE_ERROR;
	private String STR_ERROR;
	private String FIT;

public svg() {
	super();
}
public void drawCanvas(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary) {
	initCanvas(_tagLibrary,_beanLibrary);
}
public void executeFirst(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
}
public void executeLast(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
	try{

		PdfWriter pdfWriter = (PdfWriter)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent());


			Image chartIm = null;
			String pathImg = "";
			try{
				pathImg = util_web.adaptPath(getIMAGE_SOURCE(), _beanLibrary);
			}catch(Exception e){
				pathImg=null;
			}catch(Throwable e){
				pathImg=null;
			}
			if(pathImg==null) pathImg = getIMAGE_SOURCE();

			if(!IMAGE_CREATOR.equals("") || chartIm==null){
				Object loader = null;
				try{
					loader = Class.forName(IMAGE_CREATOR).newInstance();
					util_reflect.setValue(loader, "setProperty", new Object[]{IMAGE_SOURCE});
					try {
						util_reflect.setValue(loader, "setProperty", new Object[]{IMAGE_SOURCE, pathImg, internal_style});
					}catch(Exception e) {						
					}
					try {
						chartIm = (Image)util_reflect.getValue(loader,"getImage",new Object[] {(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent())});
					}catch(Exception e) {						
					}

				}catch(Exception e){
					chartIm=null;
					setError(e,iStub.log_ERROR);
				}
			}			
			

			if(chartIm==null){
				InputStream input = null;
				try{
					if(!IMAGE_LOADER.equals("")){
						Object loader = null;
						try{
							loader = Class.forName(IMAGE_LOADER).newInstance();
							util_reflect.setValue(loader, "setProperty", new Object[]{IMAGE_SOURCE});
							try {
								util_reflect.setValue(loader, "setProperty", new Object[]{IMAGE_SOURCE,pathImg,internal_style});
							}catch(Exception e) {						
							}
							byte[] imgBytes = (byte[])util_reflect.getValue(loader,"getBytes",null);
							input = new ByteArrayInputStream(imgBytes);

						}catch(Exception e){
							input = null;
							setError(e,iStub.log_ERROR);
						}
					}

					Exception ex=null;
					if(input==null){
						try{
							input = new FileInputStream(getIMAGE_SOURCE());
						}catch(Exception e){
							ex=e;
						}
					}
					if(input==null){
						try{
							input = getClass().getResource(getIMAGE_SOURCE()).openStream();
						}catch(Exception e){
							ex=e;
						}
					}
					if(input==null){
						try{
							input = new URL(pathImg).openStream();
						}catch(Exception e){
							ex=e;
						}
					}
					if(input==null && ex!=null)
						setError(ex,iStub.log_ERROR);
					else {
						SVGDocument svgDoc = new SAXSVGDocumentFactory(XMLResourceDescriptor.getXMLParserClassName()).createSVGDocument(null, input);
	
					    // Try to read embedded height and width
					    float svgWidth = Float.parseFloat(svgDoc.getDocumentElement().getAttribute("width").replaceAll("[^0-9.,]",""));
					    float svgHeight = Float.parseFloat(svgDoc.getDocumentElement().getAttribute("height").replaceAll("[^0-9.,]",""));
	
					    PdfTemplate svgTempl = PdfTemplate.createTemplate(pdfWriter, svgWidth, svgHeight);
					    Graphics2D g2d = new PdfGraphics2D(svgTempl, svgTempl.getWidth(), svgTempl.getHeight());
					    GraphicsNode chartGfx = (new GVTBuilder()).build(new BridgeContext(new UserAgentAdapter()), svgDoc);
					    chartGfx.paint(g2d);
					    g2d.dispose();
	
					    chartIm = new ImgTemplate(svgTempl);
					}
				}catch(Exception e){
					
				}finally {
					if(input!=null) {
						try {
							input.close();
						}catch (Exception e) {
						}
					}
				}
			}

			float _d_h = 0;
			float _d_v = 0;
			try{
				_d_h = Integer.valueOf(internal_style.getDIMENTION_H()).intValue();
			}catch(Exception e){
			}
			if(_d_h==0){
				try{
					_d_h = Integer.valueOf(internal_style.getWIDTH()).intValue();
				}catch(Exception e){
				}
			}
			try{
				_d_v = Integer.valueOf(internal_style.getDIMENTION_V()).intValue();
			}catch(Exception e){
			}
			if(_d_v==0){
				try{
					_d_v = Integer.valueOf(internal_style.getHEIGHT()).intValue();
				}catch(Exception e){
				}
			}


			float rotation = 0;
			try{
				rotation = new Float(getStyle().getTEXT_ROTATION_DEGREE()).floatValue();
			}catch(Exception e){
			}

			if(chartIm!=null) {
				if(_d_h>0 && _d_v>0){
					try{
						chartIm.scaleAbsolute(_d_h,_d_v);
					}catch(Exception e){
					}
				}else if(_d_h>0 || _d_v>0){
					float origHeight = chartIm.getHeight();
					float origWidth = chartIm.getWidth();
					if(_d_h>0) {
						float delta = origWidth/_d_h;
						_d_v = origHeight/delta;
					}else {
						float delta = origHeight/_d_v;
						_d_h = origWidth/delta;
					}
					try{
						chartIm.scaleAbsolute(_d_h,_d_v);
					}catch(Exception e){
					}				
				}
	
				if(rotation!=0){
					try{
						chartIm.setRotationDegrees(rotation);
					}catch(Exception e){
					}
				}
			}

			if(chartIm!=null && !internal_style.getABSOLUTE_X().equals("") && !internal_style.getABSOLUTE_Y().equals("")){
				try{
					chartIm.setAbsolutePosition(new Float(internal_style.getABSOLUTE_X()).floatValue(),new Float(internal_style.getABSOLUTE_Y()).floatValue());
					_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas).add2content(chartIm);
					if(_tagLibrary.get(getName()+":"+getID())==null)
						_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
					else _tagLibrary.remove(getName()+":"+getID());

					return;
				}catch(Exception e){
				}
			}

			if(chartIm==null) {
				if(getIMAGE_ERROR()!=null)
					_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas).add2content(new Phrase(getIMAGE_ERROR()));
				else if(getSTR_ERROR()!=null)
					_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas).add2content(new Phrase(getSTR_ERROR()));
				else
					_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas).add2content(new Phrase("ERRORE: Path "+pathImg+" isn't correct."));
			}
			else 
				_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas).add2content(chartIm);
			
			

			


		if(_tagLibrary.get(getName()+":"+getID())==null)
			_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
		else _tagLibrary.remove(getName()+":"+getID());

	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}
}
public void reimposta() {
	setName("SVG");
	IMAGE_SOURCE = "";
	IMAGE_CREATOR = "";
	IMAGE_LOADER = "";
//	IMAGE_ERROR = "";
	STYLE_ID = "";
	FIT = "false";
}
public void reStyle(style style_new) {
	if(internal_style==null) return;
	internal_style.reStyle(style_new);
}
public String getIMAGE_SOURCE() {
	return IMAGE_SOURCE;
}
public void setIMAGE_SOURCE(String string) {
	IMAGE_SOURCE = string;
}
public String getIMAGE_LOADER() {
	return IMAGE_LOADER;
}
public void setIMAGE_LOADER(String image_loader) {
	IMAGE_LOADER = image_loader;
}
public String getIMAGE_ERROR() {
	return IMAGE_ERROR;
}
public void setIMAGE_ERROR(String iMAGE_ERROR) {
	IMAGE_ERROR = iMAGE_ERROR;
}
public String getSTR_ERROR() {
	return STR_ERROR;
}
public void setSTR_ERROR(String sTR_ERROR) {
	STR_ERROR = sTR_ERROR;
}
public String getFIT() {
	return FIT;
}
public void setFIT(String fIT) {
	FIT = fIT;
}
public String getIMAGE_CREATOR() {
	return IMAGE_CREATOR;
}
public void setIMAGE_CREATOR(String iMAGE_CREATOR) {
	IMAGE_CREATOR = iMAGE_CREATOR;
}
}
