package cs544.exercise19_1;

import java.util.List;


public interface IBookDao {

	public abstract List<cs544.exercise19_1.Book> getAll();

	public abstract void add(cs544.exercise19_1.Book book);

	public abstract cs544.exercise19_1.Book get(int id);

	public abstract void update(int bookId, cs544.exercise19_1.Book book);

	public abstract void delete(int bookId);

}