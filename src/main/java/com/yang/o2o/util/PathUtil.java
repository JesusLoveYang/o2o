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
	
	// �����̵Ĳ�ͬͼƬ ����ڲ�ͬ���ļ����У����ͨ����ͬ��id ���ͼƬ
	public static String getShopImgPath(Long shopId) {
		String imagePath = "/uploads/images/shop" + shopId +"/";
		return imagePath.replace("/", seperator);
	}

}
