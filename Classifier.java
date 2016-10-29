package code;

import java.util.ArrayList;

import com.Init;
import com.Item;
import com.Template;
import com.TotalTable;

public class Classifier {
	public static void main(String[] args) {
		String trainpath = "D:/Data/car.train.utf8";
		String testpath = "D:/Data/car.test.utf8";
		ArrayList<Item> train = new ArrayList<Item>();
		ArrayList<Item> test = new ArrayList<Item>();
		Readfile rf = new Readfile();
		rf.readFile(trainpath, train);
		rf.readFile(testpath, test);
		TotalTable totalTable = new TotalTable(train);
		ArrayList<Template> train_temp = new ArrayList<Template>();
		ArrayList<Template> test_temp = new ArrayList<Template>();
		Transform T = new Transform();
		T.translated(totalTable, train, train_temp); // 3000 1000
		T.translated(totalTable, test, test_temp);
		Init variable = new Init(totalTable, train_temp);
		//for (int times = 0; times < 10000; times++) {
			for (int i = 0; i < train_temp.size(); i++) {
				Converter c = new Converter(totalTable);
				c.getY(train_temp.get(i).tfeature, variable.W);
				c.enProbability(c.y);
				System.out.println(c.y.get(0)+","+c.y.get(1));
				c.getGradw(c.enPy, train_temp.get(i).tlabel);
				/*
				 * for j:fSize do gradW[x[y]]+=ly;
				 */
				System.out.println(c.gradw_item.get(0)+","+c.gradw_item.get(1));
				for (int j = 0; j < train_temp.get(i).tfeature.size(); j++) {
					int feature_id = train_temp.get(i).tfeature.get(j);
					for (int k = 0; k < variable.gradW.get(feature_id).size(); k++) {
						variable.gradW.get(feature_id).set(k,variable.gradW.get(feature_id).get(k) + c.gradw_item.get(k));
					}
				}
				c.updataW(train_temp.get(i).tfeature, variable.gradW, variable.W);
				variable.gradWclear();
			}
			/*int yes = 0;
			for (int i = 0; i < test_temp.size(); i++) {
				ArrayList<Integer> y = new ArrayList<Integer>();
				double a = 0.0;
				double b = 0.0;
				for (int j = 0; j < test_temp.get(i).tfeature.size(); j++) {
					a += variable.W.get(test_temp.get(i).tfeature.get(j)).get(0);
					b += variable.W.get(test_temp.get(i).tfeature.get(j)).get(1);
				}
				if (a > b) {
					y.add(1);
					y.add(0);
				} else {
					y.add(0);
					y.add(1);
				}
				if (test_temp.get(i).tlabel.get(0) == y.get(0) && test_temp.get(i).tlabel.get(1) == y.get(1)) {
					yes++;
				}
			}
			System.out.println(yes / (test_temp.size() + 0.0) * 100 + "%");*/
		}
	//}
}
