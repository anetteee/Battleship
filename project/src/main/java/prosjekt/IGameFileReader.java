package prosjekt;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface IGameFileReader {
	/**
	 * Read a Game from a given InputStream.
	 * @param ios The input stream to read from.
	 * @return The TodoList from the InputStream.
	 */
	Game read(InputStream is);
	/**
	 * Read a Game with a given name, from a default (implementation-specific) location.
	 * @param name The name of the TodoList
	 * @return The TodoList with the given name from the default location
	 * @throws IOException if the TodoList can't be found.
	 */
	Game read(String name) throws IOException;
	
	/**
	 * Write a Game to a given OutputStream
	 * @param  The list to write
	 * @param os The stream to write to
	 */
	void write(Game game, OutputStream os);
	/**
	 * Write a TodoList to a file named after the list in a default (implementation specific) location.
	 * @param todoList The list to write
	 * @throws IOException If a file at the proper location can't be written to
	 */
	void write(Game game) throws IOException;
}
