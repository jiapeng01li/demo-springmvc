//默认的回调函数
function callback(data) {
	if (data.retStatus && "0" != data.retStatus) {
		layer.msg(data.errorCode + " " + data.errorMsg, {
			icon : 5,
			time : 1000
		});
	} else {
	}
}

/**
 * 异步调用方法，需要传递回调函数
 * 
 * @param arg
 * @param url
 * @param func
 */
function ajaxRequest(arg, url, func) {
	arg.channel = "P";
	arg.accessSource = "1";
	arg.version = "1.0";
	$.ajax({
		type : "POST",
		url : url,
		contentType : 'application/json;charset=UTF-8',
		data : JSON.stringify(arg),
		dataType : "json",
		success : function(data) {// data为json对象
			if (data.retStatus && "0" != data.retStatus && "000006"==data.errorCode) {
			    if(parent.frames.length>0)
					parent.window.location.href = ctx+"/login";
			    else
			    	window.location.href = ctx+"/login";
			}
			if (func) {
				var _function = eval(func);
				_function(data);
			} else {
				callback(data);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			layer.msg("调用服务发生异常！错误描述\"" + errorThrown + "\"", {
				icon : 5,
				time : 1000
			});
		}
	});
}

/**
 * 同步调用方法，返回结果
 * 
 * @param arg
 * @param url
 * @param func
 */
function ajaxRequestSync(arg, url) {
	arg.channel = "P";
	arg.accessSource = "1";
	arg.version = "1.0";
	var res;
	$.ajax({
		type : "POST",
		async : false,
		url : url,
		contentType : 'application/json;charset=UTF-8',
		data : JSON.stringify(arg),
		dataType : "json",
		success : function(data) {// data为json对象
			if (data.retStatus && "0" != data.retStatus && "000006"==data.errorCode) {
			    if(parent.frames.length>0)
					parent.window.location.href = ctx+"/login";
			    else
			    	window.location.href = ctx+"/login";
			}
			res = data;
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			layer.msg("调用服务发生异常！错误描述\"" + errorThrown + "\"", {
				icon : 5,
				time : 1000
			});
		}
	});
	return res;
}

//获取cookie值
function setCookie(name, value, hours, path) {
    var name = escape(name);
    var value = escape(value);
    var expires = new Date();
    expires.setTime(expires.getTime() + hours * 3600000);
    path = path == "" ? "": ";path=" + path;
    _expires = (typeof hours) == "string" ? "": ";expires=" + expires.toUTCString();
    document.cookie = name + "=" + value + _expires + path;
}

//获取cookie值
function getCookieValue(name) {
    var name = escape(name);
    //读cookie属性，这将返回文档的所有cookie
    var allcookies = document.cookie;
    //查找名为name的cookie的开始位置
    name += "=";
    var pos = allcookies.indexOf(name);
    //如果找到了具有该名字的cookie，那么提取并使用它的值
    if (pos != -1) { //如果pos值为-1则说明搜索"version="失败
        var start = pos + name.length; //cookie值开始的位置
        var end = allcookies.indexOf(";", start); //从cookie值开始的位置起搜索第一个";"的位置,即cookie值结尾的位置
        if (end == -1) end = allcookies.length; //如果end值为-1说明cookie列表里只有一个cookie
        var value = allcookies.substring(start, end); //提取cookie的值
        return (value); //对它解码   
    } else return ""; //搜索失败，返回空字符串
}

//删除cookie
function deleteCookie(name, path) {
    var name = escape(name);
    var expires = new Date(0);
    path = path == "" ? "": ";path=" + path;
    document.cookie = name + "=" + ";expires=" + expires.toUTCString() + path;
}

/**
 * 加载select下拉框
 * @param comboIds 下拉框对象
 * @param dictId 字典编号
 * @param async 是否异步
 */
function loadCombo(comboIds, dictId, async){
	var reqData = {};
	reqData["dictId"] = dictId;
	reqData["channel"]  = "P";
	reqData["accessSource"] = "1";
	reqData["version"] = "1.0";
	$.ajax( {   
	     type : "POST",   
	     url : ctx+"/web/dict/getComboData",
	     contentType : 'application/json;charset=UTF-8', 
	     data : JSON.stringify(reqData),
	     async : async,
	     dataType: "json",
	     success : function(data) {
	    	 if (data.retStatus && "0" == data.retStatus){
	    		 if(comboIds instanceof Array){
	    			 for(var n in comboIds){
		    			 var combo = $("[id='" + comboIds[n] + "']");
			    		 var comboDatas = data.comboDatas;
			    		 combo.empty();
			    		 for(var i in comboDatas) {
			    			 combo.append("<option value='" + comboDatas[i].code + "'>" + comboDatas[i].code + " " + comboDatas[i].codeDesc + "</option>");
			    		 }
		    		 }
	    		 }else{
	    			 var comboId = comboIds;
	    			 var combo = $("[id='" + comboId + "']");
		    		 var comboDatas = data.comboDatas;
		    		 combo.empty();
		    		 for(var i in comboDatas) {
		    			 combo.append("<option value='" + comboDatas[i].code + "'>" + comboDatas[i].code + " " + comboDatas[i].codeDesc + "</option>");
		    		 }
	    		 }
	    		 
	    		 
	    	 }else{
	    		 layer.msg(data.errorCode + " " + data.errorMsg, {icon:5, time:1000});
	    	 }
	     },   
	     error :function(XMLHttpRequest, textStatus, errorThrown){
	    	 layer.msg("请求服务器发生异常！错误描述\""+errorThrown+"\"", {icon:5, time:1000});
	     }   
	 });
}

/**
 * 获取数据字典列表
 * @param dictId 数据字典编号
 * @param dictDatas 需要返回的字典对象
 * @param async 是否异步查询
 * @returns {Array} 返回字典信息
 */
function getComboData(dictId, dictDatas, async){
	var reqData = {};
	reqData["dictId"] = dictId;
	reqData["channel"]  = "P";
	reqData["accessSource"] = "1";
	reqData["version"] = "1.0";
	var res = [];
	$.ajax({
		type : "POST",
		async : async,
		url : ctx+"/web/dict/getComboData",
		contentType : 'application/json;charset=UTF-8',
		data : JSON.stringify(reqData),
		dataType : "json",
		success : function(data) {// data为json对象
			if (data.retStatus && "0" == data.retStatus){
				res = data.comboDatas;
				for(var i in res){
					dictDatas.push(res[i]);
				}
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
	});
	return res;
}

/**
 * 根据code获取数据字典描述
 * @param comboDatas 数据字典
 * @param code 数据字典code
 * @returns {String}
 */
function getComboDataDesc(comboDatas, code){
	if(""==code || null==code){
		return "";
	}
	var desc = code;
	for ( var i in comboDatas){
		if(comboDatas[i].code == code){
			desc = comboDatas[i].code + " " + comboDatas[i].codeDesc;
			break;
		}
	}
	return desc;
}