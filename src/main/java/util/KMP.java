package util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import org.springframework.stereotype.Service;

import po.Information_toUser;

public class KMP {
	private String input;
	private Map<Integer,String> datas;

	public KMP(String input, Map<Integer,String> datas) {
		super();
		this.input = input;
		this.datas = datas;
	}

	/* 计算出next数组 */
	public static int[] kmpnext(String dest) {
		int[] next = new int[dest.length()];
		next[0] = 0;
		for (int i = 1, j = 0; i < dest.length(); i++) {
			while (j > 0 && dest.charAt(j) != dest.charAt(i)) {
				j = next[j - 1];
			}
			if (dest.charAt(i) == dest.charAt(j)) {
				j++;
			}
			next[i] = j;
		}
		return next;
	}

	/* 模式串和文本串匹配的过程 */
	public static int kmp(String str, String dest, int[] next) {// str文本串 dest 模式串
		int i, j;
		for (i = 0, j = 0; i < str.length(); i++) {
			while (j > 0 && str.charAt(i) != dest.charAt(j)) {
				j = next[j - 1];
			}
			if (str.charAt(i) == dest.charAt(j)) {
				j++;
			}
			if (j == dest.length()) {
				return i - j + 1;
			}
		}
		return j;
	}

	// 业务逻辑部分
	public List Judgement(){
		        int[] next = kmpnext(input);                 //    优先计算出搜索内容的next数组            
				List result = new ArrayList<Integer>();
				
				for(Map.Entry<Integer,String> element:datas.entrySet()) {     
					//else if存在的目的，因为不用会导致开头一致的话不匹配，所以取巧反向匹配
		        	if(kmp(element.getValue(),input,next) > 0) {
		        		result.add(element.getKey());
		        	}else if(kmp(input,element.getValue(),next) > 0) {
		        		result.add(element.getKey());
		        	}
				}
				return result;
	}
}
