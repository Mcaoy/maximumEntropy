package code;

import java.util.ArrayList;

public class ExtractF {
	public ExtractF(ArrayList<String> words,ArrayList<String> features){
		for(String s:words){
			features.add("uni"+s);
		}
	}
}
