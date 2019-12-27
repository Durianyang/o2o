package top.ywlog.o2o.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Author: Durian
 * Date: 2019/12/25 21:39
 * Description: 图片处理工具类
 */
public class ImageUtil
{
    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random R = new Random();

    /**
     * 处理缩略图，并返回新生产图片的相对路径
     *
     * @param thumbnailInputStream org.springframework.web.multipart.commons.CommonsMultipartFile
     * @param targetAddr           目标路径
     * @return 新生产图片的相对路径
     */
    public static String generateThumbnail(InputStream thumbnailInputStream, String targetAddr, String fileName)
    {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(fileName);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        logger.debug("现在的相对路径是" + relativeAddr);
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        logger.debug(("现在的目标路径是" + dest));
        try
        {
            basePath = URLDecoder.decode(basePath, "utf-8");
            logger.debug("basePath:" + basePath);
            Thumbnails.of(thumbnailInputStream).size(200, 200).
                    watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f).
                    outputQuality(0.8f).toFile(dest);
        } catch (IOException e)
        {
            throw new RuntimeException("创建缩略图失败：" + e.toString());
        }
        return relativeAddr;
    }

    /**
     * 创建目标路径上所涉及的目录
     *
     * @param targetAddr 目标路径
     */
    private static void makeDirPath(String targetAddr)
    {
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists())
        {
            dirPath.mkdirs();
        }
    }

    /**
     * 获取输入文件的扩展名
     *
     * @param fileName 文件名称
     * @return 文件扩展名
     */
    private static String getFileExtension(String fileName)
    {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 生成随机文件名
     *
     * @return 随机文件名
     */
    public static String getRandomFileName()
    {
        // 获取随机五位数
        int randomNum = R.nextInt(89999) + 10000;
        String nowTimeStr = SIMPLE_DATE_FORMAT.format(new Date());
        return nowTimeStr + randomNum;
    }

    /**
     * 文件转存
     *
     * @param file org.springframework.web.multipart.commons.CommonsMultipartFile
     * @return newFile
     */
    public static File transferCommonsMultipartFileToFile(CommonsMultipartFile file)
    {
        File newFile = new File(file.getOriginalFilename());
        try
        {
            file.transferTo(newFile);
        } catch (IOException e)
        {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return newFile;
    }

    /**
     * 删除图片文件或路径
     * storePath是文件则删除该文件，是目录则删除该目录下文件所有
     *
     * @param storePath 路径
     */
    public static void deleteImgFile(String storePath)
    {
        File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);
        if (fileOrPath.exists())
        {
            if (fileOrPath.isDirectory())
            {
                File[] files = fileOrPath.listFiles();
                for (File file : files)
                {
                    file.delete();
                }
            }
            fileOrPath.delete();
        }
    }

    public static void main(String[] args)
    {

    }
}
