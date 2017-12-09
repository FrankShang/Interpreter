class MultipleFunDef extends FunDefList
{
	FunDef funDef;
	FunDefList funDefList;
	
	MultipleFunDef(FunDef fdef, FunDefList fdeflist)
	{
		funDef = fdef;
		funDefList = fdeflist;
	}
	
	void printParseTree(String indent)
	{
		funDef.printParseTree(indent);
		IO.displayln("\n--------------------\n");
		funDefList.printParseTree(indent);
	}

	void buildTypeMaps()
	{
		funDef.buildTypeMaps();
		funDefList.buildTypeMaps();
	}
	
	TypeVal typeEval()
	{
		TypeVal funDefType = funDef.typeEval();
		TypeVal funDefListType = funDefList.typeEval();

		if ( funDefType == TypeVal.Correct && funDefListType == TypeVal.Correct )
			return TypeVal.Correct;
		else
			return TypeVal.Error;
	}
}