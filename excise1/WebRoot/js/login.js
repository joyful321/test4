function changeImg(){
    document.getElementById("vcodeImg").src="createVerifyImage.do?t="+Math.random();
    
}

var xmlHttp;
function createXmlHttp(){
    if(window.XMLHttpRequest) xmlHttp=new XMLHttpRequest();
    else xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
}

function ajaxCheckLogin(){
    console.log("console.log"); 
    
    var userName=document.getElementById("userName").value;
    var password=document.getElementById("password").value;
    var vcode=document.getElementById("vcode").value;
    var autologin=document.getElementById("autologin").value;

    
    createXmlHttp();

    xmlHttp.open("post","ajaxLoginCheck.do",true);
    xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    // if(autologin!=null)
        xmlHttp.send("userName="+userName+"&password="+password+"&vcode="+vcode+"&autologin="+autologin);
    // else {
    //     xmlHttp.send("userName="+userName+"&password="+password+"&vcode="+vcode);
    //     alert("alert");
    // }
    xmlHttp.onreadystatechange=function(){
        if(xmlHttp.readyState==4&&xmlHttp.status==200){
            var info=xmlHttp.responseText;
            var obj=JSON.parse(info);
            if(obj.code==4){
            	document.getElementById("checkError").innerText=obj.info;
                if(obj.user==0)
                    document.getElementById("userinfo").innerText=obj.userinfo;
                else document.getElementById("userinfo").innerText="";
                if(obj.password==0) 
                     document.getElementById("passinfo").innerText=obj.passwordinfo;
                else document.getElementById("passinfo").innerText="";
                if(obj.vcode==0) 
                      document.getElementById("vcodeinfo").innerText=obj.vcodeinfo;
                else document.getElementById("vcodeinfo").innerText="";
            }else{
                if(obj.code==0){ //登陆成功
                    window.location.href="main.jsp";
                }else{//登陆失败
                    document.getElementById("checkError").innerText=obj.info;
                }
            }
            
        }
    }
}
