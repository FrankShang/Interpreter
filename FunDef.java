import java.util.*;

class FunDef extends FunDefList
{
	Header header;
	Exp exp;
	
	FunDef(Header h, Exp e)
	{
		header = h;
		exp = e;
	}
	
	void printParseTree(String indent)
	{
		String indent1 = indent + " ";
		
		IO.displayln(indent + indent.length() + " <fun def>");		
		header.printParseTree(indent1);
		exp.printParseTree(indent1);
	}

	void buildTypeMaps()
	{
		header.buildTypeMaps();
	}

	TypeVal typeEval()
	{
		String returnType = header.returnType();
		String funName = header.funName();

		HashMap<String,TypeVal> paramMap = TypeChecker.paramTypeMap.get(funName);
		HashMap<Integer,TypeVal> paramNumMap = TypeChecker.paramNumTypeMap.get(funName);

		TypeVal expType = exp.typeEval(paramMap, paramNumMap);

		if ( returnType.equals(expType.toString()) )
			return TypeVal.Correct;
		else
		{
			IO.displayln("Type Error: incompatible return type and body expression type in function "+funName );
			return TypeVal.Error;
		}
	}
}