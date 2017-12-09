import java.util.*;

class Parameters
{
	Parameter parameter;
	Parameters parameters; // parameters is null at the end of the list.
	
	Parameters(Parameter p, Parameters ps)
	{
		parameter = p;
		parameters = ps;
	}
	
	void printParseTree(String indent)
	{
		parameter.printParseTree(indent);
		if ( parameters != null )
			parameters.printParseTree(indent);
	}

	void buildTypeMaps(int i, HashMap<String,TypeVal> paramMap, HashMap<Integer,TypeVal> paramNumMap,
			HashMap<Integer,String> paramNumNameMap)
	{
		String paramId = parameter.ident;
		String paramType = parameter.type;

		if ( paramMap.get(paramId) != null )
			IO.displayln("parameter "+paramId+" already declared");
		else
		{
			paramMap.put(paramId, TypeVal.toTypeVal(paramType));
			paramNumMap.put(i, TypeVal.toTypeVal(paramType));
			paramNumNameMap.put(i, paramId);
		}
		if ( parameters != null )
			parameters.buildTypeMaps(i+1, paramMap, paramNumMap, paramNumNameMap);
	}
}