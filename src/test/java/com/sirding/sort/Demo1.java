package com.sirding.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Demo1 {

	int[] arr = new int[]{1,5,2,10,3,11,6,2};
	
	public void test1(){
		List<Element> list = new ArrayList<Element>();
		list.add(new Element(1,5));
		list.add(new Element(3,4));
		list.add(new Element(9,5));
		Collections.sort(list);
	}
	
	class Element implements Comparable<Element>{
		private int a;
		private int b;
		private int c;
		
		public Element(){}
		
		public Element(int a, int b){
			this.a = a;
			this.b = b;
			this.sub("1");
		}
		
		public Element(int a, int b, String flag){
			this.a = a;
			this.b = b;
			this.sub(flag);
		}
		
		public int getA() {
			return a;
		}

		public void setA(int a) {
			this.a = a;
		}

		public int getB() {
			return b;
		}

		public void setB(int b) {
			this.b = b;
		}

		public int getC() {
			return c;
		}

		public void setC(int c) {
			this.c = c;
		}

		public void sub(String flag){
			if("0".equals(flag)){
				this.c = this.a - this.b;
			}else if("1".equals(flag)){
				this.c = this.b - this.a;
			}else if("2".equals(flag)){
				this.c = Math.abs(this.a - this.b);
			}
		}

		@Override
		public int compareTo(Element o) {
			return o.getC() - this.c;
		}
	}
}
