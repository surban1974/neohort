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

package neohort.universal.output;

import java.io.IOException;
import java.lang.ref.WeakReference;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import neohort.universal.output.util.I_OutputRunTime;

public class creator_iHort extends HttpServlet{
	private static final long serialVersionUID = -9213982084116715558L;
/*	
public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	I_OutputRunTime ort = null;
	WeakReference ref = null;
	boolean gc = (req.getParameter("gc")!=null && req.getParameter("gc").equals("true"))?true:false;
	try {
		ort = new iHort(req,res,getServletConfig());
		if(gc)
			ref = new WeakReference(ort);			
	} catch (Exception exc) {
	} finally {
		if(ort!=null)
			ort.clear();
		ort=null;
		if(gc){
			while(ref.get()!=null)
		        System.gc();
		}		
	}
}
public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	I_OutputRunTime ort = null;
	WeakReference ref = null;
	boolean gc = (req.getParameter("gc")!=null && req.getParameter("gc").equals("true"))?true:false;
	try {
		ort = new iHort(req,res,getServletConfig());
		if(gc)
			ref = new WeakReference(ort);			
	} catch (Exception exc) {
	} finally {
		if(ort!=null)
			ort.clear();
		ort=null;
		if(gc){
			while(ref.get()!=null)
		        System.gc();
		}		
	}
}
*/
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		I_OutputRunTime ort = null;
		boolean gc = (req.getParameter("gc")!=null && req.getParameter("gc").equals("true"))?true:false;
		try {
			ort = new iHort(req,res,getServletConfig());
		} catch (Exception exc) {
		} finally {
			if(gc){
				final WeakReference ref = new WeakReference(ort);
				if(ort!=null)
					ort.clear();
				ort=null;
				
				new Thread(
					new Runnable() {							
						@Override
						public void run() {		
							int count=0;
							while(ref.get()!=null && count<10){
								System.gc();
								count++;
								try {
									Thread.sleep(1000);											
								} catch (InterruptedException e) {
								}
							}
						}
					}						
				).start();
			}else{
				if(ort!=null)
					ort.clear();
				ort=null;
			}
			
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		I_OutputRunTime ort = null;
		boolean gc = (req.getParameter("gc")!=null && req.getParameter("gc").equals("true"))?true:false;
		try {
			ort = new iHort(req,res,getServletConfig());
		} catch (Exception exc) {
		} finally {
			if(gc){
				final WeakReference ref = new WeakReference(ort);
				if(ort!=null)
					ort.clear();
				ort=null;
				
				new Thread(
					new Runnable() {							
						@Override
						public void run() {		
							int count=0;
							while(ref.get()!=null && count<10){
								System.gc();
								count++;
								try {
									Thread.sleep(1000);											
								} catch (InterruptedException e) {
								}
							}
						}
					}						
				).start();
			}else{
				if(ort!=null)
					ort.clear();
				ort=null;
			}
		}
	}	
}
