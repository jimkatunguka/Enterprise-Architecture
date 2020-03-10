package cs544.exercise19_1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookDao implements cs544.exercise19_1.IBookDao {
	private static int idCount = 1;
	private Map<Integer, cs544.exercise19_1.Book> books = new HashMap<>();
	
	public BookDao() {
		add(new cs544.exercise19_1.Book("Sunshine", "45678", "James Bond", 65.7));
		add(new cs544.exercise19_1.Book("Moonlight", "87654", "Karl jenkins", 180.2));
	}
	
	@Override
	public List<cs544.exercise19_1.Book> getAll() {
		return new ArrayList<cs544.exercise19_1.Book>(books.values());
	}
	
	@Override
	public void add(cs544.exercise19_1.Book book) {
		book.setId(idCount);
		books.put(idCount, book);
		idCount++;
	}
	
	@Override
	public cs544.exercise19_1.Book get(int id) {
		cs544.exercise19_1.Book result = books.get(id);
		
		if (result == null) {
			throw new NoSuchResourceException("Book", id);
		}
		
		return result;
	}
	
	@Override
	public void update(int bookId, cs544.exercise19_1.Book book) {
		books.put(bookId, book);
	}
	
	@Override
	public void delete(int bookId) {
		cs544.exercise19_1.Book removed = books.remove(bookId);
		if (removed == null) {
			throw new NoSuchResourceException("Book", bookId);
		}
	}
}
