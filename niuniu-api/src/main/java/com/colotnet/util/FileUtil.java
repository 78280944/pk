
package com.colotnet.util;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class FileUtil {

	private static final short BUFFER_SIZE = 20 * 1024;
	static BASE64Encoder encoder = new BASE64Encoder();
	static BASE64Decoder decoder = new BASE64Decoder();

	private FileUtil() {
	}

	/**
	 * <h1>拷贝文件</h1>
	 * 
	 * @Title: copyFile
	 * @Description: TODO 拷贝文件
	 * @param from
	 * @param to
	 * @throws Exception
	 */
	public synchronized static void copyFile(File from, File to) throws Exception {
		copyFile(from, to, BUFFER_SIZE);
	}

	/**
	 * <h1>拷贝文件</h1>
	 * 
	 * @Title: copyFile
	 * @Description: TODO 拷贝文件
	 * @param from
	 * @param to
	 * @throws Exception
	 */
	public synchronized static void copyFile(String from, String to) throws Exception {
		copyFile(new File(from), new File(to), BUFFER_SIZE);
	}

	/**
	 * 拷贝文件
	 * 
	 * @param from
	 *            源
	 * @param to
	 *            目标
	 * @param bufferSize
	 *            缓存大小
	 * @throws IOException
	 * @throws Exception
	 */
	public synchronized static void copyFile(File from, File to, short bufferSize) throws IOException {
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;

		initFile(to.getAbsolutePath());

		try {
			inBuff = new BufferedInputStream(new FileInputStream(from));
			outBuff = new BufferedOutputStream(new FileOutputStream(to));
			byte[] b = new byte[bufferSize];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			outBuff.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (null != inBuff)
					inBuff.close();
			} catch (Exception e) {
			}
			try {
				if (null != outBuff)
					outBuff.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * 写文件
	 * 
	 * @param from
	 *            源
	 * @param to
	 *            目标
	 * @param bufferSize
	 *            缓存大小
	 * @throws IOException
	 * @throws Exception
	 */
	public synchronized static void writeFile(InputStream from, File to, short bufferSize) throws IOException {
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;

		initFile(to.getAbsolutePath());
		try {
			inBuff = new BufferedInputStream(from);
			outBuff = new BufferedOutputStream(new FileOutputStream(to));
			byte[] b = new byte[bufferSize];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			outBuff.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (null != inBuff)
					inBuff.close();
			} catch (Exception e) {
			}
			try {
				if (null != outBuff)
					outBuff.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * <h1>高性能文件读取</h1>
	 * 
	 * @Title: readFile
	 * @Description: TODO 一次性奖整个文件加载到内容中，如果文件过大无法使用该方法
	 * @param filePath
	 * @param charSet
	 * @return
	 * @throws Exception
	 */
	public static synchronized String readFile(String filePath, String charSet) throws Exception {
		FileInputStream fileInputStream = new FileInputStream(filePath);
		try {
			FileChannel fileChannel = fileInputStream.getChannel();
			ByteBuffer byteBuffer = ByteBuffer.allocate((int) fileChannel.size());
			fileChannel.read(byteBuffer);
			byteBuffer.flip();
			return new String(byteBuffer.array(), charSet);
		} finally {
			fileInputStream.close();
		}

	}

	private static final int LINE = '\n';

	/**
	 * @deprecated 目前无法准确获取行内容
	 * @Title: readLineFile
	 * @Description: TODO
	 * @param filePath
	 * @param charSet
	 * @param start
	 * @param size
	 * @return
	 * @throws Exception
	 */
	@Deprecated
	public static synchronized String readLineFile(String filePath, String charSet, int start, int size)
			throws Exception {
		FileInputStream fis = null;
		InputStreamReader r = null;
		StringBuilder lineBuilder = new StringBuilder();
		int lineSize = 0;
		int end = start + size;
		try {
			fis = new FileInputStream(filePath);
			r = new InputStreamReader(fis, charSet);
			char[] chars = new char[1024];
			StringBuilder builder = new StringBuilder();
			while (r.read(chars) > 0) {

				if (lineSize < start) {
					if (findLineIndex(chars) >= 0) {
						lineSize++;
					}
				}
				for (int j = 0; j < chars.length; j++) {
					if (chars[j] == LINE) {
						if (lineSize >= start) {
							builder.append(lineBuilder).append("\r\n");
						}
						if (lineSize >= end) {
							return builder.toString();
						}
						lineSize++;
						lineBuilder.delete(0, lineBuilder.length());
					} else {
						if (lineSize >= start) {
							lineBuilder.append(chars[j]);
						}
					}
				}
			}
			System.out.println(lineSize);
			return builder.toString();
		} finally {
			if (null != r) {
				r.close();
			}
			if (null != fis) {
				fis.close();
			}
		}
	}

	private static synchronized int findLineIndex(char[] chars) {
		for (int j = 0; j < chars.length; j++) {
			if (chars[j] == '\n') {
				return j;
			}
		}
		return -1;
	}

	/**
	 * 构建目录
	 * 
	 * @param outputDir
	 * @param subDir
	 */
	public void createDirectory(String outputDir, String subDir) {
		File file = new File(outputDir);
		if (!(subDir == null || subDir.trim().equals(""))) {// 子目录不为空
			file = new File(outputDir + "/" + subDir);
		}
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	/**
	 * 以UTF8方式 读取文件
	 * 
	 * @Title: readFile
	 * @Description: TODO 以UTF8方式 读取文件
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static synchronized String readFile(String filePath) throws Exception {
		return readFile(filePath, "UTF8");
	}

	public static synchronized FileUtil getInstance() {
		return new FileUtil();
	}

	/**
	 * 写文件
	 * 
	 * @Title: write
	 * @Description: TODO
	 * @param path
	 * @param content
	 * @param charSet
	 */
	public static synchronized void write(String path, String content, String charSet) {
		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(path);
			outputStream.write(content.getBytes(charSet));
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != outputStream)
					outputStream.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * 读取文件 byte[]内容
	 * 
	 * @Title: readFileByte
	 * @Description: TODO
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 */
	public static synchronized byte[] readFileByte(File file) {
		if (file.exists()) {
			try {
				return byteByInputStream(new FileInputStream(file));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 读取文件 byte[]内容
	 * 
	 * @Title: readFileByte
	 * @Description: TODO
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 */
	public static synchronized byte[] readFileByte(FileInputStream fileInputStream) {
		return byteByInputStream(fileInputStream);
	}

	/**
	 * 將文件轉換成 byte 數組
	 * 
	 * @Title: readFileByte
	 * @Description: TODO
	 * @param filePath
	 * @return
	 */
	public static synchronized byte[] readFileByte(String filePath) {
		try {
			FileInputStream fileInputStream = new FileInputStream(
					Thread.currentThread().getContextClassLoader().getResource("/").getPath() + filePath);
			
			return readFileByte(fileInputStream);
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	private static synchronized byte[] byteByInputStream(InputStream in) {
		try {
			int fl = in.available();
			if (null != in) {
				byte[] s = new byte[fl];
				in.read(s, 0, fl);
				return s;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 刪除文件(包括文件夾)
	 * 
	 * @Title: delete
	 * @Description: TODO
	 * @param file
	 */
	public synchronized static void delete(File file) {
		if (file.listFiles() != null) {
			for (File item : file.listFiles()) {
				delete(item);
			}
		}
		file.delete();
	}

	/**
	 * 初始文件(包括文件夹)
	 * 
	 * @Title: initFile
	 * @Description: TODO
	 * @param name
	 * @throws IOException
	 */
	public synchronized static void initFile(String name) throws IOException {
		File f = new File(name);
		if (!f.exists()) {
			String path = f.getCanonicalPath();
			path = path.replaceAll("\\\\", "/");
			String dirPath = path.substring(0, path.lastIndexOf("/"));
			new File(dirPath).mkdirs();
			new File(path).createNewFile();
		}
	}

	/**
	 * 根据文件流获取文件头信息
	 * 
	 * @param is
	 *            文件流
	 * @return 文件头信息
	 */
	public static String getFileHeader(InputStream is) {
		String value = null;
		try {
			byte[] b = new byte[4];
			/*
			 * int read() 从此输入流中读取一个数据字节。 int read(byte[] b) 从此输入流中将最多 b.length
			 * 个字节的数据读入一个 byte 数组中。 int read(byte[] b, int off, int len)
			 * 从此输入流中将最多 len 个字节的数据读入一个 byte 数组中。
			 */
			is.read(b, 0, b.length);
			value = bytesToHexString(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}

	/**
	 * 将要读取文件头信息的文件的byte数组转换成string类型表示
	 * 
	 * @param src
	 *            要读取文件头信息的文件的byte数组
	 * @return 文件头信息
	 */
	private static String bytesToHexString(byte[] src) {
		StringBuilder builder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		String hv;
		for (int i = 0; i < src.length; i++) {
			// 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
			hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
			if (hv.length() < 2) {
				builder.append(0);
			}
			builder.append(hv);
		}
		return builder.toString();
	}

	public static boolean isImage(String fileHeader) {
		Map<String, String> fileTypes = new HashMap<String, String>();
		fileTypes.put("FFD8FF", "jpg");
		fileTypes.put("89504E47", "png");
		fileTypes.put("47494638", "gif");
		fileTypes.put("49492A00", "tif");
		fileTypes.put("424D", "bmp");
		Iterator<Entry<String, String>> iterator = fileTypes.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			String fileTypeKey = entry.getKey();
			if (fileHeader.toUpperCase().startsWith(fileTypeKey)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 将base64字符串 生成图片文件
	 * 
	 * @param imgBase64Str
	 * @param Path
	 * @param fileName
	 * @return 生成成功 返回0，其他均为失败
	 */
	public static int base64StrToImg(String imgBase64Str, String Path, String fileName) {
		int mkResult = getInstance().mkDirs(Path);
		if (mkResult != 0) {
			return mkResult;
		}
		String fileFullPath = Path + "/" + fileName;
		try {
			byte[] bytes1 = decoder.decodeBuffer(imgBase64Str);
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
			BufferedImage bi1 = ImageIO.read(bais);
			File file = new File(fileFullPath);
			ImageIO.write(bi1, "jpg", file);
			return 0;
		} catch (IOException e) {
		}
		return 1;
	}

	/**
	 * 创建指定目录，如果不存在
	 * 
	 * @param path
	 *            路径
	 * @return
	 */
	private int mkDirs(String path) {
		File fileDirs = new File(path);
		if (fileDirs.exists()) {
			if (!fileDirs.isDirectory()) {
				if (fileDirs.delete()) {
					if (fileDirs.mkdirs()) {
						return 0;
					}
					return 2;
				}
				return 1;
			}
			return 0;
		}
		if (fileDirs.mkdirs()) {
			return 0;
		}
		return 2;
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param fileName
	 * @return
	 */
	public boolean isExist(String fileName) {
		File file = new File(fileName);
		return file.exists();
	}

	public static void main(String[] args) throws IOException {
		// String lib = "E:/workspace/Intgrt/applib";
		// String path = "E:/temp/tempJar";
		// new File(path).mkdirs();
		// File file = new File(lib);
		// for (File fileItem : file.listFiles(
		// new FileFilter() {
		//
		// @Override
		// public boolean accept(File pathname) {
		// // TODO Auto-generated method stub
		// return pathname.getName().toLowerCase().indexOf(".jar")>=0;
		// }
		// })) {
		// FileUtil.getInstance().unZip(fileItem.getAbsolutePath(), path);
		// }

		System.out.println(URLDecoder.decode("ea91aea36e6d99e31644b319327dfbd1c1088c79b553734f", "UTF8"));

	}
}
