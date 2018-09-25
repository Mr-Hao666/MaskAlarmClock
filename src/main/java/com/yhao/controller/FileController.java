package com.yhao.controller;

import com.yhao.domain.UserMapper;
import com.yhao.domain.entity.User;
import com.yhao.enums.ResultStatus;
import com.yhao.exception.BusinessException;
import com.yhao.result.BaseResult;
import com.yhao.util.I18nUtil;
import com.yhao.util.ImagesUtil;
import com.yhao.util.IpUtil;
import com.yhao.util.StringUtil;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 杨浩
 * @create 2018-09-17 15:12
 **/
@RestController
@Api(description = "文件管理")
@RequestMapping(value = "/file")
public class FileController {

    private final static Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private IpUtil ipUtil;

    @Autowired
    private I18nUtil i18nUtil;

    @Value("${img.location}")
    private String location;

    @Value("${img.path.patterns}")
    private String patterns;

    @PostMapping("/img/upload")
    public BaseResult uploadImg(@RequestParam("file") MultipartFile multipartFile) {
        if (multipartFile.isEmpty() || StringUtil.isBlank(multipartFile.getOriginalFilename())) {
            throw new BusinessException(i18nUtil.getMessage(ResultStatus.FILE_IS_EMPTY.getMessage()));
        }
        String contentType = multipartFile.getContentType();
        if (!contentType.contains("")) {
            throw new BusinessException(i18nUtil.getMessage(ResultStatus.FILE_FORMAT_ERROR.getMessage()));
        }
        String rootFileName = multipartFile.getOriginalFilename();
        logger.info("上传图片:name={},type={}", rootFileName, contentType);
        //处理图片
        //获取路径
        String filePath = location;
        logger.info("图片保存路径={}", filePath);
        String fileName = null;
        try {
            fileName = ImagesUtil.saveImg(multipartFile, filePath);
            if (StringUtil.isBlank(fileName)) {
                return new BaseResult(ResultStatus.UPDATE_FAILURE.getCode(),i18nUtil.getMessage(ResultStatus.UPDATE_FAILURE.getMessage()));
            }
            String image = "http://" + ipUtil.getIp() + patterns + fileName;
            Map<String, String> map = new HashMap<>(1);
            map.put("image", image);
            logger.info("返回值：{}", image);
            return new BaseResult(map,ResultStatus.SUCCESS.getCode(), i18nUtil.getMessage(ResultStatus.SUCCESS.getMessage()));
        } catch (IOException e) {
            throw new BusinessException(i18nUtil.getMessage(ResultStatus.FILE_UPLOAD_FAIL.getMessage()));
        }
    }
}
