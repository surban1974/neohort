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

package neohort.universal.output.lib.chart_pdf;

import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

public class chart_element {
public chart_element() {
	super();
}
public Image chartFactory(String type, PdfWriter pWriter) {
	try{
		PdfContentByte cb = pWriter.getDirectContent();
		return createImageWithBarcode(cb);
	}catch(Exception e){
//		setError(e);
	}
	
	return null;
}
    private Image createImageWithBarcode(PdfContentByte cb) {
        try {
            return Image.getInstance(createTemplateWithBarcode(cb));
        }
        catch (Exception e) {
            throw new ExceptionConverter(e);
        }
    }
private PdfTemplate createTemplateWithBarcode(PdfContentByte cb) {
	PdfTemplate tp = cb.createTemplate(0, 0);
    Rectangle rect = placeBarcode(tp);
    tp.setBoundingBox(rect);
    return tp;
}
    private Rectangle placeBarcode(PdfContentByte cb) {
	try{ 
        cb.setColorFill(java.awt.Color.blue);
        boolean print = true;
        for(int i=0;i<15;i++){
	        if(print){
		        cb.setColorFill(java.awt.Color.pink);
        		cb.rectangle(21+i*5, 17, 5, 10+i*5);
        		cb.fill();
		        
		        cb.setColorFill(java.awt.Color.blue);
        		cb.rectangle(20+i*5, 16, 5, 10+i*5);
        		cb.fill();
	        }	
        	print=!print;	
        }
        cb.fill();
        cb.setColorFill(java.awt.Color.black);
        cb.rectangle(15,14,100,1); 
        cb.rectangle(15,14,1,100);
        cb.fill();
        
            cb.beginText();
            cb.setFontAndSize(BaseFont.createFont("Helvetica", "winansi", false), 8);
            cb.setTextMatrix(30, 4);  
            cb.showText("Tempo, t");
            cb.endText();
       
            cb.beginText();
            cb.setFontAndSize(BaseFont.createFont("Helvetica", "winansi", false), 8);
            cb.setTextMatrix(0, 0);  
            cb.showTextAligned(0,"Sconfinamenti, euro",10,50,90);
            cb.endText();
        
        return new Rectangle(100, 100);
	}catch(Exception e){}
	return null;
    }
}
