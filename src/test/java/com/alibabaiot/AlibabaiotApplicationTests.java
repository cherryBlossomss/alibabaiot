package com.alibabaiot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlibabaiotApplicationTests {
	@Test
	public void  duplicateRemoval2() {
		int[]  duplicate = {1,1,2,3,4,3,5,4,6,7,88,9,9};
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i <duplicate.length ; i++) {
           if (!list.contains(duplicate[i])){
           	  list.add(duplicate[i]);
		   }
		}
		System.out.printf("去重后的数据："+list);
	}
    public static boolean add1(Integer e) {
        Map<Integer,Object> map =  new HashMap<Integer,Object>() ;
        return map.put(e, "aiyoubucuoa")==null;
    }


}
