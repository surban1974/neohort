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

package neohort.universal.output.lib_rtf;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URL;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.lib.report_element_base;
import neohort.universal.output.lib.style;
import neohort.util.util_reflect;
import neohort.util.util_web;

import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;

public class image extends element{
	private static final long serialVersionUID = -667432326013798264L;
	private PdfPCell cell;
	private String IMAGE_SOURCE;
	private String IMAGE_LOADER;
	
public image() {
	super();
}
public void drawCanvas(Hashtable _tagLibrary, Hashtable _beanLibrary) {
	initCanvas(_tagLibrary,_beanLibrary);
}
public void executeFirst(Hashtable _tagLibrary, Hashtable _beanLibrary){
}
public void executeLast(Hashtable _tagLibrary, Hashtable _beanLibrary){
	try{


			int _align = getField_Int(new PdfPCell(new Phrase("")).getClass(),"ALIGN_"+internal_style.getALIGN(),0);
			int border = 0;
			try{
				border = Integer.valueOf(internal_style.getBORDER()).intValue();
			}catch(Exception e){}
			float padding = 0;
			try{
				padding = Float.valueOf(internal_style.getPADDING()).floatValue();
			}catch(Exception e){}

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
			
			if(!IMAGE_LOADER.equals("")){
				Object loader = null;
				try{
					loader = Class.forName(IMAGE_LOADER).newInstance();
					util_reflect.setValue(loader, "setProperty", new Object[]{IMAGE_SOURCE});
					byte[] imgBytes = (byte[])util_reflect.getValue(loader,"getBytes",null);
					chartIm = Image.getInstance(imgBytes);
				}catch(Exception e){	
					chartIm=null;
					setError(e,iStub.log_ERROR);
				}
			}
			if(IMAGE_LOADER.equals("") || chartIm==null){
				 
				
				try{
					BufferedImage input = null;
					Exception ex=null;
					try{
						input = ImageIO.read(new File(getIMAGE_SOURCE()));
					}catch(Exception e){
						ex=e;
					}
					if(input==null){
						try{
							input = ImageIO.read(getClass().getResource(getIMAGE_SOURCE()));
						}catch(Exception e){
							ex=e;
						}
					}
					if(input==null){
						try{
							input = ImageIO.read(new URL(pathImg));
						}catch(Exception e){
							ex=e;
						}
					}
					if(input==null && ex!=null)
						setError(ex,iStub.log_ERROR);
					if(input!=null){
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						ImageIO.write(input, "PNG", baos);
						chartIm = Image.getInstance(baos.toByteArray());
					}					
/*						
					if(pathImg.trim().indexOf("http:")==0 || pathImg.trim().indexOf("https:")==0){
						java.awt.Image awtImage = Toolkit.getDefaultToolkit().createImage(new java.net.URL(pathImg));
						chartIm = Image.getInstance(awtImage,null);
					}
					else{
						java.awt.Image awtImage = Toolkit.getDefaultToolkit().createImage(pathImg);
						chartIm = Image.getInstance(awtImage,null);
						chartIm = Image.getInstance(ImageIO.read(new File(pathImg)),null);
					}
*/					
				}catch(Exception e){}
			}
			int _d_h = 0;
			int _d_v = 0;
			try{
				_d_h = Integer.valueOf(internal_style.getDIMENTION_H()).intValue();
				_d_v = Integer.valueOf(internal_style.getDIMENTION_V()).intValue();
			}catch(Exception e){
				_d_h = 0;
				_d_v = 0;
			}
			
			float rotation = 0;
			try{
				rotation = new Float(getStyle().getTEXT_ROTATION_DEGREE()).floatValue();
			}catch(Exception e){
			}

			
			if(_d_h>0 && _d_v>0){
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
			
			if(chartIm!=null && !internal_style.getABSOLUTE_X().equals("") && !internal_style.getABSOLUTE_Y().equals("")){
				try{
					chartIm.setAbsolutePosition(new Float(internal_style.getABSOLUTE_X()).floatValue(),new Float(internal_style.getABSOLUTE_Y()).floatValue());
					((java.util.Vector)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas)).getContent())).add(chartIm);
					if(_tagLibrary.get(getName()+":"+getID())==null)
						_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
					else _tagLibrary.remove(getName()+":"+getID());
					
					return;
				}catch(Exception e){
				}
			}
			
			if(chartIm==null) cell = new PdfPCell(new Phrase("ERRORE: Path "+pathImg+" isn't correct."));
			else cell = new PdfPCell(chartIm);
				cell.setHorizontalAlignment(_align);
				cell.setBorder(border);
			if(padding!=0) cell.setPadding(padding);
			((java.util.Vector)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas)).getContent())).add(cell);


		if(_tagLibrary.get(getName()+":"+getID())==null)
			_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
		else _tagLibrary.remove(getName()+":"+getID());

	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}
}
public void reimposta() {
	setName("IMAGE");
	IMAGE_SOURCE = "";
	IMAGE_LOADER = "";
	STYLE_ID = "";
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

}
