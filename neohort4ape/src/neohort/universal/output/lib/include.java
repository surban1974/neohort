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

package neohort.universal.output.lib;

import java.util.Enumeration;
import java.util.Hashtable;

import neohort.universal.output.iConst;
import neohort.universal.output.iHort;

public class include extends report_element_base{
	private static final long serialVersionUID = 1735041360413529270L;
	private java.lang.String LINK;
public include() {
	super();	
}
public void executeFirst(Hashtable _tagLibrary, Hashtable _beanLibrary) {
    Boolean included =
        (Boolean) (((report_element_base) _beanLibrary.get("SYSTEM:"+iConst.iHORT_SYSTEM_Included)).getContent());
    String hash_prev = "";

    iHort new_pdfrt = new iHort(LINK, _beanLibrary, this.getParent());
    _beanLibrary = new_pdfrt.get_beanLibrary();
    hash_prev = "_ids_" + new_pdfrt.hashCode();

    if (!hash_prev.equals("")) {
        Enumeration keys = _beanLibrary.keys();
        while (keys.hasMoreElements()) {
            try {
                String key = (String) keys.nextElement();
                if (key.indexOf(hash_prev) > -1)
                    _beanLibrary.remove(key);
            } catch (Exception e) {}
        }
    }

    if (included == null
        || (included != null && included.booleanValue() == false)) {
        _beanLibrary.remove("SYSTEM:"+iConst.iHORT_SYSTEM_Included);
        bean _sysIncluded = new bean();
        _sysIncluded.setContent(new Boolean(false));
        _sysIncluded.setName("SYSTEM");
        _sysIncluded.setID(iConst.iHORT_SYSTEM_Included);
        _beanLibrary.put(
            _sysIncluded.getName() + ":" + _sysIncluded.getID(),
            _sysIncluded);
    }
}
public void executeLast(Hashtable _tagLibrary, Hashtable _beanLibrary){
}
public java.lang.String getLINK() {
	return LINK;
}
public void reimposta() {
	setName("INCLUDE");
	LINK = "";
}
public void setLINK(java.lang.String newLINK) {
	LINK = newLINK;
}
}
