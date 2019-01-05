package ch06;

//鍥剧殑閭绘帴琛ㄥ瓨鍌ㄨ〃绀轰腑鐨勯《鐐圭粨鐐圭被
public class VNode {
	public Object data;// 椤剁偣淇℃伅

	public ArcNode firstArc;// 鎸囧悜绗竴鏉′緷闄勪簬璇ラ《鐐圭殑寮�

	public VNode() {
		this(null, null);
	}
	public VNode(Object data) {
		this(data, null);
	}
	public VNode(Object data, ArcNode firstArc) {
		this.data = data;
		this.firstArc = firstArc;
	}


}
