
function generateLink(){
	document.getElementById("codein").disabled=false;
	document.getElementById("codebtn").disabled=false;
	document.getElementById("download").disabled=true;
	document.getElementById("handimg").setAttribute("src", "");
	var req;
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		req=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		req=new ActiveXObject("Microsoft.XMLHTTP");
	}
	req.onreadystatechange=function()
	{
		if (req.readyState==4 && req.status==200)
		{
			document.getElementById("link").
			setAttribute("href", 
					req.responseXML.getElementsByTagName("url")[0].
					childNodes[0].nodeValue);
		}
	};
	req.open("GET","authurl",true);
	req.send();
}

function loadModal() {
	$('#progressbar').modal({show: true, 
        backdrop: 'static'});
}

function unloadModal(){
	$('#progressbar').modal('hide');
}

function HomeViewModal(){
	var self = this;

	self.sendCode = function(){
		loadModal();
		var code = document.getElementById('codein').value;
		var req;
		var url = "getnetwork";
		var params = "code=" + code;
		if (typeof XMLHttpRequest != "undefined") {
			req = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}
		req.open("POST", url, true);
		req.setRequestHeader("Content-type",
		"application/x-www-form-urlencoded");
		req.setRequestHeader("Content-length", params.length);
		req.setRequestHeader("Connection", "close");
		req.onreadystatechange = function() {
			if (req.readyState == 4) {
				if (req.status == 200) {
					if (req.responseXML != null) {
						unloadModal();
						document.getElementById("codein").disabled=true;
						document.getElementById("codebtn").disabled=true;
						document.getElementById("download").disabled=false;
						document.getElementById("handimg").setAttribute("src", "img/hand-left.png");
					}
				}
			}
		};
		req.send(params);
		document.getElementById("download").disabled = false;
	};
}
ko.applyBindings(new HomeViewModal());