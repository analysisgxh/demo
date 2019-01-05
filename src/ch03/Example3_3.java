package ch03;

/**
 * 
 * 銆愪緥3-3銆戣〃杈惧紡姹傚�奸棶棰橈細缂栫▼瀹炵幇绠楁湳琛ㄨ揪寮忔眰鍊硷紙鍖呮嫭灏嗗師琛ㄨ揪寮忚浆鎹㈡垚鍚庣紑琛ㄨ揪寮忓強鏍规嵁鍚庣紑琛ㄨ揪寮忔眰鍊间袱涓繃绋嬶級
 * 
 */
public class Example3_3 {
	// 姝ゅ嚱鏁板皢琛ㄨ揪寮忓彉鎹负鍚庣紑琛ㄨ揪寮忥紝鎶婄粨鏋滀互瀛楃涓茬殑褰㈠紡杩斿洖锛屾鍑芥暟鐨勭畻娉曞涓嬶細
	// 1)妫�鏌ヨ〃杈惧紡鐨勪笅涓�鍏冪礌銆�
	// 2)鍋囧鏄釜鎿嶄綔鏁帮紝杈撳嚭銆�
	// 3)鍋囧鏄釜寮�鎷彿锛屽皢鍏跺帇鏍堛��
	// 4)鍋囧鏄釜杩愮畻绗︼紝鍒�
	// i) 鍋囧鏍堜负绌猴紝灏嗘杩愮畻绗﹀帇鏍堛��
	// ii) 鍋囧鏍堥《鏄紑鎷彿锛屽皢姝よ繍绠楃鍘嬫爤銆�
	// iii) 鍋囧姝よ繍绠楃姣旀爤椤惰繍绠楃浼樺厛绾ч珮锛屽皢姝よ繍绠楃鍘嬪叆鏍堜腑銆�
	// iv) 鍚﹀垯鏍堥《杩愮畻绗﹀嚭鏍堝苟杈撳嚭锛岄噸澶嶆楠�4銆�
	// 5)鍋囧鏄釜闂嫭鍙凤紝鏍堜腑杩愮畻绗﹂�愪釜鍑烘爤骞惰緭鍑猴紝鐩村埌閬囧埌寮�鎷彿銆傚紑鎷彿鍑烘爤骞朵涪寮冦��
	// 6)鍋囧琛ㄨ揪寮忚繕鏈畬姣曪紝璺宠浆鍒版楠�1銆�
	// 7)鍋囧閬嶅巻琛ㄨ揪寮忓畬姣曪紝鏍堜腑鍓╀綑鐨勬墍鏈夋搷浣滅鍑烘爤骞跺姞鍒板悗缂�琛ㄨ揪寮忕殑灏鹃儴銆�

	public String convertToPostfix(String expression) throws Exception {
		LinkStack st = new LinkStack();// 鐢ㄤ簬瀛樻斁鍑芥暟杩愯杩囩▼涓殑鎷彿鍜岃繍绠楃锛堝嚱鏁扮粨鏉熸椂姝ゆ爤涓虹┖锛�
		String postfix = new String();// 鐢ㄤ簬杈撳嚭鐨勫悗缂�琛ㄨ揪寮�
		for (int i = 0; expression != null && i < expression.length(); i++) {
			char c = expression.charAt(i);// 鎸囧畾绱㈠紩澶勭殑 char 鍊�
			if (' ' != c) {// 瀛楃c涓嶄负绌烘牸
				if (isOpenParenthesis(c)) {
					st.push(c);// 涓哄紑鎷彿锛屽帇鏍�
				} else if (isCloseParenthesis(c)) {// 涓洪棴鎷彿,鏍堜腑杩愮畻绗﹂�愪釜鍑烘爤骞舵斁鍏ョ敤浜庤緭鍑虹殑鏍堬紝鐩村埌閬囧埌寮�鎷彿銆傚紑鎷彿鍑烘爤骞朵涪寮�
					Character ac = (Character) st.pop();// 绉婚櫎鏍堥《鍏冪礌
					while (!isOpenParenthesis(ac.charValue())) {// 涓�鐩村埌涓哄紑鎷彿涓烘
						postfix = postfix.concat(ac.toString());// 涓茶仈鍒板悗缂�琛ㄨ揪寮忕殑缁撳熬
						ac = (Character) st.pop();
					}
				} else if (isOperator(c)) {// 涓鸿繍绠楃
					if (!st.isEmpty()) {// 鏍堥潪绌猴紝鍙栧嚭鏍堜腑浼樺厛绾ч珮鐨勬搷浣滅涓茶仈鍒板悗缂�琛ㄨ揪寮忕殑缁撳熬
						Character ac = (Character) st.pop();
						while (ac != null
								&& priority(ac) >= priority(c)) {// 浼樺厛绾ф瘮杈�
							postfix = postfix.concat(ac.toString());// 涓茶仈鍒板悗缂�琛ㄨ揪寮忕殑缁撳熬
							ac = (Character) st.pop();
						}

						if (ac != null) {// 濡傛灉鏈�鍚庝竴娆″彇鍑虹殑浼樺厛绾т綆鐨勬搷浣滅锛岄噸鏂板帇鏍�
							st.push(ac);
						}
					}
					st.push(c);
				}0.
				else {// 涓烘搷浣滄暟锛屼覆鑱斿埌鍚庣紑琛ㄨ揪寮忕殑缁撳熬
					postfix = postfix.concat(String.valueOf(c));
				}
			}
		}

		while (!st.isEmpty()) {// 鏍堜腑鍓╀綑鐨勬墍鏈夋搷浣滅涓茶仈鍒板悗缂�琛ㄨ揪寮忕殑缁撳熬
			postfix = postfix.concat(String.valueOf(st.pop()));// 涓茶仈鍒板悗缂�琛ㄨ揪寮忕殑缁撳熬
		}

		return postfix;
	}

	// 瀵瑰悗缂�琛ㄨ揪寮忚繘琛屾眰鍊艰绠楋紝姝ゅ嚱鏁扮殑绠楁硶濡備笅锛�
	// 1)鍒濆鍖栦竴涓┖鍫嗘爤
	// 2)浠庡乏鍒板彸璇诲叆鍚庣紑琛ㄨ揪寮�
	// i)濡傛灉瀛楃鏄竴涓搷浣滄暟锛屾妸瀹冨帇鍏ュ爢鏍堛��
	// ii)濡傛灉瀛楃鏄釜鎿嶄綔绗︼紝寮瑰嚭涓や釜鎿嶄綔鏁帮紝鎵ц鎭板綋鎿嶄綔锛岀劧鍚庢妸缁撴灉鍘嬪叆鍫嗘爤銆傚鏋滀笉鑳藉寮瑰嚭涓や釜鎿嶄綔鏁帮紝鍚庣紑琛ㄨ揪寮忕殑璇硶灏变笉姝ｇ‘銆�
	// 3)鍒板悗缂�琛ㄨ揪寮忔湯灏撅紝浠庡爢鏍堜腑寮瑰嚭缁撴灉銆傝嫢鍚庣紑琛ㄨ揪寮忔牸寮忔纭紝閭ｄ箞鍫嗘爤搴旇涓虹┖銆�

	public double numberCalculate(String postfix) throws Exception {
		LinkStack st = new LinkStack();
		for (int i = 0; postfix != null && i < postfix.length(); i++) {
			char c = postfix.charAt(i);// 鎸囧畾绱㈠紩澶勭殑 char 鍊�
			if (isOperator(c)) {// 褰撲负鎿嶄綔绗︽椂
				// 鍙栧嚭涓や釜鎿嶄綔鏁�
				double d2 = Double.valueOf(st.pop().toString());
				double d1 = Double.valueOf(st.pop().toString());
				double d3 = 0;
				if ('+' == c) {// 鍔犳硶杩愮畻
					d3 = d1 + d2;
				} else if ('-' == c) {// 鍔犳硶杩愮畻
					d3 = d1 - d2;
				} else if ('*' == c) {// 涔樻硶杩愮畻
					d3 = d1 * d2;
				} else if ('/' == c) {// 闄ゆ硶杩愮畻
					d3 = d1 / d2;
				} else if ('^' == c) {// 骞傝繍绠�
					d3 = Math.pow(d1, d2);
				} else if ('%' == c) {
					d3 = d1 % d2;
				}
				st.push(d3);
			} else {// 褰撲负鎿嶄綔鏁版椂
				st.push(c);
			}
		}
		return (Double) st.pop();// 杩斿洖杩愮畻缁撴灉
	}

	// 鍒ゆ柇瀛楃涓叉槸鍚︿负杩愮畻绗�
	public boolean isOperator(char c) {
		if ('+' == c || '-' == c || '*' == c || '/' == c || '^' == c
				|| '%' == c) {
			return true;
		} else {
			return false;
		}
	}

	// 鍒ゆ柇瀛楃涓叉槸鍚︿负寮�鎷彿
	public boolean isOpenParenthesis(char c) {
		return '(' == c;
	}

	// 鍒ゆ柇瀛楃涓叉槸鍚︿负闂嫭鍙�
	public boolean isCloseParenthesis(char c) {
		return ')' == c;
	}

	// 鍒ゆ柇杩愮畻娉曠殑浼樺厛绾�
	public int priority(char c) {
		if (c == '^') {// 涓哄箓杩愮畻
			return 3;
		}
		if (c == '*' || c == '/' || c == '%') {// 涓轰箻銆侀櫎銆佸彇妯¤繍绠�
			return 2;
		} else if (c == '+' || c == '-') {// 涓哄姞銆佸噺杩愮畻
			return 1;
		} else { // 鍏朵粬
			return 0;
		}
	}

	public static void main(String[] args) throws Exception {
		Example3_3 p = new Example3_3();
		String postfix = p.convertToPostfix("(1 + 2)*(5 - 2)/2^2 + 5%3");// 杞寲涓哄悗缂�琛ㄨ揪寮�
		System.out.println("琛ㄨ揪寮忕殑缁撴灉涓猴細 " + p.numberCalculate(postfix));// 瀵瑰悗缂�琛ㄨ揪寮忔眰鍊煎悗锛屽苟杈撳嚭
	}
}

// 璋冭瘯缁撴灉锛�
// 琛ㄨ揪寮忕殑缁撴灉涓猴細 4.25

