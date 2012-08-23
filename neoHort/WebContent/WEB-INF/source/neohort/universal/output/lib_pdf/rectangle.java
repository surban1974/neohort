/**
* Creation date: (12/01/2006)
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

import java.awt.Color;
import java.util.Hashtable;

import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.lib.report_element_base;
import neohort.universal.output.lib.style;
import com.lowagie.text.pdf.PdfContentByte;

import com.lowagie.text.pdf.PdfWriter;


public class rectangle extends element{
	private static final long serialVersionUID = 2898978196248810829L;
	private String X_LEFT;
	private String Y_TOP;
	private String X_RIGHT;
	private String Y_BOTTOM;
	
	
public rectangle() {
	super();
}
public void executeFirst(Hashtable _tagLibrary, Hashtable _beanLibrary){	

}
public void executeLast(Hashtable _tagLibrary, Hashtable _beanLibrary){
	
	((java.util.Vector)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas)).getContent())).add(this);
	if(_tagLibrary.get(getName()+":"+getID())==null)
		_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
	else _tagLibrary.remove(getName()+":"+getID());

}

public void drawDirect(Hashtable _beanLibrary){
	try{
		PdfWriter pdfWriter = (PdfWriter)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent());
		PdfContentByte cb = pdfWriter.getDirectContent();
		float x_left = 0;
		float x_right = 0;
		float y_top = 0;
		float y_bottom = 0;
		try{
			x_left = Float.valueOf(getX_LEFT()).floatValue();
		}catch(Exception e){}
		try{
			x_right = Float.valueOf(getX_RIGHT()).floatValue();
		}catch(Exception e){}
		try{
			y_top = Float.valueOf(getY_TOP()).floatValue();
		}catch(Exception e){}
		try{
			y_bottom = Float.valueOf(getY_BOTTOM()).floatValue();
		}catch(Exception e){}

		Color color = getField_Color(new Color(0).getClass(),internal_style.getBORDER_COLOR(),Color.black);

		cb.setColorFill(color);
			cb.moveTo(x_left,y_top);
				cb.lineTo(x_right,y_top);
				cb.lineTo(x_left,y_bottom);
			cb.moveTo(x_right,y_bottom);
			cb.lineTo(x_right,y_top);
			cb.lineTo(x_left,y_bottom);
		cb.fill();
		
		if(internal_style!=null && !internal_style.getBACK_COLOR().equals("")){
			Color background = getField_Color(new Color(0).getClass(),internal_style.getBACK_COLOR(),Color.white);
			cb.setColorFill(background);
			cb.rectangle(x_left+0.1f,y_top+0.1f,x_right-0.1f,y_bottom-0.1f); 
			cb.fill();
		}
		

	}catch(Exception e){
		setError(e,iStub.log_ERROR);
	}
	
}

public void reimposta() {
	setName("RECTANGLE");
	STYLE_ID = "";
	X_LEFT = "0";
	Y_TOP = "0";
	X_RIGHT = "0";
	Y_BOTTOM = "0";
	
	
}
public void reStyle(style style_new) {
	if(internal_style==null) return;
	internal_style.reStyle(style_new);
}
public void drawCanvas(Hashtable _tagLibrary, Hashtable _beanLibrary) {
	initCanvas(_tagLibrary,_beanLibrary);
}

	public String getX_LEFT() {
		return X_LEFT;
	}

	public String getX_RIGHT() {
		return X_RIGHT;
	}

	public String getY_BOTTOM() {
		return Y_BOTTOM;
	}

	public String getY_TOP() {
		return Y_TOP;
	}

	public void setX_LEFT(String string) {
		X_LEFT = string;
	}

	public void setX_RIGHT(String string) {
		X_RIGHT = string;
	}

	public void setY_BOTTOM(String string) {
		Y_BOTTOM = string;
	}

	public void setY_TOP(String string) {
		Y_TOP = string;
	}

}
