package math;

import java.math.BigDecimal;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.descriptive.moment.Kurtosis;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.Skewness;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.rank.Percentile;

public class MathTest {
	
	public static void main(String[] args) {
		 double[] values = new double[] {0,54,56,67,67,76,78,88,90,99,100,90,89,87,78,67,66,56,55,34}; 
		 //71.6000	23.104977818643324	0.6000	2	0	1	2
		 
		 Mean mean = new Mean(); // 算术平均值 
		 System.out.println("mean："+mean.evaluate(values));
		 
		 StandardDeviation StandardDeviation =new StandardDeviation();//标准差 
	     System.out.println("StandardDeviation: " +StandardDeviation.evaluate(values)); 

	     Percentile percentile = new Percentile(); // 百分位数:一组n个观测值按数值大小排列,处于p%位置的值称第p百分位数
	     System.out.println("percentile: "  + percentile.evaluate(values,60)); 
		 
	     
	     Skewness skewness = new Skewness(); //偏度
	     System.out.println("skewness："+skewness.evaluate(values));

	     Kurtosis kurtosis = new Kurtosis(); //峰度 
	     System.out.println("kurtosis："+kurtosis.evaluate(values));
	     
//	     NormalDistribution normaldis = new NormalDistribution(0,1);//新建一个标准正态分布对象
	     
	     double avg = new Mean().evaluate(values);
	     double std = new StandardDeviation().evaluate(values);
	     NormalDistribution normaldis = new NormalDistribution(avg,std); //正态分布
	     System.out.println("normaldis: ");
	     for(int i=0;i<values.length;i++){
	    	 double s1 = normaldis.cumulativeProbability(values[i]); //计算正态分布概率
	    	 double f1 = new BigDecimal(s1).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
	    	 System.out.print(f1+",");
	     }
	     
		
	}
	
	
	
	 
}
