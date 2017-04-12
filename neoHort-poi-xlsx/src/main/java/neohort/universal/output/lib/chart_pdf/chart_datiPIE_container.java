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

public class chart_datiPIE_container {
	private float X0;
	private float X1;
	private float Y0;
	private float Y1;
	private float Xcenter;
	private float Ycenter;
	private float Xpoint1;
	private float Xpoint2;
	private float Ypoint1;
	private float Ypoint2;
	private java.lang.String label="";
	private float Xlabel;
	private float Ylabel;
	private float Alfalabel;
	private java.awt.Color colorTop = java.awt.Color.lightGray;
	private java.awt.Color colorSh = java.awt.Color.lightGray;
	private java.lang.String labelPerc="";
	public java.util.ArrayList points;

public chart_datiPIE_container() {
	super();
}
public float getAlfalabel() {
	return Alfalabel;
}
public java.awt.Color getColorSh() {
	return colorSh;
}
public java.awt.Color getColorTop() {
	return colorTop;
}
public java.lang.String getLabel() {
	return label;
}
public java.lang.String getLabelPerc() {
	return labelPerc;
}
public java.util.ArrayList getPoints() {
	return points;
}
public float getX0() {
	return X0;
}
public float getX1() {
	return X1;
}
public float getXcenter() {
	return Xcenter;
}
public float getXlabel() {
	return Xlabel;
}
public float getXpoint1() {
	return Xpoint1;
}
public float getXpoint2() {
	return Xpoint2;
}
public float getY0() {
	return Y0;
}
public float getY1() {
	return Y1;
}
public float getYcenter() {
	return Ycenter;
}
public float getYlabel() {
	return Ylabel;
}
public float getYpoint1() {
	return Ypoint1;
}
public float getYpoint2() {
	return Ypoint2;
}
public void setAlfalabel(float newAlfalabel) {
	Alfalabel = newAlfalabel;
}
public void setColorSh(java.awt.Color newColorSh) {
	colorSh = newColorSh;
}
public void setColorTop(java.awt.Color newColorTop) {
	colorTop = newColorTop;
}
public void setLabel(java.lang.String newLabel) {
	label = newLabel;
}
public void setLabelPerc(java.lang.String newLabelPerc) {
	labelPerc = newLabelPerc;
}
public void setPoints(java.util.ArrayList newPoints) {
	points = newPoints;
}
public void setX0(float newX0) {
	X0 = newX0;
}
public void setX1(float newX1) {
	X1 = newX1;
}
public void setXcenter(float newXcener) {
	Xcenter = newXcener;
}
public void setXlabel(float newXlabel) {
	Xlabel = newXlabel;
}
public void setXpoint1(float newXpoint1) {
	Xpoint1 = newXpoint1;
}
public void setXpoint2(float newXpoint2) {
	Xpoint2 = newXpoint2;
}
public void setY0(float newY0) {
	Y0 = newY0;
}
public void setY1(float newY1) {
	Y1 = newY1;
}
public void setYcenter(float newYcenter) {
	Ycenter = newYcenter;
}
public void setYlabel(float newYlabel) {
	Ylabel = newYlabel;
}
public void setYpoint1(float newYpoint1) {
	Ypoint1 = newYpoint1;
}
public void setYpoint2(float newYpoint2) {
	Ypoint2 = newYpoint2;
}
}
