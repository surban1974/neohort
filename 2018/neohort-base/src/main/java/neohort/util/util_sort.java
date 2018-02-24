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


package neohort.util;

import java.util.ListIterator;
import java.util.Vector;


public class util_sort {
public util_sort() {
	super();
}
private static void _clone_mergeSort(Object src[], Object dest[], int low, int high, Object index_src[], Object index[]) {
	int length = high - low;


	if (length <7) {

		for (int i=low; i<high; i++)
		for (int j=i; j>low && ((java.lang.Comparable)dest[j-1]).compareTo((java.lang.Comparable)dest[j])>0; j--){
			Object t = dest[j];
				dest[j] = dest[j-1];
				dest[j-1] = t;	
			Object ti = index[j];
				index[j] = index[j-1];
				index[j-1] = ti;	
		}    
		return;
	}
	

		int mid = (low + high)/2;
		_clone_mergeSort(dest, src, low, mid, index, index_src);
		_clone_mergeSort(dest, src, mid, high, index ,index_src);


		if (((java.lang.Comparable)src[mid-1]).compareTo((java.lang.Comparable)src[mid]) <= 0) {
		   System.arraycopy(src, low, dest, low, length);
		   System.arraycopy(index_src, low, index, low, length);
		   return;
		}


		
		for(int i = low, p = low, q = mid; i < high; i++) {
			if (q>=high || p<mid && ((java.lang.Comparable)src[p]).compareTo(src[q])<=0){
				dest[i] = src[p];
				index[i] = index_src[p];
				p++;
			}else{
				dest[i] = src[q];
				index[i] = index_src[q];
				q++;
			}	
				
		}
		
}
public Vector _clone_sort(Vector dati, Vector dati_cr) {
	Vector result = new Vector();
	Vector index = new Vector();
	for(int i=0;i<dati_cr.size();i++)
		index.add(new Integer(i));
	try{

		Object a[] = dati_cr.toArray();
		Object a_index[] = index.toArray();
			Object src_index[] = (Object[])a_index.clone();
			Object src[] = (Object[])a.clone();
			_clone_mergeSort(src, a, 0, a.length, src_index, a_index);
		ListIterator li = dati_cr.listIterator();
		ListIterator l_index = index.listIterator();
		for (int j=0; j<a.length; j++) {
			li.next();li.set(a[j]);
			l_index.next();l_index.set(a_index[j]);	    	
		}
	}catch(Exception e){
		e.toString();
	}

	for(int i=0;i<index.size();i++)
		result.add(dati.elementAt(((Integer)index.elementAt(i)).intValue()));
	return result;
}
private static void _clone_sortSelf(Vector dati, Vector dati_cr, int start, int finish) {
	if(	dati==null ||
		dati.size()==0 || 
		start<0 ||
		start>dati.size() ||
		finish<0 ||
		finish>dati.size() ||	
		start>finish ||
		dati_cr==null ||
		dati_cr.size()!=dati.size())
		return;
		
	Vector result = (Vector)dati.clone();
	Vector index = new Vector();
	for(int i=start;i<finish;i++)
		index.add(new Integer(i));
	try{

		Object a_buf[] = dati_cr.toArray();
		Object a[] = new Object[finish-start];
		for(int j=start;j<finish;j++)
			a[j-start]= a_buf[j];
		Object a_index[] = index.toArray();
			Object src_index[] = (Object[])a_index.clone();
			Object src[] = (Object[])a.clone();
			_clone_mergeSort(src, a, 0, a.length, src_index, a_index);
		ListIterator li = dati_cr.listIterator();	
		ListIterator l_index = index.listIterator();
		for (int j=0; j<finish; j++){
			if(j>=start){
				li.next();li.set(a[j-start]);
				l_index.next();l_index.set(a_index[j-start]);
			}else li.next();		
		}
	}catch(Exception e){
		e.toString();
	}
	
	for(int i=start;i<finish;i++)
		dati.setElementAt(result.elementAt(((Integer)index.elementAt(i-start)).intValue()),i);
	
}
private Object getObject(Object current, String method) {
	try{
		java.lang.reflect.Method mtd = current.getClass().getMethod(method,null);
		if(mtd==null) return null;
		else return mtd.invoke(current, null);
	}catch(Exception e){
		return null; 
	}	
}
public Vector sort(Vector dati, String[] cr_array) {
	String[] cr_ad = new String[cr_array.length];
	for(int i=0;i<cr_ad.length;i++) cr_ad[i]="A";
	return sort(dati,cr_array,cr_ad);
}
public Vector sort(Vector dati, String[] cr_array, String[] cr_ad) {
	if(dati==null || dati.size()<=1) return dati;
	dati = sort(dati,cr_array[0],cr_ad[0]);
	
	if(cr_array.length>1){
		String[] new_cr_array = new String[cr_array.length-1];
		String[] new_cr_ad = new String[cr_ad.length-1];
		for(int i=1;i<cr_array.length;i++){
			new_cr_array[i-1]=cr_array[i];
			new_cr_ad[i-1]=cr_ad[i];
		}
		String keyMethod = cr_array[0];
		Vector buf = new Vector();
		int k=0; 
		int kcur=0;
		Object keyObject = getObject(dati.get(kcur),"get"+keyMethod);		
		while(kcur<dati.size()){
			while(kcur<dati.size() && keyObject!=null && getObject(dati.get(kcur),"get"+keyMethod)!=null && getObject(dati.get(kcur),"get"+keyMethod).equals(keyObject)){
				buf.add(dati.get(kcur));
				kcur++;
			}
			if(buf.size()>1){
				buf = sort(buf,new_cr_array,new_cr_ad);
				for(int j=k;j<kcur;j++) dati.set(j,buf.get(j-k));
			}
			if(buf.size()==0) kcur++;
			if(kcur<dati.size()){
				buf=new Vector();
				k=kcur;
				keyObject = getObject(dati.get(kcur),"get"+keyMethod);
			}	
		}
		
	}
	return dati;
}
public Vector sort(Vector dati, String cr) {
	return sort(dati,cr,"A");
}
public Vector sort(Vector dati, String cr, String ord) {
	Vector dati_cr = new Vector();
	for(int i=0;i<dati.size();i++){
		Object current = dati.elementAt(i); 
		if(cr.trim().equals(""))
			dati_cr.add(current);
		else{
			try{
				Object forAdd = util_reflect.getValue(current,"get"+cr,null);
				if(forAdd==null) forAdd = util_reflect.getValue(current,"get"+util_reflect.adaptMethodName(cr),null);
				if(forAdd==null){
					Object[] prm = new Object[1];
					prm[0] = cr;
					forAdd = util_reflect.getValue(current,"getMethod",prm);
				}
				if(forAdd!=null) dati_cr.add(forAdd);
				else return dati;
			}catch(Exception e){
				return dati;  
			}	
		}
	}
	_clone_sortSelf(dati,dati_cr,0,dati.size());
	if(ord.toUpperCase().equals("D")){
		Vector dati_b=new Vector();
		for(int i=dati.size()-1;i>-1;i--) dati_b.add(dati.get(i));
		dati = dati_b;
	}
	return dati;
}
}
