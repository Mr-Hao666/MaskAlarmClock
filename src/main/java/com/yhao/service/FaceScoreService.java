package com.yhao.service;

import com.yhao.domain.FaceScoreMapper;
import com.yhao.domain.UserMapper;
import com.yhao.domain.entity.FaceScore;
import com.yhao.enums.FaceScoreStyle;
import com.yhao.enums.ResultStatus;
import com.yhao.exception.BusinessException;
import com.yhao.result.BaseResult;
import com.yhao.util.Colls;
import com.yhao.util.I18nUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 杨浩
 * @create 2018-09-19 12:21
 **/
@Service
public class FaceScoreService {

    @Autowired
    private FaceScoreMapper faceScoreMapper;

    @Autowired
    private UserService userService;
    @Autowired
    private I18nUtil i18nUtil;

    //变动类型：1补水，2美白，3紧致，4滋润，5听音乐，6点评
    public BaseResult add(Integer userId, Integer style, Integer score) {
        preAdd(userId, style);
        userService.addPoint(userId, score);
                create(userId, style, score, 1);
        return new BaseResult(ResultStatus.CONTINUE_SUCCESS.getCode(),i18nUtil.getMessage(ResultStatus.CONTINUE_SUCCESS.getMessage()));
    }

    //变动类型：1补水，2美白，3紧致，4滋润，5听音乐，6点评
    public FaceScore reduce(Integer userId, Integer style, Integer score) {
        userService.reducePoint(userId, score);
        return create(userId, style, -score, 2);
    }

    private FaceScore create(Integer userId, Integer style, Integer score,Integer type){
        FaceScore faceScore = new FaceScore();
        faceScore.setUserId(userId);
        faceScore.setStyle(style);
        faceScore.setType(type);
        faceScore.setScore(score);
        faceScoreMapper.insert(faceScore);
        return faceScore;
    }

    public BaseResult add(Integer userId, Integer style) {
        return add(userId, style, 1);
    }

    /**
     * 未补水减一分
     * @param userId
     * @return
     */
    public FaceScore reduceStyle1(Integer userId) {
        return reduce(userId, FaceScoreStyle.WATER.getValue(), 1);
    }

    /**
     * 未美白减一分
     * @param userId
     * @return
     */
    public FaceScore reduceStyle2(Integer userId) {
        return reduce(userId, FaceScoreStyle.WHITE.getValue(), 1);
    }

    /**
     * 未紧致减一分
     * @param userId
     * @return
     */
    public FaceScore reduceStyle3(Integer userId) {
        return reduce(userId, FaceScoreStyle.COMPACT.getValue(), 1);
    }


    /**
     * 未滋润减一分
     * @param userId
     * @return
     */
    public FaceScore reduceStyle4(Integer userId) {
        return reduce(userId, FaceScoreStyle.MOIST.getValue(), 1);
    }


    /**
     * 未闹钟减一分
     * @param userId
     * @return
     */
    public FaceScore reduceStyle5(Integer userId) {
        return reduce(userId, FaceScoreStyle.ALARM.getValue(), 1);
    }


    /**
     * 点评加五分
     * @param userId
     * @return
     */
    public BaseResult addStyle6(Integer userId) {
        return add(userId, FaceScoreStyle.COMMENT.getValue(), 5);
    }


    /**
     * 添加颜值之前的判断
     * @param userId
     * @param style
     */
    private void preAdd(Integer userId, Integer style){
        if(style != FaceScoreStyle.COMMENT.getValue()){
            List<FaceScore> faceScoreList = faceScoreMapper.findByUserIdAndStyleToday(userId, style);
            if (Colls.notEmpty(faceScoreList)) {
                if (style == FaceScoreStyle.WATER.getValue()) {
                    if (faceScoreList.size() >= 2) {
                        throw new BusinessException(ResultStatus.HAD_WATER.getCode(),i18nUtil.getMessage(ResultStatus.HAD_WATER.getMessage()));
                    }
                } else if (style == FaceScoreStyle.COMPACT.getValue()) {
                    if (faceScoreList.size() >= 1) {
                        throw new BusinessException(ResultStatus.HAD_COMPACT.getCode(),i18nUtil.getMessage(ResultStatus.HAD_COMPACT.getMessage()));
                    }
                } else if (style == FaceScoreStyle.ALARM.getValue()) {
                    if (faceScoreList.size() >= 3) {
                        throw new BusinessException(ResultStatus.HAD_ALARM.getCode(),i18nUtil.getMessage(ResultStatus.HAD_ALARM.getMessage()));
                    }
                }else if(style == FaceScoreStyle.WHITE.getValue()){
                    if (faceScoreList.size() >= 2) {
                        throw new BusinessException(ResultStatus.HAD_WHITE.getCode(),i18nUtil.getMessage(ResultStatus.HAD_WHITE.getMessage()));
                    }
                }else if (style == FaceScoreStyle.MOIST.getValue()) {
                    if (faceScoreList.size() >= 1) {
                        throw new BusinessException(ResultStatus.HAD_MOIST.getCode(),i18nUtil.getMessage(ResultStatus.HAD_MOIST.getMessage()));
                    }
                }
            }
        }
    }
}
