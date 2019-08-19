/**
 * 
 */
// 一般的写法：$(...)：当一加载的时候  就会跑里面的内容了
$(function() {
	//** 取出shopId
	var shopId = getQueryString("shopId");
	var isEdit = shopId ? true : false; // 这个不能写 shopId ！= null，因为shopId有可能是空字符串
	
	// 用于店铺注册时候的店铺类别以及区域列表的初始化的URL
	var initUrl = '/o2o/shopadmin/getshopinitinfo';
	// 注册店铺的URL
	var registerShopUrl = '/o2o/shopadmin/registershop';
	
	//** 根据店铺id获取店铺信息
	var shopInfoUrl = '/o2o/shopadmin/getshopbyid?shopId='+shopId;
	//** 初始化修改店铺url
	var modifyShopUrl = '/o2o/shopadmin/modifyshop';
	
//	alert(initUrl);//调试
	// 判断是编辑操作还是注册操作
	if(isEdit){
		getShopInfo(shopId);
	}else {
		getShopInitInfo();  //该js文件 一加载 就调用这个方法，获取店铺和区域信息，获取到之后，下面的function是并将其加载到前面的控件里面去
	}
	
	//通过传入shopId 获取店铺信息，主要是店铺编辑用的
	function getShopInfo(shopId) {
		$.getJSON(shopInfoUrl, function(data) {
			if (data.success) {
				// 若访问成功，则依据后台传递过来的店铺信息为表单元素赋值
				var shop = data.shop;
				$('#shop-name').val(shop.shopName);
				$('#shop-addr').val(shop.shopAddr);
				$('#shop-phone').val(shop.phone);
				$('#shop-desc').val(shop.shopDes);
				// 给店铺类别选定原先的店铺类别值
				var shopCategory = '<option data-id="'
						+ shop.shopCategory.shopCategoryId + '" selected>'
						+ shop.shopCategory.shopCategoryName + '</option>';
				var tempAreaHtml = '';
				// 初始化区域列表
				data.areaList.map(function(item, index) {
					tempAreaHtml += '<option data-id="' + item.areaId + '">'
							+ item.areaName + '</option>';
				});
				$('#shop-category').html(shopCategory);
				// 不允许选择店铺类别
				$('#shop-category').attr('disabled', 'disabled');
				$('#shop-area').html(tempAreaHtml);
				// 给店铺选定原先的所属的区域
				$("#shop-area option[data-id='" + shop.area.areaId + "']").attr(
						"selected", "selected");
			}
		});
	}
	
	// 取得所有二级店铺类别以及区域信息，并分别赋值进类别列表以及区域列表
	function getShopInitInfo() {  
		$.getJSON(initUrl, function(data) {   // 它是以JSON字符串的形式返回的，第一个参数是url，第二个参数是回调的方法
			if (data.success) {
				var tempHtml = '';
				var tempAreaHtml = '';
				data.shopCategoryList.map(function(item, index) {     // data是从后台返回的数据，是个列表，遍历它，  jquery的写法
					tempHtml += '<option data-id="' + item.shopCategoryId
							+ '">' + item.shopCategoryName + '</option>';
				});
				data.areaList.map(function(item, index) {              // 区域列表信息
					tempAreaHtml += '<option data-id="' + item.areaId + '">'
							+ item.areaName + '</option>';
				});
				$('#shop-category').html(tempHtml);
				$('#shop-area').html(tempAreaHtml);
			}
		});
	}
	
	// 提交按钮的事件响应，分别对店铺注册和编辑操作做不同响应
	$('#submit').click(function() {
		// 创建shop对象
		var shop = {};
		if(isEdit){
			shop.shopId=shopId;
		}
		// 获取表单里的数据并填充进对应的店铺属性中
		shop.shopName = $('#shop-name').val();
		shop.shopAddr = $('#shop-addr').val();
		shop.phone = $('#shop-phone').val();
		shop.shopDes = $('#shop-desc').val();
		// 选择选定好的店铺类别，  从列表中选出一个
		shop.shopCategory = {
			shopCategoryId : $('#shop-category').find('option').not(function() {
				return !this.selected;   // 双重否定 等于肯定
			}).data('id')
		};
		// 选择选定好的区域信息
		shop.area = {
			areaId : $('#shop-area').find('option').not(function() {
				return !this.selected;
			}).data('id')
		};
		// 获取上传的图片文件流（图片是个文件流）， 控件的名字是shop-img
		var shopImg = $('#shop-img')[0].files[0];
		// 生成表单对象，用于接收参数并传递给后台
		var formData = new FormData();
		// 添加图片流进表单对象里
		formData.append('shopImg', shopImg);
		// 将shop json对象转成字符流保存至表单对象key为shopStr的的键值对里
		formData.append('shopStr', JSON.stringify(shop));
		// 获取表单里输入的验证码, 将验证码传入后台
		var verifyCodeActual = $('#in_kaptcha').val();
		if (!verifyCodeActual) {
			$.toast('请输入验证码！');
			return;
		}
		formData.append('verifyCodeActual', verifyCodeActual);
		// 将数据提交至后台处理相关操作
		$.ajax({
			url : (isEdit ? modifyShopUrl : registerShopUrl), //要么注册 要么修改
			type : 'POST',
			data : formData,
			contentType : false,  // 既要传文件 又要传文字
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					$.toast('提交成功！');   // 弹出个信息 
				} else {
					$.toast('提交失败!' + data.errMsg);
				}
				// 点击提交的时候，验证码会改变
				$('#img_kaptcha').click();
			}
		});
	});

})