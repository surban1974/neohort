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

import java.util.HashMap;
import java.util.Vector;

import neohort.universal.output.util.I_OutputRunTime;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.PdfContentByte;

public interface I_chart_content {

    static public int or_CENTER = 0;
    static public int or_BOTTOM = 1;
    static public int or_TOP = 2;
    static public int or_LEFT = 3;
    static public int or_RIGHT = 4;

    static public float space_0 = 2;

public void ActionAfter(PdfContentByte cb, I_chart_content ch_contentT,I_chart_content ch_contentB,I_chart_content ch_contentL,I_chart_content ch_contentR,float _h_d,float _w_d);
public I_chart_dati ChartDatiFactory();
public BaseColor getBackground();
public I_chart_dati getDati();
public BaseColor getElement_color_1();
public BaseColor getElement_color_2();
public BaseColor getElement_color_3();
public String getExternal();
public float getHeight();
public java.lang.String getLabel();
public java.lang.String getLabel_align();
public BaseColor getLabel_color();
public com.itextpdf.text.pdf.BaseFont getLabel_font();
public int getLabel_fontsize();
public float getLabel_gr();
public int getOrientation();
public Vector<Object> getScale();
public BaseColor getScale_color();
public com.itextpdf.text.pdf.BaseFont getScale_font();
public int getScale_fontsize();
public java.lang.String getScale_format();
public float getScale_gr();
public int getScale_max();
public Vector<Object> getValueXY();
public float getWidth();
public float getX();
public float getY();
public boolean isShow_scale();
public PdfContentByte placeBarcode(PdfContentByte cb, boolean paint);
public void positionBody(PdfContentByte cb, I_chart_content ch_contentT,I_chart_content ch_contentB,I_chart_content ch_contentL,I_chart_content ch_contentR,float _h_d,float _w_d);
public void reimposta();
public void setBackground(BaseColor newBackground);
public void setDati(I_chart_dati newDati);
public void setElement_color_1(BaseColor newElement_color_1);
public void setElement_color_2(BaseColor newElement_color_2);
public void setElement_color_3(BaseColor newElement_color_3);
public void setExternal(String newExternal);
public void setHeight(float newHeight);
public void setLabel(java.lang.String newLabel);
public void setLabel_align(java.lang.String newLabel_align);
public void setLabel_color(BaseColor newLabel_color);
public void setLabel_font(com.itextpdf.text.pdf.BaseFont newLabel_font);
public void setLabel_fontsize(int newLabel_fontsize);
public void setLabel_gr(float newLabel_gr);
public void setOrientation(int newOrientation);
public void setScale(Vector<Object> newScale);
public void setScale_color(BaseColor newScale_color);
public void setScale_font(com.itextpdf.text.pdf.BaseFont newScale_font);
public void setScale_fontsize(int newScale_fontsize);
public void setScale_format(java.lang.String newScale_format);
public void setScale_gr(float newScale_gr);
public void setScale_max(int newScale_max);
public void setShow_scale(boolean newShow_scale);
public void setValueXY(Vector<Object> newValueXY);
public void setWidth(float newWidth);
public void setX(float newX);
public void setY(float newY);
public void setExternal_parameters(HashMap<String,Object> external_parameters);
public HashMap<String,Object> getExternal_parameters();
public void setMotore(I_OutputRunTime motore);
public void setIsError(boolean isError);
public void setError(Exception e, String level);


}
