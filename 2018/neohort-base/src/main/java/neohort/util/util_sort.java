package neohort.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class util_sort {

	

	public static <T extends Object, E extends List<T>> E sort(E dati, String[] cr_array) {
		String[] cr_ad = new String[cr_array.length];
		for(int i=0;i<cr_ad.length;i++) cr_ad[i]="A";
		return sort(dati,cr_array,cr_ad);
	}
	
	public static <T extends Object, E extends List<T>> E sort(E dati, String[] cr_array, String[] cr_ad) {
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
			List<T> buf = new ArrayList<T>();
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
					buf=new ArrayList<T>();
					k=kcur;
					keyObject = getObject(dati.get(kcur),"get"+keyMethod);
				}
			}

		}
		return dati;
	}
	public static <T extends Object, E extends List<T>> E sort(E dati, String cr) {
		return sort(dati,cr,"A");
	}
	public static <T extends Object, E extends List<T>> E sort(E dati, String cr, String ord) {
		List<T> dati_cr = new ArrayList<T>();
		for(int i=0;i<dati.size();i++){
			T current = dati.get(i);
			if(cr.trim().equals(""))
				dati_cr.add(current);
			else{
				try{
					T forAdd = invoke(current,cr);
					if(forAdd!=null)
						dati_cr.add((T)forAdd);
					else return dati;
				}catch(Exception e){
					return dati;
				}
			}
		}
		_clone_sortSelf(dati,dati_cr,0,dati.size());
		if(ord.toUpperCase().equals("D")){
			Collections.reverse(dati);
//			List<T> dati_b=new ArrayList<T>();
//			for(int i=dati.size()-1;i>-1;i--) dati_b.add(dati.get(i));
//			dati = dati_b;
		}
		return dati;
	}


	private static <T extends Object> Object getObject(Object current, String method) { 
		try{
			java.lang.reflect.Method mtd = current.getClass().getMethod(method);
			if(mtd==null) 
				return null;
			else 
				return mtd.invoke(current);
		}catch(Exception e){
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	private static <T extends Object> T invoke(T current, String cr) throws Exception{
		Object forAdd = util_reflect.getValue(current,"get"+cr,null);
		if(forAdd==null) forAdd = util_reflect.getValue(current,"get"+util_reflect.adaptMethodName(cr),null);
		if(forAdd==null){
			Object[] prm = new Object[1];
			prm[0] = cr;
			forAdd = util_reflect.getValue(current,"getMethod",prm);
		}
		if(forAdd==null) 
			forAdd = util_reflect.prepareWriteValueForTag(current,"get",cr,null);
		
		return (T)forAdd;
	}


	private static <T extends Object> void  _clone_sortSelf(List<T> dati, List<T> dati_cr, int start, int finish) {
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


		List<Integer> index = new ArrayList<Integer>();
		for(int i=start;i<finish;i++)
			index.add(i);
		try{

			@SuppressWarnings("unchecked")
			T[] a_buf = (T[])dati_cr.toArray();
			
			@SuppressWarnings("unchecked")
			T[] a = (T[])new Comparable[finish-start];
			for(int j=start;j<finish;j++)
				a[j-start]= a_buf[j];
			Integer[] a_index = index.toArray(new Integer[0]);
			Integer src_index[] = a_index.clone();
			T[] src = a.clone();
			_clone_mergeSort(src, a, 0, a.length, src_index, a_index);
			ListIterator<T> li = dati_cr.listIterator();
			ListIterator<Integer> l_index = index.listIterator();
			for (int j=0; j<finish; j++){
				if(j>=start){
					li.next();
					li.set(a[j-start]);
			    	l_index.next();
			    	l_index.set(a_index[j-start]);
				}else li.next();
			}
		}catch(Exception e){
			
		}

		List<T> result = new ArrayList<T>();
		for(int i=start;i<finish;i++)
			result.add(dati.get(((Integer)index.get(i-start)).intValue()));
		
		for(int i=start;i<finish;i++)
			dati.set(i,result.get(i));

	}
	
	@SuppressWarnings("unchecked")
	private static <T extends Object> void _clone_mergeSort(T[] src, T[] dest, int low, int high, Integer index_src[], Integer index[]) {
		int length = high - low;
		
		

		if (length <7) {
			if(dest instanceof Comparable[]){
				for (int i=low; i<high; i++)
					for (int j=i; j>low && ((Comparable<T>)dest[j-1]).compareTo(dest[j])>0; j--){
						T t = dest[j];
							dest[j] = dest[j-1];
							dest[j-1] = t;
						Integer ti = index[j];
							index[j] = index[j-1];
							index[j-1] = ti;
					}
			    return;
			}
		}


		int mid = (low + high)/2;
		_clone_mergeSort(dest, src, low, mid, index, index_src);
		_clone_mergeSort(dest, src, mid, high, index ,index_src);

		if(src instanceof Comparable[]){
			if (((Comparable<T>)src[mid-1]).compareTo(src[mid]) <= 0) {
				System.arraycopy(src, low, dest, low, length);
				System.arraycopy(index_src, low, index, low, length);
				return;
			}


			for(int i = low, p = low, q = mid; i < high; i++) {
				if (q>=high || p<mid && ((Comparable<T>)src[p]).compareTo(src[q])<=0){
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
	}
	
	
}

