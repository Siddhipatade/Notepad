# Notepad - Java Swing Text Editor
This Java Swing-based text editor application allows users to create, open, edit, and save text files. It provides a graphical user interface (GUI) for performing various text editing operations, such as cut, copy, and paste. The application also supports features like zooming in and out, finding and replacing text, and a toggle for dark mode.

# Features
1. `New:` Create a new empty text file.
2. `Open:` Open an existing text file for editing.
3. `Save:` Save the current text to a file.
4. `Exit:` Close the application, with an option to save the file if it has been modified.
5. `Cut:` Cut the selected text and copy it to the clipboard.
6. `Copy:` Copy the selected text to the clipboard.
7. `Paste:` Paste the contents of the clipboard at the current cursor position.
8. `Zoom In:` Increase the font size of the text.
9. `Zoom Out:` Decrease the font size of the text.
10. `Find:` Find occurrences of a specific search term within the text.
11. `Replace:` Find and replace occurrences of a specific search term with a replacement text.
12. `Toggle Dark Mode:` Switch between the text editor's light and dark theme.

## How to Use
Launch the application by executing the main method in the Notepad class.
The main window will open with an empty text area.
Use the menu options or keyboard shortcuts to perform various operations such as creating a new file, opening an existing file, saving the file, and editing the text.
The line count is displayed at the bottom of the window and updates automatically as you type.
Use the toolbar button `"Toggle Dark Mode"` to switch between light and dark themes.
To zoom in or out on the text, use the `"Zoom In"` and `"Zoom Out"` options in the View menu, or use the corresponding keyboard shortcuts.
Use the `"Find"` and `"Replace"` options in the Edit menu to search for specific text within the document and replace it with other text.
To exit the application, click the close button or select the `"Exit"` option from the File menu. If the file has unsaved changes, a prompt will appear to confirm whether to save the changes before closing.

## Dependencies
This application is built using Java Swing, which is a part of the Java Development Kit (JDK). No additional external dependencies are required.

## Limitations
The application does not support advanced text editing features such as syntax highlighting or code completion.
It does not have file management functionalities like creating directories or renaming files.
The search functionality only finds the first occurrence of the search term and does not support searching in a case-insensitive manner.
The application does not provide an undo/redo feature.

## Contributing
This project is provided as a starting point for building a text editor application in Java Swing. You are welcome to fork the repository, make enhancements or add new features, and submit pull requests with your changes.

If you encounter any issues or have suggestions for improvements, please open an issue on the project's GitHub repository.
