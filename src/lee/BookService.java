/**
 * Description:
 * <br/>Copyright (C), 2001-2012, mshen
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  mshen
 * @version  1.0
 */
package lee;

/**
 * @author mshen
 *
 */
public class BookService {
	public Book[] getBooks()
	{
		return new Book[]
		{
			new Book("Spring2.0宝典","李刚"),
			new Book("轻量级J2EE企业应用实战","李刚"),
			new Book("基于J2EE的Ajax宝典","李刚")
		 };
	}

}
