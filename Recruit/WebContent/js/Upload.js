function updateProgress(file) {
	$('.progress-box .progress-bar > div').css('width',
			parseInt(file.percentUploaded) + '%');
	$('.progress-box .progress-num > b').html(
			SWFUpload.speed.formatPercent(file.percentUploaded));
}

function initProgress() {
	$('.progress-box').show();
	$('.progress-box .progress-bar > div').css('width', '0%');
	$('.progress-box .progress-num > b').html('0%');
}

function successAction(fileInfo) {
	setUploadImage(fileInfo);
	// 如果上传完成了
	$('.progress-box').hide();
}

function setUploadImage(fileInfo) {
	var listEls = $('.batch-pic');
	for ( var i = 0; i < fileInfo.length; i++) {
		var innerHtml = '<li>'
				+ '<div class="delete-pic" id="'
				+ fileInfo[i].xcxxAttachId
				+ '"><img src="images/red-close-btn.gif" title="删除"></img></div>'
				+ '<div class="p-pic"><img src="' + fileInfo[i].xcxxAttachUrl
				+ '" /></div>' + '<div class="p-des">'
				+ fileInfo[i].xcxxAttachTitle + '</div>'
				// + '<div class="edit-pic" id="'
				// + fileInfo[i].xcxxAttachId
				// + '"><img src="images/red-close-btn.gif"
				// title="图片说明"></img></div>'
				// + '<div class="p-text"><input type="text" value="' +
				// fileInfo[i].xcxxAttachContent + '" /></div>'
				+ '</li>';
		listEls.find('ul').append(innerHtml);
	}
	initImageListFn();
}

function initImageListFn() {
	$('.batch-pic').find('ul > li .delete-pic').each(function() {
		$(this).unbind('click').click(function() {
			if (confirm("确定要删除该图片吗?")) {
				var pic =$(this).parent();
				$.ajax({
					type : 'POST',
					url : "DelAttach",
					data : {
						id : $(this)[0].id
					},
					dataType : "text",
					async : false,
					success : function(results) {
						pic.remove();
						alert("删除成功");
					}
				});
			}
		});
	});

}

var swfImageUpload;
$(document).ready(function() {
	// var flashvars = {
	// 'file': '/ueditor/jsp/upload/video/20140611/1402447649119024343.mp4',
	// 'id': 'playerID',
	// 'image': 'attach/56C13140-4EDF-E13D-02DF-26670EB6F517/1402552673416.jpg'
	// };
	// var params = {
	// 'allowfullscreen' : 'true',
	// 'allowscriptaccess' : 'always',
	// 'bgcolor' : '#000000'
	// };
	// var attributes = {
	// 'align' : 'top',
	// 'name' : 'playerID',
	// 'id' : 'playerID'
	// };
	// swfobject.embedSWF('images/swfobject.swf', 'currenPly', '550',
	// '310', '9.0.124', 'images/expressInstall.swf', flashvars,
	// params, attributes);
	var settings = {
		flash_url : "resources/upload/swfupload.swf",
		flash9_url : "resources/upload/swfupload_fp9.swf",
		upload_url : "ImageServlet",// 接受上传的地址
		file_size_limit : "2 MB",// 文件大小限制
		file_types : "*.jpg;*.gif;*.png;*.jpeg;",// 限制文件类型
		file_types_description : "Image Files",// 说明，自己定义
		file_upload_limit : 0,
		file_queue_limit : 0,
		custom_settings : {},
		debug : false,
		// Button settings
		button_image_url : "resources/upload/upload-btn.png",
		button_width : "143",
		button_height : "45 ",
		moving_average_history_size : 40,

		// The event handler functions are defined in handlers.js
		// swfupload_preload_handler : preLoad,
		// swfupload_load_failed_handler : loadFailed,
		file_queued_handler : fileQueued,
		file_dialog_complete_handler : fileDialogComplete,
		upload_start_handler : function(file) {
			initProgress();
			updateProgress(file);
		},
		upload_progress_handler : function(file, bytesComplete, bytesTotal) {
			updateProgress(file);
		},
		upload_success_handler : function(file, data, response) {
			// 上传成功后处理函数
			var fileInfo = eval("(" + data + ")");
			successAction(fileInfo);
		},
		upload_error_handler : function(file, errorCode, message) {
			alert('上传发生了错误！');
		},
		file_queue_error_handler : function(file, errorCode, message) {
			if (errorCode == -110) {
				alert('您选择的文件太大了。');
			}
		}
	};
	swfImageUpload = new SWFUpload(settings);
	initImageListFn();
});

$.ajax({
	type : 'POST',
	url : "LoadAttach",
	data : {},
	dataType : "json",
	success : function(results) {
		setUploadImage(results);
	}
});