var frame_dynamic = true;
var timeForOut = 250;
var varScrollLeftPrev=0;
var varScrollTopPrev=0;
var xmouse=0;
var ymouse=0;
var variables = new Array;
var value_variables = new Array;  


var NS4=(document.layers);
var NS6=false;
var IE4=(document.all);
if (!IE4) {
	NS6=(document.getElementById);
}

function Mouse(evnt){
  xmouse = ((NS4) || (NS6))?(evnt.screenX):event.x;
  ymouse = ((NS4) || (NS6))?(evnt.screenY):event.y; 
}
((NS4) || (NS6))?window.onMouseMove=Mouse:document.onmousemove=Mouse;






function ObjectDraw(_parent,_type,_index,_descr,_function,_class,_img,PathImage,_img_before,_viewborder,_height,_width,_disable){


	
	if(_index=="") _index = new Date().getTime();
	var check_prev_border=false;
	var v_b = true;
	var disable = false;

	var height = 18;
	var width = "100%";
	try{
	
		if(_viewborder=="false") v_b=false;
		if(_viewborder==undefined) check_prev_border=true;

	}catch(e){
		check_prev_border=true;
	}
	
	try{
		if(_disable==true) disable=true;
	}catch(e){
	}

	
	try{
		if(_height/1!=0) height=_height;
	}catch(e){
	}	
	try{
		if(_width.length>0) width=_width;
	}catch(e){

	}	
	
	var win_status = _descr;
	if(win_status.indexOf("'")>-1) win_status="";
	if(win_status.indexOf("'")>-1) win_status="";

	
	if(_descr!="" && _descr.indexOf("'")>-1){
		_descr=_descr.replace("'","\'");
		_descr_ws="";
	}
	
	if(PathImage=="") PathImage="images/menu/";
	if(NS4){
		document.layers[_parent].document.write("		<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\">");
		document.layers[_parent].document.write("			<tr>");
		document.layers[_parent].document.write("			  <td name=\""+_type+"_s_c_"+_index+"\" id=\""+_type+"_s_c_"+_index+"\" nowrap=\"nowrap\" border=\"0\" height=\""+height+"\" background=\""+PathImage+""+_type+"_center_n.gif\">");
		document.layers[_parent].document.write("			  		<span class=\""+_class+"\"><a href=\"javascript:void("+_function+"());\" onMouseOver=\"window.status='"+win_status+"';menuMouseOver1("+_index+",'"+_type+"','"+PathImage+"');return true;\" onMouseOut=\"window.status='';menuMouseOut1("+_index+",'"+_type+"','"+PathImage+"');return true;\" onMouseDown=\"window.status='';return true;\"  onMouseUp=\"window.status='';return true;\">"+_descr+"</a></span>");
		document.layers[_parent].document.write("			  </td>");
		document.layers[_parent].document.write("			</tr>");
		document.layers[_parent].document.write("		</table>");

	}else{
		if((check_prev_border && _descr!="" && v_b) || (!check_prev_border && v_b)){
			document.write("<table cellpadding=\"0\" cellspacing=\"0\" width='"+width+"' style=\"border: solid 1px silver; " );
		}else{
			document.write("<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width='"+width+"' style=\"");
		}
		if(disable==true)
			document.write("filter: alpha(opacity=65);opacity: 0.65;");
			
		document.write("\" >");
		
		document.write("<tr>");
		document.write("<td align=\"center\" valign=\"middle\" name=\""+_type+"_s_c_"+_index+"\" id=\""+_type+"_s_c_"+_index+"\" nowrap=\"nowrap\" border=\"0\" height=\""+height+"\" background=\""+PathImage+""+_type+"_center_n.gif\" style=\"cursor:pointer \" ");

	
		if(disable==false)
			document.write(" onMouseOver=\"window.status='"+win_status+"';menuMouseOver1("+_index+",'"+_type+"','"+PathImage+"');return true;\" onMouseOut=\"window.status='';menuMouseOut1("+_index+",'"+_type+"','"+PathImage+"');return true;\"  onMouseDown=\"window.status='';return true;\" onMouseUp=\"window.status='';return true;\" ");
	
		if(_function=="" || disable==true){
		}else{
			if(_function.indexOf("(")>-1) document.write(" onClick=\""+_function+";\" ");
			else document.write("onClick=\""+_function+"()\" ");
		}
		document.write(">");		
		
		if(disable==true){
			document.write("<span class=\""+_class+"_dis\"  id=\""+_type+"_s_c_span_"+_index+"\" >");
		}else{
			document.write("<span class=\""+_class+"\"  id=\""+_type+"_s_c_span_"+_index+"\" >");
		}
			
			document.write("<img src=\""+PathImage+"button_left_n.gif\" border=\"0\" >");
			if(_img_before && _img_before!="") document.write("<img src=\""+PathImage+_img_before+"\" border=\"0\" >");				
			if(_descr!=""){
				document.write("<a >");
				document.write("<img src=\""+PathImage+"button_left_n.gif\" border=\"0\" >");
				document.write(""+_descr);
				document.write("<img src=\""+PathImage+"button_right_n.gif\" border=\"0\" >");
				document.write("</a>");
			}
			if(_img!="") document.write("<img src=\""+PathImage+_img+"\" border=\"0\" >");	
			document.write("<img src=\""+PathImage+"button_right_n.gif\" border=\"0\" >");
			
			document.write("</span>");
	
		
		document.write("</td>");
		document.write("</tr>");
		document.write("</table>");
	}

}

function ObjectDrawAsInnerHTML(obj, _parent,_type,_index,_descr,_function,_class,_img,PathImage,_img_before,_viewborder,_height,_width, _disable){
	obj_writer="";
	if(_index=="") _index = new Date().getTime();
	var _descr_ws = _descr;
	var v_b = true;
	var disable = false;
	var height = 18;
	var width = "100%";

	try{
		if(_viewborder=="false") v_b=false;
	}catch(e){
	}
	try{
		if(_disable==true) disable=true;
	}catch(e){
	}

	
	try{
		if(_height/1!=0) height=_height;
	}catch(e){
	}	
	try{
		if(_width.length>0) width=_width;
	}catch(e){

	}	

	
	var win_status = _descr;
	if(win_status.indexOf("'")>-1) win_status="";
	if(win_status.indexOf("'")>-1) win_status="";

	
	if(_descr!="" && _descr.indexOf("'")>-1){
		_descr=_descr.replace("'","\'");
		_descr_ws="";
	}
	
	if(PathImage=="") PathImage="images/menu/";

		if(_descr!="" && v_b){
			obj_writer+=("<table cellpadding=\"0\" cellspacing=\"0\"  width='"+width+"' style=\"border: solid 1px silver; ");
		}else{
			obj_writer+=("<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width='"+width+"' style=\"");
		}
		
		if(disable==true)
			obj_writer+=("filter: alpha(opacity=65);opacity: 0.65;");
		obj_writer+=("\" >");
		
		
		obj_writer+=("<tr>");
		obj_writer+=("<td align=\"center\" valign=\"middle\" name=\""+_type+"_s_c_"+_index+"\" id=\""+_type+"_s_c_"+_index+"\" nowrap=\"nowrap\" border=\"0\" height=\""+height+"\" background=\""+PathImage+""+_type+"_center_n.gif\" style=\"cursor:pointer \" ");
		
		if(disable==false)
			obj_writer+=(" onMouseOver=\"window.status='"+_descr_ws+"';menuMouseOver1("+_index+",'"+_type+"','"+PathImage+"');return true;\" onMouseOut=\"window.status='';menuMouseOut1("+_index+",'"+_type+"','"+PathImage+"');return true;\"  onMouseDown=\"window.status='';return true;\" onMouseUp=\"window.status='';return true;\" ");
		if(_function=="" || disable==true){
		}else{
			if(_function.indexOf("(")>-1) obj_writer+=(" onClick=\""+_function+";\" ");
			else obj_writer+=("onClick=\""+_function+"()\" ");
		}
		obj_writer+=(">");		
		
		if(disable==true){
			obj_writer+=("<span class=\""+_class+"_dis\"  id=\""+_type+"_s_c_span_"+_index+"\" >");
		}else{
			obj_writer+=("<span class=\""+_class+"\"  id=\""+_type+"_s_c_span_"+_index+"\" >");
		}
				
			
			obj_writer+=("<img src=\""+PathImage+"button_left_n.gif\" border=\"0\" >");
			if(_img_before && _img_before!="") obj_writer+=("<img src=\""+PathImage+_img_before+"\" border=\"0\" >");				
			if(_descr!=""){
				obj_writer+=("<a >");
				obj_writer+=("<img src=\""+PathImage+"button_left_n.gif\" border=\"0\" >");
				obj_writer+=(""+_descr);
				obj_writer+=("<img src=\""+PathImage+"button_right_n.gif\" border=\"0\" >");
				obj_writer+=("</a>");
			}
			if(_img!="") obj_writer+=("<img src=\""+PathImage+_img+"\" border=\"0\" >");	
			obj_writer+=("<img src=\""+PathImage+"button_right_n.gif\" border=\"0\" >");
			
			obj_writer+=("</span>");
	
		
		obj_writer+=("</td>");
		obj_writer+=("</tr>");
		obj_writer+=("</table>");
		
		obj.innerHTML=obj_writer;

}



function menuMouseOver1(_index,_type,PathImage){
	try{
		if(IE4) {
			document.all[_type+"_s_c_"+_index].style.backgroundImage="url(" + PathImage+""+_type+"_center_o.gif"+")";
		}
		if(NS6) {
			document.getElementById(_type+"_s_c_"+_index).style.backgroundImage= "url(" +PathImage+""+_type+"_center_o.gif"+ ")";
		}
	}catch(e){
	}
	return;
}

function menuMouseOut1(_index,_type,PathImage){
	if(getVar(_type+_index)!=1){
		try{
			if(IE4) {
				document.all[_type+"_s_c_"+_index].style.backgroundImage="url(" + PathImage+""+_type+"_center_n.gif"+")";
			}
			if(NS6) {
				document.getElementById(_type+"_s_c_"+_index).style.backgroundImage= "url(" +PathImage+""+_type+"_center_n.gif"+ ")";
			}
		}catch(e){
		}
	}
	return;
}

function menuMouseOver(_index,_type,PathImage){
	return;
	if(document.all[_type+"_s_l_"+_index]){
	}else return;
	if(IE4) {
		document.all[_type+"_s_l_"+_index].src=PathImage+""+_type+"_left_o.gif";
		if(document.all[_type+"_s_c_"+_index].style.backgroundImage){
			document.all[_type+"_s_c_"+_index].style.backgroundImage="url(" + PathImage+""+_type+"_center_o.gif"+")";
		}else{
			document.all[_type+"_s_c_"+_index].background=PathImage+""+_type+"_center_o.gif";
		}
		document.all[_type+"_s_r_"+_index].src=PathImage+""+_type+"_right_o.gif";
	}	
	if(NS6) {
		document.getElementById(_type+"_s_l_"+_index).src=PathImage+""+_type+"_left_o.gif";
		document.getElementById(_type+"_s_c_"+_index).style.backgroundImage= "url(" +PathImage+""+_type+"_center_o.gif"+ ")";
		document.getElementById(_type+"_s_r_"+_index).src=PathImage+""+_type+"_right_o.gif";
	}	
	
}
function menuMouseOut(_index,_type,PathImage){
	return;
	if(document.all[_type+"_s_l_"+_index]){
	}else return;
	if(IE4) {
		if(getVar(_type+_index)!=1){
			document.all[_type+"_s_l_"+_index].src=PathImage+""+_type+"_left_n.gif";
			if(document.all[_type+"_s_c_"+_index].style.backgroundImage){
				document.all[_type+"_s_c_"+_index].style.backgroundImage = "url(" + PathImage+""+_type+"_center_n.gif"+")";
			}else{
				document.all[_type+"_s_c_"+_index].background=PathImage+""+_type+"_center_n.gif";
			}
			
			document.all[_type+"_s_r_"+_index].src=PathImage+""+_type+"_right_n.gif";
		}	
	}	
	if(NS6) {
		if(getVar(_type+_index)!=1){
			document.getElementById(_type+"_s_l_"+_index).src=PathImage+""+_type+"_left_n.gif";
			document.getElementById(_type+"_s_c_"+_index).style.backgroundImage = "url(" +PathImage+""+_type+"_center_n.gif"+")";
			document.getElementById(_type+"_s_r_"+_index).src=PathImage+""+_type+"_right_n.gif";
		}	
	}	
		
}

function menuMouseDown(_index,_type,PathImage){
	return;
//	if(document.all[_type+"_s_l_"+_index]){
//	if(document.all && document.all[_type+"_s_l_"+_index]){
//	}else return;
	if(IE4) {
		if(getVar(_type+_index)!=1){
			if(document.all[_type+"_s_l_"+_index]){
				document.all[_type+"_s_l_"+_index].src=PathImage+""+_type+"_left_c.gif";
				if(document.all[_type+"_s_c_"+_index].style.backgroundImage){
					document.all[_type+"_s_c_"+_index].style.backgroundImage = "url(" + PathImage+""+_type+"_center_c.gif"+")";
				}else{
					document.all[_type+"_s_c_"+_index].background=PathImage+""+_type+"_center_c.gif";
				}
			
				document.all[_type+"_s_r_"+_index].src=PathImage+""+_type+"_right_c.gif";
			}	
		}	
	}	
	if(NS6) {
		if(getVar(_type+_index)!=1){
			if(document.getElementById(_type+"_s_l_"+_index)){
				document.getElementById(_type+"_s_l_"+_index).src=PathImage+""+_type+"_left_c.gif";
				document.getElementById(_type+"_s_c_"+_index).style.backgroundImage = "url(" +PathImage+""+_type+"_center_c.gif"+")";
				document.getElementById(_type+"_s_r_"+_index).src=PathImage+""+_type+"_right_c.gif";
			}	
		}	
	}	
}

function menuMouseUp(_index,_type,PathImage){
	menuMouseOver(_index,_type,PathImage);
}


function getVar(value){
	for(var i=0;i<variables.length;i++){
		if(variables[i]==value) return value_variables[i];
	}
	return -1;
}

function setVar(value,setValue){
	for(var i=0;i<variables.length;i++){
		if(variables[i]==value){
			value_variables[i]=setValue;
			return;
		}	
	}
}


function checkScroll(){
	var result = false;
	if(IE4){
		if(	Math.abs(varScrollLeftPrev - document.body.scrollLeft)<50 &&
			Math.abs(varScrollTopPrev - document.body.scrollTop)<50)
			result = true;
		else result = false;	
		varScrollLeftPrev = document.body.scrollLeft;
		varScrollTopPrev = document.body.scrollTop;		
	}	
	if(NS6){
		if(	Math.abs(varScrollLeftPrev - window.pageXOffset)<50 &&
			Math.abs(varScrollTopPrev - window.pageYOffset)<50)
			result = true;
		else result = false;	
		varScrollLeftPrev = window.pageXOffset;
		varScrollTopPrev = window.pageYOffset;		
	}	
	if(NS4){
		if(	Math.abs(varScrollLeftPrev - window.pageXOffset)<50 &&
			Math.abs(varScrollTopPrev - window.pageYOffset)<50)
			result = true;
		else result = false;	
		varScrollLeftPrev = window.pageXOffset;
		varScrollTopPrev = window.pageYOffset;		
	}	
	return result;
}

function beforeCheck(){
	if(frame_dynamic){ 
		if(IE4){
			for(var i=0;i<variables.length;i++)
				document.all[variables[i]].style.visibility = "hidden";
		}	
		if(NS6){
			for(var i=0;i<variables.length;i++)
				document.getElementById(variables[i]).style.visibility = "hidden";
		}		
		if(NS4){
			for(var i=0;i<variables.length;i++)
				document.layers[variables[i]].visibility = 'hide';
		}		
	}	
}


function beforeCheckNew(value){
	if(IE4 || NS6){
		for(var i=0;i<variables.length;i++){
			if(frame_dynamic){
				if(variables[i].indexOf("bar")!=0)
					document.getElementById(variables[i]).style.visibility = "hidden";
			}else{	
				if(variables[i].indexOf("bar")!=0 && (variables[i]!=value || value_variables[i]==0)){
					document.getElementById(variables[i]).style.visibility = "hidden";
				}	
				if(variables[i]==value && value_variables[i]==1)	
					document.getElementById(variables[i]).style.visibility = "visible";
					
			}		
			if(variables[i]!=value || value_variables[i]==0){
				if(variables[i].indexOf("page")==0){
					var td_name = "page_s_c_"+ variables[i].substring(4);
					if(document.getElementById(td_name)) document.getElementById(td_name).className="page_section";
				}	
			}
			
		}	
	}		
	if(NS4){
		for(var i=0;i<variables.length;i++){
			if(frame_dynamic){
				if(variables[i].indexOf("bar")!=0)
					document.layers[variables[i]].visibility = "hidden";
			}else{	
				if(variables[i].indexOf("bar")!=0 && variables[i]!=value)
					document.layers[variables[i]].visibility = "hide";
				if(variables[i]==value && value_variables[i]==1)	
					document.layers[variables[i]].visibility = "show";
				if(variables[i]==value && value_variables[i]==0)	
					document.layers[variables[i]].visibility = "hide";
					
			}		
		}	
	}		
}

function beforeAction(value,PathImage){
	for(var i=0;i<variables.length;i++){
		if(i!=value && variables[i].indexOf("bar")!=0){
			value_variables[i]=0;
			if(document.getElementById("page_s_c_"+value)) document.getElementById("page_s_c_"+value).className="page_section";
			if(variables[i].indexOf("page")==0)
				menuMouseOut1(variables[i].substring(variables[i].indexOf("page")+4),"page",PathImage);			
		}			
	}
}

function check_1(_index,_type,PathImage,dX,dY){
	menuMouseOver(_index,_type,PathImage);
	check(_type+_index,dX,dY)
}


function check(value,dX,dY){
	if(IE4){
		if(document.all[value]){
			if(checkScroll()){
				if(getVar(value)==1)
					document.all[value].style.visibility = "visible";
				if(frame_dynamic){	
					document.all[value].style.posLeft=document.body.scrollLeft+dX;
					document.all[value].style.posTop=document.body.scrollTop+dY;
				}	
			}else{
				if(getVar(value)==1)
					document.all[value].style.visibility = "hidden";
			}			
		}	
		if(frame_dynamic) setTimeout("check('"+value+"',"+dX+","+dY+")",timeForOut);		
		return;
	}	
	if(NS6){
		if(document.getElementById(value)){
			if(checkScroll()){
				if(getVar(value)==1)
					document.getElementById(value).style.visibility = "visible";
				if(frame_dynamic){	
					document.getElementById(value).style.left=window.pageXOffset+dX;
					document.getElementById(value).style.top=window.pageYOffset+dY;
				}	
			}else{
				if(getVar(value)==1)
					document.getElementById(value).style.visibility = "hidden";
			}
		}	
		if(frame_dynamic) setTimeout("check('"+value+"',"+dX+","+dY+")",timeForOut);
		return;
	}	
	if (NS4){

		if(document.layers[value]){
			if(checkScroll()){
				if(getVar(value)==1)
					document.layers[value].visibility = "show";
				if(frame_dynamic){	
					document.layers[value].x=window.pageXOffset+dX;
					document.layers[value].y=window.pageYOffset+dY;
				}	
			}else{
				if(getVar(value)==1)
					document.layers[value].visibility = "hide";
			}
		}	
		if(frame_dynamic) setTimeout("check('"+value+"',"+dX+","+dY+")",timeForOut);		
		return;
	}	
	
}


function findInPage(str) {
	var win = this;
	var n   = 0;
	var txt, i, found;
	
	if (str == "") return false;
	if (NS4 || NS6) {
		if (!win.find(str))
		while(win.find(str, false, true))
			n++;
		else
			n++;
		if (n == 0) alert(str + " was not found on this page.");
	}
	if (IE4) {

		if( win.document.selection && win.document.selection.type != 'None' ) {
			txt = win.document.selection.createRange();
			txt.collapse( false );
		} else {
			txt = win.document.body.createTextRange();
		}	
		
		for (i = 0; i <= n && (found = txt.findText(str)) != false; i++) {
			txt.moveStart("character", 1);
			txt.moveEnd("textedit");
		}
		if (found) {
			txt.moveStart("character", -1);
			txt.findText(str);
			txt.select();
			txt.scrollIntoView();
			n++;
		}else {
			if (n > 0) {
				n = 0;
				findInPage(str);
			}else	alert(str + " was not found on this page.");
		}
	}
	return false;
}

function startURL(URL, Descr){
	if(URL=='') return;
	screenPos = ",screenX=0"
		+",screenY=0"
		+",top=0"
		+",left=0"
		+",width=" + (screen.availWidth -10).toString()
		+",height=" + (screen.availHeight -30).toString();
	if (navigator.appName == 'Netscape')
		screenPos = screenPos + ",fullscreen=yes";

	aParams = "menubar=no"
		  +",status=no"
		  +",directories=no"
		  +",location=no"
		  +",titlebar=no"
		  +",toolbar=no"
		  +",resizable=yes"
		  +",scrollbars=yes"
		  +screenPos;
		  window.open(URL, Descr, aParams)
}

function startWin(URL){
	if(URL=='') return;
	screenPos = ",screenX=0"
		+",screenY=0"
		+",top=0"
		+",left=0"
		+",width=420"
		+",height=130"
	if (navigator.appName == 'Netscape')
		screenPos = screenPos + ",fullscreen=yes";
	aParams = "menubar=no"
		  +",status=no"
		  +",directories=no"
		  +",location=no"
		  +",titlebar=no"
		  +",toolbar=no"
		  +",resizable=yes"
		  +",scrollbars=no"
		  +screenPos;
	window.open(URL, "Tabelli", aParams);
}
/*
function beforeClick(){
	setVar("page1",0);
	setVar("p_scorr",1);
	beforeCheckNew("p_scorr");
}
*/


function f_forBar(val){
	beforeAction(val,"../images/menu/");
	if(getVar("page"+val)==0){
		setVar("page"+val,1);
		setVar("p_close",1);
		if(document.getElementById("page_s_c_"+val))
			document.getElementById("page_s_c_"+val).className="page_section_active";
	}	
	else{
		setVar("page"+val,0);
		setVar("p_close",0);
	}	
	beforeCheckNew("page"+val);	
	initLightMenuHideContent1(val);
	
	if(val==4){
		setTimeout("try{document.getElementById('button42_input').focus();}catch(e){}",500);
	}
	
}

function f_ricerca_ok(){
	var for_find="";
	if(document.getElementById("button42_input"))
		for_find = document.getElementById("button42_input").value;
	findInPage(for_find);
}
function f_fly(){
	if(frame_dynamic) document.getElementById("fly").src = "qmftool_fly_no.gif";			
	else document.getElementById("fly").src = "qmftool_fly_yes.gif";

	if(frame_dynamic) frame_dynamic=false;
	else{
		frame_dynamic=true;
		check("bar0",0,0);
		check("page1",15,21);
		check("page2",15,21);
		check("page3",15,21);
		check("page4",15,21);		
		check("p_scorr",15,21);
		check("p_close",0,21);
	}
}

function f_showMenu(){
	try{
		parent.document.getElementById("outerFrame").cols="20%,*,0%";
	}catch(e){
	}	
}

function f_closeSection(val){
	beforeAction(-1,"../images/menu/");
	beforeCheckNew("page");	
}

function beforeClick(){
	try{
		document.getElementById("content_Panel_runSubmit").style.display="block";
	}catch(e){
	}	
	setVar("page1",0);
	setVar("page2",0);
	setVar("page3",0);
	setVar("page4",0);
	setVar("p_scorr",1);
	beforeCheckNew("p_scorr");
	
}

function initLightMenu(checkError){
	document.getElementById("bar0").style.visibility="visible";
	
	variables[0]="bar0";
	value_variables[0]=1;
	
	variables[1]="page1";
	variables[2]="page2";
	
	if(checkError=="false"){
		value_variables[1]=1;
		value_variables[2]=0;
		if(document.getElementById("page_s_c_1"))
			document.getElementById("page_s_c_1").className="page_section_active";
		menuMouseOver1(1,"page","../images/menu/");
	}else{
		value_variables[1]=0;
		value_variables[2]=1;
		if(document.getElementById("page_s_c_2"))
			document.getElementById("page_s_c_2").className="page_section_active";
		menuMouseOver1(2,"page","../images/menu/");
	}
	
	variables[3]="page3";value_variables[3]=0;
	variables[4]="page4";value_variables[4]=0;
	variables[5]="p_scorr";value_variables[5]=0;
	variables[6]="p_close";value_variables[5]=0;
	
	check("bar0",0,0);
	check("page1",15,21);
	check("page2",15,21);
	check("page3",15,21);
	check("page4",15,21);
	check("p_scorr",15,21);
	check("p_close",0,21);
	setVar("p_close",1);
	window.onscroll=beforeCheck;
	
	var content = "";
	content = document.getElementById("page2").innerHTML;
	if(content.toUpperCase().indexOf("<TR>")==-1) document.getElementById("page_s_c_span_2").innerHTML="";
	
	window.setTimeout("initLightMenuHideContent()",100);

}	

function initLightMenuHideContent(){
	var content = "";
	content = document.getElementById("page1").innerHTML;
	if(content.toUpperCase().indexOf("<TR>")==-1){
		setVar("p_close",0);
		beforeCheckNew("p_close");	
	}	
}
function initLightMenuHideContent1(val){
	var content = "";
	content = document.getElementById("page"+val).innerHTML;
	if(content.toUpperCase().indexOf("<TR>")==-1){
		setVar("p_close",0);
		beforeCheckNew("p_close");	
	}	
}