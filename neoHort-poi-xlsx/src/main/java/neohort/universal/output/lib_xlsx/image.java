/**
* Creation date: (22/12/2005)
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

package neohort.universal.output.lib_xlsx;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URL;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import neohort.log.stubs.iStub;
import neohort.universal.output.iConst;
import neohort.universal.output.lib.bean;
import neohort.universal.output.lib.report_element_base;
import neohort.universal.output.lib.style;
import neohort.util.util_reflect;
import neohort.util.util_web;

public class image extends element{
	private static final long serialVersionUID = 3997198916703577511L;
	private String IMAGE_SOURCE;
	private String IMAGE_LOADER;
	private String path;

	private static final double CELL_DEFAULT_HEIGHT = 17;
	private static final double CELL_DEFAULT_WIDTH = 64;
	
public image() {
	super();
}
public void drawCanvas(Hashtable _tagLibrary, Hashtable _beanLibrary) {
	Boolean template = null;
	try{
		template = (Boolean)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Excel_template_present)).getContent());
	}catch(Exception e){	
	}
	if(template!=null && template.booleanValue()==false)
		initCanvas(_tagLibrary,_beanLibrary);
//	if(path!=null && path.toUpperCase().indexOf(".PNG")>-1)
		
}
public void executeFirst(Hashtable _tagLibrary, Hashtable _beanLibrary){
}
public void executeLast(Hashtable _tagLibrary, Hashtable _beanLibrary){

	try{
		bean _sysPdfCC = (bean)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_CurrentCELL);
		bean _sysPdfCR = (bean)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_CurrentROW);
		Boolean template = null;
		try{
			template = (Boolean)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Excel_template_present)).getContent());
		}catch(Exception e){	
		}

		
		int X = 0;
		int Y = 0;
		try{
			X = ((Integer)_sysPdfCC.getContent()).intValue();
		}catch(Exception e){
		}
		try{
			Y = ((Integer)_sysPdfCR.getContent()).intValue();
		}catch(Exception e){
		}

		byte[] imgBytes = null;

		if(!IMAGE_LOADER.equals("")){
			Object loader = null;
			try{
				loader = Class.forName(IMAGE_LOADER).newInstance();
				util_reflect.setValue(loader, "setProperty", new Object[]{IMAGE_SOURCE});
				imgBytes = (byte[])util_reflect.getValue(loader,"getBytes",null);
				
			}catch(Exception e){

				setError(e,iStub.log_ERROR);
			}
		}		

		
		if(imgBytes==null){
			try{
				path = util_web.adaptPath(getIMAGE_SOURCE(), _beanLibrary);
			}catch(Exception e){
				path=null;
			}catch(Throwable e){
				path=null;
			}
			if(path==null) path = getIMAGE_SOURCE();
	
		
			BufferedImage input = null;

			if(template!=null && template.booleanValue()==false){
				try{
					input = ImageIO.read(new File(path));
				}catch(Exception e){
					setError(e,iStub.log_WARN);
				}
				if(input==null){
					try{
						input = ImageIO.read(new URL(path));
					}catch(Exception e){
						setError(e,iStub.log_ERROR);
					}
				}


				if(input!=null){
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ImageIO.write(input, "PNG", baos);
					imgBytes=baos.toByteArray();
				}
			}
			if(imgBytes!=null){
				Workbook workbook = (Workbook)((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Workbook)).getContent();
				Sheet document = (Sheet)(((report_element_base)_beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Document)).getContent());

				int pictureIdx = workbook.addPicture(imgBytes, Workbook.PICTURE_TYPE_PNG);
				CreationHelper helper = workbook.getCreationHelper();
				Drawing drawing = document.createDrawingPatriarch();
				ClientAnchor anchor = helper.createClientAnchor();
				anchor.setCol1(X);
				anchor.setRow1(Y);
				Picture pict = drawing.createPicture(anchor, pictureIdx);
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
				if(_d_h==0 && _d_v==0)
					pict.resize();
				else
					pict.resize(_d_v,_d_h);

							
			}
			
			if(input==null) path=null;
		}
		X++;
		
		
		_sysPdfCR.setContent(new Integer(Y));
		_beanLibrary.put(_sysPdfCR.getName()+":"+_sysPdfCR.getID(),_sysPdfCR); 

		_sysPdfCC.setContent(new Integer(X));
		_beanLibrary.put(_sysPdfCC.getName()+":"+_sysPdfCC.getID(),_sysPdfCC);

		if(_tagLibrary.get(getName()+":"+getID())==null)
			_tagLibrary.remove(getName()+":"+getID()+"_ids_"+this.motore.hashCode());
		else _tagLibrary.remove(getName()+":"+getID());


	}catch(Exception e){
		setError(e,iStub.log_WARN);
	}
}

public void reStyle(style style_new) {
	if(internal_style==null) return;
	internal_style.reStyle(style_new);
}

public boolean refreshText() {
	return true;
}
public void reimposta() {
	setName("IMAGE");
	STYLE_ID="";
	IMAGE_SOURCE="";
	IMAGE_LOADER="";
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
public void setIMAGE_LOADER(String iMAGE_LOADER) {
	IMAGE_LOADER = iMAGE_LOADER;
}

//****************Image tool*****************************
/*
public static BufferedImage toBufferedImage(Image image) {
	if (image instanceof BufferedImage) {
		return (BufferedImage)image;
	}
    
	image = new ImageIcon(image).getImage();
	boolean hasAlpha = hasAlpha(image);

	BufferedImage bimage = null;
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	try {
		int transparency = Transparency.OPAQUE;
		if (hasAlpha) {
			transparency = Transparency.BITMASK;
		}
		GraphicsDevice gs = ge.getDefaultScreenDevice();
		GraphicsConfiguration gc = gs.getDefaultConfiguration();
		bimage = gc.createCompatibleImage(
		image.getWidth(null), image.getHeight(null), transparency);
	} catch (Exception e) {
	}
    
	if (bimage == null) {
		int type = BufferedImage.TYPE_INT_RGB;
		if (hasAlpha) {
			type = BufferedImage.TYPE_INT_ARGB;
		}
		bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
	}
 
	Graphics g = bimage.createGraphics();
    
	g.drawImage(image, 0, 0, null);
	g.dispose();
    
	return bimage;
}

public static boolean hasAlpha(Image image) {
	if (image instanceof BufferedImage) {
		BufferedImage bimage = (BufferedImage)image;
		return bimage.getColorModel().hasAlpha();
	}
	PixelGrabber pg = new PixelGrabber(image, 0, 0, 1, 1, false);
	try {
		pg.grabPixels();
	} catch (InterruptedException e) {
	}
	ColorModel cm = pg.getColorModel();
	return cm.hasAlpha();
}
*/
}
