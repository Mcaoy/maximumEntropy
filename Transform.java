package code;

import java.util.ArrayList;
import java.util.HashMap;

import com.Item;
import com.Template;
import com.TotalTable;

public class Transform {
	public void translated(TotalTable table,ArrayList<Item> itemlist,ArrayList<Template> template){
		for(Item it:itemlist){
			Template temp = new Template();
			labelTransL(table.label_table,it.label,temp.tlabel);
			ArrayList<String> features = new ArrayList<String>();
			new ExtractF(it.sentence,features);
			featureTransL(table.features_table,features,temp.tfeature);
			template.add(temp);
		}
	}
	public void labelTransL(ArrayList<String> label_table,String label,ArrayList<Integer> tlabel){
		for(String l:label_table){
			if(label.equals(l)){
				tlabel.add(1);
			}else{
				tlabel.add(0);
			}
		}
	}
	public void featureTransL(HashMap<String,Integer> features_table,ArrayList<String> features,ArrayList<Integer> tfeature){
		for(String f:features){
			if(features_table.get(f)!=null){
				tfeature.add(features_table.get(f));
			}
		}
	}
}
