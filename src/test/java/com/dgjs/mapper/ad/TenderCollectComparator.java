package com.dgjs.mapper.ad;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TenderCollectComparator implements Comparator<BigDecimal>{

	@Override
	public int compare(BigDecimal o1, BigDecimal o2) {
		if(o1.compareTo(o2)>0){
			return 1;
		}else{
			return -2;
		}
	}

	public static void main(String[] args) {
		List<BigDecimal> list = new ArrayList<BigDecimal>();
		list.add(new BigDecimal(3f));
		list.add(new BigDecimal(3d));
		Collections.sort(list, new TenderCollectComparator());
		System.out.println(list);
		float val1 = 3f;
		Double val2 = 1.4d;
		BigDecimal bigDecimal1 = new BigDecimal(val1);
		BigDecimal bigDecimal2 = new BigDecimal(val2);
		System.out.println(bigDecimal1.subtract(bigDecimal2));
		System.out.println(bigDecimal1.compareTo(bigDecimal2));
	}

}
