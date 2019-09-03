
		function validate() {
			var code = document.getElementById("code").value;
			var descr = document.getElementById("descr").value;
			
			if(code=="" || code == null || descr == ""|| descr == null){
				alert("Values are Empty");
				return false;
			}
		}
		
		$('#cncl').click(function(){
			if(confirm("do you want to reset text fields?")){
				$('input[type="text"]').val('');
			}					
		});
		
		
