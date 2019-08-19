package com.yang.o2o.util;

public class PathUtil {

	private static String seperator = System.getProperty("file.separator");

	public static String getImgBasePath() {
		String os = System.getProperty("os.name");
		
		//System.out.println("==========>"+os);
		
		String basePath = "";
		if (os.toLowerCase().startsWith("win")) {
			basePath = "F:/projectimage";
		} else {
			basePath = "/Users/baidu/work/image";
		}
		basePath = basePath.replace("/", seperator);
		//System.out.println("=========>"+basePath);
		
		return basePath;
	}
	
	// 将店铺的不同图片 存放在不同的文件夹中，因此通过不同的id 存放图片
	public static String getShopImgPath(Long shopId) {
		String imagePath = "/uploads/images/shop" + shopId +"/";
		return imagePath.replace("/", seperator);
	}

}
