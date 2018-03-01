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


import java.util.Hashtable;

import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.lib.report_element_base;
import neohort.universal.output.lib.style;

import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.Barcode;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.Barcode39;
import com.itextpdf.text.pdf.BarcodeCodabar;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.BarcodeInter25;
import com.itextpdf.text.pdf.BarcodePostnet;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;

public class barcode extends element{
	private static final long serialVersionUID = -2506449791104304361L;
	private PdfPCell cell;
	private String BARCODE_TYPE;
	private String CODE;
	private String BARCODE_HEIGHT;

public barcode() {
	super();
}
public void drawCanvas(Hashtable _tagLibrary, Hashtable _beanLibrary) {
	initCanvas(_tagLibrary,_beanLibrary);
}
public void executeFirst(Hashtable _tagLibrary, Hashtable _beanLibrary){
}
public void executeLast(Hashtable _tagLibrary, Hashtable _beanLibrary){
	try{
		if(getBARCODE_TYPE().equals("") || getCODE().equals("")) return;

		PdfWriter pWriter = (PdfWriter)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Writer)).getContent());
		PdfContentByte cb = pWriter.getDirectContent();

			int _align = getField_Int(new PdfPCell(new Phrase("")).getClass(),"ALIGN_"+internal_style.getALIGN(),0);
			int border = 0;
			try{
				border = Integer.valueOf(internal_style.getBORDER()).intValue();
			}catch(Exception e){}
			float padding = 0;
			try{
				padding = Float.valueOf(internal_style.getPADDING()).floatValue();
			}catch(Exception e){}

			com.itextpdf.text.Image chartIm;

			Barcode b_Code = null;

			if(getBARCODE_TYPE().trim().equalsIgnoreCase("CODABAR")){
            	b_Code = new BarcodeCodabar();
            	b_Code.setCodeType(Barcode.CODABAR);
            	b_Code.setStartStopText(false);
			}
			if(getBARCODE_TYPE().trim().equalsIgnoreCase("39")){
            	b_Code = new Barcode39();
            	b_Code.setStartStopText(false);
			}
			if(getBARCODE_TYPE().trim().equalsIgnoreCase("39_EXTENDED")){
	        	b_Code = new Barcode39();
  		       	b_Code.setStartStopText(false);
            	b_Code.setExtended(true);
			}
			if(getBARCODE_TYPE().trim().equalsIgnoreCase("128")){
            	b_Code = new Barcode128();
			}
			if(getBARCODE_TYPE().trim().equalsIgnoreCase("128_RAW")){
            	b_Code = new Barcode128();
            	b_Code.setCodeType(Barcode.CODE128_RAW);
			}
			if(getBARCODE_TYPE().trim().equalsIgnoreCase("128_UCC")){
            	b_Code = new Barcode128();
            	b_Code.setCodeType(Barcode.CODE128_UCC);
			}
			if(getBARCODE_TYPE().trim().equalsIgnoreCase("EAN13")){
            	b_Code = new BarcodeEAN();
            	b_Code.setCodeType(Barcode.EAN13);
			}
			if(getBARCODE_TYPE().trim().equalsIgnoreCase("EAN8")){
            	b_Code = new BarcodeEAN();
            	b_Code.setCodeType(Barcode.EAN8);
			}
			if(getBARCODE_TYPE().trim().equalsIgnoreCase("INTERLEAVED")){
	            b_Code = new BarcodeInter25();
 	           	b_Code.setGenerateChecksum(true);
			}
			if(getBARCODE_TYPE().trim().equalsIgnoreCase("POSTNET")){
            	b_Code = new BarcodePostnet();
			}
			if(getBARCODE_TYPE().trim().equalsIgnoreCase("PLANET")){
           		b_Code = new BarcodePostnet();
            	b_Code.setCodeType(Barcode.PLANET);
			}
			if(b_Code==null) return;
			b_Code.setCode(getCODE());

			try{
				int _f_size = Integer.valueOf(internal_style.getFONT_SIZE()).intValue();
				b_Code.setSize(_f_size);
			}catch(Exception e){}
			try{
				int _f_height = Integer.valueOf(getBARCODE_HEIGHT()).intValue();
				b_Code.setBarHeight(_f_height);
			}catch(Exception e){}

			if(!internal_style.getTEXT_ALIGN_H().equals(""))
				b_Code.setTextAlignment(getField_Int(new PdfPCell(new Phrase("")).getClass(),"ALIGN_"+internal_style.getTEXT_ALIGN_H(),0));
			try{
				int _t_align_v = Integer.valueOf(internal_style.getTEXT_ALIGN_V()).intValue();
				b_Code.setBaseline(_t_align_v);
			}catch(Exception e){}

			chartIm = b_Code.createImageWithBarcode(cb, getField_Color(internal_style.getBAR_COLOR()), getField_Color(internal_style.getFONT_COLOR()));



			if(chartIm==null) return;


			int _d_h = 0;
			int _d_v = 0;
			try{
				_d_h = Integer.valueOf(internal_style.getDIMENTION_H()).intValue();
				_d_v = Integer.valueOf(internal_style.getDIMENTION_V()).intValue();
			}catch(Exception e){
				_d_h = 0;
				_d_v = 0;
			}

			if(_d_h>0 && _d_v>0){
				try{
					chartIm.scaleAbsolute(_d_h,_d_v);
				}catch(Exception e){
					e.toString();
				}
			}

			if(chartIm!=null && !internal_style.getABSOLUTE_X().equals("") && !internal_style.getABSOLUTE_Y().equals("")){
				try{
					chartIm.setAbsolutePosition(new Float(internal_style.getABSOLUTE_X()).floatValue(),new Float(internal_style.getABSOLUTE_Y()).floatValue());
					((java.util.Vector)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas)).getContent())).add(cell);

					if(_tagLibrary.get(getName()+":"+getID())==null)
						_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
					else _tagLibrary.remove(getName()+":"+getID());
					return;
				}catch(Exception e){
				}
			}


			cell = new PdfPCell(chartIm);
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
	setName("BARCODE");
	BARCODE_TYPE = "";
	CODE = "";
	STYLE_ID = "";
	BARCODE_HEIGHT = "";
}
public void reStyle(style style_new) {
	if(internal_style==null) return;
	internal_style.reStyle(style_new);
}
public String getBARCODE_TYPE() {
	return BARCODE_TYPE;
}
public String getCODE() {
	return CODE;
}
public void setBARCODE_TYPE(String string) {
	BARCODE_TYPE = string;
}
public void setCODE(String string) {
	CODE = string;
}
public String getBARCODE_HEIGHT() {
	return BARCODE_HEIGHT;
}
public void setBARCODE_HEIGHT(String string) {
	BARCODE_HEIGHT = string;
}
}
