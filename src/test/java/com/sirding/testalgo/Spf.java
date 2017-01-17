package com.sirding.testalgo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @Described	: Spf = Shortest Process First.最短优先算法
 * @project		: com.sirding.testalgo.Spn
 * @author 		: zc.ding
 * @date 		: 2017年1月17日
 */
public class Spf {

	private List<SpfJob> jobList = new ArrayList<SpfJob>();
	private long[] arr = {1, 4, 10, 7};
	
	/**
	 * @Described			: 单通道多任务不同时到达
	 * @author				: zc.ding
	 * @date 				: 2017年1月17日
	 */
//	@Before
	public void diffTime(){
		long start = System.currentTimeMillis();
		for(long tmp : arr){
			jobList.add(new SpfJob(start + (int)(Math.random()*100), tmp));
		}
		sortList();
	}
	
	/**
	 * @Described			: 单通道多任务同时到达
	 * @author				: zc.ding
	 * @date 				: 2017年1月17日
	 */
	@Before
	public void sameTime(){
		long start = System.currentTimeMillis();
		for(long tmp : arr){
			jobList.add(new SpfJob(start, tmp));
		}
		sortList();
	}
	
	private void sortList() {
		Collections.sort(jobList, new Comparator<SpfJob>() {
			@Override
			public int compare(SpfJob o1, SpfJob o2) {
				return (int)(o1.execTime - o2.execTime);
			}
		});
	}
	
	/**
	 * @Described			: 单通道最短优先算法-计算平均周转时间
	 * 							测试用例:
	 * 								多个任务同时到达，sameTime()初始化数据
	 * @author				: zc.ding
	 * @date 				: 2017年1月17日
	 */
	@Test
	public void test(){
//		方式一：逐步计算每个任务的周转时间
		long total = 0;
		long costTime = 0;
		for(SpfJob job : jobList){
			costTime = (costTime + job.getExecTime());
			total += costTime;
			System.out.println("job_" + job.execTime + "周转时间:" + costTime);
		}
		
//		方法二:通过阶乘的方式计算
//		for(int i = 0; i < jobList.size(); i++){
//			total += (jobList.size() - i) * jobList.get(i).getExecTime();
//		}
		System.out.println("总周转时间：" + total);
		System.out.println("平均周转时间：" + ((double)total/(double)jobList.size()));
	}	
	
	/**
	 * @Described	: 任务信息
	 * @project		: com.sirding.testalgo.SpfJob
	 * @author 		: zc.ding
	 * @date 		: 2017年1月17日
	 */
	static class SpfJob implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		//到达时间
		private long arrivalTime;
		//执行时间
		private long execTime;
		//任务开始时间
		private long startTime;
		//任务结束时间
		private long endTime;
		//周转时间
		private long totalTime;
		
		public SpfJob(){}
		
		public SpfJob(long startTime, long execTime){
			this.startTime = startTime;
			this.execTime = execTime;
		}
		
		public long getArrivalTime() {
			return arrivalTime;
		}
		public void setArrivalTime(long arrivalTime) {
			this.arrivalTime = arrivalTime;
		}
		public long getExecTime() {
			return execTime;
		}
		public void setExecTime(long execTime) {
			this.execTime = execTime;
		}
		public long getStartTime() {
			return startTime;
		}
		public void setStartTime(long startTime) {
			this.startTime = startTime;
		}
		public long getEndTime() {
			return endTime;
		}
		public void setEndTime(long endTime) {
			this.endTime = endTime;
		}
		public long getTotalTime() {
			return totalTime;
		}
		public void setTotalTime(long totalTime) {
			this.totalTime = totalTime;
		}
	}
}
