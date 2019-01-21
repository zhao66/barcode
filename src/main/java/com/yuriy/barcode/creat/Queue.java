/**
 * 
 * 
 */
package com.yuriy.barcode.creat;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Yuriy
 * @date 2019年1月10日下午5:58:15
 * @description csv文件存放的队列
 */
public class Queue {
	public static BlockingQueue<CsvPo> queue =  new LinkedBlockingQueue<CsvPo>();
}
