package ch06;

import ch03.LinkStack;

//鍒ゆ柇鍥句腑鏄惁瀛樺湪鐜�
public class Example6_3 {
	private boolean[] visited;// 璁块棶鏍囧織鏁扮粍

	private LinkStack S = new LinkStack();// 鎸夋繁搴︿紭鍏堟悳绱㈣闂殑鍏堝悗椤哄簭璁板綍鍦ㄤ竴涓繛閫氬垎鏀綋涓殑椤剁偣鍏冪礌

	private boolean find = false;// 鏍囩ず鏄惁宸叉壘鍒颁簡鐜�

	public void findCicle(IGraph G) throws Exception {
		visited = new boolean[G.getVexNum()];
		visited = new boolean[G.getVexNum()];
		for (int v = 0; v < G.getVexNum(); v++)
			// 璁块棶鏍囧織鏁扮粍鍒濆鍖�
			visited[v] = false;
		for (int v = 0; v < G.getVexNum(); v++)
			if (!visited[v])// 瀵瑰皻鏈闂殑椤剁偣璋冪敤DFS
				find_DFS(G, v);
		if (find)
			System.out.println("此有向图存在环!");
		else
			System.out.println("此有向图不存在环!");
	}

	public void find_DFS(IGraph G, int v) throws Exception {
		if (!find) {
			visited[v] = true;
			S.push(v);
			for (int w = G.firstAdjVex(v); w >= 0; w = G.nextAdjVex(v, w))
				if (visited[w] && isDuplicate(w))
					find = true;
				else
					// 瀵箆鐨勫皻鏈闂殑閭绘帴椤剁偣w閫掑綊璋冪敤DFS
					find_DFS(G, w);
			S.pop();
		}
	}

	// 鍒ゆ柇鏍圫涓槸鍚﹀瓨鍦ㄥ�涓簑鐨勬暟鎹厓绱�
	private boolean isDuplicate(Integer w) throws Exception {
		LinkStack S1 = new LinkStack();// 杈呭姪鏍�
		while (!S.isEmpty() && !((Integer) S.peek()).equals(w))
			// 鍒ゆ柇鏍圫涓槸鍚﹀瓨鍦ㄤ负w鐨勬暟鎹厓绱狅紝骞跺埄鐢ㄨ緟鍔╂爤S1锛岃褰曞嚭鏍堢殑鍏冪礌
			S1.push(S.pop());

		if (S.isEmpty()) {// 閲嶆柊鎶婃暟鎹厓绱犳斁鍏ユ爤S
			while (!S1.isEmpty())
				S.push(S1.pop());
			return false;
		} else
			return true;
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
		Example6_3 e = new Example6_3();
		e.findCicle(G);
	}
}
//姝ゆ湁鍚戝浘瀛樺湪鐜�
