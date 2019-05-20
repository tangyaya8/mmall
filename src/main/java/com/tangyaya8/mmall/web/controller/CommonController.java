package com.tangyaya8.mmall.web.controller;

import com.tangyaya8.mmall.common.Resp;
import com.tangyaya8.mmall.exception.MallException;
import com.tangyaya8.mmall.service.ICommonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tangxuejun
 * @version 2019-05-13 20:43
 */
@Controller
public class CommonController {
    private final ICommonService commonService;

    public CommonController(ICommonService commonService) {
        this.commonService = commonService;
    }

    @PostMapping("/upload")
    @ResponseBody
    public Resp<String> uploadFile(@RequestParam("fileUpload") MultipartFile file, HttpServletRequest request) throws MallException {
        String upload = request.getServletContext().getRealPath("upload");
        commonService.uploadFile(file, upload);
        return Resp.success();
    }
}
