package com.taotao.test;

import org.junit.Test;

import com.taotao.common.utils.IDUtils;

public class TestStr {
	@Test
	public void testCase1(){
		String oldName = "abc.jpg";
		String newName = IDUtils.genImageName()+oldName.substring(oldName.indexOf("."));
		System.out.println(newName);

	}
	
	
	
}
