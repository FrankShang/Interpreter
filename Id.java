import java.util.*;

class Id extends Exp
{
	String id;
	
	Id(String s)
	{
		id = s;
	}
	
	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <exp>");
		String indent1 = indent+" ";
		IO.displayln(indent1 + indent1.length() + " " + id);
	}

	TypeVal typeEval(HashMap<String,TypeVal> paramMap, HashMap<Integer,TypeVal> paramNumMap)
	{
		TypeVal idType = paramMap.get(id);
		if ( idType != null )
			return idType;
		else
		{
			IO.displayln("Error: undefined variable "+id+" found");
			return null;			
		}
	}
	//change
	Val Eval(HashMap<String, Val> state){
		Val idVal = state.get(id);
		if(idVal != null)
			return idVal.cloneVal();
		else{
			System.out.println("variable "+ id + "does not have a value" );
			return null;
		}
	}
}