package com.yang.o2o.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.yang.o2o.dto.ImageHolder;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

/**
 * ��Ҫ������װThumnailator
 * @author Yang
 *
 */
public class ImageUtil {

	private static String basePath = "F:\\2. ��������Ŭ���ɣ�\\eclipse-work\\o2o\\src\\main\\resources";
	private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final Random r = new Random();
	private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);
	
	/**
	 * ��CommonsMultipartFileת����File��
	 * 
	 * @param cFile
	 * @return
	 */
	public static File transferCommonsMultipartFileToFile(CommonsMultipartFile cFile) {
		File newFile = new File(cFile.getOriginalFilename());
		try {
			cFile.transferTo(newFile);
		} catch (IllegalStateException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		return newFile;
	}
	
	/**
	 * ��������ͼ��������������ͼƬ�����ֵ·��
	 * 
	 * @param thumbnail
	 * @param targetAddr
	 * @return
	 */
	public static String generateThumbnail(ImageHolder thumbnail, String targetAddr) {
		String realFileName = getRandomFileName();
		String extension = getFileExtension(thumbnail.getImageName());
		makeDirPath(targetAddr);
		String relativeAddr = targetAddr + realFileName + extension;
		logger.debug("current relativeAddr is:" + relativeAddr);
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		logger.debug("current completeAddr is:" + PathUtil.getImgBasePath() + relativeAddr);
		try {
			Thumbnails.of(thumbnail.getImage()).size(200, 200)
			          .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "\\watermark.jpg")), 0.2f)
			          .outputQuality(0.8f).toFile(dest);
		} catch (IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		
		return relativeAddr;
	}
	
	/**
	 * ����Ŀ��·�����漰����Ŀ¼����/home/work/xiangze/xxx.jpg, ��ô home work xiangze
	 * �������ļ��ж����Զ�����
	 * @param targetAddr
	 */
	private static void makeDirPath(String targetAddr) {
		String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
		File dirPath = new File(realFileParentPath);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}

	/**
	 * ��ȡ�����ļ�������չ��
	 * @param thumbnail
	 * @return
	 */
	private static String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * ��������ļ�������ǰ������Сʱ��������+��λ�����
	 * @return
	 */
	public static String getRandomFileName() {
		// ��ȡ�������λ��
		int rannum = r.nextInt(89999) + 10000;
		String nowTimeStr = sDateFormat.format(new Date());
		return nowTimeStr + rannum;
	}

	/**
	 *  shopPath���ļ�·������Ŀ¼·��
	 *  ������ļ�·������ɾ�����ļ�
	 *  �����Ŀ¼·���������ɾ��
	 * @param shopPath
	 */
	public static void deleteFileOrPath(String shopPath) {
		File file = new File(PathUtil.getImgBasePath() + shopPath);  //����·��+���·��
		//�ж��Ƿ����
		if (file.exists()) {
			if (file.isDirectory()) {
				File files[] = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					files[i].delete();
				}
			}
			file.delete();
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		//ͨ���̵߳����������ȡ classpath ����·��
		String basePath = "F:\\2. ��������Ŭ���ɣ�\\eclipse-work\\o2o\\src\\main\\resources";
		
		// of:����������Ҫ������ļ�;size�����ɵ�ͼƬ��С 200���أ�watermark:��ͼƬ���ˮӡ��outputQuality:ѹ����ѹ����80%��;toFile:�����λ��
		Thumbnails.of(new File("F:\\projectimage\\james.jpg")).size(200, 200)
				.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "\\watermark.jpg")), 0.25f)
				.outputQuality(0.8f).toFile("F:\\projectimage\\jamesnew.jpg");
	}

	/**
	 * ��������ͼ��������������ͼƬ�����ֵ·��
	 * 
	 * @param thumbnail
	 * @param targetAddr
	 * @return
	 */
	public static String generateNormalImg(ImageHolder thumbnail, String targetAddr) {
		// ��ȡ���ظ��������
		String realFileName = getRandomFileName();
		// ��ȡ�ļ�����չ����png,jpg��
		String extension = getFileExtension(thumbnail.getImageName());
		// ���Ŀ��·�������ڣ����Զ�����
		makeDirPath(targetAddr);
		// ��ȡ�ļ��洢�����·��(���ļ���)
		String relativeAddr = targetAddr + realFileName + extension;
		logger.debug("current relativeAddr is :" + relativeAddr);
		// ��ȡ�ļ�Ҫ���浽��Ŀ��·��
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		logger.debug("current complete addr is :" + PathUtil.getImgBasePath() + relativeAddr);
		// ����Thumbnails���ɴ���ˮӡ��ͼƬ
		try {
			Thumbnails.of(thumbnail.getImage()).size(337, 640)
					.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
					.outputQuality(0.9f).toFile(dest);
		} catch (IOException e) {
			logger.error(e.toString());
			throw new RuntimeException("������ͼƬʧ�ܣ�" + e.toString());
		}
		// ����ͼƬ���·����ַ
		return relativeAddr;
	}
	
	

}
