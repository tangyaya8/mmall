package com.tangyaya8.mmall.utils;

import com.tangyaya8.mmall.exception.MallException;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author tangxuejun
 * @version 2019-05-13 21:07
 */
@Slf4j
@ToString
@Getter
@Setter
@Configuration
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "ftp")
@PropertySource("classpath:mmall.properties")
public class FTPUtil {
    public static final String IMG = "img";
    private String ip;
    private int port;
    private String user;
    private String pass;
    private FTPClient ftpClient;

    public boolean uploadFile(List<File> fileList) throws IOException, MallException {
        log.info("开始连接FTP服务器");
        boolean result = this.uploadFile(IMG, fileList);
        log.info("开始连接ftp服务器,结束上传,上传结果:{}");
        return result;
    }

    private boolean uploadFile(String remotePath, List<File> fileList) throws IOException {
        boolean uploaded = true;
        FileInputStream fis = null;
        //连接FTP服务器
        if (connectServer(this.ip, this.port, this.user, this.pass)) {
            try {
                ftpClient.changeWorkingDirectory(remotePath);
                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
//                ftpClient.enterLocalActiveMode();
                for (File fileItem : fileList) {
                    fis = new FileInputStream(fileItem);
                    boolean b = ftpClient.storeFile(fileItem.getName(), fis);
                    System.out.println(ftpClient.getReplyString());
                    System.out.println(ftpClient.getReplyCode());
                    System.out.println(b);
                }

            } catch (IOException e) {
                log.error("上传文件异常", e);
                uploaded = false;
                e.printStackTrace();
            } finally {
                fis.close();
                ftpClient.disconnect();
            }
        }
        return uploaded;
    }

    private boolean connectServer(String ip, int port, String user, String pwd) {

        boolean isSuccess = false;
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip);
            isSuccess = ftpClient.login(user, pwd);
        } catch (IOException e) {
            log.error("连接FTP服务器异常", e);
        }
        return isSuccess;
    }
}
