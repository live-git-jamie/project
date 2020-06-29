package com.dreams.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

public class Test {

	public static void main(String[] args) {
		String string = "This is a string of many formms";
		//List<String> list = new ArrayList<String>(Arrays.asList(string.split("\\s+")));
		//list.forEach((String s) -> System.out.println(s));
//		String[] strings = string.split("\\s+");
//		
//		Stream<String> filtered = Arrays.stream(strings).filter(string->string.length()>3);
		
		System.out.println(string.contains("many differences"));
	}
}
