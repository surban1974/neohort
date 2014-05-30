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


import java.awt.Color;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.lib.bean;
import neohort.universal.output.lib.report_element;
import neohort.universal.output.lib.report_element_base;
import neohort.universal.output.lib.style;

import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;


public abstract class element extends report_element_base implements report_element {

	private static final long serialVersionUID = 1L;
	int _sys_border = 0;
	int _sys_align = 0;
public element() {
	super();
	Parameters.addElement("ID");
	reimposta();
}
public void drawCanvas(Hashtable _tagLibrary, Hashtable _beanLibrary) {}
public Object execute(Hashtable _beanLibrary) {
	return null;
}
public PdfPCell getCellC(String frase,int border,Hashtable _beanLibrary) {
	try{
		
		if(frase==null) frase="";
		
		PdfPCell cell = null;
		
		float rotation = 0;
		try{
			rotation = new Float(getStyle().getTEXT_ROTATION_DEGREE()).floatValue();
		}catch(Exception e){
		}
		
		if(internal_style==null){
			internal_style=new style();
		}
		
		if(rotation==0){
			int fontDim = 0;
			int fontType = 0;
			int fontStyle = 0;
			
					
			if(!internal_style.getFONT_SIZE().equals("")){
				try{
					fontDim = Integer.valueOf(internal_style.getFONT_SIZE()).intValue();
				}catch(Exception e){}
			}	
			if(fontDim == 0) fontDim = 8;
			
			if(!internal_style.getFONT().equals("")){
				try{
					fontType = getField_Int(new com.lowagie.text.Font().getClass(),internal_style.getFONT(),com.lowagie.text.Font.COURIER); 
				}catch(Exception e){
				}
			}		
			
			if(!internal_style.getFONT_TYPE().equals("")){
				try{
					fontStyle = getField_Int(new com.lowagie.text.Font().getClass(),internal_style.getFONT_TYPE(),com.lowagie.text.Font.NORMAL);
				}catch(Exception e){
				}
			}	
		
			if(fontStyle == 0) fontStyle = com.lowagie.text.Font.NORMAL;
			Font font = new Font(fontType, fontDim, fontStyle);
			
			if(!internal_style.getFONT_COLOR().equals("")){
				try{
					Color fontColor = getField_Color(new Color(0).getClass(),internal_style.getFONT_COLOR(),Color.black);
					font.setColor(fontColor);
				}catch(Exception e){}
			}	
		
			if(!internal_style.getFONT_STYLE().equals(""))
				font.setStyle(internal_style.getFONT_STYLE().toLowerCase());
				
			cell = null;
			int _f_leading = -1;
			try{
				_f_leading = Integer.valueOf(internal_style.getLEADING()).intValue();
			}catch(Exception e){}
			if(_f_leading==-1) cell = new PdfPCell(new Phrase(frase,font));
			else cell = new PdfPCell(new Phrase(_f_leading,frase,font));
			

		}else{

//			int _f_size = 10;
//			try{
//				_f_size = Integer.valueOf(internal_style.getFONT_SIZE()).intValue();
//			}catch(Exception e){}
//		
//			BaseFont bs = null;
//			String bs_name="Helvetica";
//		
//			if(internal_style.getFONT()!=null && !internal_style.getFONT().equals("")){
//				bs_name = adaptAttrName(internal_style.getFONT());
//				if(internal_style.getFONT_TYPE()!=null && !internal_style.getFONT_TYPE().equals(""))
//					bs_name+="-"+adaptAttrName(internal_style.getFONT_TYPE());
//				if(internal_style.getFONT_STYLE()!=null && !internal_style.getFONT_STYLE().equals("")){
//					if(bs_name.indexOf("_")==-1) bs_name+="-"+adaptAttrName(internal_style.getFONT_STYLE());
//					else bs_name+=adaptAttrName(internal_style.getFONT_STYLE());
//				}
//			}
//			String bs_code = "Cp1252";
//			if(internal_style.getFONT_ENCODED()!=null && !internal_style.getFONT_ENCODED().equals("")){
//				bs_code = internal_style.getFONT_ENCODED();
//			}	
//			try{
//				bs = BaseFont.createFont(bs_name, bs_code, BaseFont.NOT_EMBEDDED);
//			}catch(Exception e){
//				bs = BaseFont.createFont("Helvetica", BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
//			}
//			
//			float height_check=-1;
//			if(!internal_style.getHEIGHT().equals("")){
//				try{
//					height_check = Float.valueOf(internal_style.getHEIGHT()).floatValue();
//				}catch(Exception e){}
//			}
//			if(!internal_style.getDIMENTION_V().equals("")){
//				try{
//					height_check = Float.valueOf(internal_style.getDIMENTION_V()).floatValue();
//				}catch(Exception e){}
//			}	
//
//
//			float width_img = bs.getWidthPoint(frase, _f_size)+4;
//			float height_img = _f_size + 2; 
//			
//			if(height_check>0){
//				double alfa = rotation*Math.PI/180;
//				double h_real =Math.abs(Math.cos(alfa)*height_img)+Math.abs(Math.sin(alfa)*width_img);
//
//			
//				if(height_check<h_real){
//					int l = (int)(h_real/(height_check-2));
//					if((int)(h_real/(height_check-2))<width_img/(height_check-2)) l++;
//					width_img = height_check; 
////					height_img = height_img*l; 
//					frase = split(frase,l);
//				}	
//			}
//			
//			
//			RtfWriter2 pdfWriter = (RtfWriter2)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent());
//			PdfTemplate template = pdfWriter.getDirectContent().createTemplate(20, 20);
//			template.beginText();
//
//			template.setColorFill(getField_Color(new Color(0).getClass(),internal_style.getFONT_COLOR(),Color.black));
////			template.setRGBColorFillF(1, 1, 1);
//			
//			template.setFontAndSize(bs, _f_size);
//			template.setTextMatrix(2, 2);
//			template.showText(frase);
//			template.endText();
//			template.setWidth(width_img);
//			template.setHeight(height_img);
//			Image img = Image.getInstance(template);
//			img.setRotationDegrees(rotation);
//			
//			cell = new PdfPCell(img);
			
			cell = new PdfPCell();
		}
			
		cell.setBorder(border);
		if(!internal_style.getBORDER_WIDTH_TOP().equals("")){	
			float padding = 0;
			try{
				padding = Float.valueOf(internal_style.getPADDING()).floatValue();
				if(padding!=0) cell.setPadding(padding);
			}catch(Exception e){}
		}
		
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
				Color color = getField_Color(new Color(0).getClass(),internal_style.getBORDER_COLOR(),Color.black);
				cell.setBorderColor(color);
			}catch(Exception e){}
		}	
		if(!internal_style.getBORDER_COLOR_TOP().equals("")){
			try{
				Color color = getField_Color(new Color(0).getClass(),internal_style.getBORDER_COLOR_TOP(),Color.black);
				cell.setUseVariableBorders(true);
				cell.setBorderColorTop(color);
			}catch(Exception e){}
		}	
		if(!internal_style.getBORDER_COLOR_BOTTOM().equals("")){
			try{
				Color color = getField_Color(new Color(0).getClass(),internal_style.getBORDER_COLOR_BOTTOM(),Color.black);
				cell.setUseVariableBorders(true);
				cell.setBorderColorBottom(color);
			}catch(Exception e){}
		}	
		if(!internal_style.getBORDER_COLOR_LEFT().equals("")){
			try{
				Color color = getField_Color(new Color(0).getClass(),internal_style.getBORDER_COLOR_LEFT(),Color.black);
				cell.setUseVariableBorders(true);
				cell.setBorderColorLeft(color);
			}catch(Exception e){}
		}	
		if(!internal_style.getBORDER_COLOR_RIGHT().equals("")){
			try{
				Color color = getField_Color(new Color(0).getClass(),internal_style.getBORDER_COLOR_RIGHT(),Color.black);
				cell.setUseVariableBorders(true);
				cell.setBorderColorRight(color);
			}catch(Exception e){}
		}			
		if(!internal_style.getBACK_COLOR().equals("")){
			try{
				Color color = getField_Color(new Color(0).getClass(),internal_style.getBACK_COLOR(),Color.black);
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
		if(!internal_style.getHEIGHT().equals("")){
			try{
				float height = Float.valueOf(internal_style.getHEIGHT()).floatValue();
				cell.setFixedHeight(height);
			}catch(Exception e){}
		}
		if(!internal_style.getDIMENTION_V().equals("")){
			try{
				float height = Float.valueOf(internal_style.getDIMENTION_V()).floatValue();
				cell.setFixedHeight(height);
			}catch(Exception e){}
		}	
			
		if(!internal_style.getTEXT_ALIGN_V().equals("")){
			try{
				int align_v = getField_Int(new PdfPCell(new Phrase("")).getClass(),"ALIGN_"+internal_style.getTEXT_ALIGN_V(),0);
				cell.setVerticalAlignment(align_v);
			}catch(Exception e){
			}
		}
		if(!internal_style.getTEXT_ALIGN_H().equals("")){
			try{
				int align_h = getField_Int(new PdfPCell(new Phrase("")).getClass(),"ALIGN_"+internal_style.getTEXT_ALIGN_H(),0);
				cell.setHorizontalAlignment(align_h);
			}catch(Exception e){
			}
		}
		if(!internal_style.getALIGN().equals("")){
			try{
				int align_h = getField_Int(new PdfPCell(new Phrase("")).getClass(),"ALIGN_"+internal_style.getALIGN(),0);
				cell.setHorizontalAlignment(align_h);
			}catch(Exception e){
			}
		}

		return cell;
	}catch(Exception e){
		setError(e,iStub.log_WARN);
		return new PdfPCell(new Phrase(""));
	}
}
public void initCanvas(Hashtable _tagLibrary, Hashtable _beanLibrary) {
	try{
		Boolean initProcess = new Boolean(false);
		try{
			initProcess = (Boolean)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_initProcess)).getContent());
		}catch(Exception e){
       		bean _sysinitProcess = new bean();
        	_sysinitProcess.setContent(initProcess);
        	_sysinitProcess.setName("SYSTEM");
        	_sysinitProcess.setID(iConst.iHORT_SYSTEM_initProcess);
        	_beanLibrary.put(
            	_sysinitProcess.getName() + ":" + _sysinitProcess.getID(),
            	_sysinitProcess);

		}
		java.util.Vector canvas = ((java.util.Vector)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas)).getContent()));
		Object current_Element = canvas.lastElement();
			canvas.removeElement(canvas.lastElement());
		Object content_Element = canvas.lastElement();

		if(	initProcess.booleanValue() &&
			(	((bean)current_Element).getID().equals("PageFooter_") ||
				((bean)current_Element).getID().equals("PageHeader_"))
			){
			return;
		}
//		if(isError) return;


		if(content_Element instanceof bean){
			if(((bean)content_Element).getID().equals("PageFooter_"))
				((java.util.Vector)((bean)content_Element).getContent()).add(current_Element);
			if(((bean)content_Element).getID().equals("PageHeader_"))
				((java.util.Vector)((bean)content_Element).getContent()).add(current_Element);
			return;
		}
		if(current_Element instanceof text){
			((text)current_Element).drawDirect(_beanLibrary);
			if(((bean)content_Element).getID().equals("PageFooter_"))
				((java.util.Vector)((bean)content_Element).getContent()).add(current_Element);
			if(((bean)content_Element).getID().equals("PageHeader_"))
				((java.util.Vector)((bean)content_Element).getContent()).add(current_Element);
			return;
		}
		
		if(content_Element instanceof List){
			if(current_Element instanceof com.lowagie.text.pdf.PdfPTable){
				com.lowagie.text.pdf.PdfPCell cell = new com.lowagie.text.pdf.PdfPCell((com.lowagie.text.pdf.PdfPTable)current_Element);
				cell.setBorder(_sys_border);
				cell.setHorizontalAlignment(_sys_align);
				((List)content_Element).add(cell);
				return;
			}
			if(current_Element instanceof com.lowagie.text.pdf.PdfPCell){
				((List)content_Element).add((com.lowagie.text.pdf.PdfPCell)current_Element);
				return;
			}
			
			if(current_Element instanceof com.lowagie.text.Phrase){
				com.lowagie.text.pdf.PdfPCell cell = new com.lowagie.text.pdf.PdfPCell((com.lowagie.text.Phrase)current_Element);
				cell.setBorder(_sys_border);
				cell.setHorizontalAlignment(_sys_align);
				((List)content_Element).add(cell);
				return;
			}
			if(current_Element instanceof com.lowagie.text.Image){
				com.lowagie.text.pdf.PdfPCell cell = new com.lowagie.text.pdf.PdfPCell((com.lowagie.text.Image)current_Element);
				cell.setBorder(_sys_border);
				cell.setHorizontalAlignment(_sys_align);
				((List)content_Element).add(cell);
				return;
			}
		}
				
		if(content_Element instanceof com.lowagie.text.pdf.PdfPTable){
			if(current_Element instanceof com.lowagie.text.pdf.PdfPTable){
				com.lowagie.text.pdf.PdfPCell cell = new com.lowagie.text.pdf.PdfPCell((com.lowagie.text.pdf.PdfPTable)current_Element);
				cell.setBorder(_sys_border);
				cell.setHorizontalAlignment(_sys_align);
				((com.lowagie.text.pdf.PdfPTable)content_Element).addCell(cell);
				return;
			}
			if(current_Element instanceof com.lowagie.text.pdf.PdfPCell){
				((com.lowagie.text.pdf.PdfPTable)content_Element).addCell((com.lowagie.text.pdf.PdfPCell)current_Element);
				return;
			}
			if(current_Element instanceof com.lowagie.text.pdf.PdfPRow){
				((com.lowagie.text.pdf.PdfPTable)content_Element).getRows().add(((com.lowagie.text.pdf.PdfPRow)current_Element));
				return;
			}			
			if(current_Element instanceof com.lowagie.text.Phrase){
				com.lowagie.text.pdf.PdfPCell cell = new com.lowagie.text.pdf.PdfPCell((com.lowagie.text.Phrase)current_Element);
				cell.setBorder(_sys_border);
				cell.setHorizontalAlignment(_sys_align);
				((com.lowagie.text.pdf.PdfPTable)content_Element).addCell(cell);
				return;
			}
			if(current_Element instanceof com.lowagie.text.Image){
				com.lowagie.text.pdf.PdfPCell cell = new com.lowagie.text.pdf.PdfPCell((com.lowagie.text.Image)current_Element);
				cell.setBorder(_sys_border);
				cell.setHorizontalAlignment(_sys_align);
				((com.lowagie.text.pdf.PdfPTable)content_Element).addCell(cell);
				return;
			}
		}
		if(content_Element instanceof com.lowagie.text.Document){
			if(current_Element instanceof com.lowagie.text.pdf.PdfPTable){
				((com.lowagie.text.Document)content_Element).add((com.lowagie.text.Element)current_Element);
				return;
			}
			if(current_Element instanceof bean){
				if(	((bean)current_Element).getID().equals("PageHeader_")){
					((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_initProcess)).setContent(new Boolean(true));
						Vector buf = ((java.util.Vector)((bean)current_Element).getContent());
						for(int i=0;i<buf.size();i++){
							boolean added=false;
							if(buf.get(i) instanceof text){
								((text)buf.get(i)).drawDirect(_beanLibrary);
								added=true; 
							}


							if(!added) ((com.lowagie.text.Document)content_Element).add((com.lowagie.text.Element)buf.elementAt(i));
						}	

					((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_initProcess)).setContent(new Boolean(false));

					return;
				}
				if(	((bean)current_Element).getID().equals("PageFooter_")){
					((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_initProcess)).setContent(new Boolean(true));
						Vector buf = ((java.util.Vector)((bean)current_Element).getContent());
					for(int i=0;i<buf.size();i++){
						boolean added=false;
						if(buf.get(i) instanceof text){
							((text)buf.get(i)).drawDirect(_beanLibrary);
							added=true; 
						}


						if(!added) ((com.lowagie.text.Document)content_Element).add((com.lowagie.text.Element)buf.elementAt(i));
					}	
					((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_initProcess)).setContent(new Boolean(false));

					return;
				}
			}
			
			if(current_Element instanceof text){
				((text)current_Element).drawDirect(_beanLibrary);
				return; 
			}
	
			
			
			if(current_Element instanceof com.lowagie.text.pdf.PdfPCell){
				com.lowagie.text.pdf.PdfPTable	_table = new com.lowagie.text.pdf.PdfPTable(1);
				_table.setHorizontalAlignment(50);
				_table.setWidthPercentage(100);
				_table.addCell((com.lowagie.text.pdf.PdfPCell)current_Element);
				((com.lowagie.text.Document)content_Element).add((com.lowagie.text.Element)_table);
				return;
			}
/*			
			if(current_Element instanceof com.lowagie.text.Watermark){
				((com.lowagie.text.Document)content_Element).add((com.lowagie.text.Watermark)current_Element);
				return;
			}
*/			
			if(current_Element instanceof com.lowagie.text.Image){
				((com.lowagie.text.Document)content_Element).add((com.lowagie.text.Image)current_Element);
				return;
			}

			
			((com.lowagie.text.Document)content_Element).add((com.lowagie.text.Element)current_Element);
			return;
		}
		if(content_Element instanceof com.lowagie.text.Paragraph){
//			if(current_Element instanceof com.lowagie.text.Watermark)
//				((com.lowagie.text.Paragraph)content_Element).add((com.lowagie.text.Watermark)current_Element);
//			else 
				((com.lowagie.text.Paragraph)content_Element).add((com.lowagie.text.Element)current_Element);
			return;
		}
	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}

}
public void reimposta(){
	name = "ELEMENT";
}
public static String adaptAttrName(String value){
	if(value==null) return "";
	String result="";
	if(value.length()>1) result=value.substring(0,1).toUpperCase()+value.substring(1).toLowerCase();
	else result = value;
	return result;
}
private String split(String frase,int l){
	String result="";
	int length = frase.length()/l;
	int length_curr=0;
	for(int i=0;i<frase.length();i++){
		result+=frase.charAt(i);
		if(length_curr==length){
			length_curr=0;
			result+=_separator();
		}else length_curr++;
	}
	return result;
}
}
