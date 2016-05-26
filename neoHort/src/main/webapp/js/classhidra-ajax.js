(function(factory){

	// CommonJS/RequireJS and "native" compatibility
	if(typeof module !== "undefined" && typeof exports == "object") {
		// A commonJS/RequireJS environment
		if(typeof window != "undefined") {
			// Window and document exist, so return the factory's return value.
			module.exports = factory();
		} else {
			// Let the user give the factory a Window and Document.
			module.exports = factory;
		}
	} else {
		// Assume a traditional browser.
		window.clajax = factory();
	}

})(function(root){

	var clajax = function(prot){
		
		this.form = 			(prot)?prot.form:null;
		this.action = 			(prot)?prot.action:null;
		this.method = 			(prot)?prot.method:null;
		this.url = 				(prot)?prot.url:null;
		this.json = 			(prot)?prot.json:null;
		this.xml = 				(prot)?prot.xml:null;
		this.mpart = 			(prot)?prot.mpart:null;
		this.target = 			(prot)?prot.target:null;
		this.responseType = 	(prot)?prot.responseType:null;
		this.asynchronous = 	(prot)?prot.asynchronous:true;
		this.rel = 				(prot)?prot.rel:null;
		this.media = 			(prot)?prot.media:null;
		this.contentEncoding = 	(prot)?prot.contentEncoding:null;
		this.contentType = 		(prot)?prot.contentType:null;
		this.progressWait = 	(prot)?prot.progressWait:null;
		this.mimeType = 		(prot)?prot.mimeType:null;
		
		this.start = 			(prot)?prot.start:null;
		
		this.success = 			(prot)?prot.success:null;
		this.ready = 			(prot)?prot.ready:null;
		this.fail = 			(prot)?prot.fail:null;
		this.error =			(prot)?prot.error:null;
		this.finish = 			(prot)?prot.finish:null;
		
		
		this.base64 = 			(prot)?((prot.base64)?prot.base64:false):false;
		this.asJson = 			(prot)?((prot.asJson)?prot.asJson:false):false;
		this.asXml = 			(prot)?((prot.asXml)?prot.asXml:false):false;
		this.asMpart = 			(prot)?((prot.asMpart)?prot.asMpart:false):false;
		this.asScript = 		(prot)?((prot.asScript)?prot.asScript:false):false;
		this.asCss = 			(prot)?((prot.asCss)?prot.asCss:false):false;
		
		this.acceptableStatus = (prot)
								?
									prot.acceptableStatus
								:
									[
/*									 
									 {
										status:200,
										success:null,
										ready:null
									}
*/									
									 ];
		
	}
	
	clajax.prototype = {
	
			clear : function(){
		
				this.form=null;
				this.action=null;
				this.method=null;
				this.url=null;
				this.json=null;
				this.xml=null;
				this.mpart=null;
				this.target=null;
				this.responseType=null;
				this.asynchronous = true;
				this.rel=null;
				this.media=null;
				this.contentEncoding=null;
				this.contentType=null;
				this.progressWait=null;
				this.mimeType=null;
				
				this.start=null;
				this.success=null;
				this.ready=null;
				this.fail=null;
				this.error;
				this.finish;
				
				this.base64 = false;
				this.asJson = false;
				this.asXml = false;
				this.asMpart = false;
				this.asScript = false;
				this.asCss = false;
				this.acceptableStatus = [];
				return this;
			},
	
			setForm : function(_form){
				this.form = _form;
				return this;
			},
	
			setMethod : function(_method){
				this.method = _method;
				return this;
			},
	
			setAction : function(_action){
				this.action = _action;
				return this;
			},
	
			setUrl : function(_url){
				this.url = _url;
				return this;
			},
	
			setJson : function(_json){
				this.json = _json;
				return this;
			},
	
			setXml : function(_xml){
				this.xml = _xml;
				return this;
			},	
	
			setMpart : function(_mpart){
				this.mpart = _mpart;
				return this;
			},	
	
			setTarget : function(_target){
				this.target = _target;
				return this;
			},
			
			setRel : function(_rel){
				this.rel = _rel;
				return this;
			},
			
			setMedia : function(_media){
				this.media = _media;
				return this;
			},	
			
			setResponseType : function(_responseType){
				this.responseType = _responseType;
				return this;
			},
			
			setAsynchronous : function(_asynchronous){
				this.asynchronous = _asynchronous;
				return this;
			},
			
			setContentEncoding : function(_contentEncoding){
				this.contentEncoding = _contentEncoding;
				return this;
			},
			
			setContentType : function(_contentType){
				this.contentType = _contentType;
				return this;
			},
			
			setMimeType : function(_mimeType){
				this.mimeType = _mimeType;
				return this;
			},
			
			setStart : function(_start){
				this.start = _start;
				return this;
			},
			
			setSuccess : function(_success){
				this.success = _success;
				return this;
			},
			
			setRedy : function(_ready){
				this.ready = _ready;
				return this;
			},	
			
			setFail : function(_fail){
				this.fail = _fail;
				return this;
			},	
			
			setError : function(_error){
				this.error = _error;
				return this;
			},
			
			setFinish : function(_finish){
				this.finish = _finish;
				return this;
			},	
			
			setBase64 : function(_base64){
				this.base64 = _base64;
				return this;
			},
			
			setAsJson : function(_asJson){
				this.asJson = _asJson;
				return this;
			},
			
			setAsXml : function(_asXml){
				this.asXml = _asXml;
				return this;
			},	
		
			setAsMpart : function(_asMpart){
				this.asMpart = _asMpart;
				return this;
			},	
			
			setAsScript : function(_asScript){
				this.asScript = _asScript;
				return this;
			},	
			
			setAsCss : function(_asCss){
				this.asCss = _asCss;
				return this;
			},		
			
			setAcceptableStatus : function(_acceptableStatus){
				this.acceptableStatus = _acceptableStatus;
				return this;
			},
	

			clone : function(){
				
				var result = new clajax()
				.setForm(this.form)
				.setMethod(this.method)
				.setAction(this.action)
				.setUrl(this.url)
				.setJson(this.json)
				.setXml(this.xml)
				.setMpart(this.mpart)
				.setTarget(this.target)
				.setRel(this.rel)
				.setMedia(this.media)
				.setResponseType(this.responseType)
				.setContentEncoding(this.contentEncoding)
				.setContentType(this.contentType)
				.setAsynchronous(this.asynchronous)
				.setMimeType(this.mimeType)
				.setStart(this.start)
				.setSuccess(this.success)
				.setRedy(this.ready)
				.setFail(this.fail)
				.setError(this.error)
				.setFinish(this.finish)
				.setBase64(this.base64)
				.setAsJson(this.asJson)
				.setAsXml(this.asXml)
				.setAsMpart(this.asMpart)
				.setAsScript(this.asScript)
				.setAsCss(this.asCss)
				.setAcceptableStatus(this.acceptableStatus);
				
				return result;
		
			},
	
			load : function(){
				if(this.asScript){
					var e = document.createElement('script');
			
					if(this.base64){
						var parameters = this.getParametersAsUrl(null,this.url);
						if(this.url.indexOf('?')>-1)
							e.src = url.substring(0,this.url.indexOf('?'))+parameters;
						else
							e.src = this.url+parameters;
					}else
						e.src = this.url;
			
					if(this.type && this.type!='')
						e.type=this.type;
					else
						e.type='text/javascript';
			
					if(this.rel && this.rel!='')
						e.rel=this.rel;
					
					if(this.contentEncoding && this.contentEncoding!='')
						e.charset=this.contentEncoding;
					
					var instance  = this;
					try{
						if(instance.success && instance.success!=''){
							if (typeof instance.success === 'function') {
								//real browsers
							    e.onload=instance.success;
							    //Internet explorer
							    e.onreadystatechange = function() {
							        if (this.readyState == 'complete') {
							        	instance.success();
							        }
							    }
			
							}else{
								//real browsers
							    	e.onload=eval(instance.success + '()');
							    //Internet explorer
								    e.onreadystatechange = function() {
								        if (this.readyState == 'complete') {
								        	eval(instance.success + '()');
								        }
								    }
							}
						}
					}catch(e){
					}
					try{
						document.getElementsByTagName('head')[0].appendChild(e);
					}catch(e){
					}
				}
				if(this.asCss){
					var e = document.createElement('link');
		
					if(this.base64){
						var parameters = this.getParametersAsUrl(null,this.url);
						if(href.indexOf('?')>-1)
							e.href = this.url.substring(0,this.url.indexOf('?'))+parameters;
						else
							e.href = this.url+parameters;
					}else
						e.href = this.url;
		
					if(this.type && this.type!='')
						e.type=this.type;
					else
						e.type='text/css';
		
		
					if(this.rel && this.rel!='')
						e.rel=rel;
					else
						e.rel='stylesheet';
		
					if(this.media && this.media!='')
						e.media=this.media;
					
					if(this.contentEncoding && this.contentEncoding!='') 
						e.charset=this.contentEncoding;
					
					var instance  = this;
					try{
						if(instance.success && instance.success!=''){
							if (typeof instance.success === 'function') {
								//real browsers
							    e.onload=instance.success;
							    //Internet explorer
							    e.onreadystatechange = function() {
							        if (this.readyState == 'complete') {
							        	instance.success();
							        }
							    }
		
							}else{
								//real browsers
							    	e.onload=eval(instance.success + '()');
							    //Internet explorer
								    e.onreadystatechange = function() {
								        if (this.readyState == 'complete') {
								        	eval(instance.success + '()');
								        }
								    }
							}
						}
					}catch(e){
					}
					try{
						document.getElementsByTagName('head')[0].appendChild(e);
					}catch(e){
					}			
				}
				
				return this;
		
			},	


			submit : function(_form, _action){
				if(_form)
					this.form = _form;
		
				if(this.form){
					if(_action)
						this.action = _action;
					else
						this.action = this.form.action;
		
						
					if(this.asJson){
						this
							.setUrl(this.action)
							.setJson(this.getParametersAsJson(this.form, this.action))
							.request(this.form.method);
					}else if(this.asXml){
						this
							.setUrl(this.action)
							.setXml(this.getParametersAsXml(this.form, this.action))
							.request(this.form.method);
					}else if(this.asMpart){
						this
							.setUrl(this.action)
							.setMpart(this.getParametersAsMpart(this.form, this.action))
							.request(this.form.method);					
					}else
						this
							.setUrl(this.action+this.getParametersAsUrl(this.form, this.action))
							.request(this.form.method);
				}
				return this;
				
			},
	
			request : function(_method){	
				if(_method && _method!='')
					this.method = _method;
				
				var urlOnly='';
				var parametersOnly='';
				var sendJson;
		
				if(this.asJson==false && this.json && this.json.length>0)
					this.asJson=true;
				else if(this.asXml==false && this.xml && this.xml.length>0)
					this.asXml=true;
				else if(this.asMpart==false && this.mpart)
					this.asMpart=true;		
		
				if(this.asJson==false && this.asXml==false && this.asMpart==false){
					
					if(this.url){
						if(this.url.indexOf('?')>-1){
							var pos = this.url.indexOf('?');
							urlOnly = this.url.substring(0,pos);
							parametersOnly = this.url.substring(pos+1);
						}else{
							urlOnly=this.url;
						}
					}
					if(parametersOnly=='') 
						parametersOnly+='js4ajax=true';
					else 
						parametersOnly+='&js4ajax=true';
					
				}else if(this.asJson){	
					
					sendJson = JSON.parse(this.json);
					if(this.url){
						if(this.url.indexOf('?')>-1){		
							var pos = this.url.indexOf('?');
							urlOnly = this.url.substring(0,pos);
					    	var urlParameters=this.url.substring(pos+1,this.url.length);
					    	if(urlParameters.length>0){
					    		urlParameters = decodeURIComponent(urlParameters);
					    		var chunks = urlParameters.split('&');
					    		for(var c=0; c < chunks.length; c++){
					    			var split = chunks[c].split('=', 2);
					    			sendJson[split[0]] = split[1];
					    		}
					    	}
						}else{
							urlOnly=this.url;
						}
					}
					sendJson['js4ajax'] = 'true';
				
				}else if(this.asXml){	
					
					urlOnly=this.url;
					
				}else if(this.asMpart){
					
					if(this.url){
						if(this.url.indexOf('?')>-1){
							var pos = this.url.indexOf('?');
							urlOnly = this.url.substring(0,pos);
					    	var urlParameters=this.url.substring(pos+1,this.url.length);
					    	if(urlParameters.length>0){
					    		urlParameters = decodeURIComponent(urlParameters);
					    		var chunks = urlParameters.split('&');
					    		for(var c=0; c < chunks.length; c++){
					    			var split = chunks[c].split('=', 2);
					    			this.mpart.append(split[0], (split[1]));
					    		}
					    	}
						}else{
							urlOnly=this.url;
						}
					}
					this.mpart.append('js4ajax','true');			
				}
				
		
				if(this.start && this.start!=''){
		    		if (typeof this.start === 'function') {
		    			this.start();
		    		}else{
		    			eval(this.start + '()');
		    		}
		    	}
		
				if(this.target){
					try{
						if(this.progressWait && this.progressWait!='')
							this.target.innerHTML=this.progressWait;
					}catch(e){
					}
				}
		
				var http_request = false;
				var setResponseType=false;
		
		
		
		
			    if (window.ActiveXObject) { // IE
			       try {
		
			          http_request = new ActiveXObject('Msxml2.XMLHTTP');
			       } catch (e) {
			          try {
			             http_request = new ActiveXObject('Microsoft.XMLHTTP');
		
			          } catch (e) {}
			       }
			    }
			    if (window.XMLHttpRequest) { 
				       http_request = new XMLHttpRequest();
				       
				       if(this.responseType && this.responseType!=''){
				    	   try{
				    		   http_request.responseType = this.responseType;
				    		   setResponseType=true;
				    	   }catch(e){
				    	   }
				       }else{   
					       if (http_request.overrideMimeType) {
					    	   if(this.mimeType && this.mimeType!='')
					    		   http_request.overrideMimeType(this.mimeType);
					    	   else
					    		   http_request.overrideMimeType('text/html');
					       }
				       }
		
			    }
			    if (!http_request) {
			    	if(instance.error && instance.error!=''){
		        		if (typeof instance.error === 'function') {
		        			instance.error(http_request, null, 'Cannot create XMLHTTP instance');
		        		}else{
		        			eval(instance.error + '(http_request, null, "Cannot create XMLHTTP instance")');
		        		}
		        	}else
		        		alert('Cannot create XMLHTTP instance');
			       return;
			    }
		
			    var instance  = this.clone();
			    http_request.onreadystatechange = function() {
			    	try{
				    	if (http_request.readyState == 4) {
				    		
				    		var statusAccepted = false;				    		
				    		var _fready;
				    		var _fsuccess;
				    		
				    		if (http_request.status == 200 ) {
				    			statusAccepted = true;
				    			if(instance.ready && instance.ready!='')
			            			_fready = instance.ready;
				    			if(instance.success && instance.success!='')
			            			_fsuccess = instance.success;
				    		}
				    		if(statusAccepted==false && instance.acceptableStatus && instance.acceptableStatus.length>0){
				    			var acceptable = null;
			            		var i=0;
			            		while(!acceptable && i<instance.acceptableStatus.length){
			            			if(instance.acceptableStatus[i].status == http_request.status)
			            				acceptable = instance.acceptableStatus[i];
			            			i++;				            			
			            		}
				            	if(acceptable){
				            		statusAccepted = true;
				            		if(acceptable.ready && acceptable.ready!='')
				            			_fready = acceptable.ready;
				            		else if(instance.ready && instance.ready!='')
				            			_fready = instance.ready;
				            		if(acceptable.success && acceptable.success!='')
				            			_fsuccess = acceptable.success;
				            		else if(instance.success && instance.success!='')
				            			_fsuccess = instance.success;
				            	
				            	}				            	
				    		}
				    		if(statusAccepted==true){
				            	if(_fready){
				            		if (typeof _fready === 'function') {
				            			_fready(http_request);
				            		}else{
				            			eval(_fready + '(http_request)');
				            		}
				            	}else{
				            		if(instance.target)
				            			instance.target.innerHTML=http_request.responseText;
				            	}
				            	if(_fsuccess){
				            		if (typeof _fsuccess === 'function') {
				            			_fsuccess(http_request);
				            		}
				            		else
				            			eval(_fsuccess + '(http_request)');				            		
				            	}				    			
				    		}else{
				            	if(instance.fail && instance.fail!=''){
				            		if (typeof instance.fail === 'function') {
				            			instance.fail(http_request);
				            		}else{
				            			eval(instance.fail + '(http_request)');
				            		}
				            	}		            	
				            }

			            
				            try{
				            	http_request.close();
				            }catch(e){
				            }
				        }
			    	}catch(e){
			    		if(instance.error && instance.error!=''){
		            		if (typeof instance.error === 'function') {
		            			instance.error(http_request, e, e.toString());
		            		}else{
		            			eval(instance.error + '(http_request, e, e.toString())');
		            		}
		            	}else
		            		alert('There was a generic problem with callback_function():'+e.toString());
			    	}
			    	
			    	if(instance.finish && instance.finish!=''){
		        		if (typeof instance.finish === 'function') {
		        			instance.finish(http_request);
		        		}else{
		        			eval(instance.finish + '(http_request)');
		        		}
		        	}
		
		        };
		
		
		
		        if(this.method && this.method!='')
		        	http_request.open(this.method, urlOnly, this.asynchronous);
		        else
		        	http_request.open('POST', urlOnly, this.asynchronous);
		        
			    if(this.responseType && this.responseType!='' && setResponseType==false){
			    	try{
			    		  http_request.responseType = this.responseType;
			    	}catch(e){
			    	}
			    }
		
			    if(this.contentType && this.contentType!='')
			    	http_request.setRequestHeader('Content-type', this.contentType);
			    else
			    	http_request.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
			    if(this.contentEncoding && this.contentEncoding!='')
			    	http_request.setRequestHeader('Content-Encoding', this.contentEncoding);
			    else
			    	http_request.setRequestHeader('Content-Encoding', 'iso-8859-1');
		
			    if(this.asJson==false && this.asXml==false && this.asMpart==false){
			    	http_request.send(parametersOnly);
			    }else if(this.asJson){
			    	http_request.send(JSON.stringify(sendJson));
			    }else if(this.asXml){
			    	http_request.send(this.xml);
			    }if(this.asMpart){
			    	http_request.send(this.mpart);
			    }
			    
			    return this;
		
			},

	
			getParametersAsUrl : function(frm,_url) {
		
			    var getstr = '?';
			    if(_url.indexOf('?')>-1) getstr='&';
			    if(this.base64){
			    	getstr+='$inputBase64=true&';
			    	if(_url.indexOf('?')>-1){
			        	var urlParameters=_url.substring(_url.indexOf('?')+1,_url.length);
			        	if(urlParameters.length>0){
			        		urlParameters = decodeURIComponent(urlParameters);
			        		var chunks = urlParameters.split('&');
			        		for(var c=0; c < chunks.length; c++){
			        			var split = chunks[c].split('=', 2);
			        			getstr+=split[0]+'='+encodeURIComponent(base64_encode(split[1]))+'&';
			        		}
			        	}
			        }
			    }
		
			    if(frm){
				    for (i=0; i<frm.elements.length; i++) {
						var element = frm.elements[i];
						var element_name;
						if(element.name && element.name!='')
							element_name=element.name;
						else if(element.id && element.id!='')
							element_name=element.id;
						
						if(	element_name &&
							_url.indexOf('?'+element_name+'=')==-1 &&
							_url.indexOf('&'+element_name+'=')==-1){
							
								if	(element.type.toUpperCase() == 'TEXT' ||
							         element.type.toUpperCase() == 'HIDDEN' ||
							         element.type.toUpperCase() == 'PASSWORD') {
									if(this.base64)
										getstr += element_name + '=' + encodeURIComponent(base64_encode(element.value)) + '&';
									else	
										getstr += element_name + '=' + encodeURIComponent(element.value) + '&';
							    }
								if	(element.type.toUpperCase() == 'TEXTAREA') {
									if(this.base64)
										getstr += element_name + '=' + encodeURIComponent(base64_encode(element.value)) + '&';
									else	
							        	getstr += element_name + '=' + encodeURIComponent(element.value) + '&';
								}
		
							    if (element.type.toUpperCase() == 'CHECKBOX') {
							    	if (element.checked) {
							    		if(this.base64)
							    			getstr += element_name + '=' + encodeURIComponent(base64_encode(element.value)) + '&';
							    		else	
							    			getstr += element_name + '=' + encodeURIComponent(element.value) + '&';
							        } else {
							        	if(this.base64)
							        		getstr += element_name + '=&';
							        	else	
							        		getstr += element_name + '=&';
							        }
							    }
							    if (element.type.toUpperCase() == 'RADIO') {
							    	if (element.checked) {
							    		if(this.base64)
							    			getstr += element_name + '=' + encodeURIComponent(base64_encode(element.value)) + '&';
							    		else
							    			getstr += element_name + '=' + encodeURIComponent(element.value) + '&';
							        }
							    }
		
								if (element.type.toUpperCase().indexOf('SELECT')==0) {
							    	var sel = element;
							    	try{
							    		if(this.base64)
							    			getstr += sel.name + '=' + encodeURIComponent(base64_encode(sel.options[sel.selectedIndex].value)) + '&';
							    		else
							    			getstr += sel.name + '=' + encodeURIComponent(sel.options[sel.selectedIndex].value) + '&';
							    	}catch(e){
							    	}
							    }
						}
				    }
			    }
			    return getstr;
			 },

			getParametersAsJson : function(frm,_url) {
			    var issue;
			    
			    if(this.json)
			    	issue = JSON.parse(this.json);
			    else
			    	issue = {};
		
			    if(this.base64)
			    	issue[$inputBase64]='true';
		
			    if(_url.indexOf('?')>-1){
			    	var urlParameters=_url.substring(_url.indexOf('?')+1,_url.length);
			    	if(urlParameters.length>0){
			    		urlParameters = decodeURIComponent(urlParameters);
			    		var chunks = urlParameters.split('&');
			    		for(var c=0; c < chunks.length; c++){
			    			var split = chunks[c].split('=', 2);
			    			if(this.base64)
			    				issue[split[0]] = base64_encode(split[1]);
			    			else
			    				issue[split[0]] = split[1];
			    		}
			    	}
			    }
		
			    try{
				    for (i=0; i<frm.elements.length; i++) {
				 		var element = frm.elements[i];
				 		
						var element_name;
						
						if(element.name && element.name!='')
							element_name=element.name;
						else if(element.id && element.id!='')
							element_name=element.id;
				 		
						if(	element_name &&
							_url.indexOf('?'+element_name+'=')==-1 &&
							_url.indexOf('&'+element_name+'=')==-1){
							
		
								if	(element.type.toUpperCase() == 'TEXT' ||
							         element.type.toUpperCase() == 'HIDDEN' ||
							         element.type.toUpperCase() == 'PASSWORD') {
									if(this.base64)
										issue[element_name ] = base64_encode(element.value);
									else
										issue[element_name ] = element.value;
							    }
								if	(element.type.toUpperCase() == 'TEXTAREA') {
									if(this.base64)
										issue[element_name ] = base64_encode(element.value);
									else
										issue[element_name ] = element.value;
								}
		
							    if (element.type.toUpperCase() == 'CHECKBOX') {
							    	if (element.checked) {
							    		if(this.base64)
							    			issue[element_name ] = base64_encode(element.value);
							    		else
							    			issue[element_name ] = element.value;
							        } else {
										issue[element_name ] = '';
		
							        }
							    }
							    if (element.type.toUpperCase() == 'RADIO') {
							    	if (element.checked) {
							    		if(this.base64)
							    			issue[element_name ] = base64_encode(element.value);
							    		else
							    			issue[element_name ] = element.value;
							        }
							    }
		
								if (element.type.toUpperCase().indexOf('SELECT')==0) {
							    	var sel = element;
							    	try{
							    		if(this.base64)
							    			issue[sel.name ] = base64_encode(sel.options[sel.selectedIndex].value);
							    		else
							    			issue[sel.name ] = sel.options[sel.selectedIndex].value;
							    	}catch(e){
							    	}
							    }
		
						}
		
				    }
			    }catch(e){
			    }
		
		
			    return JSON.stringify(issue);
			},
	
			getParametersAsMpart : function(frm,_url) {
		
				if(window.FormData){
					var formdata;
					if(this.mpart)
						formdata = this.mpart;
					else
						formdata = new FormData();
		
					if(this.base64)
						formdata.append('$inputBase64','true');
					
				    if(_url.indexOf('?')>-1){
				    	var urlParameters=_url.substring(_url.indexOf('?')+1,_url.length);
				    	if(urlParameters.length>0){
				    		urlParameters = decodeURIComponent(urlParameters);
				    		var chunks = urlParameters.split('&');
				    		for(var c=0; c < chunks.length; c++){
				    			var split = chunks[c].split('=', 2);
				    			if(this.base64)
				    				formdata.append(split[0], base64_encode(split[1]));
				    			else
				    				formdata.append(split[0], split[1]);
				    		}
				    	}
				    }
		
				    try{
					    for (i=0; i<frm.elements.length; i++) {
					 		var element = frm.elements[i];
					 		
							var element_name;
							
							if(element.name && element.name!='')
								element_name=element.name;
							else if(element.id && element.id!='')
								element_name=element.id;
					 		
							if(	element_name &&
								_url.indexOf('?'+element_name+'=')==-1 &&
								_url.indexOf('&'+element_name+'=')==-1){
		
		
		
									if	(element.type.toUpperCase() == 'TEXT' ||
								         element.type.toUpperCase() == 'HIDDEN' ||
								         element.type.toUpperCase() == 'PASSWORD') {
										if(this.base64)
											formdata.append(element_name, base64_encode(element.value));
										else
											formdata.append(element_name, element.value);
								    }
									if	(element.type.toUpperCase() == 'TEXTAREA') {
										if(this.base64)
											formdata.append(element_name, base64_encode(element.value));
										else
											formdata.append(element_name, element.value);
									}
		
								    if (element.type.toUpperCase() == 'CHECKBOX') {
								    	if (element.checked) {
								    		if(this.base64)
								    			formdata.append(element_name, base64_encode(element.value));
								    		else
								    			formdata.append(element_name, element.value);
								        } else {
								        	formdata.append(element_name, '');
								        }
								    }
								    if (element.type.toUpperCase() == 'RADIO') {
								    	if (element.checked) {
								    		if(this.base64)
								    			formdata.append(element_name, base64_encode(element.value));
								    		else
								    			formdata.append(element_name, element.value);
								        }
								    }
		
									if (element.type.toUpperCase().indexOf('SELECT')==0) {
								    	var sel = element;
								    	try{
								    		if(this.base64)
								    			formdata.append(sel.name, base64_encode(sel.options[sel.selectedIndex].value));
								    		else
								    			formdata.append(sel.name, sel.options[sel.selectedIndex].value);
								    	}catch(e){
								    	}
								    }
								    if (element.type.toUpperCase() == 'FILE') {
							    		formdata.append(element_name, element.files[0]);
								    }
		
							}
		
					    }
				    }catch(e){
				    }
		
		
				    return formdata;
				}else{
					alert('Features supported for object FormData (). Available in Chrome, FireFox, Safari, IE9...');
				}
			}
}




return clajax;

});


function base64_encode(data) {

	  // From: http://phpjs.org/functions
	  // +   original by: Tyler Akins (http://rumkin.com)
	  // +   improved by: Bayron Guevara
	  // +   improved by: Thunder.m
	  // +   improved by: Kevin van Zonneveld (http://kevin.vanzonneveld.net)
	  // +   bugfixed by: Pellentesque Malesuada
	  // +   improved by: Kevin van Zonneveld (http://kevin.vanzonneveld.net)
	  // +   improved by: Rafal Kukawski (http://kukawski.pl)

	  // mozilla has this native
	  // - but breaks in 2.0.0.12!

	  if (typeof this.window['btoa'] === 'function') {
//	      return btoa(data);
		  return btoa(unescape(encodeURIComponent(data)));
	  }
	  var b64 = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=';
	  var o1, o2, o3, h1, h2, h3, h4, bits, i = 0,
	    ac = 0,
	    enc = '',
	    tmp_arr = [];

	  if (!data) {
	    return data;
	  }

	  do { // pack three octets into four hexets
	    o1 = data.charCodeAt(i++);
	    o2 = data.charCodeAt(i++);
	    o3 = data.charCodeAt(i++);

	    bits = o1 << 16 | o2 << 8 | o3;

	    h1 = bits >> 18 & 0x3f;
	    h2 = bits >> 12 & 0x3f;
	    h3 = bits >> 6 & 0x3f;
	    h4 = bits & 0x3f;

	    // use hexets to index into b64, and append result to encoded string
	    tmp_arr[ac++] = b64.charAt(h1) + b64.charAt(h2) + b64.charAt(h3) + b64.charAt(h4);
	  } while (i < data.length);

	  enc = tmp_arr.join('');

	  var r = data.length % 3;

	  return (r ? enc.slice(0, r - 3) : enc) + '==='.slice(r || 3);

	}


