Our Model is made up of an IWorksheet, which contains a HashMap of <Coords, ICells>. Each of these Cells contains a Formula which is one of Value, Function, or Reference. 

	A Value is one of BooleanValue, StringValue, or NumberValue. 
		Each of these Value classes contains a raw data field of their associated type.

	A Function is a Formula that contains a list of Formulas, and has some way to evaluate them.
		Each Function in our model has a visitor for each type of raw data that it needs to handle and manipulate based
		on the formulas it contains.

	A Reference is a Formula that contains a HashMap <Coords, ICells> referencing other ICells based on the Coord keys.

Our Views Takes in a ReadableWorksheet of ReadableCells, which are then represented textually or graphically.
	
	The textual view shows a list of all cells that are currently saved within our model.

	The graphical view shows a 13x13 section of the sheet, and displays the values within those cells. We have scrolling buttons that allow the user to move the view up, down, left, or right in this view.

	The editable graphical view takes in a graphical view that it decorates with a text box and enter button. It uses a controller to make update requests to the model. Our editable view uses a Mouse listener to send Select Coordinate requests to the controller as Coord objects. We also have an action listener that waits for the enter button to be pressed, sending a new Formula to the controller, asking it to be saved to the selected cell.

Our controller is able to provide our view with a mock model. It is also able to select a cell in the model, as requested by the view. It can also update the value of the selected cell to match the value entered in the text field when the enter button is pressed.
	
	Our controller responds to requests in the editable view that are triggered by MouseListeners, KeyListeners, and ActionListeners. We do not import any swing information in our controller, and the implementation is general to any front end represenation that is able to send Coord and Formula values (which are as general as we could make it using our IWorksheet<ICell>).

**CHANGES FROM 6 to 7**
	We were unable to scroll in our previous assignment. This has been corrected. We were having an issue previously with getting multiple JPanels to appear on the JFrame. 

	Our ICell now has a getFormula method that more easily returns the contents of a cell as a Formula object, instead of converting it to a string first, which we were planning on using before.

**EXTRA CREDIT**
	~wow if you have the textbox field selected you can use the arrow keys to move around the selected cell. The render() method in the editable cell view has the textBox request the focus at the end of each action(except for typing in the text box), so you should be able to do this at most times without having to reselect the text box. This implementation uses Key Listeners in the Editable graphical view that send a Select Coordinate request to the controller.

	~wowee if you desired to save your work, you can now. Just hit save and the program will over-write your file with a the information that you saved, so once you close it, you can come back to it!  It uses a WorksheetWriter class to write the information back to the file in a readable form.  In order to do this, we had to add a file that holds the file path and button that listens for saving the state.

To test our file, just type: java -jar SpreadSheetApp.jar File.txt -command

You can use any file in the resources package.

Our commands are as follows:
          -edit
          -gui
          -text

We love you.