package ch06;

import ch03.LinkStack;

public class TopologicalSort {
	// 鑻鏃犲洖璺紝鍒欒緭鍑篏鐨勯《鐐圭殑涓�釜鎷撴墤搴忓垪骞惰繑鍥瀟rue锛屽惁鍒欒繑鍥瀎alse
	public static boolean topologicalSort(ALGraph G) throws Exception {
		int count = 0;// 杈撳嚭椤剁偣璁℃暟
		int[] indegree = findInDegree(G);// 姹傚悇椤剁偣鍏ュ害
		LinkStack S = new LinkStack();// 寤洪浂鍏ュ害椤剁偣鏍圫
		for (int i = 0; i < G.getVexNum(); i++)
			if (indegree[i] == 0)// 鍏ュ害涓�鑰呰繘鏍�
				S.push(i);

		while (!S.isEmpty()) {
			int i = (Integer) S.pop();
			System.out.print(G.getVex(i) + " ");// 杈撳嚭v鍙烽《鐐瑰苟璁℃暟
			++count;
			for (ArcNode arc = G.getVexs()[i].firstArc; arc != null; arc = arc
					.nextArc) {
				int k = arc.adjVex;
				if (--indegree[k] == 0)// 瀵筳鍙烽《鐐圭殑姣忎釜閭绘帴鐐圭殑鍏ュ害鍑�
					S.push(k);// 鑻ュ叆搴﹀噺涓�锛屽垯鍏ユ爤
			}
		}
		if (count < G.getVexNum())
			return false;// 璇ユ湁鍚戝浘鏈夊洖璺�
		else
			return true;
	}

	// 姹傚悇椤剁偣鍏ュ害
	public static int[] findInDegree(ALGraph G) throws Exception {
		int[] indegree = new int[G.getVexNum()];
		for (int i = 0; i < G.getVexNum(); i++)
			for (ArcNode arc = G.getVexs()[i].firstArc; arc != null; arc = arc
					.nextArc)
				++indegree[arc.adjVex];// 鍏ュ害澧�
		return indegree;
	}

	public static void main(String[] args) throws Exception {
		ALGraph G = GenerateGraph.generateALGraph();
		TopologicalSort.topologicalSort(G);
	}
}

// 璋冭瘯缁撴灉
// v1 v2 v3 v5 v7 v4 v6 v8 v9
