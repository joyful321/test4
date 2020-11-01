
function fillProvince(){
    $.ajax({
        type: "post",
        url: "queryProvinceCity.do",
        data: {},
        dataType: "json",
        success: function (response) {
            var provinceElement=document.getElementById("province");
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
    var userName=document.getElementById("userName").value;
    var chrName=document.getElementById("chrName").value;
    var mail=document.getElementById("mail").value;
    var province=document.getElementById("province").value;
    var pageSize=document.getElementById("pageSize").value;
    var pageNumber=document.getElementById("pageNumber").innerText;
    console.log(userName+userName+mail+province+pageSize+pageNumber); 
    var queryParam ={
        "userName":userName,"chrName":chrName,"mail":mail,"provinceName":province
    }
    var pageParam={
        "pageSize":pageSize,"pageNumber":pageNumber
    }
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
            var pageCount=Math.ceil(total/pageSize);
            $("#total").text(total);
            $("#pageCount").text(pageCount);
            $("tbody").empty();
            console.log(rows);
            $.each(rows, function (index, row) { 
                var r = JSON.stringify(row);
                var str = "<tr data='" + r + "'>";
                str = str + '<td width="40"><input type="checkbox" value=' + row.userName + '></td>';
                str = str + '<td>' + row.userName + '</td>';
                str = str + '<td width="110">' + row.chrName + '</td>';
                str = str + '<td>' + row.mail + '</td>';
                str = str + '<td width="70">' + row.provinceName + '</td>';
                str = str + '<td width="70">' + row.cityName + '</td>';
                str = str + '<td width="120"><a href="#" id="btnUpdate">修改</a>';
                str = str + '<a href="# id="btnDel">删除</a></td>';
                str + str + '</tr>'
                console.log(str);
                $("tbody").append(str);
            
            });
            // $('tbody tr:even').addClass('tr_even');
            // $('tbody tr:odd').addClass('tr_odd');
            // $("tbody").on("mouseover", "tr",function () {
            //     $(this).attr("class","tr_hover");              
            // });
            // $("tbody").on("mouseout", "tr",function () {
            //     $(this).removeClass("tr_hover");
            //     $('tbody tr:even').addClass('tr_even');
            //     $('tbody tr:odd').addClass('tr_odd');
            // });
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
       }
    });
}
$(document).ready(function(f){
	
	fillProvince();
    query();
    $('tbody tr:even').addClass('tr_even');
    $('tbody tr:odd').addClass('tr_odd');
    
    $("tbody").on("click", "tr input:checkbox",function () {
        if(this.checked==true){
            
            $(this).parents("tr").removeAttr("class");
            $(this).parents("tr").addClass('tr_select');
            alert($(this).parents("tr").attr("class"));
        }else{
            $(this).parents("tr").removeClass('tr_select');
            $("tbody").on("mouseover", "tr",function () {
                $(this).attr("class","tr_hover");              
            });
            $("tbody").on("mouseout", "tr",function () {
                $(this).removeClass("tr_hover");
                $('tbody tr:even').addClass('tr_even');
                $('tbody tr:odd').addClass('tr_odd');
            });
        }
    });
});