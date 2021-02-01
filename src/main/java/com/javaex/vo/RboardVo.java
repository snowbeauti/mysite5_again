package com.javaex.vo;

public class RboardVo {

	private int no;
	private int user_no;
	private String title;
	private String content;
	private int hit;
	private String reg_date;
	private int group_no;
	private int order_no;
	private int depth;
	private String id;
	private String name;

	public RboardVo() {
	}

	public RboardVo(int no) {
		this.no = no;
	}

	public RboardVo(int no, int hit) {
		this.no = no;
		this.hit = hit;
	}
	

	public RboardVo(int no, String title, String content) {

		this.no = no;
		this.title = title;
		this.content = content;
	}

	
	
	public RboardVo(String title, String content, int user_no) {
		this.user_no = user_no;
		this.title = title;
		this.content = content;
	}
	

	public RboardVo(int no, String title, String name, String reg_date) {
		this.no = no;
		this.title = title;
		this.reg_date = reg_date;
		this.name = name;
	}

	public RboardVo(int no, int user_no, String content, int hit) {
		this.no = no;
		this.user_no = user_no;
		this.content = content;
		this.hit = hit;
	}

	public RboardVo(int no, String title, String content, int hit, String reg_date, int user_no) {
		this.no = no;
		this.user_no = user_no;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.reg_date = reg_date;
	}

	public RboardVo(int no, String title, String content, int hit, String reg_date, int user_no, String id,
			String name) {
		this.no = no;
		this.user_no = user_no;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.reg_date = reg_date;
		this.id = id;
		this.name = name;
	}

	public RboardVo(int no, int user_no, String title, String content, int hit, String reg_date, int group_no,
			int order_no, int depth, String id, String name) {
		this.no = no;
		this.user_no = user_no;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.reg_date = reg_date;
		this.group_no = group_no;
		this.order_no = order_no;
		this.depth = depth;
		this.id = id;
		this.name = name;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public int getGroup_no() {
		return group_no;
	}

	public void setGroup_no(int group_no) {
		this.group_no = group_no;
	}

	public int getOrder_no() {
		return order_no;
	}

	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "RboardVo [no=" + no + ", user_no=" + user_no + ", title=" + title + ", content=" + content + ", hit="
				+ hit + ", reg_date=" + reg_date + ", group_no=" + group_no + ", order_no=" + order_no + ", depth="
				+ depth + ", id=" + id + ", name=" + name + "]";
	}

	

}
