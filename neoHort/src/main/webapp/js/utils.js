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

function showPdf(action){
	if(!document.getElementById('modalpopup')){
		var tmp_container = document.createElement('div');
		tmp_container.id='tmp_container';
		document.body.appendChild(tmp_container);
	}
		

		new clajax({
			url:'../included/modal_iframe.html',
			target: document.getElementById('tmp_container'),
			success: function(){ 
				new Modal(document.getElementById('modalpopup')).open();
/*				
				var modal = new Modal(
						document.getElementById('modalpopup'), 
						{
							content: ''
						}
				);
				
				modal.open();	
*/				
				try{
					document.getElementById('content_body_IFrame_Popup').src=action;
				}catch(e){
				}
			}
		}).request('GET');
		
}



