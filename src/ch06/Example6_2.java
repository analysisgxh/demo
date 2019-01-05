package ch06;

//銆愪緥6.2銆戠紪绋嬪疄鐜板垽鏂竴涓湁鍚戝浘涓换鎰忕粰瀹氱殑涓や釜椤剁偣涔嬮棿鏄惁瀛樺湪涓�潯闀垮害涓簁鐨勭畝鍗曡矾寰勩�

public class Example6_2 {
	private boolean[] visited;// 璁块棶鏍囧織鏁扮粍

	private int i = 0;// 杈呭姪鍙橀噺锛屽湪閬嶅巻杩囩▼涓敤浜庤褰曚粠婧愮偣鍑哄彂鐨勮矾寰勯暱搴�

	private boolean find = false;// 鏍囩ず鏄惁宸叉壘鍒颁簡鎸囧畾闀垮害鐨勮矾寰�

	public void findPath(IGraph G, int u, int v, int k) throws Exception {
		visited = new boolean[G.getVexNum()];
		for (int w = 0; w < G.getVexNum(); w++)
			// 璁块棶鏍囧織鏁扮粍鍒濆鍖�
			visited[w] = false;
		find_DFS(G, u, v, k);
		if (find)
			System.out.println(G.getVex(u) + "和" + G.getVex(v) + "之间存在一条长度为"
					+ k + "的简单路径");
		else
			System.out.println(G.getVex(u) + "和" + G.getVex(v) + "之间不存在一条长度为"
					+ k + "的简单路径");
	}

	private void find_DFS(IGraph G, int u, int v, int k) throws Exception {
		if (i == k && u == v)
			find = true;
		else if (!find) {
			visited[u] = true;
			for (int w = G.firstAdjVex(u); w >= 0; w = G.nextAdjVex(u, w))
				if (!visited[w]) {
					if (i < k) {
						++i;
						find_DFS(G, w, v, k);// 瀵箆鐨勫皻鏈闂殑閭绘帴椤剁偣w閫掑綊璋冪敤find_DFS
					} else
						break;// 鑻ヨ矾寰勯暱搴﹀凡杈惧埌k鍊艰�浠嶆湭鎵惧埌绠�崟璺緞锛屽垯涓嶅啀缁х画瀵瑰綋鍓嶉《鐐硅繘琛屾繁搴︿紭鍏堟悳绱�
				}
			--i;// 鍥為�涓�釜椤剁偣
		}
	}

	public static void main(String[] args) throws Exception {
		ArcNode ab = new ArcNode(1);
		VNode A = new VNode("A", ab);

		ArcNode bc = new ArcNode(2);
		ArcNode be = new ArcNode(4, 0, bc);
		VNode B = new VNode("B", be);

		ArcNode cd = new ArcNode(3);
		VNode C = new VNode("C", cd);

		ArcNode de = new ArcNode(4);
		VNode D = new VNode("D", de);

		ArcNode ef = new ArcNode(5);
		VNode E = new VNode("E", ef);

		ArcNode fa = new ArcNode(0);
		ArcNode fb = new ArcNode(1, 0, fa);
		VNode F = new VNode("F", fb);

		VNode[] vexs = { A, B, C, D, E, F };
		ALGraph G = new ALGraph(GraphKind.DG, 6, 8, vexs);
		Example6_2 e = new Example6_2();
		e.findPath(G, 0, 5, 3);
	}
}
// A鍜孎涔嬮棿瀛樺湪涓�潯闀垮害涓�鐨勭畝鍗曡矾寰�
