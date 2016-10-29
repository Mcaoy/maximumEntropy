package code;

import java.util.ArrayList;

import com.TotalTable;

public class Converter {
	public ArrayList<Double> y;
	public ArrayList<Double> enPy;
	public ArrayList<Double> gradw_item;

	public ArrayList<ArrayList<Double>> W2;
	public double EPS = 1e-6;
	public double ALPA = 0.01;
	public double REG = 1e-8;

	public Converter(TotalTable table) {
		W2 = new ArrayList<ArrayList<Double>>();
		for (int i = 0; i < table.features_table.size(); i++) {
			ArrayList<Double> al = new ArrayList<Double>();
			for (int j = 0; j < table.label_table.size(); j++) {
				al.add(0.0);
			}
			W2.add(al);
		}
	}

	// 求y
	public void getY(ArrayList<Integer> tfeature, ArrayList<ArrayList<Double>> W) {
		y = new ArrayList<Double>();
		double a = 0;
		double b = 0;
		for (int j = 0; j < tfeature.size(); j++) {
			a += W.get(tfeature.get(j)).get(0);
			b += W.get(tfeature.get(j)).get(1);
		}
		y.add(a);
		y.add(b);
		//System.out.println(a+ ", " + b);
	}

	// 概率化
	public void enProbability(ArrayList<Double> y) {
		enPy = new ArrayList<Double>();
		double max = y.get(0);
		if (y.get(0) < y.get(1)) {
			max = y.get(1);
		}
		double a = Math.pow(Math.E, y.get(0) - max);
		double b = Math.pow(Math.E, y.get(1) - max);
		double sum = a + b;
		enPy.add(a / sum);
		enPy.add(b / sum);
	}

	// 梯度？
	public void getGradw(ArrayList<Double> enPy, ArrayList<Integer> tlabel) {
		gradw_item = new ArrayList<Double>();
		for (int i = 0; i < tlabel.size(); i++) {
			gradw_item.add(i, enPy.get(i) - tlabel.get(i));
		}
	}

	// 更新W
	public void updataW(ArrayList<Integer> tfeature, ArrayList<ArrayList<Double>> gradW,
			ArrayList<ArrayList<Double>> W) {
		for (int i = 0; i < tfeature.size(); i++) {
			int feature_id = tfeature.get(i);
			for (int j = 0; j < W2.get(feature_id).size(); j++) {
				double squareW = gradW.get(feature_id).get(j) * gradW.get(feature_id).get(j);
				W2.get(feature_id).set(j, W2.get(feature_id).get(j) + squareW);
			}
			ArrayList<Double> sqrt_W2 = new ArrayList<Double>();
			sqrt_W2.add(0.0);
			sqrt_W2.add(0.0);
			for (int k = 0; k < W2.get(feature_id).size(); k++) {
				double sqrt_w = Math.sqrt(W2.get(feature_id).get(k) + EPS);
				sqrt_W2.set(k, sqrt_w);
			}
			// W[x[j]] = (W[x[j]]*sqrtEg2W-gradW[x[y]*ALPA)/(ALPA+sqrtEg2W)
			ArrayList<Double> list = new ArrayList<Double>();// W[x[j]]*sqrtEg2W
			ArrayList<Double> list2 = new ArrayList<Double>();// gradW[x[y]*ALPA
			ArrayList<Double> list3 = new ArrayList<Double>();// ALPA+sqrtEg2W
			ArrayList<Double> list4 = new ArrayList<Double>();
			for (int n = 0; n < W.get(feature_id).size(); n++) {
				list.add(W.get(feature_id).get(n) * sqrt_W2.get(n));
				list2.add(gradW.get(feature_id).get(n) * ALPA);
			}
			for (int p = 0; p < sqrt_W2.size(); p++) {
				list3.add(sqrt_W2.set(p, sqrt_W2.get(p) + ALPA * REG));
			}
			for (int p = 0; p < list.size(); p++) {
				list4.add((list.get(p) - list2.get(p)));
			}
			for (int p = 0; p < list4.size(); p++) {
				W.get(feature_id).set(p, list4.get(p) / list3.get(p));
			}
			//System.out.println(W.get(feature_id).get(0)+","+W.get(feature_id).get(1));
		}
		//System.out.println();
	}
}
