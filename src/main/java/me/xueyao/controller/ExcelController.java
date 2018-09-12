package me.xueyao.controller;

import me.xueyao.common.BaseResponse;
import me.xueyao.common.ResponseStatus;
import me.xueyao.entity.Feedback;
import me.xueyao.service.ExcelService;
import me.xueyao.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Description:
 * User: Simon.Xue
 * Date: 2018/9/12.
 */
@Controller
@RequestMapping(value = "/excel")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @RequestMapping(value = "/export",method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse exportFeedBack(HttpServletResponse response){

        String fileName = "考核员工管理"+System.currentTimeMillis()+".xls"; //文件名
        String sheetName = "考核员工管理";//sheet名

        String []title = new String[]{"绩效考核名称","考核开始时间","考核结束时间","归档人数"};//标题

        List<Feedback> list = excelService.getAllFeedbackForExcel();//内容list

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String [][]values = new String[list.size()][];
        for(int i=0;i<list.size();i++){
            values[i] = new String[title.length];
            //将对象内容转换成string
            Feedback obj = list.get(i);
            values[i][0] = obj.getName();
            values[i][1] = obj.getStartTime();
            values[i][2] = obj.getEndTime();
            values[i][3] = obj.getDocumentNumber();


        }

        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, values, null);

        //将文件存到指定位置
        try {
            this.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BaseResponse(ResponseStatus.SUCCESS.code(),"ok");
    }

    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(),"ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
