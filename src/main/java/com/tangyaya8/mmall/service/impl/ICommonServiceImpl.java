package com.tangyaya8.mmall.service.impl;

import com.tangyaya8.mmall.exception.MallException;
import com.tangyaya8.mmall.service.ICommonService;
import com.tangyaya8.mmall.utils.FTPUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author tangxuejun
 * @version 2019-05-13 20:48
 */
@Service
@Slf4j
public class ICommonServiceImpl implements ICommonService {
    private final FTPUtil ftpUtil;

    public ICommonServiceImpl(FTPUtil ftpUtil) {
        this.ftpUtil = ftpUtil;
    }

    @Override
    public void uploadFile(MultipartFile file, String path) throws MallException {
        String filename = file.getOriginalFilename();
        String extName = filename.substring(filename.lastIndexOf("."));
        String realName = UUID.randomUUID().toString() + "." + extName;
        log.info("开始上传文件,上传文件名为:{},上传路径为:{},新文件名为：{}", filename, path, realName);
        File fileDir = new File(path);
        if (!fileDir.exists()) {
            boolean wirteAble = fileDir.setWritable(true);
            boolean isMkdirs = fileDir.mkdirs();
            if (!isMkdirs) {
                throw new MallException("创建文件路径失败");
            }
        }
        File targetFile = new File(path, realName);
        try {
            file.transferTo(targetFile);
            //将target 上传到ftp
            ftpUtil.uploadFile(List.of(targetFile));
            //上传完成之后删除upload 文件夹下的文件
            boolean delete = targetFile.delete();
        } catch (IOException e) {
            log.error("文件转换失败", e);
            throw new MallException("文件上传失败:" + e.getMessage());
        }

    }
}
