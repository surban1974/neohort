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

import java.awt.Color;
import java.util.Hashtable;

import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.lib.bean;
import neohort.universal.output.lib.report_element_base;
import neohort.universal.output.lib.style;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

/*
 	"Courier";
	"Courier-Bold";
	"Courier-Oblique";
	"Courier-BoldOblique";
	"Helvetica";
	"Helvetica-Bold";
	"Helvetica-Oblique";
	"Helvetica-BoldOblique";
	"Symbol";
	"Times-Roman";
	"Times-Bold";
	"Times-Italic";
	"Times-BoldItalic";
	"ZapfDingbats";

	CP1250 = "Cp1250";
    CP1252 = "Cp1252";
	CP1257 = "Cp1257";
	WINANSI = "Cp1252";

 */

public class text_paragraph extends text{

	private static final long serialVersionUID = -1L;
	private String ISTEMPLATE;
	private PdfTemplate template=null;
	private boolean drawTextInTemplate=false;
	private boolean isCreated=false;


public text_paragraph() {
	super();
}
public void executeFirst(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
	try{
		if(getISTEMPLATE().equalsIgnoreCase("TRUE") && _beanLibrary.get(getName()+":"+getID())!=null)
			this.setTemplate(((text_paragraph)_beanLibrary.get(getName()+":"+getID())).getTemplate());
	}catch(Exception e){
	}

}
public void executeLast(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
	_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas).add2content(this);
	if(getISTEMPLATE().equalsIgnoreCase("TRUE")){
		_beanLibrary.put(getName()+":"+getID(),this);
		if(_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Templates)==null) _beanLibrary.put("SYSTEM:"+iConst.iHORT_SYSTEM_Templates,new bean());
		if(((bean)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Templates)).getContent()==null) 
			((bean)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Templates)).setContent(new Hashtable<String,Object>());
		_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Templates).getContentAsMap().put(getName()+":"+getID(),getName()+":"+getID());
	}else{
		if(_tagLibrary.get(getName()+":"+getID())==null)
			_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
		else _tagLibrary.remove(getName()+":"+getID());
	}
}

public void drawDirect(Hashtable<String, report_element_base> _beanLibrary){
	try{
		if(	getISTEMPLATE().equalsIgnoreCase("TRUE") &&
			_beanLibrary.get(getName()+":"+getID())!=null) isCreated=true;

		this.init_element(this.getNodeCurrent(),(report_element_base)this.getParent(), null, _beanLibrary, null);
		int _f_size = 10;
		try{
			_f_size = Integer.valueOf(internal_style.getFONT_SIZE()).intValue();
		}catch(Exception e){}

		BaseFont bs = null;
		String bs_name="Helvetica";

		if(internal_style.getFONT()!=null && !internal_style.getFONT().equals("")){
			bs_name = adaptAttrName(internal_style.getFONT());
			if(internal_style.getFONT_TYPE()!=null && !internal_style.getFONT_TYPE().equals(""))
				bs_name+="-"+adaptAttrName(internal_style.getFONT_TYPE());
			if(internal_style.getFONT_STYLE()!=null && !internal_style.getFONT_STYLE().equals("")){
				if(bs_name.indexOf("_")==-1) bs_name+="-"+adaptAttrName(internal_style.getFONT_STYLE());
				else bs_name+=adaptAttrName(internal_style.getFONT_STYLE());
			}

			String bs_code = "Cp1252";
			if(internal_style.getFONT_ENCODED()!=null && !internal_style.getFONT_ENCODED().equals("")){
				bs_code = internal_style.getFONT_ENCODED();
			}
			try{
				bs = BaseFont.createFont(bs_name, bs_code, BaseFont.NOT_EMBEDDED);
			}catch(Exception e){
				bs = BaseFont.createFont("Helvetica", BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
			}
		}else if(internal_style.getEXTRA_FONT()!=null && !internal_style.getEXTRA_FONT().equals("")){
			if(!getStyle().getFONT_ENCODED().equals("")){
				if(!getStyle().getFONT_EMBEDDED().equals("")){
					if(getStyle().getFONT_EMBEDDED().equalsIgnoreCase("EMBEDDED"))
						bs = BaseFont.createFont(getStyle().getEXTRA_FONT(), getStyle().getFONT_ENCODED(), BaseFont.EMBEDDED);
					else
						bs = BaseFont.createFont(getStyle().getEXTRA_FONT(), getStyle().getFONT_ENCODED(), BaseFont.NOT_EMBEDDED);
				}else{
					bs = BaseFont.createFont(getStyle().getEXTRA_FONT(), getStyle().getFONT_ENCODED(), BaseFont.EMBEDDED);
				}
			}else
				bs = BaseFont.createFont(getStyle().getEXTRA_FONT(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

		}else{
			String bs_code = "Cp1252";
			if(internal_style.getFONT_ENCODED()!=null && !internal_style.getFONT_ENCODED().equals("")){
				bs_code = internal_style.getFONT_ENCODED();
			}
			try{
				bs = BaseFont.createFont(bs_name, bs_code, BaseFont.NOT_EMBEDDED);
			}catch(Exception e){
				bs = BaseFont.createFont("Helvetica", BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
			}
		}



		String content = prepareContentString(internal_style.getFORMAT());

		float absolute_x = 0;
		try{
			absolute_x = new Float(getStyle().getABSOLUTE_X()).floatValue();
		}catch(Exception e){
		}
		float absolute_y = 0;
		try{
			absolute_y = new Float(getStyle().getABSOLUTE_Y()).floatValue();
		}catch(Exception e){
		}
		float rotation = 0;
		try{
			rotation = new Float(getStyle().getTEXT_ROTATION_DEGREE()).floatValue();
		}catch(Exception e){
		}
		
		float width = 0;
		try{
			width = new Float(getStyle().getWIDTH()).floatValue();
		}catch(Exception e){
		}
		float height = 0;
		try{
			height = new Float(getStyle().getHEIGHT()).floatValue();
		}catch(Exception e){
		}		

		PdfWriter pdfWriter = (PdfWriter)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent());
		PdfContentByte cb = pdfWriter.getDirectContent();
		
		if(ISTEMPLATE.equalsIgnoreCase("TRUE")){

			if(!isCreated || template==null) {	
				if(width>0 && height>0)
					template = cb.createTemplate(width, height);
				else
					template = cb.createTemplate(absolute_y, absolute_x);
			}
//			template.setBoundingBox(new com.lowagie.text.Rectangle(-20, -20, width, height));
			if(!drawTextInTemplate) 
				cb.addTemplate(template, absolute_x, absolute_y);
			else{
				template.beginText();
				template.setFontAndSize(bs, _f_size);
				template.setColorFill(getField_Color(internal_style.getFONT_COLOR(),Color.black));
				template.setTextMatrix(0, 0);
				if(rotation==0)	template.showText(content);
				else template.showTextAligned(PdfContentByte.ALIGN_LEFT, content, 0, 0, rotation);
				template.endText();
			}
		}else{
			Font font = getFont();
			Paragraph paragraph = null;
			int _f_leading = -1;
			try{
				_f_leading = Integer.valueOf(internal_style.getLEADING()).intValue();
			}catch(Exception e){}
			if(_f_leading==-1) paragraph = new Paragraph(content,font);
			else paragraph = new Paragraph(_f_leading,content,font);
			int align_h = getField_Int(new PdfPCell(new Phrase("")).getClass(),"ALIGN_"+internal_style.getALIGN(),0);

			if(!internal_style.getDIRECTION().equals("") && internal_style.getDIRECTION().equalsIgnoreCase("RTL"))
				showTextAligned(cb, align_h, paragraph, absolute_x, absolute_y, rotation, width, height, PdfWriter.RUN_DIRECTION_RTL, ColumnText.AR_NOVOWEL);
			else
				showTextAligned(cb, align_h, paragraph, absolute_x, absolute_y, rotation, width, height);
				
		}


//		cb.saveState();

	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}
}
public static void showTextAligned(PdfContentByte canvas, int alignment, Paragraph paragraph, float x, float y, float rotation, float width, float height) {
    showTextAligned(canvas, alignment, paragraph, x, y, rotation, width, height, PdfWriter.RUN_DIRECTION_NO_BIDI, 0);
}
public static void showTextAligned(PdfContentByte canvas, int alignment, Paragraph paragraph, float x, float y, float rotation, float width, float height, int runDirection, int arabicOptions) {
    if (alignment != Element.ALIGN_LEFT && alignment != Element.ALIGN_CENTER
        && alignment != Element.ALIGN_RIGHT)
        alignment = Element.ALIGN_LEFT;
    canvas.saveState();
    ColumnText ct = new ColumnText(canvas);
    float lly = -1;
    float ury = 2;
    if(height>0)
    	ury+=height;
    float llx;
    float urx;
    switch (alignment) {
        case Element.ALIGN_LEFT:
            llx = 0;
            if(width>0)
            	urx = width;
            else
            	urx = 20000;
            break;
        case Element.ALIGN_RIGHT:
            if(width>0)
            	llx = (-1)*width;
            else
            	llx = -20000;
            urx = 0;
            break;
        default:
        	if(width>0) {
	            llx = (-1)*width; 
	            urx = width;      		
        	}else {
	            llx = -20000;
	            urx = 20000;
        	}
            break;
    }
    if (rotation == 0) {
        llx += x;
        lly += y;
        urx += x;
        ury += y;
    }
    else {
        double alpha = rotation * Math.PI / 180.0;
        float cos = (float)Math.cos(alpha);
        float sin = (float)Math.sin(alpha);
        canvas.concatCTM(cos, sin, -sin, cos, x, y);
    }
    ct.setSimpleColumn(llx, lly, urx, ury, 2, alignment);
    ct.addElement(paragraph);
    if (runDirection == PdfWriter.RUN_DIRECTION_RTL) {
        if (alignment == Element.ALIGN_LEFT)
            alignment = Element.ALIGN_RIGHT;
        else if (alignment == Element.ALIGN_RIGHT)
            alignment = Element.ALIGN_LEFT;
    }
    ct.setAlignment(alignment);
    ct.setArabicOptions(arabicOptions);
    ct.setRunDirection(runDirection);
    try {
        ct.go();
    }
    catch (DocumentException e) {
        throw new ExceptionConverter(e);
    }
    canvas.restoreState();
}
public boolean refreshText() {
	return true;
}
public void reimposta() {
	setName("TEXT_PARAGRAPH");
	STYLE_ID = "";
	ISTEMPLATE="FALSE";

}
public void reStyle(style style_new) {
	if(internal_style==null) return;
	internal_style.reStyle(style_new);
}
public void drawCanvas(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary) {
	initCanvas(_tagLibrary,_beanLibrary);
}
	public PdfTemplate getTemplate() {
		return template;
	}

	public void setTemplate(PdfTemplate template) {
		this.template = template;
	}

	public String getISTEMPLATE() {
		return ISTEMPLATE;
	}

	public void setISTEMPLATE(String string) {
		ISTEMPLATE = string;
	}

	public boolean isDrawTextInTemplate() {
		return drawTextInTemplate;
	}

	public void setDrawTextInTemplate(boolean b) {
		drawTextInTemplate = b;
	}

}
