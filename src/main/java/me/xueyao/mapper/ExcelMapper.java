package me.xueyao.mapper;

import me.xueyao.entity.Feedback;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description:
 * User: Simon.Xue
 * Date: 2018/9/12.
 */
@Repository
public interface ExcelMapper {
    List<Feedback> getAllFeedbackForExcel();
}
