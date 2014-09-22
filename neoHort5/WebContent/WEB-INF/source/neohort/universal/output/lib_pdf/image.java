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

import java.awt.Toolkit;
import java.util.Hashtable;

import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.lib.report_element_base;
import neohort.universal.output.lib.style;
import neohort.util.util_reflect;
import neohort.util.util_web;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;

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
//					chartIm = Image.getInstance(imgBytes);
					java.awt.Image awtImage = Toolkit.getDefaultToolkit().createImage(imgBytes);
					chartIm = Image.getInstance(awtImage,null);
					
				}catch(Exception e){
					chartIm=null;
					setError(e,iStub.log_ERROR);
				}
			}
			if(IMAGE_LOADER.equals("") || chartIm==null){


				try{
					if(pathImg.trim().indexOf("http:")==0){
//						chartIm = Image.getInstance(new java.net.URL(pathImg));
						
						java.awt.Image awtImage = Toolkit.getDefaultToolkit().createImage(new java.net.URL(pathImg));
						chartIm = Image.getInstance(awtImage,null);
					}
					else{
//						chartIm = Image.getInstance(pathImg);
						java.awt.Image awtImage = Toolkit.getDefaultToolkit().createImage(pathImg);
						chartIm = Image.getInstance(awtImage,null);

					}
				}catch(Exception e){}
			}
			int _d_h = 0;
			int _d_v = 0;
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
			else 
				cell = new PdfPCell(chartIm, false);
			
			cell.setHorizontalAlignment(_align);
			cell.setBorder(border);

			if(padding!=0)
				cell.setPadding(padding);
			


			if(!internal_style.getBORDER_WIDTH_TOP().equals("")){
				try{
					float localborderWidth = Float.valueOf(internal_style.getBORDER_WIDTH_TOP()).floatValue();
					cell.setUseVariableBorders(true);
					cell.setBorderWidthTop(localborderWidth);
				}catch(Exception e){}
			}
			if(!internal_style.getBORDER_WIDTH_BOTTOM().equals("")){
				try{
					float localborderWidth = Float.valueOf(internal_style.getBORDER_WIDTH_BOTTOM()).floatValue();
					cell.setUseVariableBorders(true);
					cell.setBorderWidthBottom(localborderWidth);
				}catch(Exception e){}
			}
			if(!internal_style.getBORDER_WIDTH_LEFT().equals("")){
				try{
					float localborderWidth = Float.valueOf(internal_style.getBORDER_WIDTH_LEFT()).floatValue();
					cell.setBorderWidthLeft(localborderWidth);
				}catch(Exception e){}
			}
			if(!internal_style.getBORDER_WIDTH_RIGHT().equals("")){
				try{
					float localborderWidth = Float.valueOf(internal_style.getBORDER_WIDTH_RIGHT()).floatValue();
					cell.setUseVariableBorders(true);
					cell.setBorderWidthRight(localborderWidth);
				}catch(Exception e){}
			}
			if(!internal_style.getBORDER_COLOR().equals("")){
				try{
					BaseColor color = getField_Color(internal_style.getBORDER_COLOR(),BaseColor.BLACK);
					cell.setBorderColor(color);
				}catch(Exception e){}
			}
			if(!internal_style.getBORDER_COLOR_TOP().equals("")){
				try{
					BaseColor color = getField_Color(internal_style.getBORDER_COLOR_TOP(),BaseColor.BLACK);
					cell.setUseVariableBorders(true);
					cell.setBorderColorTop(color);
				}catch(Exception e){}
			}
			if(!internal_style.getBORDER_COLOR_BOTTOM().equals("")){
				try{
					BaseColor color = getField_Color(internal_style.getBORDER_COLOR_BOTTOM(),BaseColor.BLACK);
					cell.setUseVariableBorders(true);
					cell.setBorderColorBottom(color);
				}catch(Exception e){}
			}
			if(!internal_style.getBORDER_COLOR_LEFT().equals("")){
				try{
					BaseColor color = getField_Color(internal_style.getBORDER_COLOR_LEFT(),BaseColor.BLACK);
					cell.setUseVariableBorders(true);
					cell.setBorderColorLeft(color);
				}catch(Exception e){}
			}
			if(!internal_style.getBORDER_COLOR_RIGHT().equals("")){
				try{
					BaseColor color = getField_Color(internal_style.getBORDER_COLOR_RIGHT(),BaseColor.BLACK);
					cell.setUseVariableBorders(true);
					cell.setBorderColorRight(color);
				}catch(Exception e){}
			}
			if(!internal_style.getBACK_COLOR().equals("")){
				try{
					BaseColor color = getField_Color(internal_style.getBACK_COLOR(),BaseColor.BLACK);
					cell.setUseVariableBorders(false);
					cell.setBackgroundColor(color);
				}catch(Exception e){}
			}
			if(!internal_style.getBORDER_WIDTH().equals("")){
				try{
					float localborderWidth = Float.valueOf(internal_style.getBORDER_WIDTH()).floatValue();

					cell.setBorderWidth(localborderWidth);
				}catch(Exception e){}
			}
			
			if(!internal_style.getDIRECTION().equals("") && internal_style.getDIRECTION().equalsIgnoreCase("RTL")){
				if(cell!=null)
					cell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
			}
			
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
