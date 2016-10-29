package com;

import java.util.ArrayList;
import java.util.HashMap;

import code.ExtractF;

public class TotalTable {
	public HashMap<String,Integer> features_table;
	public ArrayList<String> label_table;
	public TotalTable(ArrayList<Item> itemlist){
		features_table = new HashMap<String,Integer>();
		label_table = new ArrayList<String>();
		for(Item it:itemlist){
			String label = it.label;
			int isExist = 0;
			for(String s:label_table){
				if(s.equals(label)){
					isExist = 1;
					break;
				}
			}
			if(isExist == 0) label_table.add(label);
			ArrayList<String> words = it.sentence;
			ArrayList<String> features = new ArrayList<String>();
			//³éÈ¡ÌØÕ÷
			new ExtractF(words,features);
			for(String f:features){
				if(features_table.get(f)==null){
					features_table.put(f, features_table.size());
				}
			}
		}
	}
}
