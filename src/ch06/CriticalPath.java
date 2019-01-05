package ch06;

import ch03.LinkStack;

public class CriticalPath {
	private LinkStack T = new LinkStack();// 鎷撴墤閫嗗簭鍒楅《鐐规爤

	private int[] ve, vl;// 鍚勯《鐐圭殑鏈�棭鍙戠敓鏃堕棿鍜屾渶杩熷彂鐢熸椂闂�

	// 鏈夊悜缃慓閲囩敤閭绘帴琛ㄥ瓨鍌ㄧ粨鏋勶紝姹傚悇椤剁偣鐨勬渶鏃╁彂鐢熸椂闂磛e锛岃嫢G鏃犲洖璺紝鍒欑敤鏍圱杩斿洖G鐨勪竴涓嫇鎵戝簭鍒楋紝涓斿嚱鏁拌繑鍥瀟rue锛屽惁鍒欎负false
	public boolean topologicalOrder(ALGraph G) throws Exception {
		int count = 0;// 杈撳嚭椤剁偣璁℃暟
		int[] indegree = TopologicalSort.findInDegree(G);// 姹傚悇椤剁偣鍏ュ害
		LinkStack S = new LinkStack();// 寤洪浂鍏ュ害椤剁偣鏍圫
		for (int i = 0; i < G.getVexNum(); i++)
			if (indegree[i] == 0)// 鍏ュ害涓�鑰呰繘鏍�
				S.push(i);
		ve = new int[G.getVexNum()];// 鍒濆鍖�
		while (!S.isEmpty()) {
			int j = (Integer) S.pop();
			T.push(j);// j鍙烽《鐐瑰叆T鏍堝苟璁℃暟
			++count;
			for (ArcNode arc = G.getVexs()[j].firstArc; arc != null; arc = arc.nextArc) {
				int k = arc.adjVex;
				if (--indegree[k] == 0)// 瀵筳鍙烽《鐐圭殑姣忎釜閭绘帴鐐圭殑鍏ュ害鍑�
					S.push(k);// 鑻ュ叆搴﹀噺涓�锛屽垯鍏ユ爤
				if (ve[j] + arc.value > ve[k])
					ve[k] = ve[j] + arc.value;
			}
		}

		if (count < G.getVexNum())
			return false;// 璇ユ湁鍚戝浘鏈夊洖璺�
		else
			return true;
	}

	// G涓烘湁鍚戠綉锛岃緭鍑篏鐨勫悇椤瑰叧閿椿鍔�
	public boolean criticalPath(ALGraph G) throws Exception {
		if (!topologicalOrder(G))
			return false;
		vl = new int[G.getVexNum()];
		for (int i = 0; i < G.getVexNum(); i++)
			// 鍒濆鍖栭《鐐逛簨浠剁殑鏈�繜鍙戠敓鏃堕棿
			vl[i] = ve[G.getVexNum() - 1];
		while (!T.isEmpty()) {// 鎸夋嫇鎵戦�搴忔眰鍚勯《鐐圭殑vl鍊�
			int j = (Integer) T.pop();
			for (ArcNode arc = G.getVexs()[j].firstArc; arc != null; arc = arc.nextArc) {
				int k = arc.adjVex;
				int value = arc.value;
				if (vl[k] - value < vl[j])
					vl[j] = vl[k] - value;
			}
		}

		for (int j = 0; j < G.getVexNum(); j++)
			// 姹俥e锛宔l鍜屽叧閿椿鍔�
			for (ArcNode arc = G.getVexs()[j].firstArc; arc != null; arc = arc.nextArc) {
				int k = arc.adjVex;
				int value = arc.value;
				int ee = ve[j];
				int el = vl[k] - value;
				char tag = (ee == el) ? '*' : ' ';
				System.out.println(G.getVex(j) + "->" + G.getVex(k) + " "
						+ value + " " + ee + " " + el + " " + tag);// 杈撳嚭鍏抽敭娲诲姩
			}
		return true;
	}

	public static void main(String[] args) throws Exception {
		ALGraph G = GenerateGraph.generateALGraph();
		CriticalPath p = new CriticalPath();
		p.criticalPath(G);

	}
}

// 璋冭瘯缁撴灉
// v1->v4 5 0 3
// v1->v3 4 0 2
// v1->v2 6 0 0 *
// v2->v5 1 6 6 *
// v3->v5 1 4 6
// v4->v6 2 5 8
// v5->v8 7 7 7 *
// v5->v7 9 7 7 *
// v6->v8 4 7 10
// v7->v9 2 16 16 *
// v8->v9 4 14 14 *
