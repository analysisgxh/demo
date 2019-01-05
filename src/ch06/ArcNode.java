package ch06;

//鍥剧殑閭绘帴琛ㄥ瓨鍌ㄨ〃绀轰腑鐨勫姬缁撶偣绫�
public class ArcNode {
	public int adjVex;// 璇ュ姬鎵�寚鍚戠殑椤剁偣浣嶇疆

	public int value;// 杈圭殑鏉冨�

	public ArcNode nextArc;// 鎸囧悜涓嬩竴鏉″姬

	public ArcNode() {
		this(-1, 0, null);
	}

	public ArcNode(int adjVex) {
		this(adjVex, 0, null);
	}

	public ArcNode(int adjVex, int value) {
		this(adjVex, value, null);
	}

	public ArcNode(int adjVex, int value, ArcNode nextArc) {
		this.value = value;
		this.adjVex = adjVex;
		this.nextArc = nextArc;
	}

}
