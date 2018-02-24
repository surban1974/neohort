/**
* Creation date: (24/01/2006)
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
import java.util.StringTokenizer;
import java.util.Vector;

import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.lib.report_element_base;
import neohort.universal.output.lib.style;

import com.lowagie.text.pdf.PdfPTable;

public class table_row extends element{
	private static final long serialVersionUID = -532623496974995818L;
	PdfPTable table;
public table_row() {
	super();
}
public void executeFirst(Hashtable _tagLibrary, Hashtable _beanLibrary){
	table parentT = null;
	report_element_base parentC = (report_element_base)getParent();
	while (parentC!=null && !parentC.getName().equalsIgnoreCase("TABLE"))
		parentC=(report_element_base)parentC.getParent();
	parentT = (table)parentC;
	try{
		int col=1;
		try{
			col = Integer.valueOf(parentT.getCOL()).intValue();
		}catch(Exception e){}
		table = new com.lowagie.text.pdf.PdfPTable(col);
		
		if(	parentT.internal_style!=null && 
			(!parentT.internal_style.getALIGN().equals("") || !parentT.internal_style.getTEXT_ALIGN_H().equals(""))
			){
				if(!parentT.internal_style.getALIGN().equals("")){	
					try{
						int align_h = getField_Int(table.getClass(),"ALIGN_"+parentT.internal_style.getALIGN(),50);				
						table.setHorizontalAlignment(align_h);
					}catch(Exception e){
						table.setHorizontalAlignment(50);
					}
				}	
				if(!parentT.internal_style.getTEXT_ALIGN_H().equals("")){	
					try{
						int align_h = getField_Int(table.getClass(),"ALIGN_"+parentT.internal_style.getTEXT_ALIGN_H(),50);				
						table.setHorizontalAlignment(align_h);
					}catch(Exception e){
						table.setHorizontalAlignment(50);
					}
				}	
		}else table.setHorizontalAlignment(50);

		if(parentT.internal_style!=null && !parentT.internal_style.getWIDTH().equals("")){
			try{	
				if(parentT.internal_style.getWIDTH().indexOf("%")>-1)
					table.setWidthPercentage(new Float(replace(parentT.internal_style.getWIDTH(),"%","")).floatValue());
				else{
					table.setTotalWidth(new Float(parentT.internal_style.getWIDTH()).floatValue());
					table.setLockedWidth(true); 
				}
							
			}catch(Exception e){
				table.setWidthPercentage(100);
			}
		}else table.setWidthPercentage(100);	

		StringTokenizer st = new StringTokenizer(replace(parentT.getCOLLS_DIMENTION(),"%",""), ",");
		Vector width = new Vector();
		float delta = 100/col;
		for(int i=0;i<col;i++) width.add(new Float(delta).toString());
		int k=0;
		while (st.hasMoreTokens()){
			try{
				width.set(k,st.nextToken());
				k++;
			}catch(Exception e){	 
			}
		}
	
		float[] dim = new float[width.size()];
		for (int i=0;i<width.size();i++) dim[i]=Float.valueOf((String)width.elementAt(i)).floatValue();
		table.setWidths(dim);


	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}
}
public void executeLast(Hashtable _tagLibrary, Hashtable _beanLibrary){	
	try{
		if(_tagLibrary.get(getName()+":"+getID())==null)
			_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
		else _tagLibrary.remove(getName()+":"+getID());

	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}

}
public void reimposta() {
	setName("TABLE_ROW");
	STYLE_ID= "";
}
public void setCanvas(Hashtable _tagLibrary, Hashtable _beanLibrary) {
	try{
		((java.util.Vector)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Canvas)).getContent())).addElement(table);
	}catch(Exception e){
		setError(e,iStub.log_ERROR);
	} 

}
public void drawCanvas(Hashtable _tagLibrary, Hashtable _beanLibrary) {
	initCanvas(_tagLibrary,_beanLibrary);
}
public void reStyle(style style_new) {
	if(internal_style==null) return;
	internal_style.reStyle(style_new);
}
}
