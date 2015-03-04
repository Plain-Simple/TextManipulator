package plainsimple.textmanipulator;

import c10n.annotations.*;

public interface Messages {

  @En("THE Plain + Simple Text Manipulator")
  String program_full_name();

  @En("Text Manipulator")
  String program_name();

  @En("Basic Tools")
  String basic_tools();

  @En("Line Functions")
  String line_functions();

  @En("Word Functions")
  String word_functions();

  @En("Find & Replace")
  String find_replace();

  @En("Settings")
  String settings();

  @En("accent")
  String accent();

  @En("Plain + Simple Programming, 2015")
  String author_notice();

  @En("File")
  String file_menu();

  @En("Edit")
  String edit_menu();

  @En("Undo")
  String undo();

  @En("Redo")
  String redo();

  @En("Text Analysis")
  String text_analysis();

  @En("Words")
  String analysis_words();

  @En("Chars")
  String analysis_chars();

  @En("Sentences")
  String analysis_sentences();

  @En("Lines")
  String analysis_lines();

  @En("Please enter a command:")
  String cli_function_prompt();

  @En("adds specified argument to the beginning of every line")
  String addprefix_description();

  @En("adds specified argument to the end of every line")
  String addsuffix_description();

  @En("removes all lines that are duplicates of other lines")
  String removeduplicatelines_description();

  @En("removes all lines containing the specified argument")
  String removelinescontaining_description();

  @En("scrambles lines in a random order")
  String scramblelines_description();

  @En("sorts lines alphabetically (by English alphabet)")
  String sortlinesalphabetically_description();

  @En("sorts lines by size")
  String sortlinesbysize_description();

  @En("numbers lines in format specified by user")
  String numberlines_description();

  @En("removes all empty lines")
  String removeemptylines_description();

  @En("merges text of two files/strings, line by line")
  String mergetext_description();

  @En("simple find and replace function")
  String findreplace_description();

  @En("finds argument in text and removes it")
  String removeargument_description();

  @En("replaces spaces with commas, essentially (though it will be improved)")
  String commaseparatevalues_description();

  @En("seperates words line-by-line")
  String lineseparatevalues_description();

  @En("this function has not been written yet")
  String splitsentences_description();

  @En("this function has not been written yet")
  String comparelines_description();

  @En("remove all punctuation from text")
  String removepunctuation_description();

  @En("uppercases all alphabetical characters in the text")
  String uppercase_description();

  @En("lowercases all alphabetical characters in the text")
  String lowercase_description();

  @En("prints contents of specified file")
  String print_description();

  @En("loaded")
  String file_loaded();

  @En("file")
  String file();

  @En("Welcome to the Plain+Simple text manipulator (CLI mode)")
  String cli_welcome();

  @En("Please enter the name of the text file you would like to manipulate in directory. Or, specify the file's full PATH:")
  String file_load_description();

  @En("The command you entered was not recognized")
  String invalid_cli_option();

  @En("Please enter a valid file name")
  String invalid_file();

  @En("No Selection")
  String no_selection();

  @En("Selection")
  String selection();

  @En("Please enter")
  String argument_prompt();

  @En("prefix")
  String prefix();

  @En("suffix")
  String suffix();

  @En("the contents of lines to be removed")
  String containing_what();

  @En("anything to be added before the number")
  String number_prefix();

  @En("anything to be added after the number")
  String number_suffix();

  @En("the text to find")
  String text_to_find();

  @En("the text to replace the found text with")
  String text_for_replace();

  @En("the argument to remove")
  String argument_to_remove();

  @En("Open File")
  String open_file_menu();

  @En("Save File")
  String save_file_menu();

  @En("Error reading settings file")
  String error_reading_settings();

  @En("Error writing default settings")
  String error_writing_default_settings();

  @En("Error: File does not exist in specified path or could not be accessed")
  String error_reading_file();

}
