package com.source.test_services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.source.test_dao.FilesMapper;
import com.source.test_model.Files;
import com.source.test_model.P1;



@Service
public class FileService {
	
	@Resource
	private P1Service p1Service;
	
	@Resource
	private FilesMapper filesMapper;
	
	
	
	public  String convertCellToString(Cell cell){
        //如果为null会抛出异常，应当返回空字符串
       if (cell == null)
           return "";
       /*
       //POI对单元格日期处理很弱，没有针对的类型，日期类型取出来的也是一个double值，所以同样作为数值类型
       //解决日期2006/11/02格式读入后出错的问题，POI读取后变成“02-十一月-2006”格式
       if(cell.toString().contains("-") && checkDate(cell.toString())){
           String ans = "";
           try {
               ans = new SimpleDateFormat("yyyy/MM/dd").format(cell.getDateCellValue());
           } catch (Exception e) {
               ans = cell.toString();
           }
           return ans;
       }
	*/
       cell.setCellType(CellType.STRING);
       return cell.getStringCellValue();
   }
	
	
	public String file_import ( MultipartFile files) {
		String result = "";
		long startTime = System.currentTimeMillis();
		int count = 0;

		
		String fileName = files.getOriginalFilename();
		Workbook wb = null; 
		ByteArrayInputStream  is=null;
		try {
			is =  (ByteArrayInputStream ) files.getInputStream();
		} catch (IOException e) {
			result ="请上传正确格式的文档";
			e.printStackTrace();
		}
		
		if(fileName.endsWith("xls")){  
			//Workbook wb = WorkbookFactory.create(is);    //导入2003
			try {
				wb = new HSSFWorkbook(is);
			} catch (IOException e) {
				result ="请上传正确格式的文档";
				e.printStackTrace();
			}
		}else if(fileName.endsWith("xlsx")){
		//	XSSFWorkbook  wb =  (XSSFWorkbook) WorkbookFactory.create(is);  //导入2007 以上
			try {
				wb = new XSSFWorkbook(is);
			} catch (IOException e) {
				result ="请上传excel表格";
				e.printStackTrace();
			} 
		}else {
			result ="请上传excel表格";
		}

		Sheet sheet = wb.getSheetAt(0);
		Row row = null;
		Cell cell = null;
		List<P1> listP1 = new ArrayList<P1>();
		//获取Excel的总行数:Sheet.getLastRowNum()+1(需要+1)
		for(int i=sheet.getFirstRowNum()+2; i<sheet.getLastRowNum()+1; i++){
			List list = new ArrayList();
			P1 p1 = new P1();
			//获取数据表里面的某一行
			row = sheet.getRow(i);    
			//获取Excel的总列数:Row.getLastCellNum()(不用+1)
			for(int j=row.getFirstCellNum(); j<row.getLastCellNum(); j++){
				//获取一行中的一个单元格
				String cellData = convertCellToString(row.getCell(j)).trim();
				list.add(cellData);
			}
			count++;
			p1.setName((String) list.get(0));
			listP1.add(p1);
		}
		
		try {
			p1Service.bacthAdd(listP1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "写入失败";
		}
		long endTime = System.currentTimeMillis();
		long useTime = endTime - startTime;
		String suffix = String.valueOf(useTime % 1000);
		while(suffix.endsWith("0")){
			suffix = suffix.substring(0, suffix.length()-1);
		}
		result = "写入成功，导入数据[" + count + "]条,耗时" + (useTime/1000) + "." + suffix + "秒";

		
		return result;
	}
	
	
	public int file_upload (Files file)throws Exception{
		
		return filesMapper.addFile(file);
		
	}

}
