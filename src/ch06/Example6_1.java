package ch06;

//銆愪緥6.1銆戠紪绋嬪疄鐜板簲鐢ㄥ箍搴︿紭鍏堟悳绱㈢畻娉曠‘瀹氭棤鍚戝浘鐨勮繛閫氬垎閲忋�
import ch03.LinkQueue;

public class Example6_1 {
	public final static int INFINITY = Integer.MAX_VALUE;

	public static void CC_BFS(IGraph G) throws Exception {
		boolean[] visited = new boolean[G.getVexNum()];// 璁块棶鏍囧織鏁扮粍
		for (int v = 0; v < G.getVexNum(); v++)
			// 璁块棶鏍囧織鏁扮粍鍒濆鍖�
			visited[v] = false;
		LinkQueue Q = new LinkQueue();// 杈呭姪闃熷垪Q
		LinkQueue P = new LinkQueue();// 杈呭姪闃熷垪P,鐢ㄤ簬璁板綍杩為�鍒嗛噺鐨勯《鐐�
		int i = 0;// 鐢ㄤ簬璁版暟杩為�鍒嗛噺鐨勪釜鏁�
		for (int v = 0; v < G.getVexNum(); v++) {
			P.clear();// 闃熷垪娓呯┖
			if (!visited[v]) {// v灏氭湭璁块棶
				visited[v] = true;
				P.offer(G.getVex(v));
				Q.offer(v);// v鍏ラ槦鍒�
				while (!Q.isEmpty()) {
					int u = (Integer) Q.poll();// 闃熷ご鍏冪礌鍑洪槦鍒楀苟璧嬪�缁檜
					for (int w = G.firstAdjVex(u); w >= 0; w = G.nextAdjVex(u,
							w)) {
						if (!visited[w]) {// w涓簎鐨勫皻鏈闂殑閭绘帴椤剁偣
							visited[w] = true;
							P.offer(G.getVex(w));
							Q.offer(w);
						}
					}
				}
				System.out.println("图的第" + ++i + "个连通分量为：");
				while (!P.isEmpty())
					System.out.print(P.poll().toString() + " ");
				System.out.println();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		Object vexs[] = { "A", "B", "C", "D", "E", "F", "G" };
		int[][] arcs = { { 0, 1, INFINITY, 1, INFINITY, INFINITY, INFINITY },
				{ 1, 0, 1, INFINITY, INFINITY, INFINITY, INFINITY },
				{ INFINITY, 1, 0, 1, INFINITY, INFINITY, INFINITY },
				{ 1, INFINITY, 1, 0, INFINITY, INFINITY, INFINITY },
				{ INFINITY, INFINITY, INFINITY, INFINITY, 0, 1, INFINITY },
				{ INFINITY, INFINITY, INFINITY, INFINITY, 1, 0, 1 },
				{ INFINITY, INFINITY, INFINITY, INFINITY, INFINITY, 1, 0 }, };
		MGraph G = new MGraph(GraphKind.UDG, 7, 6, vexs, arcs);
		CC_BFS(G);
	}
}
// 璋冭瘯缁撴灉锛�
// 鍥剧殑绗�涓繛閫氬垎閲忎负锛�
// A B D C
// 鍥剧殑绗�涓繛閫氬垎閲忎负锛�
// E F G
