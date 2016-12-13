

$(document).ready(function(){
	MrcPanel.init();
});


MrcFormula = {
	
	A_Max: 10000, B_Max: 1000, C_Max: 100, D_Max: 50, E_Max: 10,
	  			  
				  B_Eat: 0.3,  C_Eat: 0.5, D_Eat: 0.7,  E_Eat: 3,
				  
				  B_Brt: 1.04,  C_Brt: 1.03, D_Brt: 1.02,  E_Brt: 1.01,
	  			  
				  B_Buy: 500,  C_Buy: 30,  D_Buy: 10,  E_Buy: 2,
	
	consumeEach: function(eat, pop, available){
		
		return consumeEach = Math.round(pop * eat / available);
		
	},
	
	getHungry: function(eat, pop, leftover){
		
		
		
	}
	
}


MrcLocalTimer = {
	running: false,
	step: 1000,
	init: function(){
		MrcLocalTimer.running = true;
		window.setInterval(MrcLocalTimer.run,MrcLocalTimer.step);
	},
	run: function(){
		for(var i in MrcManager.creatures){
			if (MrcManager.creatures[i].substring(0,1) != "a"){
				MrcManager.consume(MrcManager.creatures[i]);
			}
		}
		for(var i in MrcManager.creatures){
			var panel = $('#'+MrcManager.creatures[i]);
			var pop = parseInt(panel.attr('pop'));
			var dead = parseInt(panel.attr('dead'));
			var newPop = pop - dead > 0 ? pop - dead : 0;
			var max = parseInt(panel.attr('max'));
			var width = Math.round((newPop/max)*100);
			$('#' + MrcManager.creatures[i]).attr('dead',0);
			$('#' + MrcManager.creatures[i]).attr('pop',newPop);
			if (MrcManager.creatures[i].substring(0,1) != "a"){
				$('#' + MrcManager.creatures[i]+'-pop').width(width+'%');
			}
		}
		var popA1 = parseInt($('#a1').attr('pop'));
		var popA2 = parseInt($('#a2').attr('pop'));
		var popA3 = parseInt($('#a3').attr('pop'));
		var spawn = Math.round((MrcFormula.A_Max - (popA1 + popA2 + popA3)) / 3);
		$('#a1').attr('pop',parseInt(popA1+spawn));
		$('#a2').attr('pop',parseInt(popA2+spawn));
		$('#a3').attr('pop',parseInt(popA3+spawn));
		$('#a1-pop').width(Math.round(((popA1+spawn)/MrcFormula.A_Max)*100)+'%');
		$('#a2-pop').width(Math.round(((popA2+spawn)/MrcFormula.A_Max)*100)+'%');
		$('#a3-pop').width(Math.round(((popA3+spawn)/MrcFormula.A_Max)*100)+'%');
	}
		
}

MrcManager = {
		
	creatures: new Array(),
	register: function(id){
		MrcManager.creatures.push(id);
	},
	add: function(divId){
		var panel = $('#'+divId);
		var category = panel.attr('id').substring(0,1);
		var step;
		switch(category) {
			case "b" : step = MrcFormula.B_Buy; break;
			case "c" : step = MrcFormula.C_Buy; break;
			case "d" : step = MrcFormula.D_Buy; break;
			case "e" : step = MrcFormula.E_Buy; break;
		}
		var pop = parseInt(panel.attr('pop'));
		panel.attr('pop',pop+step);
	},
	consume: function(divId){
		var eatAmount = MrcPrey.getEatAmount(divId);
		var pop = parseInt($('#' + divId).attr('pop'));
		var hungry = parseInt($('#' + divId).attr('hungry'));
		if (pop > 0){
			var availablePrey = MrcPrey.getPrey(divId);	
			if (availablePrey.length > 0){
				var consumeEach = MrcFormula.consumeEach(eatAmount,pop,availablePrey.length);
				var leftOver = 0;
				for (i=0; i<availablePrey.length; i++){
					var preyPop = parseInt($('#' + availablePrey[i]).attr('pop'));
					var deadPrey = 0;
					if (preyPop < consumeEach + leftOver){
						deadPrey = preyPop;
						leftOver = (consumeEach + leftOver) - preyPop;
					} else {
						deadPrey = consumeEach + leftOver;
						leftOver = 0;
					}
					var dead = parseInt($('#' + availablePrey[i]).attr('dead'));					
					$('#' + availablePrey[i]).attr('dead',dead + deadPrey);
				}
				if (leftOver > 0){
					var hungry = Math.round(leftOver / consumeEach);
					var dead = parseInt($('#' + divId[i]).attr('dead'));
					$('#' + divId[i]).attr('dead',dead + hungry);
				}
			} else {
				var maxPop = $('#' + divId).attr('max');
				var hungry = Math.round(maxPop / 10);
				var dead = parseInt($('#' + divId).attr('dead'));
				$('#' + divId).attr('dead',dead + hungry);
			}
		}
	}
};

MrcPanel = {
	init: function(){
		
		MrcPanel.addPanel(200,100,460,1,"e");
		MrcPanel.addPanel(1160,100,460,2,"e");
		
		var dy = 200;
		var dx = 80;
		var dwidth = 220;
		
		for (di=1; di<=7; di++){
			MrcPanel.addPanel(dx,dy,dwidth,di,"d");
			dx = dx + dwidth + 20;
		}
		
		
		var cy = 300;
		var cx = 20;
		var cwidth = 100;
		
		for (ci=1; ci<=15; ci++){
			MrcPanel.addPanel(cx,cy,cwidth,ci,"c");
			cx = cx + cwidth + 20;
		}
		
		var by = 400;
		var bx = 80;
		var bwidth = 220;
		
		for (bi=1; bi<=7; bi++){
			MrcPanel.addPanel(bx,by,bwidth,bi,"b");
			bx = bx + bwidth + 20;
		}
		
		var ay = 500;
		var ax = 200;
		var awidth = 460;
		
		for (ai=1; ai<=3; ai++){
			MrcPanel.addPanel(ax,ay,awidth,ai,"a");
			ax = ax + awidth + 20;
		}
		
		MrcLocalTimer.init();
		
		$('.cgroup').mouseover(function(){
			$(this).find('div.cpanel').hide();
			$(this).find('div.ccontrol').show();
		});
		
		$('.cgroup').mouseout(function(){
			$(this).find('div.ccontrol').hide();
			$(this).find('div.cpanel').show();
		});
		
		$('.cbuy').click(function(){
			MrcManager.add($(this).attr('id').split('-')[0]);
		});
		
		$('.csell').click(function(){
			alert("Sell: " + $(this).attr('id').split('-')[0]);
		});
		
	},
	addPanel: function(x,y,width,i,id){
		var divId = id + i;
		var pop = 0;
		var maxPop;
		var prey = MrcPrey.whatPrey(divId);
		switch(id) {
			case "a" : maxPop = MrcFormula.A_Max; pop=Math.round(maxPop/3); break;
			case "b" : maxPop = MrcFormula.B_Max; break;
			case "c" : maxPop = MrcFormula.C_Max; break;
			case "d" : maxPop = MrcFormula.D_Max; break;
			case "e" : maxPop = MrcFormula.E_Max; break;
		}
		$(".MrcPanel").append("<div id='" + divId + "' pop='" + pop + "' hungry='0' pregnant='0' dead='0' max='" + maxPop + "' prey='" + prey + "' class='w3-container cgroup' style='width: " + width + "px; left: " + x + "px; top: " + y + "px;'>" +
				"<div class='w3-row'>" +
				"<div id='" + divId + "-star' class='cstar w3-col m6'><img src='img/0s3.png'/></div>" +
				"<div id='" + divId + "-trend' class='ctrend w3-col m6' style='display:none'><img src='img/pop_up.png'/></div>" +
				"</div>" +
				
				"<div class='w3-row cnews w3-animate-zoom' style='display:none; background-image: url(\"/mrc/img/" + id + i + ".png\")'>" +
				"<div class='change w3-col m12' style='height: 40px; width: " + width + "px; padding-left: 45px;'><span id='" + id + i + "-change'>+0</span>" +
				"</div>" +
				"</div>" +
				
				"<div class='w3-row ccontrol' style='display:none; background-image: url(\"img/" + id + i + ".png\")'>" +
				"<button id='" + divId + "-sell' class='csell w3-col m1  w3-btn w3-red w3-opacity-max w3-disabled' style='height: 40px; width: 40px; padding: 0px;'>-" +
				"</button>" +
				"<button id='" + divId + "-buy' class='cbuy w3-col m1 w3-btn w3-green w3-opacity-min' style='height: 40px; width: " + (width - 40) + "px; padding: 0px;'>+" +
				"</button>" +
				"</div>" +	
				
				"<div class='w3-row cpanel'>" +
				"<div class='cbar w3-col m1 w3-progress-container' style='height: 40px; width: " + width + "px; background-image: url(\"/mrc/img/" + id + i + ".png\")'>" +
				"<div id='" + divId + "-pop' class='w3-progressbar w3-opacity' style='width:0%;'></div>" +
				"</div>" +
					
				"</div>");
		MrcManager.register(divId);
	}
}

MrcPrey = {
		
	whatPrey: function(cid){		
		var prey;		
		switch(cid) {
			case "b1" : prey="a1"; break;
			case "b2" : prey="a1"; break;
			case "b3" : prey="a1,a2"; break;
			case "b4" : prey="a2"; break;
			case "b5" : prey="a2,a3"; break;
			case "b6" : prey="a3"; break;
			case "b7" : prey="a3"; break;
			case "c1" : prey="b1"; break;
			case "c2" : prey="b1"; break;
			case "c3" : prey="b1,b2"; break;
			case "c4" : prey="b2"; break;
			case "c5" : prey="b2,b3"; break;
			case "c6" : prey="b3"; break;
			case "c7" : prey="b3,b4"; break;
			case "c8" : prey="b4"; break;
			case "c9" : prey="b4,b5"; break;
			case "c10" : prey="b5"; break;
			case "c11" : prey="b5,b6"; break;
			case "c12" : prey="b6"; break;
			case "c13" : prey="b6,b7"; break;
			case "c14" : prey="b7"; break;
			case "c15" : prey="b7"; break;
			case "d1" : prey="c1,c2,c3"; break;
			case "d2" : prey="c3,c4,c5"; break;
			case "d3" : prey="c3,c4,c5"; break;
			case "d4" : prey="c5,c6,c7"; break;
			case "d5" : prey="c7,c8,c9"; break;
			case "d6" : prey="c9,c10,c11"; break;
			case "d7" : prey="c11,c12,c13"; break;
			case "e1" : prey="d1,d2,d3"; break;
			case "e2" : prey="d5,d6,d7"; break;		
		}
		return prey;
	},
	getEatAmount(cid){
		var eatAmount;
		var category = cid.substring(0,1);
		switch (category) {
			case "b" : eatAmount=MrcFormula.B_Eat; break;
			case "c" : eatAmount=MrcFormula.C_Eat; break;
			case "d" : eatAmount=MrcFormula.D_Eat; break;
			case "e" : eatAmount=MrcFormula.E_Eat; break;
		}
		return eatAmount;
	},	
	getPrey: function(cid){		
		var availablePrey = new Array();
		if (cid.substring(0,1) != "a"){
			var prey = $('#'+cid).attr('prey').split(',');
			for (i=0; i<prey.length; i++){
				var pop = parseInt($('#' + prey[i]).attr('pop'));
				if (pop > 0)
					availablePrey.push(prey[i]);
			}
		}
		return availablePrey;
	}		
}

