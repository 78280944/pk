package com.jetpay.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class FileUtil {
	  public static final String IMG_TYPE = ".jpg|.gif|.png|.bmp";
	  public static final String ALL_TYPE = ".jpg|.jepg|.gif|.png|.bmp|.gz|.rar|.zip|.pdf|.txt|.swf|.mp3|.jar|.apk|.ipa";

	  public static String formatPath(String path)
	  {
	    if (path.contains("/")) {
	      path = path.replace("/", File.separator);
	    }
	    if (path.contains("\\")) {
	      path = path.replace("/", File.separator);
	    }
	    return path;
	  }
	  public static boolean checkFileSize(File file, int kb)
	  {
	    long size = file.length();
	    if (size > 1024 * kb) {
	      return true;
	    }
	    return false;
	  }

	  public static boolean checkFileType(String fileName, boolean isImg)
	  {
	    String fileType = getFileType(fileName);
	    if (isImg) {
	      return ".jpg|.gif|.png|.bmp".indexOf(fileType.toLowerCase()) != -1;
	    }
	    return ".jpg|.jepg|.gif|.png|.bmp|.gz|.rar|.zip|.pdf|.txt|.swf|.mp3|.jar|.apk|.ipa".indexOf(fileType.toLowerCase()) != -1;
	  }

	  public static String getFileType(String fileName)
	  {
	    return fileName.substring(fileName.lastIndexOf("."), fileName.length());
	  }

	  public static boolean isExists(String path)
	  {
	    File file = new File(path);
	    if ((file.exists()) && (file.length() > 0L)) {
	      return true;
	    }
	    return false;
	  }

	  public static boolean isDir(String path)
	  {
	    File file = new File(path);
	    if ((file.exists()) && (file.isDirectory())) {
	      return true;
	    }
	    return false;
	  }

	  public static void delete(String path)
	  {
	    delete(new File(path));
	  }

	  public static void delete(File file)
	  {
	    if (file.exists())
	      file.delete();
	  }

	  public static void rename(String path, String toPath)
	    throws IOException
	  {
	    File toBeRenamed = new File(path);

	    if ((!toBeRenamed.exists()) || (toBeRenamed.isDirectory())) {
	      throw new IOException("File does not exist: " + path);
	    }

	    File newFile = new File(toPath);
	    if (!toBeRenamed.renameTo(newFile))
	      throw new IOException("rename error");
	  }

	  public static File[] getFiles(String path)
	  {
	    File file = new File(path);
	    if (file.exists()) {
	      return file.listFiles();
	    }
	    return null;
	  }

	  public static Properties getProp(File file)
	  {
	    InputStream inputStream = null;
	    try {
	      inputStream = new FileInputStream(file);
	      Properties properties = new Properties();
	      properties.load(new InputStreamReader(inputStream, "UTF-8"));
	      return properties;
	    } catch (IOException e) {
	      throw new RuntimeException("Error loading properties file.", e);
	    } finally {
	      if (inputStream != null)
	        try {
	          inputStream.close();
	        } catch (IOException e) {
	          e.printStackTrace();
	        }
	    }
	  }
}
