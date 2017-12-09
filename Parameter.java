class Parameter
{
	String type;
	String ident;
	
	Parameter(String t, String id)
	{
		type = t;
		ident = id;
	}
	
	void printParseTree(String indent)
	{
		IO.display(indent + indent.length() + " <parameter> " + type + " ");
		IO.displayln(ident);
	}
}