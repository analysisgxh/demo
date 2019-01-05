package ch06;

import java.util.Scanner;

//鍥剧殑鏁扮粍琛ㄧず娉�
public class MGraph implements IGraph {
	public final static int INFINITY = Integer.MAX_VALUE;

	private GraphKind kind;// 鍥剧殑绉嶇被鏍囧織

	private int vexNum, arcNum;// 鍥剧殑褰撳墠椤剁偣鏁板拰杈规暟

	private Object[] vexs;// 椤剁偣

	private int[][] arcs;// 閭绘帴鐭╅樀

	public MGraph() {
		this(null, 0, 0, null, null);
	}

	public MGraph(GraphKind kind, int vexNum, int arcNum, Object[] vexs,
			int[][] arcs) {
		this.kind = kind;
		this.vexNum = vexNum;
		this.arcNum = arcNum;
		this.vexs = vexs;
		this.arcs = arcs;
	}

	// 鍒涘缓涓�釜鍥�
	public void createGraph() {
		Scanner sc = new Scanner(System.in);
		System.out.println("璇疯緭鍏ュ浘鐨勭被鍨嬶細");
		GraphKind kind = GraphKind.valueOf(sc.next());
		switch (kind) {
		case UDG:
			createUDG();// 鏋勯�鏃犲悜鍥�
			return;
		case DG:
			createDG();// 鏋勯�鏈夊悜鍥�
			return;
		case UDN:
			createUDN();// 鏋勯�鏃犲悜缃�
			return;
		case DN:
			createDN();// 鏋勯�鏈夊悜缃�
			return;
		}
	}

	// 鍒涘缓鏃犲悜鍥�
	private void createUDG() {
		// 鐣�
	};

	// 鍒涘缓鏈夊悜鍥�
	private void createDG() {
		// 鐣�
	};

	// 鍒涘缓鏃犲悜缃�
	private void createUDN() {
		Scanner sc = new Scanner(System.in);
		System.out.println("璇峰垎鍒緭鍏ュ浘鐨勯《鐐规暟銆佸浘鐨勮竟鏁�");
		vexNum = sc.nextInt();
		arcNum = sc.nextInt();
		vexs = new Object[vexNum];
		System.out.println("璇峰垎鍒緭鍏ュ浘鐨勫悇涓《鐐�");
		for (int v = 0; v < vexNum; v++)
			// 鏋勯�椤剁偣鍚戦噺
			vexs[v] = sc.next();

		arcs = new int[vexNum][vexNum];
		for (int v = 0; v < vexNum; v++)
			// 鍒濆鍖栭偦鎺ョ煩闃�
			for (int u = 0; u < vexNum; u++)
				arcs[v][u] = INFINITY;

		System.out.println("璇疯緭鍏ュ悇涓竟鐨勯《鐐瑰強鍏舵潈鍊�");
		for (int k = 0; k < arcNum; k++) {
			int v = locateVex(sc.next());
			int u = locateVex(sc.next());
			arcs[v][u] = arcs[u][v] = sc.nextInt();
		}
	}

	// 鍒涘缓鏈夊悜缃�
	private void createDN() {
		Scanner sc = new Scanner(System.in);
		System.out.println("璇峰垎鍒緭鍏ュ浘鐨勯《鐐规暟銆佸浘鐨勮竟鏁�");
		vexNum = sc.nextInt();
		arcNum = sc.nextInt();
		vexs = new Object[vexNum];
		System.out.println("璇峰垎鍒緭鍏ュ浘鐨勫悇涓《鐐�");
		for (int v = 0; v < vexNum; v++)
			// 鏋勯�椤剁偣鍚戦噺
			vexs[v] = sc.next();

		arcs = new int[vexNum][vexNum];
		for (int v = 0; v < vexNum; v++)
			// 鍒濆鍖栭偦鎺ョ煩闃�
			for (int u = 0; u < vexNum; u++)
				arcs[v][u] = INFINITY;

		System.out.println("璇疯緭鍏ュ悇杈圭殑椤剁偣鍙婂叾鏉冨�:");
		for (int k = 0; k < arcNum; k++) {
			int v = locateVex(sc.next());
			int u = locateVex(sc.next());
			arcs[v][u] = sc.nextInt();
		}

	}

	// 杩斿洖椤剁偣鏁�
	public int getVexNum() {
		return vexNum;
	}

	// 杩斿洖杈规暟
	public int getArcNum() {
		return arcNum;
	}

	// 缁欏畾椤剁偣鐨勫�vex锛岃繑鍥炲叾鍦ㄥ浘涓殑浣嶇疆锛屽鏋滃浘涓笉鍖呭惈姝ら《鐐癸紝鍒欒繑鍥�1
	public int locateVex(Object vex) {
		for (int v = 0; v < vexNum; v++)
			if (vexs[v].equals(vex))
				return v;
		return -1;
	}

	// 杩斿洖v琛ㄧず缁撶偣鐨勫�锛�0 <= v < vexNum
	public Object getVex(int v) throws Exception {
		if (v < 0 && v >= vexNum)
			throw new Exception("第" + v + "个顶点不存在!");
		return vexs[v];
	}

	// 杩斿洖v鐨勭涓�釜閭绘帴鐐癸紝鑻娌℃湁閭绘帴鐐瑰垯杩斿洖-1锛�0 <= v < vexnum
	public int firstAdjVex(int v) throws Exception {
		if (v < 0 && v >= vexNum)
			throw new Exception("第" + v + "个顶点不存在!");

		for (int j = 0; j < vexNum; j++)
			if (arcs[v][j] != 0 && arcs[v][j] < INFINITY)
				return j;

		return -1;
	}

	// 杩斿洖v鐩稿浜巜鐨勪笅涓�釜閭绘帴鐐癸紝鑻鏄痸鐨勬渶鍚庝竴涓偦鎺ョ偣锛屽垯杩斿洖-1锛屽叾涓�鈮, w<vexNum
	public int nextAdjVex(int v, int w) throws Exception {
		if (v < 0 && v >= vexNum)
			throw new Exception("第" + v + "个顶点不存在!");

		for (int j = w + 1; j < vexNum; j++)
			if (arcs[v][j] != 0 && arcs[v][j] < INFINITY)
				return j;

		return -1;
	}

	public GraphKind getKind() {
		return kind;
	}

	public int[][] getArcs() {
		return arcs;
	}

	public Object[] getVexs() {
		return vexs;
	}

	public void setArcNum(int arcNum) {
		this.arcNum = arcNum;
	}

	public void setArcs(int[][] arcs) {
		this.arcs = arcs;
	}

	public void setKind(GraphKind kind) {
		this.kind = kind;
	}

	public void setVexNum(int vexNum) {
		this.vexNum = vexNum;
	}

	public void setVexs(Object[] vexs) {
		this.vexs = vexs;
	}

}
