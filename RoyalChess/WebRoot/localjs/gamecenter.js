var record = $("#record").val();
var time;

$().ready(function(){
	//checkChange();
	time = setInterval("checkChange()",500);
	
	$(".btn-primary").click(function(){
		window.clearInterval(time);
		var btn = $(this);
		var id = btn.attr("id");
		btn.attr("disabled","disabled");
		$.ajax({
			url: 'joingame',
			type: 'POST',
			async:false,
			data:{'round.id':id},
			dataType: 'html',
			success: function(result){
				if(result.indexOf("infighting")>=0){
					if(confirm("当前正在战斗，是否返回战场？")){
						var roundId = result.substring(10);
						window.location.href = "game.action?round.id="+roundId;
					}else{
						time = setInterval("checkChange()",500);
						btn.removeAttr("disabled");
					}
				}else if(result == "sessioninvalid"){
					humane.error("登录信息失效，请重新登录！");
					time = setInterval("checkChange()",500);
					btn.removeAttr("disabled");
				}else if(result == "full"){
					humane.error("该战场已满，请选择其他战场！");
					time = setInterval("checkChange()",500);
					btn.removeAttr("disabled");
				}else if(result == "fail"){
					humane.error("进入战场失败，请稍后重试！");
					time = setInterval("checkChange()",500);
					btn.removeAttr("disabled");
				}else if(result == "success"){
					alert(result);
					window.location.href = "game.action?round.id="+id;
				}
			}
		});
	});
});

function checkChange(){
	$.ajax({
		url: 'checkrecord',
		type: 'POST',
		async:true,
		data:{'record':record},
		dataType: 'html',
		success: function(result){
			if(result != "nochange"){
				window.location.reload();
			}
		}
	});
}
