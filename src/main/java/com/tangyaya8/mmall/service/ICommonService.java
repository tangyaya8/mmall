package com.tangyaya8.mmall.service;

import com.tangyaya8.mmall.exception.MallException;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author tangxuejun
 * @version 2019-05-13 20:48
 */
public interface ICommonService {
    void uploadFile(MultipartFile file, String path) throws MallException;
}
