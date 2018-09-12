package me.xueyao.service;

import me.xueyao.entity.Feedback;

import java.util.List;

/**
 * Description:
 * User: Simon.Xue
 * Date: 2018/9/12.
 */
public interface ExcelService {

    List<Feedback> getAllFeedbackForExcel();
}
