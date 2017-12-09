import java.util.*;

class Header
{
	String type;
	String funName;
	ParameterList parameterList; // parameterList is null if <parameter list> is empty.
	
	Header(String t, String f, ParameterList p)
	{
		type = t;
		funName = f;
		parameterList = p;
	}

	String returnType()
	{
		return type;
	}

	String funName()
	{
		return funName;
	}

	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <header>");
							  
		String indent1 = indent+" ";

		IO.displayln(indent1 + indent1.length() + " <type> " + type);
		IO.displayln(indent1 + indent1.length() + " <fun name> " + funName);
		if ( parameterList != null )
		{
			IO.displayln(indent1 + indent1.length() + " <parameter list>");
			parameterList.printParseTree(indent1+" ");
		}
	}

	void buildTypeMaps()
	{
		TypeChecker.funTypeMap.put(funName, TypeVal.toTypeVal(type));
		if ( parameterList != null )
		{
			HashMap<String,TypeVal> paramMap = new HashMap<String,TypeVal>();
			HashMap<Integer,TypeVal> paramNumMap = new HashMap<Integer,TypeVal>();
  //when you got the pararamter know its location(1 ,2, 3...) = key then know its name is value
			HashMap<Integer, String> paramNumNameMap = new HashMap<Integer, String>();
			
			parameterList.buildTypeMaps(1, paramMap, paramNumMap, paramNumNameMap);

			TypeChecker.paramTypeMap.put(funName, paramMap);
			TypeChecker.paramNumTypeMap.put(funName, paramNumMap);
			TypeChecker.paramFunNameNumNameMap.put(funName, paramNumNameMap);
		}			
	}
}