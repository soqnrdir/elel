package com.study.common.util;

import java.text.DecimalFormat;

public class StudyFileUtil {
	
	public static String fancySize(long size) {
		DecimalFormat df = new DecimalFormat("#,###.00");
		if(size < 1024) {
			return size + "bytes";
		} else if(size < (1024 *1024)) {
			return df.format(size/1024.0) + "Kb";
		} else if(size < (1024*1024*1024)) {
			return df.format(size/(1024.0*1024.0)) + "Mb";
		} else {
			return df.format(size/(1024.0*1024.0*1024.0)) + "Gb";
		}
	}
}
