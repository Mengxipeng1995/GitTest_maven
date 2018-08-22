<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>上传</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <script src="static/js/jquery-2.2.1.js"></script>
    <link href="/static/Boostrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/Boostrap/css/bootstrap-theme.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/jQuery-File-Upload/css/jquery.fileupload.css">
    <link rel="stylesheet" href="/static/jQuery-File-Upload/css/jquery.fileupload-ui.css">

</head>
<body>

<div style="width: 100%">
<!-- Large modal -->
<%--<button type="button" class="btn btn-primary" data-toggle="modal" id="iii">Large modal</button>--%>
<div>
    <div id="myModal" style="text-align: center" aria-hidden="true" data-keyboard="false" data-backdrop="static" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog"
         aria-labelledby="myLargeModalLabel">
        <div class="modiv dialog modal-lg" style="width: 100%" role="document">
            <div class="modal-body">

                <div  class="progress progress-striped active" role="progressbar" aria-valuemin="10" aria-valuemax="100"
                     aria-valuenow="0">
                    <div id="m_progress" class="progress-bar progress-bar-success" style="width:0%;"></div>
                </div>

            </div>
        </div>
    </div>
</div>
<span id="weixin_upload" class="btn btn-primary fileinput-button">
<span>上传</span>
<input type="file" id="myFile" name="myFile" multiple>
</span>

</div>
<script src="static/js/jquery-2.2.1.js"></script>
<script src="/static/Boostrap/js/bootstrap.js"></script>
<script src="/static/jQuery-File-Upload/js/vendor/jquery.ui.widget.js"></script>
<script src="/static/jQuery-File-Upload/js/jquery.fileupload.js"></script>
<script src="/static/jQuery-File-Upload/js/jquery.iframe-transport.js"></script>
<script>
    var jqXHR;
    $(function () {
        $("#myFile").fileupload({
            url: '/doUpload',
            limitConcurrentUploads: 1,
            sequentialUploads: true,
            progressInterval: 100,
            maxChunkSize: 100000, //设置上传片段大小，不设置则为整个文件上传
            dataType: "json",
            add: function (e, data) {
                jqXHR = data.submit();
                $('#myModal').modal('show');
            },done:function (e,data) {
                alert("上传完成！")
                location.reload();
            }
        }).bind('fileuploadprogress', function (e, data) {
            //return;
            var progress = parseInt(data.loaded / data.total * 100, 10) - 1;
            $("#m_progress").css('width', progress + '%');
            $("#m_progress").html(progress + '%');
        }).bind('fileuploaddone', function (e, data) {
            $("#m_progress").css('width', 100 + '%');
            $("#m_progress").html(100 + '%');
        }).bind('fileuploadpaste', function (e, data) {
            alert("aaa");
        }).bind('fileuploadsubmit', function (e, data) {

        });
    });
    $("#iii").click(function () {
        $('#myModal').modal('show');
    })

    //取消上传
    // function cancelUpload() {
    //     jqXHR.abort();
    // }
</script>
</body>
</html>