var pageNumber="1";
var pageCount;
function fillProvince(){
    $.ajax({
        type: "post",
        url: "queryProvinceCity.do",
        data: {},
        dataType: "json",
        success: function (response) {
            var provinceElement=document.getElementById("provinceName");
            console.log("console.log");
            provinceElement.options.length=0;
            provinceElement.add(new Option("请选择省份",""));
            for(var index=0;index<response.length;index++){
                provinceElement.add(new Option(response[index].provinceName,response[index].provinceCode));
            }
        }
    });
}
function query(){
    var userName=document.getElementById("username").value;
    var chrName=document.getElementById("chrName").value;
    var mail=document.getElementById("mail").value;
    var province=document.getElementById("provinceName").value;
    var pageSize=document.getElementById("pageSize").value;
//    pageNumber=document.getElementById("pageNumber").innerText;
    console.log(userName+userName+mail+province+pageSize+pageNumber); 
    var queryParam ={
        "userName":userName,"chrName":chrName,"mail":mail,"provinceName":province
    };
    var pageParam={
        "pageSize":pageSize,"pageNumber":pageNumber
    };
    var queryParams=JSON.stringify(queryParam);
    var pageParams=JSON.stringify(pageParam);
    $.ajax({
        type: "post",
        url: "queryUser.do",
        contentType:"application/x-www-form-urlencoded;charset=utf-8",
        data:{"queryParams":queryParams,"pageParams":pageParams},
        dataType: "json",
        success: function (response) {
            // console.log(userName+userName+mail+province+pageSize+pageNumber); 
            var rows=response.rows;
            var total=response.total;
            pageCount=Math.ceil(total/pageSize);
            $("#total").text(total);
            $("#pageNumber").text(pageNumber);
            $("#pageCount").text(pageCount);
            $("tbody").empty();
            console.log(rows);
            $.each(rows, function (index, row) { 
                var r = JSON.stringify(row);
                var str = "<tr data='" + r + "'>";
                str = str + '<td width="40"><input type="checkbox" value=' + row.userName + '></td>';
                str = str + '<td class="userName">' + row.userName + '</td>';
                str = str + '<td width="110">' + row.chrName + '</td>';
                str = str + '<td>' + row.mail + '</td>';
                str = str + '<td width="70">' + row.provinceName + '</td>';
                str = str + '<td width="70">' + row.cityName + '</td>';
                str = str + '<td width="120"><a href="javascript:void(0)" id="btnUpdate">修改</a>';
                str = str + '<a href="javascript:void(0)" id="btnDel" value=' + row.userName + '>删除</a></td>';
                str + str + '</tr>';
                console.log(str);
                $("tbody").append(str);
            
            });
             $('tbody tr:even').addClass('tr_even');
             $('tbody tr:odd').addClass('tr_odd');
             
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
       }
    });
}
//翻页
//function pageTurning(){
var pageTurning = function() {

    var idValue=$(this).attr("id");
    if(idValue=="first"){
        pageNumber=1;
    }else if(idValue=="last"){
        pageNumber=pageCount;
    }else if(idValue=="back"){
        if(pageNumber>1){
            pageNumber--;
        }
    }else if(idValue=="next"){
        if(pageNumber<pageCount){
            pageNumber++;
        }   
    }
    pageNumber = pageNumber.toString(); //转换为字符串
    query();
}
var FormUtils={
    fillForm:function(formid,data){
//        $('#'+formid).find(':input').each(function(){
//            $(this).val(data[$(this).attr('id')]);
////            alert(data[$(this).attr('id')]);
//        });  
        $("#userName").val(data['userName']);
        $("#name").val(data['chrName']);
        $("#E-mail").val(data['mail']);
        $("#password").val(data['password']);
        $("#checkedPassword").val(data['password']);
    },
    emptyForm:function(formid){
        $('#'+formid).find(':input').each(function(){
            $(this).val("");
//            alert($(this).attr('id'));
        });
        $('#'+formid).find('span').each(function(){
            $(this).html("");
        });
    }
};

//修改弹出框
function openEditDiv(rowData){
    var data=JSON.parse(rowData);
    
    FormUtils.emptyForm("registerForm");
    FormUtils.fillForm("registerForm",data);
    FormUtils.emptyForm("registerForm");
//    alert(data['userName']);
//    $("#userName").val(data['userName']);
//    $("#name").val(data['chrName']);
//    $("#E-mail").val(data['mail']);
//    $("#password").val(data['password']);
//    $("#checkedPassword").val(data['password']);
//    $("#province option").val(data['provinceName']);
//    $("#city option").val(data['cityName']);
    $("#formTitle").html("用户修改");
    //用户名只读
    $("user").attr("readonly",true);
    //移除用户名的blur事件
    $("user").off("blur");
    //重新绑定邮箱的blur事件，只需验证格式的合法性
//    $("#email").unbind("blur").blur();
    $("#action").val("update");

    ShowDiv('MyDiv', 'fade');
}
//弹出隐藏层
function ShowDiv(show_div,bg_div) {  
    document.getElementById(show_div).style.display="block";
    document.getElementById(bg_div).style.display="block";

    // 设置背景层高度宽度
    // var bgdiv=document.getElementById(bg_div);

    //弹出层居中
    var windowHeight=$(window).height();
    var windowWidth=$(window).width();
    var popupHeight=$("#"+show_div).height();
    var popupWidth=$("#"+show_div).width();
    var posiTop=(windowHeight-popupHeight)/2;
    var posiLeft=(windowWidth-popupWidth)/2;
    $("#"+show_div).css({"left":posiLeft+"px","top":posiTop+"px","display":"block"});
}
// 关闭弹出层
function CloseDiv(show_div,bg_div){
    document.getElementById(show_div).style.display="none";
    document.getElementById(bg_div).style.display="none";

}
//function Register(){
//	console.log(userT); 
//	console.log(nameT); 
//	console.log(passwordT); 
//	console.log(mailT); 
//	console.log(proT); 
//    if(userT&&nameT&&passwordT&&mailT&&proT){
////    	var userName=document.getElementById("userName").value;
////        var password=document.getElementById("password").value;
////        var name=document.getElementById("name").value;
////        var Email=document.getElementById("E-mail").value;
////        var province=document.getElementById("province").value;
////        var city=document.getElementById("city").value;
////        var 
////        $("#action").val("insert");
//
////        console.log(userName+" "+password+" "+name+" "+Email+" "+province+" "+city);
////        var flag="1";
//        $.ajax({
//            type: "post",
//            url: "ajaxRegisterCheck.do",
////            data: {"userName":userName,"password":password,"name":name,"flag":flag,"Email":Email,"province":province,"city":city},
//            data: $("#registerForm").serialize(), //将表单内容序列化成一个URL 编码字符串
//            dataType: "json",
//            success: function (response) {
//            	alert(response.info);
//            	 if (response.code == 0) {
//                     if ($("#action").val() != "") {
//                         CloseDiv('MyDiv', 'fade');
//                         query();
//                     } else {
//                         window.location.href = "login.html";
//                     }
//                 }
//            },
//            error: function(XMLHttpRequest, textStatus, errorThrown) {
//           	 alert(XMLHttpRequest.status);
//           	 alert(XMLHttpRequest.readyState);
//           	 alert(textStatus);
//           }
//        });
//    }else{
//    	
//    }
//    
//}


$(document).ready(function(f){
	
	fillProvince();
    query();
//    $('tbody tr:even').addClass('tr_even');
//    $('tbody tr:odd').addClass('tr_odd');
    $("tbody").on("mouseover", "tr",function () {
        $(this).attr("class","tr_hover");  
    });
    $("tbody").on("mouseout", "tr",function () {
        $(this).removeClass("tr_hover");
        $('tbody tr:even').addClass('tr_even');
        $('tbody tr:odd').addClass('tr_odd');
    });
    
    $("tbody").on("click", "tr input:checkbox",function () {
        if(this.checked==true){
            
//            $(this).parents("tr").removeAttr("class");
            $(this).parents("tr").addClass('tr_select');
//            alert($(this).parents("tr").attr("class"));
        }else{
            $(this).parents("tr").removeClass('tr_select');
//            $("tbody").on("mouseover", "tr",function () {
//                $(this).attr("class","tr_hover");              
//            });
//            $("tbody").on("mouseout", "tr",function () {
//                $(this).removeClass("tr_hover");
//                $('tbody tr:even').addClass('tr_even');
//                $('tbody tr:odd').addClass('tr_odd');
//            }
//        );
        }
    });
    
   

    // 绑定分页操作的按钮点击事件
    $("#first,#back,#next,#last").click(pageTurning);
    //绑定标题栏的复选框单击事件
     $("#checkAll").click(function() {
        if (this.checked == true) { //判断是否被选择
            $("tbody tr input:checkbox").prop("checked", true);
            $("tbody tr").addClass('tr_select');
        } else {
            $("tbody tr input:checkbox").prop("checked", false);
            $("tbody tr").removeClass('tr_select');
        }
    });
    $("#btClear").click(function () { 
        document.getElementById("searchForm").reset();
//        FormUtils.emptyForm("searchForm");
        query(); //加载数据
    });


    //每行删除
    $('tbody').on('click', '#btnDel', function() {
        var userName = $(this).attr("value");
        $.ajax({
            type: "post",
            url: "deleteUser.do",
            data: {userName:userName },
            dataType: "json",
            success: function(response) {
                if (response.code == 0) {
                    query();
                }
            }
        });
    });
    //批量删除
    $("#btDelete").click(function () { 
        var len=$('tbody tr input:checkbox:checked').length;
        if(len==0){
            alert("请勾选要删除的记录");
            return ;
        }
        var userArray=[];
        $('tbody tr input:checkbox:checked').each(function(index,item){
            userArray.push($(this).val());
        });
        $.ajax({
            type: "post",
            url: "deleteUser.do",
            data: { userName: userArray.join(",") },
            dataType: "json",
            success: function (response) {
            	alert(response.info);
                if (response.code == 0) {
                    query();
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
           }
        });     
    });

   
    
    
    //增加
    $("#btAdd").click(function () { 
        FormUtils.emptyForm("registerForm");
        $("#formTitle").html("用户新增");
        $("#user").attr("readonly",false);
        $("#action").val("insert");

        ShowDiv('MyDiv','fade');
        
    });
    //表格修改
    $('table').on('click','#btnUpdate', function () {
    	var rowData=$(this).parents("tr").attr("data");
    	console.log(rowData+"***************");
    	userT=true;
//    	var rowData=$(this).parents("tr").text();
        openEditDiv(rowData);
    });
    //工具栏修改
    $("#btUpdate").click(function () { 
        var len=$('tbody tr input:checkbox:checked').length;
        if(len==0){
            alert("请选择你要修改的项");
            return;
        }
        if(len>1){
            alert("一次只能修改一项");
            return;
        }
        userT=true;
        var rowData=$('tbody tr input:checkbox:checked').parents("tr").attr("data");
//        console.log(rowData+"***************");
//        var rowData=$('tbody tr input:checkbox:checked').parents("tr").text();
        openEditDiv(rowData);
    });

    
});