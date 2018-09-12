package me.xueyao.service.impl;

import me.xueyao.entity.Feedback;
import me.xueyao.mapper.ExcelMapper;
import me.xueyao.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 * User: Simon.Xue
 * Date: 2018/9/12.
 */
@Service("appinfoService")

public class ExcelServiceImpl implements ExcelService {
    @Autowired
    private ExcelMapper excelMapper;

    @Override
    public List<Feedback> getAllFeedbackForExcel() {
        return excelMapper.getAllFeedbackForExcel();
    }
}
