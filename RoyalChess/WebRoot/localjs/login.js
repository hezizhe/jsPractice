function validateLoginUserFunc(){
	flag = false;
	var playerName = $("#playerName").val();
	var password = $("#inputPassword").val();
	$.ajax({
		url: 'validLogin',
		type: 'POST',
		async:false,
		data:{'player.playerName':playerName,'player.password':password},
		dataType: 'html',
		success: function(result){
			if(result == "wrongpsw"){
				humane.error("用户名或密码错误！");
			}else if(result == "fail"){
				humane.error("异常：系统没有反应！");
			}else{
				flag = true;
			}
		}
	});
	return flag;
}

