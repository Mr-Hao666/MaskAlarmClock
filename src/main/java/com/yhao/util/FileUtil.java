package com.yhao.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.util.UUID;

/**
 * @author zhenfei.wang
 * @since 1.0.0
 * Created On: 2015-01-12 14:28
 */
public class FileUtil {
    private final static Logger log = LoggerFactory.getLogger(FileUtil.class);


    public static String getExtName(String imageName) {
        if (imageName == null || imageName.isEmpty()) {
            return null;
        }

        int idx1 = imageName.lastIndexOf(".");
        int idx2 = imageName.lastIndexOf("/");
        int idx3 = imageName.lastIndexOf("\\");
        int idx4 = Math.max(idx2, idx3);

        if (idx1 < 0 || idx1 < idx4) {
            return null;
        }

        return imageName.substring(idx1);
    }

    /**
     * 返回文件的新名称
     *
     * @param fileName 带后缀名的文件名称或路径或url。若无后缀则返回.jpg的后缀
     * @return UUID字符串合成的文件名，默认为.jpg的后缀
     */
    public static String newFileName(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return null;
        }

        String ext = getExtName(fileName);
        return UUID.randomUUID().toString() + ((ext == null || ext.isEmpty()) ? ".jpg" : ext);
    }

    public static byte[] read(InputStream in) {
        BufferedInputStream buffer = new BufferedInputStream(in);

        short buffSize = 1024;
        ByteArrayOutputStream out = new ByteArrayOutputStream(buffSize);
        byte[] temp = new byte[buffSize];

        try {
            int size;
            while ((size = buffer.read(temp)) != -1) {
                out.write(temp, 0, size);
            }
            return out.toByteArray();

        } catch (Exception e) {
            log.error("Read input stream to bytes error!", e);
        } finally {
            try {
                buffer.close();
            } catch (Exception e) {
                log.error("Read input stream to bytes error!", e);
            }
        }

        return new byte[]{};
    }

    public static boolean write(byte[] data, String savePath) {
        if (data == null || data.length == 0) {
            return false;
        }

        FileOutputStream fos = null;
        try {

            File file = new File(savePath);
            if (!file.getParentFile().exists()) {
                boolean flag = file.getParentFile().mkdirs();
                if (!flag) {
                    return false;
                }
            }

            fos = new FileOutputStream(savePath);
            fos.write(data);
            fos.flush();
            return true;

        } catch (Exception e) {
            log.error("Save byte data to file error, path: {}", e, savePath);
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                log.error("Save byte data to file error, path: {}", e, savePath);
            }
        }

        return false;
    }

    public static boolean write(InputStream in, String savePath) {
        if (savePath == null || savePath.isEmpty()) {
            return false;
        }

        FileOutputStream fos = null;
        try {

            File file = new File(savePath);
            if (!file.getParentFile().exists()) {
                boolean flag = file.getParentFile().mkdirs();
                if (!flag) {
                    return false;
                }
            }

            fos = new FileOutputStream(savePath);

            int len;
            byte[] data = new byte[256];
            while ((len = in.read(data)) > -1) {
                fos.write(data, 0, len);
            }

            fos.flush();
            return true;

        } catch (Exception e) {
            log.error("Save byte data to file error, path: {}", e, savePath);
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                log.error("Save byte data to file error, path: {}", e, savePath);
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                log.error("Save byte data to file error, path: {}", e, savePath);
            }
        }

        return false;
    }

    public static boolean write(URL url, String savePath) {
        if (url == null || savePath == null || savePath.isEmpty()) {
            return false;
        }

        InputStream in = null;
        try {
            in = url.openStream();
            return write(in, savePath);
        } catch (Exception e) {
            log.error("Open url error, url: {}", url, e);
        } finally {
            closeQuietly(in);
        }

        return false;
    }

    private static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException ioe) {
            // ignore exception
        }
    }

    /**
     * 删除目录、文件及子目录
     *
     * @param dir 目录名称
     * @return 成功为true，失败false
     */
    public static boolean deleteDir(File dir) {
        if (dir == null) {
            return false;
        }
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                //递归删除目录中的子目录下
                for (File file : files) {
                    boolean success = deleteDir(file);
                    if (!success) {
                        return false;
                    }
                }
            }
        }

        // 目录此时为空，可以删除
        return dir.delete();
    }
}
