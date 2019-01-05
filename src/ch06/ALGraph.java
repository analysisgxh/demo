package ch06;

import java.util.Scanner;

//鍥剧殑閭绘帴琛ㄥ瓨鍌ㄨ〃绀�
public class ALGraph implements IGraph {
	private GraphKind kind;// 鍥剧殑绉嶇被鏍囧織

	private int vexNum, arcNum;// 鍥剧殑褰撳墠椤剁偣鏁板拰杈规暟

	private VNode[] vexs;// 椤剁偣

	public ALGraph() {
		this(null, 0, 0, null);
	}

	public ALGraph(GraphKind kind, int vexNum, int arcNum, VNode[] vexs) {
		this.kind = kind;
		this.vexNum = vexNum;
		this.arcNum = arcNum;
		this.vexs = vexs;
	}

	// 鍒涘缓鍥�
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
		case DN:
			createDN();// 鏋勯�鏈夊悜缃�
			return;
		case UDN:
			createUDN();// 鏋勯�鏃犲悜缃�
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
		vexs = new VNode[vexNum];
		System.out.println("璇峰垎鍒緭鍏ュ浘鐨勫悇涓《鐐�");
		for (int v = 0; v < vexNum; v++)
			// 鏋勯�椤剁偣鍚戦噺
			vexs[v] = new VNode(sc.next());

		System.out.println("璇疯緭鍏ュ悇涓竟鐨勯《鐐瑰強鍏舵潈鍊�");
		for (int k = 0; k < arcNum; k++) {
			int v = locateVex(sc.next());// 寮у熬
			int u = locateVex(sc.next());// 寮уご
			int value = sc.nextInt();
			addArc(v, u, value);
			addArc(u, v, value);
		}
	}

	// 鍒涘缓鏈夊悜缃�
	private void createDN() {
		Scanner sc = new Scanner(System.in);
		System.out.println("璇峰垎鍒緭鍏ュ浘鐨勯《鐐规暟銆佸浘鐨勮竟鏁�");
		vexNum = sc.nextInt();
		arcNum = sc.nextInt();
		vexs = new VNode[vexNum];
		System.out.println("璇峰垎鍒緭鍏ュ浘鐨勫悇涓《鐐�");
		for (int v = 0; v < vexNum; v++)
			// 鏋勯�椤剁偣鍚戦噺
			vexs[v] = new VNode(sc.next());

		System.out.println("璇疯緭鍏ュ悇涓竟鐨勯《鐐瑰強鍏舵潈鍊�");
		for (int k = 0; k < arcNum; k++) {
			int v = locateVex(sc.next());// 寮у熬
			int u = locateVex(sc.next());// 寮уご
			int value = sc.nextInt();
			addArc(v, u, value);
		}
	}

	// 鍦ㄤ綅缃负v銆乽鐨勯《鐐逛箣闂达紝娣诲姞涓�潯寮э紝鍏舵潈鍊间负value
	public void addArc(int v, int u, int value) {
		ArcNode arc = new ArcNode(u, value);
		arc.nextArc=vexs[v].firstArc;
		vexs[v].firstArc=arc;
	}

	// 杩斿洖椤剁偣鏁�
	public int getVexNum() {
		return vexNum;
	}

	// 杩旇竟鏁�
	public int getArcNum() {
		return arcNum;
	}

	// 缁欏畾椤剁偣鐨勫�vex锛岃繑鍥炲叾鍦ㄥ浘涓殑浣嶇疆锛屽鏋滃浘涓笉鍖呭惈姝ら《鐐癸紝鍒欒繑鍥�1
	public int locateVex(Object vex) {
		for (int v = 0; v < vexNum; v++)
			if (vexs[v].data.equals(vex))
				return v;
		return -1;
	}

	public VNode[] getVexs() {
		return vexs;
	}

	public GraphKind getKind() {
		return kind;
	}

	// 杩斿洖v琛ㄧず缁撶偣鐨勫�锛�0 <= v < vexNum
	public Object getVex(int v) throws Exception {
		if (v < 0 && v >= vexNum)
			throw new Exception("第" + v + "个顶点不存在!");

		return vexs[v].data;
	}

	// 杩斿洖v鐨勭涓�釜閭绘帴鐐癸紝鑻娌℃湁閭绘帴鐐瑰垯杩斿洖-1锛�0 <= v < vexnum
	public int firstAdjVex(int v) throws Exception {
		if (v < 0 && v >= vexNum)
			throw new Exception("第" + v + "个顶点不存在!");

		VNode vex = vexs[v];
		if (vex.firstArc != null)
			return vex.firstArc.adjVex;
		else
			return -1;
	}

	// 杩斿洖v鐩稿浜巜鐨勪笅涓�釜閭绘帴鐐癸紝鑻鏄痸鐨勬渶鍚庝竴涓偦鎺ョ偣锛屽垯杩斿洖-1锛屽叾涓�鈮, w<vexNum
	public int nextAdjVex(int v, int w) throws Exception {
		if (v < 0 && v >= vexNum)
			throw new Exception("第" + v + "个顶点不存在!");
		VNode vex = vexs[v];

		ArcNode arcvw = null;
		for (ArcNode arc = vex.firstArc; arc != null; arc = arc.nextArc)
			if (arc.adjVex == w) {
				arcvw = arc;
				break;
			}
		if (arcvw != null && arcvw.nextArc != null)
			return arcvw.nextArc.adjVex;
		else
			return -1;
	}

	public void setArcNum(int arcNum) {
		this.arcNum = arcNum;
	}

	public void setKind(GraphKind kind) {
		this.kind = kind;
	}

	public void setVexNum(int vexNum) {
		this.vexNum = vexNum;
	}

	public void setVexs(VNode[] vexs) {
		this.vexs = vexs;
	}
}
