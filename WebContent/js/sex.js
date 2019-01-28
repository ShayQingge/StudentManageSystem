/**
 * 
 */
$(function(){
	$("#sgender").change(function(){
		var sgender = $(this).val();
		alert("sgender="+sgender);
		$.post("/StudentManageSystem/SexServlet",{sgender:sgender},function(data,status){
			$("#body").html("<tr colspan='8' align='center'><td></td></tr>");
			$(data).each(function(index,c){
				alert("phone = " + c.phone);
			});
		},"json");
	});
});

