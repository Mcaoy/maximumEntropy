package code;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.Item;

public class Readfile {
	void readFile(String path,ArrayList<Item> itemlist){
		try {
			FileInputStream fis = new FileInputStream(path);   
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8"); 
			BufferedReader br = new BufferedReader(isr);
			while(br.ready()){
				Item it = new Item();
				String s = br.readLine();
				String[] words = s.split(" ");
				it.label = words[0];
				for(int i=1;i<words.length;i++){
					if(!words[i].equals("")){
						it.sentence.add(words[i]);
					}
				}
				itemlist.add(it);
			}
			br.close();
			isr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}