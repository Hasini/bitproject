
		function validate() {
			var code = document.getElementById("code").value;
			var descr = document.getElementById("descr").value;
			
			if(code=="" || code==null,descr == ""|| descr == null){
				alert("Values are Empty");
				return false;
			}
		}
		
		function clearinputs() {
			alert("hhhhhhhhh");
			document.getElementById("code").value = " ";
			document.getElementById("descr").value = " ";
		} 
