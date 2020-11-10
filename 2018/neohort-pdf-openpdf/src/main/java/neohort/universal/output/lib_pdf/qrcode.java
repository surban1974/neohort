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

import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.Hashtable;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfWriter;

import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.lib.report_element_base;
import neohort.universal.output.lib.style;

public class qrcode extends element{
	private static final long serialVersionUID = -1L;
	private PdfPCell cell;
	private String CODE;
	private String CODE_SIZE;
	private String CODE_ERROR_CORRECTION_LEVEL;
	private String IMAGE_TYPE;

public qrcode() {
	super();
}
public void drawCanvas(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary) {
	initCanvas(_tagLibrary,_beanLibrary);
}
public void executeFirst(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
}
public void executeLast(Hashtable<String, report_element_base> _tagLibrary, Hashtable<String, report_element_base> _beanLibrary){
	try{
		if(getCODE().equals("")) return;

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
			int desiredQrCodeSize = 0;
			try{
				desiredQrCodeSize = Integer.valueOf(getCODE_SIZE()).intValue();
			}catch(Exception e){}
			
			
			
			@SuppressWarnings({ "unchecked", "rawtypes" })
			Map<EncodeHintType, Object> hints = new EnumMap(EncodeHintType.class);
			hints.put(EncodeHintType.ERROR_CORRECTION, CODE_ERROR_CORRECTION_LEVEL);
			hints.put(EncodeHintType.MARGIN, 0);
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			
			final QRCode qrCode = Encoder.encode(
					getCODE(),
					ErrorCorrectionLevel.valueOf(CODE_ERROR_CORRECTION_LEVEL), hints);
			int minimalQrCodeSize = qrCode.getMatrix().getWidth();
			int optimalQrCodeRenderSize = minimalQrCodeSize;
			if (minimalQrCodeSize < desiredQrCodeSize) 
				optimalQrCodeRenderSize = minimalQrCodeSize * Math.floorDiv(desiredQrCodeSize, minimalQrCodeSize);
	        
			
	        final QRCodeWriter qrCodeWriter = new QRCodeWriter();
	        final BitMatrix bitMatrix = qrCodeWriter.encode(getCODE(), BarcodeFormat.QR_CODE, optimalQrCodeRenderSize, optimalQrCodeRenderSize, hints);
	        
	        final MatrixToImageConfig config = new MatrixToImageConfig(MatrixToImageConfig.BLACK, MatrixToImageConfig.WHITE);

	        final BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix, config);

	        final Image chartIm = Image.getInstance(qrImage , null); 
	        

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
					_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas).add2content(cell);

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

		_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas).add2content(cell);

		if(_tagLibrary.get(getName()+":"+getID())==null)
			_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
		else _tagLibrary.remove(getName()+":"+getID());

	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}
}
public void reimposta() {
	setName("QRCODE");
	CODE = "";
	STYLE_ID = "";
	CODE_SIZE = "";
	CODE_ERROR_CORRECTION_LEVEL = "M";
	IMAGE_TYPE = "";
}
public void reStyle(style style_new) {
	if(internal_style==null) return;
	internal_style.reStyle(style_new);
}
public String getCODE() {
	return CODE;
}
public void setCODE(String string) {
	CODE = string;
}
public String getCODE_SIZE() {
	return CODE_SIZE;
}
public void setCODE_SIZE(String cODE_SIZE) {
	CODE_SIZE = cODE_SIZE;
}
public String getCODE_ERROR_CORRECTION_LEVEL() {
	return CODE_ERROR_CORRECTION_LEVEL;
}
public void setCODE_ERROR_CORRECTION_LEVEL(String cODE_ERROR_CORRECTION_LEVEL) {
	CODE_ERROR_CORRECTION_LEVEL = cODE_ERROR_CORRECTION_LEVEL;
}
public String getIMAGE_TYPE() {
	return IMAGE_TYPE;
}
public void setIMAGE_TYPE(String iMAGE_TYPE) {
	IMAGE_TYPE = iMAGE_TYPE;
}

}
