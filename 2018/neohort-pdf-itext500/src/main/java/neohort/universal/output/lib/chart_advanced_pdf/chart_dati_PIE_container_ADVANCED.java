/**
 * Creation date: (14/12/2005)
 * @author: Svyatoslav Urbanovych surban@bigmir.net  svyatoslav.urbanovych@gmail.com
 */
package neohort.universal.output.lib.chart_advanced_pdf;

import java.util.ArrayList;

import com.itextpdf.text.BaseColor;

public class chart_dati_PIE_container_ADVANCED {
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
	private float Xcontrol;
	private float Ycontrol;

	private float XcontrolL = -1;
	private float YcontrolL = -1;
	private float XcontrolR = -1;
	private float YcontrolR = -1;
		
	private float Alfalabel;
	private BaseColor colorTop = new BaseColor(BaseColor.LIGHT_GRAY.getRGB());
	private BaseColor colorSh = new BaseColor(BaseColor.LIGHT_GRAY.getRGB());
	private java.lang.String labelPerc="";
	public ArrayList<float[]> points;
	private int id = -1;
	private float MIN_DRW = 0;
public chart_dati_PIE_container_ADVANCED() {
	super();
}
public float getAlfalabel() {
	return Alfalabel;
}
public BaseColor getColorSh() {
	return colorSh;
}
public BaseColor getColorTop() {
	return colorTop;
}
public int getId() {
	return id;
}
public java.lang.String getLabel() {
	return label;
}
public java.lang.String getLabelPerc() {
	return labelPerc;
}
/**
 * Insert the method's description here.
 * Creation date: (06/08/2003 15.24.13)
 * @return float
 */
public float getMIN_DRW() {
	return MIN_DRW;
}
public ArrayList<float[]> getPoints() {
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
public float getXcontrol() {
	return Xcontrol;
}
public float getXcontrolL() {
	return XcontrolL;
}
public float getXcontrolR() {
	return XcontrolR;
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
public float getYcontrol() {
	return Ycontrol;
}
public float getYcontrolL() {
	return YcontrolL;
}
public float getYcontrolR() {
	return YcontrolR;
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
public void setColorSh(BaseColor newColorSh) {
	colorSh = newColorSh;
}
public void setColorTop(BaseColor newColorTop) {
	colorTop = newColorTop;
}
public void setId(int newId) {
	id = newId;
}
public void setLabel(java.lang.String newLabel) {
	label = newLabel;
}
public void setLabelPerc(java.lang.String newLabelPerc) {
	labelPerc = newLabelPerc;
}
/**
 * Insert the method's description here.
 * Creation date: (06/08/2003 15.24.13)
 * @param newMIN_DRW float
 */
public void setMIN_DRW(float newMIN_DRW) {
	MIN_DRW = newMIN_DRW;
}
public void setPoints(ArrayList<float[]> newPoints) {
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
public void setXcontrol(float newXlabel) {
	Xcontrol = newXlabel;
}
public void setXcontrolL(float newXlabel) {
	XcontrolL = newXlabel;
}
public void setXcontrolR(float newXlabel) {
	XcontrolR = newXlabel;
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
public void setYcontrol(float newYlabel) {
	Ycontrol = newYlabel;
}
public void setYcontrolL(float newYlabel) {
	YcontrolL = newYlabel;
}
public void setYcontrolR(float newYlabel) {
	YcontrolR = newYlabel;
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
