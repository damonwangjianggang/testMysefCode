<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<body style="margin: 0 auto">
	<form id="newsupload" method="post" action="/ylcy/saveNews" style="margin: 0 auto" enctype="multipart/form-data">
		<div><strong>Title</strong> : <input type="text" placeholder="新闻标题" name="news_title"></div>
		
		<div>
			<strong>img : </strong>
			<input type="button" value="上传图片" onclick="f.click()" /><br>  
       		<p><input type="file" id="f" name="uploadfile" onchange="sc(this);" style="display:none"/></p>
       		<div id="result"></div>
			<div id="show"></div>
		</div>
		
		<strong>Content</strong> ：<textarea id="contents" name="news_content" style="width: 80%; height: 80%"></textarea>
		<SCRIPT type=text/javascript>
			var editor = new UE.ui.Editor();
			editor.render("contents");
		</SCRIPT>
		<input type="submit" value="发布">
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
    var maxSize = 5242880;              //最大5MB  
    
    if(ext !='.PNG' && ext !='.GIF' && ext !='.JPG' && ext !='.JPEG' && ext !='.BMP'){  
        alert('文件类型错误,请上传图片类型');  
        return false;  
    }else if(parseInt(fileSize) >= parseInt(maxSize)){  
        alert('上传的文件不能超过5MB');  
        return false;  
    }else{
        var uploadfile = new FormData($('#newsupload')[0]);   
        $.ajax({    
            url: "/ylcy/news/imgupload",   
            type: 'POST',    
            data: uploadfile,    
            cache: false,    
            processData: false,    
            contentType: false    
        }).done(function(ret){
            if(ret['isSuccess'] == true){
                var result = '';  
                var result1 = '';  
                // $("#show").attr('value',+ ret['f'] +);  
                result += '<img src="'  + ret['f']  + '" width="100">'; 
                imgurl = '<input type="hidden" name="news_imgurl" value="' + ret['f'] + '">';
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