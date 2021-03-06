<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- UEditor配置文件 -->
<script type="text/javascript" src="./ueditor/ueditor.config.js"></script>
<!-- UEditor编辑器源码文件 -->
<script type="text/javascript" src="./ueditor/ueditor.all.js"></script>
<link rel="stylesheet" href="./ueditor/themes/default/css/ueditor.css">
<script type="text/javascript" src="./ueditor/lang/zh-cn/zh-cn.js"></script>

<script type="text/javascript" src="./js/jquery.js"></script>
<title>Insert title here</title>
</head>
<body>

<form id="goodsupload" method="post" action="/ylcy/updateGoods" style="margin: 0 auto" enctype="multipart/form-data">
		<div><strong>商品名称 : </strong><input type="text" placeholder="商品名称" name="com_name" value="${goods.com_name}"></div>
		<div><strong>商品价格 : </strong><input type="text" placeholder="商品价格" name="com_price" value="${goods.com_price}"></div>
		<div><strong>商品积分 : </strong><input type="text" placeholder="商品积分" name="com_jf" value="${goods.com_jf}"></div>
		<div><strong>是否虚拟货物 : </strong>
									
										<input type="radio" name="isTicket" value="1" <c:if test="${goods.isTicket == 1}">checked</c:if>>虚拟货物
										<input type="radio" name="isTicket" value="0">实物商品
		</div>
		<div>
			<strong>图片 : </strong>
			<input type="button" value="上传图片" onclick="f.click()" style="margin-left: 32px"/><br>  
       		<p><input type="file" id="f" name="uploadfile" onchange="sc(this);" style="display:none"/></p>
       		<div id="result"></div>
			<div id="show"></div>
		</div>
		
		<strong>商品细节</strong> ：<textarea id="contents" name="com_detail" style="width: 80%; height: 80%" value="${goods.com_detail}"></textarea>
		<SCRIPT type=text/javascript>
			var editor = new UE.ui.Editor();
			editor.render("contents");
		</SCRIPT>
		<input type="submit" value="提交">
	</form>
</body>

<script type="text/javascript">
function sc(){  
    var animateimg = $("#f").val(); //获取上传的图片名 带
    var imgarr=animateimg.split('\\'); //分割  
    var myimg=imgarr[imgarr.length-1]; //去掉 // 获取图片名  
    var houzui = myimg.lastIndexOf('.'); //获取 . 出现的位置  
    var ext = myimg.substring(houzui, myimg.length).toUpperCase();  //切割 . 获取文件后缀  
    var file = $('#f').get(0).files[0]; //获取上传的文件  
    var fileSize = file.size;           //获取上传的文件大小  
    var maxSize = 1048576;              //最大1MB  
    
    if(ext !='.PNG' && ext !='.GIF' && ext !='.JPG' && ext !='.JPEG' && ext !='.BMP'){  
        alert('文件类型错误,请上传图片类型');  
        return false;  
    }else if(parseInt(fileSize) >= parseInt(maxSize)){  
        alert('上传的文件不能超过1MB');  
        return false;  
    }else{
        var uploadfile = new FormData($('#goodsupload')[0]);   
        $.ajax({    
            url: "/ylcy/goods/imgUpload",   
            type: 'POST',    
            data: uploadfile,    
            cache: false,    
            processData: false,    
            contentType: false    
        }).done(function(ret){
            if(ret['isSuccess']){
                var result = '';  
                var result1 = '';  
                // $("#show").attr('value',+ ret['f'] +);  
                result += '<img src="'  + ret['f']  + '" width="100">'; 
                imgurl = '<input type="hidden" name="com_imgurl" value="' + ret['f'] + '">';
                $('#result').html(imgurl);  
                $('#show').html(result);  
                alert('上传成功');  
            }else{    
                alert('上传失败');  
            }    
        });    
        return false;  
       }    
    }  



</script>
</html>