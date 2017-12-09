import java.util.*;

class ParameterList
{
	Parameters parameters;
	
	ParameterList(Parameters ps)
	{
		parameters = ps;
	}
	
	void printParseTree(String indent)
	{
		parameters.printParseTree(indent);
	}

	void buildTypeMaps(int i, HashMap<String,TypeVal> paramMap, HashMap<Integer,TypeVal> paramNumMap,
			            HashMap<Integer,String> paramNumNameMap )
	{
		parameters.buildTypeMaps(i, paramMap, paramNumMap, paramNumNameMap);
	}
}