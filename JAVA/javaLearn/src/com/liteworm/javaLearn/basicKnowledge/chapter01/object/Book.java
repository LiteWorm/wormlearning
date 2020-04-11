package com.liteworm.javaLearn.basicKnowledge.chapter01.object;
/**
 *
 * 定义实体类
 * 一般把所有字段都私有化，提供getter()/setter()方法对字段进行访问
 * 一般只提供午餐构造
 * 一般需要重写toString(), equals()/hashCode()
 * alt + shift + s  快捷重写
 * 
 * @author LiteWorm
 *
 */
public class Book {

	private String bookNmme;
	private String author;
	private int price;
	private String press;
	private String  isbn;
	
	
	public Book(String bookNmme, String author, int price, String press, String isbn) {
		super();
		this.bookNmme = bookNmme;
		this.author = author;
		this.price = price;
		this.press = press;
		this.isbn = isbn;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((bookNmme == null) ? 0 : bookNmme.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + ((press == null) ? 0 : press.hashCode());
		
		result = prime * result + price;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (bookNmme == null) {
			if (other.bookNmme != null)
				return false;
		} else if (!bookNmme.equals(other.bookNmme))
			return false;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (press == null) {
			if (other.press != null)
				return false;
		} else if (!press.equals(other.press))
			return false;
		if (price != other.price)
			return false;
		return true;
	}


	//重写toString()
	//快捷键 alt + shift + s  选择重写方法或者setter()/getter()方法
	@Override
	public String toString() {
		return "Book [bookNmme=" + bookNmme + ", author=" + author + ", price=" + price + ", press=" + press + ", isbn="
				+ isbn + "]";
	}


	public String getBookNmme() {
		return bookNmme;
	}


	public void setBookNmme(String bookNmme) {
		this.bookNmme = bookNmme;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public String getPress() {
		return press;
	}


	public void setPress(String press) {
		this.press = press;
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	//无参构造
	public Book() {
		System.out.println(this.toString());
	}
	
	
	public static void main(String[] args) {

		test();
	}


	private static void test() {
		Book b2 = new Book();
		Book b1 = b2;
		System.out.println(b1.hashCode());
		
	}

}

