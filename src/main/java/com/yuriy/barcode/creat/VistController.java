/**
 * 
 * 
 */
package com.yuriy.barcode.creat;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.csvreader.CsvReader;

import net.sf.json.JSONObject;

/**
 * @author Yuriy
 * @date 2019年1月10日下午4:20:19
 * @description
 */
@RestController
@RequestMapping(value = "request")
public class VistController {
	
	private Logger logger = LoggerFactory.getLogger(VistController.class);
	
	@RequestMapping(value = "code", method = { RequestMethod.POST, RequestMethod.GET })
	public String request(@RequestParam(name = "csvName" ,required = false) String csvName ) {
		Response rsp = new Response();
		int count = 0;
		if (StringUtils.isEmpty(csvName)) {
			rsp.setCode("3001");
			rsp.setInfo("csv文件名为空,请检查.");
		} else {
			String path = "d://" + csvName;
			String name = csvName.split("\\.")[0];
			try {
				CsvReader csvReader = new CsvReader(path);
				try {
					//csvReader.readHeaders();
					CsvPo csvPo = null;
					while (csvReader.readRecord()) {
						String msg = csvReader.getRawRecord();
						try {
							csvPo = new CsvPo();
							csvPo.setName(name);
							csvPo.setMsg(msg);
							Queue.queue.put(csvPo);
							count = count + 1;
							logger.debug("文件{},数据{}保存至队列.",csvName,msg);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					rsp.setCode("0000");
					rsp.setInfo(csvName+"文件数据已存入队列,待生成条码,此csv文件共有"+count+"条数据");
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return JSONObject.fromObject(rsp).toString();
	}
}
