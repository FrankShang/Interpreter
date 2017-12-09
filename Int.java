import java.util.*;

class Int extends Exp
{
	int intElem;
	
	Int(int i)
	{
		intElem = i;
	}
//	int get(){
//		return intElem;
//	}
	
	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " <exp>");
		String indent1 = indent+" ";
		IO.displayln(indent1 + indent1.length() + " " + intElem);
	}

	TypeVal typeEval(HashMap<String,TypeVal> paramMap, HashMap<Integer,TypeVal> paramNumMap)
	{
		return TypeVal.Int;
	}	
	//change
	Val Eval(HashMap<String,Val> state){
		return new IntVal(intElem);
	}
}