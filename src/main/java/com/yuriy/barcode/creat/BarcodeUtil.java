/**
 * 
 * 
 */
package com.yuriy.barcode.creat;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
import org.springframework.util.StringUtils;


/**
 * @author Yuriy
 * @date 2019年1月10日下午1:57:02
 * @description
 */
public class BarcodeUtil {
	/**
	 * 生成文件
	 *
	 * @param msg
	 * @param path
	 * @return
	 */
	public static File generateFile(String msg, String path) {
		File file = new File(path);
		try {
			generate(msg, new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		return file;
	}


	/**
	 * 生成到流
	 *
	 * @param msg
	 * @param ous
	 */
	public static void generate(String msg, OutputStream ous) {
		if (StringUtils.isEmpty(msg) || ous == null) {
			return;
		}

		Code39Bean bean = new Code39Bean();

		// 精细度
		final int dpi = 150;
		// module宽度
		final double moduleWidth = UnitConv.in2mm(1.0f / dpi);

		// 配置对象
		bean.setModuleWidth(moduleWidth);
		bean.setWideFactor(3);
		bean.doQuietZone(false);

		String format = "image/png";
		try {
			// 输出到流
			BitmapCanvasProvider canvas = new BitmapCanvasProvider(ous, format, dpi, BufferedImage.TYPE_BYTE_BINARY,false, 0);
			// 生成条形码
			bean.generateBarcode(canvas, msg);
			// 结束绘制
			canvas.finish();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}finally {
			if(ous != null){
				try {
					ous.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
