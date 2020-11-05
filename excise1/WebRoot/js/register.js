var userT=false;
var nameT=false;
var passwordT=false;
var mailT=false;
var proT=false;


function checkEmail(){
    
//    console.log("console.log$$$$$$$$$$$$$$$$$$$$$$$$"); 
    var str = $("#E-mail").val(); 
    var pattern = /[\w]*[@][\w]*[\.][com|net|gov]/;
    if(str.length==0){
        document.getElementById("E-mailInfo").innerText="电子邮箱不能为空";
    }else{
        if(pattern.test(str)) {
            	mailT=true;
                document.getElementById("E-mailInfo").innerText="电子邮件地址合法";
        } else {
        	mailT=false;
            document.getElementById("E-mailInfo").innerText="电子邮件地址非法";
        }
    }
    
    
}
function checkRegister(){
    
        var userName=document.getElementById("userName").value;
//        var flag="0";
        $.ajax({
            type: "post",
            url: "ajaxExit.do",
            contentType:"application/x-www-form-urlencoded;charset=utf-8",
            data: {"userName":userName},
            dataType: "json",
            success: function (response) {
                if(response.code==0){
                	userT=true;
//                	console.log("console.log12324"); 
                }
                else{
                	userT=false;
//                	console.log(response+"######################x#"); 
                }
//                console.log(response.code+"(("+response.info);
                document.getElementById("userNameInfo").innerText=response.info;
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
            	 alert(XMLHttpRequest.status);
            	 alert(XMLHttpRequest.readyState);
            	 alert(textStatus);
            }
            
        });
    
    
}
function checkUsername(){
    var str = $("#userName").val(); 
    var pattern = /^[a-zA_Z][a-zA-Z0-9]{4,15}$/;
    console.log(str); 
    if(str.length==0){
        document.getElementById("userNameInfo").innerText="用户名不能为空";
    }else{
        if(pattern.test(str)) {
        	checkRegister();
        } else {
            document.getElementById("userNameInfo").innerText="用户名只能使用英文字母和数字，以字母开头，长度为4到15个字符";
            userT=false;
        }
    }
    
    
}
function checkName(){
    
    var str = $("#name").val(); 
    var pattern = /^[\u4e00-\u9fa5]{2,4}$/;
    if(str.length==0){
        document.getElementById("nameInfo").innerText="真实姓名不能为空";
    }else{
        if(pattern.test(str)) {
            
            document.getElementById("nameInfo").innerText="";
            nameT=true;
        } else {
        	nameT=false;
        	document.getElementById("nameInfo").innerText="真实姓名只能是2-4长度的中文";
        }
    }
    
    
}

function checkPassword(){
    
    var str = $("#password").val(); 
    var pattern =/^{.{4,}$/;
    if(str.length==0){
        document.getElementById("passwordInfo").innerText="密码不能为空";
    }else{
        if(pattern.test(str)) {
        	document.getElementById("passwordInfo").innerText="密码最小长度为4";
        	
        } else {
        	document.getElementById("passwordInfo").innerText="";
            
        }
    }
    
    
}

function recheckedPassword(){
    
    var str1 = $("#checkedPassword").val(); 
    var str2 = $("#password").val();
    
    
    if(str1!=str2||str1.length<4){
    	passwordT=false;
    	document.getElementById("checkedPasswordInfo").innerText="密码不正确或者长度不够";
    }
        
    else {
        document.getElementById("checkedPasswordInfo").innerText="";
        passwordT=true;
    }
    
}






function fillProvince(){
    $.ajax({
        type: "post",
        url: "queryProvinceCity.do",
        data: {},
        dataType: "json",
        success: function (response) {
            var provinceElement=document.getElementById("province");
            console.log("console.log");
            // ���select������option
            provinceElement.options.length=0;
            // ����һ��ѡ��
            provinceElement.add(new Option("请选择省份",""));
            // ѭ��������������ѡ��
            for(var index=0;index<response.length;index++){
                provinceElement.add(new Option(response[index].provinceName,response[index].provinceCode));
            }
        }
    });
}
function register(){
	console.log(userT); 
	console.log(nameT); 
	console.log(passwordT); 
	console.log(mailT); 
	console.log(proT); 
    if(userT&&nameT&&passwordT&&mailT&&proT){
        var userName=document.getElementById("userName").value;
        var password=document.getElementById("password").value;
        var name=document.getElementById("name").value;
        var Email=document.getElementById("E-mail").value;
        var province=document.getElementById("province").value;
        var city=document.getElementById("city").value;
        var flag="1";
        console.log(userName+" "+password+" "+name+" "+Email+" "+province+" "+city); 
        $.ajax({
            type: "post",
            url: "ajaxRegisterCheck.do",
            data: {"userName":userName,"password":password,"name":name,"flag":flag,"Email":Email,"province":province,"city":city},
//            data:$("#registerForm").serialize(),
            dataType: "json",
            success: function (response) {
                if(response.register==0){
                	console.log("注册成功"); 
                	window.location.href="login.html";
                }
                console.log(response.register); 
                console.log("注册失败"); 
                    
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
           	 alert(XMLHttpRequest.status);
           	 alert(XMLHttpRequest.readyState);
           	 alert(textStatus);
           }
        });
    }else{
    	
    }
    
}
function Register(){
	console.log(userT); 
	console.log(nameT); 
	console.log(passwordT); 
	console.log(mailT); 
	console.log(proT); 
    if(userT&&nameT&&passwordT&&mailT&&proT){
//    	var userName=document.getElementById("userName").value;
//        var password=document.getElementById("password").value;
//        var name=document.getElementById("name").value;
//        var Email=document.getElementById("E-mail").value;
//        var province=document.getElementById("province").value;
//        var city=document.getElementById("city").value;
//        var 
//        $("#action").val("insert");

//        console.log(userName+" "+password+" "+name+" "+Email+" "+province+" "+city);
//        var flag="1";
        $.ajax({
            type: "post",
            url: "ajaxRegisterCheck.do",
//            data: {"userName":userName,"password":password,"name":name,"flag":flag,"Email":Email,"province":province,"city":city},
            data: $("#registerForm").serialize(), //将表单内容序列化成一个URL 编码字符串
            dataType: "json",
            success: function (response) {
            	alert(response.info);
            	 if (response.code == 0) {
                     if ($("#action").val() != "") {
                         CloseDiv('MyDiv', 'fade');
                         query();
                     } else {
                         window.location.href = "login.html";
                     }
                 }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
           	 alert(XMLHttpRequest.status);
           	 alert(XMLHttpRequest.readyState);
           	 alert(textStatus);
           }
        });
    }else{
    	
    }
    
}


$(document).ready(function(f){
//    alert("alert");
	fillProvince();
    $("#province").change(function(e){
        $("#city").empty();
        $("#city").append($("<option>").val("").text("请选择城市"));
        if($(this).val()==""){
            $("#provinceError").css("color","#c00202");
            $("#provinceError").text("省份不能为空");
            return;
        }
        province_correct=true;
        proT=true;
        $("#provinceError").text("");
        var provinceCode=$("#province").val();
        $.ajax({
            type: "post",
            url: "queryProvinceCity.do",
            data: {provinceCode:provinceCode},
            dataType: "json",
            success: function (response) {
                for(var index=0;index<response.length;index++){
                    var option =$("<option>").val(response[index].cityCode).text(response[index].cityName);
                    $("#city").append(option);
                }
            }
        });
    });
});
