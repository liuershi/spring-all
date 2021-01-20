package cn.infocore.excel.util;

import cn.infocore.excel.myenum.SexEnum;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.util.StringUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.CharsetEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2021/1/20 9:56
 * @Description  工具类
 */
@Slf4j
public class ExcelUtil {

    /**
     * 导出Excel
     * @param title 导出表标题
     * @param lines 列名
     * @param dataList 所有列数据
     * @param fileName exportExcel
     * @param response 响应
     */
    public void exportExcel(String title, String[] lines, List<Object[]> dataList, String fileName, HttpServletResponse response) throws IOException {
        OutputStream outputStream = response.getOutputStream();
        response.reset();
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        response.setContentType("application/vnd.ms-excel");
        this.export(title, lines, dataList, outputStream);
        this.close(outputStream);
    }

    /**
     * 导出数据
     */
    private void export(String title, String[] lines, List<Object[]> dataList, OutputStream outputStream){
        try {
            // 创建工作簿对象
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 创建工作表
            HSSFSheet sheet = workbook.createSheet(title);
            // 创建表格标题行
            HSSFRow row = sheet.createRow(0);
            // 创建表格标题列
            HSSFCell cellTitle = row.createCell(0);

            // 获取列头样式对象
            HSSFCellStyle topStyle = this.getColumnsTopStyle(workbook);
            // 获取单元格样式对象
            HSSFCellStyle style = this.getStyle(workbook);
            // 合并表格标题行
            // 并列数为列名的长度,第一个0为起始行号，第二个1为终止行号，第三个0为起始列好，第四个参数为终止列号
            sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, lines.length-1));
            // 设置标题行样式
            cellTitle.setCellStyle(topStyle);
            // 设置标题行值
            cellTitle.setCellValue(title);

            // 创建内容，从索引为2开始
            HSSFRow contentRow = sheet.createRow(2);
            // 将列头设置到sheet的单元格中
            IntStream.range(0, lines.length).forEach(i -> {
                // 创建列头对应个数的单元格
                HSSFCell cell = contentRow.createCell(i);
                // 设置列头单元格的数据类型
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                HSSFRichTextString text = new HSSFRichTextString(lines[i]);
                // 设置列头单元格的值
                cell.setCellValue(text);
                // 设置列头单元格样式
                cell.setCellStyle(topStyle);
            });

            // 将查询出的数据放在sheet对应的单元格中
            Stream.iterate(0, i -> i + 1).limit(dataList.size())
                    .forEach(index -> {
                        Object[] items = dataList.get(index);
                        HSSFRow content = sheet.createRow(index + 3);
                        Stream.iterate(0, j -> j + 1).limit(items.length)
                                .forEach(i -> {
                                    HSSFCell cell;
                                    if (i == 0) {
                                        cell = content.createCell(i, HSSFCell.CELL_TYPE_NUMERIC);
                                        cell.setCellValue(index + 1);
                                    } else {
                                        cell = content.createCell(i, HSSFCell.CELL_TYPE_STRING);
                                        if (!StringUtils.isEmpty(items[i])) {
                                            // 设置单元格的值
                                            if (items[i] instanceof SexEnum) {
                                                SexEnum sexEnum = (SexEnum)items[i];
                                                cell.setCellValue(sexEnum.getDesc());
                                            } else {
                                                cell.setCellValue(items[i].toString());
                                            }
                                        }
                                    }
                                    cell.setCellStyle(style);
                                });
                    });

            // 让列宽随着导出的长度自动适应
            IntStream.range(0, lines.length).forEach(index -> {
                int columnWidth = sheet.getColumnWidth(index) / 256;
                for (int i = 0; i < sheet.getLastRowNum(); i++) {
                    HSSFRow currentRow;
                    // 当前行未被使用
                    if (sheet.getRow(i) == null) {
                        currentRow = sheet.createRow(i);
                    } else {
                        currentRow = sheet.getRow(i);
                    }
                    if (currentRow.getCell(index) != null){
                        HSSFCell currentCell = currentRow.getCell(index);
                        if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                            try {
                                int length = currentCell.getStringCellValue().getBytes().length;
//                                log.info("current cell length : {}", length);
                                if (columnWidth < length) {
                                    columnWidth = length;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                if (index == 0) {
                    sheet.setColumnWidth(index, (columnWidth - 2) * 256);
                } else {
                    sheet.setColumnWidth(index, (columnWidth + 4) * 256);
                }
            });

            workbook.write(outputStream);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取列头单元格样式
     */
    public HSSFCellStyle getColumnsTopStyle(HSSFWorkbook workbook){
        // 设置字体
        HSSFFont font = workbook.createFont();
        // 设置字体大小
        font.setFontHeightInPoints((short)11);
        // 设置字体加粗
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 设置字体名称
        font.setFontName("Courier New");

        // 设置样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        // 设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        // 设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        // 设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        // 设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // 设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        // 设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        // 设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        // 在样式用应用设置的字体;
        style.setFont(font);
        // 设置自动换行;
        style.setWrapText(false);
        // 设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        return style;
    }

    /*
     * 列数据信息单元格样式
     */
    private HSSFCellStyle getStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        // 设置字体大小
        // font.setFontHeightInPoints((short)10);
        // 字体加粗
        // font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 设置字体名字
        font.setFontName("Courier New");
        // 设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        // 设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        // 设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        // 设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        // 设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // 设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        // 设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        // 设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        // 在样式用应用设置的字体;
        style.setFont(font);
        // 设置自动换行;
        style.setWrapText(false);
        // 设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        return style;
    }

    /**
     * 关闭输出流
     * @param outputStream
     */
    private void close(OutputStream outputStream){
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
