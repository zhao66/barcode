package com.yuriy.barcode;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.yuriy.barcode.creat.ExeRunable;

@SpringBootApplication
public class BarcodeApplication implements CommandLineRunner{
	
	//ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 8, 200, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(1024));
	
	public static void main(String[] args) {
		SpringApplication.run(BarcodeApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		new Thread(new ExeRunable()).start();
	}

}

