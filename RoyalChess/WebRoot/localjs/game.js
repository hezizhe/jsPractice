var rid = $("#rid").val();
var abledway=[];
var cheaked=[9,9];
var wait;
var cheakstart;

$().ready(function(){
	restoreColour();
	var status = $("#status").val();
	if(status == 0){
		cheakstart = setInterval("cheakStart()",500);
	}if(status == 1){
		getPieces();
		if($("#steps").text()==0){
			var first = $("#first").val()==0?"红":"蓝";
			humane.success("开战！"+first+"方先手");
		}
		if(cheakCamp()==-1){
			wait = setInterval("waitAction()",500);
		}
	}
});

function restoreColour(){
	for(var i=1;i<7;i++){
		for(var j=1;j<7;j++){
			if((i+j)%2==0){
				f(i,j).css({"background-color":"#333333"});
			}else{
				f(i,j).css({"background-color":"#FFF"});
			}
		}
	}
}

function fight(a,b){
	if(a==1 && b==6){
		return true;
	}else if(a==6 && b==1){
		return false;
	}else{
		if(a>=b){
			return true;
		}else{
			return false;
		}
	}
}

function moveable(t,h,tt,hh){
	var cheakedch=f(t,h).attr("class").split("-");
	if(f(tt,hh).attr("class")=="unknown"){
		f(tt,hh).css({"background-color":"#F00"});
	}else if(f(tt,hh).attr("class")=="" || f(tt,hh).attr("class")==undefined){
		f(tt,hh).css({"background-color":"#0F0"});
		abledway[abledway.length]=[tt,hh];
	}else{ 
		var movech=f(tt,hh).attr("class").split("-");
		if(cheakedch[0]==movech[0]){
			f(tt,hh).css({"background-color":"#F00"});
		}else if(fight(cheakedch[1],movech[1])){
			f(tt,hh).css({"background-color":"#0F0"});
			abledway[abledway.length]=[tt,hh];
		}else{
			f(tt,hh).css({"background-color":"#F00"});
		}
	}
}

function active(t,h){
	var status = $("#status").val();
	var nowCamp = cheakCamp();
	if(nowCamp!=-1 && status==1){  //判断玩家阵营为当前回合阵营并且处于战争状态，才进行处理
		if(cheaked[0]==9 && cheaked[1]==9){  //判断当前没有棋子被选中
			var cheakedch=f(t,h).attr("class").split("-");
			if(f(t,h).attr("class")=="unknown"){
				takeAction(t+","+h);
			}else if(cheakedch[0]==("ch"+nowCamp)){ 
				cheaked[0]=t;  cheaked[1]=h;
				f(t,h).css({"background-color":"#FF0"});
				if(t<6)
					moveable(t,h,t+1,h);
				if(t>1)
					moveable(t,h,t-1,h);
				if(h<6)
					moveable(t,h,t,h+1);
				if(h>1)
					moveable(t,h,t,h-1);
				if(abledway.length==0){
					cheaked[0]=9; cheaked[1]=9;
					window.setTimeout("restoreColour()",300);
				}
			}
		}else{
			var canmove=false;
			for(var i=0;i<abledway.length;i++){
				if(abledway[i][0]==t && abledway[i][1]==h){
					canmove=true;
				}
			}
			if(canmove){
				takeAction(cheaked[0]+","+cheaked[1]+"-"+t+","+h);
			}
			abledway.splice(0,abledway.length);
			cheaked[0]=9; cheaked[1]=9;
			window.setTimeout("restoreColour()",200);
		}
	}
}

function takeAction(action){
	$.ajax({
		url: 'takeaction',
		type: 'POST',
		async:false,
		data:{'action':action,'round.id':rid},
		dataType: 'json',
		success: function(result){
			if(result[0] == null || result[0].steps == null && result[0].steps == ""){
				humane.error("网络异常！请尝试刷新网页");
			}else{
				$("#steps").html(result[0].steps);
				if(result[0].show != null && result[0].show != ""){
					var showPiece = action.split(",");
					var role = result[0].show.split("-");
					f(showPiece[0],showPiece[1]).attr("class","ch"+role[0]+"-"+role[1]);
				}else{
					if(result[0].pieceCount != null && result[0].pieceCount != ""){
						var piece = result[0].pieceCount.split("-");
						var camp = piece[0]=="0"?"reds":"blues";
						$("#"+camp).html(piece[1]);
					}
					var pieces = action.split("-");
					var attack = pieces[0].split(",");
					var defense = pieces[1].split(",");
					f(defense[0],defense[1]).attr("class",f(attack[0],attack[1]).attr("class"));
					f(attack[0],attack[1]).attr("class","");
					if(result[0].winlose != null && result[0].winlose != ""){
						if(result[0].winlose == "deadlock"){
							humane.success("胜负未分，平局");
						}
					}
				}
				wait = setInterval("waitAction()",500);
			}
		}
	});
}

function waitAction(){
	var steps = $("#steps").text();
	$.ajax({
		url: 'waitaction',
		type: 'POST',
		async:true,
		data:{'round.id':rid,'steps':steps},
		dataType: 'json',
		success: function(result){
			if(result[0].steps != null && result[0].steps != ""){
				window.clearInterval(wait);
				$("#steps").html(result[0].steps);
				if(result[0].action.indexOf(":")!=-1){
					var showAct = result[0].action.split(":");
					var showPiece = showAct[0].split("-");
					var role = showAct[1].split("-");
					f(showPiece[0],showPiece[1]).attr("class","ch"+role[0]+"-"+role[1]);
				}else{
					if(result[0].pieceCount != null && result[0].pieceCount != ""){
						var piece = result[0].pieceCount.split("-");
						$("#reds").html(piece[0]);
						$("#blues").html(piece[1]);
					}
					var pieces = result[0].action.split("-");
					var attack = pieces[0].split(",");
					var defense = pieces[1].split(",");
					if(f(attack[0],attack[1]).attr("class")!=undefined && f(attack[0],attack[1]).attr("class")!=""){
						f(defense[0],defense[1]).attr("class",f(attack[0],attack[1]).attr("class"));
						f(attack[0],attack[1]).attr("class","");
					}
					if(result[0].winlose != null && result[0].winlose != ""){
						if(result[0].winlose == "deadlock"){
							humane.success("胜负未分，平局");
						}
					}
				}
			}
		}
	});
}

function f(t,h){
	return $("#t"+t+"h"+h);
}

function cheakCamp(){ //判断是为己方回合并返回当前回合阵营,否则返回-1
	var first = $("#first").val();
	var playerCamp = $("#playerCamp").val();
	var steps = $("#steps").text();
	if(first!=null && first!="" && playerCamp!=null && playerCamp!="" && steps!=null && steps!=""){
		var camp = first==0?steps%2:Math.abs(steps%2-1);
		if(camp==playerCamp)
			return camp;
	}
	return -1;
}

function cheakStart(){
	$.ajax({
		url: 'cheakstart',
		type: 'POST',
		async:true,
		data:{'round.id':rid},
		dataType: 'html',
		success: function(result){
			if(result != "nochange"){
				window.location.reload();
			}
		}
	});
}

function getPieces(){
	$.ajax({
		url: 'getpieces',
		type: 'POST',
		async:false,
		data:{'round.id':rid},
		dataType: 'json',
		success: function(result){
			if(result.length>0){
				for ( var i = 0; i < result.length; i++) {
					var piece = result[i];
					var camp = piece.camp==0?"red":"blue";
					if(piece.hide==0)
						f(piece.locationX,piece.locationY).attr("class","unknown");
					else
						f(piece.locationX,piece.locationY).attr("class","ch"+piece.camp+"-"+piece.power);
				}
			}
		}
	});
}
