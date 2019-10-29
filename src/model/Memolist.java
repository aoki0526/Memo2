package model;
// 1件分のメモを格納する
public class Memolist {
	private int no;
	private String title;
	private String text;
	private String date;


public Memolist (int no,String title,String text,String date){
	this.no = no;
	this.title = title;
	this.text = text;
	this.date = date;
}
// 新規作成用
public Memolist(String title, String text, String date) {
	this.title = title;
	this.text = text;
	this.date = date;
}
public int getNo(){ return no; }
public String getTitle(){ return title; }
public String getText(){ return text; }
public String getDate(){ return date; }

public void setNo(int no){
	this.no = no;
}

public void setTitle(String title){
	this.title = title;
}
public void setText(String text){
	this.text = text;
}
public void setDate(String date){
	this.date = date;
}

}