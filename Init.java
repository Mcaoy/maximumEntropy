package com;

import java.util.ArrayList;

public class Init {
	public ArrayList<ArrayList<Double>> W;
	//public ArrayList<ArrayList<Double>> W2;
	public ArrayList<ArrayList<Double>> gradW;
	
	public Init(TotalTable table,ArrayList<Template> temp){
		W = new ArrayList<ArrayList<Double>>();
		for(int i=0;i<table.features_table.size();i++){
			ArrayList<Double> al = new ArrayList<Double>();
			for(int j=0;j<table.label_table.size();j++){
				al.add(Math.random()*10);
			}
			W.add(al);
		}
		gradW = new ArrayList<ArrayList<Double>>();
		for(int i=0;i<table.features_table.size();i++){
			ArrayList<Double> al = new ArrayList<Double>();
			for(int j=0;j<table.label_table.size();j++){
				al.add(0.0);
			}
			gradW.add(al);
		}
	}
	public void gradWclear(){
		for(int i=0;i<gradW.size();i++){
			for(int j=0;j<gradW.get(0).size();j++){
				gradW.get(i).set(j, 0.0);
			}
		}
	}
}
