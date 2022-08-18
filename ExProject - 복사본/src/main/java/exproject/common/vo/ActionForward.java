package exproject.common.vo;

public class ActionForward {
	private String url;
	private boolean redirect; // false면 dispatch방식으로 포워딩
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isRedirect() {
		return redirect;
	}
	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
}
