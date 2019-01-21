/**
 * 
 * 
 */
package com.yuriy.barcode.creat;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Yuriy
 * @date 2019年1月10日下午5:41:19
 * @description
 */
public class ExeRunable implements Runnable {

	private Logger logger = LoggerFactory.getLogger(ExeRunable.class);
	
	@Override
	public void run() {
		while (true) {
			CsvPo csvPo = null;
			try {
				logger.debug("队列中剩余数量为{}", Queue.queue.size());
				csvPo = Queue.queue.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if ( csvPo != null) {
				String msg = csvPo.getMsg();
				String fileUrl =  "d://"+csvPo.getName();
				File file = new File(fileUrl);
				if(!file.exists()){
					file.mkdirs();
				}
				logger.debug("文件{}.csv,数据{},正生成条码信息...",csvPo.getName(), msg);
				BarcodeUtil.generateFile(msg, fileUrl+"//" + msg + ".png");
			}
		}

	}

}


